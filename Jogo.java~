import java.util.Scanner;

class Jogo {

	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		Wumpus w = new Wumpus();
		int direcao = -1;
		// 0 - sair 8 - andar norte 2 - andar sul 4 - andar 6 - andar oeste 5 - atirar
		while(direcao != 0 && !w.getHeroi().estaVivo()){
			System.out.print("\nFaça o seu movimento: ");
			direcao = in.nextInt();
			if(direcao == 5){
				System.out.println("Atirar em qual direção?");
				direcao = in.nextInt();
				w.getHeroi().atirar(direcao);
			} else {
				w.getHeroi().andar(direcao);
			}
		}
		System.out.println("");
	}
}
