package com.hello.kaiser.searchviewtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>  implements Filterable {

    Context context;
    List<FruitsData> list = new ArrayList<>();
    List<FruitsData> list1 = new ArrayList<>();

    public MyAdapter(Context context, List<FruitsData> list) {
        this.context = context;
        this.list = list;
        this.list1 = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_row,viewGroup,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

//        myHolder.imageView.setImageResource(list.get(i).getImage());
        myHolder.textView.setText(list.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_view);
            textView = itemView.findViewById(R.id.text_view);

        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();

                if (charString.isEmpty()){
                    list = list1;
                }else{

                    List<FruitsData> filterList = new ArrayList<>();

                    for (FruitsData data : list1){

                        if (data.getName().toLowerCase().contains(charString)){
                            filterList.add(data);
                        }
                    }
                    list = filterList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = list;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                list = (List<FruitsData>) results.values;
                notifyDataSetChanged();
            }
        };

    }
}
