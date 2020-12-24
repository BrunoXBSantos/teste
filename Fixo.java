import java.util.*;
/**
 *    Tipo de Servico:
 *                  Preço e trajeto fixo 
 */

public class Fixo extends Servicos{
    //construtores
    /**
       Servico fixo, com distancia fixa e preco por km. 
       */
    public Fixo(double dist, int numT, double c){
        super(dist,numT,c);
    }
    public Fixo(double dist,int numT, double c, int ano, int mes, int dia){
        super(dist,numT,c,ano,mes,dia);
    }
    public Fixo(double dist,int numT, double c, GregorianCalendar d){
        super(dist,numT,c,d);
    }
    
    public double preco(){
        return super.getDistancia() * super.getCustoPorkm();
    }
    
    public Servicos clone(){
        Servicos s = new Fixo(super.getDistancia(),super.getNumTransportavel(),super.getCustoPorkm(),
                          super.getData());
        Servicos.decContaServicos();
        s.setNumServicos(s.getNumServicos()-1);
        return s;
    }
}