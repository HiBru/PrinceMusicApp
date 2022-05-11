package de.hicedevelopments.princemusicapp.app.pagination

import androidx.paging.DataSource
import de.hicedevelopments.princemusicapp.data.model.Release
import de.hicedevelopments.princemusicapp.data.model.Result
import de.hicedevelopments.princemusicapp.data.repository.Repo
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListRow

class ReleasesSourceFactory(
    private val repo: Repo,
    private val isLoading: (isLoading: Boolean) -> Unit
) : DataSource.Factory<Int, ReleaseListRow>() {
    override fun create(): DataSource<Int, ReleaseListRow> = ReleasesDataSource(repo, isLoading)
}