import java.util.*;

public class Empresa{
    // variaveis de instancia
    private String nomeEmpresa;  // nome da empresa
    private TreeMap<Integer,Cliente> listaClientes;   // numero cliente -> ficha de cliente
    private HashMap<String,Veiculo> listaVeiculos;  // matricula->veiculo
    private TreeMap<Integer,Servicos> listaServicosRealizados;
    private TreeMap<Integer,ArrayList<Reserva>> listaReservasPorCliente;  // lista de clientes por reservas terminadas
    
    private TreeMap<Integer,Reserva> listaReservas;
    private TreeMap<Integer,Servicos> listaServicosPendentes;
    
    // construtores
    public Empresa(String nome){
        this.nomeEmpresa = nome;
        this.listaClientes = new TreeMap<Integer,Cliente>();
        this.listaVeiculos = new HashMap<String,Veiculo>();
        this.listaServicosRealizados = new TreeMap<Integer,Servicos>();
        this.listaReservas = new TreeMap<Integer,Reserva>();
        this.listaServicosPendentes = new TreeMap<Integer,Servicos>();
        this.listaReservasPorCliente = new TreeMap<Integer,ArrayList<Reserva>>();
    }
    
    /**
     * Adiciona um novo cliente
     */
    
    public void adicionarCliente(Cliente c){
        this.listaClientes.put(c.getNumero(),c.clone());
    }
    
    public boolean pesquisarClientePorNif(int nif){
        for(Cliente c:this.listaClientes.values())
            if(c.getNif() == nif)
                return true;
        return false;
    }
    
    public Cliente obterClientePorNif(int nif){
        if(this.pesquisarClientePorNif(nif))
            for(Cliente c:this.listaClientes.values())
                if(c.getNif() == nif)
                    return c.clone();
        return null;
   }
    /**
     * Adiciona um veiculo 
     */
   public void adicionarVeiculo(Veiculo v){
       this.listaVeiculos.put(v.getMatricula(),v.clone());
    }
    
    /**
       Adicionar um servico Pendente (Ainda nao terminado). 
       Este servico ainda nao esta concluido.
       */
    
   public void adicionarServicoPendente(Servicos s){
       this.listaServicosPendentes.put(s.getNumServicos(),s.clone());
    } 
    
    /**
       Fazer Pedido introduzindo um cliente, um servico e a localizacao do cliente
                    e do destino
       */
    
    public Pedido fazerPedido(Cliente c, Servicos s, GeoLocalizacao r, GeoLocalizacao d){
        Pedido p = new Pedido(c.getNumero(),s.getNumServicos(),r,d);
        return p;
    }
    
    /**
       Atribui um veiculo a um pedido tendo em conta a localizacao do cliente, do veiculo e do
       tipo de servico
       */
    public Veiculo atribuirVeiculoPedido(Pedido p){
        Servicos s = this.listaServicosPendentes.get(p.getNumServicos()).clone();
        Veiculo escolhido = null; 
        double menorDist = Integer.MAX_VALUE;
        double temp = 0;
        for(Veiculo v:this.listaVeiculos.values()){
            if(s.getClass().getSimpleName().equals("TuberEats"))
                if(v instanceof PessoasRefeicoes)
                    temp=this.distEntreLocalizacoes(v.getLocalizacao(),p.getLocalCliente());
            if(s.getClass().getSimpleName().equals("Fixo"))
                if(v instanceof PessoasRefeicoes)
                    temp=this.distEntreLocalizacoes(v.getLocalizacao(),p.getLocalCliente());
            if(s.getClass().getSimpleName().equals("CorreioExpresso"))
                if(v instanceof Mercadorias)
                    temp=this.distEntreLocalizacoes(v.getLocalizacao(),p.getLocalCliente());
            if(temp<menorDist){
                menorDist=temp; 
                escolhido = v;
            }
        }
        return (Veiculo) escolhido.clone();
    }
    
    /**
        Fazer uma reserva.
       */
    public void adicionarReserva(Pedido p){
        Veiculo v = atribuirVeiculoPedido(p);
        Reserva reserva = new Reserva(p.clone(),v.getMatricula());
        this.listaVeiculos.get(v.getMatricula()).setEstadoOcupado();
        this.listaReservas.put(reserva.getNumReserva(),reserva.clone());
    }
    
    
    /**
       Dar por concluido uma viagem que inicialmente foi feita a reserva
       Para tal, recebe o numero da reserva e da por cocluida a reserva e coloca o veiculo atribuido
       no estado livre
       */
    public void terminarViagem(int numReserva){
        this.listaReservas.get(numReserva).setEstadoTerminada(); // terminar a reserva
        Reserva r = this.listaReservas.get(numReserva).clone();
        this.listaVeiculos.get(r.getMatricula()).setEstadoLivre(); // colocar veiculo livre
        Servicos s = this.listaServicosPendentes.get(r.getPedido().getNumServicos()); // obtem o servico pendente associado a reserva
        
        // adicionar o servico na lista de servicos transportaveis no veiculo em questao
        this.listaVeiculos.get(r.getMatricula()).adicionarServicoTransportavel((Transportavel) s.clone()); 
        this.listaServicosRealizados.put(s.getNumServicos(),s.clone());
        
        // adicionar na listaReservasPorCliente Terminadas a correspondete reserva
        int numCliente = r.getPedido().getNumCliente();
        if(this.listaReservasPorCliente.containsKey(numCliente))
            this.listaReservasPorCliente.get(numCliente).add(r.clone());
        else{
            ArrayList<Reserva> temp = new ArrayList<Reserva>();
            temp.add(r.clone());
            this.listaReservasPorCliente.put(numCliente,temp);
        }
    }
    
    
    
