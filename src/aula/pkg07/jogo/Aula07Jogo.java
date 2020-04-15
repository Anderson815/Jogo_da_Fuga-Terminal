package aula.pkg07.jogo;

import java.util.Scanner;

public class Aula07Jogo {    
    public static void main(String[] args) {
        /* NOTA DE OBSERVAÇÃO
        Toda SubClasse pode ser considerada como se fosse a sua SuperClasse
        Neste programa eu posso considerar Player ou Bots como se fosse Jogadores
        
        Player p = new Player();
        Bot b = new Bot();
        
        p.setLinha(10);
        p.setColuna(10);
        
        b.setLinha(10);
        b.setColuna(10);
        
        Preste atenção no código a seguir
        p.verificarPosicao(b); ele considera b, que é uma instância da classe Bot, como se fosse uma instância da classe Jogador
        b.verificarPosicao(p); aqui é mesmo caso que o de cima, porém considerando Player como Jogador
              
        */
        
        // Declarações de variáveis
        Player p = new Player();
        Bot[] b = new Bot[8];
        b[0] = new Bot();
        b[1] = new Bot();
        b[2] = new Bot();
        b[3] = new Bot();
        b[4] = new Bot();
        b[5] = new Bot();
        b[6] = new Bot();
        b[7] = new Bot();
        Bot.setBots(b);       
        Scanner leitor = new Scanner(System.in);
        int op;
        String resposta;
        
        // Menu
        do{
            System.out.println("### Bem vindo ao jogo da Fuga ###");
            System.out.println("1 - Level");
            System.out.println("2 - Level");
            System.out.println("3 - Level");
            System.out.println("4 - Level");
            System.out.println("5 - Level");
            System.out.println("0 - Sair");
            System.out.println("Escolha uma das opções");
            op = leitor.nextInt();
            resposta = "n";
            
            do{
                if(resposta.equals("s")){
                    op++;
                }
                switch(op){
                    case 0:
                        break;
                    case 1:
                        Aula07Jogo.level(p, b, 1);                    
                        break;
                    case 2:
                        Aula07Jogo.level(p, b, 2);
                        break;
                    case 3:
                        Aula07Jogo.level(p, b, 3);
                        break;
                    case 4:
                        Aula07Jogo.level(p, b, 4);
                        break;
                    case 5:
                        Aula07Jogo.level(p, b, 5);
                        break;
                    default:
                        System.out.println("Opção incorreta, por favor colocar um valor válido");
                }
                if(op != 0 && op != 5){
                    do{
                        System.out.println("Ir para o proxímo nével(S/N): ");
                        resposta = leitor.next();
                    }while(!resposta.equals("s") && !resposta.equals("n"));
                }else{
                    resposta = "n";
                }
                Bot.zerarBots(b);
                p.zerarPlayer();
            }while(resposta.equals("s"));
        }while(op != 0);
        
        //Level 5
        

        
    }
      
    private static void level(Player p, Bot[] b, int n){
        // 1 - Todos os Bots pensam
        // 2 - São 60 passos
        System.out.println("\n\n\n\n\n#### 60 - Passos ####");
        Mapa m = new Mapa(p, b);
        boolean continua = true; 
        int nivelbot = 0;
        
        for(int contador = 1; contador <= 60; contador++){
            continua = true;
            // Player
            p.movimentar();
            for(int indice = 0; indice <= 7; indice++){
                continua = p.verificarGameOver(b[indice]);
                if(!continua){
                    break;
                }
            }
            
            if(!continua){
                System.out.println("\n\n\n\n\n#### " + (60 - contador) + " - Passos ####");
                m.atualizaMapa(p, b);
                System.out.println("\n\n#### !!!! GAME OVER !!!! ####\n\n");
                break;
            }
            
            // Bots
            for(int indice = 0; indice <= 7; indice++){
                nivelbot = 0;
                switch(n){
                    case 1:
                        nivelbot = 0;
                        break;
                    case 2:
                        if(indice == 0 || indice == 7){
                            nivelbot = 1;
                        }else if(indice == 2 || indice == 6){
                            nivelbot = 2;
                        }else{
                            nivelbot = 0;
                        }
                        break;
                    case 3:
                        if(indice == 0 || indice == 3 || indice == 4 || indice == 7){
                            nivelbot = 1;
                        }else if( indice == 2 || indice == 6){
                            nivelbot = 2;
                        }else{
                            nivelbot = 0;
                        }
                        break;
                    case 4:
                        if(indice == 0 || indice == 3 || indice == 4 || indice == 7){
                            nivelbot = 1;
                        }else{
                            nivelbot = 2;
                        }
                        break;
                    case 5:
                        nivelbot = 1;
                        break;
                }
                b[indice].movimentar(p, nivelbot);
                continua = b[indice].verificarGameOver(p);
                if(!continua){
                    break;
                }
            }
            System.out.println("\n\n\n\n\n#### " + (60 - contador) + " - Passos ####");
            m.atualizaMapa(p, b);
                   
            if(!continua){
                System.out.println("\n\n#### !!!! GAME OVER !!!! ####\n\n");
                break;
            }
        }
        
        if(continua){
            System.out.println("\n\n##### Parabéns você ganhou !!! #####\n\n");
        }
    }
}

