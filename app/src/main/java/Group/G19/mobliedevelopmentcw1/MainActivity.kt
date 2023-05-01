package Group.G19.mobliedevelopmentcw1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //-----!For all activity in the application, only firestore controller can access to the firestore database!------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Register all button in main page
        val toStudentRegisterPageBtn: Button = findViewById(R.id.studentRegisterPageBtn)
        val toStudentLoginPageBtn: Button = findViewById(R.id.studentLoginPageBtn)
        val toTeacherRegisterPageBtn: Button = findViewById(R.id.teacherRegisterPageBtn)
        val toTeacherLoginPageBtn: Button = findViewById(R.id.teacherLoginPageBtn)

        toStudentRegisterPageBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, StudentRegisterPage::class.java)
            startActivity(intent)
        }

        toStudentLoginPageBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, StudentLoginPage::class.java)
            startActivity(intent)
        }
        toTeacherRegisterPageBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, TeacherRegisterPage::class.java)
            startActivity(intent)
        }

        toTeacherLoginPageBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, TeacherLoginPage::class.java)
            startActivity(intent)
        }


    }



}