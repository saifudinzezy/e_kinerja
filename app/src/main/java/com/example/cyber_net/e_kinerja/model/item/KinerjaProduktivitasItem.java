package com.example.cyber_net.e_kinerja.model.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class KinerjaProduktivitasItem implements Parcelable {

	@SerializedName("id_lap_prod")
	private String idLapProd;

	@SerializedName("kuantitas")
	private String kuantitas;

	@SerializedName("kuantitas2")
	private String kuantitas2;

	@SerializedName("id_kuantitas")
	private String id;

	@SerializedName("nip")
	private String nip;

	@SerializedName("update_on")
	private String updateOn;

	@SerializedName("satuan")
	private String satuan;

	@SerializedName("kegiatan")
	private String kegiatan;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("update_from")
	private String updateFrom;

	@SerializedName("update_by")
	private String updateBy;

	@SerializedName("status")
	private String status;

	public String getKuantitas2() {
		return kuantitas2;
	}

	public void setKuantitas2(String kuantitas2) {
		this.kuantitas2 = kuantitas2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIdLapProd(String idLapProd){
		this.idLapProd = idLapProd;
	}

	public String getIdLapProd(){
		return idLapProd;
	}

	public void setKuantitas(String kuantitas){
		this.kuantitas = kuantitas;
	}

	public String getKuantitas(){
		return kuantitas;
	}

	public void setNip(String nip){
		this.nip = nip;
	}

	public String getNip(){
		return nip;
	}

	public void setUpdateOn(String updateOn){
		this.updateOn = updateOn;
	}

	public String getUpdateOn(){
		return updateOn;
	}

	public void setSatuan(String satuan){
		this.satuan = satuan;
	}

	public String getSatuan(){
		return satuan;
	}

	public void setKegiatan(String kegiatan){
		this.kegiatan = kegiatan;
	}

	public String getKegiatan(){
		return kegiatan;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setUpdateFrom(String updateFrom){
		this.updateFrom = updateFrom;
	}

	public String getUpdateFrom(){
		return updateFrom;
	}

	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}

	public String getUpdateBy(){
		return updateBy;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"KinerjaProduktivitasItem{" + 
			"id_lap_prod = '" + idLapProd + '\'' + 
			",kuantitas = '" + kuantitas + '\'' + 
			",nip = '" + nip + '\'' + 
			",update_on = '" + updateOn + '\'' + 
			",satuan = '" + satuan + '\'' + 
			",kegiatan = '" + kegiatan + '\'' + 
			",tanggal = '" + tanggal + '\'' + 
			",update_from = '" + updateFrom + '\'' + 
			",update_by = '" + updateBy + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}

	public KinerjaProduktivitasItem() {
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.idLapProd);
		dest.writeString(this.kuantitas);
		dest.writeString(this.id);
		dest.writeString(this.nip);
		dest.writeString(this.updateOn);
		dest.writeString(this.satuan);
		dest.writeString(this.kegiatan);
		dest.writeString(this.tanggal);
		dest.writeString(this.updateFrom);
		dest.writeString(this.updateBy);
		dest.writeString(this.status);
	}

	protected KinerjaProduktivitasItem(Parcel in) {
		this.idLapProd = in.readString();
		this.kuantitas = in.readString();
		this.id = in.readString();
		this.nip = in.readString();
		this.updateOn = in.readString();
		this.satuan = in.readString();
		this.kegiatan = in.readString();
		this.tanggal = in.readString();
		this.updateFrom = in.readString();
		this.updateBy = in.readString();
		this.status = in.readString();
	}

	public static final Creator<KinerjaProduktivitasItem> CREATOR = new Creator<KinerjaProduktivitasItem>() {
		@Override
		public KinerjaProduktivitasItem createFromParcel(Parcel source) {
			return new KinerjaProduktivitasItem(source);
		}

		@Override
		public KinerjaProduktivitasItem[] newArray(int size) {
			return new KinerjaProduktivitasItem[size];
		}
	};
}