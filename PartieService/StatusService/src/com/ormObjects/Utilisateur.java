package com.ormObjects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "Utilisateur")
public class Utilisateur {
	@DatabaseField(id=true,columnName=COLUMN_USER_ID)
	private int cle_user;
	@DatabaseField(canBeNull=false,columnName=COLUMN_USER_ID)
	private String compte_user;
	@DatabaseField(canBeNull=false,columnName=COLUMN_BLOQUE,defaultValue="0")
	private int booleenBloque;
	
	
	public static final String COLUMN_USER_ID="utilisateur_cle";
	public static final String COLUMN_USER_COMPTE="utilisateur_compte";
	public static final String COLUMN_BLOQUE="utilisateur_bloque";
	
	
	/***************** getters and setters *****************/
	public int getCle_user() {
		return cle_user;
	}
	public void setCle_user(int cle_user) {
		this.cle_user = cle_user;
	}
	public String getCompte_user() {
		return compte_user;
	}
	public void setCompte_user(String compte_user) {
		this.compte_user = compte_user;
	}
	public int isBooleenBloque() {
		return booleenBloque;
	}
	public void setBooleenBloque(int booleenBloque) {
		this.booleenBloque = booleenBloque;
	}
	
	
}
