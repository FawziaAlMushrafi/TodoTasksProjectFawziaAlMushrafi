package com.yameen.todotasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        val arg = Bundle()
        val eventfr: Fragment = MyTasksFragment()
        eventfr.arguments = arg
        val fragmentmanegar = supportFragmentManager.beginTransaction()
        fragmentmanegar.replace(R.id.fragment_container, eventfr).addToBackStack("1")
        .commit()

    }

    override fun onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }

    }
}