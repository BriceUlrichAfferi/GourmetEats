package fr.opc.practice.p9a11y

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.opc.practice.p9a11y.databinding.ActivityCase1Binding

class Case1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var quantity = 0

        // Initialize quantity text and content description
        updateQuantityTextAndAnnounce(quantity)

        binding.addButton.setOnClickListener {
            quantity++
            updateQuantityTextAndAnnounce(quantity)
        }

        binding.removeButton.setOnClickListener {
            if (quantity > 0) {
                quantity--
                updateQuantityTextAndAnnounce(quantity)
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.impossible_d_avoir_une_quantit_n_gative),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    /**
     * Updates the quantity text and sets the content description for accessibility,
     * ensuring TalkBack announces the update.
     */
    private fun updateQuantityTextAndAnnounce(quantity: Int) {
        binding.quantityText.text = "$quantity"

        binding.quantityText.contentDescription = null

        // small delay to reset the content description to force TalkBack to announce
        Handler(Looper.getMainLooper()).postDelayed({
            binding.quantityText.contentDescription = getString(R.string.quantity_content_description, quantity)
            binding.quantityText.sendAccessibilityEvent(android.view.accessibility.AccessibilityEvent.TYPE_VIEW_FOCUSED)
        }, 100)
    }
}
