package asn1;

import java.util.ArrayList;

import asn1.com.ASN1ContentDecoder;
import asn1.com.ASN1Exception;

/**
 * The ASN1Component class represents a general ASN.1 component. It extends the {@link ASN1Data} class. 
 * The class must be extended to more specific ASN.1 components.
 * 
 * @author Panos Katsikogiannis
 */
public class ASN1Component extends ASN1Data {
	
	private short tag;
	private int lengthBytes						= 1;
	private long length 						= 0;
	private ASN1Data contents 					= null;
	private boolean constructed 				= false;
	private String name 						= "COMPONENT";
	private ArrayList<ASN1Component> components = null;
	private short type 							= NONE_TYPE;
	private ASN1ContentDecoder contentDecoder	= null;
	
	public ASN1Component () {}
	
	/**
	 * Creates an {@link ASN1Component} holding the given ASN.1 data.
	 * @param data - the data to be included in the component.
	 * @param name - the name of the created component.
	 * @param lengthBytes - the number of length bytes of the component.
	 */
	public ASN1Component (ASN1Data data, String name, int lengthBytes) {
		super(data.getAllData());
		setName(name);
		this.lengthBytes = lengthBytes;
		// Length is equal to ASN.1 data length minus the tag length and length byte length
		this.length = data.getDataLength() - (1 + lengthBytes);
		setContents(data);
		this.constructed = false;
	}
	
	/**
	 * Creates an {@link ASN1Component} holding the given ASN.1 data.
	 * @param data - the data to be included in the component, including the tag and length.
	 * @param name - the name of the created component.
	 */
	public ASN1Component (ASN1Data data, String name) {
		super(data.getAllData());
		int offset = 0;
		int firstLengthByte = 0;
		/* Set the tag */
		setTag(data.getDataAtIndex(TAG_INDEX));
		/* Set the name */
		setName(name);
		/* Find length bytes, length and set data */
		firstLengthByte = data.getDataAtIndex(LENGTH_INDEX);
		if ((firstLengthByte & MASK_EXTENDED) == EXTENDED_LENGTH) {
			offset = firstLengthByte & (~MASK_EXTENDED);
			for (int i = 1; i <= offset; i++) {
				this.length += (int) (data.getDataAtIndex(LENGTH_INDEX + i) * Math.pow(256, offset - i));
			}
		} else this.length += firstLengthByte;
		this.lengthBytes = offset + 1;
		setContents(new ASN1Data(data.getDataFromOffsetUntilIndex(DATA_INDEX + offset, (int) (DATA_INDEX + offset + length))));
		/* Check for constructed and continue analysis if necessary */
		if ((this.tag & MASK_CONSTRUCTED) == CONSTRUCTED) {
			this.constructed = true;
			components = new ArrayList<ASN1Component>();
			ASN1Decoder decoder = new ASN1Decoder(getContents());
			for (int i = 0; i < decoder.getAll().size(); i++) {
				components.add(decoder.getAll().get(i));
			}
		}
		else this.constructed = false;
	}
	
	/**
	 * Creates a new ASN.1 component holding the given ASN.1 data.
	 * @param data - the data to be included in the component, including the tag and length.
	 * @param table - a table of components that could be included in the component.
	 * @param name - the name of the created component.
	 * @param possibleValues - the possible values that the component can take.
	 * @throws ASN1Exception
	 */
	public ASN1Component(ASN1Data data, ASN1CustomComponent[] table, String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		super(data.getAllData());
		int offset = 0;
		int firstLengthByte = 0;
		/* Set the tag */
		setTag(data.getDataAtIndex(TAG_INDEX));
		/* Set the name */
		setName(name);
		this.contentDecoder = contentDecoder;
		/* Find length bytes, length and set data */
		firstLengthByte = data.getDataAtIndex(LENGTH_INDEX);
		if ((firstLengthByte & MASK_EXTENDED) == EXTENDED_LENGTH) {
			offset = firstLengthByte & (~MASK_EXTENDED);
			for (int i = 1; i <= offset; i++) {
				this.length += (int) (data.getDataAtIndex(LENGTH_INDEX + i) * Math.pow(256, offset - i));
			}
		} else this.length += firstLengthByte;
		this.lengthBytes = offset + 1;
		//setContents(new ASN1Data(data.getStringDataOffsetToIndex(DATA_INDEX + offset, (int) (DATA_INDEX + offset + length))));
		setContents(new ASN1Data(data.getDataFromOffsetUntilIndex(DATA_INDEX + offset, (int) (DATA_INDEX + offset + length))));
		/* Check for constructed and continue analysis if necessary */
		if ((this.tag & MASK_CONSTRUCTED) == CONSTRUCTED) {
			this.constructed = true;
			components = new ArrayList<ASN1Component>();
			ASN1Decoder decoder = new ASN1Decoder(getContents(), table);
			for (int i = 0; i < decoder.getAll().size(); i++) {
				components.add(decoder.getAll().get(i));
			}
		}
		else this.constructed = false;
	}

