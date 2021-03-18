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
 * Prescription And Notes
 * 
 * @author Divergent
 *
 */
public class PrescriptionAndNotes {

	/**
	 * Make Custructor
	 */
	private PrescriptionAndNotes() {
		super();
	}

	/**
	 * Get Information
	 * 
	 * @return
	 */
	private static Map<String, String> inputPrescriptionData() {
		Scanner sc = new Scanner(System.in);
		System.out.println(Messages.getString("PrescriptionAndNotes.0"));
		String prescriId = sc.nextLine();
		System.out.println(Messages.getString("PrescriptionAndNotes.1"));
		String patientId = sc.nextLine();
		System.out.println(Messages.getString("PrescriptionAndNotes.2"));
		String prescription = sc.nextLine();
		System.out.println(Messages.getString("PrescriptionAndNotes.3"));
		String note = sc.nextLine();
		System.out.println(Messages.getString("PrescriptionAndNotes.4"));
		String doctorid = sc.nextLine();

		Map<String, String> map = new HashMap<>();
		map.put(Messages.getString("PrescriptionAndNotes.5"), prescriId);
		map.put(Messages.getString("PrescriptionAndNotes.6"), patientId);
		map.put(Messages.getString("PrescriptionAndNotes.7"), prescription);
		map.put(Messages.getString("PrescriptionAndNotes.8"), note);
		map.put(Messages.getString("PrescriptionAndNotes.9"), doctorid);
		sc.close();
		return map;
	}

	/**
	 * Patient Prescription
	 */
	static void prescriptionPatient() {
		try {
			Class.forName(Messages.getString("PrescriptionAndNotes.10"));
			Connection con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME,
					ClinicDatabase.PASSWORD);
			String sql = (Messages.getString("PrescriptionAndNotes.11"));
			PreparedStatement stmt = con.prepareStatement(sql);
			Map<String, String> map = inputPrescriptionData();
			stmt.setString(1, map.get(Messages.getString("PrescriptionAndNotes.12")));
			stmt.setString(2, map.get(Messages.getString("PrescriptionAndNotes.13")));
			stmt.setString(3, map.get(Messages.getString("PrescriptionAndNotes.14")));
			stmt.setString(4, map.get(Messages.getString("PrescriptionAndNotes.15")));
			stmt.setString(5, map.get(Messages.getString("PrescriptionAndNotes.16")));
			int i = stmt.executeUpdate();
			System.out.println(Messages.getString("PrescriptionAndNotes.17"));
			System.out.println(i);
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * History and Prescription of all Patient
	 */
	static void historyAndPresciption() {
		try {
			Class.forName(Messages.getString("PrescriptionAndNotes.18"));
			Connection con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME,
					ClinicDatabase.PASSWORD);
			Statement st = con.createStatement();
			String sql = Messages.getString("PrescriptionAndNotes.19") + Messages.getString("PrescriptionAndNotes.20")
					+ Messages.getString("PrescriptionAndNotes.21") + Messages.getString("PrescriptionAndNotes.22")
					+ Messages.getString("PrescriptionAndNotes.23") + Messages.getString("PrescriptionAndNotes.24");
			ResultSet rs = st.executeQuery(sql);
			if (!rs.next()) {
				System.out.println(Messages.getString("PrescriptionAndNotes.25"));
				Doctor.printDoctorOptions();
			} else {
				System.out.println(Messages.getString("PrescriptionAndNotes.26"));
				System.out.println(Messages.getString("PrescriptionAndNotes.27"));
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
				System.out.printf(Messages.getString("PrescriptionAndNotes.28"), pid, pname, pgender, page, doctorId,
						doctorname, appoinId, pproblem, appoindate, prescri, note);
				System.out.println(Messages.getString("PrescriptionAndNotes.29"));
			}
			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
