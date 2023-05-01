package Group.G19.mobliedevelopmentcw1

import Group.G19.mobliedevelopmentcw1.HomePageNavi.studentHomePageCourseFragement
import Group.G19.mobliedevelopmentcw1.HomePageNavi.studentHomePageHomeFragement
import Group.G19.mobliedevelopmentcw1.HomePageNavi.studentHomePageSettingFragement
import Group.G19.mobliedevelopmentcw1.MyNavigationViewer.mViewPager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView

class StudentHomePage: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_homepage)
        initBottomNavi()
    }

    fun initBottomNavi()
    {
        val m_ViewPager : mViewPager = findViewById(R.id.studentHomePageViewPager)
        val m_StudentHomePageBottomNavigation : BottomNavigationView = findViewById(R.id.studentHomePageBottomNavi)
        val homeFrag : studentHomePageHomeFragement = studentHomePageHomeFragement()
        val courseFrag :studentHomePageCourseFragement = studentHomePageCourseFragement()
        val settingFrag : studentHomePageSettingFragement = studentHomePageSettingFragement()
        m_ViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                m_StudentHomePageBottomNavigation.getMenu().getItem(position).setChecked(true);
            }
        })

        m_StudentHomePageBottomNavigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            m_ViewPager.setCurrentItem(menuItem.getOrder())
            true
        })

        m_ViewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> homeFrag
                    1 -> courseFrag
                    2 -> settingFrag
                    else -> throw IllegalArgumentException("Invalid position $position")
                }
            }

            override fun getCount(): Int {
                return 3
            }
        }


    }
}