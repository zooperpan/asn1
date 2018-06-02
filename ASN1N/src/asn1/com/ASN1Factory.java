package asn1.com;

import asn1.ASN1Component;
import asn1.ASN1CustomComponent;
import asn1.ASN1Data;
import asn1.ASN1Decoder;
import asn1.component.ASN1BitString;
import asn1.component.ASN1Boolean;
import asn1.component.ASN1Choice;
import asn1.component.ASN1Enumerated;
import asn1.component.ASN1IA5String;
import asn1.component.ASN1Integer;
import asn1.component.ASN1Null;
import asn1.component.ASN1NumericString;
import asn1.component.ASN1ObjectID;
import asn1.component.ASN1OctetString;
import asn1.component.ASN1Sequence;
import asn1.component.ASN1Set;
import asn1.component.ASN1VisibleString;

public class ASN1Factory implements ASN1Definitions {

	public static ASN1Component getComponent (ASN1Data asn1Data, ASN1CustomComponent table[]) throws ASN1Exception {
		
		ASN1Component subComponent = null;
		for (int i = 0; i < table.length; i++) {
			switch (table[i].getTagType()) {
			case BOOLEAN_TYPE:
				subComponent = ASN1Component.retrieveComponent(asn1Data);
				if (subComponent != null && subComponent.getTag() == table[i].getTag()) {
					ASN1Boolean asn1Boolean = new ASN1Boolean(subComponent.getAllASN1Data(), table[i].getComponents(), 
							table[i].getTagName(), table[i].getContentDecoder());
					return asn1Boolean;
				}
				break;
			case INT_TYPE:
				subComponent = ASN1Component.retrieveComponent(asn1Data);
				if (subComponent != null && subComponent.getTag() == table[i].getTag()) {
					ASN1Integer asn1Integer = new ASN1Integer(subComponent.getAllASN1Data(), table[i].getComponents(), 
							table[i].getTagName(), table[i].getContentDecoder());
					return asn1Integer;
				}
				break;
			case BIT_STR_TYPE:
				subComponent = ASN1Component.retrieveComponent(asn1Data);
				if (subComponent != null && subComponent.getTag() == table[i].getTag()) {
					ASN1BitString asn1BitString = new ASN1BitString(subComponent.getAllASN1Data(), table[i].getComponents(), 
							table[i].getTagName(), table[i].getContentDecoder());
					return asn1BitString;
				}
				break;
			case OCTET_STR_TYPE:
				subComponent = ASN1Component.retrieveComponent(asn1Data);
				if (subComponent != null && subComponent.getTag() == table[i].getTag()) {
					ASN1OctetString asn1OctetString =  new ASN1OctetString(subComponent.getAllASN1Data(), table[i].getComponents(), 
							table[i].getTagName(), table[i].getContentDecoder());
					return asn1OctetString;
				}
				break;
			case NULL_TYPE:
				subComponent = ASN1Component.retrieveComponent(asn1Data);
				if (subComponent != null && subComponent.getTag() == table[i].getTag()) {
					ASN1Null ans1Null = new ASN1Null(subComponent.getAllASN1Data(), table[i].getComponents(), 
							table[i].getTagName(), table[i].getContentDecoder());
					return ans1Null;
				}
				break;
			case OBJ_ID_TYPE:
				subComponent = ASN1Component.retrieveComponent(asn1Data);
				if (subComponent != null && subComponent.getTag() == table[i].getTag()) {
					ASN1ObjectID asn1ObjectID = new ASN1ObjectID(subComponent.getAllASN1Data(), table[i].getComponents(), 
							table[i].getTagName(), table[i].getContentDecoder());
					return asn1ObjectID;
				}
				break;
			case ENUM_TYPE:
				subComponent = ASN1Component.retrieveComponent(asn1Data);
				if (subComponent != null && subComponent.getTag() == table[i].getTag()) {
					ASN1Enumerated asn1Enumerated = new ASN1Enumerated(subComponent.getAllASN1Data(), table[i].getComponents(), 
							table[i].getTagName(), table[i].getContentDecoder());
					return asn1Enumerated;
				}
				break;
			case SEQ_TYPE:
				subComponent = ASN1Component.retrieveComponent(asn1Data);
				if (subComponent != null && subComponent.getTag() == table[i].getTag()) {
					ASN1Sequence asn1Sequence = new ASN1Sequence(subComponent.getAllASN1Data(), table[i].getComponents(), 
							table[i].getTagName(), table[i].getContentDecoder());
					return asn1Sequence;
				}
				break;
			case SET_TYPE:
				subComponent = ASN1Component.retrieveComponent(asn1Data);
				if (subComponent != null && subComponent.getTag() == table[i].getTag()) {
					ASN1Set asn1Set = new ASN1Set(subComponent.getAllASN1Data(), table[i].getComponents(), 
							table[i].getTagName(), table[i].getContentDecoder());
					return asn1Set;
				}
				break;
			case NUM_STR_TYPE:
				subComponent = ASN1Component.retrieveComponent(asn1Data);
				if (subComponent != null && subComponent.getTag() == table[i].getTag()) {
					ASN1NumericString asn1NumericString = new ASN1NumericString(subComponent.getAllASN1Data(), table[i].getComponents(), 
							table[i].getTagName(), table[i].getContentDecoder());
					return asn1NumericString;
				}
				break;
			case IA5_STR_TYPE:
				subComponent = ASN1Component.retrieveComponent(asn1Data);
				if (subComponent != null && subComponent.getTag() == table[i].getTag()) {
					ASN1IA5String asn1IA5String = new ASN1IA5String(subComponent.getAllASN1Data(), table[i].getComponents(), 
							table[i].getTagName(), table[i].getContentDecoder());
					return asn1IA5String;
				}
				break;
			case VIS_STR_TYPE:
				subComponent = ASN1Component.retrieveComponent(asn1Data);
				if (subComponent != null && subComponent.getTag() == table[i].getTag()) {
					ASN1VisibleString asn1VisibleString = new ASN1VisibleString(subComponent.getAllASN1Data(), table[i].getComponents(), 
							table[i].getTagName(), table[i].getContentDecoder());
					return asn1VisibleString;
				}
				break;
			case CHOICE_TYPE:
				ASN1Decoder decoder = new ASN1Decoder(ASN1Component.getFirstComponent(asn1Data), table[i].getComponents());
				if (decoder.getAll().size() > 0) {
					ASN1Choice asn1Choice = new ASN1Choice(decoder.getAll().get(0),  table[i].getComponents(),
							table[i].getTagName(), table[i].getContentDecoder());
					return asn1Choice;
				}
				break;
			default:
				break;
			}
		}
		return null;
	}
}
