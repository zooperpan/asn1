package asn1.component;

import asn1.ASN1Component;
import asn1.ASN1CustomComponent;
import asn1.ASN1Data;
import asn1.DataContainer;
import asn1.com.ASN1ContentDecoder;
import asn1.com.ASN1Exception;

/**
* The ASN1IA5String class represents an ASN.1 IA5 string component. It extends the more general
* {@link ASN1Component}.
* 
* @author Panos Katsikogiannis
*/
public class ASN1IA5String extends ASN1Component {

	private String value;
	
	/**
	 * Creates an {@link ASN1IA5String} component.
	 * @param tag - the tag of the component.
	 * @param value - the value of the IA5 string component.
	 * @param name - the name of the component.
	 */
	public ASN1IA5String (short tag, String value, String name) {
		super(convertToASN1Data(tag, value), name);
		setType(IA5_STR_TYPE);
		this.value = value;
	}
	
	/**
	 * Create a IA5String component with the default tag
	 * @param value The value of the IA5String component
	 */
	public ASN1IA5String (String value, String name) {
		this(ASN1_TAG_IA5_STR, value, name);
	}
	
	/**
	 * Creates an {@link ASN1IA5String} component with the specific data including the tag and the length.
	 * @param data - the data of the IA5 string component.
	 * @param name - the name of the component.
	 */
	public ASN1IA5String (ASN1Data data, String name) {
		super(data, name);
		setType(IA5_STR_TYPE);
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < getContents().getDataLength(); i++) {
			output.append((char)getContents().getDataAtIndex(i));
		}
		this.value = output.toString();
	}
	
	/**
	 * Creates an {@link ASN1IA5String} component with a specific tag.
	 * @param tag - the specific tag.
	 * @param value - the value of the IA5 string component.
	 * @param table - a table of components that could be included in the IA5 string component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the IA5 string component can take.
	 * @throws ASN1Exception
	 */
	public ASN1IA5String (short tag, String value, ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(convertToASN1Data(tag, value), table, name, contentDecoder);
		setType(IA5_STR_TYPE);
		this.value = value;
	}
	
	/**
	 * Creates an {@link ASN1IA5String} component with the specific data including the tag and the length.
	 * @param data - the data of the IA5 string component.
	 * @param table - a table of components that could be included in the IA5 string component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the IA5 string component can take.
	 * @throws ASN1Exception
	 */
	public ASN1IA5String (ASN1Data data, ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(data, table, name, contentDecoder);
		setType(IA5_STR_TYPE);
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
	 * @param tag The specified tag of the component
	 * @param value The value of the component
	 * @return an ASN1Data object
	 */
	public static ASN1Data convertToASN1Data (short tag, String value) {
		
		StringBuffer output = new StringBuffer("");
		String helpString = "";
		ASN1Data asn1Data = new ASN1Data(1);
		
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			helpString = Integer.toHexString((int)(c));
			if (helpString.length() == 1) helpString = "0" + helpString;
			output.append(helpString);
		}
		// *********************** Check number of length bytes start ***********************
		int length = output.length() / 2;
		asn1Data.setDataAtIndex(tag, TAG_INDEX);
		asn1Data.addData(ASN1Component.convertLengthToASN1Data(length));
		// ************************ Check number of length bytes end ************************
		asn1Data.addData(output.toString());
		return asn1Data;
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
		contData = new short[(int) getLength()];
		for (int i = 0; i < getLength(); i++) {
			contData[i] = getDataAtIndex(i + 1 + getLengthBytes());
		}
		String valueName = "\"" + value + "\"";
		result.addData(new DataContainer(contData, "Value", valueName, (int) getLength(), false));
		return result;
	}
}
