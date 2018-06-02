package asn1.component.decoder;

import asn1.ASN1Data;
import asn1.DataContainer;
import asn1.com.ASN1ContentDecoder;

/**
 * The ASN1OctetStringDecoder class is an abstract ASN.1 octet string decoder. It's purpose is to decode an
 * octet string ASN.1 component and return it's string/DataContainer representation. It extends the 
 * {@link ASN1ContentDecoder} class. This calls must be extended to more specific octet string decoders
 * in order to implement the {@link ASN1OctetStringDecoder#decodeData decodeData} method.
 * 
 * @author Panos Katsikogiannis
 */
public abstract class ASN1OctetStringDecoder extends ASN1ContentDecoder {

	/**
	 * Creates a new {@link ASN1OctetStringDecoder}.
	 */
	public ASN1OctetStringDecoder () {
		super(null);
	}

	/**
	 * Decodes the given ASN.1 data and returns the decoded string representation.
	 */
	public String decode (ASN1Data contents) {
		return "";
	}
	
	/**
	 * Decodes the given ASN.1 data and returns the decoded DataContainer.
	 * @param contents - the ASN.1 data to be decoded.
	 * @return the decoded DataContainer.
	 */
	public abstract DataContainer decodeData (ASN1Data contents);
}
