package com.divergent.clinicmanagmentsystem;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Admin Class For Login and Update into all infromation
 * @author Divergent
 *
 */
public class Admin {
	Patient patient = new Patient();
	Drug drug = new Drug();
	LabTest labtest = new LabTest();
	CurdDoctor doctor = new CurdDoctor();
	Appoinment appoin = new Appoinment();
	Scanner sc = new Scanner(System.in);

	/**
	 * Admin Login
	 * @return
	 */
	public boolean adminLogin() {

		Console cons = System.console();
		System.out.println("\n-----Admin Login------");
		System.out.print("\nEnter Username: ");
		String username = sc.nextLine();
		System.out.print("\nEnter Password: ");
		String password = sc.nextLine();

		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(ClinicDatabase.url, ClinicDatabase.user, ClinicDatabase.password);
			Statement st = con.createStatement();
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
			try {
				con.close();
			} catch (Exception e) {
				System.out.println("Connection problem");
			}
		}
		return false;
	}

	public void printAdminOptions() {
		back: while (true) {
			System.out.println("\n----Admin Panel-----");
			System.out.println("1.	Patient");
			System.out.println("2. 	Doctor");
			System.out.println("3. 	Drug");
			System.out.println("4.	Lab Test");
			System.out.println("5. 	Book appointment for a patient by selecting Patient, Doctor and Data/time");
			System.out.println("6. 	Logout");
			System.out.print("Enter Your Choice: ");

			int input = sc.nextInt();

			switch (input) {
			case 1:
				patient.patientPanel();
				break;
			case 2:
				doctor.docterPanel();
				break;
			case 3:
				drug.drugPanel();
				break;
			case 4:
				labtest.labTestPanel();
				break;
			case 5:
				appoin.appoinmentPanel();
				break;
			case 6:
				break back;
			default:
				break;
			}
		}
	}

}
