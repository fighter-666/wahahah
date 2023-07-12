package com.example.helper

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
//    lateinit var binding: ActivityMainBinding

    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2

    val activeColor: Int = Color.parseColor("#ff678f")
     val normalColor: Int = Color.parseColor("#666666")

    var activeSize: Int = 20
    var normalSize: Int = 14

    lateinit var fragments: ArrayList<Fragment>
    lateinit var mediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager)
        val tabs: Array<String> = arrayOf("Components", "    Helper", "       Lab")
        viewPager2.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        viewPager2.adapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle) {
            override fun getItemCount(): Int {
                // 返回Fragment的数量
                return tabs.size
            }

            override fun createFragment(position: Int): Fragment {
                // 创建并返回对应位置的Fragment实例
                return TestFragment.newInstance(tabs[position])
            }
        }

        val adapter = DynamicFragmentAdapter(supportFragmentManager,lifecycle)
        viewPager2.adapter = adapter

        val mediator = TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            // 这里可以自定义TabView
            val tabView = TextView(this@MainActivity)
            val states = arrayOf(
                intArrayOf(android.R.attr.state_selected),
                intArrayOf()
            )
            val colors = intArrayOf(activeColor, normalColor)
            val colorStateList = ColorStateList(states, colors)
            tabView.text = tabs[position]
            tabView.setTextSize(normalSize.toFloat())
            tabView.setTextColor(colorStateList)
            tab.customView = tabView
        }
// 要执行这一句才是真正将两者绑定起来
        mediator.attach()
    }

    class DynamicFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
        private val fragments = listOf(
            TestFragment(),
            HelperFragment(),
            LabFragment(),
            // 添加更多的 Fragment 实例...
        )

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }
}