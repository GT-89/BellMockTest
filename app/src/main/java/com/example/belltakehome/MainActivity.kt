package com.example.belltakehome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.belltakehome.databinding.ActivityMainBinding
import com.example.belltakehome.fragments.FragmentHome
import com.example.belltakehome.toothpick.ActivityModule
import com.example.belltakehome.toothpick.ActivityScope
import com.example.belltakehome.toothpick.ApplicationScope
import toothpick.ktp.KTP
import toothpick.smoothie.lifecycle.closeOnDestroy

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHost: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeToothpick()
        setupNavigationVariables()
        setupActionBar()
        setupNavigationDrawer()
        setupSearch()
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    private fun initializeToothpick() {
        KTP
            .openRootScope()
            .openSubScope(ApplicationScope::class.java)
            .openSubScope(ActivityScope::class.java)
            .installModules(ActivityModule(this))
            .closeOnDestroy(this)
            .inject(this)
    }

    private fun setupActionBar() {
        appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.appbarToolbar.setupWithNavController(navController, appBarConfiguration)
        setSupportActionBar(binding.appbarToolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupNavigationDrawer() {
        drawerLayout = binding.navDrawerLayout

        // Close the soft keyboard when you open or close the Drawer
        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.appbarToolbar,
            R.string.nav_drawer_open,
            R.string.nav_drawer_close) {
            override fun onDrawerClosed(drawerView: View) {
                // Triggered once the drawer closes
                super.onDrawerClosed(drawerView)
                applicationContext.hideKeyboard(drawerView)
            }

            override fun onDrawerOpened(drawerView: View) {
                // Triggered once the drawer opens
                super.onDrawerOpened(drawerView)
                applicationContext.hideKeyboard(drawerView)
            }
        }
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        binding.appbarToolbar.setupWithNavController(navController, appBarConfiguration)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupNavigationVariables() {
        navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
    }

    private fun setupSearch() {
        binding.svAppbarSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                applicationContext.hideKeyboard(binding.svAppbarSearch)
                query?.let { searchQuery ->
                    updateFragmentWithSearchQuery(searchQuery)
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

    private fun updateFragmentWithSearchQuery(searchQuery: String) {
        // TODO: check which fragment its on, and change if necessary (back to home)
        (navHost.childFragmentManager.fragments[0] as FragmentHome).updateSearchResults(searchQuery)
        navHost.childFragmentManager.primaryNavigationFragment?.let {
            (it as FragmentHome).updateSearchResults(searchQuery)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            // Check fragment count on back stack
            if (supportFragmentManager.backStackEntryCount > 0) {
                // Go to the previous fragment
                supportFragmentManager.popBackStack()
            } else {
                // Exit the app
                super.onBackPressed()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}