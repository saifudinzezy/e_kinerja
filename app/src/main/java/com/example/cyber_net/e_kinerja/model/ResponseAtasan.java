package com.example.cyber_net.e_kinerja.model;

import com.example.cyber_net.e_kinerja.model.item.AtasanItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAtasan{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("atasan")
	private List<AtasanItem> atasan;

	@SerializedName("response")
	private String response;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setAtasan(List<AtasanItem> atasan){
		this.atasan = atasan;
	}

	public List<AtasanItem> getAtasan(){
		return atasan;
	}

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"ResponseAtasan{" + 
			"pesan = '" + pesan + '\'' + 
			",atasan = '" + atasan + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}