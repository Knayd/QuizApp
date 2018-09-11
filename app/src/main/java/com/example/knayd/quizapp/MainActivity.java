package com.example.knayd.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements QuestionAdapter.OnRadioButtonSelected {
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
        questionList.add(new RadioButtonQuestion("2+2 equals to?","4","3","2","0",1));
        questionList.add(new RadioButtonQuestion("3*3 equals to?","9","5","6","2",1));
        questionList.add(new RadioButtonQuestion("3*3+2+2 equals to?","13","21","15","None of the above",1));
        questionList.add(new RadioButtonQuestion("0*5^2+1*2 equals to?","2","6","8","0",1));
        //CB
//        questionList.add(new CheckBoxQuestion("3+3 equals to?","2","6","8","0"));
//        questionList.add(new CheckBoxQuestion("4+4 equals to?","2","6","8","0"));
//        questionList.add(new CheckBoxQuestion("5+5 equals to?","2","6","8","0"));
//        questionList.add(new CheckBoxQuestion("6+6 equals to?","2","6","8","0"));

        //Do the adapter-magic
        QuestionAdapter adapter = new QuestionAdapter(this, questionList);
        //Show the items in the rv
        rv.setAdapter(adapter);

        //region check button
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i=0;i<questionList.size(); i++) {

                    //First, I check what kind of object is stored in the list which has to match with the one in the RV

                    if(questionList.get(i) instanceof RadioButtonQuestion) {
                        //With that object, now I have access to the individual elements of the view

                        if (i==0){
                            //Checks for the first question
                            if(((RadioButtonQuestion) questionList.get(i)).getCorrectAnswer() == 1){
                                correctAnswers+=1;
                            } else {
                                incorrectAnswers+=1;
                                numberOfIncorrect += "N."+(i+1) + " ";
                            }
                        } else if(i==1) {
                            //Checks for the second question
                            if(((RadioButtonQuestion) questionList.get(i)).getCorrectAnswer() == 1){
                                correctAnswers+=1;
                            } else {
                                incorrectAnswers+=1;
                                numberOfIncorrect += "N."+(i+1) + " ";
                            }
                        } else if(i==2) {
                            //Checks for the third question
                            if(((RadioButtonQuestion) questionList.get(i)).getCorrectAnswer() == 1){
                                correctAnswers+=1;
                            } else {
                                incorrectAnswers+=1;
                                numberOfIncorrect += "N."+(i+1) + " ";
                            }
                        } else if(i==3) {
                            //Checks for the fourth question
                            if(((RadioButtonQuestion) questionList.get(i)).getCorrectAnswer() == 1){
                                correctAnswers+=1;
                            } else {
                                incorrectAnswers+=1;
                                numberOfIncorrect += "N."+(i+1) + " ";
                            }
                        }
                    } else { //if it is an instance of CheckBoxQuestion


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
        //endregion

    }
    //Implementation of the interface
    @Override
    public void onRadioButtonSelected(int position, int answer) {
         questionList.get(position).setCorrectAnswer(answer);
    }
}
