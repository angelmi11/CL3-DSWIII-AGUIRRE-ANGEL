package pe.edu.cibertec.CL3DSWII.exception;

public class BadCredentialsException extends RuntimeException  {
    public BadCredentialsException (String message){
        super(message);
    }
}
