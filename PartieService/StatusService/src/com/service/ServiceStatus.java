package com.service;

import java.sql.SQLException;

import java.util.Date;

import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.bdd.ConnecteurBdd;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.ormObjects.Contenu;



@Path("status/update")
public class ServiceStatus {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnTitle(String incomingData){
		try {
			JSONObject paquetRecu=new JSONObject(incomingData);
			traitementAuthentification(paquetRecu);
			ConnectionSource connexionSource=new JdbcConnectionSource(ConnecteurBdd.url(),ConnecteurBdd.user(),ConnecteurBdd.password());
			Dao<Contenu, Integer> daoContenu=DaoManager.createDao(connexionSource, Contenu.class);
			daoContenu.create(getConetenu(paquetRecu));
			return Response.status(Response.Status.ACCEPTED).build();
			
		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		}
	}
	
	private void traitementAuthentification(JSONObject paquetRecu) {
		// TODO Auto-generated method stub
		
	}

	public Contenu getConetenu(JSONObject JsonPacket) throws JSONException{
		Contenu status=new Contenu();
		status.setContenu_date_modification(toDate(JsonPacket.get("date_modification")));
		status.setContenu_date_publication(toDate(JsonPacket.get("date_publication")));
		status.setContenu_text(JsonPacket.getString("status"));
		status.setCle_utilisateur(JsonPacket.getInt("id_user"));

		return status;
	}

	@SuppressWarnings("deprecation")
	private Date toDate(Object object) {
		return new Date((String) object);
	}
}
