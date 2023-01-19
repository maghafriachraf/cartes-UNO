package Uno;

public class CarteUno {
private Ecouleur couleur;
private final Evaleur valeur;

public CarteUno(Ecouleur couleur, Evaleur valeur){
	this.valeur= valeur;
	this.couleur= couleur;
}

public Ecouleur getcouleur() {
	return this.couleur;
}

public void setcouleur(Ecouleur x) {
	this.couleur=x;
}

public Evaleur getvaleur() {
	return this.valeur;
}

public void afficheCarte() {
	System.out.println(valeur.toNum()+" "+couleur);
	}

public boolean action() {
	switch (valeur) {
	case PLUS2,PLUS4,INVERSE,PASSET,JOKER :
		return true;
		default :
		return false;
	}
}


}
 