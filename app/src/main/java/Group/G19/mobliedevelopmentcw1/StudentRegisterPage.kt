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

class StudentRegisterPage : AppCompatActivity(){

    enum class studentRegisterErrorCode
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
        setContentView(R.layout.activity_student_register)

        var errorCode : studentRegisterErrorCode = studentRegisterErrorCode.NoError
        val fireStoreController = FireStoreController()

        val registerBtn: Button = findViewById(R.id.studentRegisterBtn)
        val backToMainPageBtn : Button = findViewById(R.id.studentRegisterPageBackToMainPageBtn)

        registerBtn.setOnClickListener{
            GlobalScope.launch {
                val data: HashMap<String, Any> = getDataFromStudentRegisterInput()
                if (data["Error"] != null) {
                    errorCode = data["Error"] as studentRegisterErrorCode
                } else {
                    errorCode = studentRegisterErrorCode.NoError
                }

                when (errorCode) {
                    studentRegisterErrorCode.NoError -> {
                        fireStoreController.addNewUser(data)
                        Log.i("Register", "Student Register Success")
                        withContext(Dispatchers.Main) {
                            showDialog("Register Success","Hello!" + data["Name"])
                        }
                        val intent = Intent(this@StudentRegisterPage, StudentLoginPage::class.java)
                        var bundle = Bundle()
                        bundle.putString("Email", data["Email"].toString())
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                    studentRegisterErrorCode.InvalidOrEmptyName -> {
                        Log.i("Error", "Invalid or Empty Name")
                        withContext(Dispatchers.Main) {
                            showDialog("Error Occur", "Invalid or Empty Name")
                        }
                    }
                    studentRegisterErrorCode.InvalidOrEmptyEmail -> {
                        Log.i("Error", "Invalid or Empty Email")
                        withContext(Dispatchers.Main) {
                            showDialog("Error Occur", "Invalid or Empty Email")
                        }
                    }
                    studentRegisterErrorCode.EmailExisted -> {
                        Log.i("Error", "Email Existed")
                        withContext(Dispatchers.Main) {
                            showDialog("Error Occur", "Email Existed")
                        }
                    }
                    studentRegisterErrorCode.InvalidOrEmptyPassword -> {
                        Log.i("Error", "Invalid or Empty Password")
                        withContext(Dispatchers.Main) {
                            showDialog("Error Occur", "Invalid or Empty Password")
                        }

                    }
                    studentRegisterErrorCode.TwoPasswordNotMatch -> {
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
            val intent = Intent(this@StudentRegisterPage, MainActivity::class.java)
            startActivity(intent)
        }


    }

    suspend fun getDataFromStudentRegisterInput(): HashMap<String,Any> {
        val emailInputTextField : TextView = findViewById(R.id.studentRegisterEmailInputField)
        val passwordInputField : TextView = findViewById(R.id.studentRegisterPasswordInputfield)
        val passwordCheckInputField : TextView = findViewById(R.id  .studentRegisterPasswordInputfieldCheck)
        val nameInputTextField : TextView = findViewById(R.id.studentRegisterNameInputField)
        val fireStoreController = FireStoreController()

        var email : String
        var password : String
        var passwordCheck : String
        var name_inupt : String

        email = emailInputTextField.text.toString()
        name_inupt = nameInputTextField.text.toString()
        password = passwordInputField.text.toString()
        passwordCheck = passwordCheckInputField.text.toString()
        var errorCode : studentRegisterErrorCode = studentRegisterErrorCode.NoError
        var data :HashMap<String,Any> = hashMapOf()
        if(email == "")
        {
            errorCode = studentRegisterErrorCode.InvalidOrEmptyEmail
             data = hashMapOf(
                "Error" to errorCode
                )
        }
        else if(name_inupt == "")
        {
            errorCode = studentRegisterErrorCode.InvalidOrEmptyName
             data = hashMapOf(
                "Error" to errorCode
            )
        }
        else if(password == "")
        {
            errorCode = studentRegisterErrorCode.InvalidOrEmptyPassword
             data = hashMapOf(
                "Error" to errorCode
            )
        }
        else if(passwordCheck == "" || password != passwordCheck)
        {
            errorCode = studentRegisterErrorCode.TwoPasswordNotMatch
             data = hashMapOf(
                "Error" to errorCode
            )
        }
        else if(fireStoreController.checkEmailExist(email))
        {
            errorCode = studentRegisterErrorCode.EmailExisted
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
        val studentLoginDialog = AlertDialog.Builder(this).setTitle(title).setMessage(message).create().show()
    }
}