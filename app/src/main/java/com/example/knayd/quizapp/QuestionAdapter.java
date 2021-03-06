package com.example.knayd.quizapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    OnRadioButtonSelected mCallBack;
    List<Question> questionList;

    //The interface
    public interface OnRadioButtonSelected {
       void onRadioButtonSelected(int position, int answer);
    }

    private final int RADIO_LAYOUT = 0, CHECKBOX_LAYOUT=1;

    //
    public QuestionAdapter(OnRadioButtonSelected callBack, List<Question> questionList) {
        this.questionList = questionList;
        this.mCallBack = callBack;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder=null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case RADIO_LAYOUT:
                View v1 = inflater.inflate(R.layout.item_question_radiobutton, parent, false);
                viewHolder = new RBQuestionViewHolder(v1);
                break;

            case CHECKBOX_LAYOUT:
                View v2 = inflater.inflate(R.layout.item_question_checkbox, parent, false);
                viewHolder = new CBQuestionViewHolder(v2);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof RBQuestionViewHolder) {
            RBQuestionViewHolder rb = (RBQuestionViewHolder) holder;
            rb.bindView(questionList, position);

        } else if (holder instanceof CBQuestionViewHolder) {
            CBQuestionViewHolder cb = (CBQuestionViewHolder) holder;
            cb.bindView(questionList, position);
        }

    }

    @Override
    public int getItemViewType(int position){

        if(questionList.get(position) instanceof RadioButtonQuestion){
            return RADIO_LAYOUT;
        } else {
            return CHECKBOX_LAYOUT;
        }
    }


    @Override
    public int getItemCount() {
        return questionList.size();
    }

    //A class for every type of viewholder
    class RBQuestionViewHolder extends RecyclerView.ViewHolder{
        TextView question;
        RadioButton option1,option2,option3,option4;
        RadioGroup radioGroup;

        public RBQuestionViewHolder(View itemView) {
            super(itemView);
            radioGroup = itemView.findViewById(R.id.rg);
            question = itemView.findViewById(R.id.tv_question);
            option1 = itemView.findViewById(R.id.rb_option1);
            option2 = itemView.findViewById(R.id.rb_option2);
            option3 = itemView.findViewById(R.id.rb_option3);
            option4 = itemView.findViewById(R.id.rb_option4);
        }
        //Sets the configurations for the RB VH
        public void bindView(List<Question> list, final int position) {
            question.setText(list.get(position).getQuestion());
            option1.setText(list.get(position).getOption1());
            option1.setChecked(true); //This is so the first rb appears checked
            option2.setText(list.get(position).getOption2());
            option3.setText(list.get(position).getOption3());
            option4.setText(list.get(position).getOption4());

            //checks when an element of the radiogroup is clicked
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i) {
                        case R.id.rb_option1 :
                            mCallBack.onRadioButtonSelected(position, 1);
                            break;
                        case R.id.rb_option2 :
                            mCallBack.onRadioButtonSelected(position, 2);
                            break;
                        case R.id.rb_option3 :
                            mCallBack.onRadioButtonSelected(position, 3);
                            break;
                        case R.id.rb_option4 :
                            mCallBack.onRadioButtonSelected(position, 4);
                            break;
                    }
                }
            });
        }

    }
}



class CBQuestionViewHolder extends RecyclerView.ViewHolder{

    TextView question;
    CheckBox option1,option2,option3,option4;

    public CBQuestionViewHolder(View itemView) {
        super(itemView);
        question = itemView.findViewById(R.id.tv_question);
        option1 = itemView.findViewById(R.id.cb_option1);
        option2 = itemView.findViewById(R.id.cb_option2);
        option3 = itemView.findViewById(R.id.cb_option3);
        option4 = itemView.findViewById(R.id.cb_option4);
    }

    //Sets the configuration for the CB
    public void bindView(List<Question> list, int position){

        question.setText(list.get(position).getQuestion());
        option1.setText(list.get(position).getOption1());
        option1.setChecked(true);
        option2.setText(list.get(position).getOption2());
        option3.setText(list.get(position).getOption3());
        option4.setText(list.get(position).getOption4());
    }
}