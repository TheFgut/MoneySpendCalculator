package com.example.moneyspendcalculator.autintification

import android.telephony.mbms.MbmsErrors.InitializationErrors
import com.example.moneyspendcalculator.data_manage.DataLoaderAndSaver
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.security.KeyStore.LoadStoreParameter

class Authentification {


    companion object {
        val controller: Authentification = Authentification();

        fun emailCheck(email: String) : Boolean {
            if(email.isEmpty()){
                return false;
            }
            var dogSplitted = email.split('@')
            if(dogSplitted.size <= 1 || dogSplitted.size > 2){
                return false
            }
            return true
        }

        fun passwordCheck(password: String) : Boolean {
            if(password.isEmpty()){
                return false;
            }
            if(password.length < 5){
                return false
            }
            return true
        }

        fun Init(){

        }
    }

    private var currentUser: FirebaseUser? = null;

    fun Autentificated(user: FirebaseUser){
        currentUser = user
    }

    fun SignOut(){
        var mAuth: FirebaseAuth = FirebaseAuth.getInstance();
        mAuth.signOut()
        currentUser = null
    }

    public fun isAuthentificated() : Boolean {
        return currentUser != null
    }
}