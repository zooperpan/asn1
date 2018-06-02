package asn1.component;

import asn1.ASN1Component;
import asn1.ASN1CustomComponent;
import asn1.ASN1Data;
import asn1.com.ASN1ContentDecoder;
import asn1.com.ASN1Exception;

/**
 * The ASN1Set class represents an ASN.1 set component. It extends the more general
 * {@link ASN1Component}.
 * 
 * @author Panos Katsikogiannis
 */
public class ASN1Set extends ASN1Component {

	/**
	 * Creates an {@link ASN1Set} component.
	 * @param tag - the tag  of the component.
	 * @param value - the value of the set component.
	 * @param name - the name of the component.
	 */
	public ASN1Set (short tag, ASN1Data value, String name) {
		super(convertToASN1Data(tag, value), name);
		setType(SET_TYPE);
	}
	
	/**
	 * Creates an {@link ASN1Set} component with the specific data including the tag and the length.
	 * @param data - the data of the set component.
	 * @param name - the name of the component.
	 */
	public ASN1Set (ASN1Data data, String name) {
		super(data, name);
		setType(SET_TYPE);
	}
	
	/**
	 * Creates an {@link ASN1Set} component with a specific tag.
	 * @param tag - the specific tag.
	 * @param value - the value of the set component.
	 * @param table - a table of components that could be included in the set component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the set component can take.
	 * @throws ASN1Exception
	 */
	public ASN1Set (short tag, ASN1Data value, ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(convertToASN1Data(tag, value), table, name, contentDecoder);
		setType(SET_TYPE);
	}
	
	/**
	 * Creates an {@link ASN1Set} component with the specific data including the tag and the length.
	 * @param data - the data of the set component.
	 * @param table - a table of components that could be included in the set component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the set component can take.
	 * @throws ASN1Exception
	 */
	public ASN1Set (ASN1Data data, ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(data, table, name, contentDecoder);
		setType(SET_TYPE);
	}
	
	/**
	 * Converts the value of the specified component to ASN1Data containing the tag
	 * and the length of the component.
	 * @param tag - the specified tag of the component.
	 * @param value - the value of the component.
	 * @return an ASN1Data object
	 */
	public static ASN1Data convertToASN1Data (short tag, ASN1Data value) {
		ASN1Data asn1Data = new ASN1Data(1);
		asn1Data.setDataAtIndex(tag, TAG_INDEX);
		// *********************** Check number of length bytes start ***********************
		int length = value.toString().length() / 2;
		asn1Data.setDataAtIndex(tag, TAG_INDEX);
		asn1Data.addData(ASN1Component.convertLengthToASN1Data(length));
		// ************************ Check number of length bytes end ************************
		asn1Data.addData(value.toString());
		return (asn1Data);
	}
	
	/**
	 * Returns the object in a printable string.
	 * @param tabs - the tabs in front of the visual representation.
	 * @return a String object to be printed.
	 * @deprecated
	 */
	public String printData (String tabs) {
		return (super.printData(tabs));
	}
}
