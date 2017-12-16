
package io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class ArticlesResponse {

    @Nullable
    @SerializedName("status")
    public abstract String status();

    @Nullable
    @SerializedName("copyright")
    public abstract String copyright();

    @Nullable
    @SerializedName("num_results")
    public abstract Long numResults();

    @Nullable
    @SerializedName("results")
    public abstract List<Result> results();

    public static TypeAdapter<ArticlesResponse> typeAdapter(Gson gson){
        return new AutoValue_ArticlesResponse.GsonTypeAdapter(gson);
    }
}
