package Uno;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Manche {
public int premier;
public static Esens sensJeu;
public int tour;
public static ArrayList<CarteUno> pioche = new ArrayList<>();
public static ArrayList<CarteUno> talon = new ArrayList<>();
public boolean vide; //si un joueur a vidé sa carte est vrai, faux sinon


public Manche(){
	premier();
	sensJeu = Esens.GAUCHE;
	pioche();
	seDistribuer();
	talon();
	tour=premier;
	verifierAction(talon.get(talon.size()-1));
	
	do {
		prendreCommande();
		tour();
		if(vide()) {
			if (testerPlus4()) {//si la dernier carte jouée est une carte +2 ou +4, le joueur suivant devra tirer 2 ou 4 cartes de la pioche 
				Scanner s = new Scanner(System.in);
				System.out.println(">> C'est à "+Partie.joueurs[tour].getNom()+" de joueur.\n> Si vous voulez lancer un défi à "+Partie.joueurs[tourPrece()].getNom()+" : entrez la commande /b ,sinon : entrez une autre commande" );
				String decision = s.nextLine();
				if (decision.substring(0,2).equalsIgnoreCase("/b")) {
						Partie.joueurs[tour].setCarteJouée(null);
						bluffer();
				} else plus4();
			}
		}
			
		
	} while(!vide());
	

	//afficher le score de manche
	System.out.println("\n>> SCORE : ");
	for(int i=0;i<Partie.nbjoueur;i++) {
	Partie.joueurs[i].calScore();
	System.out.println("\n> "+Partie.joueurs[i].getNom()+" : "+Partie.joueurs[i].getScore());
	//compter les manches gangées
	if(Partie.joueurs[i].getScore()==0)
		Partie.joueurs[i].setnbMancheGagné(Partie.joueurs[i].getnbMancheGagné()+1);		
	}
	
	
	
	
	
	
	
	
}

public void premier() {
	int aleatoire = new Random().nextInt(Partie.nbjoueur);
	premier=aleatoire;
	System.out.println("\n> c'est à "+Partie.joueurs[aleatoire].getNom()+" de jouer le premier !");
}

public void pioche() {
		Paquet p = new Paquet();
		pioche.addAll(p.cartes.subList(0, 108));
		}

public void talon() {
    int depart = (int)(Math.random() * pioche.size());
    	
    	talon.clear();
		//si on a tombé sur une +4, ça va le remettre dans la pioche et tirer une autre
		while (pioche.get(depart).getvaleur()==Evaleur.PLUS4) 
		    	depart = (int)(Math.random() * pioche.size());
		//l'ajout à le talon
		talon.add(pioche.get(depart));
		//suppression de carte tirée de la pioche
	    pioche.remove(depart);  
		//affichage du talon
		System.out.print("\nLa carte visible du talon :");
		talon.get(talon.size()-1).afficheCarte();
}

public void seDistribuer() {
	
		for(int i=0;i<Partie.nbjoueur;i++) {
			Partie.joueurs[i].getSaMain().clear();
			for(int j=0; j<6 ; j++) {
			    int aleat = (int)(Math.random() * pioche.size());
				Partie.joueurs[i].getSaMain().add(pioche.get(aleat));
				pioche.remove(aleat);  
			}
		}
}

public void prendreCommande() {
	//recevoire la commande
	String commande;

	Scanner s = new Scanner(System.in);
	if (testerPlus4()) {
		System.out.println(">> C'est à "+Partie.joueurs[tour].getNom()+" de décider.\n> Si vous voulez lancer un défi à "+Partie.joueurs[tourPrece()].getNom()+" : entrez la commande /b ,sinon : entrez une autre commande" );
		commande = s.nextLine();
		if (commande.substring(0,2).equalsIgnoreCase("/b")) {
			Partie.joueurs[tour].setCarteJouée(null);
			bluffer();
			System.out.print("\nLa carte visible du talon :");
			talon.get(talon.size()-1).afficheCarte();
		} 
		else {
			plus4();
			System.out.print("\nLa carte visible du talon :");
			talon.get(talon.size()-1).afficheCarte();
		}
	}
	else {	
		 System.out.println("\n"+Partie.joueurs[tour].getNom()+" doit jouer :" );
	 

	commande = s.nextLine();
	//lire la commande
	
		if (commande.substring(0,2).equalsIgnoreCase("/b")) {
			System.out.println("\n>> COMMANDE INVALIDE ! le joueur n'as pas joué une +4" );
			prendreCommande();
		}
	
		else if (commande.substring(0,2).equalsIgnoreCase("/j")) {
				System.out.println("\n> Entrez la carte que vous voulez (en respectant l'ordre)  : " );
				int choix = s.nextInt();
				
					Partie.joueurs[tour].joueCarte(choix-1);
						if(!Partie.joueurs[tour].getCarteValide())
							prendreCommande();
						else verifierAction(Partie.joueurs[tour].getCarteJouée());
						
						
		}
		
		else if (commande.substring(0,2).equalsIgnoreCase("/p")) {
					piocheEpuise();
					Partie.joueurs[tour].piocher();
					System.out.println("\n> "+Partie.joueurs[tour].getNom()+" a pioché !");
					System.out.print("\nLa carte visible du talon :");
					Manche.talon.get(Manche.talon.size()-1).afficheCarte();
				
		}
	
		else if (commande.substring(0,2).equalsIgnoreCase("/m")) {
			Partie.joueurs[tour].voirlescartes();
			prendreCommande();
		}
		
		else {
			System.out.println("\n>> COMMANDE INVALIDE ! TAPEZ :\n/m: pour voir tes cartes.\n/j X: pour jouer la Xème carte.\n/p: pour piocher.\n/b: pour jouer une carte +4. :P  " );
			prendreCommande();
		}
	 }
}
//apres qu'il lance le defi est ce qu'il faut qu'il joue ou c à le joueur suivat de jouer
public void bluffer() {
	boolean coupable=false;
	int i =0;
		while((i<Partie.joueurs[tourPrece()].getSaMain().size())&&(!coupable)) {
			if((Partie.joueurs[tourPrece()].getSaMain().get(i).getcouleur()==Manche.talon.get(Manche.talon.size()-1).getcouleur())&&(!Partie.joueurs[tourPrece()].getSaMain().get(i).action())) 
			coupable=true;
		i++;
		}	
		//si le joueur mis au défi- est coupable
		if(coupable) {
			for(int j=0;j<4;j++) {
				piocheEpuise();
				Partie.joueurs[tourPrece()].piocher();
			}
			System.out.println("\n"+Partie.joueurs[tour].getNom()+" a gagné le défi,"+Partie.joueurs[tourPrece()].getNom()+" doit piocher 6 cartes !");

		//si le joueur mis au défi- est innocent, je ne sais pas si c'est à passer son tour ou pas ?!
		}
		else {
			for(int j=0;j<6;j++) {
				piocheEpuise();
				Partie.joueurs[tour].piocher();
			}
			System.out.println("\n"+Partie.joueurs[tour].getNom()+" a perdue le défi, il doit piocher 6 cartes !");
				
		}
}

public boolean vide() {
	for(int i=0;i<Partie.nbjoueur;i++)
		if(Partie.joueurs[i].getSaMain().isEmpty())
			return true;
	
	return false;
}

public void tour() {
	if (sensJeu==Esens.GAUCHE) {
		if (tour==Partie.nbjoueur-1)
			tour=0;	//pareil à tour=0 else tour= i+1, c'est pour éviter le dépassement du taille du tableau
		else
			tour++;
			
	} else if (sensJeu==Esens.DROITE) {
		if (tour==0)
			tour=Partie.nbjoueur-1;	//c'est pour éviter la possibilité d'avoir -1 en indice du tableau
		else 
			tour--;
	}
}

public void verifierAction(CarteUno x) {
	

	if (x!=null) {
		if (x.getvaleur()==Evaleur.INVERSE) {
			inverser();
		}
		else if (x.getvaleur()==Evaleur.PASSET) {
				if(talon.size()==1) {
					System.out.println("\n> "+Partie.joueurs[tour].getNom()+" a passé son tour !");
					passeTour();
				}
				else {
					passeTour();
					System.out.println("\n> "+Partie.joueurs[tour].getNom()+" a passé son tour !");
				}
		}
		else if (x.getvaleur()==Evaleur.PLUS2) {

			if(talon.size()==1) {
				System.out.println("\n> "+Partie.joueurs[tour].getNom()+" a pioché 2 cartes et a passé son tour !");
				plus2();
				tour();

			}
			else {
				tour();
				plus2();
				System.out.println("\n> "+Partie.joueurs[tour].getNom()+" a pioché 2 cartes et a passé son tour !");
			}

		} else if((x.getvaleur()==Evaleur.JOKER)&&(talon.size()==1)) {
			do {
				Scanner s = new Scanner(System.in);
				System.out.println("\n> Quelle couleur attendue ?");
				String colChoisi = s.next();

					if(colChoisi.equalsIgnoreCase("NOIR")) {
						System.out.println("\n> Vous ne pouvez pas choisir la couleur noire !, essayer un autre");
						Partie.joueurs[tour].setCarteValide(false);

					}
					else {	
						Manche.talon.get(Manche.talon.size()-1).setcouleur(Ecouleur.valueOf(colChoisi.toUpperCase()));					
						Partie.joueurs[tour].setCarteValide(true);
						System.out.print("\n- "+Partie.joueurs[tour].getNom()+" joue un changement de couleur en "+Manche.talon.get(Manche.talon.size()-1).getcouleur());
					}
					
				} while(Partie.joueurs[tour].getCarteValide()==false);
		}
	}
	}

public void inverser() {
	if (sensJeu==Esens.GAUCHE) {
		sensJeu=Esens.DROITE;
		System.out.println("\n> le sens du jeu  est inversé vers droite !");
	}
	else {
		sensJeu=Esens.GAUCHE;
		System.out.println("\n> le sens du jeu  est inversé vers gauche !");
	}
}

public void passeTour() {
	tour();
}

public void plus2() {
	for(int i=0;i<2;i++) {
		piocheEpuise();
		Partie.joueurs[tour].piocher();
	}
}

public void plus4() {
	for(int i=0;i<4;i++) {
		piocheEpuise();
		Partie.joueurs[tour].piocher();
	}
	System.out.println("\n> "+Partie.joueurs[tour].getNom()+" a pioché 4 cartes et a passé son tour !");
	
}

public boolean testerPlus4() {
	if (Partie.joueurs[tourPrece()].getCarteJouée() !=null) {
		if (Partie.joueurs[tourPrece()].getCarteJouée().getvaleur()==Evaleur.PLUS4) {
			Partie.joueurs[tourPrece()].setCarteJouée(null);
			return true;
		}
		else return false;	
	} 	
	else return false;
}

public int tourPrece() {
	if (sensJeu==Esens.GAUCHE) {
		if (tour==0)
			return Partie.nbjoueur-1;	//c'est pour éviter la possibilité d'avoir -1 en indice du tableau
		else
			return tour-1 ;
			
	} else {
		if (tour==Partie.nbjoueur-1)
			return 0;	//c'est pour éviter la possibilité d'avoir un indice supérieur de la taille du tableau
		else 
			return tour+1 ;
	}
}

public void piocheEpuise() {
	if (pioche.isEmpty()) {
		for(int i=0;i<talon.size()-1;i++)
			if((talon.get(i).getvaleur()==Evaleur.JOKER)||(talon.get(i).getvaleur()==Evaleur.PLUS4))
					talon.get(i).setcouleur(Ecouleur.NOIR);	
		
		//remélanger le talon
		Collections.shuffle(talon.subList(0, talon.size()-1));
		//stocker des cartes
		pioche.addAll(talon.subList(0, talon.size()-1));
		//supprimer les cartes du talon remis dans la pioche
		talon.subList(0,talon.size()-1).clear();
		System.out.println("\nle talon est remélangé, et remis dans la pioche ! ");
	} 

}


}
