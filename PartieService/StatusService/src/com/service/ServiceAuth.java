package com.service;

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
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.ormObjects.AimerContenu;
import com.ormObjects.Utilisateur;

@Path("authentification")
public class ServiceAuth {
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	
	
	public Response authentifier(String incomingData){
		JSONObject paquetRecu=null;
		try {
			paquetRecu = new JSONObject(incomingData);			
			String token = paquetRecu.getString("token");
					
			
			
			//appeler le Google API pour savoir quel user ?
			
			//faire correspondre le user au token 
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return null;
	}

}
