package aula.pkg07.jogo;
public class Mapa {
    
    public Mapa(Player p, Bot[] b){
        this.atualizaMapa( p, b);
    }
    
    public void atualizaMapa(Player p, Bot[] b){
        //System.out.println("\n\n\n\n\n");
        int indice = 0;
        for(int linha = 1; linha <= 21; linha++){ 
            //<= 69
            for(int coluna = 1; coluna <= 51 ; coluna++){
                if((linha == 1 || linha == 21) && (coluna > 1 && coluna < 51)){
                    System.out.print("=");
                }else{
                    if(coluna == 1 || coluna == 51){
                        System.out.print("|");
                    }
                    else if(this.verificarPosicaoBot(b, linha, coluna) >= 0 ){
                        System.out.print(b[this.verificarPosicaoBot(b, linha, coluna)].getForma());
                    }
                    else if(p.getColuna() == coluna && p.getLinha() == linha){
                        System.out.print(p.getForma());
                    }  
                    else{
                        System.out.print(" ");
                    }
                }
            }
            System.out.println("");
        }
    }
 
    // Métodos auxiliares
    private int verificarPosicaoBot(Bot[] b, int linha, int coluna){
        int r = -1;
        
            for(int indice = 0; indice < b.length; indice++){
                //Operador ternário: atribuição a uma variável através de uma condição
                //variavel = (condição) ? valor verdadeiro : valor falso;
                r = (b[indice].getLinha() == linha && b[indice].getColuna() == coluna)? indice: r;
            }
        
        return r;
    }
}











