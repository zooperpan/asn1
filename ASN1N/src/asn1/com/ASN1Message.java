package asn1.com;

import java.util.ArrayList;

import asn1.ASN1Component;
import asn1.ASN1Data;
import asn1.DataContainer;

/**
 * The ASN1Message class represents a message that contains ASN.1 data. The data are analyzed
 * to more specific ASN.1 components by the abstract method {@link #decodeMessage(ASN1Data)}.
 * 
 * @author Panos Katsikogiannis
 */
public abstract class ASN1Message {

	protected ArrayList<ASN1Component> components 	= null;
	
	/**
	 * Creates a new {@link ASN1Message}.
	 * @param asn1Data - the ASN.1 data that the message holds.
	 * @throws ASN1Exception 
	 */
	public ASN1Message (ASN1Data asn1Data) throws ASN1Exception {
		components = new ArrayList<ASN1Component>();
		decodeMessage(asn1Data);
	}
	
	/**
	 * Decodes the given ASN.1 data. This is an abstract method and needs to be implemented
	 * by the classes that extends this one.
	 * @param asn1Data - the ASN.1 data to be analyzed.
	 * @throws ASN1Exception 
	 */
	protected abstract void decodeMessage (ASN1Data asn1Data) throws ASN1Exception;
	
	/**
	 * Returns the number of ASN.1 components in the message.
	 * @return the number of ASN.1 components in the message.
	 */
	public int numOfComponents () {
		return this.components.size();
	}
	
	/**
	 * Returns the ASN.1 component at the specified position in the message.
	 * @param index index of the component to return.
	 * @return the component at the specified position in the message.
	 */
	public ASN1Component get (int index) {
		return this.components.get(index);
	}
	
	/**
	 * Returns the whole ASN.1 component list held in this message.
	 * @return the held ASN.1 component list.
	 */
	public ArrayList<ASN1Component> getComponents () {
		return components;
	}
	
	/**
	 * Returns the data of the ASN.1 components in a {@link DataContainer}.
	 */
	public DataContainer getDataContainer () {
		return null;
	}
}
