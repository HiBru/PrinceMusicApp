package de.hicedevelopments.princemusicapp.app.pagination

import androidx.paging.PageKeyedDataSource
import de.hicedevelopments.princemusicapp.data.model.Release
import de.hicedevelopments.princemusicapp.data.repository.Repo
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListItem
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListRow
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListSection
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

class ReleasesDataSource(
    val repo: Repo,
    val isLoading: (isLoading: Boolean) -> Unit
) : PageKeyedDataSource<Int, ReleaseListRow>() {

    var releaseYear: Int = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ReleaseListRow>
    ) {
        runSave {
            repo.getReleases(params.requestedLoadSize, 1).collect { response ->
                response?.releases?.let { items ->
                    val nextPageKey = if (items.size < params.requestedLoadSize) null else 2
                    val rows = items.mapToReleaseRows()
                    callback.onResult(rows, 0, rows.count(), null, nextPageKey)
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ReleaseListRow>) {
        runSave {
            repo.getReleases(params.requestedLoadSize, params.key).collect { response ->
                response?.releases?.let { items ->
                    val nextPageKey = if (items.count() < params.requestedLoadSize) null else params.key+1
                    val rows = items.mapToReleaseRows()
                    callback.onResult(rows, nextPageKey)
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ReleaseListRow>) {
        runSave {
            repo.getReleases(params.requestedLoadSize, params.key).collect { response ->
                response?.releases?.let { items ->
                    val nextPageKey = if (params.key > 2) params.key-1 else 1
                    val rows = items.mapToReleaseRows()
                    callback.onResult(rows, nextPageKey)
                }
            }
        }
    }

    private fun List<Release>.mapToReleaseRows(): List<ReleaseListRow> {
        val groupedReleases = this
            .filter { it.artist.equals("prince", true) && it.year >= releaseYear}
            .groupBy { it.year }
        val itemsWithSections = mutableListOf<ReleaseListRow>()

        groupedReleases.forEach { entry ->
            if (entry.key > releaseYear) {
                itemsWithSections.add(ReleaseListSection("${entry.key}"))
            }
            itemsWithSections.addAll(entry.value.map { ReleaseListItem(it) })
        }

        releaseYear = last().year

        return itemsWithSections
    }

    private fun runSave(bgFunction: suspend () -> Unit = {}) {
        isLoading(true)
        runBlocking {
            bgFunction()
        }
        isLoading(false)
    }
}