package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class package_model {

    @SerializedName("pack_id")
    @Expose
    private String packId;
    @SerializedName("pack_name")
    @Expose
    private String packName;
    @SerializedName("pack_img")
    @Expose
    private String packImg;
    @SerializedName("pack_desc")
    @Expose
    private String packDesc;
    @SerializedName("pack_price")
    @Expose
    private String packPrice;

    public String getPackId() {
        return packId;
    }

    public void setPackId(String packId) {
        this.packId = packId;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getPackImg() {
        return packImg;
    }

    public void setPackImg(String packImg) {
        this.packImg = packImg;
    }

    public String getPackDesc() {
        return packDesc;
    }

    public void setPackDesc(String packDesc) {
        this.packDesc = packDesc;
    }

    public String getPackPrice() {
        return packPrice;
    }

    public void setPackPrice(String packPrice) {
        this.packPrice = packPrice;
    }}
