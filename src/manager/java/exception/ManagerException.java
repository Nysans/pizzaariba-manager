package manager.java.exception;

public class ManagerException extends Exception {
	private static final long serialVersionUID = 1L;

	public ManagerException() {
		super("Nenhuma mensagem foi especificada");
	}

	public ManagerException(String msg) {
		super(msg);
	}
}