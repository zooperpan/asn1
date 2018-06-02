package protocols.common;

import asn1.ASN1CustomComponent;
import asn1.component.decoder.ASN1IntegerDecoder;

public interface NameOperations extends PartyNumber {

	/**
	 * <pre>
	 * CharacterSet ::= INTEGER {
	 *     unknown      ( 0 ), 
	 *     iso8859-1    ( 1 ), 
	 * -- The value 2 was assigned for CCITT Rec. T.61
	 * -- which has been withdrawn by ITU-T.
	 *     iso8859-2    ( 3 ), 
	 *     iso8859-3    ( 4 ), 
	 *     iso8859-4    ( 5 ), 
	 *     iso8859-5    ( 6 ), 
	 *     iso8859-7    ( 7 )} ( 0 .. 255 )
	 * </pre>
	 */
	final static  ASN1IntegerDecoder NOCharacterSet = new ASN1IntegerDecoder(
			new String[] {"unknown", "iso8859-1", "iso8859-2", "iso8859-3", "iso8859-4", "iso8859-5",
					"iso8859-7", "iso10646-BmpString", "iso10646-utf-8String"},
			new long[] {0, 1, 3, 4, 5, 6, 7, 8, 9}
	);
	
	/**
	 * <pre>
	 * NameSet ::= SEQUENCE {
	 *     nameData       NameData, 
	 *     characterSet   CharacterSet OPTIONAL}
	 * </pre>
	 */
	final static ASN1CustomComponent NoNameSet [] = {
		new ASN1CustomComponent(ASN1_T_NAME_DATA,					"nameData", 
				VIS_STR_TYPE,	null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_NAME_CHARACTER_SET, 			"characterSet", 
				INT_TYPE, 		null, 								true,	null,			NOCharacterSet)
	};
	
	/**
	 * <pre>
	 * NamePresentationAllowed ::= CHOICE {
	 *     namePresentationAllowedSimple       [0] IMPLICIT NameData, 
	 *     namePresentationAllowedExtended     [1] IMPLICIT NameSet}
	 * </pre>
	 */
	final static ASN1CustomComponent NoNamePresAllowed [] = {
		new ASN1CustomComponent(ASN1_T_NAME_PRES_ALLOWED_SIMPLE,	"namePresentationAllowedSimple", 
				VIS_STR_TYPE,	null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NAME_PRES_ALLOWED_EXT, 		"namePresentationAllowedExtended", 
				SEQ_TYPE, 		NoNameSet, 							true,	null,			null)
	};

	/**
	 * <pre>
	 * NamePresentationRestricted ::= CHOICE {
	 *     namePresentationRestrictedSimple       [2] IMPLICIT NameData, 
	 *     namePresentationRestrictedExtended     [3] IMPLICIT NameSet, 
	 *     namePresentationRestrictedNull         [7] IMPLICIT NULL}
	 * </pre>
	 */
	final static ASN1CustomComponent NoNamePresRestricted [] = {
		new ASN1CustomComponent(ASN1_T_NAME_PRES_RESTR_SIMPLE, 		"namePresRestrictedSimple", 
				VIS_STR_TYPE,	null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NAME_PRES_RESTR_EXT, 		"nameSet", 
				SEQ_TYPE, 		NoNameSet, 							true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NAME_PRES_RESTR_NULL, 		"namePresRestrictedNull", 
				NULL_TYPE, 		null, 								true,	null,			null),
	};
	
