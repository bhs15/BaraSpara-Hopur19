package hi.example.baraspara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LookAtSavingType extends AppCompatActivity {

    String id;
    String transactions;
    Integer totalSpent=0;

    class GetThread implements Runnable{
        private volatile String data;
        @Override
        public void run() {
            try {
                this.data = Get("http://10.0.2.2:8080/transactions/lookAtTransactions/"+id);
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

        TextView left = (TextView) findViewById(R.id.left);

        savingTypeName.setText(name);
        savingTypeAmount.setText(amount);




        try {
        GetThread gt = new GetThread();
        Thread get = new Thread(gt);
        get.start();
        get.join();
        transactions=gt.getData();
        JSONArray ts = new JSONArray(transactions);
        RecyclerView transactionViewer = (RecyclerView) findViewById(R.id.transactionViewer);



        String dates[] = new String[ts.length()];
        String amounts[] = new String[ts.length()];

        for(int i=0;i<dates.length;i++){
            JSONObject js = ts.getJSONObject(i);
            dates[i] = js.get("date").toString();
            amounts[i] = js.get("amount").toString();
            totalSpent += ((int)js.get("amount"));
        }

        left.setText(totalSpent.toString());
        TransactionAdapter stAdapter = new TransactionAdapter(this, dates, amounts);

        transactionViewer.setAdapter(stAdapter);
        transactionViewer.setLayoutManager(new LinearLayoutManager(this));

        } catch (InterruptedException | JSONException e) {
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