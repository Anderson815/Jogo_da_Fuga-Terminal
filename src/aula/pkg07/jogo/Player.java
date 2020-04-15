package aula.pkg07.jogo;
import java.util.Scanner;
public class Player extends Jogador{
    
    public Player(){
        this.zerarPlayer();
        this.setForma("P");
    }
    
    public void zerarPlayer(){
        this.setLinha(11);
        this.setColuna(26);
    }
    
    @Override
    public void movimentar(){
        Scanner leitor = new Scanner(System.in);
        String movimento;        
        System.out.println("Escolha seu movimento: ");
        movimento = leitor.nextLine();
        switch(movimento){
            case "w": case "s":
                if(this.getLinha() > 2 && movimento.equals("w")){                    
                    this.setLinha(this.getLinha() - 1);
                }else if(this.getLinha() < 20 && movimento.equals("s")){
                    this.setLinha(this.getLinha() + 1);              
                }else{
                    System.out.println("Erro");
                }
                break; 
            case "a": case "d":
                if(this.getColuna() > 2 && movimento.equals("a")){
                    this.setColuna(this.getColuna() - 1);
                }else if(this.getColuna() < 50 && movimento.equals("d")){
                    this.setColuna(this.getColuna() + 1);                    
                }else{
                    System.out.println("Erro");
                }
                break;
            //default significa que não foi acionado nem um dos cases anteriroes
            default:
                System.out.println("Movimento Inválido");
        }
    }
    
}