	/**
	 * <pre>
	 * Name ::= CHOICE {
	 *     NamePresentationAllowed, 
	 *     NamePresentationRestricted, 
           NameNotAvailable,
           nameNotAvailableRestricted}
	 * </pre>
	 */
	final static ASN1CustomComponent NoNameChoice [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"namePresentationAllowed", 
				CHOICE_TYPE, 	NoNamePresAllowed, 					true,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"namePresentationRestricted", 
				CHOICE_TYPE, 	NoNamePresRestricted, 				true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NAME_NOT_AVAILABLE, 			"nameNotAvailable", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NAME_NOT_AVAILABLE_REST, 	"nameNotAvailableRestricted", 
				NULL_TYPE, 		null,								true,	null,			null)
	};
	
	/**
	 * <pre>
	 * NoName = [tagged] Name
	 * </pre>
	 */
	final static ASN1CustomComponent NoName [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"name", 
				CHOICE_TYPE, 	NoNameChoice, 						true,	null,			null)
	};
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	final static ASN1CustomComponent NoArgumentExtension [] = {
		new ASN1CustomComponent(ASN1_T_EXTENSION_VALUE, 			"extension", 
				OBJ_ID_TYPE, 	null, 								true,	null,			null)
	};
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	final static ASN1CustomComponent NoArgumentExtensionSeqOf [] = {
		new ASN1CustomComponent(ASN1_T_EXTENSION, 					"argumentExtension", 
				SEQ_TYPE, 		NoArgumentExtension, 				true,	null,			null)
	};
	
	/**
	 * <pre>
	 * CHOICE {
	 *     [5] IMPLICIT Extension, 
	 *     [6] IMPLICIT SEQUENCE  OF Extension}
	 * </pre>
	 */
	final static ASN1CustomComponent NoNameArgExtChoice [] = {
		new ASN1CustomComponent(ASN1_T_NAME_EXTENSION, 				"Extension", 
				SEQ_TYPE, 		NoArgumentExtension, 				true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NAME_SEQ_OF_EXTENSION, 		"SeqOfExtension", 
				SEQ_TYPE, 		NoArgumentExtensionSeqOf, 			true,	null,			null)
	};
	
	/**
	 * <pre>
	 * SEQUENCE {
	 *     Name, 
	 *     CHOICE {
	 *         [5] IMPLICIT Extension, 
	 *         [6] IMPLICIT SEQUENCE  OF Extension} OPTIONAL}
	 * </pre>
	 */
	final static ASN1CustomComponent NoNameArgChoiceSequence [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"name", 
				CHOICE_TYPE, 	NoNameChoice, 						false,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"ExtensionChoice", 
				CHOICE_TYPE, 	NoNameArgExtChoice, 				true,	null,			null)
	};
	
	/**
	 * <pre>
	 * CHOICE {
	 *     Name, 
	 *     SEQUENCE {
	 *         Name, 
	 *         CHOICE {
	 *             [5] IMPLICIT Extension, 
	 *             [6] IMPLICIT SEQUENCE  OF Extension} OPTIONAL}}
	 * </pre>
	 */
	final static ASN1CustomComponent NoNameArgumentChoice [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"name", 
				CHOICE_TYPE, 	NoNameChoice, 						true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NAME_ARG_CHOICE_SEQ, 		"nameSequence", 
				SEQ_TYPE, 		NoNameArgChoiceSequence, 			true,	null,			null)
	};
	
	/**
	 * <pre>
	 * CallingName ::= OPERATION
	 *     ARGUMENT
	 *         CHOICE {
	 *             Name, 
	 *             SEQUENCE {
	 *                 Name, 
	 *                 CHOICE {
	 *                     [5] IMPLICIT Extension, 
	 *                     [6] IMPLICIT SEQUENCE OF Extension} OPTIONAL}}
	 * </pre>
	 */
	final static ASN1CustomComponent CallingName [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"CallingName", 
				CHOICE_TYPE, 	NoNameArgumentChoice, 				false,	null,			null)
	};
	
	/**
	 * <pre>
	 * CalledName ::= OPERATION
	 *     ARGUMENT
	 *         CHOICE {
	 *             Name, 
	 *             SEQUENCE {
	 *                 Name, 
	 *                 CHOICE {
	 *                     [5] IMPLICIT Extension, 
	 *                     [6] IMPLICIT SEQUENCE OF Extension} OPTIONAL}}
	 * </pre>
	 */
	final static ASN1CustomComponent CalledName [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"CalledName", 
				CHOICE_TYPE, 	NoNameArgumentChoice, 				false,	null,			null)
	};
	
	/**
	 * <pre>
	 * ConnectedName ::= OPERATION
	 *     ARGUMENT
	 *         CHOICE {
	 *             Name, 
	 *             SEQUENCE {
	 *                 Name, 
	 *                 CHOICE {
	 *                     [5] IMPLICIT Extension, 
	 *                     [6] IMPLICIT SEQUENCE OF Extension} OPTIONAL}}
	 * </pre>
	 */
	final static ASN1CustomComponent ConnectedName [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"ConnectedName", 
				CHOICE_TYPE, 	NoNameArgumentChoice, 				false,	null,			null)
	};
	
	/**
	 * <pre>
	 * BusyName ::= OPERATION
	 *     ARGUMENT
	 *         CHOICE {
	 *             Name, 
	 *             SEQUENCE {
	 *                 Name, 
	 *                 CHOICE {
	 *                     [5] IMPLICIT Extension, 
	 *                     [6] IMPLICIT SEQUENCE OF Extension} OPTIONAL}}
	 * </pre>
	 */
	final static ASN1CustomComponent BusyName [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"BusyName", 
				CHOICE_TYPE, 	NoNameArgumentChoice, 				false,	null,			null)
	};
}
