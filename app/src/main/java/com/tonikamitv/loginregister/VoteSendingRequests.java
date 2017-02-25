package com.tonikamitv.loginregister;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chahat on 13/3/16.
 */
public class VoteSendingRequests {

    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://paliwalprateekiiitv.freeoda.com/";

    public VoteSendingRequests(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please wait...");
    }

    public void storeVoteResultDataInBackground(Voting_Result voting_result,
                                              GetResultCallback resultCallback) {
        progressDialog.show();
        new StoreVoteResultDataAsyncTask(voting_result,resultCallback).execute();
    }

    public void fetchVoteResultDataAsyncTask(Voting_Result voting_result,
                                             GetResultCallback resultCallback) {
        progressDialog.show();
        new fetchVoteResultDataAsyncTask(voting_result,resultCallback).execute();
    }

    /**
     * parameter sent to task upon execution progress published during
     * background computation result of the background computation
     */

    public class StoreVoteResultDataAsyncTask extends AsyncTask<Void, Void, Void> {
        Voting_Result voting_result;
        GetResultCallback getResultCallback;

        public StoreVoteResultDataAsyncTask(Voting_Result voting_result,
                                            GetResultCallback resultCallback) {
            this.voting_result = voting_result;
            this.getResultCallback = resultCallback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("age", Integer.toString(voting_result.age)));
            dataToSend.add(new BasicNameValuePair("optionASent", Integer.toString(voting_result.optionASent)));
            dataToSend.add(new BasicNameValuePair("optionBSent", Integer.toString(voting_result.optionBSent)));
            dataToSend.add(new BasicNameValuePair("voted", Integer.toString(voting_result.voted)));

            Log.v("datatosend",""+dataToSend);

            HttpParams httpRequestParams = getHttpRequestParams();

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            Log.v("here", "ner");
            HttpPost post = new HttpPost(SERVER_ADDRESS
                    + "SentVote.php");
            Log.v("afer", "afa"+post);

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        private HttpParams getHttpRequestParams() {
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            return httpRequestParams;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
//            progressDialog.dismiss();
            getResultCallback.done(null);
        }

    }

    public class fetchVoteResultDataAsyncTask extends AsyncTask<Void, Void, Voting_Result> {
        Voting_Result voting_result;
        GetResultCallback getResultCallback;

        public fetchVoteResultDataAsyncTask(Voting_Result voting_result,
                                            GetResultCallback resultCallback) {
            this.voting_result = voting_result;
            this.getResultCallback = resultCallback;
        }

        @Override
        protected Voting_Result doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            //  dataToSend.add(new BasicNameValuePair("question", questionSending.question));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS
                    + "FetchResult.php");

            Voting_Result returnedResult = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);

                Log.e("hgjh",""+result);


                JSONObject jObject = new JSONObject(result);

               JSONArray apps = jObject.getJSONArray("apps");

                int n = apps.length();

                for (int i=0;i<n;i++)
                {
                    JSONObject js = apps.getJSONObject(i);



                    //int age = js.getInt("age");
                    int  optionAvote = js.getInt("optionAvote");
                  int optionBvote = js.getInt("optionBvote");

                    Log.v("after string",""+optionAvote);
                    Log.v("after string",""+optionBvote);

                    //int a = Integer.parseInt(optionAvote);
                   // int b = Integer.parseInt(optionBvote);

                    //int voted = js.getInt("voted");



                // Log.v("optionA",""+a);
                  //  Log.v("optionB",""+b);


                    returnedResult= new Voting_Result(-1, optionAvote,optionBvote, -1);



                }
            /*    JSONArray optionAvote = jObject.getJSONArray("optionAvote");
                JSONArray optionBvote = jObject.getJSONArray("optionBvote");
                int n = optionAvote.length();
                int m = optionBvote.length();

                JSONObject js = optionAvote.getJSONObject(0);
                JSONObject jd = optionBvote.getJSONObject(0);

                String optionA = js.getString("optionAvote");
                String optionB = jd.getString("optionBvote");

                int a = Integer.parseInt(optionA);
                int b = Integer.parseInt(optionB);

                returnedResult = new Voting_Result(a, b);*/






         /*    if (jObject.length() != 0){
                    Log.v("happened", "2");
                    String question = jObject.getString("question");
                    String optionA = jObject.getString("optionA");
                    String optionB = jObject.getString("optionB");

Log.v("ques",""+question);

                  returnedQuestion = new QuestionSending(question,optionA,optionB);
                }*/

            } catch (Exception e) {
                e.printStackTrace();
            }

            return returnedResult;
        }

        @Override
        protected void onPostExecute(Voting_Result voting_result) {
            super.onPostExecute(voting_result);
            progressDialog.dismiss();
            getResultCallback.done(voting_result);
        }
    }
}
