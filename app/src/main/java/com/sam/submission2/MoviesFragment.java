package com.sam.submission2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {
    ArrayList<Movies> movies = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView description;
        public TextView dateRelease;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_movies, parent, false));
            image = (ImageView) itemView.findViewById(R.id.user_image);
            name = (TextView) itemView.findViewById(R.id.user_name);
            description = (TextView) itemView.findViewById(R.id.txt_overview);
            dateRelease = (TextView) itemView.findViewById(R.id.txt_date_release);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Movies movies = new Movies();
                    movies.getTitle();
                    movies.getPicture();
                    movies.getDesc();
                    movies.getDate();
                    Context context = v.getContext();
                    Intent intent = new Intent(context, MoviesDetailActivity.class);
                    intent.putExtra(MoviesDetailActivity.EXTRA_POSITION, getAdapterPosition());
//                    intent.putParcelableArrayListExtra(EXTRA_POSITION,movies);
                    context.startActivity(intent);
                }
            });
        }
    }
    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 10;
        private final String[] mTitle;
        private final String[] mDesc;
        private final String[] mDate;
        private final Drawable[] mPicture;
        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mTitle = resources.getStringArray(R.array.title_movie);
            mDesc = resources.getStringArray(R.array.desc_movie);
            mDate = resources.getStringArray(R.array.date_movie);
            TypedArray pict = resources.obtainTypedArray(R.array.picture_movie);
            mPicture = new Drawable[pict.length()];
            for (int i = 0; i < mPicture.length; i++) {
                mPicture[i] = pict.getDrawable(i);
            }
            pict.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.image.setImageDrawable(mPicture[position % mPicture.length]);
            holder.name.setText(mTitle[position % mTitle.length]);
            holder.description.setText(mDesc[position % mDesc.length]);
            holder.dateRelease.setText(mDate[position % mDate.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
