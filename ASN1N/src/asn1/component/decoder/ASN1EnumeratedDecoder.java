package asn1.component.decoder;

import asn1.ASN1Data;
import asn1.com.ASN1ContentDecoder;

/**
 * The ASN1EnumeratedDecoder class is an ASN.1 Enumerated decoder. It's purpose is to decode an
 * enumerated ASN.1 component and return it's string representation. It extends the {@link ASN1ContentDecoder}
 * class.
 * 
 * @author Panos Katsikogiannis
 */
public class ASN1EnumeratedDecoder extends ASN1ContentDecoder {

	long[] values = null;
	
	/**
	 * Creates a new {@link ASN1EnumeratedDecoder}.
	 * @param results - the possible string results.
	 * @param values - the corresponding values of each string result.
	 */
	public ASN1EnumeratedDecoder (String[] results, long[] values) {
		super(results);
		this.values = values;
	}
	
	/**
	 * Decodes the given ASN.1 data and returns the decoded string representation.
	 */
	public String decode (ASN1Data contents) {
		int length = contents.getDataLength();
		long value = 0;
		String result = null;
		// Get the value from the ASN1 data
		for (int i = 0; i < length; i++) {
			value += contents.getDataAtIndex(i) * Math.pow(16, 2 * (length - (i + 1)));
		}
		// Find the corresponding string
		for (int i = 0; i < values.length; i++) {
			if (value == values[i]) {
				result = getResults()[i];
				break;
			}
		}
		// Set the result and return it
		setResult(result);
		return result;
	}
}
