package com.divergent.clinicmanagmentsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Appointment All Patient
 * 
 * @author Divergent
 *
 */
public class Appoinment {

	/**
	 * Make Private Custructor
	 */
	private Appoinment() {

	}

	/**
	 * Get Patient Information
	 * 
	 * @return
	 */
	public static Map<String, String> inputLabTestData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Appoinment Id");
		String appoinid = sc.nextLine();
		System.out.println("Enter Patient Name");
		String patientname = sc.nextLine();
		System.out.println("Enter Docter Name");
		String doctername = sc.nextLine();
		System.out.println("Enter Problem");
		String problem = sc.nextLine();
		System.out.println("Enter Appoinment Date");
		String appoindate = sc.nextLine();
		System.out.println("Enter doctor id");
		String doctorid = sc.nextLine();
		System.out.println("Enter Patient Id");
		String patientid = sc.nextLine();

		Map<String, String> map = new HashMap<>();
		map.put("1", appoinid);
		map.put("2", patientname);
		map.put("3", doctername);
		map.put("4", problem);
		map.put("5", appoindate);
		map.put("6", doctorid);
		map.put("7", patientid);
		return map;
	}

	/**
	 * All Information Stored By This Method
	 */
	public static void appoinmentPanel() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME,
					ClinicDatabase.PASSWORD);
			String sql = ("insert into appoinment values(?,?,?,?,?,?,?,?)");
			PreparedStatement stmt = con.prepareStatement(sql);
			Map<String, String> map = inputLabTestData();
			stmt.setString(1, map.get("1"));
			stmt.setString(2, map.get("2"));
			stmt.setString(3, map.get("3"));
			stmt.setString(4, map.get("4"));
			stmt.setString(5, map.get("5"));
			long millis = System.currentTimeMillis();
			Date date = new Date(millis);
			stmt.setDate(6, date);
			stmt.setString(7, map.get("6"));
			stmt.setString(8, map.get("7"));
			System.out.println("Insert successfully!!!!!!");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Show All Appointment
	 */
	public static void showAllAppoinment() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME,
					ClinicDatabase.PASSWORD);
			Statement st = con.createStatement();
			String sql = "select * from appoinment";
			ResultSet rs = st.executeQuery(sql);
			System.out.println(
					"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Appointmenr Patient Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			while (rs.next()) {
				int appoinid = rs.getInt(1);
				String pname = rs.getString(2);
				String dname = rs.getString(3);
				String problem = rs.getString(4);
				String appinDate = rs.getString(5);
				Date currentDate = rs.getDate(6);
				String did = rs.getString(7);
				String pid = rs.getString(8);
				System.out.printf("%6s  %15s  %15s  %20s  %12s  %12s  %5s  %5s\n", appoinid, pname, dname, problem,
						appinDate, currentDate, did, pid);
			}
			System.out.println(
					"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			st.close();
			con.close();

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}
}
