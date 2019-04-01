package com.example.cyber_net.e_kinerja.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cyber_net.e_kinerja.model.item.KinerjaProduktivitasItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseKinerjaProduktivitas implements Parcelable {

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("response")
	private String response;

	@SerializedName("kinerja_produktivitas")
	private List<KinerjaProduktivitasItem> kinerjaProduktivitas;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}

	public void setKinerjaProduktivitas(List<KinerjaProduktivitasItem> kinerjaProduktivitas){
		this.kinerjaProduktivitas = kinerjaProduktivitas;
	}

	public List<KinerjaProduktivitasItem> getKinerjaProduktivitas(){
		return kinerjaProduktivitas;
	}

	@Override
 	public String toString(){
		return 
			"ResponseKinerjaProduktivitas{" + 
			"pesan = '" + pesan + '\'' + 
			",response = '" + response + '\'' + 
			",kinerja_produktivitas = '" + kinerjaProduktivitas + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.pesan);
		dest.writeString(this.response);
		dest.writeTypedList(this.kinerjaProduktivitas);
	}

	public ResponseKinerjaProduktivitas() {
	}

	protected ResponseKinerjaProduktivitas(Parcel in) {
		this.pesan = in.readString();
		this.response = in.readString();
		this.kinerjaProduktivitas = in.createTypedArrayList(KinerjaProduktivitasItem.CREATOR);
	}

	public static final Parcelable.Creator<ResponseKinerjaProduktivitas> CREATOR = new Parcelable.Creator<ResponseKinerjaProduktivitas>() {
		@Override
		public ResponseKinerjaProduktivitas createFromParcel(Parcel source) {
			return new ResponseKinerjaProduktivitas(source);
		}

		@Override
		public ResponseKinerjaProduktivitas[] newArray(int size) {
			return new ResponseKinerjaProduktivitas[size];
		}
	};
}