	/**
	 * Retrieves the first component found in the passed ASN.1 data.
	 * @param asn1Data - the passed ASN.1 data.
	 * @return the first component found in the passed ASN.1 data.
	 */
	public static ASN1Component retrieveComponent (ASN1Data asn1Data) {
		
		return (new ASN1Component(getFirstComponent(asn1Data), "UNKNOWN "));
	}
	
	/**
	 * Retrieves the first component found in the passed ASN.1 data only in case that it is the 
	 * component that is defined by the expectedComponent.
	 * @param asn1Data - the passed ASN.1 data.
	 * @param expectedComponent - the definition of the expected component to be found.
	 * @return the component found in the passed ASN.1 data if it was the expected component, null otherwise.
	 * @throws ASN1Exception in case the expected component is mandatory but not found.
	 */
	public static ASN1Component retrieveComponent (ASN1Data asn1Data, 
			ASN1CustomComponent expectedComponent) throws ASN1Exception {
		
		// Check if we have the expected component
		if (expectedComponent.getTag() == asn1Data.getDataAtIndex(TAG_INDEX)) {
			return (new ASN1Component(getFirstComponent(asn1Data), expectedComponent.getComponents(), 
					expectedComponent.getTagName(), expectedComponent.getContentDecoder()));
		} else {
			// No, check if the expected component is mandatory
			if (!expectedComponent.isOptional()) {
				throw new ASN1Exception(expectedComponent.getTagName() + " is mandatory!");
			} else return (null);
		}
	}
	
	/**
	 * Returns the first ASN.1 component found in the given ASN.1 data.
	 * @param data - the given ASN.1 data.
	 * @return the first ASN.1 component found in the given ASN.1 data.
	 */
	public static ASN1Data getFirstComponent (ASN1Data data) {
		int length = 0, lengthBytes, firstLengthByte = 0, offset = 0;
		
		firstLengthByte = data.getDataAtIndex(LENGTH_INDEX);
		
		if ((firstLengthByte & MASK_EXTENDED) == EXTENDED_LENGTH) {
			offset = firstLengthByte & (~MASK_EXTENDED);
			for (int i = 1; i <= offset; i++) {
				length += (data.getDataAtIndex(LENGTH_INDEX + i) * Math.pow(256, offset - i));
			}
		} else length += firstLengthByte;
		lengthBytes = offset + 1;
		return (new ASN1Data(data.getStringDataOffsetToIndex(0, 1 + lengthBytes + length)));
	}
	
	/**
	 * Sets the Tag of this ASN.1 component.
	 * @param tag - the Tag to be set.
	 */
	public void setTag (short tag) {
		this.tag = tag;
	}
	
	/**
	 * Sets the Length of this ASN.1 component.
	 * @param length  - the Length to be set.
	 */
	public void setLength (long length) {
		this.length = length;
	}
	
	/**
	 * Sets the Name of this ASN.1 component.
	 * @param name - the name to be set.
	 */
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * Returns the type of the component.
	 * @return the type of the component.
	 */
	public void setType (short type) {
		this.type = type;
	}
	
	/**
	 * Sets the contents of this ASN.1 component.
	 * @param contents - the contents to be set.
	 */
	public void setContents (ASN1Data contents) {
		if (contents != null) {
			this.contents = contents;
		} else {
			// Error --> No contents to set
		}
	}
	
	/**
	 * Returns true is the component is constructed, false otherwise.
	 * @return true is the component is constructed, false otherwise.
	 */
	public boolean isConstructed () {
		return (constructed);
	}
	
	/**
	 * Returns the Tag of this ASN.1 component.
	 * @return the saved Tag.
	 */
	public short getTag () {
		return (this.tag);
	}
	
	/**
	 * Returns the Length of this ASN.1 component.
	 * @return the saved Length.
	 */
	public long getLength () {
		return (this.length);
	}
	
	/**
	 * Returns the contents of this ASN.1 component.
	 * @return the saved contents.
	 */
	public ASN1Data getContents () {
		return (this.contents);
	}
	
	public int getLengthBytes() {
		return (this.lengthBytes);
	}
	
