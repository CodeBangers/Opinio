package com.tonikamitv.loginregister;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class VoteResult extends ActionBarActivity {

    TextView tvA;
    TextView tvB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_result);

        tvA = (TextView) findViewById(R.id.textViewA);
        tvB = (TextView) findViewById(R.id.textViewB);

        Voting_Result voting_result=null;

        VoteSendingRequests serverRequest = new VoteSendingRequests(this);
        serverRequest.fetchVoteResultDataAsyncTask(voting_result, new GetResultCallback() {
            @Override
            public void done(Voting_Result returnedQuestion) {
//                Log.v("here A", "" + returnedQuestion.optionASent);
  //              Log.v("here B", "" + returnedQuestion.optionBSent);

                tvA.setText("option A vote:" + Integer.toString(returnedQuestion.optionASent));
                tvB.setText("option B vote:"+Integer.toString(returnedQuestion.optionBSent));

            }
        });

    }

}
