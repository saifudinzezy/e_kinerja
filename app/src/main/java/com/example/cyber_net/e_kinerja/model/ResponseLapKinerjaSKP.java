package com.example.cyber_net.e_kinerja.model;

import com.example.cyber_net.e_kinerja.model.item.LapKinerjaSkpItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseLapKinerjaSKP{

	@SerializedName("lap_kinerja_skp")
	private List<LapKinerjaSkpItem> lapKinerjaSkp;

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("response")
	private String response;

	public void setLapKinerjaSkp(List<LapKinerjaSkpItem> lapKinerjaSkp){
		this.lapKinerjaSkp = lapKinerjaSkp;
	}

	public List<LapKinerjaSkpItem> getLapKinerjaSkp(){
		return lapKinerjaSkp;
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
			"ResponseLapKinerjaSKP{" + 
			"lap_kinerja_skp = '" + lapKinerjaSkp + '\'' + 
			",pesan = '" + pesan + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}