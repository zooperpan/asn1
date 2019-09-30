package asn1.component;

import asn1.ASN1Component;
import asn1.ASN1CustomComponent;
import asn1.ASN1Data;
import asn1.DataContainer;
import asn1.com.ASN1ContentDecoder;
import asn1.com.ASN1Exception;

/**
 * The ASN1ObjectID class represents an ASN.1 odjectID component. It extends the more general
 * {@link ASN1Component}.
 * 
 * @author Panos Katsikogiannis
 */
public class ASN1ObjectID extends ASN1Component {

	private int value[];
	
	/**
	 * Creates an {@link ASN1ObjectID} component.
	 * @param tag - the tag of the component.
	 * @param value - the value of the objectID component.
	 * @param name - the name of the component.
	 */
	public ASN1ObjectID (short tag, int value[], String name) {
		super(convertToASN1Data(tag, value), name);
		setType(OBJ_ID_TYPE);
		this.value = value;
	}
	
	/**
	 * Create a objectID component with the default tag
	 * @param value the value of the objectID component
	 */
	public ASN1ObjectID (int value[], String name) {
		this(ASN1_TAG_OBJ_ID, value, name);
	}
	
	/**
	 * Creates an {@link ASN1ObjectID} component with the specific data including the tag and the length.
	 * @param data - the data of the objectID component.
	 * @param name - the name of the component.
	 */
	public ASN1ObjectID (ASN1Data data, String name) {
		super(data, name);
		setType(OBJ_ID_TYPE);
		this.value = calculateValue(getContents().getAllData());
	}
	
	/**
	 * Creates an {@link ASN1ObjectID} component with a specified tag.
	 * @param tag - the specified tag.
	 * @param value - the value of the objectID component.
	 * @param table - a table of components that could be included in the objectID component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the objectID component can take.
	 * @throws ASN1Exception
	 */
	public ASN1ObjectID (short tag, int value[], ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(convertToASN1Data(tag, value), table, name, contentDecoder);
		setType(OBJ_ID_TYPE);
		this.value = value;
	}
	
	/**
	 * Creates an {@link ASN1ObjectID} component with the specific data including the tag and the length.
	 * @param data - the data of the objectID component.
	 * @param table - a table of components that could be included in the objectID component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the objectID component can take.
	 * @throws ASN1Exception
	 */
	public ASN1ObjectID (ASN1Data data, ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(data, table, name, contentDecoder);
		setType(OBJ_ID_TYPE);
		this.value = calculateValue(getContents().getAllData());
	}
	
	/**
	 * Set the value of the specified component
	 * @param value The new value for the specified component
	 */
	public void setValue (int value[]) {
		this.value = value;
	}
	
	/**
	 * Returns the value of the current component
	 * @return the value of the component
	 */
	public int [] getValue () {
		return (this.value);
	}
	
	/**
	 * Converts the value of the specified component to ASN1Data containing the tag
	 * and the length of the component.
	 * @param tag - the specified tag of the component.
	 * @param value - the value of the component.
	 * @return an ASN1Data object.
	 */
	public static ASN1Data convertToASN1Data (short tag, int value[]) {
		
		ASN1Data asn1Data = new ASN1Data(1);
		int length = 1;
		int subID = 40 * value[0] + value[1];
		
		String helpString = "";
		int subLength = length;
		int subsubID = subID;
		while (subID >= 0x80) {
			subsubID = subID;
			if (subLength > length) {
				subsubID |= 0x80;
				helpString = Integer.toHexString(subsubID & 0xFF) + helpString;
			} else helpString = Integer.toHexString(subsubID & 0x7F) + helpString;
			if ((helpString.length() % 2) != 0) helpString = "0" + helpString;
			subID >>= 7;
			subLength++;
		}
		length = subLength;
		if (length > 1) subID |= 0x80;
		helpString = Integer.toHexString(subID) + helpString;
		if ((helpString.length() % 2) != 0) helpString = "0" + helpString;
		
		for (int i = 2; i < value.length; i++) {
			subLength = length;
			subID = value[i];
			String subHelpString = "";
			while (subID >= 0x80) {
				subsubID = subID;
				if (subLength > length) {
					subsubID |= 0x80;
					subHelpString = Integer.toHexString(subsubID & 0xFF) + subHelpString;
				} else subHelpString = Integer.toHexString(subsubID & 0x7F) + subHelpString;
				if ((subHelpString.length() % 2) != 0) subHelpString = "0" + subHelpString;
				subID >>= 7;
				subLength++;
			}
			if (subLength > length) subID |= 0x80;
			length = subLength;
			subHelpString = Integer.toHexString(subID) + subHelpString;
			if ((subHelpString.length() % 2) != 0) subHelpString = "0" + subHelpString;
			helpString += subHelpString;
			length++;
		}
		// *********************** Check number of length bytes start ***********************
		asn1Data.setDataAtIndex(tag, TAG_INDEX);
		asn1Data.addData(ASN1Component.convertLengthToASN1Data(length));
		// ************************ Check number of length bytes end ************************
		asn1Data.addData(helpString);
		return (asn1Data);
	}
	
	/**
	 * Calculates the value of the objectID component from the passed data.
	 * @param contents - the passed data.
	 * @return a short array containing the value.
	 */
	private int [] calculateValue (short contents[]) {
		// Calculate the number of object subIDs in the data
		int length = 1;
		for (int i = 0; i < contents.length; i++) {
			if ((contents[i] & 0x80) == 0x80) continue;
			length++;
		}
		int value[] = new int[length];
		
		// Calculate the first 2 object subIDs (40 * X + Y)
		int subID = 0;
		int index = 0;
		short tempValue = contents[index];
		while (tempValue > 0x80) {
			subID += (tempValue & 0x7F);
			subID <<= 7;
			index++;
			tempValue = contents[index];
		}
		subID += (tempValue & 0x7F);
		index++;
		value[0] = subID / 40;
		value[1] = subID % 40;
		
		int valueIndex = 2;
		while (index < contents.length) {
			subID = 0;
			tempValue = contents[index];
			while (tempValue > 0x80) {
				subID += (tempValue & 0x7F);
				subID <<= 7;
				index++;
				tempValue = contents[index];
			}
			subID += (tempValue & 0x7F);
			index++;
			value[valueIndex] = subID;
			valueIndex++;
		}
		return (value);
	}
	
	/**
	 * Returns the object in a printable string
	 * @param tabs - the tabs in front of the visual representation
	 * @return a String object to be printed
	 */
	public String printData (String tabs) {
		return (tabs + getName() + toArrayString() + "\n");
	}
	
	/**
	 * Returns the value of this objectID to an appropriate printable string
	 * @return a String object to be printed
	 */
	private String toArrayString () {
		StringBuffer output = new StringBuffer(" {");
		int length = this.value.length;
		
		for (int i = 0; i < length; i++) {
			if (i == length - 1) output.append(this.value[i]);
			else output.append(this.value[i] + " ");
		}
		output.append("}");
		return (output.toString());
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
		result.addData(new DataContainer(contData, "Value", toArrayString(), (int) getLength(), false));
		return result;
	}
}
