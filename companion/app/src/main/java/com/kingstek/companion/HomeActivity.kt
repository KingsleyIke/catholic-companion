package com.kingstek.companion

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kingstek.companion.databinding.ActivityHomeBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.kingstek.companion.ui.BaseViewModel
import kotlin.properties.Delegates


class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: BaseViewModel
    private lateinit var loggedInVal: MutableLiveData<Boolean>
    private lateinit var logInMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)


        viewModel = ViewModelProvider(this)[BaseViewModel::class.java]
        viewModel.isUserSignedIn()

        /**
         * Set drawer layout navigation,
         * matching ids with nav graph makes onclick work without extra code
         */
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_news,
                R.id.nav_parish,
                R.id.nav_calender,
                R.id.nav_catechism,
                R.id.nav_prayers,
                R.id.nav_readings,
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        /**
         * Set log in or sign out text depending on user sign in status
         */
        var navMenu = navView.menu
        logInMenuItem = navMenu.findItem(R.id.logInFragment)

        //todo fix observe not working fin


//        loggedInVal.observe(this) {
//            if(it) {
//                logInMenuItem.title = resources.getString(R.string.sign_out)
//            } else {
//                logInMenuItem.title = resources.getString(R.string.log_in_as_contributor)
//            }
//        }

        loggedInVal = viewModel.loggegIn
        loggedInVal.observe(this, Observer {
            if(it) {
                logInMenuItem.title = resources.getString(R.string.sign_out)
            } else {
                logInMenuItem.title = resources.getString(R.string.log_in_as_contributor)
            }
        })

        if (loggedInVal.value!!) {
            logInMenuItem.title = resources.getString(R.string.sign_out)
        } else {
            logInMenuItem.title = resources.getString(R.string.log_in_as_contributor)
        }

        /**
         * Set onclick listener for drawerlayout menu items
         */
        logInMenuItem.setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {

            //todo show alert dialog first before signOut?

            if (viewModel.loggegIn.value!!) {
                viewModel.logOut()
                viewModel.loggegIn.postValue(false)
                viewModel.isUserSignedIn()
                logInMenuItem.title = resources.getString(R.string.log_in_as_contributor)

                Toast.makeText(this@HomeActivity, "Signed Out", Toast.LENGTH_SHORT).show()
            } else {
                drawerLayout.close()
                viewModel.loggegIn.postValue(true)
                logInMenuItem.title = resources.getString(R.string.sign_out)

                navController.navigate(R.id.logInFragment)
            }
            true
        })

        /*
        navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId
            //it's possible to do more actions on several items, if there is a large amount of items I prefer switch(){case} instead of if()
            if (id == R.id.settings) {
                Toast.makeText(applicationContext, "CLICKED SETTINGS", Toast.LENGTH_SHORT).show()
            }
            //This is for maintaining the behavior of the Navigation view
            NavigationUI.onNavDestinationSelected(menuItem, navController)
            //This is for closing the drawer after acting on it
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        })
        */
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        TODO("Not yet implemented")
//    }

    override fun onResume() {
        super.onResume()
        viewModel.isUserSignedIn()
        if (loggedInVal.value!!) {
            logInMenuItem.title = resources.getString(R.string.sign_out)
        } else {
            logInMenuItem.title = resources.getString(R.string.log_in_as_contributor)
        }
    }
}