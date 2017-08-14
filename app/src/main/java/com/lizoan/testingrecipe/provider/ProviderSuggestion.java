package com.lizoan.testingrecipe.provider;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by Ivan on 8/14/2017.
 */

public class ProviderSuggestion extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.lizoan.testingrecipe.providers.ProviderSuggestion";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public ProviderSuggestion(){
        setupSuggestions(AUTHORITY, MODE);
    }

}
