package it.polito.tdp.poweroutages.model;

import java.util.List;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class TestModel {

	public static void main(String[] args) {
		PowerOutageDAO dao = new PowerOutageDAO();
		Model model = new Model();
		//System.out.println(model.getNercList());
		//System.out.println(dao.getBO(1).toString().replace("[", "").replace("]", "").replace(",", ""));
		Nerc n = model.getNercList().get(3);
		//System.out.println(n.getValue());
		List<BlackOut> lista = model.ricercaBlackOut(n, 4, 200);
		double affetti =model.affetti(lista);
		double ore = model.oreTot(lista);
	 System.out.println("Le persone affette sono state : "+affetti+";\n"+""
	 		+ "le ore di disservizione sono state : "+ore+";\n"+lista.toString().replace("[", "").replace("]", "").replace(",", ""));
	 
	}
}
