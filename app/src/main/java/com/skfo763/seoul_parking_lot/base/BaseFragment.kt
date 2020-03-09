package com.skfo763.seoul_parking_lot.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skfo763.presentation.ViewModelFactory
import com.skfo763.seoul_parking_lot.BR
import javax.inject.Inject

abstract class BaseFragment<B: ViewDataBinding, V: ViewModel> : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var viewModel: V
    lateinit var binding: B

    @LayoutRes abstract fun layoutResId(): Int
    abstract fun getViewModel(): Class<V>

    abstract fun executeInject()

    abstract fun initObserver()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        executeInject()
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModel())
        binding = DataBindingUtil.inflate(inflater, layoutResId(), container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.mapViewModel, viewModel)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initObserver()
    }

    fun showShortToast(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }
}