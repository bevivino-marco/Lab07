package it.polito.tdp.poweroutages.model;

import java.sql.Date;
import java.time.*;

public class BlackOut implements Comparable <BlackOut>{
	private int id;
	private int anno;
	private int nercId;
	private LocalDateTime inizio;
	private LocalDateTime fine;
	private double durata;
	private int affette;
	private int tag;
	public BlackOut(int id, int anno, int nercId, LocalDateTime i, LocalDateTime f, double durata2, int affette, int tag) {
		super();
		this.id = id;
		this.anno = anno;
		this.nercId = nercId;
		this.inizio = i;
		this.fine = f;
		this.durata = durata2;
		this.affette= affette;
		this.tag=tag;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public int getNercId() {
		return nercId;
	}
	public void setNercId(int nercId) {
		this.nercId = nercId;
	}

	public double getDurata() {
		return durata;
	}
	public void setDurata(double durata) {
		this.durata = durata;
	}
	public int getAffette() {
		return affette;
	}
	public void setAffette(int affette) {
		this.affette = affette;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public void setInizio(LocalDateTime inizio) {
		this.inizio = inizio;
	}
	public void setFine(LocalDateTime fine) {
		this.fine = fine;
	}
	public LocalDateTime getInizio() {
		return inizio;
	}
	public LocalDateTime getFine() {
		return fine;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlackOut other = (BlackOut) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("%-4s %-10s %-10s %3s %-6s durata = %s \n", anno, inizio, fine, 
				tag,affette, durata);
	}

	@Override
	public int compareTo(BlackOut o) {
		
		return 0;
	}
	
	

}
