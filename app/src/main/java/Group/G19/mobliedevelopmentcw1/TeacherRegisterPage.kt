package Group.G19.mobliedevelopmentcw1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeacherRegisterPage : AppCompatActivity(){

    enum class teacherRegisterErrorCode
    {
        NoError,
        InvalidOrEmptyEmail,
        InvalidOrEmptyName,
        InvalidOrEmptyPassword,
        TwoPasswordNotMatch,
        EmailExisted
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_register)

        var errorCode : teacherRegisterErrorCode = teacherRegisterErrorCode.NoError
        val fireStoreController = FireStoreController()

        val registerBtn: Button = findViewById(R.id.teacherRegisterBtn)
        val backToMainPageBtn : Button = findViewById(R.id.teacherRegisterPageBackToMainPageBtn)

        registerBtn.setOnClickListener{
            GlobalScope.launch {
                val data: HashMap<String, Any> = getDataFromteacherRegisterInput()
                if (data["Error"] != null) {
                    errorCode = data["Error"] as teacherRegisterErrorCode
                } else {
                    errorCode = teacherRegisterErrorCode.NoError
                }

                when (errorCode) {
                    teacherRegisterErrorCode.NoError -> {
                        fireStoreController.addNewUser(data)
                        Log.i("Register", "teacher Register Success")
                        withContext(Dispatchers.Main) {
                            showDialog("Register Success","Hello!" + data["Name"])
                        }
                        val intent = Intent(this@TeacherRegisterPage, TeacherLoginPage::class.java)
                        var bundle = Bundle()
                        bundle.putString("Email", data["Email"].toString())
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                    teacherRegisterErrorCode.InvalidOrEmptyName -> {
                        Log.i("Error", "Invalid or Empty Name")
                        withContext(Dispatchers.Main) {
                            showDialog("Error Occur", "Invalid or Empty Name")
                        }
                    }
                    teacherRegisterErrorCode.InvalidOrEmptyEmail -> {
                        Log.i("Error", "Invalid or Empty Email")
                        withContext(Dispatchers.Main) {
                            showDialog("Error Occur", "Invalid or Empty Email")
                        }
                    }
                    teacherRegisterErrorCode.EmailExisted -> {
                        Log.i("Error", "Email Existed")
                        withContext(Dispatchers.Main) {
                            showDialog("Error Occur", "Email Existed")
                        }
                    }
                    teacherRegisterErrorCode.InvalidOrEmptyPassword -> {
                        Log.i("Error", "Invalid or Empty Password")
                        withContext(Dispatchers.Main) {
                            showDialog("Error Occur", "Invalid or Empty Password")
                        }

                    }
                    teacherRegisterErrorCode.TwoPasswordNotMatch -> {
                        Log.i("Error", "Two Password Are Different")
                        withContext(Dispatchers.Main) {
                            showDialog("Error Occur", "Two Password Are Different")
                        }
                    }
                    else -> {}
                }
            }
        }

        backToMainPageBtn.setOnClickListener{
            val intent = Intent(this@TeacherRegisterPage, MainActivity::class.java)
            startActivity(intent)
        }


    }

    suspend fun getDataFromteacherRegisterInput(): HashMap<String,Any> {
        val emailInputTextField : TextView = findViewById(R.id.teacherRegisterEmailInputField)
        val passwordInputField : TextView = findViewById(R.id.teacherRegisterPasswordInputfield)
        val passwordCheckInputField : TextView = findViewById(R.id.teacherRegisterPasswordInputfieldCheck)
        val nameInputTextField : TextView = findViewById(R.id.teacherRegisterNameInputField)
        val fireStoreController = FireStoreController()

        var email : String
        var password : String
        var passwordCheck : String
        var name_inupt : String

        email = emailInputTextField.text.toString()
        name_inupt = nameInputTextField.text.toString()
        password = passwordInputField.text.toString()
        passwordCheck = passwordCheckInputField.text.toString()
        var errorCode : teacherRegisterErrorCode = teacherRegisterErrorCode.NoError
        var data :HashMap<String,Any> = hashMapOf()
        if(email == "")
        {
            errorCode = teacherRegisterErrorCode.InvalidOrEmptyEmail
            data = hashMapOf(
                "Error" to errorCode
            )
        }
        else if(name_inupt == "")
        {
            errorCode = teacherRegisterErrorCode.InvalidOrEmptyName
            data = hashMapOf(
                "Error" to errorCode
            )
        }
        else if(password == "")
        {
            errorCode = teacherRegisterErrorCode.InvalidOrEmptyPassword
            data = hashMapOf(
                "Error" to errorCode
            )
        }
        else if(passwordCheck == "" || password != passwordCheck)
        {
            errorCode = teacherRegisterErrorCode.TwoPasswordNotMatch
            data = hashMapOf(
                "Error" to errorCode
            )
        }
        else if(fireStoreController.checkEmailExist(email))
        {
            errorCode = teacherRegisterErrorCode.EmailExisted
            data = hashMapOf(
                "Error" to errorCode
            )
        }
        else {
            data = hashMapOf(
                "Email" to email,
                "Password" to password,
                "Name" to name_inupt
            )
        }


        return data
    }

    fun showDialog(title:String,message:String)
    {
        val teacherLoginDialog = AlertDialog.Builder(this).setTitle(title).setMessage(message).create().show()
    }
}