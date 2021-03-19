package com.divergent.clinicmanagmentsystem.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.divergent.clinicmanagmentsystem.ClinicDatabase;

public class listofdrug {
	public static Map<String, String> inputLabTestData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter LabTest_Id");
		String lbid = sc.nextLine();
		System.out.println("Enter Patient Id");
		String pid = sc.nextLine();
		System.out.println("Enter Test");
		String testname = sc.nextLine();
		System.out.println("Enter Rate of Test");
		int rate = sc.nextInt();
		String ratestring = Integer.toString(rate);

		Map<String, String> map = new HashMap<>();
		map.put("1", lbid);
		map.put("2", pid);
		map.put("3", testname);
		map.put("4", ratestring);
		sc.close();
		return map;
	}

	/**
	 * Insert Lab Test Data
	 */
	public static void insertLabTestData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME,
					ClinicDatabase.PASSWORD);
			String sql = ("insert into lab_test values(?,?,?,?,?)");
			PreparedStatement stmt = con.prepareStatement(sql);
			Map<String, String> map = inputLabTestData();
			stmt.setString(1, map.get("1"));
			stmt.setString(2, map.get("2"));
			stmt.setString(3, map.get("3"));
			long millis = System.currentTimeMillis();
			Date date = new Date(millis);
			stmt.setDate(4, date);
			stmt.setString(5, map.get("4"));
			int i = stmt.executeUpdate();
			System.out.println("Insert successfully!!!!!!"+i);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
