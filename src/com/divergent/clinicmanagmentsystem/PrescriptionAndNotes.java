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

/**
 * Prescription And Notes
 * 
 * @author Divergent
 *
 */
public class PrescriptionAndNotes {

	static Doctor doctor = new Doctor();
	
	/**
	 * Get Information
	 * 
	 * @return
	 */
	public static Map<String, String> inputPrescriptionData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Prescription Id");
		String prescriId = sc.nextLine();
		System.out.println("Enter Patient Id");
		String patientId = sc.nextLine();
		System.out.println("Enter Prescription");
		String prescription = sc.nextLine();
		System.out.println("Enter Notes");
		String note = sc.nextLine();
		System.out.println("Enter doctor id");
		String doctorid = sc.nextLine();

		Map<String, String> map = new HashMap<String, String>();
		map.put("1", prescriId);
		map.put("2", patientId);
		map.put("3", prescription);
		map.put("4", note);
		map.put("5", doctorid);
		return map;
	}

	/**
	 * Patient Prescription
	 */
	public void prescriptionPatient() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user,
					ClinicDatabase.password);
			String sql = ("insert into prescription values(?,?,?,?,?)");
			PreparedStatement stmt = con.prepareStatement(sql);
			Map<String, String> map = inputPrescriptionData();
			stmt.setString(1, map.get("1"));
			stmt.setString(2, map.get("2"));
			stmt.setString(3, map.get("3"));
			stmt.setString(4, map.get("4"));
			stmt.setString(5, map.get("5"));
			int i = stmt.executeUpdate();
			System.out.println("Insert successfully!!!!!!");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * History and Prescription of all Patient
	 */
	public void historyAndPresciption() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user,
					ClinicDatabase.password);
			Statement st = con.createStatement();
			String sql = "select patient.p_id,patient.p_name,patient.gender,patient.age,appoinment.d_id,appoinment.d_name,\r\n"
					+ "appoinment.problem,appoinment.appoinment_id,appoinment.appoinment_date,\r\n"
					+ "prescription.dprescription,prescription.dnotes\r\n"
					+ "from patient inner join appoinment on patient.p_id =  appoinment.p_id   \r\n"
					+ "inner join prescription on patient.p_id = prescription.p_id\r\n"
					+ "order by appoinment.appoinment_date desc;";
			ResultSet rs = st.executeQuery(sql);
			if (!rs.next()) {
				System.out.println("No record Found!\n");
				doctor.printDoctorOptions();
			} else {
				System.out.println("\n*-*-*-*-*-*-*-*-*-* History Of Patient *-*-*-*-*-*-*-*-*-*-*-*-*");
				System.out.println(
						"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Patient Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
				String pid = rs.getString(1);
				String pname = rs.getString(2);
				String pgender = rs.getString(3);
				String page = rs.getString(4);
				String doctorId = rs.getString(5);
				String doctorname = rs.getString(6);
				String appoinId = rs.getString(7);
				String pproblem = rs.getString(8);
				String appoindate = rs.getString(9);
				String prescri = rs.getString(10);
				String note = rs.getString(11);
				System.out.printf("%5s  %20s  %3s  %7s  %5s  %20s  %5s  %10s  %15s  %30s  %30s\n", pid, pname, pgender,
						page, doctorId, doctorname, appoinId, pproblem, appoindate, prescri, note);
				System.out.println(
						"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			}
			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
