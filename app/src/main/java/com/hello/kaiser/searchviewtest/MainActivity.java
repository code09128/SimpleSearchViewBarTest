package com.hello.kaiser.searchviewtest;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private android.support.v7.widget.SearchView searchView;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    Button button;
    List<FruitsData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initSet();

    }

    private void initSet() {

        list.add(new FruitsData("Apple"));
        list.add(new FruitsData("Banana"));
        list.add(new FruitsData("Grapes"));
        list.add(new FruitsData("JackFruit"));
        list.add(new FruitsData("Lemon"));
        list.add(new FruitsData("Mango"));
        list.add(new FruitsData("Orange"));
        list.add(new FruitsData("Papaya"));
        list.add(new FruitsData("pear"));
        list.add(new FruitsData("Pineapple"));

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(MainActivity.this, list);
        recyclerView.setAdapter(myAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 當點擊搜索按鈕時觸發該方法
            @Override
            public boolean onQueryTextSubmit(final String query) {
                final String name = query;
                Log.e("name--------", name + "");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(MainActivity.this, "您的是："+query, Toast.LENGTH_SHORT).show();
                        if(name != null){
                            myAdapter.getFilter().filter(name);
                        }else {
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });

                return false;
            }

            // 當搜索內容改變時觸發該方法
            @Override
            public boolean onQueryTextChange(String newText) {
//                myAdapter.getFilter().filter(newText);
                return false;
            }
        });


    }


    private void initView() {
        searchView = (android.support.v7.widget.SearchView) findViewById(R.id.app_bar_search);
        recyclerView = findViewById(R.id.recycler_view);
        button = findViewById(R.id.button);
    }
}
