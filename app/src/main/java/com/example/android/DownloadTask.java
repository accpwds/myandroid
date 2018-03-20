package com.example.android;

import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * Created by Administrator on 2018/3/20.
 */

public class DownloadTask extends AsyncTask<Void,Integer,Boolean> {


    @Override
    protected void onPreExecute() {


    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
