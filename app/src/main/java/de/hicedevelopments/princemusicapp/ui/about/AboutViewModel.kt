package de.hicedevelopments.princemusicapp.ui.about

import androidx.lifecycle.MutableLiveData
import de.hicedevelopments.princemusicapp.BR
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.BaseViewModel
import de.hicedevelopments.princemusicapp.common.SingleLiveEvent
import de.hicedevelopments.princemusicapp.data.model.About
import de.hicedevelopments.princemusicapp.data.model.AboutType
import de.hicedevelopments.princemusicapp.data.model.WebLink
import de.hicedevelopments.princemusicapp.data.model.WebLinkListener
import de.hicedevelopments.princemusicapp.data.repository.Repo
import kotlinx.coroutines.flow.collect
import me.tatarka.bindingcollectionadapter2.OnItemBind

class AboutViewModel(
    private val repo: Repo
) : BaseViewModel() {

    enum class AboutEvent {
        IMAGE_CLICK
    }

    val eventStream = SingleLiveEvent<AboutEvent>()
    val aboutModel = MutableLiveData<About>()
    var webLinkListener: WebLinkListener? = null

    val webLinkBinding: OnItemBind<WebLink> = OnItemBind { itemBinding, position, item ->
        itemBinding.set(BR.webLink, R.layout.item_weblink)
        itemBinding.bindExtra(BR.clickListener, webLinkListener)
    }

    fun loadAboutItem(type: AboutType) = asyncWithLoadingState(withLoadingState = false) {
        repo.getAboutItem(type).collect { item ->
            aboutModel.postValue(item)
        }
    }

    fun onEvent(event: AboutEvent) = eventStream.postValue(event)
}