import java.util.Scanner;

class Wumpus {

	public static void main(String args[]){

		Scanner in = new Scanner(System.in);

		Cenario cenario = new Cenario();
		Heroi heroi = new Heroi();

		heroi.setCenario(cenario);
		cenario.setHeroi(heroi);

		int direcao = -1;
		cenario.desenharCenario(cenario.getCenario());
		
		// 0 - sair 8 - andar norte 2 - andar sul 4 - andar 6 - andar oeste 5 - atirar
		while(direcao != 0 && !heroi.estaVivo()){
			System.out.print("\nFaça o seu movimento: ");
			direcao = in.nextInt();
			if(direcao == 5){
				System.out.println("Atirar em qual direção?");
				direcao = in.nextInt();
				heroi.atirar(direcao);
			} else {
				heroi.andar(direcao);
			}
			cenario.desenharCenario(cenario.getCenario());
		}
		System.out.println("");
	}
}
