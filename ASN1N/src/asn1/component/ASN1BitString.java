package asn1.component;

import asn1.ASN1Component;
import asn1.ASN1CustomComponent;
import asn1.ASN1Data;
import asn1.BitContainer;
import asn1.DataContainer;
import asn1.com.ASN1ContentDecoder;
import asn1.com.ASN1Exception;

/**
 * The ASN1BitString class represents an ASN.1 bit string component. It extends the more general
 * {@link ASN1Component}.
 * 
 * @author Panos Katsikogiannis
 */
public class ASN1BitString extends ASN1Component {
	
	private short value[];
	private short unusedBits = 0;
	
	/**
	 * Creates an {@link ASN1BitString} component.
	 * @param tag - the tag of the component.
	 * @param unusedBits - the unused bits of the last octet.
	 * @param value - the value of the bitString component.
	 * @param name - the name of the component.
	 */
	public ASN1BitString (short tag, short unusedBits, short value[], String name) {
		super(convertToASN1Data(tag, unusedBits, value), name);
		setType(BIT_STR_TYPE);
		this.unusedBits = unusedBits;
		this.value = value;
	}
	
	/**
	 * Create a bitString component with the default tag
	 * @param unusedBits the unused bits of the last octet
	 * @param value the value of the bitString component
	 */
	public ASN1BitString (short unusedBits, short value[], String name) {
		this(ASN1_TAG_BIT_STR, unusedBits, value, name);
	}
	
	/**
	 * Creates an {@link ASN1BitString} component with the specific data including the tag and the length.
	 * @param data - the data of the bitString component.
	 * @param name - the name of the component.
	 */
	public ASN1BitString (ASN1Data data, String name) {
		super(data, name);
		setType(BIT_STR_TYPE);
		unusedBits = data.getDataAtIndex(1 + getLengthBytes());
		this.value = data.getDataFromIndex(1 + getLengthBytes() + 1);
	}
	
	/**
	 * Creates an {@link ASN1BitString} component with a specific tag.
	 * @param tag - the specific tag.
	 * @param unusedBits - the unused bits of the last octet.
	 * @param value - the value of the bitString component.
	 * @param table - a table of components that could be included in the bitString component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the bitString component can take.
	 * @throws ASN1Exception
	 */
	public ASN1BitString (short tag, short unusedBits, short value[], ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(convertToASN1Data(tag, unusedBits, value), table, name, contentDecoder);
		setType(BIT_STR_TYPE);
		this.unusedBits = unusedBits;
		this.value = value;
	}
	
	/**
	 * Creates an {@link ASN1BitString} component with the specific data including the tag and the length.
	 * @param data - the data of the bitString component.
	 * @param table - a table of components that could be included in the bitString component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the bitString component can take.
	 * @throws ASN1Exception
	 */
	public ASN1BitString (ASN1Data data, ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(data, table, name, contentDecoder);
		setType(BIT_STR_TYPE);
		unusedBits = data.getDataAtIndex(1 + getLengthBytes());
		this.value = data.getDataFromIndex(1 + getLengthBytes() + 1);
	}
	
	
	/**
	 * Set the value of the specified component.
	 * @param unusedBits - the unused bits of the last octet.
	 * @param value - the new value for the specified component.
	 */
	public void setValue (short unusedBits, short value[]) {
		this.unusedBits = unusedBits;
		this.value = value;
	}
	
	/**
	 * Returns the value of the current component.
	 * @return the value of the component.
	 */
	public short [] getValue () {
		return (this.value);
	}
	
	/**
	 * Returns the unused bits of the current component.
	 * @return the unused bits of the component.
	 */
	public int getUnusedBits () {
		return (this.unusedBits);
	}
	
