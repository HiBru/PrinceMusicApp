package de.hicedevelopments.princemusicapp.ui.releaselist

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import de.hicedevelopments.princemusicapp.BR
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.BaseViewModel
import de.hicedevelopments.princemusicapp.app.pagination.ReleasesDiff
import de.hicedevelopments.princemusicapp.app.pagination.ReleasesSourceFactory
import de.hicedevelopments.princemusicapp.common.SingleLiveEvent
import de.hicedevelopments.princemusicapp.data.model.Release
import de.hicedevelopments.princemusicapp.data.repository.Repo
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListItem
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListListener
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListRow
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListSection
import me.tatarka.bindingcollectionadapter2.OnItemBind

const val INITIAL_LOAD_SIZE = 50
const val PAGINATION_PAGE_SIZE = 50
const val PAGINATION_PREFETCH = 25

class ReleaseListViewModel(
    private val repo: Repo
) : BaseViewModel() {

    enum class ReleaseListEvent {
        SCROLL_TO_TOP
    }

    var itemClickListener: ReleaseListListener? = null

    val eventStream = SingleLiveEvent<ReleaseListEvent>()
    val pagedReleases: LiveData<PagedList<ReleaseListRow>> by lazy {
        val pagingConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE)
            .setPageSize(PAGINATION_PAGE_SIZE)
            .setPrefetchDistance(PAGINATION_PREFETCH)
            .build()
        val sourceFactory = ReleasesSourceFactory(repo, this) { loading -> isLoading.postValue(loading) }
        LivePagedListBuilder(sourceFactory, pagingConfig).build()
    }
    val releasesDiff: DiffUtil.ItemCallback<ReleaseListRow> = ReleasesDiff()
    val releasesItemBinding: OnItemBind<ReleaseListRow> = OnItemBind { itemBinding, position, item ->
        when(item) {
            is ReleaseListItem -> {
                itemBinding.set(BR.releaseItem, R.layout.item_release)
                itemBinding.bindExtra(BR.position, position)
                itemBinding.bindExtra(BR.transitionName, "${item.id}")
                itemBinding.bindExtra(BR.clickListener, itemClickListener)
            }
            is ReleaseListSection -> {
                itemBinding.set(BR.section, R.layout.item_release_section)
            }
        }

    }

    fun refresh() = pagedReleases.value?.dataSource?.invalidate()

    fun onEvent(event: ReleaseListEvent) = eventStream.postValue(event)
}