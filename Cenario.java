class Cenario {

	private int tamanho = 6;
	
	private String[][] cenario = new String[tamanho][tamanho];
	private String[][] itens   = new String[tamanho][tamanho];
	
	private Heroi heroi;

	private String mensagem = "";

	void setHeroi(Heroi heroi){
		this.heroi = heroi;
	}

	void setPosicaoCenario(int x, int y, String caracter){
		this.cenario[x][y] = caracter;
	}

	String[][] getItens(){
		return this.itens;
	}

	String[][] getCenario(){
		return this.cenario;
	}

	Cenario(){

		// Geração aleatória dos objetos
		String[] objetos = {"W","*","B","B","B","B","B"};

		int x = 0;
		int y = 0;
		int erro = 0;

		for(String objeto : objetos){
			while(erro == 0){
				x = gerarNumero();
				y = gerarNumero();
				if(this.getItem(x,y) == null && (x != 0 && y != 0)){
					this.setItem(x,y,objeto);
					erro = 1;
				}
			}
			erro = 0;
		}
	}

	private int gerarNumero(){
		return (int) (Math.random()*100)%tamanho;
	}

	Heroi getHeroi(){
		return this.heroi;
	}	

	void desenharCenario(String vetor[][])
	{
		System.out.println("========================== \n       Caça ao Wumpus\n========================== ");
		String caractere = " "; 
		System.out.println("  +---+---+---+---+---+---+");
		System.out.println("  | 0 | 1 | 2 | 3 | 4 | 5 |");
		System.out.println("  +---+---+---+---+---+---+");
		for(int x = 0; x < tamanho; x++){
			System.out.print(x+" |");
			for(int y = 0; y < tamanho; y++){

				if(vetor[x][y] == null){
					caractere = " ";
				} else {
					caractere = vetor[x][y];
				}

				if(this.heroi.estaNaPosicao(x,y) && caractere.equals(" ")){
					caractere = "&";
				} 

				System.out.print(" "+caractere+" |");				
			}
			System.out.println("\n  +---+---+---+---+---+---+");
		}
		System.out.println("+--- Inventário -------------");		
		System.out.println("| Flechas: "+this.heroi.getFlechas());
		System.out.println("| Posição Atual: (x,y) ("+this.heroi.getPosicaoAtual()+")");
		System.out.println("| MENSAGEM: "+mensagem);
		System.out.print("+----------------------------+");
	}

	public String getItem(int x, int y){
		return this.itens[x][y];
	}

	public void setMensagem(String msg){
		this.mensagem = msg;
	}

	public void setItem(int x, int y, String valor){
		this.itens[x][y] = valor;
	}

	public void setCenario(int x, int y, String valor){
		this.cenario[x][y] = valor;
	}

	public void setItemNull(int x, int y){
		this.itens[x][y] = null;
	}

	public int getTamanho(){
		return this.tamanho;
	}
}


















