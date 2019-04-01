package com.example.cyber_net.e_kinerja.model.item;

import com.google.gson.annotations.SerializedName;

public class SkpTahunanItem{

	@SerializedName("kuantitas")
	private String kuantitas;

	@SerializedName("nip")
	private String nip;

	@SerializedName("tahun")
	private String tahun;

	@SerializedName("target_kuantitas")
	private String targetKuantitas;

	@SerializedName("target_selesai")
	private String targetSelesai;

	@SerializedName("kegiatan")
	private String kegiatan;

	@SerializedName("id_kuantitas")
	private String id;

	@SerializedName("satuan_kuantitas")
	private String satuanKuantitas;

	@SerializedName("id_skp")
	private String idSkp;

	public void setKuantitas(String kuantitas){
		this.kuantitas = kuantitas;
	}

	public String getKuantitas(){
		return kuantitas;
	}

	public void setNip(String nip){
		this.nip = nip;
	}

	public String getNip(){
		return nip;
	}

	public void setTahun(String tahun){
		this.tahun = tahun;
	}

	public String getTahun(){
		return tahun;
	}

	public void setTargetKuantitas(String targetKuantitas){
		this.targetKuantitas = targetKuantitas;
	}

	public String getTargetKuantitas(){
		return targetKuantitas;
	}

	public void setTargetSelesai(String targetSelesai){
		this.targetSelesai = targetSelesai;
	}

	public String getTargetSelesai(){
		return targetSelesai;
	}

	public void setKegiatan(String kegiatan){
		this.kegiatan = kegiatan;
	}

	public String getKegiatan(){
		return kegiatan;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setSatuanKuantitas(String satuanKuantitas){
		this.satuanKuantitas = satuanKuantitas;
	}

	public String getSatuanKuantitas(){
		return satuanKuantitas;
	}

	public void setIdSkp(String idSkp){
		this.idSkp = idSkp;
	}

	public String getIdSkp(){
		return idSkp;
	}

	@Override
 	public String toString(){
		return 
			"SkpTahunanItem{" + 
			"kuantitas = '" + kuantitas + '\'' + 
			",nip = '" + nip + '\'' + 
			",tahun = '" + tahun + '\'' + 
			",target_kuantitas = '" + targetKuantitas + '\'' + 
			",target_selesai = '" + targetSelesai + '\'' + 
			",kegiatan = '" + kegiatan + '\'' + 
			",id = '" + id + '\'' + 
			",satuan_kuantitas = '" + satuanKuantitas + '\'' + 
			",id_skp = '" + idSkp + '\'' + 
			"}";
		}
}