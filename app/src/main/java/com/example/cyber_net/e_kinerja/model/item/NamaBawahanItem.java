package com.example.cyber_net.e_kinerja.model.item;

import com.google.gson.annotations.SerializedName;

public class NamaBawahanItem{

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
			"NamaBawahanItem{" + 
			"loker = '" + loker + '\'' + 
			",nama_jab = '" + namaJab + '\'' + 
			",nip = '" + nip + '\'' + 
			",nama = '" + nama + '\'' + 
			",gelar_b = '" + gelarB + '\'' + 
			",id = '" + id + '\'' + 
			",gelar_d = '" + gelarD + '\'' + 
			"}";
		}
}