package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	private PowerOutageDAO podao;
	private List<BlackOut> lista;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	public List<BlackOut> getBO(int nercId){
		return podao.getBO(nercId);
	}
	
    public List<BlackOut> ricercaBlackOut(Nerc nerc , int anni , int ore){
    List<BlackOut> best = new LinkedList <BlackOut>();
    List<BlackOut> parziale = new LinkedList <BlackOut>();
    lista = podao.getBO(nerc.getId());
    cerca (parziale, best , 0);
	return best ;	
    }
   private void cerca (List <BlackOut> parziale, List <BlackOut> best, int livello) {
    	if () {
    		
    	}
    	for(BlackOut bo : lista) {
    		if (controlla ==true) {
//	public BlackOut(int id, int anno, int nercId, LocalDateTime i, LocalDateTime f, double durata2, int affette, int tag) {
                BlackOut b =new BlackOut (bo.getId(),bo.getAnno(),bo.getNercId(),bo.getInizio(),bo.getFine(),bo.getDurata(),bo.getAffette(),bo.getTag());
    			parziale.add(b);
    		    cerca (parziale, best, 0);
    		    parziale.remove(b);
    		}
    	}
    }
    private boolean controlla() {
		return false;
    	
    }
    private boolean eMeglio() {
		return false;
    	
    }
}
