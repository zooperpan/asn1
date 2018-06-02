package asn1.component;

import java.util.ArrayList;

import asn1.ASN1Component;
import asn1.ASN1CustomComponent;
import asn1.ASN1Data;
import asn1.DataContainer;
import asn1.com.ASN1ContentDecoder;
import asn1.com.ASN1Exception;

/**
 * The ASN1Choice class represents an ASN.1 choice component. It extends the more general
 * {@link ASN1Component}.
 * 
 * @author Panos Katsikogiannis
 */
public class ASN1Choice extends ASN1Component {

	private ASN1Component value = null;
	private String name			= "CHOICE";
	/**
	 * Creates an {@link ASN1Choice} component.
	 * @param tag - the tag of the component.
	 * @param value - the value of the choice component.
	 * @param table - a table of components that could be included in the choice component.
	 * @param name - the name of the component.
	 * @throws ASN1Exception in case the decoding fails.
	 */
	public ASN1Choice (ASN1Component asn1Component, ASN1CustomComponent table[], String name,
			ASN1ContentDecoder contentDecoder) throws ASN1Exception {
		value = asn1Component;
		this.name = name;
	}
	
	/**
	 * Returns true is the component is constructed, false otherwise.
	 * @return true is the component is constructed, false otherwise.
	 */
	public boolean isConstructed () {
		return (value.isConstructed());
	}
	
	/**
	 * Returns the Tag of this ASN.1 component.
	 * @return the saved Tag.
	 */
	public short getTag () {
		return (value.getTag());
	}
	
	/**
	 * Returns the Length of this ASN.1 component.
	 * @return the saved Length.
	 */
	public long getLength () {
		return (value.getLength());
	}
	
	/**
	 * Returns the contents of this ASN.1 component.
	 * @return the saved contents.
	 */
	public ASN1Data getContents () {
		return (value.getContents());
	}
	
	public int getLengthBytes() {
		return (value.getLengthBytes());
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
		return (CHOICE_TYPE);
	}
	
	/**
	 * Returns the possible values of this component.
	 * @return the possible values of this component.
	 */
	public ASN1ContentDecoder getContentDecoder () {
		return (value.getContentDecoder());
	}
	
	/**
	 * Returns the name of the value that this component holds (if there is any).
	 * @param tabs - the tabs in front of the visual representation.
	 * @return the name of the value that this component holds.
	 */
	public String getValueName (String tabs) {
		return (value.getValueName(tabs));
	}
	
	/**
	 * Returns the actual value of the choice component.
	 * @return the actual value of the choice component.
	 */
	public ASN1Component getValue () {
		return value;
	}
	
	/**
	 * Returns the list of components within this component.
	 * @return the list of components within this component.
	 */
	public ArrayList<ASN1Component> getComponents () {
		return value.getComponents();
	}
	
	/**
	 * Returns the value of the component in a printable string.
	 */
	public String toString () {
		return value.toString();
	}
	
	/**
	 * Returns the total length of the component.
	 */
	public int getDataLength () {
		return value.getDataLength();
	}
	
	/**
	 * Returns the object in a printable string.
	 * @param tabs - the tabs in front of the visual representation.
	 * @return a String object to be printed.
	 * @deprecated
	 */
	public String printData (String tabs) {
		return (tabs + getName() + "\n" + value.printData(tabs + TABS));
	}
	
	/**
	 * Returns the data of the ASN.1 component in a {@link DataContainer}.
	 */
	public DataContainer getDataContainer () {
		DataContainer result = new DataContainer(null, getName(), "", getDataLength(), true);
		result.addData(value.getDataContainer());
		return result;
	}
}
