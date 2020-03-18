package com.example.swipequiz

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private val quizzes = arrayListOf<Quiz>()
    private val quizAdapter = QuizAdapter(quizzes)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        recyclerView.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        recyclerView.adapter = quizAdapter

        createItemTouchHelper().attachToRecyclerView(recyclerView)

        for (i in Quiz.QUESTIONS.indices) {
            quizzes.add(Quiz(Quiz.QUESTIONS[i], Quiz.ANSWERS[i]))
        }
        quizAdapter.notifyDataSetChanged()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // if swiped right
                if(direction == ItemTouchHelper.RIGHT ){
                    val position = viewHolder.adapterPosition

                    // if the answer is true
                    if(quizzes[position].answer){
                        quizAdapter.removeItem(position)
                        quizAdapter.notifyDataSetChanged()
                    } else {
                        // make temp quiz
                        val tmp = quizzes[position]
                        // remove the quiz of the list
                        quizAdapter.removeItem(position)

                        // make snackbar with an action where it adds the temp item back
                        Snackbar.make(recyclerView, "Answer not correct", Snackbar.LENGTH_LONG)
                            .setAction("OK", { _ ->
                                quizAdapter.addItem(position, tmp)
                                quizAdapter.notifyItemInserted(position)
                                quizAdapter.notifyDataSetChanged()
                            }).show()
                    }
                } else {
                    val position = viewHolder.adapterPosition
                    if(!quizzes[position].answer){
                        quizAdapter.removeItem(position)
                        quizAdapter.notifyDataSetChanged()
                    } else {
                        val tmp = quizzes[position]
                        quizAdapter.removeItem(position)

                        Snackbar.make(recyclerView, "Answer not correct", Snackbar.LENGTH_LONG)
                            .setAction("OK", { _ ->
                                quizAdapter.addItem(position, tmp)
                                quizAdapter.notifyItemInserted(position)
                                quizAdapter.notifyDataSetChanged()
                            }).show()
                    }
                }
            }
        }

        return ItemTouchHelper(callback)
    }


}
