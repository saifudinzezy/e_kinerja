package com.example.cyber_net.e_kinerja.model;

import com.example.cyber_net.e_kinerja.model.item.SkpTahunanItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSKPTahunan {

	@SerializedName("skp_tahunan")
	private List<SkpTahunanItem> skpTahunan;

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("response")
	private String response;

	public void setSkpTahunan(List<SkpTahunanItem> skpTahunan){
		this.skpTahunan = skpTahunan;
	}

	public List<SkpTahunanItem> getSkpTahunan(){
		return skpTahunan;
	}

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

	@Override
 	public String toString(){
		return 
			"ResponseSKPTahunan{" +
			"skp_tahunan = '" + skpTahunan + '\'' + 
			",pesan = '" + pesan + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}