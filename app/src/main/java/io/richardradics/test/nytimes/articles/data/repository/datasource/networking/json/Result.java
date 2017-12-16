
package io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class Result {

    @Nullable
    @SerializedName("url")
    public abstract String url();

    @Nullable
    @SerializedName("adx_keywords")
    public abstract String adxKeywords();

    @Nullable
    @SerializedName("column")
    public abstract Object column();

    @Nullable
    @SerializedName("section")
    public abstract String section();

    @Nullable
    @SerializedName("byline")
    public abstract String byline();

    @Nullable
    @SerializedName("type")
    public abstract String type();

    @Nullable
    @SerializedName("title")
    public abstract String title();

    @Nullable
    @SerializedName("abstract")
    public abstract String _abstract();

    @Nullable
    @SerializedName("published_date")
    public abstract String publishedDate();

    @Nullable
    @SerializedName("source")
    public abstract String source();

    @Nullable
    @SerializedName("id")
    public abstract Long id();

    @Nullable
    @SerializedName("asset_id")
    public abstract Long assetId();

    @Nullable
    @SerializedName("views")
    public abstract Long views();

    @Nullable
    @SerializedName("media")
    public abstract List<Medium> media();

    public static TypeAdapter<Result> typeAdapter(Gson gson){
        return new AutoValue_Result.GsonTypeAdapter(gson);
    }
}
