package com.window;

import static org.junit.Assert.*;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.junit.Test;

import com.window.Guest.GuestAdd;

public class JUnitTest {

	@Test
	
	public void shouldReturnFalseWhenConveyingCorrectData() {
		// given
		JTextField firstName = new JTextField("Adam");
		JTextField lastName = new JTextField("Kowalski");
		JTextField dateOfBirth = new JTextField("1.1.1");
		JTextField pesel = new JTextField("12122ee312");

		// when
		boolean result = GuestAdd.CheckEmptyCells(firstName, lastName, dateOfBirth, pesel);

		// then
		assertFalse(result);
	}
}
