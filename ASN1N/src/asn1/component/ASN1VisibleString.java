package asn1.component;

import asn1.ASN1Component;
import asn1.ASN1CustomComponent;
import asn1.ASN1Data;
import asn1.DataContainer;
import asn1.com.ASN1ContentDecoder;
import asn1.com.ASN1Exception;

/**
 * The ASN1VisibleString class represents an ASN.1 visible string component. It extends the more general
 * {@link ASN1Component}.
 * 
 * @author Panos Katsikogiannis
 */
public class ASN1VisibleString extends ASN1Component {

	private String value;
	
	/**
	 * Creates an {@link ASN1VisibleString} component.
	 * @param tag - the tag of the component.
	 * @param value - the value of the visible string component.
	 * @param name - the name of the component.
	 */
	public ASN1VisibleString (short tag, String value, String name) {
		super(convertToASN1Data(tag, value), name);
		setType(VIS_STR_TYPE);
		this.value = value;
	}
	
	/**
	 * Creates an {@link ASN1VisibleString} component with the specific data including the tag and the length.
	 * @param data - the data of the visible string component.
	 * @param name - the name of the component.
	 */
	public ASN1VisibleString (ASN1Data data, String name) {
		super(data, name);
		setType(VIS_STR_TYPE);
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < getContents().getDataLength(); i++) {
			output.append((char)getContents().getDataAtIndex(i));
		}
		this.value = output.toString();
	}
	
	/**
	 * Creates an {@link ASN1VisibleString} component with a specific tag.
	 * @param tag - the specific tag.
	 * @param value - the value of the visible string component.
	 * @param table - a table of components that could be included in the visible string component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the visible string component can take.
	 * @throws ASN1Exception
	 */
	public ASN1VisibleString (short tag, String value, ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(convertToASN1Data(tag, value), table, name, contentDecoder);
		setType(VIS_STR_TYPE);
		this.value = value;
	}
	
	/**
	 * Creates an {@link ASN1VisibleString} component with the specific data including the tag and the length.
	 * @param data - the data of the visible string component.
	 * @param table - a table of components that could be included in the visible string component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the visible string component can take.
	 * @throws ASN1Exception
	 */
	public ASN1VisibleString (ASN1Data data, ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(data, table, name, contentDecoder);
		setType(VIS_STR_TYPE);
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < getContents().getDataLength(); i++) {
			output.append((char)getContents().getDataAtIndex(i));
		}
		this.value = output.toString();
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
			return (tabs + getName() + this.value + "\n");
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
		contData = new short[(int) getLength()];
		for (int i = 0; i < getLength(); i++) {
			contData[i] = getDataAtIndex(i + 1 + getLengthBytes());
		}
		String valueName = "\"" + value + "\"";
		result.addData(new DataContainer(contData, "Value", valueName, (int) getLength(), false));
		return result;
	}
}
