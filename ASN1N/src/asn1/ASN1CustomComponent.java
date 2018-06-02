package asn1;

import asn1.com.ASN1ContentDecoder;

/**
 * The ASN1CustomComponent class is used in order to define custom ASN.1 components. The tag, name, type, 
 * optional filed and more can be defined and so construct an APDU definition.
 * 
 * @author Panos Katsikogiannis
 */
public class ASN1CustomComponent {
	
	private short 				tag;
	private String 				tagName;
	private int 				tagType;
	private ASN1CustomComponent components [];
	private boolean 			optional;
	private String 				definedBy;
	private ASN1ContentDecoder 	contentDecoder;

	/**
	 * Creates an {@link ASN1CustomComponent}.
	 * @param tag - the tag of the component.
	 * @param tagName - the tag name of the component.
	 * @param tagType - the tag type of the component.
	 * @param components - an array of {@link ASN1CustomComponent ASN1CustomComponents} held by this component.
	 * @param optional - true if component should be optional, false otherwise.
	 * @param definedBy - the defined by string of the component.
	 * @param contentDecoder - the content decoder of the component.
	 */
	public ASN1CustomComponent (short tag, String tagName, int tagType, ASN1CustomComponent components[],
			boolean optional, String definedBy, ASN1ContentDecoder contentDecoder) {
		this.tag = tag;
		this.tagName = tagName;
		this.tagType = tagType;
		this.components = components;
		this.optional = optional;
		this.definedBy = definedBy;
		this.contentDecoder = contentDecoder;
	}

	/**
	 * Returns the tag of the custom component.
	 * @return the tag of the custom component.
	 */
	public short getTag() {
		return (tag);
	}

	/**
	 * Sets the tag of the custom component to the specific value.
	 * @param tag - the specific value of the tag.
	 */
	public void setTag(short tag) {
		this.tag = tag;
	}

	/**
	 * Returns the tag name of the custom component.
	 * @return the tag name of the custom component.
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * Sets the tag name of the custom component to the specific value.
	 * @param tagName - the specific value of the tag name.
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	/**
	 * Returns the tag type of the custom component.
	 * @return the tag type of the custom component.
	 */
	public int getTagType() {
		return (tagType);
	}

	/**
	 * Sets the tag type of the custom component to the specific value.
	 * @param tagType - the specific value of the tag type.
	 */
	public void setTagType(int tagType) {
		this.tagType = tagType;
	}

	/**
	 * Returns the array of {@link ASN1CustomComponent ASN1CustomComponents} held by this custom component.
	 * @return the array of ASN1CustomComponents held by this custom component.
	 */
	public ASN1CustomComponent[] getComponents() {
		return (components);
	}

	/**
	 * Sets the array of {@link ASN1CustomComponent ASN1CustomComponents} to the specific value.
	 * @param components - the specific value of the ASN1CustomComponents.
	 */
	public void setComponents(ASN1CustomComponent components[]) {
		this.components = components;
	}

	/**
	 * Returns true if the component is optional, false otherwise.
	 * @return true if the component is optional, false otherwise.
	 */
	public boolean isOptional() {
		return (optional);
	}

	/**
	 * Sets the optional fiend of the custom component to the specific value.
	 * @param optional - true if component should be optional, false otherwise.
	 */
	public void setOptional(boolean optional) {
		this.optional = optional;
	}
	
	/**
	 * Returns the defined by string of the custom component.
	 * @return the defined by string of the custom component.
	 */
	public String getDefinedBy() {
		return (definedBy);
	}

	/**
	 * Sets the defined by string of the custom component to the specific value.
	 * @param definedBy - the specific value of the defined by string.
	 */
	public void setDefinedBy(String definedBy) {
		this.definedBy = definedBy;
	}
	
	/**
	 * Returns the string representation (name) of the custom component. 
	 */
	public String toString () {
		return (this.tagName);
	}

	/**
	 * Returns the content decoder of the custom component.
	 * @return the content decoder of the custom component.
	 */
	public ASN1ContentDecoder getContentDecoder() {
		return (this.contentDecoder);
	}
	
	/**
	 * Sets the content decoder of the custom component to the specific value.
	 * @param contentDecoder - the specific value of the content decoder.
	 */
	public void setContentDecoder(ASN1ContentDecoder contentDecoder) {
		this.contentDecoder = contentDecoder;
	}
}
