package com.service;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.bdd.ConnecteurBdd;
import com.google.api.client.googleapis.util.Utils;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.ormObjects.Contenu;
import com.sun.jersey.multipart.FormDataParam;


@Path("status/update")
public class ServiceStatus {
	
	@POST
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response posterStatus(@FormDataParam("file") InputStream file,
			@FormDataParam("status") String incomingData){
		try {
			JSONObject paquetRecu=new JSONObject(incomingData);
			traitementAuthentification(paquetRecu);
			ConnectionSource connexionSource=ConnecteurBdd.getConnexion();
			Dao<Contenu, Integer> daoContenu=DaoManager.createDao(connexionSource, Contenu.class);
			daoContenu.create(getConetenu(paquetRecu,file));
			return Response.status(Response.Status.ACCEPTED).build();
			
		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		}
	}
	
	private void traitementAuthentification(JSONObject paquetRecu) {
		// TODO Auto-generated method stub
		
	}

	public Contenu getConetenu(JSONObject JsonPacket,InputStream file) throws JSONException, IOException{
		Contenu status=new Contenu();
		byte[] tabFichier=getBytes(file);
		status.setContenu_text(JsonPacket.getString("status"));
		status.setCle_utilisateur(JsonPacket.getInt("id_user"));
		status.setContenu_date_modification(toDate(JsonPacket.get("date_modification")));
		status.setContenu_date_publication(toDate(JsonPacket.get("date_publication")));
		status.setContenu_type(JsonPacket.getString("type"));
		status.setContenu_binaire(tabFichier);
		status.setContenu_accepterBoolean(false);
		

		return status;
	}

	@SuppressWarnings("deprecation")
	private Date toDate(Object object) {
		return new Date((Long) object);
	}
	public static byte[] getBytes(InputStream is) throws IOException {

	    int len;
	    int size = 1024;
	    byte[] buf;

	    if (is instanceof ByteArrayInputStream) {
	      size = is.available();
	      buf = new byte[size];
	      len = is.read(buf, 0, size);
	    } else {
	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      buf = new byte[size];
	      while ((len = is.read(buf, 0, size)) != -1)
	        bos.write(buf, 0, len);
	      buf = bos.toByteArray();
	    }
	    return buf;
	  }
}
