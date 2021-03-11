package hi.example.baraspara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LookAtSavingType extends AppCompatActivity {

    String id;
    String transactions;

    class GetThread implements Runnable{
        private volatile String data;
        @Override
        public void run() {
            try {
                this.data = Get("http://10.0.2.2:8080/lookAtTransactions/"+id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getData(){return this.data;}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_at_saving_type);


        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        id = intent.getStringExtra("Id");
        String amount = intent.getStringExtra("Amount");

        TextView savingTypeName = (TextView) findViewById(R.id.savingTypeName);
        TextView savingTypeAmount = (TextView) findViewById(R.id.savingTypeAmount);

        savingTypeName.setText(name);
        savingTypeAmount.setText(amount);

        RecyclerView transactionViewer = (RecyclerView) findViewById(R.id.transactionViewer);
        
        try {
        GetThread gt = new GetThread();
        Thread get = new Thread(gt);
        get.start();
        get.join();
        transactions=gt.getData();
        System.out.println(transactions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    String Get(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}