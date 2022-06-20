package de.hicedevelopments.princemusicapp.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import de.hicedevelopments.princemusicapp.R

abstract class DataBindingDialog<VB : ViewDataBinding> : DialogFragment() {

    protected lateinit var binding: VB

    protected abstract val layoutId: Int
    protected abstract val cancelable: Boolean

    protected open var fm: FragmentManager? = null

    abstract fun bind(binding: VB)

    open fun show() {
        fm?.let { fragmentManager ->
            if (isVisible) return
            show(fragmentManager, javaClass.simpleName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
        isCancelable = cancelable
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setWindowAnimations(R.style.DialogAnimation)
        bind(binding)
    }
}