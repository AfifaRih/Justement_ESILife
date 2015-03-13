package com.service;

import java.sql.SQLException;
import java.util.List;

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
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.ormObjects.AimerContenu;
import com.ormObjects.Contenu;
import com.ormObjects.Utilisateur;



@Path("contenu/aimer")
public class ServiceAimer {
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	
	
	public Response aimerContenu(String incomingData){
		JSONObject paquetRecu=null;
		try {
			paquetRecu = new JSONObject(incomingData);
			traitementAuthentification(paquetRecu);
			ConnectionSource connexionSource=new JdbcConnectionSource(ConnecteurBdd.url(),ConnecteurBdd.user(),ConnecteurBdd.password());
			Dao<Utilisateur, Integer> daoUtilisateur=DaoManager.createDao(connexionSource, Utilisateur.class);
			int id_contenu=paquetRecu.getInt("id_contenu");
			int id_user=paquetRecu.getInt("id_user");
			if(daoUtilisateur.idExists(id_user)){
				Dao<AimerContenu,Integer> daoAimer=DaoManager.createDao(connexionSource,AimerContenu.class);
				AimerContenu a=new AimerContenu();
				a.setCle_user(id_user);
				a.setCle_contenu(id_contenu);
				daoAimer.create(a);
			}else{
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			
		}
		
		
		return null;
	}

	private void traitementAuthentification(JSONObject paquetRecu) {
		// TODO Auto-generated method stub
		
	}
}
