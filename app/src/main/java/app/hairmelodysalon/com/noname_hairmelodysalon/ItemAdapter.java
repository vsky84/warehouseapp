package app.hairmelodysalon.com.noname_hairmelodysalon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private List<Item> items;
    private Context recyclerContext;
    private int rowLayout;

    public ItemAdapter(Context context, int rowLayout, List<Item> items){
        this.recyclerContext=context;
        this.rowLayout=rowLayout;
        this.items=items;
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.recyclerContext);
        View view = inflater.inflate(rowLayout, parent, false);
        return new ItemAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.imageView.setImageBitmap(item.getPic());
        holder.textID.setText(item.getId());
        holder.textName.setText(item.getName());
        holder.textCategory.setText(item.getCategory());
        holder.textStock.setText(String.valueOf(item.getStock()));
    }

    @Override
    public int getItemCount() {
        return (items!=null)?items.size():0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textID;
        private TextView textName;
        private TextView textCategory;
        private TextView textStock;
        public ItemViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textID = itemView.findViewById(R.id.textView_card_id);
            textName = itemView.findViewById(R.id.textView_card_name);
            textCategory = itemView.findViewById(R.id.textView_card_category);
            textStock = itemView.findViewById(R.id.textView_card_stock);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(recyclerContext,ItemDetailsActivity.class);
                    ((Activity) recyclerContext).startActivity(intent);
                }
            });
        }
    }
}
