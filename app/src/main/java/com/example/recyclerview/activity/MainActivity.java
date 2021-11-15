package com.example.recyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.recyclerview.R;
import com.example.recyclerview.adaptador.RecyclerAdapter;
import com.example.recyclerview.modelCatogry.ItemList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClick, SearchView.OnQueryTextListener {
    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapter adapter;
    private List<ItemList> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        initViews();
        initValues();
        initListener();
    }

    private void initViews(){
        rvLista = findViewById(R.id.rvLista);
        svSearch = findViewById(R.id.svSearch);
    }

    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        items = getItems();
        adapter = new RecyclerAdapter(items, this);
        rvLista.setAdapter(adapter);
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    private List<ItemList> getItems() {
        List<ItemList> itemLists = new ArrayList<>();
        itemLists.add(new ItemList("RECOMENDADOS",  R.drawable.star));
        itemLists.add(new ItemList("ABRIGOS",  R.drawable.abrigo));
        itemLists.add(new ItemList("BOLSOS", R.drawable.backpack));
        itemLists.add(new ItemList("SOMBREROS",  R.drawable.hat));
        itemLists.add(new ItemList("HOMBRE",  R.drawable.man));
        itemLists.add(new ItemList("ROPA INTERIOR",  R.drawable.bra));
        itemLists.add(new ItemList("CALCETAS", R.drawable.sock2));
        itemLists.add(new ItemList("POLERAS", R.drawable.tshirt));
        itemLists.add(new ItemList("MUJERES",  R.drawable.woman));
        itemLists.add(new ItemList("ACCESORIOS",  R.drawable.clock));
        itemLists.add(new ItemList("ZAPATOS",  R.drawable.zapatos));


        return itemLists;
    }

    @Override
    public void itemClick(ItemList item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("itemDetail", item);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}
