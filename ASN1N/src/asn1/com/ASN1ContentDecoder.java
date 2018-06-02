package asn1.com;

import asn1.ASN1Data;

/**
 * The ASN1ContentDecoder class is an abstract ASN.1 content decoder class. It's purpose is to decode a
 * given ASN.1 component and return the corresponding string representation. This class must be extended
 * to more specific subclasses in order to implement the {@link #decode} method.
 * 
 * @author Panos Katsikogiannis
 */
public abstract class ASN1ContentDecoder {

	private String[] results 	= null;
	private String result		= null;
	
	/**
	 * Creates a new {@link ASN1ContentDecoder}.
	 * @param results - the possible string results.
	 */
	public ASN1ContentDecoder (String[] results) {
		this.results = results;
	}
	
	/**
	 * Decodes the given ASN.1 data and returns the decoded string representation.
	 * @param contents - the ASN.1 data to be decoded.
	 * @return the decoded string representation.
	 */
	public abstract String decode (ASN1Data contents);
	
	/**
	 * Returns the possible string results.
	 * @return the possible string results.
	 */
	public String[] getResults () {
		return (results);
	}
	
	/**
	 * Sets the decoded string representation to the specific value.
	 * @param result - the specific decoded string representation.
	 */
	protected void setResult (String result) {
		this.result = result;
	}
	
	/**
	 * Returns the decoded string representation.
	 * @return  the decoded string representation.
	 */
	public String getResult () {
		return (result);
	}
}
