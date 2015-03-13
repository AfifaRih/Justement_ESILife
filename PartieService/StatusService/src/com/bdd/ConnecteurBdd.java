package com.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnecteurBdd {
	static Connection connect;
	public static String urlMysql="jdbc:mysql://localhost:3306/dlala";
	public static String userMysql="root";
	public static String passwordMysql="a1551993";
	public static String driverMysql="com.mysql.jdbc.Driver";
	public static String urlOracle="jdbc:oracle:thin:@localhost:1521/XE";
	public static String userOracle="esilifeuser";
	public static String passwordOracle="esilife1062906";
	public static String driverOracle="oracle.jdbc.driver.OracleDriver";
	public static final boolean test=false;
	
	private ConnecteurBdd(){
		
	}
	public static Connection getConnexion(){
		if(connect==null){
			try {
				if(test){
					Class.forName(driverMysql);
				}else{
					Class.forName(driverOracle);
				}
	            
	        } catch (ClassNotFoundException e) {
	        }
	        try {
	        	if(test){
	        		connect = DriverManager.getConnection(urlMysql, userMysql, passwordMysql);
				}else{
					connect = DriverManager.getConnection(urlOracle, userOracle, passwordOracle);
				}
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
		return connect;
	}
	public static String url(){
		if(test){
			return urlMysql;
		}else{
			return urlOracle;
		}
	}
	public static String user(){
		if(test){
			return userMysql;
		}else{
			return userOracle;
		}
	}
	public static String password(){
		if(test){
			return passwordMysql;
		}else{
			return passwordOracle;
		}
	}
}
