
package io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class MediaMetadatum {

    @Nullable
    @SerializedName("url")
    public abstract String url();

    @Nullable
    @SerializedName("format")
    public abstract String format();

    @Nullable
    @SerializedName("height")
    public abstract Long height();

    @Nullable
    @SerializedName("width")
    public abstract Long width();

    public static TypeAdapter<MediaMetadatum> typeAdapter(Gson gson){
        return new AutoValue_MediaMetadatum.GsonTypeAdapter(gson);
    }
}
