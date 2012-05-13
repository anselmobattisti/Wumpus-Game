class Heroi {

	private int posx = 0; // posicao x do heroi
	private int posy = 0; // posicao y do heroi
	private int flechas = 3; // flechas restantes
	private boolean morreu = false; 
	Cenario cenario;
	
	void setCenario(Cenario cenario){
		this.cenario = cenario;
	}

	boolean estaVivo(){
		return this.morreu;
	}

	int getFlechas(){
		return this.flechas;
	}

	void sentir(){
		this.cenario.setMensagem("");
		if(this.cenario.getItem(posx,posy) != null){
			if(this.cenario.getItem(posx,posy).equals("W") || this.cenario.getItem(posx,posy).equals("B")){
				this.cenario.setCenario(posx,posy,this.cenario.getItem(posx,posy));
				this.cenario.setMensagem("MORREU");
				morreu = true;
				return;
			}

			if(this.cenario.getItem(posx,posy).equals("*")){
				this.cenario.setMensagem("Vitória");
				this.cenario.setCenario(posx,posy,this.cenario.getItem(posx,posy));
				morreu = true;
				return;
			}
		}

		if(posx > 0){
			ativarSentidos(posx-1,posy);
		}

		if(posx < (this.cenario.getTamanho() -1)){
			ativarSentidos(posx+1,posy);
		}

		if(posy > 0){
			ativarSentidos(posx,posy-1);
		}

		if(posy < (this.cenario.getTamanho() - 1)){
			ativarSentidos(posx,posy+1);
		}
	}

	void ativarSentidos(int x, int y){
		// poderia ser usado o try no lugar desse if, seria mais adequado porém ainda não chegamos lá
		if(this.cenario.getItem(x,y) != null){
			if(this.cenario.getItem(x,y).equals("W")){
				this.cenario.setCenario(posx,posy,"%");
				this.cenario.setMensagem("Cheiro de carne podre");
				return;
			}
			if(this.cenario.getItem(x,y).equals("B")){
				this.cenario.setCenario(posx,posy,"~");
				this.cenario.setMensagem("Sinto um frio na espinha!");
				return;
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
				if(posx < this.cenario.getTamanho()-1){
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
				if(posy < this.cenario.getTamanho()-1){
					posy++;
					sentir();
				}
				break;
			default:

				break;
		}
	}

	void atirar(int direcao){

		if(flechas <= 0) {
				this.cenario.setMensagem("Maldição! Estou sem flechas!");
		} else { 
			flechas--;
			switch(direcao) {
				case 8:
					if(posx > 0){
						atirarFlexa(posx-1,posy);
					}
					break;
				case 2:
					if(posx < this.cenario.getTamanho()-1){
						atirarFlexa(posx+1,posy);
					}
					break;
				case 4:
					if(posy > 0){
						atirarFlexa(posx,posy-1);
					}
					break;
				case 6:
					if(posy < this.cenario.getTamanho()-1){
						atirarFlexa(posx,posy+1);
					}
					break;
				default:
					break;
			}
		}
	}

	void atirarFlexa(int x, int y)
	{
		if(this.cenario.getItem(x,y) != null && this.cenario.getItem(x,y).equals("W")){
			this.cenario.setMensagem("E a besta finalmente está morta!");
			this.cenario.setItemNull(x,y);
		} else {
			this.cenario.setMensagem("Errei o alvo");
		}
	}

	boolean estaNaPosicao(int x, int y){
		if(this.posx == x && this.posy == y){
			return true;
		}
		return false;
	}

	String getPosicaoAtual(){
		return this.posx+","+this.posy;
	}
}
