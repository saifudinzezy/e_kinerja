package com.example.cyber_net.e_kinerja.model.item;

import com.google.gson.annotations.SerializedName;

public class PnsItem{

	@SerializedName("kode_eselon")
	private String kodeEselon;

	@SerializedName("loker")
	private String loker;

	@SerializedName("jenis_jab")
	private String jenisJab;

	@SerializedName("nama_jab")
	private String namaJab;

	@SerializedName("nip")
	private String nip;

	@SerializedName("nama_pegawai")
	private String namaPegawai;

	@SerializedName("kode_gol")
	private String kodeGol;

	@SerializedName("gelar_b")
	private String gelarB;

	@SerializedName("id_jab")
	private String idJab;

	public void setKodeEselon(String kodeEselon){
		this.kodeEselon = kodeEselon;
	}

	public String getKodeEselon(){
		return kodeEselon;
	}

	public void setLoker(String loker){
		this.loker = loker;
	}

	public String getLoker(){
		return loker;
	}

	public void setJenisJab(String jenisJab){
		this.jenisJab = jenisJab;
	}

	public String getJenisJab(){
		return jenisJab;
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

	public void setNamaPegawai(String namaPegawai){
		this.namaPegawai = namaPegawai;
	}

	public String getNamaPegawai(){
		return namaPegawai;
	}

	public void setKodeGol(String kodeGol){
		this.kodeGol = kodeGol;
	}

	public String getKodeGol(){
		return kodeGol;
	}

	public void setGelarB(String gelarB){
		this.gelarB = gelarB;
	}

	public String getGelarB(){
		return gelarB;
	}

	public void setIdJab(String idJab){
		this.idJab = idJab;
	}

	public String getIdJab(){
		return idJab;
	}

	@Override
 	public String toString(){
		return 
			"PnsItem{" + 
			"kode_eselon = '" + kodeEselon + '\'' + 
			",loker = '" + loker + '\'' + 
			",jenis_jab = '" + jenisJab + '\'' + 
			",nama_jab = '" + namaJab + '\'' + 
			",nip = '" + nip + '\'' + 
			",nama_pegawai = '" + namaPegawai + '\'' + 
			",kode_gol = '" + kodeGol + '\'' + 
			",gelar_b = '" + gelarB + '\'' + 
			",id_jab = '" + idJab + '\'' + 
			"}";
		}
}