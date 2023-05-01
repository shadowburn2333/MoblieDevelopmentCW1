package Group.G19.mobliedevelopmentcw1

import android.text.BoringLayout
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FireStoreController {

      var db = Firebase.firestore

    interface ExistEmail {
        fun onCallback(ExistEmail: Boolean)
    }

    public fun addNewUser( data: HashMap<String, Any>)
    {
        Log.i("DB_related", "Start Write in")
        var commonCourse = hashMapOf(
            "ID" to "Uos001",
            "Description" to "This is the common course that every student have."
        )



        db.collection("AccountList").document(data["Email"].toString()).set(data)
        db.collection("AccountList").document(data["Email"].toString()).collection("CourseInfo").document("Uos_Common_Course").set(commonCourse)

    }

    public suspend fun checkEmailExist(emailInput: String): Boolean {
        var isEmailExist = false
        val emailsnap = db.collection("AccountList").get().await()
        emailsnap.documents.forEach { data ->
            if (data.id.toString() == emailInput) {
                isEmailExist = true
                return@forEach
            }
        }
        return isEmailExist
    }

    public suspend fun checkPasswordCorrect(passwordInput : String,emailInput: String) : Boolean
    {
        var isPasswordCorrect : Boolean = false
        val accountDetail = db.collection("AccountList").document(emailInput).get().await()
        if(accountDetail.exists())
        {
            if(passwordInput == accountDetail.data?.get("Password").toString())
            {
                isPasswordCorrect = true
            }
        }

        return isPasswordCorrect
    }

    public fun createLoginBundle() : HashMap<String, Any>
    {
        var data:HashMap<String,Any> = HashMap()

        return data
    }

    public suspend fun getName(emailInput : String) : String
    {
        var name : String = ""
        val accountDetail = db.collection("AccountList").document(emailInput).get().await()
        if(accountDetail.exists())
        {
           name = accountDetail.data?.get("Name").toString()
        }
        return name
    }


}