package com.example.cyber_net.e_kinerja.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ResponseInformasi implements Parcelable {

	@SerializedName("ipaddress")
	private String ipaddress;

	@SerializedName("tanggal")
	private String tanggal;

	public void setIpaddress(String ipaddress){
		this.ipaddress = ipaddress;
	}

	public String getIpaddress(){
		return ipaddress;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	@Override
 	public String toString(){
		return 
			"ResponseInformasi{" + 
			"ipaddress = '" + ipaddress + '\'' + 
			",tanggal = '" + tanggal + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.ipaddress);
		dest.writeString(this.tanggal);
	}

	public ResponseInformasi() {
	}

	protected ResponseInformasi(Parcel in) {
		this.ipaddress = in.readString();
		this.tanggal = in.readString();
	}

	public static final Parcelable.Creator<ResponseInformasi> CREATOR = new Parcelable.Creator<ResponseInformasi>() {
		@Override
		public ResponseInformasi createFromParcel(Parcel source) {
			return new ResponseInformasi(source);
		}

		@Override
		public ResponseInformasi[] newArray(int size) {
			return new ResponseInformasi[size];
		}
	};
}