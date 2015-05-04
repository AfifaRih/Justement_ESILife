package com.ormObjects;



import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;



@DatabaseTable(tableName = "contenu")
public class Contenu {
	
	public static final String COLUMN_CONTENU_CLE="contenu_cle";
	public static final String COLUMN_DATE_PUBLICATION="contenu_date_publication";
	public static final String COLUMN_DATE_MODIFICATION="contenu_date_modification";
	public static final String COLUMN_CONTENU_TEXT="contenu_text";
	public static final String COLUMN_USER_CLE="utilisateur_cle";
	public static final String COLUMN_CONTENU_CONTENU="contenu_binaire";
	public static final String COLUMN_CONTENU_TYPE="contenu_type";
	public static final String COLUMN_CONTENU_ACCEPTER="contenu_accepter";
	
	@DatabaseField(generatedIdSequence="contenu_cle",canBeNull=false,columnName=COLUMN_CONTENU_CLE)
	private int contenu_cle;
	@DatabaseField(canBeNull=false,columnName=COLUMN_DATE_PUBLICATION,dataType=DataType.DATE_STRING,format="DD-MM-YYYY HH:MM:SS")
	private Date contenu_date_publication;
	@DatabaseField(canBeNull=true,columnName=COLUMN_DATE_MODIFICATION,dataType=DataType.DATE_STRING,format="DD-MM-YYYY HH:MM:SS")
	private Date contenu_date_modification;
	@DatabaseField(canBeNull=false,columnName=COLUMN_CONTENU_TEXT)
	private String contenu_text;
	@DatabaseField(canBeNull=false,columnName=COLUMN_USER_CLE)
	private int cle_utilisateur;
	@DatabaseField(columnName=COLUMN_CONTENU_CONTENU, dataType=DataType.BYTE_ARRAY,canBeNull=true)
	private byte[] contenu_binaire;
	@DatabaseField(columnName=COLUMN_CONTENU_TYPE)
	private String contenu_type;
	@DatabaseField(canBeNull=false,columnName=COLUMN_CONTENU_ACCEPTER)
	private byte contenu_accepter;
	
	public byte isContenu_accepter() {
		return contenu_accepter;
	}

	public Contenu(Date contenu_date_publication,
			Date contenu_date_modification, String contenu_text,
			int cle_utilisateur, byte[] contenu_binaire, String contenu_type,
			byte contenu_accepter) {
		
		this.contenu_date_publication = contenu_date_publication;
		this.contenu_date_modification = contenu_date_modification;
		this.contenu_text = contenu_text;
		this.cle_utilisateur = cle_utilisateur;
		this.contenu_binaire = contenu_binaire;
		this.contenu_type = contenu_type;
		this.contenu_accepter = contenu_accepter;
	}

	public void setContenu_accepter(byte contenu_accepter) {
		this.contenu_accepter = contenu_accepter;
	}

	public byte[] getContenu_binaire() {
		return contenu_binaire;
	}

	public void setContenu_binaire(byte[] contenu_binaire) {
		this.contenu_binaire = contenu_binaire;
	}

	public String getContenu_type() {
		return contenu_type;
	}

	public void setContenu_type(String contenu_type) {
		this.contenu_type = contenu_type;
	}

	public Contenu(){
		
	}
	
	public Contenu(Date contenu_date_publication,
			Date contenu_date_modification, String contenu_text,
			int cle_utilisateur, byte[] contenu_binaire,
			String contenu_type, boolean b) {
		this.contenu_date_publication = contenu_date_publication;
		this.contenu_date_modification = contenu_date_modification;
		this.contenu_text = contenu_text;
		this.cle_utilisateur = cle_utilisateur;
		this.contenu_binaire = contenu_binaire;
		this.contenu_type = contenu_type;
		setContenu_accepterBoolean(b);
	}

	/*************** getters & setters***************/
	public int getContenu_cle() {
		return contenu_cle;
	}
	public void setContenu_cle(int contenu_cle) {
		this.contenu_cle = contenu_cle;
	}
	public Date getContenu_date_publication() {
		return contenu_date_publication;
	}
	public void setContenu_date_publication(Date contenu_date_publication) {
		this.contenu_date_publication = contenu_date_publication;
	}
	public Date getContenu_date_modification() {
		return contenu_date_modification;
	}
	public void setContenu_date_modification(Date contenu_date_modification) {
		this.contenu_date_modification = contenu_date_modification;
	}
	public String getContenu_text() {
		return contenu_text;
	}
	public void setContenu_text(String contenu_text) {
		this.contenu_text = contenu_text;
	}

	public int getCle_utilisateur() {
		return cle_utilisateur;
	}

	public void setCle_utilisateur(int cle_utilisateur) {
		this.cle_utilisateur = cle_utilisateur;
	}

	public void setContenu_accepterBoolean(boolean b) {
		if(b){
			contenu_accepter=1;
		}else contenu_accepter=0;
		
	}
	
	public boolean getContenu_accepterBoolean(){
		if(contenu_accepter==0){
			return false;
		}else{
			return true;
		}
	}

	
	
	
}
