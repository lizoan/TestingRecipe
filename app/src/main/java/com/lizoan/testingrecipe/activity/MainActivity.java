package com.lizoan.testingrecipe.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lizoan.testingrecipe.R;
import com.lizoan.testingrecipe.fragments.CategoriesFragment;
import com.lizoan.testingrecipe.fragments.RecipesFragment;
import com.lizoan.testingrecipe.utils.Utils;

public class MainActivity extends AppCompatActivity implements CategoriesFragment.OnCategorySelectedListener, RecipesFragment.OnRecipeSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setLogo(R.drawable.ic_logo);

//        assert getSupportActionBar() != null;
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Handle item menu in toolbar.
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuSearch:
                        return true;
                    case R.id.menuFavorites:
                        // Open Favorites screen.
                        Intent favoritesIntent = new Intent(getApplicationContext(), FavoritesActivity.class);
                        startActivity(favoritesIntent);
                        overridePendingTransition(R.anim.open_next, R.anim.close_main);
                        return true;
                    case R.id.menuAbout:
                        // Open about screen.
                        Intent aboutIntent = new Intent(getApplicationContext(), AboutActivity.class);
                        startActivity(aboutIntent);
                        overridePendingTransition(R.anim.open_next, R.anim.close_main);
                        return true;
                    default:
                        return true;
                }
            }
        });

        // If app run in single pane layout.
        if (findViewById(R.id.item_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of FragmentCategories.
            CategoriesFragment fragCategory = new CategoriesFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments.
            fragCategory.setArguments(getIntent().getExtras());

            // Add the fragment to the 'item_container' FrameLayout.
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_container, fragCategory).commit();
        }
    }

    @Override
    public void onCategorySelected(String ID, String CategoryName) {

        // Create an instance of RecipesFragment
        RecipesFragment fragRecipes = (RecipesFragment)
                getSupportFragmentManager().findFragmentById(R.id.item_detail_container);

        if (fragRecipes != null) {
            // In two-pane mode, show the recipes list in this activity by
            // adding or replacing the RecipesFragment using a
            // fragment transaction.
            fragRecipes.updateRecipes(ID, Utils.ARG_CATEGORY);

        } else {

            // If the fragRecipes is not available, we're in the one-pane layout and must swap frags...
            // create instance of RecipesFragment and give it an argument for the selected article
            RecipesFragment fragment = new RecipesFragment();
            Bundle args = new Bundle();
            args.putString(Utils.ARG_KEY, ID);
            args.putString(Utils.ARG_PAGE, Utils.ARG_CATEGORY);
            args.putString(Utils.ARG_CATEGORY, CategoryName);
            fragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the item_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.item_container, fragment);
            transaction.addToBackStack(null);
            transaction.setTransition(FragmentTransaction.TRANSIT_UNSET);

            // Commit the transaction.
            transaction.commit();

        }
    }

    @Override
    public void onRecipeSelected(String ID, String CategoryName) {
        // Call Detail screen and passing recipe id to that screen.
        Intent detailIntent = new Intent(getApplicationContext(), DetailsActivity.class);
        detailIntent.putExtra(Utils.ARG_KEY, ID);
        detailIntent.putExtra(Utils.ARG_PARENT_ACTIVITY, Utils.ARG_ACTIVITY_HOME);
        startActivity(detailIntent);
        overridePendingTransition(R.anim.open_next, R.anim.close_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Get the SearchView and set the searchable configuration.
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menuSearch).getActionView();
        // Assumes current activity is the searchable activity.
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        // Do not iconify the widget; expand it by default.
        searchView.setIconifiedByDefault(false);

        return true;
    }

}
