package co.edu.uniandes.dse.CarMotor.exceptions;

public class ErrorMessage {
    public static final String VEHICULO_NOT_FOUND = "El vehiculo con el id dado no fue encontrado";
	public static final String ASESOR_NOT_FOUND = "El asesor con el id dado no fue encontrado";
	public static final String ENTIDADBANCARIA_NOT_FOUND = "La entidad bancaria con el id dado no fue encontrada";
	public static final String HORARIOTESTDRIVE_NOT_FOUND = "El horario test drive con el id dado no fue encontrado";
	public static final String IMAGEN_NOT_FOUND = "La imagen con el id dado no fue encontrada";
	public static final String SEDE_NOT_FOUND = "La sede con el id dado no fue encontrada";

	private ErrorMessage() {
		throw new IllegalStateException("Utility class");
	}
    
}
