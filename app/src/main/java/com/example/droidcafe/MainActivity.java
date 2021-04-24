package com.example.droidcafe;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements DesertListAdapter.DesertSelectedListener{

    private final Retrofit mDesertsRetrofit;
    private final DesertsApi mDesertsApi;
    private RecyclerView mDesertList;
    private DesertListAdapter mDesertListAdapter;

    public MainActivity() {
        mDesertsRetrofit = RetrofitSingleton.getDesertsRetrofit();
        mDesertsApi = mDesertsRetrofit.create(DesertsApi.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });


        mDesertList = findViewById(R.id.desertsRecyclerView);
        mDesertListAdapter = new DesertListAdapter(this);
        mDesertList.setAdapter(mDesertListAdapter);
        mDesertList.setLayoutManager(new LinearLayoutManager(this));


        mDesertsApi.getDeserts().enqueue(new Callback<List<Desert>>() {
            @Override
            public void onResponse(Call<List<Desert>> call, Response<List<Desert>> response) {
                Log.d("MainActivity", "onResponse:" + response.toString());
                List<Desert> deserts = response.body();
                Log.d("MainActivity", deserts.toString());
                mDesertListAdapter.setDesertList(deserts);
            }

            @Override
            public void onFailure(Call<List<Desert>> call, Throwable t) {
                Log.d("MainActivity", "onFailure:" + t.toString());
            }
        });
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showDonutOrder(View view) {
        displayToast(getString(R.string.donut_order_message));
    }

    public void showIceCreamOrder(View view) {
        displayToast(getString(R.string.ice_cream_order_message));
    }

    public void showFroyoOrder(View view) {
        displayToast(getString(R.string.froyo_order_message));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void desertSelected(int position) {
        Log.d("MainActivity", "Selected desert: " + position);
    }
}