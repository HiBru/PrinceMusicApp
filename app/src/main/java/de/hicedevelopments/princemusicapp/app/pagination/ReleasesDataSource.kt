package de.hicedevelopments.princemusicapp.app.pagination

import android.util.Log
import androidx.paging.PageKeyedDataSource
import de.hicedevelopments.princemusicapp.data.model.Release
import de.hicedevelopments.princemusicapp.data.remote.ErrorHandler
import de.hicedevelopments.princemusicapp.data.repository.Repo
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListItem
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListRow
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListSection
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

class ReleasesDataSource(
    private val repo: Repo,
    private val errorHandler: ErrorHandler,
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
            .filter {
                it.artist?.contains("prince", true) ?: false
                        && it.year >= releaseYear
            }
            .groupBy { it.year }
        val itemsWithSections = mutableListOf<ReleaseListRow>()

        if (groupedReleases.isEmpty()) return itemsWithSections

        groupedReleases.forEach { entry ->
            if (entry.key > releaseYear) {
                itemsWithSections.add(ReleaseListSection("${entry.key}"))
            }
            itemsWithSections.addAll(entry.value.map { ReleaseListItem(it) })
        }

        releaseYear = groupedReleases.keys.last()

        return itemsWithSections
    }

    private fun runSave(bgFunction: suspend () -> Unit = {}) {
        isLoading(true)
        runBlocking {
            catchError { bgFunction() }
        }
        isLoading(false)
    }

    private suspend fun catchError(toGuard: suspend () -> Any) {
        try {
            toGuard()
        } catch (e: Exception) {
            Log.e("ASYNC ERROR", e.message.toString())
            errorHandler.handleException(e)
        }
    }
}