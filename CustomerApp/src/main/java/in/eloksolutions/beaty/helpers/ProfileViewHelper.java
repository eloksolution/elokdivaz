package in.eloksolutions.beaty.helpers;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.net.URL;

import in.eloksolutions.beaty.activities.ProfileView;
import in.eloksolutions.beaty.util.RestServices;


/**
 * Created by welcome on 7/6/2017.
 */

public class ProfileViewHelper {

    private ProfileView mcontext;
    String companyId="2";

    public ProfileViewHelper(ProfileView mcontext) {
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

