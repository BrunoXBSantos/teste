import java.lang.Math;

public class Pedido{
    // variaveis de instancia
    private int numCliente;
    private int numServicos;
    private GeoLocalizacao localCliente;
    private GeoLocalizacao localDestino;
    
    public Pedido(int nc, int ns, GeoLocalizacao c, GeoLocalizacao d){
        this.numCliente = nc;
        this.numServicos = ns;
        this.localCliente = c.clone();
        if(d!=null)
            this.localDestino = d.clone();
        else
            this.localDestino = null;
    }
    public Pedido(Pedido p){
        this(p.getNumCliente(),p.getNumServicos(),p.getLocalCliente(),p.getLocalDestino());
    }
    
    public int getNumCliente(){ return this.numCliente; }
    public int getNumServicos(){ return this.numServicos; }
    public GeoLocalizacao getLocalCliente(){ return this.localCliente.clone(); }
    public GeoLocalizacao getLocalDestino(){ return this.localDestino.clone(); }
    
    public Pedido clone(){
        return new Pedido(this);
    }
}