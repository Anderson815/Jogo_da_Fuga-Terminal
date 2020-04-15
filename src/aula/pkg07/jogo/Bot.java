package aula.pkg07.jogo;

import java.util.Random; // aleatório

public class Bot extends Jogador{    
    // Atributos da classe
    private static int instancia_linha = 5;
    private static int instancia_coluna = 11;
    private static int cod = 0;
    private static Bot[] bots;
        
    //Métodos principais da classe
    public static void zerarBots(Bot[] bot){
        Bot.setInstancia_linha(5);
        Bot.setInstancia_coluna(11);
        
        for(int indice = 0; indice <= bot.length - 1; indice++){
            bot[indice].setLinha(Bot.getInstancia_linha());
            bot[indice].setColuna(Bot.getInstancia_coluna());
            
            Bot.setInstancia_coluna(Bot.getInstancia_coluna() + 10);
            
            if(indice == 3){
                Bot.setInstancia_linha(17);
                Bot.setInstancia_coluna(11);
            }
        }
    }
    
    // Métodos especiais da classe
    public static int getInstancia_linha() {
        return instancia_linha;
    }

    public static void setInstancia_linha(int instancia_linha) {
        Bot.instancia_linha = instancia_linha;
    }

    public static int getInstancia_coluna() {
        return instancia_coluna;
    }

    public static void setInstancia_coluna(int instancia_coluna) {
        Bot.instancia_coluna = instancia_coluna;
    }

    public static int getCod() {
        return cod;
    }

    public static void setCod(int cod) {
        Bot.cod = cod;
    }
       
    public static Bot[] getBots() {
        return bots;
    }

    public static void setBots(Bot[] bots) {
        Bot.bots = bots;
    }
    
    //Atributos de Teste
    public int codIndice;
    
    // Método Construtor
    
    public Bot(){
        this.setLinha(Bot.getInstancia_linha());
        this.setColuna(Bot.getInstancia_coluna());
        this.setForma("E");
        this.codIndice = Bot.getCod();
        
        Bot.setInstancia_coluna(Bot.getInstancia_coluna() + 10);
        Bot.setCod(Bot.getCod() + 1);
        
        if(Bot.getCod() == 4){
            Bot.setInstancia_linha(17);
            Bot.setInstancia_coluna(11);
        }        
    }
    
    // 0 Métodos Interface
    
    @Override
    public void movimentar(){   
    }
 
    public void movimentar(Player p, int nivelbot){
        //!!!!! escolha de raciocínio !!!!!
        int cod_movimento = 0;
        switch (nivelbot){
            case 0:
                cod_movimento = this.raciocinioN0();
                break;
            case 1:
                cod_movimento = this.raciocinioN1(p);
                break;
            case 2:
                cod_movimento = this.raciocinioN2(p);
                break;
        }
        // Correção do movimento - não permite movimentos inválidos
        cod_movimento = this.vcMovimento(cod_movimento);
        // !!!!! Ação de movimentar !!!!!
        
        // cod_movimento é um tipo inteiro que vai do número 1 ao 4
        // cada número vai fazer um determinado movimento
                
        switch(cod_movimento){
            case 1:
                // vai para a direita
                this.setColuna(this.getColuna() + 1);
                break;
            case 2:
                // vai para a esquerda
                this.setColuna(this.getColuna() - 1);
                break;
            case 3:
                // vai para baixo
                this.setLinha(this.getLinha() + 1);
                break;
            case 4:
                // vai para cima
                this.setLinha(this.getLinha() - 1);
                break;
            default:
                System.out.println("Movimento inválido do bot");
        }
    }
    
    // 1 Métodos Raiocinios (faz o bot pensar)
    private int raciocinioN0(){
        Random valorAleatorio = new Random();
        //gera numeros aleatórios de 0 a 3 por isso + 1
        int movimento = valorAleatorio.nextInt(4) + 1;
        return movimento;
    }
    
    private int raciocinioN1(Player p){
        int distancialinha = this.distancia(p.getLinha(), this.getLinha(), true);
        int distanciacoluna = this.distancia(p.getColuna(), this.getColuna(), true);
        int movimento = 0;
        
        if(distancialinha * 2.5f <= distanciacoluna){
            //Operador ternário: atribuição a uma variável através de uma condição
            //variável = (condição) ? valor verdade : valor falso ;
            movimento = (this.distancia(p.getColuna(), this.getColuna(), false) > 0) ? 1: 2;
        }else{
            movimento = (this.distancia(p.getLinha(), this.getLinha(), false) > 0) ? 3 : 4;
        }
        return movimento;
    }
    
