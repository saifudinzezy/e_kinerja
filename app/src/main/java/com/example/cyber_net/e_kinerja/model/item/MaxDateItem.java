package com.example.cyber_net.e_kinerja.model.item;

import com.google.gson.annotations.SerializedName;

public class MaxDateItem{

	@SerializedName("awal")
	private String awal;

	@SerializedName("id_date")
	private String idDate;

	@SerializedName("akhir")
	private String akhir;

	public void setAwal(String awal){
		this.awal = awal;
	}

	public String getAwal(){
		return awal;
	}

	public void setIdDate(String idDate){
		this.idDate = idDate;
	}

	public String getIdDate(){
		return idDate;
	}

	public void setAkhir(String akhir){
		this.akhir = akhir;
	}

	public String getAkhir(){
		return akhir;
	}

	@Override
 	public String toString(){
		return 
			"MaxDateItem{" + 
			"awal = '" + awal + '\'' + 
			",id_date = '" + idDate + '\'' + 
			",akhir = '" + akhir + '\'' + 
			"}";
		}
}