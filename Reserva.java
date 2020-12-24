/***
 * class Reserva
 * Faz uma reserva, atribuindo um veiculo a um dado pedido
 */

public class Reserva{
    // variaveis de classe
    private static int numReservasTotal = 0;          // numero de reservas e sequencial
    
    // metodos de classe
    public static int getNumReservasTotal(){ return Reserva.numReservasTotal; }
    public static void decNumReservasTotal(){ --(Reserva.numReservasTotal); }
    
    // variaveis de instancia
    private int numReserva;   // numero da reserva
    private Pedido pedido;    // pedido efetuado
    private String matricula; // matricula do veiculo atribuida
    private EstadoReserva estado;   // estado da reserva
    
    // construtores
    public Reserva(Pedido p, String m){
        this.pedido = p.clone();
        this.matricula = m;
        this.numReserva = Reserva.numReservasTotal++;
        this.estado = EstadoReserva.CURSO;
    }
    
    public int getNumReserva(){ return this.numReserva; }
    public Pedido getPedido(){ return this.pedido.clone(); }
    public String getMatricula(){ return this.matricula; }
    public EstadoReserva getEstado(){ return this.estado; }
    
    public void setNumReserva(int n){ this.numReserva=n; }
    public void setEstadoTerminada(){ this.estado = EstadoReserva.TERMINADA; }
    public void setEstadoCurso() { this.estado = EstadoReserva.CURSO; }
    
    public Reserva clone(){
        Reserva r = new Reserva(this.getPedido(), this.getMatricula());
        r.setNumReserva(this.getNumReserva());
        Reserva.decNumReservasTotal();
        return r;
    }
}