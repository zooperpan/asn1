package asn1;

import java.util.ArrayList;

import asn1.com.ASN1Definitions;
import asn1.com.ASN1Exception;
import asn1.com.ASN1Static;
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

public class ASN1Decoder implements ASN1Definitions {

	private ArrayList<ASN1Component> components = new ArrayList<ASN1Component>();
	private ASN1Data asn1Data					= null;
	
	/**
	 * Creates a new decoder in order to analyze the given ASN.1 data.
	 * @param asn1Data - the given ASN.1 data.
	 */
	public ASN1Decoder (ASN1Data asn1Data) {
		this.asn1Data = asn1Data;
		analyze();
	}
	
	/**
	 * Creates a new decoder in order to analyze the given ASN.1 data using a specific table 
	 * of components that could be included in the data.
	 * @param asn1Data - the given ASN.1 data.
	 * @param table - the table of components that could be included in the data.
	 * @throws ASN1Exception
	 */
	public ASN1Decoder (ASN1Data asn1Data, ASN1CustomComponent table[]) throws ASN1Exception {
		this.asn1Data = asn1Data;
		if (table != null) analyze(table);
	}
	
	/**
	 * Performs an analysis of all ASN.1 data held by this decoder
	 */
	private void analyze () {
		ASN1Data subData 	= new ASN1Data(asn1Data.toString());
		long subLength 		= 0;
		long totalLength 	= asn1Data.getDataLength();
		
		while (totalLength > 0) {
			subLength 	= decode(subData);
			totalLength -= subLength;
			subData 	= subData.subData(subData, subLength);
		}
	}
	
	/**
	 * Performs an analysis of all ASN.1 data held by this decoder using a specific table
	 * of components that could be included in the data.
	 * @param table - the table of components that could be included in the data.
	 * @throws ASN1Exception
	 */
	private void analyze (ASN1CustomComponent table[]) throws ASN1Exception {
		
		ASN1Data subData 	= asn1Data;
		long subLength 		= 0;
		long totalLength 	= asn1Data.getDataLength();
		
//		while (totalLength > 0) {
			for (int i = 0; (i < table.length) && (totalLength > 0); i++) {
				subLength = decode(subData, table[i]);
				totalLength -= subLength;
				if (subLength > 0) subData = subData.subData(subData, subLength);
			}
//		}
	}
	
