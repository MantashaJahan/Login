package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.regBtn.setOnClickListener {
            if (binding.emailET.text.toString() == ""&& binding.emailET.toString() == ""){
                Toast.makeText(this@MainActivity,"please Enter all the Information",Toast.LENGTH_SHORT).show()

            }
            else{
                Firebase.auth.createUserWithEmailAndPassword(binding.emailET.text.toString(),binding.passET.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){

                        startActivity(Intent(this@MainActivity,HomeActivity::class.java))
                        finish()
                    }
                    else{

                        Toast.makeText(this@MainActivity,it.exception?.localizedMessage,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser !=null){
            startActivity(Intent(this@MainActivity,HomeActivity::class.java))
            finish()
        }
    }
}
