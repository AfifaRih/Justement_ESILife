package com.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.bdd.ConnecteurBdd;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.ormObjects.Contenu;
import com.service.ServiceStatus;


public class ServiceStatusTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testReturnTitle() {
		ServiceStatus s=new ServiceStatus();
		String JsonPacketSimule="{\"status\":\"test\",\"date_modification\":\"2/2/2002\",\"date_publication\":\"1/1/2001\",id_user:1}";
		s.posterStatus(null, JsonPacketSimule);
		ConnectionSource connexionSource;
		try {
			connexionSource = new JdbcConnectionSource(ConnecteurBdd.url(),ConnecteurBdd.user(),ConnecteurBdd.password());
			Dao<Contenu, Integer> daoContenu=DaoManager.createDao(connexionSource, Contenu.class);
			Map<String, Object> tableValeurs=new HashMap<String, Object>();
			tableValeurs.put("contenu_date_publication", new Date("1/1/2001"));
			tableValeurs.put("contenu_date_modification", new Date("2/2/2002"));
			tableValeurs.put("contenu_text", "test");
			
			java.util.List<Contenu> c=daoContenu.queryForFieldValues(tableValeurs);
			if(c.size()!=0){
				daoContenu.delete(s.getConetenu(new JSONObject(JsonPacketSimule), null));
			}
			assertFalse("ajout d'objet ne fonctionne pas correctement",c.size()==0);
			
		} catch (SQLException e) {
			fail("requete sql ne fonctionne pas");
		} catch (JSONException e) {
			fail("traduction en json object ne fonctionne pas");
		} catch (IOException e) {
			fail("traduction en json object ne fonctionne pas");
		}
		
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetConetenu() {
		ServiceStatus s=new ServiceStatus();
		String JsonPacketSimule="{\"status\":\"test\",\"date_modification\":\"2/2/2002\",\"date_publication\":\"1/1/2001\",id_user:1}";
		try {
			Contenu c=s.getConetenu(new JSONObject(JsonPacketSimule), null);
			assertTrue("transformation en objet contenu erroné",c.getContenu_date_modification().equals(new Date("2/2/2002")) && c.getContenu_date_publication().equals(new Date("1/1/2001")) && c.getContenu_text().compareTo("test")==0);
		} catch (JSONException e) {
			fail("traduction en json object ne fonctionne pas");
		} catch (IOException e) {
			fail("traduction en json object ne fonctionne pas");
		}
		
	}

}
