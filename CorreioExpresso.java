
import java.util.*;

public class CorreioExpresso extends Servicos{    
    
    private double peso;
    
    
    // construtores
    public CorreioExpresso(double p,int numT,double c){
        super(numT,c);
        this.peso = p;
    }
    
    public CorreioExpresso(double p,double dist,int numT,double c,int ano, int mes, int dia){
        super(dist,numT,c,ano,mes,dia);
        this.peso = p;
    }
    
    public CorreioExpresso(double p,double dist,int numT,double c, GregorianCalendar d){
        super(dist,numT,c,d);
        this.peso = p;
    }
    
    
    public double getPeso(){
        return this.peso;
    }
    public void setPeso(double np)
    {
        this.peso = np;
    }
    
    // redefinicao de metodos abstratos
    public double preco(){
        return this.peso * super.getDistancia() * super.getCustoPorkm() 
               * super.getNumTransportavel();
    }
    
    public Servicos clone(){
        Servicos s = new CorreioExpresso(this.peso,super.getDistancia(),super.getNumTransportavel(),
                       super.getCustoPorkm(),super.getData());
        Servicos.decContaServicos();
        s.setNumServicos(s.getNumServicos()-1);
        return s;
    }
    
}