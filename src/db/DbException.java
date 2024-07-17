package db;

public class DbException extends RuntimeException {

	private static final long serialVersionUID = 1L; // aqui eu configuro a exceção, caso aconteça
	
	public DbException(String msg) {
		super(msg);
	}
	

}
