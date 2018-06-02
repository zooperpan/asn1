package asn1;

import asn1.com.ASN1Definitions;

/**
 *
 * This class holds the basic ASN.1 data. It provides some useful methods
 * in order to manipulate these data. 
 *
 * @author Panos Katsikogiannis
 */
public class ASN1Data implements ASN1Definitions {
	
	private short data[];
	
	/**
	 * Creates a new {@link ASN1Data} object.
	 */
	public ASN1Data () {}
	
	/**
	 * Creates a new {@link ASN1Data} object with the specified length.
	 * @param length - the specified length.
	 */
	public ASN1Data (int length) {
		this.data = new short[length];
	}
	
	/**
	 * Creates a new {@link ASN1Data} object containing the octets given in the specified string.
	 * @param data - the specified string.
	 */
	public ASN1Data (String data) {
		int length = data.length();
		this.data = new short[length / 2];
		this.data = convertString2IntArray(data);
	}
	
	/**
	 * Creates a new {@link ASN1Data} object containing the specific data.
	 * @param data - the specific data.
	 */
	public ASN1Data (short data[]) {
		this.data = new short[data.length];
		for (int i = 0; i < data.length; i++) {
			this.data[i] = data[i];
		}
	}
	
	/**
	 * Adds new data given in an octet string to the ASN1Data object.
	 * @param data - the octet string data to be added in the object.
	 */
	public void addData (String data) {
		
		int i = 0;
		int length = 0;
		
		if (this.data != null) length = this.data.length + (data.length() / 2);
		else length = (data.length() / 2);
		
		short newData[] = new short[length];
		
		if (this.data != null) {
			// Copy existed data
			for (i = 0; i < this.data.length; i++) {
				newData[i] = this.data[i];
			}
		}
		this.data = new short[length];
		this.data = newData;
		newData = convertString2IntArray(data);
		// Copy the new data
		for (int j = 0; j < newData.length; j++) {
			this.data[i + j] = newData[j];
		}
	}
	
	/**
	 * Returns the object in an octet string representation.
	 */
	public String toString () {
		
		StringBuffer output = new StringBuffer("");
		String helpString = "";
		if (data != null)
		{
			for (int i = 0; i < this.data.length; i++) {
				helpString = Integer.toHexString(this.data[i]);
				if (helpString.length() == 1) output.append("0" + helpString);
				else output.append(helpString);
			}
		}
		return (output.toString());
	}
	
	/**
	 * Returns the length of the ASN1Data data.
	 * @return the length of the ASN1Data data.
	 */
	public int getDataLength () {
		return (this.data.length);
	}
	
	/**
	 * Returns the data at the specified index.
	 * @param index - the specified index.
	 * @return the data at the specified index.
	 */
	public short getDataAtIndex (int index) {
		return (this.data[index]);
	}
	
	/**
	 * Sets  the data at the specified index.
	 * @param data - the data to be set.
	 * @param index - the specified index.
	 */
	public void setDataAtIndex (short data, int index) {
		this.data[index] = data;
	}
	
	/**
	 * Returns a short array with the data taken from the specified index.
	 * @param index - the specified index.
	 * @return a short array with the data taken from the specified index.
	 */
	public short [] getDataFromIndex (long index) {
		short data[] = new short [(int) (this.data.length - index)];
		for (int i = 0; i < this.data.length - index; i++) {
			data[i] = this.data[(int) (i + index)];
		}
		return (data);
	}
	
	/**
	 * Returns a short array with all the data held by this object.
	 * @return  a short array with all the data held by this object.
	 */
	public short [] getAllData () {
		return (this.data);
	}
	
	/**
	 * Returns this object.
	 * @return this object.
	 */
	public ASN1Data getAllASN1Data () {
		return (this);
	}
	
	/**
	 * Returns a new ASN1Data object taken from the given data and starting from the specified offset.
	 * @param asn1Data - the given data.
	 * @param offset - the specified offset.
	 * @return a new ASN1Data object.
	 */
	public ASN1Data subData (ASN1Data asn1Data, long offset) {
		
		ASN1Data subData = null;
		subData = new ASN1Data(asn1Data.getDataFromIndex(offset));
		return (subData);
	}
	
	/**
	 * Returns a string representation of the object's data starting from the specified index.
	 * @param index - the specified index.
	 * @return a string representation of the object's data taken from the specified index.
	 */
	public String getStringDataFromIndex (int index) {
		
		StringBuffer output = new StringBuffer("");
		String helpString = "";
		for (int i = 0; i < this.data.length - index; i++) {
			helpString = String.format("%02X", this.data[i + index]);
			output.append(helpString);
		}
		return (output.toString());
	}
	
	/**
	 * Returns a string representation of the object's sub-data starting from the specified offset 
	 * and ending at the specified index.
	 * @param offset - the specified offset.
	 * @param index - the specified index.
	 * @return a string representation of the object's data.
	 */
	public String getStringDataOffsetToIndex (int offset, int index) {
		
		StringBuffer output = new StringBuffer("");
		String helpString = "";
		for (int i = offset; i < index; i++) {
			helpString = Integer.toHexString(this.data[i]);
			if (helpString.length() == 1) output.append("0" + helpString);
			else output.append(helpString);
		}
		return (output.toString());
	}
	
	/**
	 * Returns a short array of the object's data starting from the specified offset 
	 * and ending at the specified index.
	 * @param offset - the specified offset.
	 * @param index - the specified index.
	 * @return a short array of the object's sub-data.
	 */
	public short [] getDataFromOffsetUntilIndex (int offset, int index) {
		
		short output[] = new short[index - offset];
		for (int i = offset; i < index; i++) {
			output[i - offset] = this.data[i];
		}
		return (output);
	}
	
	/**
	 * Converts the given octet string into a short array.
	 * @param dataString - the octet string to be converted.
	 * @return a short array with the new data.
	 */
	private short [] convertString2IntArray (String dataString) {
		int j = 0;
		int length = dataString.length();
		short data[] = new short[length / 2];
		
		for (int i = 0; i < length; i++, j++) {
			data[j] = (short) (Character.digit(dataString.charAt(i), 16) * 16);
			i++;
			data[j] += (short) Character.digit(dataString.charAt(i), 16);
		}
		return (data);
	}
}
