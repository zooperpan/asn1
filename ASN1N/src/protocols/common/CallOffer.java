package protocols.common;

import protocols.Tags;
import asn1.ASN1CustomComponent;

public interface CallOffer extends Tags {
	
	final static ASN1CustomComponent CallOfferExtensionChoice [] = {
		new ASN1CustomComponent(ASN1_T_CO_EXT_NONE, 				"null", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_CO_EXTENSION_TAG_1, 			"extension", 
				NONE_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_CO_EXTENSION_TAG_2, 			"sequenceOfExtn", 
				NONE_TYPE, 		null, 								true,	null,			null)
	};
	
	final static ASN1CustomComponent CallOfferRequestArgument [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"DummyArg", 
				CHOICE_TYPE, 	CallOfferExtensionChoice, 			false,	null,			null)
	};
	
	final static ASN1CustomComponent CallOfferRequestResult [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"DummyRes", 
				CHOICE_TYPE, 	CallOfferExtensionChoice, 			false,	null,			null)
	};
}
