package asn1.com;

import java.util.ArrayList;

import asn1.ASN1Component;
import asn1.ASN1CustomComponent;
import asn1.component.ASN1Choice;

public class ASN1Static implements ASN1Definitions {

	private static ASN1CustomComponent reference [] = null;
	/**
	 * This method searches in the list of the passed ASN1Components for a component that holds the given name.
	 * @param components The list of ASN1Components to search in
	 * @param name The name of the ASN1 component to be found in the list
	 * @return the ASN1Component in the list that holds the given name, null in no match is found
	 */
	public static ASN1Component getComponentByName (ArrayList<ASN1Component> components, String name) {
		
		if (components != null) {
			// Check for all components in the list
			for (int i = 0; i < components.size(); i++) {
				ASN1Component activeASN1Component = components.get(i);
				// Check if this is the requested component
				if (activeASN1Component.getName().equals(name)) {
					if (activeASN1Component.getType() == CHOICE_TYPE) {
						return ((ASN1Choice)activeASN1Component).getValue();
					} else return activeASN1Component;
				}
				// Check if active component is constructed (holds more components)
				if (activeASN1Component.isConstructed()) {
					// Find the ASN1 component holding the requested name
					activeASN1Component = getComponentByName(activeASN1Component.getComponents(), name);
					// Check if requested component has been found
					if (activeASN1Component != null) return activeASN1Component;
				}
			}
		}
		return null;
	}
	
	/**
	 * Sets the reference custom component table to the specific value.
	 * @param reference - the specific reference custom component table.
	 */
	public static void setReferenceComponent (ASN1CustomComponent[] reference) {
		ASN1Static.reference = reference;
	}
	
	/**
	 * Returns the reference custom component table.
	 * @return the reference custom component table.
	 */
	public static ASN1CustomComponent[] getReferenceComponent () {
		return ASN1Static.reference;
	}
}
