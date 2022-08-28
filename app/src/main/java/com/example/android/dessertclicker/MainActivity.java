package com.example.android.dessertclicker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import com.example.android.dessertclicker.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private int revenue = 0;
    private int dessertsSold = 0;

    // Contains all the views
    private ActivityMainBinding binding;

    private Dessert currentDessert;

    public List<Dessert> allDesserts() {
        List<Dessert> desserts = new ArrayList<>();
        desserts.add(new Dessert(R.drawable.cupcake, 5,0));
        desserts.add(new Dessert(R.drawable.donut, 10, 5));
        desserts.add(new Dessert(R.drawable.eclair, 15, 20));
        desserts.add(new Dessert(R.drawable.froyo, 30, 50));
        desserts.add(new Dessert(R.drawable.gingerbread, 50, 100));
        desserts.add(new Dessert(R.drawable.honeycomb, 100, 200));
        desserts.add(new Dessert(R.drawable.icecreamsandwich, 500, 500));
        desserts.add(new Dessert(R.drawable.jellybean, 1000, 1000));
        desserts.add(new Dessert(R.drawable.kitkat, 2000, 2000));
        desserts.add(new Dessert(R.drawable.lollipop, 3000, 4000));
        desserts.add(new Dessert(R.drawable.marshmallow, 4000, 8000));
        desserts.add(new Dessert(R.drawable.nougat, 5000, 16000));
        desserts.add(new Dessert(R.drawable.oreo, 6000, 20000));
        return desserts;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity", "onCreate Called");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.dessertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDessertClicked();
            }
        });

        // Set the TextViews to the right values
        binding.setRevenue(revenue);
        binding.setAmountSold(dessertsSold);

        currentDessert = allDesserts().get(0);

        // Make sure the correct dessert is showing
        binding.dessertButton.setImageResource(currentDessert.getImageId());

    }

    private void onDessertClicked() {
        // Update the score
        revenue += currentDessert.getPrice();
        dessertsSold++;

        binding.setRevenue(revenue);
        binding.setAmountSold(dessertsSold);

        // Show the next dessert
        showCurrentDessert();
    }

    private void showCurrentDessert() {
        Dessert newDessert = allDesserts().get(0);

        for (Dessert dessert : allDesserts() ) {
            if (revenue >= dessert.getStartProductionAmount()) {
                newDessert = dessert;
            }
            // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
            // you'll start producing more expensive desserts as determined by startProductionAmount
            // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
            // than the amount sold.
            else break;
        }

        // If the new dessert is actually different than the current dessert, update the image
        if (newDessert != currentDessert) {
            currentDessert = newDessert;
            binding.dessertButton.setImageResource(newDessert.getImageId());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.shareMenuButton) {
            onShare();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onShare() {
        System.out.println("Share");

        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setText(getString(R.string.share_text, dessertsSold, revenue))
                .setType("text/plain")
                .getIntent();

        startActivity(shareIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Log.i("MainActivity", "onStart Called");
        Timber.i("onStart Called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
