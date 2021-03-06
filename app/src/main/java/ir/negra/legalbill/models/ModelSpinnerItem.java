package ir.negra.legalbill.models;

import com.google.gson.annotations.SerializedName;

public class ModelSpinnerItem {

    @SerializedName("id")
    String id;

    @SerializedName("title")
    String title;

    public ModelSpinnerItem() {
    }

    public ModelSpinnerItem(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