	/**
	 * Decodes the first component found in the given ASN.1 data. The component is added 
	 * in the component list of the decoder.
	 * @param data - the ASN.1 data that contains a component to be decoded.
	 * @return the number of data that have been decoded.
	 */
	private long decode (ASN1Data data) {
		
		short tag 	= 0;
		long length = 0;
		
		ASN1Component subComponent = ASN1Component.retrieveComponent(data);
		tag = subComponent.getTag();
		
		switch (tag) {
		case ASN1_TAG_BOOLEAN:
			ASN1Boolean ans1Boolean = new ASN1Boolean(subComponent.getAllASN1Data(), "BOOLEAN");
			this.components.add(ans1Boolean);
			length = 1 + ans1Boolean.getLengthBytes() + ans1Boolean.getLength();
			break;
		case ASN1_TAG_INT:
			ASN1Integer asn1Integer = new ASN1Integer(subComponent.getAllASN1Data(), "INTEGER");
			this.components.add(asn1Integer);
			length = 1 + asn1Integer.getLengthBytes() + asn1Integer.getLength();
			break;
		case ASN1_TAG_BIT_STR:
		case ASN1_TAG_BIT_STR | CONSTRUCTED:
			ASN1BitString asn1BitString = new ASN1BitString(subComponent.getAllASN1Data(), "BITSTRING");
			this.components.add(asn1BitString);
			length = 1 + asn1BitString.getLengthBytes() + asn1BitString.getLength();
			break;
		case ASN1_TAG_OCTET_STR:
		case ASN1_TAG_OCTET_STR | CONSTRUCTED:
			ASN1OctetString asn1OctetString = new ASN1OctetString(subComponent.getAllASN1Data(), "OCTET STRING");
			this.components.add(asn1OctetString);
			length = 1 + asn1OctetString.getLengthBytes() + asn1OctetString.getLength();
			break;
		case ASN1_TAG_NULL:
			ASN1Null asn1Null = new ASN1Null(subComponent.getAllASN1Data(), "NULL");
			this.components.add(asn1Null);
			length = 1 + asn1Null.getLengthBytes() + asn1Null.getLength();
			break;
		case ASN1_TAG_OBJ_ID:
			ASN1ObjectID asn1ObjectID = new ASN1ObjectID(subComponent.getAllASN1Data(), "OBJECT ID");
			this.components.add(asn1ObjectID);
			length = 1 + asn1ObjectID.getLengthBytes() + asn1ObjectID.getLength();
			break;
		case ASN1_TAG_ENUM:
			ASN1Enumerated asn1Enumerated = new ASN1Enumerated(subComponent.getAllASN1Data(), "ENUMERATED");
			this.components.add(asn1Enumerated);
			length = 1 + asn1Enumerated.getLengthBytes() + asn1Enumerated.getLength();
			break;
		case ASN1_TAG_SEQ | CONSTRUCTED:
			ASN1Sequence asn1Sequence = new ASN1Sequence(subComponent.getAllASN1Data(), "SEQUENCE");
			this.components.add(asn1Sequence);
			length = 1 + asn1Sequence.getLengthBytes() + asn1Sequence.getLength();
			break;
		case ASN1_TAG_SET | CONSTRUCTED:
			ASN1Set asn1Set = new ASN1Set(subComponent.getAllASN1Data(), "SET");
			this.components.add(asn1Set);
			length = 1 + asn1Set.getLengthBytes() + asn1Set.getLength();
			break;
		case ASN1_TAG_NUM_STR:
		case ASN1_TAG_NUM_STR | CONSTRUCTED:
			ASN1NumericString asn1NumericString = new ASN1NumericString(subComponent.getAllASN1Data(), "NUMERIC STRING");
			this.components.add(asn1NumericString);
			length = 1 + asn1NumericString.getLengthBytes() + asn1NumericString.getLength();
			break;
		case ASN1_TAG_IA5_STR:
		case ASN1_TAG_IA5_STR | CONSTRUCTED:
			ASN1IA5String asn1IA5String = new ASN1IA5String(subComponent.getAllASN1Data(), "IA5 STRING");
			this.components.add(asn1IA5String);
			length = 1 + asn1IA5String.getLengthBytes() + asn1IA5String.getLength();
			break;
		case ASN1_TAG_VIS_STR:
		case ASN1_TAG_VIS_STR | CONSTRUCTED:
			ASN1VisibleString asn1VisibleString = new ASN1VisibleString(subComponent.getAllASN1Data(), "VISIBLE STRING");
			this.components.add(asn1VisibleString);
			length = 1 + asn1VisibleString.getLengthBytes() + asn1VisibleString.getLength();
			break;
		default:
			switch(tag & MASK_CLASS) {
			case APPLICATION:
				subComponent.setName("[APPLICATION " + (tag & MASK_ASN1_TAG) + "]");
				break;
			case CONTEXT:
				subComponent.setName("[CONTEXT " + (tag & MASK_ASN1_TAG) + "]");
				break;
			case PRIVATE:
				subComponent.setName("[PRIVATE " + (tag & MASK_ASN1_TAG) + "]");
				break;
			default: break;
			}
			this.components.add(subComponent);
			length = 1 + subComponent.getLengthBytes() + subComponent.getLength();
			break;
		}
		return (length);
	}
	
	
	private long decode (ASN1Data data, ASN1CustomComponent expectedComponent) throws ASN1Exception {
		
		ASN1Component subComponent 	= null;
		int tagType 				= NONE_TYPE;
		long length 				= 0;
		
		tagType = expectedComponent.getTagType();
		
		switch (tagType) {
		case CHOICE_TYPE:
			ASN1Decoder decoder = new ASN1Decoder(ASN1Component.getFirstComponent(data), expectedComponent.getComponents());
			if (decoder.getAll().size() > 0) {
				ASN1Choice asn1Choice = new ASN1Choice(decoder.getAll().get(0),  expectedComponent.getComponents(),
						expectedComponent.getTagName(), expectedComponent.getContentDecoder());
				this.components.add(asn1Choice);
				length = 1 + asn1Choice.getLengthBytes() + asn1Choice.getLength();
			}
			break;
		case BOOLEAN_TYPE:
			subComponent = ASN1Component.retrieveComponent(data, expectedComponent);
			if (subComponent != null) {
				ASN1Boolean ans1Boolean = new ASN1Boolean(subComponent.getAllASN1Data(), expectedComponent.getComponents(),
						expectedComponent.getTagName(), expectedComponent.getContentDecoder());
				this.components.add(ans1Boolean);
				length = 1 + ans1Boolean.getLengthBytes() + ans1Boolean.getLength();
			}
			break;
		case INT_TYPE:
			subComponent = ASN1Component.retrieveComponent(data, expectedComponent);
			if (subComponent != null) {
				ASN1Integer asn1Integer = new ASN1Integer(subComponent.getAllASN1Data(), expectedComponent.getComponents(),
						expectedComponent.getTagName(), expectedComponent.getContentDecoder());
				this.components.add(asn1Integer);
				length = 1 + asn1Integer.getLengthBytes() + asn1Integer.getLength();
			}
			break;
		case BIT_STR_TYPE:
			subComponent = ASN1Component.retrieveComponent(data, expectedComponent);
			if (subComponent != null) {
				ASN1BitString asn1BitString = new ASN1BitString(subComponent.getAllASN1Data(), expectedComponent.getComponents(),
						expectedComponent.getTagName(), expectedComponent.getContentDecoder());
				this.components.add(asn1BitString);
				length = 1 + asn1BitString.getLengthBytes() + asn1BitString.getLength();
			}
			break;
		case OCTET_STR_TYPE:
			subComponent = ASN1Component.retrieveComponent(data, expectedComponent);
			if (subComponent != null) {
				ASN1OctetString asn1OctetString = new ASN1OctetString(subComponent.getAllASN1Data(), expectedComponent.getComponents(),
						expectedComponent.getTagName(), expectedComponent.getContentDecoder());
				this.components.add(asn1OctetString);
				length = 1 + asn1OctetString.getLengthBytes() + asn1OctetString.getLength();
			}
			break;
		case NULL_TYPE:
			subComponent = ASN1Component.retrieveComponent(data, expectedComponent);
			if (subComponent != null) {
				ASN1Null asn1Null = new ASN1Null(subComponent.getAllASN1Data(), expectedComponent.getComponents(),
						expectedComponent.getTagName(), expectedComponent.getContentDecoder());
				this.components.add(asn1Null);
				length = 1 + asn1Null.getLengthBytes() + asn1Null.getLength();
			}
			break;
		case OBJ_ID_TYPE:
			subComponent = ASN1Component.retrieveComponent(data, expectedComponent);
			if (subComponent != null) {
				ASN1ObjectID asn1ObjectID = new ASN1ObjectID(subComponent.getAllASN1Data(), expectedComponent.getComponents(),
						expectedComponent.getTagName(), expectedComponent.getContentDecoder());
				this.components.add(asn1ObjectID);
				length = 1 + asn1ObjectID.getLengthBytes() + asn1ObjectID.getLength();
			}
			break;
		case ENUM_TYPE:
			subComponent = ASN1Component.retrieveComponent(data, expectedComponent);
			if (subComponent != null) {
				ASN1Enumerated asn1Enumerated = new ASN1Enumerated(subComponent.getAllASN1Data(), expectedComponent.getComponents(),
						expectedComponent.getTagName(), expectedComponent.getContentDecoder());
				this.components.add(asn1Enumerated);
				length = 1 + asn1Enumerated.getLengthBytes() + asn1Enumerated.getLength();
			}
			break;
		case SEQ_TYPE:
			subComponent = ASN1Component.retrieveComponent(data, expectedComponent);
			if (subComponent != null) {
				ASN1Sequence asn1Sequence = new ASN1Sequence(subComponent.getAllASN1Data(), expectedComponent.getComponents(),
						expectedComponent.getTagName(), expectedComponent.getContentDecoder());
				this.components.add(asn1Sequence);
				length = 1 + asn1Sequence.getLengthBytes() + asn1Sequence.getLength();
			}
			break;
		case SEQ_OF_TYPE:
			subComponent = ASN1Component.retrieveComponent(data, expectedComponent);
			if (subComponent != null) {
				ASN1Sequence asn1Sequence = new ASN1Sequence(subComponent.getAllASN1Data(), null,
						expectedComponent.getTagName(), expectedComponent.getContentDecoder());
				long sequenceLength = asn1Sequence.getLength();
				ASN1Data subData = subComponent.getContents();
				while (sequenceLength > 0) {
					subComponent = new ASN1Component(subData, expectedComponent.getTagName());
					decoder = new ASN1Decoder(subData, expectedComponent.getComponents());
					if (decoder.getAll().size() > 0) {
						asn1Sequence.addComponent(decoder.getAll().get(0));
						sequenceLength -= decoder.getAll().get(0).getDataLength();
						subData = subData.subData(subData, decoder.getAll().get(0).getDataLength());
					}
				}
				this.components.add(asn1Sequence);
				length = 1 + asn1Sequence.getLengthBytes() + asn1Sequence.getLength();
			}
			break;
		case SET_TYPE:
			subComponent = ASN1Component.retrieveComponent(data, expectedComponent);
			if (subComponent != null) {
				ASN1Set asn1Set = new ASN1Set(subComponent.getAllASN1Data(), expectedComponent.getComponents(),
						expectedComponent.getTagName(), expectedComponent.getContentDecoder());
				this.components.add(asn1Set);
				length = 1 + asn1Set.getLengthBytes() + asn1Set.getLength();
			}
			break;
		case NUM_STR_TYPE:
			subComponent = ASN1Component.retrieveComponent(data, expectedComponent);
			if (subComponent != null) {
				ASN1NumericString asn1NumericString = new ASN1NumericString(subComponent.getAllASN1Data(), expectedComponent.getComponents(),
						expectedComponent.getTagName(), expectedComponent.getContentDecoder());
				this.components.add(asn1NumericString);
				length = 1 + asn1NumericString.getLengthBytes() + asn1NumericString.getLength();
			}
			break;
		case IA5_STR_TYPE:
			subComponent = ASN1Component.retrieveComponent(data, expectedComponent);
			if (subComponent != null) {
				ASN1IA5String asn1IA5String = new ASN1IA5String(subComponent.getAllASN1Data(), expectedComponent.getComponents(),
						expectedComponent.getTagName(), expectedComponent.getContentDecoder());
				this.components.add(asn1IA5String);
				length = 1 + asn1IA5String.getLengthBytes() + asn1IA5String.getLength();
			}
			break;
		case VIS_STR_TYPE:
			subComponent = ASN1Component.retrieveComponent(data, expectedComponent);
			if (subComponent != null) {
				ASN1VisibleString asn1VisibleString = new ASN1VisibleString(subComponent.getAllASN1Data(), expectedComponent.getComponents(),
						expectedComponent.getTagName(), expectedComponent.getContentDecoder());
				this.components.add(asn1VisibleString);
				length = 1 + asn1VisibleString.getLengthBytes() + asn1VisibleString.getLength();
			}
			break;
		case ANY_BY_TYPE:
			if (ASN1Static.getReferenceComponent() != null) {
				ASN1Decoder anyDecoder = new ASN1Decoder(ASN1Component.getFirstComponent(data), ASN1Static.getReferenceComponent());
				if (anyDecoder.getAll().size() > 0) {
					this.components.add(anyDecoder.getAll().get(0));
					length = 1 + anyDecoder.getAll().get(0).getLengthBytes() + anyDecoder.getAll().get(0).getLength();
				}
			} else {
				subComponent = ASN1Component.retrieveComponent(data);
				if (subComponent != null) {
					switch (subComponent.getTag()) {
					case ASN1_TAG_BOOLEAN:
						subComponent.setName("BOOLEAN");
						break;
					case ASN1_TAG_INT:
						subComponent.setName("INTEGER");
						break;
					case ASN1_TAG_BIT_STR:
					case ASN1_TAG_BIT_STR | CONSTRUCTED:
						subComponent.setName("BITSTRING");
						break;
					case ASN1_TAG_OCTET_STR:
					case ASN1_TAG_OCTET_STR | CONSTRUCTED:
						subComponent.setName("OCTET STRING");
						break;
					case ASN1_TAG_NULL:
						subComponent.setName("NULL");
						break;
					case ASN1_TAG_OBJ_ID:
						subComponent.setName("OBJECT ID");
						break;
						
					case ASN1_TAG_ENUM:
						subComponent.setName("ENUMERATED");
						break;
					case ASN1_TAG_SEQ | CONSTRUCTED:
						subComponent.setName("SEQUENCE");
						break;
					case ASN1_TAG_SET | CONSTRUCTED:
						subComponent.setName("SET");
						break;
					case ASN1_TAG_NUM_STR:
					case ASN1_TAG_NUM_STR | CONSTRUCTED:
						subComponent.setName("NUMERIC STRING");
						break;
					case ASN1_TAG_IA5_STR:
					case ASN1_TAG_IA5_STR | CONSTRUCTED:
						subComponent.setName("IA5 STRING");
						break;
					case ASN1_TAG_VIS_STR:
					case ASN1_TAG_VIS_STR | CONSTRUCTED:
						subComponent.setName("VISIBLE STRING");
						break;
					default:
						switch(subComponent.getTag() & MASK_CLASS) {
						case APPLICATION:
							subComponent.setName("[APPLICATION " + (subComponent.getTag() & MASK_ASN1_TAG) + "]");
							break;
						case CONTEXT:
							subComponent.setName("[CONTEXT " + (subComponent.getTag() & MASK_ASN1_TAG) + "]");
							break;
						case PRIVATE:
							subComponent.setName("[PRIVATE " + (subComponent.getTag() & MASK_ASN1_TAG) + "]");
							break;
						default: break;
						}
						break;
					}
					this.components.add(subComponent);
					length = 1 + subComponent.getLengthBytes() + subComponent.getLength();
				}
			}
			break;
		default:
			subComponent = ASN1Component.retrieveComponent(data);
			
			switch(subComponent.getTag() & MASK_CLASS) {
			case APPLICATION:
				subComponent.setName("[APPLICATION " + (subComponent.getTag() & MASK_ASN1_TAG) + "]");
				break;
			case CONTEXT:
				subComponent.setName("[CONTEXT " + (subComponent.getTag() & MASK_ASN1_TAG) + "]");
				break;
			case PRIVATE:
				subComponent.setName("[PRIVATE " + (subComponent.getTag() & MASK_ASN1_TAG) + "]");
				break;
			default: break;
			}
			if (subComponent != null) {
				this.components.add(subComponent);
				length = 1 + subComponent.getLengthBytes() + subComponent.getLength();
			}
			break;
		}
		return (length);
	}
	
	/**
	 * Returns all the analyzed components decoded by this decoder.
	 * @return an ArrayList with all the analyzed components decoded by this decoder.
	 */
	public ArrayList<ASN1Component> getAll () {
		return (this.components);
	}
}
