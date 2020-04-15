package aula.pkg07.jogo;
public abstract class Jogador {
    private int linha;
    private int coluna;
    private String forma;
    
   
    public abstract void movimentar();
    
    public boolean verificarGameOver(Jogador j){
        boolean continua = true;
        //Operador ternário: uma atribuição a uma uníca variável através de uma condição
        //variável = (condição) ? atribuição caso seja verdade : atribuição caso seja falso;
        continua = (this.getLinha() == j.getLinha() && this.getColuna() == j.getColuna())? false: continua;
        return continua;
    }
    
    public void setForma(String forma){
        this.forma = forma;
    }
    
    public String getForma(){
        return this.forma;
    }
    
    public void setLinha(int linha){
        this.linha = linha;
    }
    
    public int getLinha(){
        return this.linha;
    }
    
    public void setColuna(int coluna){
        this.coluna = coluna;
    }
    
    public int getColuna(){
        return this.coluna;
    }
}
