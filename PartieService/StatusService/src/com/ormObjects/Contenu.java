package com.ormObjects;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.mysql.jdbc.Blob;


@DatabaseTable(tableName = "contenu")
public class Contenu {
	
	public static final String COLUMN_CONTENU_CLE="contenu_cle";
	public static final String COLUMN_DATE_PUBLICATION="contenu_date_publication";
	public static final String COLUMN_DATE_MODIFICATION="contenu_date_modification";
	public static final String COLUMN_CONTENU_TEXT="contenu_text";
	public static final String COLUMN_USER_CLE="utilisateur_cle";
	public static final String COLUMN_CONTENU_CONTENU="contenu_contenu";
	public static final String COLUMN_CONTENU_TYPE="contenu_type";
	public static final String COLUMN_CONTENU_ACCEPTER="contenu_accepter";
	
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
	@DatabaseField(columnName=COLUMN_CONTENU_CONTENU, dataType=DataType.BYTE_ARRAY)
	private byte[] contenu_binaire;
	@DatabaseField(columnName=COLUMN_CONTENU_TYPE)
	private String contenu_type;
	@DatabaseField(canBeNull=false,columnName=COLUMN_CONTENU_ACCEPTER)
	private boolean contenu_accepter;
	public boolean isContenu_accepter() {
		return contenu_accepter;
	}
	public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }
	public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }
	public Contenu(Date contenu_date_publication,
			Date contenu_date_modification, String contenu_text,
			int cle_utilisateur, byte[] contenu_binaire, String contenu_type,
			boolean contenu_accepter) {
		
		this.contenu_date_publication = contenu_date_publication;
		this.contenu_date_modification = contenu_date_modification;
		this.contenu_text = contenu_text;
		this.cle_utilisateur = cle_utilisateur;
		this.contenu_binaire = contenu_binaire;
		this.contenu_type = contenu_type;
		this.contenu_accepter = contenu_accepter;
	}

	public void setContenu_accepter(boolean contenu_accepter) {
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
