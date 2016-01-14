package com.mig.cpsudev.studentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton choice1;
    RadioButton choice2;
    RadioButton choice3;
    RadioButton choice4;
    private String answer = "Answer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView testServerUrl = (TextView) findViewById(R.id.url1);
        final EditText editText = (EditText) findViewById(R.id.editText);

        choice1 = (RadioButton) findViewById(R.id.choice1);
        choice2 = (RadioButton) findViewById(R.id.choice2);
        choice3 = (RadioButton) findViewById(R.id.choice3);
        choice4 = (RadioButton) findViewById(R.id.choice4);

        Button sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choice1.isChecked()){
                    answer = "1";
                }else if (choice2.isChecked()){
                    answer = "2";
                }else if (choice3.isChecked()){
                    answer = "3";
                }else if (choice4.isChecked()){
                    answer = "4";
                }else{
                    answer = "No Answer";
                }

                String url;
                if(editText.getText().toString().equals("")){
                    url = testServerUrl.getText().toString();
                }else {
                    url = editText.getText().toString();
                }
                Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();
                PostAnswerTask task = new PostAnswerTask(MainActivity.this, answer);
                task.execute(url);
            }
        });
    }
}
