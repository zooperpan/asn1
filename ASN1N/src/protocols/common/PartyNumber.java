package protocols.common;

import asn1.ASN1CustomComponent;
import asn1.component.decoder.ASN1EnumeratedDecoder;
import protocols.Tags;

/**
 * Defines decoding objects for Addressing.
 * 
 * @author Panos Katsikogiannis
 */
public interface PartyNumber extends Tags {

	/**
	 * <pre>
	 * PublicTypeOfNumber ::= ENUMERATED {
	 *     unknown                  ( 0 ), 
	 * -- if used number digits carry prefix indicating type
	 * -- of number according to national recommendations.
	 *     internationalNumber      ( 1 ), 
	 *     nationalNumber           ( 2 ), 
	 *     networkSpecificNumber    ( 3 ), 
	 * -- not used, value reserved
	 *     subscriberNumber         ( 4 ), 
	 *     abbreviatedNumber        ( 6 ) }
	 * </pre>
	 */
	final static  ASN1EnumeratedDecoder PTPublicTypeOfNumber = new ASN1EnumeratedDecoder(
			new String[] {"unknown", "internationalNumber", "nationalNumber", "networkSpecificNumber",
					"subscriberNumber", "abbreviatedNumber"},
			new long[] {0, 1, 2, 3, 4, 6}
	);
	
	/**
	 * <pre>
	 * PrivateTypeOfNumber ::= ENUMERATED {
	 *     unknown                 ( 0 ), 
	 *     level2RegionalNumber    ( 1 ), 
	 *     level1RegionaNumber     ( 2 ), 
	 *     pINSpecificNumber       ( 3 ), 
	 *     localNumber             ( 4 ), 
	 *     abbreviatedNumber       ( 6 ) }
	 * </pre>
	 */
	final static  ASN1EnumeratedDecoder PTPrivateTypeOfNumber = new ASN1EnumeratedDecoder(
			new String[] {"unknown", "level2RegionalNumber", "level1RegionalNumber", "pTNSpecificNumber",
					"localNumber", "abbreviatedNumber"},
			new long[] {0, 1, 2, 3, 4, 6}
	);
	
	/**
	 * <pre>
	 * ScreeningIndicator ::= ENUMERATED {
	 *     userProvidedNotScreened          ( 0 ), 
	 * -- number was provided by a remote user terminal equip-
	 * -- ment, and has been screened by a network that is not
	 * -- the local public or the local private network.
	 *     userProvidedVerifiedAndPassed    ( 1 ), 
	 * -- number was provided by a remote user terminal equip-
	 * -- ment (or by a remote private network), and has been
	 * -- screened by the local public or private network.
	 *     userProvidedVerifiedAndFailed    ( 2 ), 
	 * -- not used, value reserved.
	 *     networkProvided                  ( 3 ) }
	 * -- number was provided by local public or local
	 * -- private network.
	 * </pre>
	 */
	final static  ASN1EnumeratedDecoder PTScreeningIndicator = new ASN1EnumeratedDecoder(
			new String[] {"userProvidedNotScreened", "userProvidedVerifiedAndPassed", 
					"userProvidedVerifiedAndFailed", "networkProvided"},
			new long[] {0, 1, 2, 3}
	);
	
	/**
	 * <pre>
	 * PublicPartyNumber ::= SEQUENCE {
	 *     publicTypeOfNumber   PublicTypeOfNumber, 
	 *     publicNumberDigits   NumberDigits}
	 * </pre>
	 */
	final static ASN1CustomComponent PublicPartyNumber [] = {
		new ASN1CustomComponent(ASN1_T_NUM_PUBLIC_TON, 				"publicTypeOfNumber", 
				ENUM_TYPE, 		null, 								false,	null,			PTPublicTypeOfNumber),
		new ASN1CustomComponent(ASN1_T_NUM_NUMBER_DIGITS, 			"publicNumberDigits", 
				NUM_STR_TYPE, 	null, 								false,	null,			null)
	};
	
