package com.rajit.customtabsexample

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.rajit.customtabsexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val url = arrayListOf<String>(
            "https://www.tutorialspoint.com/java/index.htm"
        )

        _binding.openUrlBtn.setOnClickListener { loadURLInCustomTab(url.random()) }

    }

    private fun loadURLInCustomTab(url: String) {

        // Light Mode Toolbar Color
        val lightModeColor = ContextCompat.getColor(
            this@MainActivity,
            R.color.colorprimary
        )

        // Dark Mode Toolbar Color
        val darkModeColor = ContextCompat.getColor(
            this@MainActivity,
            R.color.colorprimary
        )

        // Setting the Custom Tab Toolbar Configuration for Light Mode
        val customTabToolbarColorLight = CustomTabColorSchemeParams
            .Builder()
            .setToolbarColor(lightModeColor)
            .build()

        // Setting the Custom Tab Toolbar Configuration for Dark Mode
        val customTabToolbarColorDark = CustomTabColorSchemeParams
            .Builder()
            .setToolbarColor(darkModeColor)
            .build()

        // Create a Custom Tab Intent
        // Which will open the browser inside the app without an overhead
        CustomTabsIntent.Builder().apply {

            // Show Title of the Website in Custom Tab ToolBar
            setShowTitle(true)

            // applying light mode color
            setDefaultColorSchemeParams(customTabToolbarColorLight)

            // applying dark mode color
            setColorSchemeParams(CustomTabsIntent.COLOR_SCHEME_DARK, customTabToolbarColorDark)

            // ENTER & EXIT ANIMATION
            setStartAnimations(this@MainActivity, R.anim.slide_in_right, R.anim.slide_out_left)
            setExitAnimations(this@MainActivity, android.R.anim.slide_in_left, android.R.anim.slide_out_right)

            // Build & Launch URL in Custom Tab
            build().launchUrl(this@MainActivity, Uri.parse(url))
        }
    }

}