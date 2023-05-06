package Group.G19.mobliedevelopmentcw1

//import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class QuizStartActivity : AppCompatActivity()  {

   // @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_quiz)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val btn_start=findViewById<Button>(R.id.btn_start)
        btn_start.setOnClickListener {
            val intent = Intent(this,QuizQuestionsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}