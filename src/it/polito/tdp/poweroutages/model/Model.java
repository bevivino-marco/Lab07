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
    try{lista = podao.getBO(nerc.getId());}
    catch (NullPointerException e) {
    	return null;
    }
    cerca (parziale, best ,(durataMax*60), 1);
	return best ;	
    }
   private void cerca (List <BlackOut> parziale, List <BlackOut> best, int durataMax, int livello) {
	   //System.out.println(parziale);
	if (durataTot(parziale)>durataMax) {
	   return;
   }
	   if (livello==2 && best.size()==0) {
		   best.addAll(parziale);
	   } else {
	   if (livello==lista.size()&& lista.size()!=1) {
		   return;
	   }
	   if (eMeglio(parziale, best)) {
		   best.clear();
		   best.addAll(parziale);
		   //System.out.println("\n IL BEST AL LIVELLO "+livello+" E :\n"+best.toString());
		   return;
	   }}
    		
    		
    	for(BlackOut bo : lista) {
    		if ( controlla(parziale,bo, livello) && !parziale.contains(bo)) {
       			parziale.add(bo);
    			//System.out.println(parziale.toString());
    		    cerca (parziale, best, durataMax, livello+1);
    		    parziale.remove(parziale.size()-1);
    		}
    		
    		
    	}
    }
    private boolean controlla(List <BlackOut> l,BlackOut b, int livello) {
    	if (livello ==1) {
    		return true;
    	}
    	int annol =0;
    	int annob=b.getAnno();
    	for (int i=0; i<l.size();i++) {
    		annol=l.get(i).getAnno();
    		long differenza =annol-annob;
    		if (differenza>4 || differenza<-4) {
    		//if ((l.get(i).getAnno()-b.getAnno())<-4||(l.get(i).getAnno()-b.getAnno())>4 || l.get(i).getId()==b.getId()){
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
    public int affetti(List<BlackOut> b) {
    	int affetteB=0;
    	
    	for (int i=0; i< b.size();i++) {
    		affetteB+=b.get(i).getAffette();
    		
    	}
    	return affetteB;
    	
    }
    public int oreTot (List<BlackOut> b) {
int affetteB=0;
    	
    	for (int i=0; i< b.size();i++) {
    		affetteB+=(b.get(i).getDurata())/60;
    		
    	}
    	return affetteB;
    }
    
    
    
    
    
    
    
}
