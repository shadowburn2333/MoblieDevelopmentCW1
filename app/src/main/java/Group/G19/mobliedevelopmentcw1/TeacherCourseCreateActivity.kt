package Group.G19.mobliedevelopmentcw1

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.ktx.database

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TeacherCourseCreateActivity: AppCompatActivity() {



    val db=Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_course_create_activity)




        val quizbutton=findViewById<Button>(R.id.QuizBtn)
       /* val data:HashMap<String,Any>
       data= hashMapOf(
            "CourseName" to quizname
        )*/
        quizbutton.setOnClickListener{
            val quizName=findViewById<EditText>(R.id.EditQuizName)
            val quizname=quizName.text.toString()
            val quizContent=findViewById<EditText>(R.id.EditQuizContent)
            val quizcontent=quizContent.text.toString()

            Log.i("222",quizname)
            val quizdata:HashMap<String,Any>
            quizdata= hashMapOf(
                quizname to quizcontent
            )
            db.collection("Course")
                .whereEqualTo("Coursename", quizname)
                .get()
                .addOnSuccessListener { documents ->
                    db.collection("Quiz").add(quizdata)

                    if(documents!=null) {
                        for (document in documents) {

                            Log.d("1111", "${document.id} => ${document.data}")
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }
            val intent = Intent(this@TeacherCourseCreateActivity, TeacherAddQuiz::class.java)
            startActivity(intent)

        }


    }

}