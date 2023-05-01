package Group.G19.mobliedevelopmentcw1.HomePageNavi

import Group.G19.mobliedevelopmentcw1.R
import Group.G19.mobliedevelopmentcw1.TeacherAddCourse
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment



class teacherHomePageCourseFragement : Fragment {



    constructor() : super()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.fragment_teacher_home_page_course_fragement, container, false)

        val button = view.findViewById<Button>(R.id.CourseCreateBtn)
        button.setOnClickListener {
            val intent = Intent(requireActivity(), TeacherAddCourse::class.java)
            startActivity(intent)
        }

        return view
    }
}



