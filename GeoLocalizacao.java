/**
 * Geolocalizacao refere se a uma localizacao vista como um ponto no espaco tridimensional.
 * pode ser so usado como um ponto no plano, utilizando apenas as variaveis latitude e longitude
 */
public class GeoLocalizacao{
    // variaveis de instancia
    private double longitude;
    private double latitude;
      
    // construtores
    public GeoLocalizacao(double longi, double lati){
        this.longitude = longi;
        this.latitude = lati;
    } 

    public GeoLocalizacao(GeoLocalizacao g){
        this.longitude = g.getLongitude();
        this.latitude = g.getLatitude();
    }
    // metodos seletores e modificadores
    public double getLongitude(){ return this.longitude; }
    public double getLatitude(){ return this.latitude; }
    
    public void setLongitude(int nl){ this.longitude = nl; }
    public void setLatitude(int nl){ this.latitude = nl; }

    
    /**
     * metodo clone
     */
    public GeoLocalizacao clone()
        { return new GeoLocalizacao(this); }
    
    /**
     * Converte a Geolocalizacao para String
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Localizaçao: ");
        s.append("\n\tLongitude: " + this.getLongitude());
        s.append("\n\tLatitude: " + this.getLatitude());
        return s.toString();
    }
    
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if((this.getClass() != obj.getClass()) || obj == null)
            return false;
        GeoLocalizacao g = (GeoLocalizacao) obj;
        return ((this.getLongitude() == g.getLongitude()) && 
               (this.getLatitude() == g.getLatitude()));
    }
}