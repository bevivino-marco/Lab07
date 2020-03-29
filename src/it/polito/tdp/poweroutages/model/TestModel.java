package it.polito.tdp.poweroutages.model;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class TestModel {

	public static void main(String[] args) {
		PowerOutageDAO dao = new PowerOutageDAO();
		Model model = new Model();
		System.out.println(model.getNercList());
		System.out.println(dao.getBO(1).toString().replace("[", "").replace("]", "").replace(",", ""));
	}
}
