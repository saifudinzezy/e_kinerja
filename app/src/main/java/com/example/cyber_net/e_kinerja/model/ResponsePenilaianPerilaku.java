package com.example.cyber_net.e_kinerja.model;

import com.example.cyber_net.e_kinerja.model.item.PenilaianPerilakuItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePenilaianPerilaku{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("penilaian_perilaku")
	private List<PenilaianPerilakuItem> penilaianPerilaku;

	@SerializedName("response")
	private String response;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setPenilaianPerilaku(List<PenilaianPerilakuItem> penilaianPerilaku){
		this.penilaianPerilaku = penilaianPerilaku;
	}

	public List<PenilaianPerilakuItem> getPenilaianPerilaku(){
		return penilaianPerilaku;
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
			"ResponsePenilaianPerilaku{" + 
			"pesan = '" + pesan + '\'' + 
			",penilaian_perilaku = '" + penilaianPerilaku + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}