package asn1.component;

import asn1.ASN1Component;
import asn1.ASN1CustomComponent;
import asn1.ASN1Data;
import asn1.DataContainer;
import asn1.com.ASN1ContentDecoder;
import asn1.com.ASN1Exception;

/**
 * The ASN1Enumerated class represents an ASN.1 enumerated component. It extends the more general
 * {@link ASN1Component}.
 * 
 * @author Panos Katsikogiannis
 */
public class ASN1Enumerated extends ASN1Component {

	private long value = 0;
	
	/**
	 * Creates an {@link ASN1Enumerated} component.
	 * @param tag - the tag of the component.
	 * @param value - the value of the enumerated component.
	 * @param name - the name of the component.
	 */
	public ASN1Enumerated (short tag, long value, String name) {
		super(convertToASN1Data(tag, value), name);
		setType(ENUM_TYPE);
		this.value = value;
	}
	
	/**
	 * Create an enumerated component with the default tag
	 * @param value The value of the enumerated component
	 */
	public ASN1Enumerated (long value, String name) {
		this(ASN1_TAG_ENUM, value, name);
	}
	
	/**
	 * Creates an {@link ASN1Enumerated} component with the specific data including the tag and the length.
	 * @param data - the data of the enumerated component.
	 * @param name - the name of the component.
	 */
	public ASN1Enumerated (ASN1Data data, String name) {
		super(data, name);
		setType(ENUM_TYPE);
		int length = data.getDataLength();
		for (int i = 1 + getLengthBytes(); i < length; i++) {
			value += data.getDataAtIndex(i) * Math.pow(16, 2 * (length - (i + 1)));
		}
	}
	
	/**
	 * Creates an {@link ASN1Enumerated} component with a specific tag.
	 * @param tag - the specific tag.
	 * @param value - the value of the enumerated component.
	 * @param table - a table of components that could be included in the enumerated component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the enumerated component can take.
	 * @throws ASN1Exception
	 */
	public ASN1Enumerated (short tag, long value, ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(convertToASN1Data(tag, value), table, name, contentDecoder);
		setType(ENUM_TYPE);
		this.value = value;
	}
	
	/**
	 * Creates an {@link ASN1Enumerated} component with the specific data including the tag and the length.
	 * @param data - the data of the enumerated component.
	 * @param table - a table of components that could be included in the enumerated component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the enumerated component can take.
	 * @throws ASN1Exception
	 */
	public ASN1Enumerated (ASN1Data data, ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(data, table, name, contentDecoder);
		setType(ENUM_TYPE);
		int length = data.getDataLength();
		for (int i = 1 + getLengthBytes(); i < length; i++) {
			value += data.getDataAtIndex(i) * Math.pow(16, 2 * (length - (i + 1)));
		}
	}
	
	/**
	 * Converts the value of the specified component to ASN1Data containing the tag
	 * and the length of the component.
	 * @param tag - the specified tag of the component.
	 * @param value - the value of the component.
	 * @return an ASN1Data object.
	 */
	public static ASN1Data convertToASN1Data (short tag, long value) {
		
		ASN1Data asn1Data = null;
		short length;
		String stringValue = Long.toHexString(value);
		
		length = (short) stringValue.length();
		
		if (length % 2 == 1) {
			stringValue = "0" + stringValue;
			length++;
		}
		length /= 2;
		asn1Data = new ASN1Data (2);
		asn1Data.setDataAtIndex(tag, TAG_INDEX);
		asn1Data.setDataAtIndex(length, LENGTH_INDEX);
		asn1Data.addData(stringValue);
		return (asn1Data);
	}
	
	/**
	 * Returns the object in a printable string.
	 * @param tabs - the tabs in front of the visual representation.
	 * @return a String object to be printed.
	 */
	public String printData (String tabs) {
		String valueName = getValueName(tabs);
		if (valueName.length() != 0) return (tabs + getName() + " " + valueName + "\n");
		return (tabs + getName() + " " + this.value + "\n");
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
		contData = new short[(int) getLength()];
		for (int i = 0; i < getLength(); i++) {
			contData[i] = getDataAtIndex(i + 1 + getLengthBytes());
		}
		String valueName = "";
		if (getContentDecoder() != null) {
			valueName = getContentDecoder().decode(getContents());
		} else valueName = String.format("%d", value);
		result.addData(new DataContainer(contData, "Value", valueName, (int) getLength(), false));
		return result;
	}
}
