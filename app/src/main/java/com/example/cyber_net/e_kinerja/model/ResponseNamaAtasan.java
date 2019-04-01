package com.example.cyber_net.e_kinerja.model;

import com.example.cyber_net.e_kinerja.model.item.NamaAtasanItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseNamaAtasan{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("response")
	private String response;

	@SerializedName("count")
	private int count;

	@SerializedName("nama_atasan")
	private List<NamaAtasanItem> namaAtasan;

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

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setNamaAtasan(List<NamaAtasanItem> namaAtasan){
		this.namaAtasan = namaAtasan;
	}

	public List<NamaAtasanItem> getNamaAtasan(){
		return namaAtasan;
	}

	@Override
 	public String toString(){
		return 
			"ResponseNamaAtasan{" + 
			"pesan = '" + pesan + '\'' + 
			",response = '" + response + '\'' + 
			",count = '" + count + '\'' + 
			",nama_atasan = '" + namaAtasan + '\'' + 
			"}";
		}
}