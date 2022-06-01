package de.hicedevelopments.princemusicapp.ui.releasedetail

import androidx.lifecycle.MutableLiveData
import de.hicedevelopments.princemusicapp.BR
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.BaseViewModel
import de.hicedevelopments.princemusicapp.common.SingleLiveEvent
import de.hicedevelopments.princemusicapp.data.model.Master
import de.hicedevelopments.princemusicapp.data.model.Tracklist
import de.hicedevelopments.princemusicapp.data.model.VideoLink
import de.hicedevelopments.princemusicapp.data.repository.Repo
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseType
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseType.MASTER
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseType.RELEASE
import kotlinx.coroutines.flow.collect
import me.tatarka.bindingcollectionadapter2.OnItemBind

class ReleaseDetailViewModel(
    private val repo: Repo
) : BaseViewModel() {

    enum class ReleaseDetailEvent {
        IMAGE_CLICK
    }

    val eventStream = SingleLiveEvent<ReleaseDetailEvent>()

    var videoLinkListener: VideoLinkListener? = null

    val detailItem = MutableLiveData<Master>()
    val tracklist = MutableLiveData<List<Tracklist>>()
    val tracklistBinding : OnItemBind<Tracklist> = OnItemBind { itemBinding, position, item ->
        val bg = if(position.mod(2) == 0) R.drawable.even_tracklist_item_bg else R.drawable.odd_tracklist_item_bg
        itemBinding.set(BR.tracklist, R.layout.item_tracklist)
        itemBinding.bindExtra(BR.item_bg, bg)
    }
    val videoBinding : OnItemBind<VideoLink> = OnItemBind { itemBinding, position, item ->
        itemBinding.set(BR.video, R.layout.item_video)
        itemBinding.bindExtra(BR.videoListener, videoLinkListener)
    }

    fun loadDetails(type: ReleaseType?, id: String) {
        when(type) {
            MASTER -> loadMaster(id)
            RELEASE -> loadRelease(id)
            else -> return
        }
    }

    private fun loadRelease(id: String) = asyncWithLoadingState {
        repo.getRelease(id).collect { release ->
            detailItem.postValue(release)
            tracklist.postValue(release?.tracklist?.filter { it.type.equals("track") })
        }
    }

    private fun loadMaster(id: String) = asyncWithLoadingState {
        repo.getMaster(id).collect { master ->
            detailItem.postValue(master)
            tracklist.postValue(master?.tracklist?.filter { it.type.equals("track") })
        }
    }

    fun onEvent(event: ReleaseDetailEvent) = eventStream.postValue(event)
}