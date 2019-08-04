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

public class TvShowFragment extends Fragment {
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
        public ImageView avator;
        public TextView name;
        public TextView description;
        public TextView dateRelease;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_tvshow, parent, false));
            avator = (ImageView) itemView.findViewById(R.id.user_image);
            name = (TextView) itemView.findViewById(R.id.user_name);
            description = (TextView) itemView.findViewById(R.id.txt_overview);
            dateRelease = (TextView) itemView.findViewById(R.id.txt_date_release);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, TvShowDetailActivity.class);
                    intent.putExtra(TvShowDetailActivity.EXTRA_POSITION, getAdapterPosition());
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
            mTitle = resources.getStringArray(R.array.title_tvshow);
            mDesc = resources.getStringArray(R.array.desc_tvshow);
            mDate = resources.getStringArray(R.array.date_tvshow);
            TypedArray pict = resources.obtainTypedArray(R.array.picture_tvshow);
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
            holder.avator.setImageDrawable(mPicture[position % mPicture.length]);
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
