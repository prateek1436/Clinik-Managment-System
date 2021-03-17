package com.divergent.clinicmanagmentsystem;

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
	 */
	public static void main(String[] args) {
		Admin admin = new Admin();
		Doctor doctor = new Doctor();
		Scanner sc = new Scanner(System.in);

		Main: while (true) {

			System.out.println("\n----Login Panel----");
			System.out.println("1. Admin");
			System.out.println("2. Doctor");
			System.out.println("3. Exit");

			String input = sc.nextLine();

			Login: switch (input) {

			case "1":
				if (admin.adminLogin()) {
					while (true) {
						admin.printAdminOptions();
						if (sc.nextLine().equals("6")) {
							break Login;
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
						doctor.printDoctorOptions();
						if (sc.nextLine().equals("5")) {
							break Login;
						} else {
							System.out.println("Select Right Option");
						}
					}

				} else {
					System.out.println("You are not Authorised");
				}
				break;

			case "3":
				break Main;

			default:
				System.out.println("Invalid Input");
				break;
			}

		}
	}
}
