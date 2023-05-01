package Group.G19.mobliedevelopmentcw1.MyNavigationViewer

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.NonNull
import androidx.viewpager.widget.ViewPager


class mViewPager : ViewPager {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, false)
    }
}

