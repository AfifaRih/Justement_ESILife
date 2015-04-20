package com.service;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.multipart.FormDataParam;


@Path("test/upload")
public class ServiceTestUploadFile {
	
	@POST
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response testUpload(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("status") String incomingJSON
			){
		try {
			@SuppressWarnings("unused")
			JSONObject paquetRecu=new JSONObject(incomingJSON);
			Image img = ImageIO.read(fileInputStream);
			JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(img)));
			
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		}
		
		
		return Response.status(Status.OK).build();
	}

}
