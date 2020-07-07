package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Addon_model {
    @SerializedName("extra_id")
    @Expose
    private String extraId;
    @SerializedName("extra_name")
    @Expose
    private String extraName;
    @SerializedName("extra_img")
    @Expose
    private String extraImg;
    @SerializedName("extra_desc")
    @Expose
    private String extraDesc;
    @SerializedName("extra_price")
    @Expose
    private String extraPrice;

    public String getExtraId() {
        return extraId;
    }

    public void setExtraId(String extraId) {
        this.extraId = extraId;
    }

    public String getExtraName() {
        return extraName;
    }

    public void setExtraName(String extraName) {
        this.extraName = extraName;
    }

    public String getExtraImg() {
        return extraImg;
    }

    public void setExtraImg(String extraImg) {
        this.extraImg = extraImg;
    }

    public String getExtraDesc() {
        return extraDesc;
    }

    public void setExtraDesc(String extraDesc) {
        this.extraDesc = extraDesc;
    }

    public String getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(String extraPrice) {
        this.extraPrice = extraPrice;
    }
}
