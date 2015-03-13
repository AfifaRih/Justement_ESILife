package com.ormObjects;



import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "contenu")
public class Contenu {
	
	public static final String COLUMN_CONTENU_CLE="contenu_cle";
	public static final String COLUMN_DATE_PUBLICATION="contenu_date_publication";
	public static final String COLUMN_DATE_MODIFICATION="contenu_date_modification";
	public static final String COLUMN_CONTENU_TEXT="contenu_text";
	public static final String COLUMN_USER_CLE="utilisateur_cle";
	
	
	
	
	@DatabaseField(id=true,columnName=COLUMN_CONTENU_CLE)
	private int contenu_cle;
	@DatabaseField(canBeNull=false,columnName=COLUMN_DATE_PUBLICATION)
	private Date contenu_date_publication;
	@DatabaseField(canBeNull=false,columnName=COLUMN_DATE_MODIFICATION)
	private Date contenu_date_modification;
	@DatabaseField(canBeNull=false,columnName=COLUMN_CONTENU_TEXT)
	private String contenu_text;
	@DatabaseField(canBeNull=false,columnName=COLUMN_USER_CLE)
	private int cle_utilisateur;
	
	public Contenu(){
		
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

	
	
	
}
