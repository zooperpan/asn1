package protocols.etsi;

import protocols.common.Header;
import asn1.ASN1Component;
import asn1.ASN1Data;
import asn1.com.ASN1Exception;
import asn1.com.ASN1Factory;
import asn1.com.ASN1Message;
import asn1.com.ASN1Static;

public class ETSIMessage extends ASN1Message implements Header {

	/**
	 * Creates a new {@link ETSIMessage}.
	 * @param asn1Data - the ASN.1 data that the message holds.
	 * @throws ASN1Exception in case the decoding fails.
	 */
	public ETSIMessage (ASN1Data asn1Data) throws ASN1Exception {
		super(asn1Data);
	}

	/**
	 * Decodes the given ASN.1 data.
	 * @throws ASN1Exception in case the decoding fails.
	 */
	protected void decodeMessage (ASN1Data asn1Data) throws ASN1Exception {
		int length = asn1Data.getDataLength();
		ASN1Component asn1Component = null;
		ASN1Component reference = null;
		
		
		// Network Facility Extensions
		asn1Component = ASN1Factory.getComponent(asn1Data, NetworkFacilityExtensions);
		if (asn1Component != null) {
			components.add(asn1Component);
			length -= asn1Component.getDataLength();
			asn1Data = new ASN1Data(asn1Data.getDataFromIndex(asn1Component.getDataLength()));
		}
		if (length <= 0) return;
		
		// Interpretation APDU
		asn1Component = ASN1Factory.getComponent(asn1Data, InterpretationAPDU);
		if (asn1Component != null) {
			this.components.add(asn1Component);
			length -= asn1Component.getDataLength();
			asn1Data = new ASN1Data(asn1Data.getDataFromIndex(asn1Component.getDataLength()));
		}
		if (length <= 0) return;
		
		while (length > 0) {
			ASN1Static.setReferenceComponent(null);
			reference = null;
			asn1Component = ASN1Factory.getComponent(asn1Data, RoseAPDU);
			if (asn1Component != null) {
				// Check for invoke, return result, error or reject
				switch (asn1Component.getTag()) {
				case ASN1_T_INVOKE_PDU:
					reference = ASN1Static.getComponentByName(asn1Component.getComponents(), "operationValue");
					ASN1Static.setReferenceComponent(OperationDecoder.getInvokeTable(reference));
					break;
				case ASN1_T_RETURN_RESULT_PDU:
					reference = ASN1Static.getComponentByName(asn1Component.getComponents(), "operationValue");
					ASN1Static.setReferenceComponent(OperationDecoder.getReturnResultTable(reference));
					break;
				case ASN1_T_RETURN_ERROR_PDU:
				case ASN1_T_REJECT_PDU:
				default:
					break;
				}
				asn1Component = ASN1Factory.getComponent(asn1Data, RoseAPDU);
				this.components.add(asn1Component);
				length -= asn1Component.getDataLength();
				asn1Data = new ASN1Data(asn1Data.getDataFromIndex(asn1Component.getDataLength()));
			} else throw new ASN1Exception("Wrong ROSE APDU included!");
		}
	}
}