	/**
	 * <pre>
	 * PrivatePartyNumber ::= SEQUENCE {
	 *     privateTypeOfNumber   PrivateTypeOfNumber, 
	 *     privateNumberDigits   NumberDigits}
	 * </pre>
	 */
	final static ASN1CustomComponent PrivatePartyNumber [] = {
		new ASN1CustomComponent(ASN1_T_NUM_PRIVATE_TON, 			"privateTypeOfNumber", 
				ENUM_TYPE, 		null, 								false,	null,			PTPrivateTypeOfNumber),
		new ASN1CustomComponent(ASN1_T_NUM_NUMBER_DIGITS, 			"privateNumberDigits", 
				NUM_STR_TYPE, 	null, 								false,	null,			null)
	};
	
	/**
	 * <pre>
	 * PartyNumber ::= CHOICE {
	 *     unknownPartyNumber              [0] IMPLICIT NumberDigits, 
	 * -- the numbering plan is the default numbering
	 * -- plan of the network. It is recommended that
	 * -- this value is used.
	 *     publicPartyNumber               [1] IMPLICIT PublicPartyNumber, 
	 * -- the numbering plan is according to
	 * -- Recommendations E.163 and E.164.
	 *     dataPartyNumber                 [3] IMPLICIT NumberDigits, 
	 * -- not used, value reserved.
	 *     telexPartyNumber                [4] IMPLICIT NumberDigits, 
	 * -- not used, value reserved.
	 *     privatePartyNumber              [5] IMPLICIT PrivatePartyNumber, 
	 *     nationalStandardPartyNumber     [8] IMPLICIT NumberDigits}
	 * -- not used, value reserved.
	 * </pre>
	 */
	final static ASN1CustomComponent PartyNumber [] = {
		new ASN1CustomComponent(ASN1_T_NUM_PARTY_NUM_UNKNOWN, 		"unknownPartyNumber", 
				NUM_STR_TYPE, 	null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_PARTY_NUM_PUBLIC, 		"publicPartyNumber", 
				SEQ_TYPE,		PublicPartyNumber, 					true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_PARTY_NUM_DATA, 			"dataPartyNumber", 
				NUM_STR_TYPE,	null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_PARTY_NUM_TELEX, 		"telexPartyNumber", 
				NUM_STR_TYPE, 	null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_PARTY_NUM_PRIVATE, 		"privatePartyNumber", 
				SEQ_TYPE,		PrivatePartyNumber, 				true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_PARTY_NUM_NATIONAL, 		"nationalStandardPartyNumber", 
				NUM_STR_TYPE, 	null, 								true,	null,			null)
	};
	
	/**
	 * <pre>
	 * UserSpecifiedSubaddress ::= SEQUENCE {
	 *     SubaddressInformation, 
	 *     oddCountIndicator   BOOLEAN OPTIONAL}
	 * </pre>
	 */
	final static ASN1CustomComponent UserSpecifiedSubaddress [] = {
		new ASN1CustomComponent(ASN1_T_NUM_SUBADDRESS_INFO, 		"SubaddressInformation", 
				OCTET_STR_TYPE, null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_ODD_COUNT_IND, 			"oddCountIndicator", 
				BOOLEAN_TYPE, 	null, 								true,	null,			null)
	};
	
	/**
	 * <pre>
	 * PartySubaddress ::= CHOICE {
	 *     UserSpecifiedSubaddress, 
	 *     NSAPSubaddress}
	 * </pre>
	 */
	final static ASN1CustomComponent PartySubaddress [] = {
		new ASN1CustomComponent(ASN1_T_NUM_USER_SUBADDRESS, 		"UserSpecifiedSubaddress", 
				SEQ_TYPE, 		UserSpecifiedSubaddress, 			true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_NSAP_SUBADDRESS, 		"NSAPSubaddress", 
				OCTET_STR_TYPE, null, 								true,	null,			null)
	};
	
