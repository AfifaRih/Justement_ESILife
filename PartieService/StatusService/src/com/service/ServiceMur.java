package com.service;

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

import org.apache.coyote.spdy.SpdyProxyProtocol.TomcatJioHandler;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.bdd.ConnecteurBdd;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.ormObjects.Contenu;


@Path("contenu/mur")
public class ServiceMur {

	private static int nb_contenu=5;
	
	@SuppressWarnings("deprecation")
	@GET
	public Response getMur(@QueryParam("lastPost") long lastPostDate){
		ConnectionSource connexionSource;
		try {
			connexionSource = new JdbcConnectionSource(ConnecteurBdd.url(),ConnecteurBdd.user(),ConnecteurBdd.password());
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
				tabJsonObjects.add(new JSONObject(JsonMapper.writeValueAsString(resultatRequete.get(i))));
			}
			JSONArray tableauAEnvoye=new JSONArray(tabJsonObjects);
			return Response.ok(tableauAEnvoye).build();
			
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
}
