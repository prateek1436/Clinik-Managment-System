package com.divergent.clinicmanagmentsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Admin Class For Login and Update into all infromation
 * 
 * @author Divergent
 *
 */
public class Admin {
	Scanner sc = new Scanner(System.in);

	/**
	 * Admin Login
	 * 
	 * @return
	 * @throws SQLException
	 */
	public boolean adminLogin() throws SQLException {
		System.out.println("\n-----Admin Login------");
		System.out.print("\nEnter Username: ");
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
				System.out.println("Admin Login Successful");
				return true;
			} else {
				System.out.println("Incorrect Username & Password");
			}

		} catch (Exception e) {
			System.out.println("error with database");
		} finally {
			st.close();
			con.close();
			System.out.println("Connection problem");
		}
		return false;
	}

	public void printAdminOptions() {
		while (true) {
			executedMethod();
		}
	}

	static void executedMethod() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n----Admin Panel-----");
		System.out.println("1.  Patient");
		System.out.println("2. 	Doctor");
		System.out.println("3. 	Drug");
		System.out.println("4.	Lab Test");
		System.out.println("5. 	Book appointment for a patient by selecting Patient, Doctor and Data/time");
		System.out.println("6. 	Logout");
		System.out.print("Enter Your Choice: ");

		int input = sc.nextInt();

		switch (input) {
		case 1:
			Patient.patientPanel();
			break;
		case 2:
			CurdDoctor.docterPanel();
			break;
		case 3:
			Drug.drugPanel();
			break;
		case 4:
			LabTest.labTestPanel();
			break;
		case 5:
			Appoinment.appoinmentPanel();
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
			System.out.println("Back");
			break;
		}
	}

}
