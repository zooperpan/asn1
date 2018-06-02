package protocols.common;

import asn1.ASN1CustomComponent;
import asn1.component.decoder.ASN1EnumeratedDecoder;
import asn1.component.decoder.ASN1IntegerDecoder;

/**
 * Defines decoding objects for Generic Functions.
 * 
 * @author Panos Katsikogiannis
 */
public interface Header extends PartyNumber {
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	final static ASN1CustomComponent OperationValue [] = {
		new ASN1CustomComponent(ASN1_T_OPERATION_LOCAL_VALUE, 		"operationLocalValue", 
				INT_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_OPERATION_GLOBAL_VALUE, 		"operationGlobalValue", 
				OBJ_ID_TYPE, 	null, 								true,	null,			null)
	};
	
	/**
	 * <pre>
	 * SEQUENCE {
	 *     operationValue   OPERATION, 
	 *     result           ANY DEFINED BY operationValue }
	 * </pre>
	 */
	final static ASN1CustomComponent RetResultOperationSequence [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"operationValue", 
				CHOICE_TYPE, 	OperationValue, 					false,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"result", 
				ANY_BY_TYPE, 	null, 								true,	null,			null)
	};
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	final static  ASN1IntegerDecoder localErrorValues = new ASN1IntegerDecoder(
			new String[] {	"userNotSubscribed",
							"notAvailable",
							"notImplemented",
							"invalidServedUserNr",
							"invalidCallState",
							"basicServiceNotProvided",
							"notIncomingCall",
							"supplementaryServiceInteractionAllowed",
							"resourceUnavailable",
							"temporarilyUnavailable",
							"collision",
							"cretiriaPermanentUnachievable",
							"cretiriaTemporarilyUnachievable",
							"invalidRerouteingNumber",
							"unrecognizedCallIdentity",
							"establishmentFailure",
							"notAuthorized",
							"unspecified",
							"notBusy",
							"shortTermRejection",
							"longTermRejection",
							"remoteUserBusyAgain",
							"failureToMatch",
							"failedDueToInterworking"},
			new long[] {0, 3, 4, 6, 7, 8, 9, 10, 11, 1000, 1001, 1002, 1003, 1004, 1005,
						1006, 1007, 1008, 1009, 1010, 1011, 1012, 1013, 1014}
	);
	
	/**
	 * <pre>
	 * ERROR
	 * </pre>
	 */
	final static ASN1CustomComponent ErrorValue [] = {
		new ASN1CustomComponent(ASN1_T_ERROR_LOCAL_VALUE, 			"errorLocalValue", 
				INT_TYPE, 		null, 								true,	null,			localErrorValues),
		new ASN1CustomComponent(ASN1_T_ERROR_GLOBAL_VALUE, 			"errorGlobalValue", 
				OBJ_ID_TYPE, 	null, 								true,	null,			null)
	};
	
	/**
	 * <pre>
	 * invokeID   CHOICE {
	 *     InvokeIDType, 
	 *     NULL},
	 * </pre>
	 */
	final static ASN1CustomComponent InvokeIDChoice [] = {
		new ASN1CustomComponent(ASN1_T_INVOKE_ID, 					"invokeID", 
				INT_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NULL, 						"null", 
				NULL_TYPE, 		null, 								true,	null,			null)
	};

	/**
	 * <pre>
	 * GeneralProblem ::= INTEGER {
	 *     unrecognisedAPDU       ( 0 ), 
	 *     mistypedAPDU           ( 1 ), 
	 *     badlyStructuredAPDU    ( 2 )} ( 0 .. 255 )
	 * </pre>
	 */
	final static  ASN1IntegerDecoder generalProblemValues = new ASN1IntegerDecoder(
			new String[] {"unrecognisedAPDU", "mistypedAPDU", "badlyStructuredAPDU"},
			new long[] {0, 1, 2}
	);
	
	/**
	 * <pre>
	 * InvokeProblem ::= INTEGER {
	 *     duplicateInvocation             ( 0 ), 
	 *     unrecognisedOperation           ( 1 ), 
	 *     mistypedArgument                ( 2 ), 
	 *     resourceLimitation              ( 3 ), 
	 *     initiatorReleasing              ( 4 ), 
	 *     unrecognisedLinkedIdentifier    ( 5 ), 
	 *     linkedResponseUnexpected        ( 6 ), 
	 *     unexpectedChildOperation        ( 7 )} ( 0 .. 255 )
	 * </pre>
	 */
	final static  ASN1IntegerDecoder invokeProblemValues = new ASN1IntegerDecoder(
			new String[] {"duplicateInvocation", "unrecognisedOperation", "mistypedArgument",
					"resourceLimitation", "initiatorReleasing", "unrecognisedLinkedIdentifier",
					"linkedResponseUnexpected", "unexpectedChildOperation"},
			new long[] {0, 1, 2, 3, 4, 5, 6, 7}
	);
	
	/**
	 * <pre>
	 * ReturnResultProblem ::= INTEGER {
	 *     unrecognisedInvocation      ( 0 ), 
	 *     resultResponseUnexpected    ( 1 ), 
	 *     mistypedResult              ( 2 )} ( 0 .. 255 )
	 * </pre>
	 */
	final static  ASN1IntegerDecoder returnResultProblemValues = new ASN1IntegerDecoder(
			new String[] {"unrecognisedInvocation", "resultResponseUnexpected", "mistypedResult"},
			new long[] {0, 1, 2}
	);
	
	/**
	 * <pre>
	 * ReturnErrorProblem ::= INTEGER {
	 *     unrecognisedInvocation     ( 0 ), 
	 *     errorResponseUnexpected    ( 1 ), 
	 *     unrecognisedError          ( 2 ), 
	 *     unexpectedError            ( 3 ), 
	 *     mistypedParameter          ( 4 )} ( 0 .. 255 )
	 * </pre>
	 */
	final static  ASN1IntegerDecoder returnErrorProblemValues = new ASN1IntegerDecoder(
			new String[] {"unrecognisedInvocation", "errorResponseUnexpected", "unrecognisedError",
					"unexpectedError", "mistypedParameter"},
			new long[] {0, 1, 2, 3, 4}
	);
	
	/**
	 * <pre>
	 * problem CHOICE {
	 *     [0] IMPLICIT GeneralProblem, 
	 *     [1] IMPLICIT InvokeProblem, 
	 *     [2] IMPLICIT ReturnResultProblem, 
	 *     [3] IMPLICIT ReturnErrorProblem}
	 * </pre>
	 */
	final static ASN1CustomComponent ProblemChoice [] = {
		new ASN1CustomComponent(ASN1_T_REJ_GENERAL, 				"generalProblem", 
				INT_TYPE, 		null, 								true,	null,			generalProblemValues),
		new ASN1CustomComponent(ASN1_T_REJ_INVOKE, 					"invokeProblem", 
				INT_TYPE, 		null, 								true,	null,			invokeProblemValues),
		new ASN1CustomComponent(ASN1_T_REJ_RETURN_RESULT, 			"returnResultProblem", 
				INT_TYPE, 		null, 								true,	null,			returnResultProblemValues),
		new ASN1CustomComponent(ASN1_T_REJ_RETURN_ERROR, 			"returnErrorProblem", 
				INT_TYPE, 		null, 								true,	null,			returnErrorProblemValues)
	};
	
	/**
	 * <pre>
	 * InvokePDU ::= SEQUENCE {
	 *     invokeID         InvokeIDType, 
	 *     linkedID         [0] IMPLICIT InvokeIDType OPTIONAL, 
	 *     operationValue   OPERATION, 
	 *     argument         ANY DEFINED BY operationValue OPTIONAL}
	 * </pre>
	 */
	final static ASN1CustomComponent InvokePDUSequence [] = {
		new ASN1CustomComponent(ASN1_T_INVOKE_ID, 					"invokeID", 
				INT_TYPE, 		null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_LINKED_ID, 					"linkedID", 
				INT_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"operationValue", 
				CHOICE_TYPE, 	OperationValue, 					false,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"argument", 
				ANY_BY_TYPE, 	null, 								true,	null,			null)
	};
	
	/**
	 * <pre>
	 * ReturnResultPDU ::= SEQUENCE {
	 *     invokeID   InvokeIDType, 
	 *     SEQUENCE {
	 *         operationValue   OPERATION, 
	 *         result           ANY DEFINED BY operationValue } OPTIONAL}
	 * </pre>
	 */
	final static ASN1CustomComponent RetResultPDUSequence [] = {
		new ASN1CustomComponent(ASN1_T_INVOKE_ID, 					"invokeID", 
				INT_TYPE, 		null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_RETURN_RESULT_OPERATION, 	"retResultOperation", 
				SEQ_TYPE, 		RetResultOperationSequence, 		true,	null,			null)
	};
	
	/**
	 * <pre>
	 * ReturnErrorPDU ::= SEQUENCE {
	 *     invokeID     InvokeIDType, 
	 *     errorValue   ERROR, 
	 *     parameter    ANY DEFINED BY errorValue  OPTIONAL}
	 * </pre>
	 */
	final static ASN1CustomComponent RetErrorPDUSequence [] = {
		new ASN1CustomComponent(ASN1_T_INVOKE_ID, 					"invokeID", 
				INT_TYPE, 		null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"errorValue", 
				CHOICE_TYPE, 	ErrorValue, 						false,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"parameter", 
				ANY_BY_TYPE,	null, 								true,	null,			null)
	};
	
	/**
	 * <pre>
	 * RejectPDU ::= SEQUENCE {
	 *     invokeID   CHOICE {
	 *         InvokeIDType, 
	 *         NULL}, 
	 *     problem    CHOICE {
	 *         [0] IMPLICIT GeneralProblem, 
	 *         [1] IMPLICIT InvokeProblem, 
	 *         [2] IMPLICIT ReturnResultProblem, 
	 *         [3] IMPLICIT ReturnErrorProblem}}
	 * </pre>
	 */
	final static ASN1CustomComponent RejectPDUSequence [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"invokeIDChoice", 
				CHOICE_TYPE, 	InvokeIDChoice, 					false,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"problemChoice", 
				CHOICE_TYPE, 	ProblemChoice, 						false,	null,			null)
	};
	
	
	/**
	 * <pre>
	 * RoseAPDU ::= CHOICE {
	 *     invoke        [1] IMPLICIT InvokePDU, 
	 *     retResult     [2] IMPLICIT ReturnResultPDU, 
	 *     retError      [3] IMPLICIT ReturnErrorPDU, 
	 *     reject        [4] IMPLICIT RejectPDU}
	 * </pre>
	 */
	final static ASN1CustomComponent RoseAPDUChoice [] = {
		new ASN1CustomComponent(ASN1_T_INVOKE_PDU, 					"invoke", 
				SEQ_TYPE, 		InvokePDUSequence, 					true,	null,			null),
		new ASN1CustomComponent(ASN1_T_RETURN_RESULT_PDU, 			"retResult", 
				SEQ_TYPE, 		RetResultPDUSequence, 				true,	null,			null),
		new ASN1CustomComponent(ASN1_T_RETURN_ERROR_PDU, 			"retError", 
				SEQ_TYPE, 		RetErrorPDUSequence, 				true,	null,			null),
		new ASN1CustomComponent(ASN1_T_REJECT_PDU, 					"reject", 
				SEQ_TYPE, 		RejectPDUSequence, 					true,	null,			null)
	};
	
	/**
	 * <pre>
	 * RoseAPDUChoice [tagged] RoseAPDU
	 * </pre>
	 */
	final static ASN1CustomComponent RoseAPDU [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"RoseAPDU", 
				CHOICE_TYPE, 	RoseAPDUChoice, 					false,	null,			null)
	};
	
	/**
	 * <pre>
	 * InterpretationApdu ::= [11] IMPLICIT ENUMERATED {
	 *     discardAnyUnrecognisedInvokePdu         ( 0 ), 
	 *     clearCallIfAnyInvokePduNotRecognised    ( 1 ), 
	 *     rejectAnyUnrecognizedInvokePdu          ( 2 ) }
	 * </pre>
	 */
	final static  ASN1EnumeratedDecoder interprAPDU = new ASN1EnumeratedDecoder(
			new String[] {"discardAnyUnrecognizedInvokePDU", "clearCallIfAnyUnrecognizedInvokePDU",
					"rejectAnyUnrecognizedInvokePDU", "ommited"},
			new long[] {0, 1, 2, 255}
	);
	
	/**
	 * <pre>
	 * InterpretationApdu ::= [11] IMPLICIT ENUMERATED {
	 *     discardAnyUnrecognisedInvokePdu         ( 0 ), 
	 *     clearCallIfAnyInvokePduNotRecognised    ( 1 ), 
	 *     rejectAnyUnrecognizedInvokePdu          ( 2 ) }
	 * </pre>
	 */
	final static ASN1CustomComponent InterpretationAPDU [] = {
		new ASN1CustomComponent(ASN1_T_INTERPRETATION_APDU, 		"InterpretationAPDU", 
				ENUM_TYPE, 		null, 								true,	null,			interprAPDU)
	};
	
	/**
	 * <pre>
	 * NFEPartyNumber [tagged] PartyNumber
	 * </pre>
	 */
	final static ASN1CustomComponent NFEPartyNumber [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE, 						"partyNumber", 
				CHOICE_TYPE, 	PartyNumber, 						false,	null,			null)
	};
	
	/**
	 * <pre>
	 * EntityType ::= ENUMERATED {
	 *     endPINX          ( 0 ), 
	 *     anyTypeOfPINX    ( 1 ) }
	 * </pre>
	 */
	final static  ASN1EnumeratedDecoder entitys = new ASN1EnumeratedDecoder(
			new String[] {"endPTNX", "anyTypeOfPTNX"},
			new long[] {0, 1}
	);
	
	/**
	 * <pre>
	 * NetworkFacilityExtension ::= [10] IMPLICIT SEQUENCE {
	 *     sourceEntity               [0] IMPLICIT EntityType, 
	 *     sourceEntityAddress        [1] AddressInformation OPTIONAL, 
	 *     destinationEntity          [2] IMPLICIT EntityType, 
	 *     destinationEntityAddress   [3] AddressInformation OPTIONAL}
	 * </pre>
	 */
	final static ASN1CustomComponent NFEEntities [] = {
		new ASN1CustomComponent(ASN1_T_NFE_SOURCE_ENTITY, 			"sourceEntity", 
				ENUM_TYPE, 		null, 								false,	null,			entitys),
		new ASN1CustomComponent(ASN1_T_NFE_SOURCE_ENTITY_ADDRESS, 	"sourceEntityAddress", 
				SEQ_TYPE, 		NFEPartyNumber, 					true,	null,			null),
		new ASN1CustomComponent(ASN1_T_NFE_DEST_ENTITY, 			"destinationEntity", 
				ENUM_TYPE, 		null, 								false,	null,			entitys),
		new ASN1CustomComponent(ASN1_T_NFE_DEST_ENTITY_ADDRESS, 	"destinationEntityAddress", 
				SEQ_TYPE, 		NFEPartyNumber, 					true,	null,			null)
	};
	
	/**
	 * <pre>
	 * NetworkFacilityExtensionsSeq [tagged] NetworkFacilityExtension
	 * </pre>
	 */
	final static ASN1CustomComponent NetworkFacilityExtensions [] = {
		new ASN1CustomComponent(ASN1_T_NFE, 						"NetworkFacilityExtension", 
				SEQ_TYPE, 		NFEEntities, 						true,	null,			null)
	};
}
