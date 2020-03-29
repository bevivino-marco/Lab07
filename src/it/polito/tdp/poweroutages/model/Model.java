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
	
    public List<BlackOut> ricercaBlackOut(Nerc nerc , int anni , int durataMax){
    List<BlackOut> best = new ArrayList <BlackOut>();
    List<BlackOut> parziale = new ArrayList <BlackOut>();
    lista = podao.getBO(nerc.getId());
    
    cerca (parziale, best ,(durataMax*60), 1);
	return best ;	
    }
   private void cerca (List <BlackOut> parziale, List <BlackOut> best, int durataMax, int livello) {
	  /* if (best.size()==0) {
			best.addAll(parziale);
		}
	   
    	if (parziale.size()>0 && durataTot(parziale)> durataMax) {
    		return;
    	}
    	
    	if (best.size()>1 && parziale.size()>1) {
    		if(eMeglio(parziale,best)) {
    				best.clear();
    				best.addAll(parziale);
    				System.out.println(best.toString());
    				return;}
    	}
   if (best.size()>0 && parziale.size()>0) {
	   if (parziale.size()>0 && durataTot(parziale)> durataMax) {
   		return;
   	   }
	   if
   }*/ if (durataTot(parziale)>durataMax) {
	   return;
   }
	   if (livello==2) {
		   best.addAll(parziale);
	   } else {
	   if (livello==lista.size()) {
		   return;
	   }
	   if (eMeglio(parziale, best)) {
		   best.clear();
		   best.addAll(parziale);
		   System.out.println("\n IL BEST AL LIVELLO "+livello+" E :\n"+best.toString());
		   return;
	   }}
    		
    		
    	for(BlackOut bo : lista) {
    		if (controlla(parziale,bo)) {
    		
//	public BlackOut(int id, int anno, int nercId, LocalDateTime i, LocalDateTime f, double durata2, int affette, int tag) {
                //BlackOut b =new BlackOut (bo.getId(),bo.getAnno(),bo.getNercId(),bo.getInizio(),bo.getFine(),bo.getDurata(),bo.getAffette(),bo.getTag());
    			parziale.add(bo);
    			//System.out.println(parziale.toString());
    		    cerca (parziale, best, durataMax, livello+1);
    		    parziale.remove(parziale.size()-1);
    		}
    		
    		
    	}
    }
    private boolean controlla(List <BlackOut> l,BlackOut b) {
    	for (int i=0; i<l.size();i++) {
    		if ((l.get(i).getAnno()-b.getAnno())<-4||(l.get(i).getAnno()-b.getAnno())>4 || l.get(i).getId()==b.getId()){
    		return false;
    	    }
    	}
    	
    	
		return true;
    	
    }
    private boolean eMeglio(List <BlackOut >p,List <BlackOut >b) {
    	int affetteP=0;
    	int affetteB=0;
    	for (int i=0; i< p.size();i++) {
    		affetteP+=p.get(i).getAffette();
    		
    	}
    	for (int i=0; i< b.size();i++) {
    		affetteB+=b.get(i).getAffette();
    		
    	}
    	if (affetteP>affetteB)
    		return true;
		return false;
    	
    }
    private double durataTot (List <BlackOut> l) {
    	double duratatot=0;
    	for (BlackOut b : l) {
    		duratatot+=b.getDurata();
    	}
		return duratatot;
    	
    	
    }
    
    
    
    
    
    
    
    
}