	/**
	 * Converts the value of the specified component to ASN1Data containing the tag
	 * and the length of the component.
	 * @param tag - the specified tag of the component.
	 * @param unusedBits - the unused bits of the component.
	 * @param value - the value of the component.
	 * @return an ASN1Data object.
	 */
	public static ASN1Data convertToASN1Data (short tag, int unusedBits, short value[]) {
		
		StringBuffer output = new StringBuffer("");
		String helpString = "";
		ASN1Data asn1Data = new ASN1Data(1);
		if (unusedBits >= 8) {
			// Error
			System.exit(0);
		}
		// *********************** Check number of length bytes start ***********************
		int length = 1 + value.length;
		asn1Data.setDataAtIndex(tag, TAG_INDEX);
		asn1Data.addData(ASN1Component.convertLengthToASN1Data(length));
		// ************************ Check number of length bytes end ************************
		helpString = Integer.toHexString(unusedBits);
		output.append("0" + helpString);
		for (int i = 0; i < value.length; i++) {
			helpString = Integer.toHexString(value[i]);
			if (helpString.length() == 1) output.append("0" + helpString);
			else output.append(helpString);
		}
		asn1Data.addData(output.toString());
		
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
			return (tabs + getName() + " " + toBitString() + " {" + getValueName() + "}\n");
		}
	}
	
	/**
	 * Returns the value of this bitString to an appropriate printable bit string.
	 * @return a String object to be printed.
	 * @deprecated
	 */
	private String toBitString () {
		StringBuffer output = new StringBuffer();
		String helpString;
		for (int i = 0; i < this.value.length; i++) {
			helpString = Integer.toBinaryString(this.value[i]);
			int length = helpString.length();
			for (int j = 0; j < 8 - length; j++) {
				helpString = "0" + helpString;
			}
			output.append(helpString);
		}
		return (output.substring(0, output.length() - this.unusedBits));
	}
	
	/**
	 * Returns a visual representation of the value of the bitString.
	 * @return a visual representation of the value of the bitString.
	 * @deprecated
	 */
	private String getValueName () {
		String output = "";
		short unusedBits = getContents().getDataAtIndex(0);
		long value = Integer.valueOf(getContents().getStringDataOffsetToIndex(1, (int)this.getLength()), 16);
		value >>= unusedBits;
		int numOfBits = (int) (((this.getLength() - 1) * 8) - unusedBits);
		int checkBit = numOfBits;
		for (int i = 0; i < numOfBits; i++) {
			checkBit--;
			if ((value & (1<<checkBit)) == (1<<checkBit)) {
				output += "unknownValue | ";
			}
		}
		if (output.length() > 3)
			return (output.substring(0, output.length() - 3));
		return (output);
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
		// UnusedBits
		contData = new short[1];
		contData[0] = unusedBits;
		result.addData(new DataContainer(contData, "Unused Bits", String.format("%d", unusedBits), 1, false));
		// Contents
		DataContainer helpDataCont = null;
		BitContainer bit = null;
		int numOfBitsLeft = (int) ((value.length * 8) - unusedBits);
		int checkBit = 0;
		if (getContentDecoder() != null) {
			// Go through each byte
			for (int i = 0; i < value.length; i++) {
				contData = new short[1];
				contData [0] = value[i];
				helpDataCont = new DataContainer(contData, 1, true);
				// Go through each bit of the actual byte starting from the MSB
				for (int j = 7; j >= 0 & checkBit < numOfBitsLeft; j--, checkBit++) {
					ASN1Data asn1Data = new ASN1Data(1);
					asn1Data.setDataAtIndex((short) (1<<j), 0);
					// Check if bit is true
					if ((value[i] & (1 << j)) == (1 << j)) {
						bit = new BitContainer(value[i], getContentDecoder().decode(asn1Data), "true", 1, j);
						helpDataCont.addBits(bit);
					} else {
						bit = new BitContainer(value[i], getContentDecoder().decode(asn1Data), "false", 1, j);
						helpDataCont.addBits(bit);
					}
				}
				result.addData(helpDataCont);
			}
		} else {
			// Go through each byte
			for (int i = 0; i < value.length; i++) {
				contData = new short[1];
				contData [0] = value[i];
				helpDataCont = new DataContainer(contData, 1, true);
				// Go through each bit of the actual byte starting from the MSB
				for (int j = 7; j >= 0 & checkBit < numOfBitsLeft; j--, checkBit++) {
					// Check if bit is true
					if ((value[i] & (1<<j)) == (1<<j)) {
						bit = new BitContainer(value[i], "bit", "true", 1, j);
						helpDataCont.addBits(bit);
					} else {
						bit = new BitContainer(value[i], "bit", "false", 1, j);
						helpDataCont.addBits(bit);
					}
				}
				result.addData(helpDataCont);
			}
		}
		return result;
	}
}
