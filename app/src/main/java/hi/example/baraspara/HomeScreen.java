package hi.example.baraspara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.commons.lang3.SerializationUtils;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Intent intent = getIntent();
        User currnetUser = SerializationUtils.deserialize(intent.getByteArrayExtra("USER"));
        TextView textView = (TextView) findViewById(R.id.Usertest);
        textView.setText(currnetUser.getUsername());
    }
}