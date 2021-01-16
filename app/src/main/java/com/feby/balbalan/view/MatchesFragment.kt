package com.feby.balbalan.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import com.feby.balbalan.R
import com.feby.balbalan.adapter.ViewPagerAdapter
import com.feby.balbalan.view.lastmatch.LastMatchFragment
import com.feby.balbalan.view.upcomingmatch.UpcomingMatchFragment

class  MatchesFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vPager = view.findViewById<ViewPager>(R.id.viewpager)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)
        val adapter = ViewPagerAdapter(childFragmentManager)
        setHasOptionsMenu(true)
        adapter.listFragment(LastMatchFragment(), getString(R.string.last_match))
        adapter.listFragment(UpcomingMatchFragment(), getString(R.string.upcoming_match))
        vPager.adapter = adapter
        tabs.setupWithViewPager(vPager)
    }

}