package com.example.cyber_net.e_kinerja.model.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserItem implements Parcelable {

	@SerializedName("unitkerja")
	private String unitkerja;

	@SerializedName("password")
	private String password;

	@SerializedName("opd")
	private String opd;

	@SerializedName("nip")
	private String nip;

	@SerializedName("nama")
	private String nama;

	@SerializedName("loker")
	private String loker;

	public String getLoker() {
		return loker;
	}

	public void setLoker(String loker) {
		this.loker = loker;
	}

	public void setUnitkerja(String unitkerja){
		this.unitkerja = unitkerja;
	}

	public String getUnitkerja(){
		return unitkerja;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setOpd(String opd){
		this.opd = opd;
	}

	public String getOpd(){
		return opd;
	}

	public void setNip(String nip){
		this.nip = nip;
	}

	public String getNip(){
		return nip;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	@Override
 	public String toString(){
		return 
			"UserItem{" + 
			"unitkerja = '" + unitkerja + '\'' + 
			",password = '" + password + '\'' + 
			",opd = '" + opd + '\'' + 
			",nip = '" + nip + '\'' + 
			",nama = '" + nama + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.unitkerja);
		dest.writeString(this.password);
		dest.writeString(this.opd);
		dest.writeString(this.nip);
		dest.writeString(this.nama);
		dest.writeString(this.loker);
	}

	public UserItem() {
	}

	protected UserItem(Parcel in) {
		this.unitkerja = in.readString();
		this.password = in.readString();
		this.opd = in.readString();
		this.nip = in.readString();
		this.nama = in.readString();
		this.loker = in.readString();
	}

	public static final Parcelable.Creator<UserItem> CREATOR = new Parcelable.Creator<UserItem>() {
		@Override
		public UserItem createFromParcel(Parcel source) {
			return new UserItem(source);
		}

		@Override
		public UserItem[] newArray(int size) {
			return new UserItem[size];
		}
	};
}