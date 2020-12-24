import java.util.*;
import java.util.Scanner;
import java.io.IOException;
import static java.lang.System.out;

public class Principal{
    private static void menu() { // menu principal
            out.println("\n\n### TUBER - Serviços de Transporte ###");
            out.println("\n                  ===========================================================");
            out.println("                  |      1 - Adicionar Cliente                                 |");
            out.println("                  |      2 - Adicionar Veiculo                                 |");
            out.println("                  |      3 - Fazer Reserva                                     |");
            out.println("                  |      4 - Ver estado de um Veiculo                          |");
            out.println("                  |      5 - Numero de Quilometros efetuados por um Veiculo    |");
            out.println("                  |      6 - Lista Servicos por Cliente                        |");
            out.println("                  |      8 - Total faturado num dia                            |");
            out.println("                  |      9 - Lista de carros por Cliente                       |");
            out.println("                  |     10 - Servico mais requisitado                          |");            
            out.println("                  |     11 - Lista de Clientes por Servico                     |");            
            out.println("                  |     12 - Veiculo mais perto de um dado local               |");            
            out.println("                  |     7 - Total faturado num dia                             |");            
            out.println("                  |     7 - Total faturado num dia                             |");            
            out.println("                  |     7 - Total faturado num dia                             |");            
            out.println("                  =============================================================\n");
    }

    public static void menuServicos(){
        out.println("\nServicos atualmente disponibilizados:");
        out.println("                  |      1 - Tuber Eats            |");
        out.println("                  |      2 - Fixo                  |");
        out.println("                  |      2 - Correio Expresso      |");
    }

    /**
        Permite adicionar um cliente
    */
    public static void adicionarCliente(Empresa emp){
        Scanner input = new Scanner(System.in);
        String nome = null;
        int nif = 0;
        System.out.println("Introduza:");
        out.print("\tNome: ");
        nome = input.nextLine();
        out.print("\tNIF: "); 
        nif=input.nextInt();       
        emp.adicionarCliente(new Cliente(nome,nif));
        out.println("Cliente adicionado com Sucesso!\n");
    }
    
    public static int introduzirNumero(int minimo, int maximo) throws NumeroInvalidoException{
        Scanner input = new Scanner(System.in);
        boolean ok = false;
        String lixo = "";
        int opcao = 0;
        while(!ok){
            out.print("Opcao -> ");
            try{
                opcao = input.nextInt();
                if(opcao < minimo || opcao > maximo)
                    throw new NumeroInvalidoException("Numero introduzida fora dos limites");
                ok = true;
            }
            catch(InputMismatchException e){
                out.println("Opcao inválido");
                lixo = input.nextLine();
            }
        }
        input.close();
        return opcao;      
    } 

    public static void adicionarVeiculo(Empresa emp, Scanner input, GeoLocalizacao localEmpresa){
        String lixo = "";
        int opcao = 0;
        String matricula = null;
        int capacidade = 0;
        boolean ok = false;
        out.println("\nIntroduza o tipo de Veiculo a adicionar:");
        out.println("                  |      1 - Pessoas e Refeicoes    |");
        out.println("                  |      2 - Mercadorias            |"); 
        do{
            try{
                opcao = introduzirNumero(1,2);
                ok=true;
            }
            catch(NumeroInvalidoException e){
                out.println(e.getMessage());
            }
        }
        while(!ok);
        input.nextLine();
        out.println("Introduza: ");
        out.print("\tMatricula: ");
        matricula = input.nextLine();
        out.print("\tCapacidade: ");
        capacidade = input.nextInt();
        if(opcao==1)
            emp.adicionarVeiculo(new PessoasRefeicoes(matricula,capacidade,localEmpresa));
        if(opcao==2)
            emp.adicionarVeiculo(new Mercadorias(matricula,capacidade,localEmpresa));
        out.println("Veiculo adicionado com sucesso!\n");
    }   

