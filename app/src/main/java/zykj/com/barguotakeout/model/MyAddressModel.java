package zykj.com.barguotakeout.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lss on 2015/5/16.
 */
public class MyAddressModel implements Parcelable {

    public MyAddressModel(){}

    private String addid;
    private String uid;
    private String realname;
    private String phonenum;
    private String postcode;
    private String address;
    private String addressdetail;
    private String isdefault;

    public String getAddid() {
        return addid;
    }

    public void setAddid(String addid) {
        this.addid = addid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressdetail() {
        return addressdetail;
    }

    public void setAddressdetail(String addressdetail) {
        this.addressdetail = addressdetail;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public MyAddressModel(Parcel in){
        addid=in.readString();
        uid=in.readString();
        realname=in.readString();
        phonenum=in.readString();
        postcode=in.readString();
        address=in.readString();
        addressdetail=in.readString();
        isdefault=in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(addid);
        parcel.writeString(uid);
        parcel.writeString(realname);
        parcel.writeString(phonenum);
        parcel.writeString(postcode);
        parcel.writeString(address);
        parcel.writeString(addressdetail);
        parcel.writeString(isdefault);
    }

    public static final Creator<MyAddressModel> CREATOR=new Creator<MyAddressModel>() {
        @Override
        public MyAddressModel createFromParcel(Parcel source) {
            return new MyAddressModel(source);
        }

        @Override
        public MyAddressModel[] newArray(int size) {
            return new MyAddressModel[0];
        }
    };
}
