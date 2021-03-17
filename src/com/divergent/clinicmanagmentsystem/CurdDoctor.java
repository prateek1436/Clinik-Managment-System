package com.divergent.clinicmanagmentsystem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurdDoctor {

	static Scanner sc = new Scanner(System.in);

	/**
	 * It show Option on console
	 */
	public static void showCRUDDoctor() {
		System.out.println("1. Insert Doctor Data");
		System.out.println("2. Update Doctor Data");
		System.out.println("3. Search Doctor Data");
		System.out.println("4. Delete Doctor Data");
		System.out.println("5. List All Doctor");
		System.out.println("6. Back");
	}

	/**
	 * It Select Option on Console Panel to choice on It.
	 */
	public static void docterPanel() {
		back: while (true) {
			System.out.println("Enter Your Choice : ");

			showCRUDDoctor();
			String input = sc.nextLine();
			switch (input) {
			case "1":
				insertDoctorData();
				break;
			case "2":
				updateDoctorData();
				break;
			case "3":
				searchDoctorData();
				break;
			case "4":
				deleteDoctorData();
				break;
			case "5":
				listAllDoctor();
				break;
			case "6":
				break back;
			default:
				break;
			}
		}
	}

	/**
	 * Get Data By Admit for insert data
	 * 
	 * @return
	 */
	public static Map<String, String> inputDoctorData() {
		System.out.println("Enter Doctor_Id");
		String d_id = sc.nextLine();
		System.out.println("Enter Doctor Name");
		String d_name = sc.nextLine();
		System.out.println("Enter Speciaslity");
		String d_specia = sc.nextLine();
		System.out.println("Enter ContactNo");
		String d_contact = sc.nextLine();
		System.out.println("Enter Fee");
		String d_fee = sc.nextLine();
		System.out.println("Enter Degree");
		String d_degree = sc.nextLine();

		Map<String, String> map = new HashMap<String, String>();
		map.put("1", d_id);
		map.put("2", d_name);
		map.put("3", d_specia);
		map.put("4", d_contact);
		map.put("5", d_fee);
		map.put("6", d_degree);
		return map;
	}

	/**
	 * Insert Doctor Data
	 */
	public static void insertDoctorData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user,
					ClinicDatabase.password);
			String sql = ("insert into doctor values(?,?,?,?,?,?)");
			PreparedStatement stmt = con.prepareStatement(sql);
			Map<String, String> map = inputDoctorData();
			stmt.setString(1, map.get("1"));
			stmt.setString(2, map.get("2"));
			stmt.setString(3, map.get("3"));
			stmt.setString(4, map.get("4"));
			stmt.setString(5, map.get("5"));
			stmt.setString(6, map.get("6"));
			int i = stmt.executeUpdate();
			System.out.println("Insert successfully!!!!!!");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * in which method get data first then change on it based on old.
	 */
	public static void updateDoctorData() {

		System.out.println("\n----Update Doctor----");
		System.out.print("\nEnter Doctor Id : ");

		Scanner sc = new Scanner(System.in);

		String did = sc.nextLine();

		Map<String, String> map = searchDoctorId(did);

		if (map.size() == 0) {
			System.out.println("\nDoctor not found!\n");
		} else {

			System.out.println("\n----Update Doctor Data----");
			System.out.println("Doctor Id : " + map.get("did"));
			System.out.println("Previous Doctor Name : " + map.get("dname"));
			System.out.println("Previous Doctor Speciality : " + map.get("speciality"));
			System.out.println("Previous Doctor Contact_no : " + map.get("dcontact"));
			System.out.println("Previous Doctor Fee : " + map.get("dfee"));
			System.out.println("Previous Doctor Degree :" + map.get("ddegree"));

			System.out.print("\nEnter New Doctor Name : ");
			map.put("dname", sc.nextLine());
			System.out.print("\nEnter New Doctor Speciality : ");
			map.put("speciality", sc.nextLine());
			System.out.println("\nEnter New Doctor Contact_No :");
			map.put("dcontact", sc.nextLine());
			System.out.println("\nEnter New Doctor Fee : ");
			map.put("dfee", sc.nextLine());
			System.out.println("\nEnter New Doctor Degree : ");
			map.put("ddegree", sc.nextLine());

			updateDoctor(map);
		}
	}

	/**
	 * In this method Map Paratmeter used and Update doctor
	 * 
	 * @param map
	 */
	public static void updateDoctor(Map<String, String> map) {

		Connection con = null;
		Statement st = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user, ClinicDatabase.password);
			st = con.createStatement();
			st.executeUpdate("update doctor set dname = '" + map.get("dname") + "', Speciality = '"
					+ map.get("speciality") + "',contact_no = '" + map.get("dcontact") + "',fee = '" + map.get("dfee")
					+ "',degree ='" + map.get("ddegree") + "' where d_id = " + map.get("did"));
			System.out.println("Data Updated Successfully...");
			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Serach Docter Details By Id for Update The Data
	 * 
	 * @param did
	 * @return
	 */
	public static Map searchDoctorId(String did) {

		Connection con = null;
		Statement st = null;
		Map<String, String> map = new HashMap<>();
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user, ClinicDatabase.password);
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from doctor where d_id = '" + did + "'");

			if (rs.next()) {
				map.put("did", rs.getString(1));
				map.put("dname", rs.getString(2));
				map.put("speciality", rs.getString(3));
				map.put("dcontact", rs.getString(4));
				map.put("dfee", rs.getString(5));
				map.put("ddegree", rs.getString(6));
				return map;
			} else {
				return map;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;

	}

	/**
	 * Search Doctor Data admin
	 */
	public static void searchDoctorData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user,
					ClinicDatabase.password);
			Statement st = con.createStatement();
			System.out.println("Enter Doctor Id : ");
			String id = sc.nextLine();
			String sql = "select * from doctor where d_id like '" + id + "%';";
			ResultSet rs = st.executeQuery(sql);
			if (!rs.next()) {
				System.out.println("No record Found!\n");
				docterPanel();
			} else {
				System.out.println(
						"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Docter Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
				String did = rs.getString(1);
				String dname = rs.getString(2);
				String specia = rs.getString(3);
				String dcontact = rs.getString(4);
				String dfee = rs.getString(5);
				String ddegree = rs.getString(6);
				System.out.printf("%5s  %20s  %25s  %18s  %15s  %15s\n", did, dname, specia, dcontact, dfee, ddegree);
				System.out.println(
						"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
				docterPanel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete Doctor Data by Id
	 */
	public static void deleteDoctorData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user,
					ClinicDatabase.password);
			Statement st = con.createStatement();
			System.out.println("Enter Doctor Id");
			String did = sc.nextLine();
			int result = st.executeUpdate("delete from doctor where d_id='" + did + "';");
			System.out.println("Delete SuccessFully....");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Show All List Of Doctor
	 */
	public static void listAllDoctor() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user,
					ClinicDatabase.password);
			Statement st = con.createStatement();
			String sql = "select * from doctor";
			ResultSet rs = st.executeQuery(sql);
			if (!rs.next()) {
				System.out.println("No Record Found!\n");
				docterPanel();
			} else {
				System.out.println(
						"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Docter Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
				String did = rs.getString(1);
				String dname = rs.getString(2);
				String specia = rs.getString(3);
				String dcontact = rs.getString(4);
				String dfee = rs.getString(5);
				String ddegree = rs.getString(6);
				System.out.printf("%5s  %20s  %25s  %18s  %15s  %15s\n", did, dname, specia, dcontact, dfee, ddegree);
			}
			System.out.println(
					"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