	/**
	 * <pre>
	 * PartyNumAPartySubaddress [tagged] PartySubaddress
	 * </pre>
	 */
	final static ASN1CustomComponent PartyNumAPartySubaddress [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"partySubaddress", 
				CHOICE_TYPE, 	PartySubaddress,					false,	null,			null)
	};
	
	/**
	 * <pre>
	 * Address ::= SEQUENCE {
	 *     PartyNumber, 
	 *     PartySubaddress OPTIONAL}
	 * </pre>
	 */
	final static ASN1CustomComponent PTAddress [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"PartyNumber", 
				CHOICE_TYPE, 	PartyNumber, 						false,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"PartySubaddress", 
				CHOICE_TYPE, 	PartySubaddress, 					true,	null,			null)
	};
	
	/**
	 * <pre>
	 * AddressScreened ::= SEQUENCE {
	 *     PartyNumber, 
	 *     ScreeningIndicator, 
           PartySubaddress OPTIONAL}
	 * </pre>
	 */
	final static ASN1CustomComponent PTAddressScreened [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"PartyNumber", 
				CHOICE_TYPE, 	PartyNumber, 						false,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_SCREENING_IND, 			"ScreeningIndicator", 
				ENUM_TYPE, 		null, 								false,	null,			PTScreeningIndicator),
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"PartySubaddress", 
				CHOICE_TYPE, 	PartySubaddress, 					true,	null,			null)
	};
	
	/**
	 * <pre>
	 * PresentedAddressScreened ::= CHOICE {
	 *     presentationAllowedAddress              [0] IMPLICIT AddressScreened, 
	 *     presentationRestricted                  [1] IMPLICIT NULL, 
	 *     numberNotAvailableDueToInterworking     [2] IMPLICIT NULL, 
	 *     presentationRestrictedAddress           [3] IMPLICIT AddressScreened}
	 * </pre>
	 */
	final static ASN1CustomComponent PresentedAddressScreened [] = {
		new ASN1CustomComponent(ASN1_T_NUM_PRES_ALLOWED, 			"presentationAlIowedAddress", 
				SEQ_TYPE, 		PTAddressScreened, 					true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_PRES_RESTR, 				"presentationRestricted", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_NA_INTERW, 				"numberNotAvailableDueTolnterworking", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_PRES_RESTR_ADR, 			"presentationRestrictedAddress", 
				SEQ_TYPE, 		PTAddressScreened, 					true,	null,			null)
	};
	
	/**
	 * <pre>
	 * PresentationAddress [tagged] PresentedAddressScreened
	 * </pre>
	 */
	final static ASN1CustomComponent PresentationAddress [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"PresentedAddressScreened", 
				CHOICE_TYPE, 	PresentedAddressScreened,			false,	null,			null)
	};
	
	/**
	 * <pre>
	 * PresentedAddressUnscreened ::= CHOICE {
	 *     presentationAllowedAddress              [0] IMPLICIT Address, 
	 *     presentationRestricted                  [1] IMPLICIT NULL, 
	 *     numberNotAvailableDueToInterworking     [2] IMPLICIT NULL, 
	 *     presentationRestrictedAddress           [3] IMPLICIT Address}
	 * </pre>
	 */
	final static ASN1CustomComponent PresentedAddressUnscreened [] = {
		new ASN1CustomComponent(ASN1_T_NUM_PRES_ALLOWED, 			"presentationAllowedAddress", 
				SEQ_TYPE, 		PTAddress,							true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_PRES_RESTR, 				"presentationRestricted", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_NA_INTERW, 				"numberNotAvailableDueTolnterworking", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_PRES_RESTR_ADR, 			"presentationRestrictedAddress", 
				SEQ_TYPE, 		PTAddress,							true,	null,			null)
	};
	
	/**
	 * <pre>
	 * PresentationAddressUnscreened [tagged] PresentedAddressUnscreened
	 * </pre>
	 */
	final static ASN1CustomComponent PresentationAddressUnscreened [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"PresentedAddressUnscreened", 
				CHOICE_TYPE, 	PresentedAddressUnscreened,			false,	null,			null)
	};
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	final static ASN1CustomComponent PresentationAllowedNumberScr [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"partyNumber", 
				CHOICE_TYPE, 	PartyNumber, 						false,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_SCREENING_IND, 			"screeningIndicator", 
				ENUM_TYPE, 		null, 								false,	null,			PTScreeningIndicator)
	};
	
	/**
	 * <pre>
	 * PresentedNumberScreened ::= CHOICE {
	 *     presentationAllowedAddress              [0] IMPLICIT NumberScreened, 
	 *     presentationRestricted                  [1] IMPLICIT NULL, 
	 *     numberNotAvailableDueToInterworking     [2] IMPLICIT NULL, 
	 *     presentationRestrictedAddress           [3] IMPLICIT NumberScreened}
	 * </pre>
	 */
	final static ASN1CustomComponent PresentedNumberScreened [] = {
		new ASN1CustomComponent(ASN1_T_NUM_PRES_ALLOWED, 			"presentationAllowedNumber", 
				SEQ_TYPE, 		PresentationAllowedNumberScr, 		true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_PRES_RESTR, 				"presentationRestricted", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_NA_INTERW, 				"numberNotAvailableDueToInterworking", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_PRES_RESTR_ADR, 			"presentationRestrictedNumber", 
				SEQ_TYPE, 		PresentationAllowedNumberScr, 		true,	null,			null)
	};
	
	/**
	 * <pre>
	 * PartyNumPresScreened [tagged] PresentedNumberScreened
	 * </pre>
	 */
	final static ASN1CustomComponent PartyNumPresScreened [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"PresentedNumberScreened", 
				CHOICE_TYPE, 	PresentedNumberScreened, 			false,	null,			null)
	};
	
	/**
	 * <pre>
	 * PresentationAllowedNumberUnscr [tagged] PartyNumber
	 * </pre>
	 */
	final static ASN1CustomComponent PresentationAllowedNumberUnscr [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"partyNumber", 
				CHOICE_TYPE, 	PartyNumber, 						true,	null,			null)
	};
	
	/**
	 * <pre>
	 * PresentationRestrictedNumUnscr [tagged] PartyNumber 
	 * </pre>
	 */
	final static ASN1CustomComponent PresentationRestrictedNumUnscr [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"partyNumber", 
				CHOICE_TYPE, 	PartyNumber, 						true,	null,			null)
	};
	
	/**
	 * <pre>
	 * PresentedNumberUnscreened ::= CHOICE {
	 *     presentationAllowedAddress              [0] PartyNumber, 
	 *     presentationRestricted                  [1] IMPLICIT NULL, 
	 *     numberNotAvailableDueToInterworking     [2] IMPLICIT NULL, 
	 *     presentationRestrictedAddress           [3] PartyNumber}
	 * </pre>
	 */
	final static ASN1CustomComponent PresentedNumberUnscreened [] = {
		new ASN1CustomComponent(ASN1_T_NUM_PRES_ALLOWED, 			"presentationAllowedNumber", 
				SEQ_TYPE, 		PresentationAllowedNumberUnscr, 	true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_PRES_RESTR, 				"presentationRestricted", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_NA_INTERW, 				"numberNotAvailableDueToInterworking", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_PRES_RESTR_ADR, 			"presentationRestrictedNumber", 
				SEQ_TYPE, 		PresentationRestrictedNumUnscr, 	true,	null,			null)
	};

	/**
	 * <pre>
	 * PartyNumberPresUnscreened [tagged] PresentedNumberUnscreened
	 * </pre>
	 */
	final static ASN1CustomComponent PartyNumberPresUnscreened [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"PresentedNumberUnscreened", 
				CHOICE_TYPE, 	PresentedNumberUnscreened,			false,	null,			null)
	};
}
