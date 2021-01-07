package pojo

import android.view.View

interface OnRecyclerViewClickListener {
    fun onItemClickListener(view: View?)
    fun onItemLongClickListener(view: View?)
}