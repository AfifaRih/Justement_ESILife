package com.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;

import com.bdd.ConnecteurBdd;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.ormObjects.Contenu;
import com.service.ServiceModerer;

public class ServiceModererTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testModererContenu() {
		ServiceModerer s=new ServiceModerer();
		ConnectionSource connexionSource;
		try {
			connexionSource = new JdbcConnectionSource(ConnecteurBdd.url(),ConnecteurBdd.user(),ConnecteurBdd.password());
			Dao<Contenu, Integer> daoContenu=DaoManager.createDao(connexionSource, Contenu.class);
			Contenu status=new Contenu(new Timestamp((new Date("1/1/2001")).getTime()),new Timestamp((new Date("1/1/2001")).getTime()),"lalala",1,null,"le walou",false);
			daoContenu.create(status);
			String incomingDataSimule="{\"accepter\":\"true\",\"contenu_id\":"+daoContenu.queryForEq(Contenu.COLUMN_CONTENU_TYPE, "le walou").get(0).getContenu_cle()+"}";
			s.modererContenu(incomingDataSimule);
			Contenu resultat=daoContenu.queryForEq(Contenu.COLUMN_CONTENU_TYPE, "le walou").get(0);
			assertEquals("le contenu n'a pas été accpeter",true, resultat.getContenu_accepterBoolean());
			daoContenu.deleteById(resultat.getContenu_cle());
			status=new Contenu(new Timestamp((new Date("1/1/2001")).getTime()),new Timestamp((new Date("1/1/2001")).getTime()),"lalala",1,null,"le walou",false);
			daoContenu.create(status);
			incomingDataSimule="{\"accepter\":\"false\",\"contenu_id\":"+daoContenu.queryForEq(Contenu.COLUMN_CONTENU_TYPE, "le walou").get(0).getContenu_cle()+"}";
			s.modererContenu(incomingDataSimule);
			assertEquals("le status n'est pas supprimmer lors du rejet ",0, daoContenu.queryForEq(Contenu.COLUMN_CONTENU_TYPE, "le walou").size());
			
			
		} catch (SQLException e) {
			//fail("erreur base de données");
			e.printStackTrace();
		}
		
		
	}

}
