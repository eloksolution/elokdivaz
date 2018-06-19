package in.eloksolutions.divaz.helpers;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.net.URL;

import in.eloksolutions.divaz.activities.ProfileUpdate;
import in.eloksolutions.divaz.util.RestServices;


/**
 * Created by welcome on 7/6/2017.
 */

public class ProfileUpdateHelper {

    private ProfileUpdate mcontext;
    String companyId="2";

    public ProfileUpdateHelper(ProfileUpdate mcontext) {
        this.mcontext = mcontext;
    }

    public class ProfileUpdateTask extends AsyncTask<String, String, String> {
        // Call after onPreExecute method
        URL url;
        String surl;
        private ProgressDialog progress;

        public ProfileUpdateTask(String surl) {
            this.surl = surl;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(mcontext);
            progress.setMessage("Loading...");
            progress.show();
        }

        protected String doInBackground(String... urls) {
            try {
                url = new URL(surl);
            } catch (Exception e) {
            }
            return RestServices.GET(url,companyId);
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                mcontext.setValuesToTextFields(result);
            }
            System.out.println("ProflleView Updatded" + result);
            progress.dismiss();

        }
    }

}

