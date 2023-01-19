package Uno;

import java.util.Scanner;

import javax.swing.*;

public class Partie {
public static int nbjoueur;
public static Joueur[] joueurs = new Joueur[10];
public boolean fin;
public Joueur gagnant=null;
//public Joueur premier;
//static Esens sensJeu;
//public Joueur tourSuiv;	
//public static CarteUno[] pioche = new CarteUno[108];
//public static ArrayList<CarteUno> talon = new ArrayList<>();
//static ???? le mot static est une obligation pour appeler ces variables (ou méthodes) sur les autres classes du package 
public Partie() {
	Scanner s = new Scanner(System.in);
	System.out.println("n de joueurs");
	nbjoueur = s.nextInt();
	joueurs();
	fin=false;
	int Nmanche=1;
	while((fin==false)) {
		System.out.println(" <-----               MANCHE N°"+Nmanche+"               ----->\n! RAPPEL ! Menu des commandes :\n                              /m : pour voir la sa main.\n                              /p : pour piocher.\n                              /j : pour jouer une carte.\n                              /b : pour lancer un défi sur un +4 (c'est possible ssi le joueur précedent a joué un +4).");
		new Manche();
		Nmanche++;
		for(int i=0;i<nbjoueur;i++)
			if (joueurs[i].getScore()>=120)
				fin=true;
	}
			
			
}


public boolean trouveGagnant() {
	int min =0;//le min
	int occMin=0;//nb d'occurences de min
	
	//trouver le joueur qui possède le score le plus bas
	for(int i=1 ; i<nbjoueur ; i++)
		if (joueurs[i].getScore()<joueurs[min].getScore())
			min=i;
	
	//verifier l'égalité des scores
	for(int i=0 ; i<nbjoueur ; i++)
		if (joueurs[min].getScore()==joueurs[i].getScore()) 
			occMin++;
		
	//en cas d'absence d'égalité
	if(occMin==1) {
		gagnant=joueurs[min];
		return true ;	
	}
	//en cas d'égalité
	else {
		int max =0;
		int occMax=0;
		//trouve le joueur qui a gagné le plus de manches
		for(int i=1 ; i<nbjoueur ; i++)
			if (joueurs[i].getnbMancheGagné()>joueurs[max].getnbMancheGagné())
				max=i;
		
		//verifier l'égalité de nb des manches gagnées
		for(int i=0 ; i<nbjoueur ; i++)
			if (joueurs[max].getnbMancheGagné()==joueurs[i].getnbMancheGagné())
				occMin++;
		
		//en cas d'absence d'égalité
		if(occMin==1) {
			gagnant=joueurs[max];
			return true ;	
		}
		else 
			return false ;
		
		
	}
}

public void joueurs() {
	
	for(int i = 0;i < nbjoueur;i++)
		joueurs[i]= new Joueur();
}







} 
