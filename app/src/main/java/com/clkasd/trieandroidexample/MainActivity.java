package com.clkasd.trieandroidexample;

import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.Toast;

import com.clkasd.trieandroidexample.Model.City;
import com.clkasd.trieandroidexample.Trie.Trie;
import com.clkasd.trieandroidexample.Util.CitiesFactory;
import com.clkasd.trieandroidexample.Util.CitiesRecyclerViewAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CitiesFactory.JsonReadListener,CitiesRecyclerViewAdapter.CityClickedListener {

    RecyclerView citiesRecyclerView;
    CitiesFactory factory;
    Trie<City> trie;
    CitiesRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        findViews();
        fillViews();

    }

    private void init() {
        trie = new Trie<>();
        factory = new CitiesFactory();
        factory.setJsonReadListener(this);
        factory.getCities(this);
    }

    private void fillViews() {

    }

    private void findViews() {
        citiesRecyclerView = findViewById(R.id.citiesView);
    }

    List<City> unfilteredCities;
    @Override
    public void onFileRead(List<City> cities) {
        new TrieInsertionTask().execute(cities);
        unfilteredCities = cities;
        adapter = new CitiesRecyclerViewAdapter(cities,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        citiesRecyclerView.setLayoutManager(layoutManager);
        citiesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        citiesRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.equals(""))
                    adapter.setItemList(unfilteredCities);

                else
                    adapter.setItemList(trie.getPayloadWithPrefix(s));
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }

    @Override
    public void onCityClicked(City city) {

    }

    private class TrieInsertionTask extends AsyncTask<List<City>,Void,Void>
    {

        @Override
        protected Void doInBackground(List<City>[] lists) {
            for (City city : lists[0]) {
                trie.insert(city.getName(),city);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(MainActivity.this,"Trie insertion complete.\r\nNow you can search!",Toast.LENGTH_LONG).show();
        }
    }
}
