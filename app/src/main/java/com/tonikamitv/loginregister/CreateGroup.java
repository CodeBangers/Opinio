package com.tonikamitv.loginregister;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CreateGroup extends ActionBarActivity {

  /*  ListView lv;
    ArrayAdapter<String> Adapter;

    //UserNum user;
    //GetUserNumCallBack getUserNumCallBack;

    String singleName;
    String singleNumber;

    String[] nameArray;
    String[] numberArray;

    ArrayList<String> myList;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);



    /*    lv = (ListView) findViewById(R.id.listView);

        registerForContextMenu(lv);


        Cursor phone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);

        while (phone.moveToNext())
        {
            String name = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            String number = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            if (name!=null)
            {
                singleName += name + ",";

                singleNumber += number + ",";
            }
        }
        phone.close();

        nameArray = singleName.split(",");
        numberArray = singleNumber.split(",");

        Adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,android.R.id.text1,numberArray);

        lv.setAdapter(Adapter);*/



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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.select, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int arrayadapterposition = info.position;

        int id1 = item.getItemId();

        if (id1==R.id.action_select)
        {
            lv.requestFocus();

            lv.setItemChecked(arrayadapterposition,true);



        }
        else if (id1==R.id.action_selectAll)
        {
            final int checkedCount  = numberArray.length;

            for (int i = 0; i <  checkedCount; i++) {

                lv.setItemChecked(i,   true);

            }
        }


        return super.onContextItemSelected(item);
    }*/





}
