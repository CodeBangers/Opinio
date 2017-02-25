package com.tonikamitv.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionFetch extends ActionBarActivity {
    int voted;

    TextView tvQ;
    TextView tvA;
    TextView tvB;
    Button btA;
    Button btB;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_fetch);

        tvQ = (TextView) findViewById(R.id.textViewQ);
        btA = (Button) findViewById(R.id.buttonA);
        btB = (Button) findViewById(R.id.buttonB);

        userLocalStore = new UserLocalStore(this);

    }

    public void fetchme(View view)
    {
        QuestionSending questionSending=null;

        QuestionServerRequests serverRequest = new QuestionServerRequests(this);
        serverRequest.fetchQuestionDataAsyncTask(questionSending, new GetQuestionCallback() {
            @Override
            public void done(QuestionSending returnedQuestion) {
                Log.v("here", "" + returnedQuestion.question);
                tvQ.setText(returnedQuestion.question);
                btA.setText(returnedQuestion.optionA);
                btB.setText(returnedQuestion.optionB);
                Log.v("here question",""+returnedQuestion.question);
            }
        });

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

    public void sendingA(View view) {
       // Register regAge = new Register();
        //int u = regAge.useAge();
        int age = 556;
        Log.v("gjhkj", "hi it s i "+age);
        int optionASent = 1;
        int optionBSent = 0;
        voted = 1;

        Voting_Result voting_result = new Voting_Result(age, optionASent,optionBSent, voted);
        sendResult(voting_result);

        this.finish();



    }

    private void sendResult(final Voting_Result voting_result) {
        Log.v(""+voting_result, "her");
        VoteSendingRequests serverRequest = new VoteSendingRequests(this);
        serverRequest.storeVoteResultDataInBackground(voting_result, new GetResultCallback() {
            @Override
            public void done(Voting_Result voting_result) {
                //Intent loginIntent = new Intent(Question.this, Login.class);
                //startActivity(loginIntent);
//                Toast.makeText(getApplicationContext(), "" + voting_result.voted, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendingB(View view) {
     //   Register regAge = new Register();
       // int u = regAge.useAge();
        int age = 120;
        Log.v("gjhkj", "hi it s i "+age);
        int optionASent = 0;
        int optionBSent = 1;
        voted = 1;

        Voting_Result voting_result = new Voting_Result(age, optionASent,optionBSent, voted);
        sendResult(voting_result);
        this.finish();
    }
}
