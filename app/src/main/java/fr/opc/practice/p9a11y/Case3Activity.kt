package fr.opc.practice.p9a11y

import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import fr.opc.practice.p9a11y.databinding.ActivityCase3Binding

class Case3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Listen for text changes in the EditText field
        binding.pseudoEdit.doOnTextChanged { text, _, _, _ ->
            val inputText = text?.toString()?.trim() ?: ""
            val isValid = inputText.length > 2

            // Enable/disable validate button
            binding.validateButton.isEnabled = isValid

            // Change background color based on validity
            binding.pseudoEdit.backgroundTintList = if (isValid) {
                ColorStateList.valueOf(resources.getColor(R.color.green400, theme))
            } else {
                ColorStateList.valueOf(resources.getColor(R.color.red400, theme))
            }

            // Show/hide error and suggestion messages
            if (isValid) {
                binding.pseudoError.visibility = android.view.View.GONE
                binding.pseudoSuggestion.visibility = android.view.View.GONE
            } else {
                binding.pseudoError.visibility = android.view.View.VISIBLE
                binding.pseudoSuggestion.visibility = android.view.View.VISIBLE

                // Set error and suggestion messages dynamically
                binding.pseudoError.text = getString(R.string.pseudo_error_message)
                binding.pseudoSuggestion.text = getString(R.string.pseudo_suggestion_message)

                // Announce error and suggestion to accessibility tools
                binding.pseudoError.announceForAccessibility(binding.pseudoError.text)
                binding.pseudoSuggestion.announceForAccessibility(binding.pseudoSuggestion.text)
            }
        }

        // Initially disable the validate button
        binding.validateButton.isEnabled = false

        // Set a click listener for the validate button
        binding.validateButton.setOnClickListener {
            val inputText = binding.pseudoEdit.text.toString()
            if (inputText.length > 2) {
                // Accessibility-friendly announcement
                val message = getString(R.string.pseudo_validate_toast_message)
                binding.validateButton.announceForAccessibility(message)

                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }

    }
}
