package de.hicedevelopments.princemusicapp.ui.releasedetail.imageslider

import androidx.navigation.fragment.navArgs
import de.hicedevelopments.princemusicapp.BR
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.DataBindingDialog
import de.hicedevelopments.princemusicapp.data.model.Image
import de.hicedevelopments.princemusicapp.databinding.DialogImageSliderBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind

class ImageSliderDialog : DataBindingDialog<DialogImageSliderBinding>() {

    override val layoutId: Int = R.layout.dialog_image_slider
    override val cancelable: Boolean = true

    private val args: ImageSliderDialogArgs by navArgs()

    val images: List<Image> by lazy { args.images.asList() }
    val imageBinding: OnItemBind<Image> = OnItemBind { itemBinding, position, item ->
        itemBinding.set(BR.image, R.layout.item_image_slide)
        itemBinding.bindExtra(BR.dialog, this)
    }

    override fun bind(binding: DialogImageSliderBinding) {
        binding.dialog = this
    }
}