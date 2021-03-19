package com.divergent.clinicmanagmentsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Drug Class For All CRUD Operation
 * 
 * @author Divergent
 *
 */
public class Drug {

	static Scanner sc = new Scanner(System.in);

	private Drug() {

	}

	/**
	 * Show All Option
	 */
	public static void showCRUDDrug() {
		System.out.println("1. Insert Drug Data");
		System.out.println("2. Update Drug Data");
		System.out.println("3. Search Drug Data");
		System.out.println("4. Delete Drug Data");
		System.out.println("5. List All Drug");
		System.out.println("6. Back");
	}

	/**
	 * Get all Panel Option
	 */
	public static void drugPanel() {
		while (true) {
			System.out.println("Enter Your Choice : ");
			showCRUDDrug();
			String input = sc.nextLine();
			switch (input) {
			case "1":
				insertDrugData();
				break;
			case "2":
				break;
			case "3":
				searchDrugData();
				break;
			case "4":
				deleteDrugData();
				break;
			case "5":
				listAllDrug();
				break;
			case "6":
				Admin.executedMethod();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * It get Drug Infomation
	 * 
	 * @return
	 */
	public static Map<String, String> inputDoctorData() {
		System.out.println("Enter Drug_Id");
		String did = sc.nextLine();
		System.out.println("Enter Drug Name");
		String dname = sc.nextLine();
		System.out.println("Enter Drug Rate");
		String drate = sc.nextLine();

		Map<String, String> map = new HashMap<>();
		map.put("1", did);
		map.put("2", dname);
		map.put("3", drate);
		return map;
	}

	/**
	 * Insert all Drug Data
	 */
	public static void insertDrugData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME,
					ClinicDatabase.PASSWORD);
			String sql = ("insert into drug values(?,?,?)");
			PreparedStatement stmt = con.prepareStatement(sql);
			Map<String, String> map = inputDoctorData();
			stmt.setString(1, map.get("1"));
			stmt.setString(2, map.get("2"));
			stmt.setString(3, map.get("3"));
			int i = stmt.executeUpdate();
			System.out.println("Insert successfully!!!!!!" + i);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Search Drug Data
	 */
	public static void searchDrugData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME,
					ClinicDatabase.PASSWORD);
			Statement st = con.createStatement();
			System.out.println("Enter Drug Id : ");
			String id = sc.nextLine();
			String sql = "select * from drug where d_id like '" + id + "%';";
			ResultSet rs = st.executeQuery(sql);
			if (!rs.next()) {
				System.out.println("No Record is Found!\n");
				drugPanel();
			} else {
				System.out.println(
						"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Drug Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");
				String did = rs.getString(1);
				String dname = rs.getString(2);
				String rate = rs.getString(3);
				System.out.printf("%5s  %20s  %25s  \n", did, dname, rate);
				System.out.println(
						"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			}
			drugPanel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete Drug Data
	 */
	public static void deleteDrugData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME,
					ClinicDatabase.PASSWORD);
			Statement st = con.createStatement();
			System.out.println("Enter Drug Id");
			String did = sc.nextLine();
			int result = st.executeUpdate("delete from drug where d_id='" + did + "';");
			System.out.println("Delete SuccessFully...." + result);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Show All Drug List
	 */
	public static void listAllDrug() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME,
					ClinicDatabase.PASSWORD);
			Statement st = con.createStatement();
			String sql = "select * from drug";
			ResultSet rs = st.executeQuery(sql);
			System.out.println(
					"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Drug Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			while (rs.next()) {
				String did = rs.getString(1);
				String dname = rs.getString(2);
				String rate = rs.getString(3);
				System.out.printf("%5s  %20s  %25s  \n", did, dname, rate);
			}
			System.out.println(
					"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");

			drugPanel();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
