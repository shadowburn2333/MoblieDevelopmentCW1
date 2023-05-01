package Group.G19.mobliedevelopmentcw1

import Group.G19.mobliedevelopmentcw1.model.CourseData
import Group.G19.mobliedevelopmentcw1.view.CourseAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

c
class TeacherAddCourse : AppCompatActivity() {

    private lateinit var addsBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var courseList:ArrayList<CourseData>
    private lateinit var courseAdapter: CourseAdapter


    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_add_course)
        addsBtn=findViewById(R.id.addingBtn)
        recv=findViewById(R.id.mRecycler)
        courseList=ArrayList()
        courseAdapter= CourseAdapter(this,courseList)
        recv.layoutManager=LinearLayoutManager(this)
        recv.adapter=courseAdapter
        addsBtn.setOnClickListener{
            addinfo()
        }
    }

    private fun addinfo() {
        val inflter=LayoutInflater.from(this)
        val v=inflter.inflate(R.layout.add_item,null)
        val courseName=v.findViewById<EditText>(R.id.CourseName)
        val courseDescription=v.findViewById<EditText>(R.id.CourseDescription)
        val addDialog=AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){

                dialog,_->
            val names=courseName.text.toString()
            val descriptions=courseDescription.text.toString()

            courseList.add(CourseData("Course name: $names","Course description : $descriptions"))
            courseAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Adding User Information Success",Toast.LENGTH_SHORT).show()
            dialog.dismiss()



        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()
        }

        addDialog.create()
        addDialog.show()

    }



}