package com.example.cyber_net.e_kinerja.model.item;

import com.google.gson.annotations.SerializedName;

public class KuantitasItem{

	@SerializedName("kuantitas")
	private String kuantitas;

	@SerializedName("id")
	private String id;

	public void setKuantitas(String kuantitas){
		this.kuantitas = kuantitas;
	}

	public String getKuantitas(){
		return kuantitas;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"KuantitasItem{" + 
			"kuantitas = '" + kuantitas + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}