package zykj.com.barguotakeout.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ss on 15-4-29. 要买的商品
 */
public class BaGuoRank implements Parcelable{

    private String articleid;
    private String resname;
    private String reslogo;
    private String resaddress;
    private String starnum;
    private String authenscore;
    private String isrecommend;
    private String onename;
    private String twoname;
    private String threename;
    private int isstarted;

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }

    public String getReslogo() {
        return reslogo;
    }

    public void setReslogo(String reslogo) {
        this.reslogo = reslogo;
    }

    public String getResaddress() {
        return resaddress;
    }

    public void setResaddress(String resaddress) {
        this.resaddress = resaddress;
    }

    public String getStarnum() {
        return starnum;
    }

    public void setStarnum(String starnum) {
        this.starnum = starnum;
    }

    public String getAuthenscore() {
        return authenscore;
    }

    public void setAuthenscore(String authenscore) {
        this.authenscore = authenscore;
    }

    public String getIsrecommend() {
        return isrecommend;
    }

    public void setIsrecommend(String isrecommend) {
        this.isrecommend = isrecommend;
    }

    public String getOnename() {
        return onename;
    }

    public void setOnename(String onename) {
        this.onename = onename;
    }

    public String getTwoname() {
        return twoname;
    }

    public void setTwoname(String twoname) {
        this.twoname = twoname;
    }

    public String getThreename() {
        return threename;
    }

    public void setThreename(String threename) {
        this.threename = threename;
    }

    public int getIsstarted() {
        return isstarted;
    }

    public void setIsstarted(int isstarted) {
        this.isstarted = isstarted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
