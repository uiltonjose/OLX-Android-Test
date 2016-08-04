package com.olxchallenge.comm;

import android.os.AsyncTask;

import com.olxchallenge.bean.Page;

public class FetchServerTask extends AsyncTask<String, Void, Page> {

    private ServerCallback callback;

    public FetchServerTask(ServerCallback callback) {
        this.callback = callback;
    }

    @Override
    protected Page doInBackground(String... params) {
        String url = null;

        if (params != null && params.length > 0) {
            url = params[0];
        }

        return CommonRequest.fetchPage(url);
    }

    @Override
    protected void onPostExecute(Page page) {
        super.onPostExecute(page);

        if (callback != null) {
            callback.resultServer(page);
        }
    }

    public interface ServerCallback {
        void resultServer(Page page);
    }
}
