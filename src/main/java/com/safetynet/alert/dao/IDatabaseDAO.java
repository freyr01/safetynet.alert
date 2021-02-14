package com.safetynet.alert.dao;

public interface IDatabaseDAO {
	/**
	 * Should return a connection to the database
	 * @return JsonDatabase
	 * @author Mathias Lauer
	 * 14 févr. 2021
	 */
	public Object getConnection();
}
