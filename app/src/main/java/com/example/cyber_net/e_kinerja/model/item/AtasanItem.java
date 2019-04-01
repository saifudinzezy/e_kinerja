package com.example.cyber_net.e_kinerja.model.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AtasanItem implements Parcelable {

	@SerializedName("loker")
	private String loker;

	@SerializedName("nama_jab")
	private String namaJab;

	@SerializedName("nip")
	private String nip;

	@SerializedName("nama")
	private String nama;

	@SerializedName("gelar_b")
	private String gelarB;

	@SerializedName("id")
	private String id;

	@SerializedName("gelar_d")
	private String gelarD;

	@SerializedName("count")
	private String count;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setLoker(String loker){
		this.loker = loker;
	}

	public String getLoker(){
		return loker;
	}

	public void setNamaJab(String namaJab){
		this.namaJab = namaJab;
	}

	public String getNamaJab(){
		return namaJab;
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

	public void setGelarB(String gelarB){
		this.gelarB = gelarB;
	}

	public String getGelarB(){
		return gelarB;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setGelarD(String gelarD){
		this.gelarD = gelarD;
	}

	public String getGelarD(){
		return gelarD;
	}

	@Override
 	public String toString(){
		return 
			"AtasanItem{" + 
			"loker = '" + loker + '\'' + 
			",nama_jab = '" + namaJab + '\'' + 
			",nip = '" + nip + '\'' + 
			",nama = '" + nama + '\'' + 
			",gelar_b = '" + gelarB + '\'' + 
			",id = '" + id + '\'' + 
			",gelar_d = '" + gelarD + '\'' + 
			"}";
		}

	public AtasanItem() {
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.loker);
		dest.writeString(this.namaJab);
		dest.writeString(this.nip);
		dest.writeString(this.nama);
		dest.writeString(this.gelarB);
		dest.writeString(this.id);
		dest.writeString(this.gelarD);
		dest.writeString(this.count);
	}

	protected AtasanItem(Parcel in) {
		this.loker = in.readString();
		this.namaJab = in.readString();
		this.nip = in.readString();
		this.nama = in.readString();
		this.gelarB = in.readString();
		this.id = in.readString();
		this.gelarD = in.readString();
		this.count = in.readString();
	}

	public static final Creator<AtasanItem> CREATOR = new Creator<AtasanItem>() {
		@Override
		public AtasanItem createFromParcel(Parcel source) {
			return new AtasanItem(source);
		}

		@Override
		public AtasanItem[] newArray(int size) {
			return new AtasanItem[size];
		}
	};
}