package com.feby.balbalan.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.bottom_nav_view.*
import com.feby.balbalan.R
import com.feby.balbalan.view.listteam.TeamFragment

class MainActivity : AppCompatActivity() {

    private var view: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, MatchesFragment())
                .commit()
        }
        bottom_navigation.setItemSelected(R.id.match)
        bottom_navigation.setOnItemSelectedListener { id ->
            when (id) {
                R.id.match -> {
                    view = MatchesFragment()
                }
                R.id.teams -> {
                    view = TeamFragment()
                }
                R.id.favorite -> {
                    view = FavoriteFragment()
                }
            }

            view!!.let {
                supportFragmentManager.beginTransaction().replace(R.id.main_container, view!!)
                    .commit()
            }
        }
    }
}
