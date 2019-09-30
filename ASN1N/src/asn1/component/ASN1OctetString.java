package asn1.component;

import asn1.ASN1Component;
import asn1.ASN1CustomComponent;
import asn1.ASN1Data;
import asn1.DataContainer;
import asn1.com.ASN1ContentDecoder;
import asn1.com.ASN1Exception;
import asn1.component.decoder.ASN1OctetStringDecoder;


/**
 * The ASN1OctetString class represents an ASN.1 octet string component. It extends the more general
 * {@link ASN1Component}.
 * 
 * @author Panos Katsikogiannis
 */
public class ASN1OctetString extends ASN1Component {

	private String value;
	
	/**
	 * Creates an {@link ASN1OctetString} component.
	 * @param tag - the tag of the component.
	 * @param value - the value of the octet string component.
	 * @param name - the name of the component.
	 */
	public ASN1OctetString (short tag, String value, String name) {
		super(convertToASN1Data(tag, value), name);
		setType(OCTET_STR_TYPE);
		this.value = value;
	}
	
	/**
	 * Create an octetString component with the default tag
	 * @param unusedBits the unused bits of the last octet
	 * @param value the value of the octetString component
	 */
	public ASN1OctetString (String value, String name) {
		this(ASN1_TAG_OCTET_STR, value, name);
	}
	
	/**
	 * Creates an {@link ASN1OctetString} component with the specific data including the tag and the length.
	 * @param data - the data of the octet string component.
	 * @param name - the name of the component.
	 */
	public ASN1OctetString (ASN1Data data, String name) {
		super(data, name);
		setType(OCTET_STR_TYPE);
		this.value = data.getStringDataFromIndex(1 + getLengthBytes());
	}
	
	/**
	 * Creates an {@link ASN1OctetString} component with a specific tag.
	 * @param tag - the specific tag.
	 * @param value - the value of the  octet string component.
	 * @param table - a table of components that could be included in the octet string component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the octet string component can take.
	 * @throws ASN1Exception
	 */
	public ASN1OctetString (short tag, String value, ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(convertToASN1Data(tag, value), table, name, contentDecoder);
		setType(OCTET_STR_TYPE);
		this.value = value;
	}
	
	/**
	 * Creates an {@link ASN1OctetString} component with the specific data including the tag and the length.
	 * @param data - the data of the octet string component.
	 * @param table - a table of components that could be included in the octet string component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the octet string component can take.
	 * @throws ASN1Exception
	 */
	public ASN1OctetString (ASN1Data data, ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(data, table, name, contentDecoder);
		setType(OCTET_STR_TYPE);
		this.value = data.getStringDataFromIndex(1 + getLengthBytes());
	}
	
	/**
	 * Set the value of the specified component.
	 * @param value - the new value for the specified component.
	 */
	public void setValue (String value) {
		this.value = value;
	}
	
	/**
	 * Returns the value of the current component.
	 * @return the value of the current component.
	 */
	public String getValue () {
		return (this.value);
	}
	
	/**
	 * Converts the value of the specified component to ASN1Data containing the tag
	 * and the length of the component.
	 * @param tag - the specified tag of the component
	 * @param value - the value of the component
	 * @return an ASN1Data object
	 */
	public static ASN1Data convertToASN1Data (short tag, String value) {
		
		ASN1Data asn1Data = new ASN1Data(1);
		// *********************** Check number of length bytes start ***********************
		int length = value.length() / 2;
		asn1Data.setDataAtIndex((short) tag, TAG_INDEX);
		asn1Data.addData(ASN1Component.convertLengthToASN1Data(length));
		// ************************ Check number of length bytes end ************************
		asn1Data.addData(value);
		return (asn1Data);
	}
	
	/**
	 * Returns the object in a printable string.
	 * @param tabs - the tabs in front of the visual representation.
	 * @return a String object to be printed.
	 * @deprecated
	 */
	public String printData (String tabs) {
		if (isConstructed()) {
			return (super.printData(tabs));
		} else {
			return (tabs + getName() + " " + this.value + "\n");
		}
	}
	
	/**
	 * Returns the data of the ASN.1 component in a {@link DataContainer}.
	 */
	public DataContainer getDataContainer () {
		DataContainer result = null;
		short contData[];
		// Tag
		contData = new short[1];
		contData[0] = getTag();
		result = new DataContainer(contData,  getName(), "", getDataLength(), true);
		// Length
		contData = new short[getLengthBytes()];
		for (int i = 0; i < getLengthBytes(); i++) {
			contData[i] = getDataAtIndex(i + 1);
		}
		result.addData(new DataContainer(contData, "Length", String.format("%d", getLength()), getLengthBytes(), false));
		// Contents
		if (getContentDecoder() != null) {
			// OctetString content decoder provides DataContainer objects
			ASN1OctetStringDecoder decoder = (ASN1OctetStringDecoder) getContentDecoder();
			result.addData(decoder.decodeData(getContents()));
		} else {
			contData = new short[(int) getLength()];
			for (int i = 0; i < getLength(); i++) {
				contData[i] = getDataAtIndex(i + 1 + getLengthBytes());
			}
			String valueName = "\"" + value + "\"";
			result.addData(new DataContainer(contData, "Value", valueName, (int) getLength(), false));
		}
		return result;
	}
}
