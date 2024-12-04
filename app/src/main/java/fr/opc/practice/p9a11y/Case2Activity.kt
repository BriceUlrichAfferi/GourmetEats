package fr.opc.practice.p9a11y

import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import fr.opc.practice.p9a11y.databinding.ActivityCase2Binding

class Case2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var isFavourite = false
        var isFirstEncounter = true

        // Add custom action to recipeCard
        binding.recipeCard.accessibilityDelegate = object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                val customAction = AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                    AccessibilityNodeInfoCompat.ACTION_CLICK,
                    getString(R.string.view_recipe_details)
                )
                AccessibilityNodeInfoCompat.wrap(info).addAction(customAction)
            }
        }

        // Add custom action to favoriteButton
        binding.favouriteButton.accessibilityDelegate = object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(host, info)

                val toggleFavoriteAction = AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                    AccessibilityNodeInfoCompat.ACTION_CLICK,
                    if (isFavourite) getString(R.string.remove_favourite)
                    else getString(R.string.add_to_favourite)
                )
                AccessibilityNodeInfoCompat.wrap(info).addAction(toggleFavoriteAction)
            }
        }

        // Handle favorite button click
        binding.favouriteButton.setOnClickListener {
            isFavourite = !isFavourite
            isFirstEncounter = false
            setFavouriteButtonIcon(isFavourite)
        }


        binding.addRecipeToBasket.setOnClickListener {
            Toast.makeText(this, getString(R.string.recette_ajout_au_panier), Toast.LENGTH_SHORT)
                .show()
        }

        binding.recipeCard.setOnClickListener {
            Toast.makeText(
                this,
                getString(R.string.recipe_added_toast_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setFavouriteButtonIcon(isFavourite: Boolean) {
        if (isFavourite) {
            binding.favouriteButton.setImageResource(R.drawable.ic_favourite_on)
            binding.favouriteButton.contentDescription = getString(R.string.add_to_favourite)
        } else {
            binding.favouriteButton.setImageResource(R.drawable.ic_favourite_off)
            binding.favouriteButton.contentDescription = getString(R.string.remove_favourite)
        }
    }
}
