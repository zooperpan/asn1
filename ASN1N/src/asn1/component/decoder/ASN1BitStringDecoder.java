package asn1.component.decoder;

import asn1.ASN1Data;
import asn1.com.ASN1ContentDecoder;


/**
 * The ASN1BitStringDecoder class is an ASN.1 BitString decoder. It's purpose is to decode a
 * bitString ASN.1 component and return it's string representation. It extends the {@link ASN1ContentDecoder}
 * class.
 * 
 * @author Panos Katsikogiannis
 */
public class ASN1BitStringDecoder extends ASN1ContentDecoder {

	short[] bits = null;
	
	/**
	 * Creates a new {@link ASN1BitStringDecoder}.
	 * @param results - the possible string results.
	 * @param bits - the corresponding bits of each string result.
	 */
	public ASN1BitStringDecoder (String[] results, short bits[]) {
		super(results);
		this.bits = bits;
	}

	/**
	 * Decodes the given ASN.1 data and returns the decoded string representation.
	 */
	public String decode (ASN1Data contents) {
		String result = "";
		for (int j = 7; j >= 0 ; j--) {
			if ((contents.getDataAtIndex(0) & (1 << j)) == (1 << j)) {
				result = getStringForBit( 8 - (j + 1));
				break;
			}
		}
		setResult(result);
		return result;
	}
	
	/**
	 * Returns the string representation of the given bit.
	 * @param checkBit - the bit to be decoded (e.g: zero (0) means the 1st bit counting from the MSB). 
	 * @return a string representation.
	 */
	private String getStringForBit (int checkBit) {
		String result = "unknown value";
		for (int i = 0; i < bits.length; i++) {
			if (bits[i] == checkBit) {
				result = getResults()[i];
				break;
			}
		}
		return result;
	}
}
