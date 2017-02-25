package com.tonikamitv.loginregister;

/**
 * Created by chahat on 12/3/16.
 */

import android.app.ProgressDialog;
import android.content.Context;
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


public class QuestionServerRequests {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://paliwalprateekiiitv.freeoda.com/";

    public QuestionServerRequests(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please wait...");
    }

    public void storeQuestionDataInBackground(QuestionSending questionSending,
                                          GetQuestionCallback questionCallBack) {
        progressDialog.show();
        new StoreQuestionDataAsyncTask(questionSending,questionCallBack).execute();
    }

    public void fetchQuestionDataAsyncTask(QuestionSending questionSending,GetQuestionCallback questionCallback) {
      progressDialog.show();
       new fetchQuestionDataAsyncTask(questionSending,questionCallback).execute();
    }

    /**
     * parameter sent to task upon execution progress published during
     * background computation result of the background computation
     */

    public class StoreQuestionDataAsyncTask extends AsyncTask<Void, Void, Void> {
        QuestionSending questionSending;
        GetQuestionCallback getQuestionCallback;

        public StoreQuestionDataAsyncTask(QuestionSending questionSending, GetQuestionCallback questionCallback) {
            this.questionSending = questionSending;
            this.getQuestionCallback = questionCallback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("question", questionSending.question));
            dataToSend.add(new BasicNameValuePair("optionA", questionSending.optionA));
            dataToSend.add(new BasicNameValuePair("optionB", questionSending.optionB));

            HttpParams httpRequestParams = getHttpRequestParams();

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            Log.v("here", "ner");
            HttpPost post = new HttpPost(SERVER_ADDRESS
                    + "AskQuestion.php");
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
            progressDialog.dismiss();
            getQuestionCallback.done(null);
        }

    }

   public class fetchQuestionDataAsyncTask extends AsyncTask<Void, Void, QuestionSending> {
        QuestionSending questionSending;
        GetQuestionCallback getQuestionCallback;

        public fetchQuestionDataAsyncTask(QuestionSending questionSending, GetQuestionCallback questionCallback) {
            this.questionSending = questionSending;
            this.getQuestionCallback = questionCallback;
        }

        @Override
        protected QuestionSending doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
          //  dataToSend.add(new BasicNameValuePair("question", questionSending.question));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS
                    + "FetchQuestion.php");

            QuestionSending returnedQuestion = null;

            try {
             post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);

                Log.e("hgjh",""+result);


                JSONObject jObject = new JSONObject(result);

                JSONArray apps = jObject.getJSONArray("apps");

                int n = apps.length();

                for (int i=0;i<n;++i)
                {
                    JSONObject js = apps.getJSONObject(i);

                    String question = js.getString("question");
                    String optionA = js.getString("optionA");
                    String optionB = js.getString("optionB");

                    returnedQuestion= new QuestionSending(question,optionA,optionB);
                }





       /*         if (jObject.length() != 0){
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

            return returnedQuestion;
        }

        @Override
        protected void onPostExecute(QuestionSending returnedQuestion) {
            super.onPostExecute(returnedQuestion);
            progressDialog.dismiss();
            getQuestionCallback.done(returnedQuestion);
        }
    }
}

