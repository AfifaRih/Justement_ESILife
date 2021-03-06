package com.service;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.bdd.ConnecteurBdd;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.ormObjects.AimerContenu;
import com.ormObjects.Contenu;
import com.ormObjects.Utilisateur;


@Path("contenu/mur")
public class ServiceMur {
	

	private static int nb_contenu=5;
	
	@SuppressWarnings("deprecation")
	@GET
	public Response getMur(@QueryParam("lastPost") long lastPostDate){
		ConnectionSource connexionSource;
		try {
			connexionSource = ConnecteurBdd.getConnexion();
			Dao<Contenu, Integer> daoContenu=DaoManager.createDao(connexionSource, Contenu.class);
			QueryBuilder<Contenu, Integer> constructionSelect=daoContenu.queryBuilder();
			if(lastPostDate!=0){
				constructionSelect.where().lt(Contenu.COLUMN_DATE_PUBLICATION, new Date(lastPostDate));	
			}
			constructionSelect.orderBy(Contenu.COLUMN_DATE_PUBLICATION, false).limit(nb_contenu);
			PreparedQuery<Contenu>  requetPreprer=constructionSelect.prepare();
			List<Contenu> resultatRequete=daoContenu.query(requetPreprer);
			ArrayList<JSONObject> tabJsonObjects=new ArrayList<JSONObject>();
			ObjectMapper JsonMapper=new ObjectMapper();
			
			for(int i=0;i<resultatRequete.size();i++){
				tabJsonObjects.add(new JSONObject(JsonMapper.writeValueAsString(statusConvert(resultatRequete.get(i)))));
			}

			FileInputStream fileInputStream=new FileInputStream(new File("/home/afou/Pictures/index.jpeg"));
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			int nRead;
			byte[] data = new byte[16384];

			while ((nRead = fileInputStream.read(data, 0, data.length)) != -1) {
			  buffer.write(data, 0, nRead);
			}

			buffer.flush();

			byte[] tab=buffer.toByteArray();
			
			//return Response.ok(tableauAEnvoye).build();
			return Response.ok(tab).build();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	@SuppressWarnings("deprecation")
	private StatusEnvoye  statusConvert(Contenu c){
		StatusEnvoye s=new StatusEnvoye();
		s.setContentText(c.getContenu_text());
		s.setDatePub(c.getContenu_date_publication().toGMTString());
		s.setLike(checkLike(c.getCle_utilisateur(),c.getContenu_cle()));
		s.setNbrLike(getNbLike(c.getContenu_cle()));
		s.setUserName(getUser(c.getCle_utilisateur()).getCompte_user());
		return s;
	}
	private Utilisateur getUser(int cle_utilisateur) {
		ConnectionSource connexionSource;
		try {
			connexionSource =ConnecteurBdd.getConnexion();
			Dao<Utilisateur, Integer> daoContenu=DaoManager.createDao(connexionSource, Utilisateur.class);
			return daoContenu.queryForId(cle_utilisateur);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	private int getNbLike(int contenu_cle) {
		ConnectionSource connexionSource;
		try {
			connexionSource = ConnecteurBdd.getConnexion();
			Dao<AimerContenu, Integer> daoAimer=DaoManager.createDao(connexionSource, AimerContenu.class);
			QueryBuilder<AimerContenu, Integer> constructionSelect=daoAimer.queryBuilder();
			constructionSelect.where().eq(AimerContenu.COLUMN_CLE_CONTENU,contenu_cle);
			constructionSelect.setCountOf(true);
			 return (int) daoAimer.countOf(constructionSelect.prepare());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	
	}
	private boolean checkLike(int cle_utilisateur, int contenu_cle) {
		ConnectionSource connexionSource;
		try {
			connexionSource = ConnecteurBdd.getConnexion();
			Dao<AimerContenu, Integer> daoAimer=DaoManager.createDao(connexionSource, AimerContenu.class);
			QueryBuilder<AimerContenu, Integer> constructionSelect=daoAimer.queryBuilder();
			constructionSelect.where().eq(AimerContenu.COLUMN_CLE_CONTENU,contenu_cle).and().eq(AimerContenu.COLUMN_CLE_USER,cle_utilisateur);
			constructionSelect.setCountOf(true);
			return daoAimer.countOf(constructionSelect.prepare())!=0;
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	class StatusEnvoye{
		
		private String userName;
		private String datePub;
		private String contentText;
		private int nbrLike;
		private boolean like;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getDatePub() {
			return datePub;
		}
		public void setDatePub(String datePub) {
			this.datePub = datePub;
		}
		public String getContentText() {
			return contentText;
		}
		public void setContentText(String contentText) {
			this.contentText = contentText;
		}
		public int getNbrLike() {
			return nbrLike;
		}
		public void setNbrLike(int nbrLike) {
			this.nbrLike = nbrLike;
		}
		public boolean isLike() {
			return like;
		}
		public void setLike(boolean like) {
			this.like = like;
		}
		
		
	}
}
