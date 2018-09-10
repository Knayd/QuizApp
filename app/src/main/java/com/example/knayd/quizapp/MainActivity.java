package com.example.knayd.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    Button btnCheck;
    String numberOfIncorrect="";

    int correctAnswers, incorrectAnswers;

    //The layout manager (This is here so I can get access to it inside the button)
    LinearLayoutManager llm = new LinearLayoutManager(this);
    //Create the list (This is here so I can get access to the "size" method later)
    ArrayList<Question> questionList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv_question);
        btnCheck = findViewById(R.id.btn_check);

        //Sets the manager for the RV
        rv.setLayoutManager(llm);

        //Fill the list with the questions

        //RB
        questionList.add(new RadioButtonQuestion("2+2 equals to?","4","3","2","0"));
        questionList.add(new RadioButtonQuestion("3*3 equals to?","9","5","6","2"));
        questionList.add(new RadioButtonQuestion("3*3+2+2 equals to?","13","21","15","None of the above"));
        questionList.add(new RadioButtonQuestion("0*5^2+1*2 equals to?","2","6","8","0"));
        //CB
        questionList.add(new CheckBoxQuestion("3+3 equals to?","2","6","8","0"));
        questionList.add(new CheckBoxQuestion("4+4 equals to?","2","6","8","0"));
        questionList.add(new CheckBoxQuestion("5+5 equals to?","2","6","8","0"));
        questionList.add(new CheckBoxQuestion("6+6 equals to?","2","6","8","0"));

        //Do the adapter-magic
        QuestionAdapter adapter = new QuestionAdapter(questionList);
        //Show the items in the rv
        rv.setAdapter(adapter);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i=0;i<questionList.size(); i++) {

                    //Gets the entire view by the position
                    View v = llm.findViewByPosition(i);
                    //First, I check what kind of object is stored in the list which has to match with the one in the RV

                    if(questionList.get(i) instanceof RadioButtonQuestion) {
                        //With that object, now I have access to the individual elements of the view
                        RadioButton op1 = v.findViewById(R.id.rb_option1);
                        RadioButton op2 = v.findViewById(R.id.rb_option2);
                        RadioButton op3 = v.findViewById(R.id.rb_option3);
                        RadioButton op4 = v.findViewById(R.id.rb_option4);

                        if (i==0){
                            //Checks for the first question
                            if(op1.isChecked()){
                                correctAnswers+=1;
                            } else {
                                incorrectAnswers+=1;
                                numberOfIncorrect += "N."+(i+1) + " ";
                            }
                        } else if(i==1) {
                            //Checks for the second question
                            if(op1.isChecked()){
                                correctAnswers+=1;
                            } else {
                                incorrectAnswers+=1;
                                numberOfIncorrect += "N."+(i+1) + " ";
                            }
                        } else if(i==2) {
                            //Checks for the third question
                            if(op1.isChecked()){
                                correctAnswers+=1;
                            } else {
                                incorrectAnswers+=1;
                                numberOfIncorrect += "N."+(i+1) + " ";
                            }
                        } else if(i==3) {
                            //Checks for the fourth question
                            if(op1.isChecked()){
                                correctAnswers+=1;
                            } else {
                                incorrectAnswers+=1;
                                numberOfIncorrect += "N."+(i+1) + " ";
                            }
                        }
                    } else {
                        //With that object, now I have access to the individual elements of the view
                        CheckBox op1 = v.findViewById(R.id.cb_option1);
                        CheckBox op2 = v.findViewById(R.id.cb_option2);
                        CheckBox op3 = v.findViewById(R.id.cb_option3);
                        CheckBox op4 = v.findViewById(R.id.cb_option4);

                        if (i==0){
                            //Checks for the fifth question
                            if(op1.isChecked()){
                                correctAnswers+=1;
                            } else {
                                incorrectAnswers+=1;
                                numberOfIncorrect += "N."+(i+1) + " ";
                            }
                        } else if(i==1) {
                            //Checks for the sixth question
                            if(op1.isChecked()){
                                correctAnswers+=1;
                            } else {
                                incorrectAnswers+=1;
                                numberOfIncorrect += "N."+(i+1) + " ";
                            }
                        } else if(i==2) {
                            //Checks for the seventh question
                            if(op1.isChecked()){
                                correctAnswers+=1;
                            } else {
                                incorrectAnswers+=1;
                                numberOfIncorrect += "N."+(i+1) + " ";
                            }
                        } else if(i==3) {
                            //Checks for the eighth question
                            if(op1.isChecked()){
                                correctAnswers+=1;
                            } else {
                                incorrectAnswers+=1;
                                numberOfIncorrect += "N."+(i+1) + " ";
                            }
                        }
                    }

                }

                //In case the user gets all the right answers
                if(numberOfIncorrect.equals("")) {
                    numberOfIncorrect = "None";
                }

                Toast.makeText(getApplicationContext(), "Correct: " + correctAnswers + "\nIncorrect: " + incorrectAnswers +"\nYour incorrect answers were: " + numberOfIncorrect , Toast.LENGTH_LONG).show();

                //Resets the values
                correctAnswers =0;
                incorrectAnswers =0;
                numberOfIncorrect ="";
            }
        });

    }
}
