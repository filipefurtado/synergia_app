package com.example.synergianewapp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TabsAdapter (manager: FragmentManager): FragmentPagerAdapter(manager){
    private val fragmentList : MutableList<Fragment> =  ArrayList()
    private val titleList : MutableList<String> = ArrayList()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment: Fragment, title:String){
        fragmentList.add(fragment)
        titleList.add(title)
    }
    override fun getPageTitle(position: Int): CharSequence?{
        return titleList[position]
    }

}