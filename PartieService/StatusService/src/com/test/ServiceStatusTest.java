package com.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;


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

	@Test
	public void testReturnTitle() {
		ServiceStatus s=new ServiceStatus();
		String JsonPacketSimule="{\"status\":\"test\",\"date_modification\":\"2/2/2002\",\"date_publication\":\"1/1/2001\",id_user:1,type:\"le walou\"}";
		s.posterStatus(null, JsonPacketSimule);
		ConnectionSource connexionSource;
		try {
			connexionSource = new JdbcConnectionSource(ConnecteurBdd.url(),ConnecteurBdd.user(),ConnecteurBdd.password());
			Dao<Contenu, Integer> daoContenu=DaoManager.createDao(connexionSource, Contenu.class);
			java.util.List<Contenu> c=daoContenu.queryForEq(Contenu.COLUMN_CONTENU_TYPE, "le walou");
			if(c.size()!=0){
				daoContenu.deleteById(c.get(0).getContenu_cle());
			}
			assertFalse("ajout d'objet ne fonctionne pas correctement",c.size()==0);
			
		} catch (SQLException e) {
			e.printStackTrace();
			fail("requete sql ne fonctionne pas");
		}
		
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetConetenu() {
		ServiceStatus s=new ServiceStatus();
		String JsonPacketSimule="{\"status\":\"test\",\"date_modification\":\"2/2/2002\",\"date_publication\":\"1/1/2001\",id_user:1,type:\"le walou\"}";
		try {
			Contenu c=s.getConetenu(new JSONObject(JsonPacketSimule), null);
			assertTrue("transformation en objet contenu erroné",(new Date(c.getContenu_date_modification().getTime())).equals(new Date("2/2/2002")) && (new Date(c.getContenu_date_publication().getTime())).equals(new Date("1/1/2001")) && c.getContenu_text().compareTo("test")==0);
		} catch (JSONException e) {
			fail("traduction en json object ne fonctionne pas");
		} catch (IOException e) {
			fail("traduction en json object ne fonctionne pas");
		}
		
	}

}
