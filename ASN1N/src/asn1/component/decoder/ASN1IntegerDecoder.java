package asn1.component.decoder;

import asn1.ASN1Data;
import asn1.com.ASN1ContentDecoder;

public class ASN1IntegerDecoder extends ASN1ContentDecoder {

	long[] values = null;
	
	public ASN1IntegerDecoder (String[] results, long[] values) {
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
