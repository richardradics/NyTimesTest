package io.richardradics.test.nytimes.articles.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.richardradics.test.nytimes.R;
import io.richardradics.test.nytimes.articles.presentation.model.PresentationArticle;
import io.richardradics.test.nytimes.core.listener.OnItemClick;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder>{

    private List<PresentationArticle> presentationArticles;
    private OnItemClick<String> onItemClick;

    @Inject
    ArticleListAdapter() {
        presentationArticles = new ArrayList<>(0);
    }

    public void setOnItemClickListener(OnItemClick<String> onItemClickListener) {
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_article, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PresentationArticle presentationArticle = presentationArticles.get(position);

        holder.textviewTitle.setText(presentationArticle.title());
        holder.textViewDescription.setText(presentationArticle.description());
        holder.url = presentationArticle.url();

        Picasso.with(holder.imageViewPicture.getContext())
                .load(presentationArticle.imageUrl())
                .into(holder.imageViewPicture);
    }

    public void addPresentationArticle(@NonNull PresentationArticle presentationArticle) {
        presentationArticles.add(presentationArticle);
        notifyItemInserted(presentationArticles.size() -1);
    }

    @Override
    public int getItemCount() {
        return presentationArticles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView_picture)
        ImageView imageViewPicture;

        @BindView(R.id.textView_title)
        TextView textviewTitle;

        @BindView(R.id.textView_description)
        TextView textViewDescription;

        String url;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.container)
        void onClickContainer() {
            onItemClick.onClick(url);
        }
    }
}
