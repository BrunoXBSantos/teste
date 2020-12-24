public class Mercadorias extends Veiculo{

    // construtores
    public Mercadorias(String matr, int capac, double longi, double lati){
            super(matr,capac,longi,lati);
        }
        
     public Mercadorias(String matr, int capac, GeoLocalizacao g){
            super(matr,capac,g);
        }      
    
    /**
     * Preco total do aluguer do veiculo, em funcao do numero de km e do peso da mercadoria
     */
    public double custoTransporte(Transportavel y){
        return y.preco() ;
    }
    
    public Veiculo clone(){
        return new Mercadorias(super.getMatricula(),super.getCapacidade(),super.getLocalizacao());
    }
}