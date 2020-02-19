package com.networkIPS.task.ui.adapter;import android.content.Context;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ImageView;import android.widget.TextView;import androidx.annotation.NonNull;import androidx.recyclerview.widget.RecyclerView;import com.bumptech.glide.Glide;import com.networkIPS.task.R;import com.networkIPS.task.data.models.items.ArticleItem;import com.networkIPS.task.helpers.GenericItemClickCallback;import com.networkIPS.task.ui.adapter.base.GenericAdapter;import java.util.ArrayList;import butterknife.BindView;import butterknife.ButterKnife;public class ArticleAdapter extends GenericAdapter<ArticleItem> {    public ArticleAdapter(ArrayList<ArticleItem> items,                          Context context,                          GenericItemClickCallback<ArticleItem> adapterItemClickCallbacks) {        super(items, context, adapterItemClickCallbacks);    }    @NonNull    @Override    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {        return new ArticleHolder(LayoutInflater.from(getContext()).inflate(R.layout.report_item, parent, false));    }    @Override    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {        super.onBindViewHolder(holder, position);        ArticleHolder articleHolder = (ArticleHolder) holder;        articleHolder.bindData(position);    }    class ArticleHolder extends RecyclerView.ViewHolder {        @BindView(R.id.iv_report_item)        ImageView ivReportItem;        @BindView(R.id.tv_date)        TextView tvDate;        @BindView(R.id.tv_title)        TextView tvTitle;        @BindView(R.id.tv_byline)        TextView tvByline;        @BindView(R.id.tv_details)        TextView tvDetails;        ArticleHolder(@NonNull View itemView) {            super(itemView);            ButterKnife.bind(this, itemView);        }        void bindData(int position) {            ArticleItem item = getItem(position);            if (item != null) {                if (item.getMediaLists() != null && !item.getMediaLists().isEmpty()) {                    Glide.with(getContext()).load(item.getMediaLists().get(0).getMediaMetadata().get(2).getUrl()).into(ivReportItem);                }                tvDate.setText(item.getPublished_date());                tvByline.setText(item.getByline());                tvTitle.setText(item.getTitle());                tvDetails.setText(item.getAbstractText());            }        }    }}