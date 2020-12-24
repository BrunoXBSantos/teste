public enum EstadoVeiculo{
    LIVRE("Livre"), 
    OCUPADO("Ocupado");
    
    private String descricao;
    
    EstadoVeiculo(String descricao){
        this.descricao=descricao;
    }
    
    public String getDescricao(){ return this.descricao; }
}