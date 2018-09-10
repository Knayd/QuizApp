package com.example.knayd.quizapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    List<Question> questionList;

    public QuestionAdapter(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_radiobutton, parent, false);
        QuestionViewHolder questionViewHolder = new QuestionViewHolder(v);
        return questionViewHolder;
    }

    //Here's where the logic goes
    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position) {
        holder.question.setText(questionList.get(position).getQuestion());

        holder.option1.setText(questionList.get(position).getOption1());
        holder.option1.setChecked(true);
        holder.option2.setText(questionList.get(position).getOption2());
        holder.option3.setText(questionList.get(position).getOption3());
        holder.option4.setText(questionList.get(position).getOption4());
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder{

        TextView question;
        RadioButton option1,option2,option3,option4;

        public QuestionViewHolder(View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.tv_question);
            option1 = itemView.findViewById(R.id.rb_option1);
            option2 = itemView.findViewById(R.id.rb_option2);
            option3 = itemView.findViewById(R.id.rb_option3);
            option4 = itemView.findViewById(R.id.rb_option4);
        }
    }
}
