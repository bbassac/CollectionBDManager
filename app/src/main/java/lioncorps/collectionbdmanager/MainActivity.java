package lioncorps.collectionbdmanager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.github.johnpersano.supertoasts.SuperActivityToast;

import lioncorps.collectionbdmanager.bean.Collection;
import lioncorps.collectionbdmanager.utils.CollectionProvider;
import lioncorps.collectionbdmanager.utils.FileUtils;
import lioncorps.collectionbdmanager.utils.ListBDAdapter;
import lioncorps.collectionbdmanager.utils.ToastUtils;

public class MainActivity extends AppCompatActivity {


    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new JSONParseTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                ToastUtils.display(MainActivity.this, FileUtils.getBuildDate(MainActivity.this));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private class JSONParseTask extends AsyncTask<String, String, Collection> {
        SuperActivityToast superActivityToast;
        Collection listBD;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            superActivityToast = ToastUtils.display2(MainActivity.this,"Loading ...");
        }

        @Override
        protected Collection doInBackground(String... args) {
            listBD = CollectionProvider.getEntireCollection(MainActivity.this);
            return listBD;
        }

        @Override
        protected void onPostExecute(Collection json) {
            superActivityToast.dismiss();
            ToastUtils.display(MainActivity.this, "Data Loaded");
            final ListBDAdapter adapter = new ListBDAdapter(getApplicationContext(), listBD, MainActivity.this);
            listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);
        }
    }
}
