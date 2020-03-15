package com.shivamsatija.wikisearch.ui.base

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.shivamsatija.wikisearch.WikiSearchApplication
import com.shivamsatija.wikisearch.di.component.ActivityComponent
import com.shivamsatija.wikisearch.di.component.DaggerActivityComponent
import com.shivamsatija.wikisearch.di.module.ActivityModule

abstract class BaseFragment : Fragment(), MvpView {

    @LayoutRes
    abstract fun getLayoutResource(): Int

    abstract fun performDependencyInjection(activityComponent: ActivityComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityComponent = DaggerActivityComponent.builder()
            .applicationComponent(
                (requireActivity().application as WikiSearchApplication)
                    .getApplicationComponent()
            )
            .activityModule(
                ActivityModule(requireActivity())
            )
            .build()

        performDependencyInjection(activityComponent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResource(), container, false)
    }

    override fun showToast(message: String?) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}