package com.boot;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for AppStartTest
 */
public class AppStartTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of AppStartTest test case
	 */
	public AppStartTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested for AppStartTest
	 */
	public static Test suite() {
		return new TestSuite(AppStartTest.class);
	}

	/**
	 * TODO Test case
	 */
	public void testApp() {
		assertTrue(true);
	}
}
