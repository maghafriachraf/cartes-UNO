package Uno;

public enum Evaleur {
	N0("0"),N1("1"),N2("2"),N3("3"),N4("4"),N5("5"),N6("6"),N7("7"),N8("8"),N9("9"),PLUS2("+2"),INVERSE("INVERSE"),PASSET("PASSE-TOUR"),PLUS4("+4"),JOKER("JOKER"),UNO("UNO à retirer");
	
	
	
	Evaleur(String num) {
		this.num = num;
	}

		private final String num ;
		public String toNum() {
		return num;
		}

}

