package in.eloksolutions.divaz.adapter;

/**
 * Created by Hi on 12/24/2016.
 */

public class AndroidVersion {
    private String recyclerViewTitleText;
    private int recyclerViewImage;
    public AndroidVersion(){

    }
    public AndroidVersion(String recyclerViewTitleText, int recyclerViewImage) {
        this.recyclerViewTitleText = recyclerViewTitleText;
        this.recyclerViewImage = recyclerViewImage;
    }

    public String getrecyclerViewTitleText() {
        return recyclerViewTitleText;
    }

    public void setAndroidVersionName(String recyclerVietTitleText) {
        this.recyclerViewTitleText = recyclerVietTitleText;
    }

    public int getrecyclerViewImage() {
        return recyclerViewImage;
    }

    public void setrecyclerViewImage(int recyclerViewImage) {
        this.recyclerViewImage = recyclerViewImage;
    }
}
