package com.example.moviesapp.sqlite;

import android.provider.BaseColumns;

public class FavoriteColuns {
    public static final class FavoriteEntry implements BaseColumns {

        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_MOVIEID = "movieid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_RANK = "rank";
        public static final String COLUMN_POSTER_PATH = "posterpath";
        public static final String COLUMN_OVERVIEW = "overview";
    }
}
