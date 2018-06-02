package asn1.component.decoder;

import asn1.ASN1Data;
import asn1.com.ASN1ContentDecoder;

/**
 * The ASN1BooleanDecoder class is an ASN.1 Boolean decoder. It's purpose is to decode a boolean ASN.1 
 * component and return it's string representation. It extends the {@link ASN1ContentDecoder} class.
 * 
 * @author Panos Katsikogiannis
 */
public class ASN1BooleanDecoder extends ASN1ContentDecoder {

	/**
	 * Creates a new {@link ASN1BooleanDecoder}.
	 * @param results - the possible string results.
	 */
	public ASN1BooleanDecoder(String[] results) {
		super(results);
	}

	/**
	 * Decodes the given ASN.1 data and returns the decoded string representation.
	 */
	public String decode(ASN1Data contents) {
		
		boolean value = contents.getDataAtIndex(0) == 0 ? false : true;
		String result;
		if (value) {
			result = getResults()[1];
		} else {
			result = getResults()[0];
		}
		setResult(result);
		return result;
	}
}
