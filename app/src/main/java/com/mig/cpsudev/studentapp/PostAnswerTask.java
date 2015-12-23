package com.mig.cpsudev.studentapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.apache.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Mig on 24-Dec-15.
 */
public class PostAnswerTask extends AsyncTask<String, Void, String> {
    private Context mContext;
    private String mAnswer;

    public PostAnswerTask(Context mContext, String mAnswer) {
        this.mContext = mContext;
        this.mAnswer = mAnswer;
    }

    @Override
    protected String doInBackground(String... urls) {
        return postData(urls[0], mAnswer);
    }

    private String postData(String url, String mAnswer) {
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("answer", mAnswer);
            //jsonObject.accumulate("place_id", checkPoint.getPlace_id());

            String json = jsonObject.toString();
            StringEntity se = new StringEntity(json);

            httpPost.setEntity(se);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpclient.execute(httpPost);
            inputStream = httpResponse.getEntity().getContent();
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }

    @Override
    protected void onPostExecute(String feedbackResult) {
        // do something after post is execute.
        TextView statusTextView = (TextView)((Activity)mContext).findViewById(R.id.statusTextView);
        statusTextView.setText(feedbackResult);
    }
}
