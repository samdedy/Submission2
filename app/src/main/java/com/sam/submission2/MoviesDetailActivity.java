package com.sam.submission2;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class MoviesDetailActivity extends AppCompatActivity {
    public static final String EXTRA_POSITION = "position";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
        // collapsingToolbar.setTitle(getString(R.string.item_title));

        int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        Resources resources = getResources();
        String[] title = resources.getStringArray(R.array.title_movie);
        collapsingToolbar.setTitle(title[postion % title.length]);

        String[] desc = resources.getStringArray(R.array.desc_movie);
        TextView Description = (TextView) findViewById(R.id.desc_detail);
        Description.setText(desc[postion % desc.length]);

        String[] date = resources.getStringArray(R.array.date_movie);
        TextView dateRelease =  (TextView) findViewById(R.id.date_release);
        dateRelease.setText(date[postion % date.length]);

        TypedArray placePictures = resources.obtainTypedArray(R.array.picture_movie);
        ImageView placePicutre = (ImageView) findViewById(R.id.image);
        placePicutre.setImageDrawable(placePictures.getDrawable(postion % placePictures.length()));

        placePictures.recycle();
    }
}
