import java.util.*;

public class TuberEats extends Servicos{
    // variavel de instancia
    private double valorRefeicao;
    
    // metodos seletores e interrogadores
    public double getValorRefeicao(){ return this.valorRefeicao; }
    public void setValorRefeicao(double nv){ this.valorRefeicao = nv; }
    
    // construtores
    public TuberEats(double vr,int numT, double c){
        super(numT,c);
        this.valorRefeicao = vr;
    }
    
    public TuberEats(double vr,double dist, int numT, double c, int ano, int mes, int dia){
        super(dist,numT,c,ano,mes,dia);
        this.valorRefeicao = vr;
    }
    
    public TuberEats(double vr,double dist, int numT, double c, GregorianCalendar d){
        super(dist,numT,c,d);
        this.valorRefeicao = vr;
    }
    
    // redefinicao de metodos
    public double preco(){
        return this.valorRefeicao * super.getCustoPorkm() * super.getDistancia() 
                * super.getNumTransportavel();
    }
    
    public Servicos clone(){
        Servicos s = new TuberEats(this.valorRefeicao,super.getDistancia(),super.getNumTransportavel(),
                    super.getCustoPorkm(),super.getData());
        Servicos.decContaServicos();
        s.setNumServicos(s.getNumServicos()-1);
        return s;
    }
}
