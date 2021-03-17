package com.divergent.clinicmanagmentsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.sql.*;

/**
 * All Opeartion Of CRUD Lab Test
 * 
 * @author Divergent
 *
 */
public class LabTest {

	public static void showCRUDDrug() {
		System.out.println("1. Insert Lab Test Data");
		System.out.println("2. Update Lab Test Data");
		System.out.println("3. Search Lab Test Data");
		System.out.println("4. Delete Lab Test Data");
		System.out.println("5. List All Lab Test");
		System.out.println("6. Back");
	}

	public void labTestPanel() {
		Scanner sc = new Scanner(System.in);
		back: while (true) {
			System.out.println("Enter Your Choice : ");

			showCRUDDrug();
			String input = sc.nextLine();
			switch (input) {
			case "1":
				insertLabTestData();
				break;
			case "2":
				updateLabTestDoctor();
				break;
			case "3":
				searchLabTestData();
				break;
			case "4":
				deleteLabTestData();
				break;
			case "5":
				listAllLabTest();
				break;
			case "6":
				break back;
			default:
				break;
			}
		}
	}

	/**
	 * Input Lab Test Data By Admin
	 * 
	 * @return
	 */
	public static Map<String, String> inputLabTestData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter LabTest_Id");
		String lbid = sc.nextLine();
		System.out.println("Enter Patient Id");
		String pid = sc.nextLine();
		System.out.println("Enter Test");
		String testname = sc.nextLine();
		System.out.println("Enter Rate of Test");
		int rate = sc.nextInt();
		String ratestring = Integer.toString(rate);

		Map<String, String> map = new HashMap<String, String>();
		map.put("1", lbid);
		map.put("2", pid);
		map.put("3", testname);
		map.put("4", ratestring);
		return map;
	}

	/**
	 * Insert Lab Test Data
	 */
	public void insertLabTestData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user,
					ClinicDatabase.password);
			String sql = ("insert into lab_test values(?,?,?,?,?)");
			PreparedStatement stmt = con.prepareStatement(sql);
			Map<String, String> map = inputLabTestData();
			stmt.setString(1, map.get("1"));
			stmt.setString(2, map.get("2"));
			stmt.setString(3, map.get("3"));
			stmt.setString(4, map.get("4"));
			long millis = System.currentTimeMillis();
			Date date = new Date(millis);
			stmt.setDate(5, (java.sql.Date) date);
			stmt.setString(6, "5");
			System.out.println("Insert successfully!!!!!!");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update Lab Test Data
	 */
	public void updateLabTestDoctor() {
		System.out.println("Not Complete");
	}

	/**
	 * Search Lab Test Data By Id
	 */
	public void searchLabTestData() {
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user,
					ClinicDatabase.password);
			Statement st = con.createStatement();
			System.out.println("Enter Lab Test Id");
			String id = sc.nextLine();
			String sql = "select * from lab_test where plab_id like '" + id + "';";
			ResultSet rs = st.executeQuery(sql);
			System.out.println("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Lab Test Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			while (rs.next()) {
				String ltid = rs.getString(1);
				String pid = rs.getString(2);
				String testname = rs.getString(3);
				Date date = rs.getDate(4);
				int rate = rs.getInt(2);
				System.out.printf("%5s  %5s  %25s  %12s  %10s\n", ltid, pid, testname, date, rate);
			}
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete Lab Test Data By Id
	 */
	public void deleteLabTestData() {
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user,
					ClinicDatabase.password);
			Statement st = con.createStatement();
			System.out.println("Enter Lab Test Id");
			String did = sc.nextLine();
			int result = st.executeUpdate("delete from lab_test where plab_id ='" + did + "';");
			System.out.println("Delete SuccessFully....");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * List And Show All Lab Test Data
	 */
	public void listAllLabTest() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user,
					ClinicDatabase.password);
			Statement st = con.createStatement();
			String sql = "select * from lab_test";
			ResultSet rs = st.executeQuery(sql);
			System.out.println("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Lab Test Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			while (rs.next()) {
				String ltid = rs.getString(1);
				String pid = rs.getString(2);
				String testname = rs.getString(3);
				Date date = rs.getDate(4);
				int rate = rs.getInt(2);
				System.out.printf("%5s  %5s  %25s  %12s  %10s\n", ltid, pid, testname, date, rate);
			}
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
