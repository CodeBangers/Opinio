package com.tonikamitv.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ListResourceBundle;

public class Question extends ActionBarActivity implements View.OnClickListener{
    EditText et;
    EditText etA;
    EditText etB;
    TextView tv;
    Button bSend;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        et = (EditText) findViewById(R.id.editText);
        etA = (EditText) findViewById(R.id.editTextA);
        etB = (EditText) findViewById(R.id.editTextB);
        //tv = (TextView) findViewById(R.id.textView);
        bSend = (Button) findViewById(R.id.send);
        userLocalStore = new UserLocalStore(this);


      bSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                String question = et.getText().toString();
                String optionA = etA.getText().toString();
                String optionB = etB.getText().toString();


                QuestionSending questionSending = new QuestionSending(question,optionA,optionB);
                sendQuestion(questionSending);
                Log.v("kyk",question+"");
                break;
        }
    }

    private void sendQuestion(final QuestionSending questionSending) {
        Log.v(""+questionSending, "her");
        QuestionServerRequests serverRequest = new QuestionServerRequests(this);
        serverRequest.storeQuestionDataInBackground(questionSending, new GetQuestionCallback() {
            @Override
            public void done(QuestionSending returnedQuestion) {
                //Intent loginIntent = new Intent(Question.this, Login.class);
                //startActivity(loginIntent);
                Toast.makeText(getApplicationContext(), "Question Sent" , Toast.LENGTH_SHORT).show();
            }
        });
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.bLogout) {
            userLocalStore.clearUserData();
            userLocalStore.setUserLoggedIn(false);
            Intent loginIntent = new Intent(this, Login.class);
            startActivity(loginIntent);
            return  true;
        }

        return super.onOptionsItemSelected(item);
    }
}

