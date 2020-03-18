package com.example.swipequiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_question.view.*


class QuestionAdapter(
    val question: List<Questions>,
    val clickListner: (Questions) -> Unit
) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return question.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        holder.bind(question[position], clickListner)//kijk de website nog een keer
        (holder as ViewHolder).bind(question[position], clickListner)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(question: Questions, clickListner: (Questions) -> Unit) {
            itemView.tvQuestion.text = question.question
            itemView.setOnClickListener { clickListner(question) }


        }
    }




}