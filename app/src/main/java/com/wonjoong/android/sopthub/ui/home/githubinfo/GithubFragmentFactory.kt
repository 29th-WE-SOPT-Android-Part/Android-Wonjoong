package com.wonjoong.android.sopthub.ui.home.githubinfo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.wonjoong.android.sopthub.ui.home.GithubFragmentType

class GithubFragmentFactory(private val fragmentType: GithubFragmentType) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            GithubFragment::class.java.name -> GithubFragment(fragmentType)
            else -> super.instantiate(classLoader, className)
        }
    }
}