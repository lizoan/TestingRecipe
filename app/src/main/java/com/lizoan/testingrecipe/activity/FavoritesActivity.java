package com.lizoan.testingrecipe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lizoan.testingrecipe.R;
import com.lizoan.testingrecipe.fragments.RecipesFragment;
import com.lizoan.testingrecipe.utils.Utils;

/**
 * Created by Ivan on 8/13/2017.
 */

public class FavoritesActivity extends AppCompatActivity implements RecipesFragment.OnRecipeSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Set up the toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            // Create the recipes fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(Utils.ARG_PAGE, Utils.ARG_FAVORITES);
            RecipesFragment fragment = new RecipesFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_container, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }

        // Handle item menu in toolbar.
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case android.R.id.home:
                        finish();
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    @Override
    public void onRecipeSelected(String ID, String CategoryName) {
        // Call Detail screen and passing recipe id to that screen.
        Intent detailIntent = new Intent(getApplicationContext(), DetailsActivity.class);
        detailIntent.putExtra(Utils.ARG_KEY, ID);
        detailIntent.putExtra(Utils.ARG_PARENT_ACTIVITY, Utils.ARG_ACTIVITY_FAVORITES);
        startActivity(detailIntent);
        overridePendingTransition(R.anim.open_next, R.anim.close_main);
    }

    // Add transition when back button pressed.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.open_main, R.anim.close_next);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Re-create recipes fragment when back to Favorites screen.
        Bundle arguments = new Bundle();
        arguments.putString(Utils.ARG_PAGE, Utils.ARG_FAVORITES);
        RecipesFragment fragment = new RecipesFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

}
