import java.util.*;

public abstract class Servicos implements Transportavel{
    // variaveis de classe
    private static int contaServicos = 0; // numero de servicos efetuados
    
    // metodos de classe
    public static int getContaServicos(){ return Servicos.contaServicos; }
    public static void decContaServicos(){ Servicos.contaServicos--; }
    
    // variaveis de classe
    private int numServicos;
    private GregorianCalendar data;
    private double distancia; // distancia percorrida
    
    private int numTransportavel;
    private double custoPorkm;
    
    //construtores
    public Servicos(int numT, double c){
        this.numServicos = ++Servicos.contaServicos;
        this.numTransportavel = numT;
        this.custoPorkm = c;
        this.distancia = 0;
        this.data = new GregorianCalendar();
    }
    
    public Servicos(double dist, int numT, double c){
        this.numServicos = ++Servicos.contaServicos;
        this.numTransportavel = numT;
        this.custoPorkm = c;
        this.distancia = dist;
        this.data = new GregorianCalendar();
    }
    
    
    public Servicos(double dist, int numT, double c, int ano, int mes, int dia){
        this.numServicos = ++Servicos.contaServicos;
        this.distancia = dist;
        this.custoPorkm = c;
        this.distancia = dist;
        this.data = new GregorianCalendar(ano,mes-1,dia);
    }
    
    public Servicos(double dist, int numT, double c, GregorianCalendar nd){
        this.numServicos = ++Servicos.contaServicos;
        this.distancia = dist;
        this.custoPorkm = c;
        this.distancia = dist;
        this.data = (GregorianCalendar)nd.clone();
    }
    
    // metodos seletores e modificadores
    public int getNumServicos(){ return this.numServicos; }
    public GregorianCalendar getData(){ 
        return (GregorianCalendar) this.data.clone();
    }
    public double getDistancia(){ return this.distancia; }
    public int getNumTransportavel(){ return this.numTransportavel; }
    public double getCustoPorkm(){ return this.custoPorkm; }
    
    public void setNumServicos(int n){ this.numServicos=n; }
    public void setDistancia(double nd){ this.distancia = nd; }
    public void setNumTransportavel(int n) { this.numTransportavel = n; }
    public void setCustoPorkm(double n){ this.custoPorkm = n; }
    
    public abstract Servicos clone();
}