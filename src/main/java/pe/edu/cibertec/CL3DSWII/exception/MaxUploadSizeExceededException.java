package pe.edu.cibertec.CL3DSWII.exception;

public class MaxUploadSizeExceededException extends RuntimeException {

    public MaxUploadSizeExceededException(String message){
        super(message);
    }
}
