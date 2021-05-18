package com.userInfo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class SaveUserInfoAppTest {

	@Test
	void testIsUserInfoValid() {
		SaveUserInfoApp testApp = new SaveUserInfoApp();
		Assert.assertFalse(testApp.isUserInfoValid(null, null, null, null, null));
		Assert.assertFalse(testApp.isUserInfoValid("", "", "", "", ""));
		
		String name="Kelly Macshane";
		String email="k-macshane@hushmail.com";
		String addr="432 Ambrose Dr";
		String phone="480-207-9513";
		String budget="3000 to 5000";
		Assert.assertTrue(testApp.isUserInfoValid(name, email, addr, phone, budget));
		
		name="Kelly";
		email="invalid_email";
		addr="432 Ambrose Dr";
		phone="480-207-9513";
		budget="3000 to 5000";
		Assert.assertFalse(testApp.isUserInfoValid(name, email, addr, phone, budget));
	}

}
