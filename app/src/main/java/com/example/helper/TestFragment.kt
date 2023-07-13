package com.example.helper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_test.*

import kotlinx.android.synthetic.main.item_view_linear_grid.view.*


class TestFragment : Fragment(R.layout.fragment_test) {

//    lateinit var recyclerView: RecyclerView
    private var rootView: View? = null

    companion object {
        fun newInstance(text: String): TestFragment {
            val args = Bundle()
            args.putString("text", text)
            val fragment = TestFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        recyclerView = view.findViewById(R.id.recycler_view)
        recycler_view.layoutManager = GridLayoutManager(context,3)
        recycler_view.adapter = MyAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_test, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val textView = rootView?.findViewById<TextView>(R.id.text_view)
        val text = arguments?.getString("text")
        textView?.text = text
    }



    inner class MyAdapter: RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView:View = LayoutInflater.from(context)
                .inflate(R.layout.item_view_linear_grid, parent, false)
            return MyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.itemView.item_image.setImageResource(R.drawable.bgs)
            holder.itemView.item_title.text = "第3次"
            holder.itemView.item_message.text = "哇哈哈"
        }

        override fun getItemCount(): Int {
            return 6
        }
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {}
      //  val item_image: ImageView = view.findViewById(R.id.item_image) // 声明并初始化 item_image


}