package ajolote.taquero.desafio_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validation
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Successful login, start MenuActivity
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)

                // Show toast notification for successful login (optional)
                Toast.makeText(this, "Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show()
            } else {
                // Show toast notification for empty fields
                Toast.makeText(this, "Por favor, ingrese su email y contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }
}