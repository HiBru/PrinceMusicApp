package de.hicedevelopments.princemusicapp.app

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.extension.showAlert
import de.hicedevelopments.princemusicapp.data.remote.NetworkErr

abstract class ResourceFragment<VB : ViewDataBinding> : BaseFragment() {

    private val progressOverlay: ProgressOverlay by lazy { ProgressOverlay(requireContext()) }

    protected open var optionsMenu: Int? = null

    protected lateinit var binding: VB

    protected abstract val layoutId: Int
    protected abstract val viewModel: BaseViewModel

    protected abstract fun bindViewModel(binding: VB)

    open fun showLoading(isLoading: Boolean) = progressOverlay.show(isLoading)
    open fun onErrorMessageButtonClick(err: NetworkErr, dialog: DialogInterface) {
        Log.e("ERROR MESSAGE CLICK", err.toString())
        dialog.dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(optionsMenu != null)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        optionsMenu?.let {
            inflater.inflate(it, menu)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setOnApplyWindowInsetsListener { v, insets ->
            val toolbar: Toolbar? = v.findViewById(R.id.toolbar)
            if (toolbar != null) {
                val layoutParams: ViewGroup.MarginLayoutParams = toolbar.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.setMargins(0, insets.systemWindowInsetTop, 0, 0)
                toolbar.layoutParams = layoutParams
            }
            insets.consumeSystemWindowInsets()
        }
        bindViewModel(binding)
        bindObserver()
    }

    private fun bindObserver() {
        viewModel.isLoading.observe(viewLifecycleOwner) { showLoading(it) }
        viewModel.errorState.observe(viewLifecycleOwner) { handlerErrorState(it) }
    }

    private fun handlerErrorState(err: NetworkErr) = showAlert(err.title, err.desc) { dialog ->
        onErrorMessageButtonClick(err, dialog)
    }
}