package Uno;

import java.util.ArrayList;
import java.util.Arrays;

public class Paquet {
public ArrayList<CarteUno> cartes = new ArrayList<>();
public Paquet() {
	
carteChiffre();
carteAction();
	
}



private void carteChiffre() {
	Ecouleur[] couleurs = Ecouleur.values();
	Evaleur[] valeurs = Evaleur.values();
		
		for(int j=0;j<10 ;j++) {
			for(int k=0 ; k<4;k++) {
				//4 cartes 0 de chaque couleur : v,b,j,r
				if (valeurs[j]==Evaleur.N0) 
					cartes.add(new CarteUno(couleurs[k],valeurs[j])) ;
				
				//18 cartes entre 1 et 9 en chaque couleur : v,b,j,r
				else {
					cartes.add(new CarteUno(couleurs[k],valeurs[j])) ;
					cartes.add(new CarteUno(couleurs[k],valeurs[j])) ;
					}
		}
	}
}

private void carteAction() {
	Ecouleur[] couleurs = Ecouleur.values();
	Evaleur[] valeurs = Evaleur.values();
		
		for(int j=10;j<16 ;j++) {
			if (j>12) // cas de +4, joker, uno
				for(int i=0;i<4;i++)
					cartes.add(new CarteUno(Ecouleur.NOIR,valeurs[j])) ;
				
				// 18 cartes entre 1 et 9 en chaque couleur : vert,bleu,jaune,rouge
			else 			
				for(int k=0 ; k<4;k++) {
					cartes.add(new CarteUno(couleurs[k],valeurs[j])) ;
					cartes.add(new CarteUno(couleurs[k],valeurs[j])) ;
				}   							
			
		}
}


}