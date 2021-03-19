package com.divergent.clinicmanagmentsystem;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Main Class In Which We can Do a admin and Doctor Login
 * 
 * @author Divergent
 *
 */
public class ClinicManagmentSystem {

	/**
	 * Main Method
	 * 
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		startAgain();
	}

	/**
	 * Admin Start Method
	 * 
	 * @throws SQLException
	 */
	public static void startAgain() throws SQLException {
		Admin admin = new Admin();
		Doctor doctor = new Doctor();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("\n----Login Panel----");
			System.out.println("1. Admin");
			System.out.println("2. Doctor");
			System.out.println("3. Exit");
			String input = sc.nextLine();
			switch (input) {
			case "1":
				if (admin.adminLogin()) {
					while (true) {
						admin.printAdminOptions();
						if (sc.nextLine().equals("6")) {
							startAgain();
							break;
						} else {
							System.out.println("Select Right Option");
						}
					}
				} else {
					System.out.println("You are not Authorised");
				}
				break;

			case "2":
				if (doctor.doctorLogin()) {
					while (true) {
						Doctor.printDoctorOptions();
						if (sc.nextLine().equals("5")) {
							startAgain();
							break;
						} else {
							System.out.println("Select Right Option");
						}
					}

				} else {
					System.out.println("You are not Authorised");
				}
				break;

			case "3":
				System.exit(0);
				break;

			default:
				System.out.println("Invalid Input");
				break;
			}
			sc.close();
		}
	}
}
