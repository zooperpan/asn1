package asn1.component;

import asn1.ASN1Component;
import asn1.ASN1CustomComponent;
import asn1.ASN1Data;
import asn1.DataContainer;
import asn1.com.ASN1ContentDecoder;
import asn1.com.ASN1Exception;

/**
 * The ASN1Null class represents an ASN.1 null component. It extends the more general
 * {@link ASN1Component}.
 * 
 * @author Panos Katsikogiannis
 */
public class ASN1Null extends ASN1Component {

	/**
	 * Creates an {@link ASN1Null} component.
	 * @param tag - the tag of the component.
	 * @param name - the name of the component.
	 */
	public ASN1Null (short tag, String name) {
		super(convertToASN1Data(tag), name);
		setType(NULL_TYPE);
	}
	
	/**
	 * Creates an {@link ASN1Null} component with the specific data including the tag and the length.
	 * @param data - the data of the null component.
	 * @param name - the name of the component.
	 */
	public ASN1Null (ASN1Data data, String name) {
		super(data, name);
		setType(NULL_TYPE);
	}
	
	/**
	 * Creates an {@link ASN1Null} component with a specified tag.
	 * @param tag - the specified tag.
	 * @param table - a table of components that could be included in the null component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the null component can take.
	 * @throws ASN1Exception
	 */
	public ASN1Null (short tag, ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(convertToASN1Data(tag), table, name, contentDecoder);
		setType(NULL_TYPE);
	}
	
	/**
	 * Creates an {@link ASN1Null} component with the specific data including the tag and the length.
	 * @param data - the data of the null component.
	 * @param table - a table of components that could be included in the null component.
	 * @param name - the name of the component.
	 * @param possibleValues - the possible values that the null component can take.
	 * @throws ASN1Exception
	 */
	public ASN1Null (ASN1Data data, ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(data, table, name, contentDecoder);
		setType(NULL_TYPE);
	}
	
	/**
	 * Converts the value of the specified component to ASN1Data containing the tag
	 * and the length of the component.
	 * @param tag - the specified tag of the component.
	 * @return an ASN1Data object.
	 */
	public static ASN1Data convertToASN1Data (short tag) {
		
		ASN1Data asn1Data = null;
		asn1Data = new ASN1Data (2);
		asn1Data.setDataAtIndex(tag, TAG_INDEX);
		asn1Data.setDataAtIndex((short) 0, LENGTH_INDEX);
		return (asn1Data);
	}
	
	/**
	 * Returns the object in a printable string
	 * @param tabs the tabs in front of the visual representation
	 * @return a String object to be printed
	 */
	public String printData (String tabs) {
		return (tabs + getName() + "\n");
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
		return result;
	}
}
