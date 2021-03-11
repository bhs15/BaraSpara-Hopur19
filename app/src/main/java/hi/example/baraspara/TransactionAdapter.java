package hi.example.baraspara;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    String dates[],amounts[];
    Context ct;
    public TransactionAdapter(Context ct, String dates[], String amounts[])
    {
        this.ct = ct;
        this.dates = dates;
        this.amounts = amounts;
        System.out.println(amounts.length);
    }

    @NonNull
    @Override
    public TransactionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflator = LayoutInflater.from(ct);
        View view = inflator.inflate(R.layout.transaction_list_card, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.MyViewHolder holder, int position) {
        holder.date.setText("Date: " + dates[position].substring(0,11));
        holder.amount.setText("Amount: " + amounts[position]);
    }

    @Override
    public int getItemCount() {
        return amounts.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView date,amount;
        ConstraintLayout mainlayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.transaction_date);
            amount = (TextView) itemView.findViewById(R.id.transaction_amount);
            mainlayout = (ConstraintLayout) itemView.findViewById(R.id.homeActivity);
        }
    }
}