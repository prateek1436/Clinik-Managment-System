package com.divergent.clinicmanagmentsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Doctor Class In Which All CRUD Operaton Done
 * 
 * @author Divergent
 *
 */
public class Doctor {

	/**
	 * Get Doctor Data
	 */
	public static void printDoctorOptions() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("\nDoctor Panel");
			System.out.println("1. List of patient");
			System.out.println("2. Add prescription and notes for a patient");
			System.out.println("3. See booked appointments for him");
			System.out.println("4. Check patient history and his prescription");
			System.out.println("5. Generate Invoice");
			System.out.println("5. Logout");
			System.out.print("Enter Your Choice: ");

			int input = sc.nextInt();

			switch (input) {
			case 1:
				Patient.listAllPatientData();
				break;
			case 2:
				PrescriptionAndNotes.prescriptionPatient();
				break;
			case 3:
				Appoinment.showAllAppoinment();
				break;
			case 4:
				PrescriptionAndNotes.historyAndPresciption();
				break;
			case 5:
				Patient.generateInvoice();
				break;
			case 6:
				System.out.println("Logout Successfully");
				try {
					ClinicManagmentSystem.startAgain();
				} catch (SQLException e) {

					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Choice Right Option");
				break;
			}
			sc.close();
		}
	}

	/**
	 * Doctor Login Method
	 * 
	 * @return
	 */
	public boolean doctorLogin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n----Doctor Login----");
		System.out.print("Enter Username: ");
		String username = sc.nextLine();
		System.out.print("\nEnter Password: ");
		String password = sc.nextLine();

		Connection con = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME, ClinicDatabase.PASSWORD);
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from administration where a_username = '" + username
					+ "' && a_password = '" + password + "'");

			if (rs.next()) {
				System.out.println("\nDoctor Login Successful");
				return true;
			} else {
				System.out.println("\nIncorrect Username & Password");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		sc.close();
		return false;
	}

}
