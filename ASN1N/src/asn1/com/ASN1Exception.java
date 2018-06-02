package asn1.com;

public class ASN1Exception extends Exception {

	
	private static final long serialVersionUID = 1L;

	public ASN1Exception (String message) {
		super("DECODING FAILED: " + message);
	}
}
