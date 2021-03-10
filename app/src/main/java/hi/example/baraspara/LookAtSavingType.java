package hi.example.baraspara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LookAtSavingType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_at_saving_type);


        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String id = intent.getStringExtra("Id");
        String amount = intent.getStringExtra("Amount");

        TextView savingTypeName = (TextView) findViewById(R.id.savingTypeName);
        TextView savingTypeAmount = (TextView) findViewById(R.id.savingTypeAmount);

        savingTypeName.setText(name);
        savingTypeAmount.setText(amount);

        RecyclerView transactionViewer = (RecyclerView) findViewById(R.id.transactionViewer);


    }
}