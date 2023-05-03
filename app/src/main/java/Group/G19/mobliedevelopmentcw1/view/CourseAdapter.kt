package Group.G19.mobliedevelopmentcw1.view

import Group.G19.mobliedevelopmentcw1.R
import Group.G19.mobliedevelopmentcw1.model.CourseData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CourseAdapter(val c:Context,val courseList: ArrayList<CourseData>,val listener: MyClickListener):RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {
    private lateinit var onItemClickListener: AdapterView.OnItemClickListener

    fun setOnItemClickListener(listener: AdapterView.OnItemClickListener) {
        this.onItemClickListener = listener
    }
    inner class CourseViewHolder(val v:View): RecyclerView.ViewHolder(v){
        val name=v.findViewById<TextView>(R.id.mTitle)
        val description=v.findViewById<TextView>(R.id.mSubTitle)
        init{
            itemView.setOnClickListener{
                val position=adapterPosition
                listener.onClick(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {

        val inflater=LayoutInflater.from(parent.context)
        val v=inflater.inflate(R.layout.list_item,parent,false)
        return CourseViewHolder(v)
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {

        val newList=courseList[position]
        holder.name.text=newList.coursename
        holder.description.text=newList.coursedescription


    }

    interface MyClickListener{
        fun onClick(position: Int)
    }
}