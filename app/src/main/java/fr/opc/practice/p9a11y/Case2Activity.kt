package fr.opc.practice.p9a11y

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        // Set initial content description for the favorite button
        binding.favouriteButton.contentDescription = getString(R.string.favorite_button_initial)

        // Handle favorite button click
        binding.favouriteButton.setOnClickListener {
            isFavourite = !isFavourite
            isFirstEncounter = false
            setFavouriteButtonIcon(isFavourite)
        }

        // Handle "Add to Basket" button click
        binding.addRecipeToBasket.setOnClickListener {
            Toast.makeText(this, getString(R.string.recette_ajout_au_panier), Toast.LENGTH_SHORT)
                .show()
        }

        // Handle recipe card click
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
