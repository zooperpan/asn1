package asn1.com;

import asn1.ASN1Component;
import asn1.ASN1Data;
import protocols.common.Header;

public class ASN1DefaultMessage extends ASN1Message implements Header {
	
	/**
	 * Creates a new {@link ASN1DefaultMessage}.
	 * @param asn1Data - the ASN.1 data that the message holds.
	 * @throws ASN1Exception in case the decoding fails.
	 */
	public ASN1DefaultMessage (ASN1Data asn1Data) throws ASN1Exception {
		super(asn1Data);
	}

	/**
	 * Decodes the given ASN.1 data.
	 * @throws ASN1Exception in case the decoding fails.
	 */
	protected void decodeMessage (ASN1Data asn1Data) throws ASN1Exception {
		int length = asn1Data.getDataLength();
		ASN1Component asn1Component = null;
		
		
		while (length > 0) {
			asn1Component = ASN1Factory.getComponent(asn1Data);
			this.components.add(asn1Component);
			length -= asn1Component.getDataLength();
			asn1Data = new ASN1Data(asn1Data.getDataFromIndex(asn1Component.getDataLength()));
		}
	}
}
