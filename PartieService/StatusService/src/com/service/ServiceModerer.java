package com.service;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.bdd.ConnecteurBdd;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.ormObjects.Contenu;


@Path("contenu/moderer")
public class ServiceModerer {

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modererContenu(String incomingData){
		JSONObject paquetRecu = null;
		try {
			paquetRecu = new JSONObject(incomingData);
			traitementAuthentification(paquetRecu);
			ConnectionSource connexionSource=ConnecteurBdd.getConnexion();
			Dao<Contenu, Integer> daoContenu=DaoManager.createDao(connexionSource, Contenu.class);
			if(paquetRecu.getBoolean("accepter")){
				Contenu c=daoContenu.queryForId(paquetRecu.getInt("contenu_id"));
				c.setContenu_accepterBoolean(true);
				int resultat=daoContenu.update(c);
				if(resultat!=1){
					return Response.status(Response.Status.BAD_REQUEST).build();
				}else{
					return Response.status(Response.Status.ACCEPTED).build();
				}
			}else{
				int resultat=daoContenu.deleteById(paquetRecu.getInt("contenu_id"));
				if(resultat!=1){
					return Response.status(Response.Status.BAD_REQUEST).build();
				}else{
					return Response.status(Response.Status.ACCEPTED).build();
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();	
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();	
		}
	}

	private void traitementAuthentification(JSONObject paquetRecu) {
		// TODO Auto-generated method stub
		
	}
}
