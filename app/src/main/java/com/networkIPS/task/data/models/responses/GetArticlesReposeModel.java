package com.networkIPS.task.data.models.responses;import com.google.gson.annotations.SerializedName;import com.networkIPS.task.data.models.items.ArticleItem;import java.util.List;public class GetArticlesReposeModel {    @SerializedName("status")    private String status;    @SerializedName("results")    private List<ArticleItem> articleItems;    public String getStatus() {        return status;    }    public void setStatus(String status) {        this.status = status;    }    public List<ArticleItem> getArticleItems() {        return articleItems;    }    public void setArticleItems(List<ArticleItem> articleItems) {        this.articleItems = articleItems;    }}