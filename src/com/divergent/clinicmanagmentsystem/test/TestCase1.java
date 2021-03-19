package com.divergent.clinicmanagmentsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Map;

import org.junit.Test;

public class TestCase1 {
	private static Map<String, String> map;
	@Test
	public static void m9() {
		map.put("1", "High");
		assertFalse(map.isEmpty());
		assertEquals(1, map.size());
	}
}
