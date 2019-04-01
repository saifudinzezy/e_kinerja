package com.example.cyber_net.e_kinerja.model.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LapKinerjaSkpItem implements Parcelable {

	@SerializedName("update_on")
	private String updateOn;

	@SerializedName("status_skp")
	private String statusSkp;

	@SerializedName("kegiatan_skp")
	private String kegiatanSkp;

	@SerializedName("update_from")
	private String updateFrom;

	@SerializedName("id_kuantitas")
	private String idKuantitas;

	@SerializedName("kuantitas_skp")
	private String kuantitasSkp;

	@SerializedName("kuantitas_kuantitas")
	private String kuantitasKuantitas;

	@SerializedName("nip")
	private String nip;

	@SerializedName("id_lap_skp")
	private String idLapSkp;

	@SerializedName("tanggal_skp")
	private String tanggalSkp;

	@SerializedName("satuan_skp")
	private int satuanSkp;

	@SerializedName("skp_tahunan_kegiatan")
	private String skpTahunanKegiatan;

	@SerializedName("update_by")
	private String updateBy;

	@SerializedName("id_skp_tahunan")
	private int idSkpTahunan;

	public void setUpdateOn(String updateOn){
		this.updateOn = updateOn;
	}

	public String getUpdateOn(){
		return updateOn;
	}

	public void setStatusSkp(String statusSkp){
		this.statusSkp = statusSkp;
	}

	public String getStatusSkp(){
		return statusSkp;
	}

	public void setKegiatanSkp(String kegiatanSkp){
		this.kegiatanSkp = kegiatanSkp;
	}

	public String getKegiatanSkp(){
		return kegiatanSkp;
	}

	public void setUpdateFrom(String updateFrom){
		this.updateFrom = updateFrom;
	}

	public String getUpdateFrom(){
		return updateFrom;
	}

	public void setIdKuantitas(String idKuantitas){
		this.idKuantitas = idKuantitas;
	}

	public String getIdKuantitas(){
		return idKuantitas;
	}

	public void setKuantitasSkp(String kuantitasSkp){
		this.kuantitasSkp = kuantitasSkp;
	}

	public String getKuantitasSkp(){
		return kuantitasSkp;
	}

	public void setKuantitasKuantitas(String kuantitasKuantitas){
		this.kuantitasKuantitas = kuantitasKuantitas;
	}

	public String getKuantitasKuantitas(){
		return kuantitasKuantitas;
	}

	public void setNip(String nip){
		this.nip = nip;
	}

	public String getNip(){
		return nip;
	}

	public void setIdLapSkp(String idLapSkp){
		this.idLapSkp = idLapSkp;
	}

	public String getIdLapSkp(){
		return idLapSkp;
	}

	public void setTanggalSkp(String tanggalSkp){
		this.tanggalSkp = tanggalSkp;
	}

	public String getTanggalSkp(){
		return tanggalSkp;
	}

	public void setSatuanSkp(int satuanSkp){
		this.satuanSkp = satuanSkp;
	}

	public int getSatuanSkp(){
		return satuanSkp;
	}

	public void setSkpTahunanKegiatan(String skpTahunanKegiatan){
		this.skpTahunanKegiatan = skpTahunanKegiatan;
	}

	public String getSkpTahunanKegiatan(){
		return skpTahunanKegiatan;
	}

	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}

	public String getUpdateBy(){
		return updateBy;
	}

	public void setIdSkpTahunan(int idSkpTahunan){
		this.idSkpTahunan = idSkpTahunan;
	}

	public int getIdSkpTahunan(){
		return idSkpTahunan;
	}

	@Override
 	public String toString(){
		return 
			"LapKinerjaSkpItem{" + 
			"update_on = '" + updateOn + '\'' + 
			",status_skp = '" + statusSkp + '\'' + 
			",kegiatan_skp = '" + kegiatanSkp + '\'' + 
			",update_from = '" + updateFrom + '\'' + 
			",id_kuantitas = '" + idKuantitas + '\'' + 
			",kuantitas_skp = '" + kuantitasSkp + '\'' + 
			",kuantitas_kuantitas = '" + kuantitasKuantitas + '\'' + 
			",nip = '" + nip + '\'' + 
			",id_lap_skp = '" + idLapSkp + '\'' + 
			",tanggal_skp = '" + tanggalSkp + '\'' + 
			",satuan_skp = '" + satuanSkp + '\'' + 
			",skp_tahunan_kegiatan = '" + skpTahunanKegiatan + '\'' + 
			",update_by = '" + updateBy + '\'' + 
			",id_skp_tahunan = '" + idSkpTahunan + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.updateOn);
		dest.writeString(this.statusSkp);
		dest.writeString(this.kegiatanSkp);
		dest.writeString(this.updateFrom);
		dest.writeString(this.idKuantitas);
		dest.writeString(this.kuantitasSkp);
		dest.writeString(this.kuantitasKuantitas);
		dest.writeString(this.nip);
		dest.writeString(this.idLapSkp);
		dest.writeString(this.tanggalSkp);
		dest.writeInt(this.satuanSkp);
		dest.writeString(this.skpTahunanKegiatan);
		dest.writeString(this.updateBy);
		dest.writeInt(this.idSkpTahunan);
	}

	public LapKinerjaSkpItem() {
	}

	protected LapKinerjaSkpItem(Parcel in) {
		this.updateOn = in.readString();
		this.statusSkp = in.readString();
		this.kegiatanSkp = in.readString();
		this.updateFrom = in.readString();
		this.idKuantitas = in.readString();
		this.kuantitasSkp = in.readString();
		this.kuantitasKuantitas = in.readString();
		this.nip = in.readString();
		this.idLapSkp = in.readString();
		this.tanggalSkp = in.readString();
		this.satuanSkp = in.readInt();
		this.skpTahunanKegiatan = in.readString();
		this.updateBy = in.readString();
		this.idSkpTahunan = in.readInt();
	}

	public static final Parcelable.Creator<LapKinerjaSkpItem> CREATOR = new Parcelable.Creator<LapKinerjaSkpItem>() {
		@Override
		public LapKinerjaSkpItem createFromParcel(Parcel source) {
			return new LapKinerjaSkpItem(source);
		}

		@Override
		public LapKinerjaSkpItem[] newArray(int size) {
			return new LapKinerjaSkpItem[size];
		}
	};
}