    /**
       Número de quilómetros que um veiculo já efetuou ao
       serviço desta empresa;
       */
      public double numerokmVeiculo(String matricula){
          ArrayList<Transportavel> temp = this.listaVeiculos.get(matricula).getServicosTransportaveis();
          double acc = 0;
          Iterator<Transportavel> i = temp.iterator();
          while(i.hasNext())
              acc += ((Servicos)i.next()).getDistancia();
          return acc;
     }
     
     /**
        Dado um cliente listar os serviços contratados por ele
        */
     public List<String> servicosPorCliente(int numCliente){
         int numServico = 0;
         ArrayList<String> temp = new ArrayList<String>(); 
         for(Reserva r:this.listaReservasPorCliente.get(numCliente)){
             numServico = r.getPedido().getNumServicos();
             if(this.listaServicosRealizados.get(numServico) instanceof TuberEats)
                temp.add("TuberEats");
             if(this.listaServicosRealizados.get(numServico) instanceof Fixo)
                temp.add("Fixo");
             if(this.listaServicosRealizados.get(numServico) instanceof CorreioExpresso)
                temp.add("CorreioExpresso");
         }
         return temp;
     }  
       
     /**
        total faturado num dia
        */
       public double totalFaturadoData(int dia, int mes, int ano){
           GregorianCalendar data = new GregorianCalendar(ano, mes-1, dia);
           double total = 0.0;
           for(Veiculo v:this.listaVeiculos.values()){
               ArrayList<Transportavel> temp = v.getServicosTransportaveis();
               Iterator<Transportavel> i = temp.iterator();
               while(i.hasNext())
                    if(data.equals(((Servicos) i.next()).getData()))
                        total += i.next().preco();
           }
           return total;
        }
     
     /**
        Devolve uma lista com os carros que um cliente já usou
        */
       
       public List<String> veiculosPorCliente(int numCliente){
           ArrayList<String> temp = new ArrayList<String>();
           for(Reserva r:this.listaReservasPorCliente.get(numCliente)){
               if(this.listaVeiculos.get(r.getMatricula()) instanceof Mercadorias)
                    temp.add("Mercadorias");
               if(this.listaVeiculos.get(r.getMatricula()) instanceof PessoasRefeicoes)
                    temp.add("PessoasRefeicoes");
            }
            return temp;
       }
        
        
        /**
            Serviço mais usado pelos clientes
        */
    public String servicoMaisUsado(){
        String maior = "";
        int pr=0, te=0, f=0, ce=0;    // pr = PessoasRefeicoes te=TuberEats f=Fixo ce=CorreioExpresso
        for(Veiculo v:this.listaVeiculos.values())
            for(Transportavel t:v.getServicosTransportaveis()){
                if(t instanceof PessoasRefeicoes)    pr++;
                if(t instanceof TuberEats)   te++;
                if(t instanceof Fixo)    f++;
                if(t instanceof CorreioExpresso)     ce++;   
            }
        if(pr>te && pr>f && pr>ce)
            maior="PessoasRefeicoes";
        else
            if(te>pr && te>f && te>ce)
                maior="TuberEats";
            else
                if(f>pr && f>te && f>ce)
                    maior="Fixo";
                else
                    maior="CorreioExpresso";
        return maior;                   
    }
       
    /**
        Criar uma estrutura que relacione tipos de serviços com clientes;
    */
    public Map<String,ArrayList<Integer>> clientesPorServico(){
        HashMap<String,ArrayList<Integer>> lista = new HashMap<String,ArrayList<Integer>>();
        Servicos s = null;
        String servico = null;
        for(Integer numCliente:this.listaReservasPorCliente.keySet()){
            for(Reserva r:this.listaReservasPorCliente.get(numCliente)){
                s = this.listaServicosRealizados.get(r.getPedido().getNumServicos());
                if(s instanceof TuberEats)
                    servico = "TuberEats";
                if(s instanceof Fixo)
                    servico = "Fixo";
                if(s instanceof CorreioExpresso)
                    servico = "CorreioExpresso";
                if(lista.containsKey(servico))
                    lista.get(servico).add(numCliente);
                else{

                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(numCliente);
                    lista.put(servico,temp);
                }
            }
        }
        return lista;
    }                  

    // calculo de localizacao de duas distancias
    public double distEntreLocalizacoes(GeoLocalizacao g1, GeoLocalizacao g2){
        double deltaLongitude = g1.getLongitude()-g2.getLongitude();
        double deltaLatitude = g1.getLatitude()-g2.getLatitude();
        return Math.sqrt(deltaLongitude*deltaLongitude + deltaLatitude*deltaLatitude);
    }

    // Identificar o veículo mais perto de um dado local (uma georreferência)
    public String veiculoMaisPertoLivre(GeoLocalizacao local){
        String perto = null;
        double distMinima = Double.MAX_VALUE;
        double distTemp = 0.0;
        for(Veiculo v:this.listaVeiculos.values()){
            if(v.getEstado()==EstadoVeiculo.LIVRE){
                distTemp = this.distEntreLocalizacoes(local,v.getLocalizacao());
                if(distTemp<distMinima){
                    perto=v.getMatricula();
                    distMinima=distTemp;
                }
            }
        }
        return perto;
    }

}

