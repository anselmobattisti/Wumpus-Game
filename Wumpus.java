class Wumpus {

	int tamanho = 6;
	String[][] cenario = new String[tamanho][tamanho];
	String[][] itens   = new String[tamanho][tamanho];
	
	int posx = 0; // posicao x do heroi
	int posy = 0; // posicao y do heroi
	int flechas = 3; // flechas restantes
	boolean morreu = false; 
	String mensagem = "";

	Wumpus(){
		// Geração aleatória dos objetos
		String[] objetos = {"W","*","B","B","B","B"};

		int x = 0;
		int y = 0;
		int erro = 0;

		for(String objeto : objetos){
			while(erro == 0){
				x = gerarNumero();
				y = gerarNumero();
				if(itens[x][y] == null && (x != 0 && y != 0)){
					itens[x][y] = objeto;
					erro = 1;
				}
			}
			erro = 0;
		}
		
		/*
		// Geração fixa dos objetos
		itens[5][3] = "*";
		itens[5][5] = "W";
		itens[3][0] = "B";
		itens[4][1] = "B";
		*/
		desenharCenario();
	}

	int gerarNumero(){
		return (int) (Math.random()*100)%tamanho;
	}

	void desenharCenario()
	{
		System.out.println("========================== \n       Caça ao Wumpus\n========================== ");
		String caractere = " "; 
		System.out.println("  +---+---+---+---+---+---+");
		System.out.println("  | 0 | 1 | 2 | 3 | 4 | 5 |");
		System.out.println("  +---+---+---+---+---+---+");
		for(int x = 0; x < tamanho; x++){
			System.out.print(x+" |");
			for(int y = 0; y < tamanho; y++){
				if(cenario[x][y] != null){
					caractere = cenario[x][y];
				} else {
					if(posx == x && posy ==y){
						caractere = "&";
					} else {
						caractere = " ";
					}
				}
				System.out.print(" "+caractere+" |");				
			}
			System.out.println("\n  +---+---+---+---+---+---+");
		}
		System.out.println("+--- Inventário -------------");		
		System.out.println("| Flechas: "+flechas);
		System.out.println("| Posição Atual: (x,y) ("+posx+","+posy+")");
		System.out.println("| MENSAGEM: "+mensagem);
		System.out.print("+----------------------------+");
	}

	void desenharItens()
	{
		String caractere;
		System.out.println("\nVeja onde estavam os objetos\n+---+---+---+---+---+---+");
		for(int x = 0; x < tamanho; x++){
			System.out.print("|");
			for(int y = 0; y < tamanho; y++){
				caractere = " ";
				if(itens[x][y] != null){
					caractere = itens[x][y];
				}
				System.out.print(" "+caractere+" |");				
			}
			System.out.println("\n+---+---+---+---+---+---+");
		}
	}

	void sentir(){
		mensagem = "";
		if(itens[posx][posy] != null){
			if(itens[posx][posy].equals("W") || itens[posx][posy].equals("B")){
				cenario[posx][posy] = itens[posx][posy];
				mensagem = "MORREU";
				desenharCenario();
				morreu = true;
				return;
			}

			if(itens[posx][posy].equals("*")){
				mensagem = "VITÓRIA";
				cenario[posx][posy] = "*";
				desenharCenario();
				morreu = true;
				return;
			}
		}

		if(posx > 0){
			ativarSentidos(posx-1,posy);
		}

		if(posx < (tamanho -1)){
			ativarSentidos(posx+1,posy);
		}

		if(posy > 0){
			ativarSentidos(posx,posy-1);
		}

		if(posy < (tamanho - 1)){
			ativarSentidos(posx,posy+1);
		}
		desenharCenario();
	}

	void ativarSentidos(int x, int y){
		if(itens[x][y] != null){
			if(itens[x][y].equals("B")){
				cenario[posx][posy] = "~";
				mensagem = "Sinto uma brisa! Deve ter um burraco aqui perto!";
			}
			if(itens[x][y].equals("W")){
				cenario[posx][posy] = "%";
				mensagem = "Quem peidou? Maldito fedor";
			}
		}
	}

	void andar(int direcao){
		switch(direcao) {
			case 8:
				if(posx > 0){
					posx--;
					sentir();
				}
				break;
			case 2:
				if(posx < tamanho-1){
					posx++;
					sentir();
				}
				break;
			case 4:
				if(posy > 0){
					posy--;
					sentir();
				}
				break;
			case 6:
				if(posy < tamanho-1){
					posy++;
					sentir();
				}
				break;
			default:
				this.desenharCenario();
				break;
		}
	}

	void atirar(int direcao){

		if(flechas <= 0) {
			mensagem = "Sem Flechas";
		} else { 
			flechas--;
			switch(direcao) {
				case 8:
					if(posx > 0){
						atirarFlexa(posx-1,posy);
					}
					break;
				case 2:
					if(posx < tamanho-1){
						atirarFlexa(posx+1,posy);
					}
					break;
				case 4:
					if(posy > 0){
						atirarFlexa(posx,posy-1);
					}
					break;
				case 6:
					if(posy < tamanho-1){
						atirarFlexa(posx,posy+1);
					}
					break;
				default:
					this.desenharCenario();
					break;
			}
		}
	}

	void atirarFlexa(int x, int y)
	{
		if(itens[x][y] != null && itens[x][y].equals("W")){
			mensagem = "Boa! Matou o Wumpus";
			itens[x][y] = null;
		} else {
			mensagem = "Errou o alvo";				
		}
		sentir();
	}
}
