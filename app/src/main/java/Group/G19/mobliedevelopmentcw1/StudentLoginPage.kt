package Group.G19.mobliedevelopmentcw1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentLoginPage :AppCompatActivity(){


    enum class studentLoginErrorCode
    {
        NoError,
        InvalidOrEmptyEmail,
        EmptyPassword,
        WrongPassword
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)

        var errorCode : studentLoginErrorCode = studentLoginErrorCode.NoError

        val loginBtn: Button = findViewById(R.id.studentLoginBtn)
        val backToMainPageBtn : Button = findViewById(R.id.studentLoginPageBackToMainPageBtn)
        val emailInputTextField : TextView = findViewById(R.id.studentLoginEmailInputField)
        val bundle = intent.extras
        if(bundle != null )
        {
            emailInputTextField.text = bundle.getString("Email")
        }


//The funciton that control the login process
        loginBtn.setOnClickListener {
            GlobalScope.launch {
                val data = getDataFromStudentloginInput()
                errorCode = data["Error"] as studentLoginErrorCode
                when (errorCode) {
                    studentLoginErrorCode.NoError -> {
                        Log.i("Login", "Student Login Success")
                        //Since the click function now is not in main thread, we need to switch to the main thread to control the UI
                        //to avoid the breakdown of the app
                        withContext(Dispatchers.Main) {
                            showDialog("Login Success","Welcome:  " + data["Name"])
                        }
                        val intent = Intent(this@StudentLoginPage, StudentHomePage::class.java)
                        var bundle = Bundle()
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                    studentLoginErrorCode.InvalidOrEmptyEmail -> {
                        Log.i("Error", "Invalid or Empty Email")
                        withContext(Dispatchers.Main) {
                            showDialog("Error Occur","Invalid or Empty Email")
                        }
                    }
                    studentLoginErrorCode.EmptyPassword -> {
                        Log.i("Error", "Empty Password")
                        withContext(Dispatchers.Main) {
                            showDialog("Error Occur","Empty Password")
                        }
                    }
                    studentLoginErrorCode.WrongPassword -> {
                        Log.i("Error", "Wrong Password")
                        withContext(Dispatchers.Main) {
                            showDialog("Error Occur","Wrong Password")
                        }
                    }
                    else -> {
                    }
                }
            }
        }
//The function that let user back to main page (Start page of APP)
        backToMainPageBtn.setOnClickListener{
            val intent = Intent(this@StudentLoginPage, MainActivity::class.java)
            startActivity(intent)
        }


    }

    suspend fun getDataFromStudentloginInput() : HashMap<String,Any>
    {
        var data:HashMap<String,Any> = HashMap()
        val emailInputTextField : TextView = findViewById(R.id.studentLoginEmailInputField)
        val passwordInputField : TextView = findViewById(R.id.studentLoginPasswordInputfield)
        val fireStoreController = FireStoreController()
        var email : String
        var password : String


        email = emailInputTextField.text.toString()
        password = passwordInputField.text.toString()

        var errorCode : studentLoginErrorCode = studentLoginErrorCode.NoError
        if(email == "" || !fireStoreController.checkEmailExist(email))
        {
            errorCode = studentLoginErrorCode.InvalidOrEmptyEmail
            data = hashMapOf(
                "Error" to errorCode
            )
        }
        else if(password == "")
        {
            errorCode = studentLoginErrorCode.EmptyPassword
            data = hashMapOf(
                "Error" to errorCode
            )
        }
        else if(!fireStoreController.checkPasswordCorrect(password,email))
        {
            errorCode = studentLoginErrorCode.WrongPassword
            data = hashMapOf(
                "Error" to errorCode
            )
        }
        else
        {
            errorCode = studentLoginErrorCode.NoError
            data = hashMapOf(
                "Error" to errorCode,
                "Name" to fireStoreController.getName(email)
            )
            //Save data in bundle and start main page
            Log.i("Login","Login Information Checked")
        }

        return data
    }

    fun showDialog(title:String,message:String)
    {
        val studentLoginDialog = AlertDialog.Builder(this).setTitle(title).setMessage(message).create().show()
    }



}