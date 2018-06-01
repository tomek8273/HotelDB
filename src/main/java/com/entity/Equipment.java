package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@SuppressWarnings("unused")
	private int number_of_tables;
	private int number_of_beds;
	private int number_of_tv;
	private int number_of_phones;

}
