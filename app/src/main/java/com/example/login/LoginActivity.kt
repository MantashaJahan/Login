package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login.databinding.ActivityLoginBinding
import com.example.login.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.identity.SignInPassword
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGoogleApiClient: GoogleApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        ActivityLoginBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        binding.exchange.setOnClickListener{
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
            finish()

            mAuth= FirebaseAuth.getInstance()

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("AIzaSyD-yTPLYGxTNRvQsQmnOhoM00r87kFCyzA")
                .requestEmail()
                .build()

            mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this) { connectionResult ->
                    Toast.makeText(this@LoginActivity, "Google Service Error", Toast.LENGTH_SHORT)
                        .show()
                }
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build()
            binding.loginBtn.setOnClickListener {
                val email = binding.emailET.text.toString()
                val password = binding.passET.text.toString()
                signInWithEmailAndPassword(email, password)
            }

                }
        fun onStart(){
            if (Firebase.auth.currentUser!=null){
                startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                finish()
            }

        }


    }
    private fun signInWithEmailAndPassword(email:String,password: String){
        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                task->
                if(task.isSuccessful){
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this@LoginActivity,"Something Wrong",Toast.LENGTH_SHORT).show()
                }
            }

                }
            }


