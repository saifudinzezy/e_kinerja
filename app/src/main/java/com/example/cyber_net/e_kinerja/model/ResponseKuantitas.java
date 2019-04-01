package com.example.cyber_net.e_kinerja.model;

import com.example.cyber_net.e_kinerja.model.item.KuantitasItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseKuantitas{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("kuantitas")
	private List<KuantitasItem> kuantitas;

	@SerializedName("response")
	private String response;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setKuantitas(List<KuantitasItem> kuantitas){
		this.kuantitas = kuantitas;
	}

	public List<KuantitasItem> getKuantitas(){
		return kuantitas;
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
			"ResponseKuantitas{" + 
			"pesan = '" + pesan + '\'' + 
			",kuantitas = '" + kuantitas + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}