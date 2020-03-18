package com.example.swipequiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.quiz_item.view.*

public class QuizAdapter(private val quizzes: ArrayList<Quiz>) :
    RecyclerView.Adapter<QuizAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.quiz_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    override fun onBindViewHolder(holder: QuizAdapter.ViewHolder, position: Int) {
        holder.bind(quizzes[position])
    }

    open fun removeItem(position: Int) {
        quizzes.removeAt(position)
        notifyItemRemoved(position)
    }

    open fun addItem(position: Int, quiz: Quiz) {
        quizzes.add(position, quiz)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(quiz : Quiz) {
            itemView.txt_item.text = quiz.question
            itemView.setOnClickListener{
                Toast.makeText(itemView.context, "the answer is " + quiz.answer, Toast.LENGTH_SHORT).show()
            }
        }
    }

}