package Group.G19.mobliedevelopmentcw1.HomePageNavi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import Group.G19.mobliedevelopmentcw1.R


class studentHomePageHomeFragement : Fragment {

    constructor() : super()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_student_home_page_home_fragement, container, false)
    }

}