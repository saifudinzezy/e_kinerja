package com.example.cyber_net.e_kinerja.model;

import com.example.cyber_net.e_kinerja.model.item.MaxDateItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMaxDate{

	@SerializedName("max_date")
	private List<MaxDateItem> maxDate;

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("response")
	private String response;

	public void setMaxDate(List<MaxDateItem> maxDate){
		this.maxDate = maxDate;
	}

	public List<MaxDateItem> getMaxDate(){
		return maxDate;
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
			"ResponseMaxDate{" + 
			"max_date = '" + maxDate + '\'' + 
			",pesan = '" + pesan + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}