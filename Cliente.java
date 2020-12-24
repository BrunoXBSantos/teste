import java.util.*;

public class Cliente{ 
    
    // variaveis de classe
    /**
     * contaClientes refere se ao numero total de clientes com ficha registada na empresa
     */
    private static int contaClientes = 0; 
    
    //metodos de classe
    public static int getContaClientes(){ return Cliente.contaClientes; }
    public static void setContaClientes(int n){ Cliente.contaClientes = n; }
    
    // variaveis de instancia
    private int numero;     // numero de cliente, sequencial
    private String nome;    // nome do cliente
    private int nif;
    private char tipo;       // tipo de cliente {A,B,C}
    private GregorianCalendar dataRegisto;  // data de registo da ficha de cliente
    
    // construtores
    /**
     * Cria um cliente, fornecendo o nome e o nif
     */
    public Cliente(String nome, int nif){
        this.numero = ++Cliente.contaClientes;
        this.nome = nome;
        this.nif = nif;
        this.tipo = 'A';
        this.dataRegisto = new GregorianCalendar();
    }
    
    /**
     * cria um cliente fornecendo o nome, nif e a data de registo(dia-mes-ano)
     */
    public Cliente(String nome, int nif, int dia, int mes, int ano){
        this.numero = ++Cliente.contaClientes;
        this.nome = nome;
        this.nif = nif;
        this.tipo = 'A';
        this.dataRegisto = new GregorianCalendar(ano, mes-1, dia);
    }
    
    
    /**
     * cria um cliente fornecendo o nome, nif e a data de registo
     */
    public Cliente(String nome, int nif, GregorianCalendar data){
        this.numero = ++Cliente.contaClientes;
        this.nome = nome;
        this.nif = nif;
        this.tipo = 'A';
        this.dataRegisto = (GregorianCalendar) data.clone();
    }
    
    
    
    // metodos seletores e modificadores
    public int getNumero(){ return this.numero; }
    public String getNome(){ return this.nome; }
    public int getNif(){ return this.nif; }
    public char getTipo(){ return this.tipo; }
    public GregorianCalendar getDataRegisto(){ return this.dataRegisto; }
    
    public void setNome(String n){ this.nome = n; }
    public void setTipo(char c){ this.tipo = c; }
    public void setNumero(int n){ this.numero = n; }

    
    /**
     * Metodo que faz o clone de uma ficha de cliente
     */
    public Cliente clone(){
        Cliente n = new Cliente(this.nome, this.nif, this.dataRegisto);
        n.setTipo(this.getTipo());
        Cliente.setContaClientes(n.getNumero()-1);
        n.setNumero(n.getNumero()-1);
        return n;
    }
}