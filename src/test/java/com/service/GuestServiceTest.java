package com.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.entity.Guest;
import com.service.impl.GuestServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class GuestServiceTest {


	@InjectMocks
	GuestService guestService;

	@Mock
	Guest guest;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void shouldAddGuest() {
		// given

		// when
		doNothing().when(guestService).addGuest(guest);
		guestService.addGuest(guest);

		// then
		assertTrue(true);
	}
}

/*
 * public void addGuest(Guest guest) { guestDao.add(guest); }
 */