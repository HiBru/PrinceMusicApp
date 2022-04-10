package de.hicedevelopments.princemusicapp.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import de.hicedevelopments.princemusicapp.app.extension.showAlert
import de.hicedevelopments.princemusicapp.data.remote.NetworkErr

abstract class ResourceFragment<VB : ViewDataBinding> : BaseFragment() {

    private val progressOverlay: ProgressOverlay by lazy { ProgressOverlay(requireContext()) }

    private lateinit var binding: VB
    protected abstract val layoutId: Int
    protected abstract val viewModel: BaseViewModel

    protected abstract fun bindViewModel(binding: VB)

    open fun showLoading(isLoading: Boolean) = progressOverlay.show(isLoading)

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
        bindViewModel(binding)
        bindObserver()
    }

    private fun bindObserver() {
        viewModel.isLoading.observe(viewLifecycleOwner) { showLoading(it) }
        viewModel.errorState.observe(viewLifecycleOwner) { handlerErrorState(it) }
    }

    private fun handlerErrorState(err: NetworkErr) = showAlert(err.title, err.desc)
}