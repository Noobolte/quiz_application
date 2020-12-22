package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final private String SCORE_KEY = "SCORE";
    final private String INDEX_KEY = "INDEX";
    private TextView mTextQuestion;
    private Button btnt;
    private Button btnf;
    private int mQuestionindex;
    private int mQuiquestion;
    private ProgressBar mProgressBar;
    private TextView mQuistats;

    private int score;

    private QuiModel[] questionCollection = new QuiModel[]{

            new QuiModel(R.string.q1,false),
            new QuiModel(R.string.q2,false),
            new QuiModel(R.string.q3,true),
            new QuiModel(R.string.q4,false),
            new QuiModel(R.string.q5,true),
            new QuiModel(R.string.q6,true),
            new QuiModel(R.string.q7,false),
            new QuiModel(R.string.q8,true),
            new QuiModel(R.string.q9,true),
            new QuiModel(R.string.q10,false),


    };
    final int USER_PROGRESS = (int) Math.ceil(100.0 /questionCollection.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            score = savedInstanceState.getInt(SCORE_KEY);
            mQuestionindex = savedInstanceState.getInt(INDEX_KEY);


        }
        else{
            score=0;
            mQuestionindex=0;
        }
        Toast.makeText(getApplicationContext(), "OnCreate method is called", Toast.LENGTH_SHORT).show();

        mTextQuestion = findViewById(R.id.txtq);
        QuiModel q1 = questionCollection[mQuestionindex];
        mQuiquestion = q1.getQuestion();
        mTextQuestion.setText(mQuiquestion);
        mProgressBar =findViewById(R.id.quizPB);
        mQuistats =findViewById(R.id.txtstats);
        mQuistats.setText(score+"");

        btnt = findViewById(R.id.btntrue);


        btnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluate(true);
                changeQuestionOnButtonClick();
            }
        });
        btnf = findViewById(R.id.btnfalse);

        btnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluate(false);
                changeQuestionOnButtonClick();
            }
        });
    }

    private void changeQuestionOnButtonClick(){
        mQuestionindex = (mQuestionindex+1)%10;
        if(mQuestionindex == 0){
            AlertDialog.Builder quiAlert = new AlertDialog.Builder(this);
            quiAlert.setCancelable(false);
            quiAlert.setTitle("The Qui is finished :)");
            quiAlert.setMessage("Your score is "+ score);
            quiAlert.setPositiveButton("Lets end this champ!!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            quiAlert.show();
        }
        mQuiquestion = questionCollection[mQuestionindex].getQuestion();
        mTextQuestion.setText(mQuiquestion);
        mProgressBar.incrementProgressBy(USER_PROGRESS);
        mQuistats.setText(score+"");
    }

    private void evaluate(boolean userGuess){
        boolean currentQuestion = questionCollection[mQuestionindex].isAnswer();

        if(currentQuestion == userGuess){
            Toast.makeText(getApplicationContext(),R.string.correct_toast_message,Toast.LENGTH_SHORT).show();
            score = score +1;
        }
        else{
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast_message,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "OnStart method is called", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "OnResume method is called", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "OnPaused method is called", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "OnStop method is called", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Ondestroy method is called", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_KEY,score);
        outState.putInt(INDEX_KEY,mQuestionindex);
    }
}