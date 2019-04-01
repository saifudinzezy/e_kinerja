package com.example.cyber_net.e_kinerja.model.item;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PenilaianPerilakuItem{

	@SerializedName("blth")
	private String blth;

	@SerializedName("integritas")
	private String integritas;

	@SerializedName("disiplin")
	private String disiplin;

	@SerializedName("kerjasama")
	private String kerjasama;

	@SerializedName("update_on")
	private String updateOn;

	@SerializedName("atasan")
	private List<AtasanItem> atasan;

	@SerializedName("pns")
	private List<PnsItem> pns;

	@SerializedName("id")
	private String id;

	@SerializedName("update_from")
	private String updateFrom;

	@SerializedName("pelayanan")
	private String pelayanan;

	@SerializedName("update_by")
	private String updateBy;

	@SerializedName("komitmen")
	private String komitmen;

	public void setBlth(String blth){
		this.blth = blth;
	}

	public String getBlth(){
		return blth;
	}

	public void setIntegritas(String integritas){
		this.integritas = integritas;
	}

	public String getIntegritas(){
		return integritas;
	}

	public void setDisiplin(String disiplin){
		this.disiplin = disiplin;
	}

	public String getDisiplin(){
		return disiplin;
	}

	public void setKerjasama(String kerjasama){
		this.kerjasama = kerjasama;
	}

	public String getKerjasama(){
		return kerjasama;
	}

	public void setUpdateOn(String updateOn){
		this.updateOn = updateOn;
	}

	public String getUpdateOn(){
		return updateOn;
	}

	public void setAtasan(List<AtasanItem> atasan){
		this.atasan = atasan;
	}

	public List<AtasanItem> getAtasan(){
		return atasan;
	}

	public void setPns(List<PnsItem> pns){
		this.pns = pns;
	}

	public List<PnsItem> getPns(){
		return pns;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUpdateFrom(String updateFrom){
		this.updateFrom = updateFrom;
	}

	public String getUpdateFrom(){
		return updateFrom;
	}

	public void setPelayanan(String pelayanan){
		this.pelayanan = pelayanan;
	}

	public String getPelayanan(){
		return pelayanan;
	}

	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}

	public String getUpdateBy(){
		return updateBy;
	}

	public void setKomitmen(String komitmen){
		this.komitmen = komitmen;
	}

	public String getKomitmen(){
		return komitmen;
	}

	@Override
 	public String toString(){
		return 
			"PenilaianPerilakuItem{" + 
			"blth = '" + blth + '\'' + 
			",integritas = '" + integritas + '\'' + 
			",disiplin = '" + disiplin + '\'' + 
			",kerjasama = '" + kerjasama + '\'' + 
			",update_on = '" + updateOn + '\'' + 
			",atasan = '" + atasan + '\'' + 
			",pns = '" + pns + '\'' + 
			",id = '" + id + '\'' + 
			",update_from = '" + updateFrom + '\'' + 
			",pelayanan = '" + pelayanan + '\'' + 
			",update_by = '" + updateBy + '\'' + 
			",komitmen = '" + komitmen + '\'' + 
			"}";
		}
}