import java.util.*;

public abstract class Veiculo{

    // variaveis de instancia
    private String matricula;
    private int capacidade;  // em numero de passageiros
    private ArrayList<Transportavel> servicosTransportaveis;
    private GeoLocalizacao localizacao;
    private EstadoVeiculo estado;
    
    
    // construtores
    public Veiculo(String matr, int capac, double longi, double lati){
        this.matricula = matr;
        this.capacidade = capac;
        this.servicosTransportaveis = new ArrayList<Transportavel>();
        this.localizacao = new GeoLocalizacao(longi,lati);
        this.estado=EstadoVeiculo.LIVRE;
    }
    
    public Veiculo(String matr, int capac, GeoLocalizacao g){
        this.matricula = matr;
        this.capacidade = capac;
        this.servicosTransportaveis = new ArrayList<Transportavel>();
        this.localizacao = g.clone();
        this.estado=EstadoVeiculo.LIVRE;
    }
    
    // metodos seletores e modificadores
    public String getMatricula(){ return this.matricula; }
    public int getCapacidade(){ return this.capacidade; }
    public GeoLocalizacao getLocalizacao(){ return this.localizacao.clone(); }
    public EstadoVeiculo getEstado(){ 
        return this.estado;
    }
    
    /**
       Devolve uma copia dos servicos tranpostaveis efetuados
       */
    public ArrayList<Transportavel> getServicosTransportaveis(){
        ArrayList<Transportavel> temp = new ArrayList<Transportavel>();
        for(Transportavel y:this.servicosTransportaveis){
            temp.add(y);
        }
        return temp;
    }
    
    
    
    public void setMatricula(String nm){ this.matricula = nm; }
    public void setLocalizacao(double longi, double lati){
        this.localizacao = new GeoLocalizacao(longi, lati);
    }
    public void setLocalizacao(GeoLocalizacao g){
        this.localizacao = g.clone();
    }
    public void setEstadoOcupado(){
        this.estado = EstadoVeiculo.OCUPADO; 
    }
    public void setEstadoLivre(){
        this.estado=EstadoVeiculo.LIVRE;
    }
    
    public void adicionarServicoTransportavel(Transportavel y){
        this.servicosTransportaveis.add(y);
    }
    
    
    /**
     * Verifica se o veiculo dado como parametro e
     * igual ao veiculo recetor
     */
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        if((obj == null) || (this.getClass() != obj.getClass()))
            return false;
        Veiculo v = (Veiculo) obj;
        return (this.getMatricula() == v.getMatricula()); 
    }
    
    /**
     * Devolve od dados referentes ao veiculo
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Dados do Veiculo: ");
        s.append("\n\tMatricula: " + this.getMatricula());
        s.append("\n\tCapacidade: " + this.getCapacidade());
        s.append("\n\tLocalizacao: ");
        s.append("\n\t\t" + localizacao.toString());
        return s.toString();
    }
    
    /**
        Metodos abstratos
       */
      
      public abstract double custoTransporte(Transportavel y);
      public abstract Veiculo clone();
}