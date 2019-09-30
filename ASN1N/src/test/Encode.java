package test;

import asn1.ASN1Data;
import asn1.com.ASN1DefaultMessage;
import asn1.com.ASN1Message;
import asn1.component.ASN1BitString;
import asn1.component.ASN1Boolean;
import asn1.component.ASN1IA5String;
import asn1.component.ASN1Integer;
import asn1.component.ASN1Null;
import asn1.component.ASN1NumericString;
import asn1.component.ASN1ObjectID;
import asn1.component.ASN1OctetString;
import asn1.component.ASN1Sequence;

public class Encode {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		int val[] = {1, 3, 12, 2, 1107, 2, 1, 92};
		ASN1Data tempData = new ASN1Data();
		// Create the objectID component
		ASN1ObjectID asn1ObjectID = new ASN1ObjectID(val, "OBJECTID");
		asn1ObjectID.writeComponent(tempData);
		// Create the bitString component
		short val2[] = {1, 3, 0xC0};
		ASN1BitString asn1BitString = new ASN1BitString((short) 3, val2, "BITSTRING");
		asn1BitString.writeComponent(tempData);
		// Create the null component
		ASN1Null asn1null = new ASN1Null("NULL");
		asn1null.writeComponent(tempData);
		// Create the octetString component
		ASN1OctetString asn1OctetString = new ASN1OctetString("0195fe", "OCTETSTRING");
		asn1OctetString.writeComponent(tempData);
		// Create the boolean component
		ASN1Boolean asn1Boolean = new ASN1Boolean(false, "BOOLEAN");
		asn1Boolean.writeComponent(tempData);
		// Create the integer component
		ASN1Integer asn1Integer = new ASN1Integer(72913, "INTEGER");
		asn1Integer.writeComponent(tempData);
		// Create the IA5String component
		ASN1IA5String asn1IA5String = new ASN1IA5String("Panos", "IA5STRING");
		asn1IA5String.writeComponent(tempData);
		// Create the numericString component
		ASN1NumericString asn1NumericString = new ASN1NumericString("01234567890" +
//				"12345678901234567890123456789" +
//				"01234567890123456789012345678901234567890123456789" +
//				"01234567890123456789012345678901234567890123456789" +
//				"01234567890123456789012345678901234567890123456789" +
//				"01234567890123456789012345678901234567890123456789" +
//				"01234567890123456789012345678901234567890123456789" +
//				"01234567890123456789012345678901234567890123456789" +
//				"01234567890123456789012345678901234567890123456789" +
//				"01234567890123456789012345678901234567890123456789" +
//				"01234567890123456789012345678901234567890123456789" +
//				"01234567890123456789012345678901234567890123456789" +
				"01234567890123456789012345678901234567890123456789", "NUMERICSTRING");
		asn1NumericString.writeComponent(tempData);
		// Create the sequence component
		ASN1Sequence asn1Sequence = new ASN1Sequence(tempData.getAllData(), "SEQUENCE");
		tempData = new ASN1Data();
		asn1Sequence.writeComponent(tempData);
		System.out.println("ASN.1 Data = " + asn1Sequence.getAllASN1Data().toString());
		
		
		ASN1Data asn1Data = new ASN1Data(asn1Sequence.getAllASN1Data().toString());
		ASN1Message message;
		try {
			message = new ASN1DefaultMessage(asn1Data);
			message.printMessage();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
