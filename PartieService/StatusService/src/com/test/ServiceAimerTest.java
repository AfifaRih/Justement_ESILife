package com.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.bdd.ConnecteurBdd;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.ormObjects.AimerContenu;
import com.service.ServiceAimer;

public class ServiceAimerTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testAimerContenu() {
		String incomingData="{\"id_user\":1,\"id_contenu\":1}";
		AimerContenu objetCheck=new AimerContenu();
		objetCheck.setCle_user(1);
		objetCheck.setCle_contenu(1);
		ServiceAimer a=new ServiceAimer();
		a.aimerContenu(incomingData);
		ConnectionSource connexionSource;
		try {
			connexionSource = new JdbcConnectionSource(ConnecteurBdd.url(),ConnecteurBdd.user(),ConnecteurBdd.password());
			Dao<AimerContenu, Integer> daoAimer=DaoManager.createDao(connexionSource, AimerContenu.class);
			QueryBuilder<AimerContenu, Integer> constructionSelect=daoAimer.queryBuilder();
			Where<AimerContenu,Integer> where=constructionSelect.where();
			where.and(where.eq(AimerContenu.COLUMN_CLE_CONTENU, "1"),
			          where.eq(AimerContenu.COLUMN_CLE_USER, "1"));
			PreparedQuery<AimerContenu> requetPreprer=constructionSelect.prepare();
			assertTrue("le contenu n'a pas été aimer ",daoAimer.query(requetPreprer).size()==1);
		} catch (SQLException e) {
			fail("probleme connexion base de données");
		}
		
	}

}
