package com.ormObjects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "utilisateur_contenu_aimer")
public class AimerContenu {
	
	@DatabaseField(canBeNull=false,columnName=COLUMN_CLE_USER)
	private int cle_user;
	@DatabaseField(canBeNull=false,columnName=COLUMN_CLE_CONTENU)
	private int cle_contenu;
	@DatabaseField(id=true,columnName=COLUMN_ID_AIMER)
	private int id_aimer;
	
	
	
	public static final String COLUMN_ID_AIMER="utilisateur_contenu_aimer_cle";
	public static final String COLUMN_CLE_USER="utilisateur_fk_cle";
	public static final String COLUMN_CLE_CONTENU="contenu_fk_cle";
	
	
	
	/***************** getters and setters *****************/
	public int getCle_user() {
		return cle_user;
	}
	public void setCle_user(int cle_user) {
		this.cle_user = cle_user;
	}
	public int getCle_contenu() {
		return cle_contenu;
	}
	public void setCle_contenu(int cle_contenu) {
		this.cle_contenu = cle_contenu;
	}
	public int getId_aimer() {
		return id_aimer;
	}
	public void setId_aimer(int id_aimer) {
		this.id_aimer = id_aimer;
	}
	
	
}
