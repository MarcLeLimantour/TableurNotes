package model;

import java.util.ArrayList;

public class Matiere {

	private int id;
	private String nom;
	private ArrayList<Examen> examens;
	private float moyenne;
	public static int nbMatieres = 0;
	
	/**
	 * constructeur de matiere
	 * 
	 * @param 	id : identifiant de la mati�re
	 * 			nom : nom de la base de donn�es
	 * 
	 **/
	
	public Matiere(int id, String nom){
		nbMatieres++;
		this.id = id;
		this.nom = nom;
		this.examens = new ArrayList<Examen>();
	}
	
	public int getId() {
		return this.id;
	}
	
		
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * methode pour obtenir un tableau d'examen
	 *
	 * 
	 **/
	public ArrayList<Examen> getExamens() {
		return examens;
	}

	public void setExamens(ArrayList<Examen> examens) {
		this.examens = examens;
	}
	
	/**
	 * methode pour r�cup�rer la moyenne de la matiere
	 * 
	 * la moyenne est obtenue � partir d'examen
	 * 
	 **/
	public float getMoyenne() {
		this.moyenne = 0;
		int j = 0;
		for(int i=0; i<this.examens.size(); i++){
				this.moyenne += this.examens.get(i).getMoyenne();
				j++;
		}
		this.moyenne = this.moyenne / j;
		this.moyenne = Math.round(this.moyenne * 100);
		return this.moyenne / 100;
	}

	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}

	public void ajouterExamen(Examen examen){
		this.examens.add(examen);
	}
	
	
}
