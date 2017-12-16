
package io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class Medium {

    @Nullable
    @SerializedName("type")
    public abstract String type();

    @Nullable
    @SerializedName("subtype")
    public abstract String subtype();

    @Nullable
    @SerializedName("caption")
    public abstract String caption();

    @Nullable
    @SerializedName("copyright")
    public abstract String copyright();

    @Nullable
    @SerializedName("approved_for_syndication")
    public abstract Long approvedForSyndication();

    @Nullable
    @SerializedName("media-metadata")
    public abstract List<MediaMetadatum> mediaMetadata();

    public static TypeAdapter<Medium> typeAdapter(Gson gson){
        return new AutoValue_Medium.GsonTypeAdapter(gson);
    }
}
