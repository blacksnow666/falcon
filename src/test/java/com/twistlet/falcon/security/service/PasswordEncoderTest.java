package com.twistlet.falcon.security.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class PasswordEncoderTest {

	@Test
	public void testSha256Inban() {
		final String username = "inban";
		final String password = "butterbun";
		final PasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
		final String actual = passwordEncoder
				.encodePassword(password, username);
		final String expected = "5727c6363d91fcc80a98f22a748c95fba96cc9e39110a23b9a38e6ea510476cb";
		assertEquals(expected, actual);
	}

	@Test
	public void testSha256titiwangsa() {
		final String username = "titiwangsa";
		final String password = "butterbun";
		final PasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
		final String actual = passwordEncoder
				.encodePassword(password, username);
		final String expected = "bb1338008e7180b5b8246884e8c37eec9ac8869fccd3340a31e9cf67b375fd6e";
		assertEquals(expected, actual);
	}

	@Test
	public void testSha256butterbun() {
		final String username = "butterbun";
		final String password = "inban";
		final PasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
		final String actual = passwordEncoder
				.encodePassword(password, username);
		final String expected = "c42dad8cd3f474de4a21f50c80c04326938037cd74f0adfcfa51c39f302ba276";
		assertEquals(expected, actual);
	}
}