    public static GeoLocalizacao introduzirLocalizacao(){
        Scanner input = new Scanner(System.in);
        boolean flag = false;
        String linha = "";
        double longitude = 0.0;
        double latitude = 0.0;
        do{
            out.print("\tFormato localizacao (x, y): ");
            linha = input.nextLine();
            String[] campos = linha.split(","); 
            if(campos.length == 2){
                try{
                    longitude = Double.parseDouble(campos[0].trim());
                    latitude = Double.parseDouble(campos[1].trim());
                    flag = true;
                }
                catch(NumberFormatException e) { return null; }
            }
            else
                out.println("Formato de localizacao incorreto!");
        }
        while(!flag);
        input.close();
        return new GeoLocalizacao(longitude,latitude);
    }

    public static Pedido fazerPedido(Empresa tuber){
        Scanner input = new Scanner(System.in);
        int opcao = 0;
        int nif = 0;
        Cliente c = null;
        boolean existeCliente = false;
        GeoLocalizacao localCliente = null;
        GeoLocalizacao localDestino = null;
        GregorianCalendar hoje = new GregorianCalendar();
        double distancia = 0.0;
        int numTransportavel = 0;
        Servicos s = null;
        menuServicos();
        out.print("Introduza o nif: ");
        nif=input.nextInt();
        existeCliente = tuber.pesquisarClientePorNif(nif);
        if(existeCliente){
            out.println("Cliente já possui ficha registada na empresa");
            c = tuber.obterClientePorNif(nif);
        }
        else{
            adicionarCliente(tuber);
            c = tuber.obterClientePorNif(nif);
        }
        out.print("Intoduza a localizacao de recolha: ");
        localCliente = introduzirLocalizacao();
        out.print("Intoduza a localizacao de Destino: ");
        localDestino = introduzirLocalizacao();
        distancia = tuber.distEntreLocalizacoes(localCliente,localDestino);
        out.print("Introduza o servico que pretende: ");
        opcao = input.nextInt();
        out.print("Introduza o numero de quantidade a transportar: ");
        numTransportavel = input.nextInt();
        if(opcao==1){
            out.print("Introduza o valor da refeicao: ");
            double vr = input.nextDouble();
            s = new TuberEats(vr,distancia,numTransportavel,0.04,hoje);
        }
        if(opcao==2){
            s = new Fixo(distancia,numTransportavel,0.04,hoje);
        }
        if(opcao==1){
            out.print("Introduza o peso do transporte: ");
            double p = input.nextDouble();
            s = new TuberEats(p,distancia,numTransportavel,0.04,hoje); 
        }
        tuber.adicionarServicoPendente(s);
        return tuber.fazerPedido(c,s,localCliente,localDestino);
    }
    
    public static void fazerReserva(Empresa tuber){
        tuber.adicionarReserva(fazerPedido(tuber));
        out.println("Veiculo atribuido!");
        out.println("Reserva efetuada com sucesso!");
    }

    public static void espera(Scanner input){
        out.print("Prima qualquer tecla para voltar para o menu: ");
        String pausa = input.next();
    }

    public final static void clearConsole()
{
    try
    {
        Runtime.getRuntime().exec("cls");
    }
    catch (final Exception e)
    {
        ;
    }
}

    public static void main(String[] args) {
        int opcao;
        Scanner input = new Scanner(System.in);
        Empresa tuber = new Empresa("Tuber");
        GeoLocalizacao localEmpresa = new GeoLocalizacao(45.0,63.1);
        
        do{
            menu();
            out.print("Opcao -> ");
            opcao = input.nextInt();
            
            switch(opcao){
            case 1:
                adicionarCliente(tuber);
                espera(input);
                break;
                
            case 2:
                adicionarVeiculo(tuber,input,localEmpresa.clone());
                espera(input);
                clearConsole();
                break;
                
            case 3:
                fazerReserva(tuber);
                espera(input);
                break;
                
            case 4:
                ;
                break;
            
            default:
                System.out.println("Opção inválida.");
            }
        } while(opcao != 0);
    }
}