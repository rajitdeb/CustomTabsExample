package com.rajit.customtabsexample

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.rajit.customtabsexample.databinding.ActivityMainBinding

/*
 * Custom Tabs in Android
 *
 * For more reference:
 * https://developer.chrome.com/docs/android/custom-tabs/guide-get-started
 */

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val url = arrayListOf<String>(
            "https://www.youtube.com/watch?v=yXiRLGwbi1M",
            "https://www.tutorialspoint.com/java/index.htm",
            "https://www.linkedin.com/in/imrajit"
        )

        _binding.openUrlBtn.setOnClickListener { loadURLInCustomTab(url.random()) }

    }

    /**
     * Load @param [url] in the Custom Tav
     * [CustomTabsIntent.Builder] is used to build the custom tab along with the specified customizations
     **/
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

        val customCloseIcon = AppCompatResources.getDrawable(this@MainActivity, R.drawable.ic_back)

        // Create a Custom Tab Intent - Which will open the browser inside the app without an overhead
        CustomTabsIntent.Builder().apply {

            // Show Title of the Website in Custom Tab ToolBar
            setShowTitle(true)

            // applying light mode color
            setDefaultColorSchemeParams(customTabToolbarColorLight)

            // applying dark mode color
            setColorSchemeParams(
                CustomTabsIntent.COLOR_SCHEME_DARK,
                customTabToolbarColorDark
            )

            // Set Custom Close Button for Custom Tab
            setCloseButtonIcon(customCloseIcon!!.toBitmap())

            // ENTER ANIMATION
            setStartAnimations(
                this@MainActivity,
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )

            // EXIT ANIMATION
            setExitAnimations(
                this@MainActivity,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )

            // Build & Launch URL in Custom Tab
            build().launchUrl(
                this@MainActivity,
                Uri.parse(url)
            )
        }
    }

}