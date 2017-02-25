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
import android.widget.Toast;


public class MainActivity extends ActionBarActivity{

    UserLocalStore userLocalStore;
    EditText etName, etAge, etUsername;

    QuestionFetch vQf = new QuestionFetch();
   // Button bLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
    //    bLogout = (Button) findViewById(R.id.bLogout);

//        bLogout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
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




  /*  @Override
    protected void onStart() {
        super.onStart();
        if (authenticate() == true) {
            displayUserDetails();
        }
    }*/

    private boolean authenticate() {
        if (userLocalStore.getLoggedInUser() == null) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    public void listofFriend(View view)
    {
        startActivity(new Intent(this,CreateGroup.class));

    }

    public void ask(View view) {
        startActivity(new Intent(this, Question.class));
    }

    public void fetch(View view) {

      //  if(vQf.voted==0)
        startActivity(new Intent(this, QuestionFetch.class));
     //   else
     //   {
       //     Toast.makeText(getApplicationContext(), "You have already voted", Toast.LENGTH_LONG).show();
        //}
    }

    public void resultsee(View view)
    {

      //if(vQf.voted ==1)
            startActivity(new Intent(this, VoteResult.class));

       // else
          //  Toast.makeText(getApplicationContext(), "Answer First", Toast.LENGTH_LONG).show();
    }



  /*  private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();
        etUsername.setText(user.username);
        etName.setText(user.name);
        etAge.setText(user.age + "");
    }*/
}
