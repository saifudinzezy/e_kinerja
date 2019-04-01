package com.example.cyber_net.e_kinerja.model;

import com.example.cyber_net.e_kinerja.model.item.NamaBawahanItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseNamaBawahan{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("response")
	private String response;

	@SerializedName("nama_bawahan")
	private List<NamaBawahanItem> namaBawahan;

	@SerializedName("count")
	private int count;

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

	public void setNamaBawahan(List<NamaBawahanItem> namaBawahan){
		this.namaBawahan = namaBawahan;
	}

	public List<NamaBawahanItem> getNamaBawahan(){
		return namaBawahan;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	@Override
 	public String toString(){
		return 
			"ResponseNamaBawahan{" + 
			"pesan = '" + pesan + '\'' + 
			",response = '" + response + '\'' + 
			",nama_bawahan = '" + namaBawahan + '\'' + 
			",count = '" + count + '\'' + 
			"}";
		}
}