    private int raciocinioN2(Player p){
        int movimento = 0;
        int porcentagem = 0;
        int direcao = 0;
        
        Random valorAleatorio = new Random();
        porcentagem = valorAleatorio.nextInt(10) + 1;
        
        if(porcentagem <= 4){
            direcao = this.distancia(p.getColuna(), this.getColuna(), false);
            //Operador ternário: atribuição a uma variavél através de uma condição
            //variável = (condição) ? valor se for verdade : valor se for falso;
            movimento = (direcao > 0)? 1: 2;
        }else if(porcentagem <= 8){
            direcao = this.distancia(p.getLinha(), this.getLinha(), false);
            movimento = (direcao > 0)? 3: 4;
        }else if(porcentagem == 9){
            direcao = this.distancia(p.getColuna(), this.getColuna(), false);
            movimento = (direcao > 0)? 2: 1;
        }else{
            direcao = this.distancia(p.getColuna(), this.getColuna(), false);
            movimento = (direcao > 0)? 4: 3;
        }
        
        return movimento;
    }
    
    // 2 Métodos Auxiliares
    
    private int distancia(int posicaop_l_ou_c, int posicaob_l_ou_c, boolean direcao){
    // Se o resultado der positivo: significa que o player está do lado direito ou em baixo do bot
    // Se o resultado der negativo: significa que o player está do lado esquerdo ou em cima do bot
        int distancia = posicaop_l_ou_c - posicaob_l_ou_c;
        // Operador ternário: uma atribuição a uma variável de acordo a uma condição
        // variavel = (condição)?valor verdadeiro:valor falso;
        distancia = (distancia < 0 && direcao)?distancia * -1:distancia;
        return distancia;
    }
    
    
    private int vcMovimento(int cod_movimento){
    // verifica e corrigi o movimento
        boolean retorno = false;
        
        // limitar no mapa
        do{
            do{
                retorno = false;
                switch(cod_movimento){
                    case 1:
                        if(this.getColuna() == 50){
                            cod_movimento++;
                            retorno = true;
                        }
                        break;
                    case 2:
                        if(this.getLinha() == 2){
                            cod_movimento++;
                            retorno = true;
                        }
                        break;
                    case 3:
                        if(this.getLinha() == 20){
                            cod_movimento++;
                            retorno = true;
                        }
                        break;
                    case 4:
                        if(this.getLinha() == 2){
                            cod_movimento = 1;
                            retorno = true;
                        }
                        break;                               
                }
            }while(retorno);
        
        // Um bot não ficar em cima do outro
        
            for(int indice = 0; indice < Bot.bots.length; indice++){
                if(indice != this.getCodIndice()){
                    switch(cod_movimento){
                        case 1:
                            if(this.getLinha() == Bot.bots[indice].getLinha() &&
                                this.getColuna() == Bot.bots[indice].getColuna() - 1){
                                cod_movimento++;
                                retorno = true;
                            }
                            break;
                        case 2:
                            if(this.getLinha() == Bot.bots[indice].getLinha() &&
                                this.getColuna() == Bot.bots[indice].getColuna() + 1){
                                cod_movimento++;
                                retorno = true;
                            }
                            break;
                        case 3:
                            if(this.getColuna() == Bot.bots[indice].getColuna() &&
                                this.getLinha() == Bot.bots[indice].getLinha() - 1){
                                cod_movimento++;
                                retorno = true;
                            }
                            break;
                        case 4:
                            if(this.getColuna() == Bot.bots[indice].getColuna() &&
                                this.getLinha() == Bot.bots[indice].getLinha() + 1){
                                cod_movimento = 1;
                                retorno = true;
                            }
                            break;
                    }
                }
            }
        }while(retorno);
        
        return cod_movimento;
    }
    
    // Métodos especiais
    public int getCodIndice() {
        return codIndice;
    }

    public void setCodIndice(int codIndice) {
        this.codIndice = codIndice;
    }
    
}
