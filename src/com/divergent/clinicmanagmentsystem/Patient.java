package com.divergent.clinicmanagmentsystem;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * All Patient CRUD Operation Is done
 * @author Divergent
 *
 */
public class Patient {

	static Scanner sc = new Scanner(System.in);
	static Connection con;

	/**
	 * Show All OPtion Of Admin to CRUD Operation
	 */
	public static void showAll() {
		System.out.println("1. Insert Patient Data");
		System.out.println("2. Update Patient Data");
		System.out.println("3. Search Patient Data");
		System.out.println("4. Delete Patient Data");
		System.out.println("5. List All Patient");
		System.out.println("6. Back");
	}

	/**
	 * Select Option by this method
	 */
	public static void patientPanel() {
		back: while (true) {
			Scanner sc = new Scanner(System.in);
			showAll();
			String input = sc.nextLine();
			switch (input) {
			case "1":
				insertPatientData();
				break;
			case "2":
				updatePatientData();
				break;
			case "3":
				searchPatientData();
				break;
			case "4":
				deletePatientData();
				break;
			case "5":
				listAllPatientData();
				break;
			case "6":
				break back;
			default:
				break;
			}
		}
	}

	/**
	 * Insert Patient Data into Map
	 * @return
	 */
	public static Map<String, String> inputPatientData() {
		System.out.println(
				"Enter Patient_ID,Name,Address,Age,Weight,Gender,Contact_No,ACurrentDate,AppoimentDate,Problem");
		String p_id = sc.nextLine();
		String p_name = sc.nextLine();
		String p_address = sc.nextLine();
		String p_age = sc.nextLine();
		String p_weight = sc.nextLine();
		String p_gender = sc.nextLine();
		String p_contactno = sc.nextLine();
		String p_appoimentdate = sc.nextLine();
		String p_problem = sc.nextLine();
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", p_id);
		map.put("2", p_name);
		map.put("3", p_address);
		map.put("4", p_age);
		map.put("5", p_weight);
		map.put("6", p_gender);
		map.put("7", p_contactno);
		map.put("9", p_appoimentdate);
		map.put("10", p_problem);

		return map;
	}

	/**
	 * In Get Map Data above Method and Store by Insert Query
	 */
	public static void insertPatientData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user, ClinicDatabase.password);
			String sql = ("insert into patient values(?,?,?,?,?,?,?,?,?,?)");
			PreparedStatement stmt = con.prepareStatement(sql);
			long millis = System.currentTimeMillis();
			Date date = new Date(millis);
			Map<String, String> map = inputPatientData();
			stmt.setString(1, map.get("1"));
			stmt.setString(2, map.get("2"));
			stmt.setString(3, map.get("3"));
			stmt.setString(4, map.get("4"));
			stmt.setString(5, map.get("5"));
			stmt.setString(6, map.get("6"));
			stmt.setString(7, map.get("7"));
			stmt.setDate(8, date);
			stmt.setString(9, map.get("9"));
			stmt.setString(10, map.get("10"));
			int i = stmt.executeUpdate();
			System.out.println("Insert successfully!!!!!!");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Patient Data Update
	 */
	private static void updatePatientData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user,
					ClinicDatabase.password);
			Statement st = con.createStatement();
			int pid = Integer.parseInt(sc.nextLine());
			String pproblem = sc.nextLine();
			String sql = "UPDATE patient SET pproblem = " + pproblem + " where pid= " + pid + ";";
			st.executeUpdate(sql);
			System.out.println("Update SuccessFully....");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Search Patient Data By Id
	 */
	private static void searchPatientData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user, ClinicDatabase.password);
			Statement st = con.createStatement();
			System.out.println("Enter Patient Id : ");
			String id = sc.nextLine();
			String sql = "select * from patient where p_id like '" + id + "%';";
			ResultSet rs = st.executeQuery(sql);
			System.out.println(
					"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Patient Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			while (rs.next()) {
				String pid = rs.getString(1);
				String pname = rs.getString(2);
				String paddress = rs.getString(3);
				String page = rs.getString(4);
				String pweight = rs.getString(5);
				String pgender = rs.getString(6);
				String pcontactno = rs.getString(7);
				Date pcurrentdate = rs.getDate(8);
				String pappoinment = rs.getString(9);
				String pproblem = rs.getString(10);
				System.out.printf("%5s  %15s  %15s  %3s  %4s  %6s  %12s  %12s  %12s  %20s\n", pid, pname, paddress,
						page, pweight, pgender, pcontactno, pcurrentdate, pappoinment, pproblem);
			}
			System.out.println(
					"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			System.out.println("Search SuccessFully....");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete Patient Data
	 */
	private static void deletePatientData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user, ClinicDatabase.password);
			Statement st = con.createStatement();
			System.out.println("Enter Patient Id : ");
			String pid = sc.nextLine();
			int result = st.executeUpdate("delete from patient where p_id='" + pid + "';");
			System.out.println("Delete SuccessFully....");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Show All Patient Data
	 */
	public static void listAllPatientData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user,
					ClinicDatabase.password);
			Statement st = con.createStatement();
			String sql = "select * from patient";
			ResultSet rs = st.executeQuery(sql);
			System.out.println(
					"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Patient Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			while (rs.next()) {
				String pid = rs.getString(1);
				String pname = rs.getString(2);
				String paddress = rs.getString(3);
				String page = rs.getString(4);
				String pweight = rs.getString(5);
				String pgender = rs.getString(6);
				String pcontactno = rs.getString(7);
				Date pcurrentdate = rs.getDate(8);
				String pappoinment = rs.getString(9);
				String pproblem = rs.getString(10);
				System.out.printf("%5s  %15s  %15s  %3s  %4s  %6s  %12s  %12s  %12s  %20s\n", pid, pname, paddress,
						page, pweight, pgender, pcontactno, pcurrentdate, pappoinment, pproblem);
			}
			System.out.println(
					"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
