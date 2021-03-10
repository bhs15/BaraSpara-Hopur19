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

public class SavingTypeAdapter extends RecyclerView.Adapter<SavingTypeAdapter.MyViewHolder> {

    String names[],amounts[],ids[];
    Context ct;
    public SavingTypeAdapter(Context ct, String names[], String amounts[], String ids[])
    {
        this.ct = ct;
        this.names = names;
        this.amounts = amounts;
        this.ids = ids;
    }

    @NonNull
    @Override
    public SavingTypeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflator = LayoutInflater.from(ct);
        View view = inflator.inflate(R.layout.saving_type_list_card, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavingTypeAdapter.MyViewHolder holder, int position) {
        holder.name.setText("Name: " + names[position]);
        holder.amount.setText("Max Amount: " + amounts[position]);

        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ct, LookAtSavingType.class);
                intent.putExtra("Name",names[position]);
                intent.putExtra("Amount", amounts[position]);
                intent.putExtra("Id",ids[position]);
                ct.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,amount;
        ConstraintLayout mainlayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.SavingTypeName);
            amount = (TextView) itemView.findViewById(R.id.SavingTypeAmount);
            mainlayout = itemView.findViewById(R.id.homeActivity);
        }
    }
}