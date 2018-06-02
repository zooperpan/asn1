package protocols.etsi;

import asn1.ASN1CustomComponent;
import asn1.component.decoder.ASN1BooleanDecoder;
import asn1.component.decoder.ASN1EnumeratedDecoder;
import protocols.common.NameOperations;

public interface CallCompletion extends NameOperations {

	final static ASN1CustomComponent CCBS_T_RequestArg [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_T_DEST_ADDRESS,			"destinationAddress", 
				SEQ_TYPE, 		PTAddress, 							false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CCBS_Q931_INFO_ELE,			"q931InfoElement", 
				OCTET_STR_TYPE, null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CCBS_T_RETENTION_SUPP_ARG,	"retentionSupported", 
				BOOLEAN_TYPE, 	null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_CCBS_T_PRES_ALLOWED_IND,		"presentationAllowedIndicator", 
				BOOLEAN_TYPE, 	null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_CCBS_T_ORIGINATING_ADDR,		"originatingAddress", 
				SEQ_TYPE, 		PTAddress, 							true,	null,			null)
	};
	
	final static ASN1CustomComponent CCBS_T_Request [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_T_REQUEST_ARG,			"CCBS-T-Request", 
				SEQ_TYPE, 		CCBS_T_RequestArg, 					false,	null,			null)
	};
	
	final static ASN1CustomComponent CCBS_T_RequestRes [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_T_RETENTION_SUPP_RES,	"retentionSupported", 
				BOOLEAN_TYPE, 	null, 								true,	null,			null)
	};
	
	final static ASN1CustomComponent CCBS_S_CallLinkageID [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_CALL_LINKAGE_ID,		"CallLinkageID", 
				INT_TYPE, 		null, 								false,	null,			null)
	};
	
	final static  ASN1EnumeratedDecoder CCBSRecallMode = new ASN1EnumeratedDecoder(
			new String[] {"globalRecall", "specificRecall"},
			new long[] {0, 1}
	);
	
	final static ASN1CustomComponent CCBS_S_RequestResArg [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_RECALL_MODE,			"recallMode", 
				ENUM_TYPE, 		null, 								false,	null,			CCBSRecallMode),
		new ASN1CustomComponent(ASN1_T_CCBS_REFERENCE,				"cCBSReference", 
				INT_TYPE, 		null, 								false,	null,			null),
	};
	
	final static ASN1CustomComponent CCBS_S_RequestRes [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_REQUEST_RESULT,			"CCBSRequestResult", 
				SEQ_TYPE, 		CCBS_S_RequestResArg, 				false,	null,			null)
	};
	
	final static ASN1CustomComponent CCBS_S_InterogateArg [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_REFERENCE,				"cCBSReference", 
				INT_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NONE,						"partyNumberOfA", 
				CHOICE_TYPE, 	PartyNumber, 						true,	null,			null)
	};
	
	final static ASN1CustomComponent CCBS_S_Interogate [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_INTERROGATE_ARG,		"CCBSInterrogate", 
				SEQ_TYPE, 		CCBS_S_InterogateArg, 				false,	null,			null)
	};
	
	final static ASN1CustomComponent CCBS_S_CallInformation [] = {
		new ASN1CustomComponent(ASN1_T_NUM_ADDRESS,					"addressOfB", 
				SEQ_TYPE, 		PTAddress, 							false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CCBS_Q931_INFO_ELE,			"q931InfoElement", 
				OCTET_STR_TYPE, null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CCBS_REFERENCE,				"cCBSReference", 
				INT_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NONE,						"subAddressOfA", 
				CHOICE_TYPE, 	PartySubaddress, 					true,	null,			null)
	};

	final static ASN1CustomComponent CCBS_S_CallDetails [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_CALL_INFORMATION,		"CallInformation", 
				SEQ_TYPE, 		CCBS_S_CallInformation, 			false,	null,			null)
	};

	final static ASN1CustomComponent CCBS_S_InterogateResArg [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_RECALL_MODE,			"recallMode", 
				ENUM_TYPE, 		null, 								false,	null,			CCBSRecallMode),
		new ASN1CustomComponent(ASN1_T_CCBS_CALL_DETAILS,			"callDetails", 
				SEQ_TYPE, 		CCBS_S_CallDetails, 				true,	null,			null)
	};
	
	final static ASN1CustomComponent CCBS_S_InterogateRes [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_INTERROGATE_RES,		"CCBSInterrogateResult", 
				SEQ_TYPE, 		CCBS_S_InterogateResArg, 			false,	null,			null)
	};

	final static ASN1CustomComponent CCBS_S_Reference [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_REFERENCE,				"cCBSReference", 
				INT_TYPE, 		null, 								false,	null,			null)
	};
	
	final static  ASN1EnumeratedDecoder CCBSEraseReason = new ASN1EnumeratedDecoder(
			new String[] {"normal-unspecified", "t-CCBS2-timeout", "t-CCBS3-timeout", "basic-call-failed"},
			new long[] {0, 1, 2, 3}
	);
	
	final static ASN1CustomComponent CCBS_S_EraseArg [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_RECALL_MODE,			"recallMode", 
				ENUM_TYPE, 		null, 								false,	null,			CCBSRecallMode),
		new ASN1CustomComponent(ASN1_T_CCBS_REFERENCE,				"cCBSReference", 
				INT_TYPE, 		null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_ADDRESS,					"addressOfB", 
				SEQ_TYPE, 		PTAddress, 							false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CCBS_Q931_INFO_ELE,			"q931InfoElement", 
				OCTET_STR_TYPE, null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CCBS_ERASE_REASON,			"eraseReason", 
				ENUM_TYPE, 		null, 								false,	null,			CCBSEraseReason)
	};

	final static ASN1CustomComponent CCBS_S_Erase [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_ERASE_ARG,				"CCBSErase", 
				SEQ_TYPE, 		CCBS_S_EraseArg, 					false,	null,			null)
	};
	
	final static ASN1CustomComponent CCBS_S_RemoteUserFreeArg [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_RECALL_MODE,			"recallMode", 
				ENUM_TYPE, 		null, 								false,	null,			CCBSRecallMode),
		new ASN1CustomComponent(ASN1_T_CCBS_REFERENCE,				"cCBSReference", 
				INT_TYPE, 		null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_ADDRESS,					"addressOfB", 
				SEQ_TYPE, 		PTAddress, 							false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CCBS_Q931_INFO_ELE,			"q931InfoElement", 
				OCTET_STR_TYPE, null, 								false,	null,			null)
	};
	
	final static ASN1CustomComponent CCBS_S_RemoteUserFree [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_REMOTE_USER_FREE_ARG,	"CCBSRemoteUserFree", 
				SEQ_TYPE, 		CCBS_S_RemoteUserFreeArg, 			false,	null,			null)
	};
	
	final static ASN1CustomComponent CCBS_S_BFreeArg [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_RECALL_MODE,			"recallMode", 
				ENUM_TYPE, 		null, 								false,	null,			CCBSRecallMode),
		new ASN1CustomComponent(ASN1_T_CCBS_REFERENCE,				"cCBSReference", 
				INT_TYPE, 		null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_NUM_ADDRESS,					"addressOfB", 
				SEQ_TYPE, 		PTAddress, 							false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CCBS_Q931_INFO_ELE,			"q931InfoElement", 
				OCTET_STR_TYPE, null, 								false,	null,			null)
	};
	
	final static ASN1CustomComponent CCBS_S_BFree [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_B_FREE_ARG,				"CCBSBFree", 
				SEQ_TYPE, 		CCBS_S_BFreeArg, 					false,	null,			null)
	};
	
	final static ASN1CustomComponent CCBSStatusRequestArg [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_RECALL_MODE,			"recallMode", 
				ENUM_TYPE, 		null, 								false,	null,			CCBSRecallMode),
		new ASN1CustomComponent(ASN1_T_CCBS_REFERENCE,				"cCBSReference", 
				INT_TYPE, 		null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CCBS_Q931_INFO_ELE,			"q931InfoElement", 
				OCTET_STR_TYPE, null, 								false,	null,			null)
	};
	
	final static ASN1CustomComponent CCBS_S_StatusRequest [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_STATUS_REQUEST_ARG,		"CCBSStatusRequest", 
				SEQ_TYPE, 		CCBSStatusRequestArg, 				false,	null,			null)
	};

	final static  ASN1BooleanDecoder CCBSStatusResult = new ASN1BooleanDecoder(
			new String[] {"busy", "free"}
	);
	
	final static ASN1CustomComponent CCBS_S_StatusRequestRes [] = {
		new ASN1CustomComponent(ASN1_T_CCBS_STATUS_REQUEST_RES,		"CCBSStatusRequestResult", 
				BOOLEAN_TYPE, 	null, 								false,	null,			CCBSStatusResult)
	};
	
	final static ASN1CustomComponent CCStatusRequestArg [] = {
		new ASN1CustomComponent(ASN1_T_STAT_REQ_COMP_MODE,			"completeMode", 
				ENUM_TYPE, 		null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CCBS_Q931_INFO_ELE,			"q931InfoElement", 
				OCTET_STR_TYPE, null, 								false,	null,			null)
	};
	
	final static ASN1CustomComponent CCStatusRequest [] = {
		new ASN1CustomComponent(ASN1_T_STATUS_REQUEST_ARG,			"ccStatusRequest", 
				SEQ_TYPE, 		CCStatusRequestArg, 				false,	null,			null)
	};
	
	final static  ASN1EnumeratedDecoder CCStatusRequestResult = new ASN1EnumeratedDecoder(
			new String[] {"compatibleAndFree", "compatibleAndBusy", "incompatible"},
			new long[] {0, 1, 2}
	);
	
	final static ASN1CustomComponent CCStatusRequestRes [] = {
		new ASN1CustomComponent(ASN1_T_STATUS_RESULT,				"ccStatusRequestResult", 
				ENUM_TYPE, 		null, 								false,	null,			CCStatusRequestResult)
	};
}
