package ir.siriusapps.ghalam

import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigationView()
    }

    fun initNavigationView() {
        navigationView.itemBackground = navigationItemBackground()
        navigationView.menu[0].isChecked = true

        val subMenu = navigationView.menu[1].subMenu
        subMenu.add(R.id.labelsGroup, 123, Menu.NONE, "Test")
        subMenu.add(R.id.labelsGroup, 124, Menu.NONE, "Test 1")
        subMenu.add(R.id.labelsGroup, 125, Menu.NONE, "Test 2")
    }

    private fun navigationItemBackground(): Drawable? {
        var background = AppCompatResources.getDrawable(
            applicationContext,
            R.drawable.navigation_item_background_rounded_right)

        if (background != null) {
            val tint = AppCompatResources.getColorStateList(
                applicationContext, R.color.navigation_item_background_tint
            )

            background = DrawableCompat.wrap(background.mutate())

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                background.setTintList(tint)
            } else {
                DrawableCompat.setTintList(background, tint)
            }
        }

        return background
    }
}
