public class PessoasRefeicoes extends Veiculo implements Transportavel{
    
    // vars para interface
    private int numTransportavel;
    private double custoPorkm;
    
    // metodos e variaveis de classe
    
    
    public PessoasRefeicoes(String matr, int capac, double longi, double lati){
            super(matr,capac,longi,lati);
    }
        
    public PessoasRefeicoes(int n,double c,String matr,int capac,double longi,double lati){
            super(matr,capac,longi,lati);
            this.numTransportavel = n;
            this.custoPorkm = c;
     }    
     public PessoasRefeicoes(String matr, int capac, GeoLocalizacao g){
            super(matr,capac,g);
        }
        
    /**
     * Preco total do aluguer do veiculo, em funcao do numero de km e do preco por km
     */
    
    public int getNumTransportavel(){ return this.numTransportavel; }
    public double getCustoPorkm(){ return this.custoPorkm; }
    
    public void setNumTransportavel(int n) { this.numTransportavel = n; }
    public void setCustoPorkm(double n){ this.custoPorkm = n; }
    
   
    public double preco(){
        return this.custoPorkm;
    }
    
    public double custoTransporte(Transportavel y){
         return y.preco();
     }
    
    public Veiculo clone(){
        return new PessoasRefeicoes(this.numTransportavel, this.custoPorkm, super.getMatricula(),
                    super.getCapacidade(),super.getLocalizacao().getLongitude(),super.getLocalizacao().getLatitude());
    }
}