package com.divergent.clinicmanagmentsystem;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * All Patient CRUD Operation Is done
 * 
 * @author Divergent
 *
 */
public class Patient {

	static Scanner sc = new Scanner(System.in);
	static Connection con;

	private Patient() {

	}

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
		while (true) {
			sc = new Scanner(System.in);
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
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Insert Patient Data into Map
	 * 
	 * @return
	 */
	public static Map<String, String> inputPatientData() {
		System.out.println(
				"Enter Patient_ID,Name,Address,Age,Weight,Gender,Contact_No,ACurrentDate,AppoimentDate,Problem");
		String pid = sc.nextLine();
		String pname = sc.nextLine();
		String paddress = sc.nextLine();
		String page = sc.nextLine();
		String pweight = sc.nextLine();
		String pgender = sc.nextLine();
		String pcontactno = sc.nextLine();
		String pappoimentdate = sc.nextLine();
		String pproblem = sc.nextLine();
		Map<String, String> map = new HashMap<>();
		map.put("1", pid);
		map.put("2", pname);
		map.put("3", paddress);
		map.put("4", page);
		map.put("5", pweight);
		map.put("6", pgender);
		map.put("7", pcontactno);
		map.put("9", pappoimentdate);
		map.put("10", pproblem);

		return map;
	}

	/**
	 * In Get Map Data above Method and Store by Insert Query
	 */
	public static void insertPatientData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME, ClinicDatabase.PASSWORD);
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
			System.out.println("Insert successfully!!!!!!" + i);
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
			Connection con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME,
					ClinicDatabase.PASSWORD);
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
			con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME, ClinicDatabase.PASSWORD);
			Statement st = con.createStatement();
			System.out.println("Enter Patient Id : ");
			String id = sc.nextLine();
			String sql = "select * from patient where p_id like '" + id + "%';";
			ResultSet rs = st.executeQuery(sql);
			if (!rs.next()) {
				System.out.println("No Record is Found!\n");
				patientPanel();
			} else {
				System.out.println(
						"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Patient Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
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
				System.out.println(
						"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			}
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
			con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME, ClinicDatabase.PASSWORD);
			Statement st = con.createStatement();
			System.out.println("Enter Patient Id : ");
			String pid = sc.nextLine();
			int result = st.executeUpdate("delete from patient where p_id='" + pid + "';");
			System.out.println("Delete SuccessFully...." + result);
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
			Connection con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME,
					ClinicDatabase.PASSWORD);
			Statement st = con.createStatement();
			String sql = "select * from patient";
			ResultSet rs = st.executeQuery(sql);

			if (!rs.next()) {
				System.out.println("No Record is Found!\n");
				patientPanel();
			} else {
				System.out.println(
						"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Patient Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
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
				System.out.println(
						"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			}
			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generate Invoice Of Patient
	 */
	public static void generateInvoice() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME,
					ClinicDatabase.PASSWORD);
			Statement st = con.createStatement();
			System.out.println("\nEnter Patient Id");
			String id = sc.nextLine();
			String sql = "select appoinment.P_ID,appoinment.P_Name,appoinment.ACurrent_Date,appoinment.Problem,doctor.D_Name,doctor.fee\r\n"
					+ "from appoinment join doctor on appoinment.d_id = doctor.D_Id where appoinment.p_id ='" + id
					+ "';";
			ResultSet rs = st.executeQuery(sql);

			if (!rs.next()) {
				System.out.println("Record Is not Found!\n");
				Doctor.printDoctorOptions();
			} else {
				System.out.println(
						"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Patient Invoice*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
				String pid = rs.getString(1);
				String pname = rs.getString(2);
				Date pcurrentdate = rs.getDate(3);
				String pproblem = rs.getString(4);
				String dname = rs.getString(5);
				String fee = rs.getString(6);
				System.out.printf("%30s  %30s\n", "Patient Id :" + pid, "Date : " + pcurrentdate);
				System.out.printf("%30s %30s\n", "Patient Name :" + pname, "Problem :" + pproblem);
				System.out.printf("%30s %30s\n\n", "Docter Name :" + dname, "Fee :" + fee);
				System.out.println(
						"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			}
			Doctor.printDoctorOptions();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
