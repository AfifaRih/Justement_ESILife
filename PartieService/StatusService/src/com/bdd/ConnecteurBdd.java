package com.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class ConnecteurBdd {
	static ConnectionSource connect;
	public static String urlMysql="jdbc:mysql://localhost:3306/dlala";
	public static String userMysql="root";
	public static String passwordMysql="a1551993";
	public static String driverMysql="com.mysql.jdbc.Driver";
	public static String urlOracle="jdbc:oracle:thin:@localhost:1521/XE";
	public static String userOracle="esilifeuser";
	public static String passwordOracle="esilifeuser";
	public static String driverOracle="oracle.jdbc.OracleDriver";
	public static final boolean test=false;
	
	private ConnecteurBdd(){
		
	}
	public static ConnectionSource getConnexion(){
		if(connect==null){
			try {
				connect=new JdbcConnectionSource(ConnecteurBdd.url(),ConnecteurBdd.user(),ConnecteurBdd.password());
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