	/**
	 * Returns the name of the component.
	 * @return the name of the component.
	 */
	public String getName() {
		return (this.name);
	}
	
	/**
	 * Returns the type of the component.
	 * @return the type of the component.
	 */
	public short getType () {
		return (this.type);
	}
	
	/**
	 * Returns the possible values of this component.
	 * @return the possible values of this component.
	 */
	public ASN1ContentDecoder getContentDecoder () {
		return (this.contentDecoder);
	}
	
	/**
	 * Adds new contents at the end of the existing contents of this ASN.1 component. It also updates the Length
	 * of the ASN.1 component.
	 * @param newContents -  the additional contents to be added.
	 */
	public void addContents (ASN1Data newContents) {
		this.length += newContents.getDataLength();
		this.contents.addData(newContents.toString());
	}
	
	/**
	 * Converts the specified length to a hex string in order to be added in the ASN1Data object. It also 
	 * contains the number of length octet, in case that the length is more than one octet.
	 * @param length - the length to be converted.
	 * @return the length octets in a hex string.
	 */
	public static String convertLengthToASN1Data (int length) {
		
		String lengthString = Integer.toHexString(length);
		if ((lengthString.length() % 2) == 1) lengthString = "0" + lengthString;
		
		int offset = lengthString.length() / 2;
		if (offset > 1) {
			lengthString = Integer.toHexString(offset | 0x80) + lengthString;
		} 
		return (lengthString);
	}
	
	/**
	 * Writes at the end of the existing ASN1Data object the current component.
	 * @param data - the existing ASN1Data object.
	 * @return the number of the written octets.
	 */
	public int writeComponent (ASN1Data data) {
		String componentData = getAllASN1Data().toString();
		data.addData(componentData);
		return (componentData.length() / 2);
	}
	
	/**
	 * Returns the object in a printable string.
	 * @param tabs - the tabs in front of the visual representation.
	 * @return a String object to be printed.
	 * @deprecated
	 */
	public String printData (String tabs) {
		
		if (isConstructed()) {
			StringBuffer output = new StringBuffer();
			ASN1Component asn1Component;
			output.append(tabs + this.name + " {\n");
			// Check if component could not be analyzed
			if (components.size() == 0) {
				output.append(tabs + TABS + this.getContents().toString() + "\n");
			} else {
				for (int i = 0; i < this.components.size(); i++) {
					asn1Component = this.components.get(i);
					output.append(asn1Component.printData(tabs + TABS));
				}
			}
			
			return (output.append(tabs + "}\n").toString());
		} else {
			return (tabs + this.name + this.contents.toString() + "\n");
		}
	}
	
	/**
	 * Returns the list of components within this component.
	 * @return the list of components within this component.
	 */
	public ArrayList<ASN1Component> getComponents () {
		return components;
	}
	
	public void addComponent (ASN1Component component) {
		if (components == null) {
			components = new ArrayList<ASN1Component>();
		}
		components.add(component);
	}
	
	/**
	 * Returns the name of the value that this component holds (if there is any).
	 * @param tabs - the tabs in front of the visual representation.
	 * @return the name of the value that this component holds.
	 */
	public String getValueName (String tabs) {
		String output = "";
		
		if (contentDecoder != null) {
			if (contentDecoder.getResult() != null)
				output = contentDecoder.getResult();
			else output = contentDecoder.decode(getContents());
		}
		return (output);
	}
	
	/**
	 * Returns the data of the ASN.1 component in a {@link DataContainer}.
	 * @return the data of the ASN.1 component in a {@link DataContainer}.
	 */
	public DataContainer getDataContainer () {
		DataContainer result = null;
		short contData[];
		
		// Tag
		contData = new short[1];
		contData[0] = tag;
		result = new DataContainer(contData,  name, "", getDataLength(), true);
		// Length
		contData = new short[lengthBytes];
		for (int i = 0; i < lengthBytes; i++) {
			contData[i] = getDataAtIndex(i + 1);
		}
		result.addData(new DataContainer(contData, "Length", String.format("%d", length), lengthBytes, false));
		// Check if it is a constructed component
		if (constructed) {
			// Contents
			for (int i = 0; i < components.size(); i++) {
				result.addData(components.get(i).getDataContainer());
			}
		} else {
			// Contents
			contData = new short[(int) length];
			for (int i = 0; i < length; i++) {
				contData[i] = getDataAtIndex(i + 1 + lengthBytes);
			}
			result.addData(new DataContainer(contData, "Contents", "Not Analyzed", (int) length, false));
		}
		return result;
	}
}
