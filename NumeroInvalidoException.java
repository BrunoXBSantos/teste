/**
   Classe definidora de uma excessao 
   */

public class NumeroInvalidoException extends Exception{
    public NumeroInvalidoException(){ super(); }
    public NumeroInvalidoException(String s){ super(s); }
}