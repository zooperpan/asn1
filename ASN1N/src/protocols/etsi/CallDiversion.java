package protocols.etsi;

import asn1.ASN1CustomComponent;
import asn1.component.decoder.ASN1EnumeratedDecoder;
import protocols.common.NameOperations;

public interface CallDiversion extends NameOperations {

	final static ASN1CustomComponent CDServedUserNumber [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE,						"individualNumber", 
				CHOICE_TYPE, 	PartyNumber, 						true,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NULL,						"allNumbers", 
				NULL_TYPE, 		null, 								true,	null,			null)
	};
	
	final static  ASN1EnumeratedDecoder CDProcedures = new ASN1EnumeratedDecoder(
			new String[] {"cfu", "cfb", "cfnr"},
			new long[] {0, 1, 2}
	);
	
	final static  ASN1EnumeratedDecoder CDBasicService = new ASN1EnumeratedDecoder(
			new String[] {"allServices", "speech", "unrestricedDigitalInformation", "audio3100Hz",
					"telephony", "teletex", "telefaxGroup4Class1", "videotexSyntaxBased",
					"videotelephony"},
			new long[] {0, 1, 2, 3, 32, 33, 34, 35, 36}
	);
	
	final static ASN1CustomComponent CDActivateDivertionArg [] = {
		new ASN1CustomComponent(ASN1_T_CF_PROCEDURE,				"procedure", 
				ENUM_TYPE,		null, 								false,	null,			CDProcedures),
		new ASN1CustomComponent(ASN1_T_BASIC_SERVICE,				"basicService", 
				ENUM_TYPE, 		null, 								false,	null,			CDBasicService),
		new ASN1CustomComponent(ASN1_T_NUM_ADDRESS,					"forwardedToAddress", 
				SEQ_TYPE, 		PTAddress, 							false,	null,			null),	
		new ASN1CustomComponent(ASN1_TAG_NONE,						"servedUserNumber", 
				CHOICE_TYPE,	CDServedUserNumber, 				false,	null,			null)
	};
	
	final static ASN1CustomComponent CDActivateDivertion [] = {
		new ASN1CustomComponent(ASN1_T_CF_ACT_DIV_ARG,				"activateDivertion", 
				SEQ_TYPE, 		CDActivateDivertionArg, 			false,	null,			null)
	};
	
	final static ASN1CustomComponent CDDeactivateDivertionArg [] = {
		new ASN1CustomComponent(ASN1_T_CF_PROCEDURE,				"procedure", 
				ENUM_TYPE,		null, 								false,	null,			CDProcedures),
		new ASN1CustomComponent(ASN1_T_BASIC_SERVICE,				"basicService", 
				ENUM_TYPE, 		null, 								false,	null,			CDBasicService),
		new ASN1CustomComponent(ASN1_TAG_NONE,						"servedUserNumber", 
				CHOICE_TYPE,	CDServedUserNumber, 				false,	null,			null)
	};
	
	final static ASN1CustomComponent CDDeactivateDivertion [] = {
		new ASN1CustomComponent(ASN1_T_CF_DEACT_DIV_ARG,			"deactivateDivertion", 
				SEQ_TYPE, 		CDDeactivateDivertionArg, 			false,	null,			null)
	};
	
	final static ASN1CustomComponent CDInteroggateDiversionArg [] = {
		new ASN1CustomComponent(ASN1_T_CF_PROCEDURE,				"procedure", 
				ENUM_TYPE,		null, 								false,	null,			CDProcedures),
		new ASN1CustomComponent(ASN1_T_BASIC_SERVICE,				"basicService", 
				ENUM_TYPE, 		null, 								true,	null,			CDBasicService),
		new ASN1CustomComponent(ASN1_TAG_NONE,						"servedUserNumber", 
				CHOICE_TYPE,	CDServedUserNumber, 				false,	null,			null)
	};
	
	final static ASN1CustomComponent CDInterrogateDivertion [] = {
		new ASN1CustomComponent(ASN1_T_CF_INTERROGATION_DIV_ARG,	"InteroggateDiversion", 
				SEQ_TYPE, 		CDInteroggateDiversionArg, 		false,	null,			null)
	};
	
	final static  ASN1EnumeratedDecoder CDDiversionReason = new ASN1EnumeratedDecoder(
			new String[] {"unknown", "cfu", "cfb", "cfnr"},
			new long[] {0, 1, 2, 3}
	);
	
	final static  ASN1EnumeratedDecoder CDSubscriptionOption = new ASN1EnumeratedDecoder(
			new String[] {"noNotification", "notificationWithoutDivertedToNr", "notificationWithDivertedToNr"},
			new long[] {0, 1, 2}
	);
	
	final static ASN1CustomComponent CDDivertingLegInformation1S0Arg [] = {
		new ASN1CustomComponent(ASN1_T_CF_DIVERSION_REASON,			"diversionReason", 
				ENUM_TYPE,		null, 								false,	null,			CDDiversionReason),
		new ASN1CustomComponent(ASN1_T_CF_SUBSCRIPTION_OPTION,		"subscriptionOption", 
				ENUM_TYPE, 		null, 								false,	null,			CDSubscriptionOption),
		new ASN1CustomComponent(ASN1_TAG_NONE,						"divertedToNumber", 
				CHOICE_TYPE, 	PresentedNumberUnscreened, 			true,	null,			null)
	};
	
	final static ASN1CustomComponent CDDivertingLegInformation1S0 [] = {
		new ASN1CustomComponent(ASN1_T_CF_DIV_LEG_INFO1_ARG,		"DivertingLegInformation1-S0", 
				SEQ_TYPE, 		CDDivertingLegInformation1S0Arg, 	false,	null,			null)
	};
	
	final static ASN1CustomComponent CDDivertingLegInformation2S0Arg [] = {
		new ASN1CustomComponent(ASN1_T_CF_DIVERSION_COUNTER,		"diversionCounter", 
				INT_TYPE,		null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CF_DIVERSION_REASON,			"diversionReason", 
				ENUM_TYPE,		null, 								false,	null,			CDDiversionReason),
		new ASN1CustomComponent(ASN1_T_DIV_NR_DIV_LEG_INFO2,		"divertingNumber", 
				SEQ_TYPE, 		PresentedNumberUnscreened, 			true,	null,			null),
		new ASN1CustomComponent(ASN1_T_OR_CALLED_NR_DIV_LEG_INFO2,	"originalCalledNumber", 
				SEQ_TYPE,		PresentedNumberUnscreened, 			true,	null,			null)
	};
	
	final static ASN1CustomComponent CDDivertingLegInformation2S0 [] = {
		new ASN1CustomComponent(ASN1_T_CF_DIV_LEG_INFO2_ARG,		"DivertingLegInformation2-S0", 
				SEQ_TYPE, 		CDDivertingLegInformation2S0Arg, 	false,	null,			null)
	};
	
	final static ASN1CustomComponent CDDivertingLegInformation3S0 [] = {
		new ASN1CustomComponent(ASN1_T_NUM_PRES_ALLOWED_IND,		"presentationAllowedIndicator", 
				BOOLEAN_TYPE, 	null, 								false,	null,			null)
	};
	
	final static ASN1CustomComponent CDLastDivertedReroutingAddress [] = {
		new ASN1CustomComponent(ASN1_TAG_ENUM,						"divertionReason", 
				ENUM_TYPE,		null, 								false,	null,			CDDiversionReason)
	};
	
	final static ASN1CustomComponent CDRerouteingubScriptionOption [] = {
		new ASN1CustomComponent(ASN1_TAG_ENUM,						"subscriptionOption", 
				ENUM_TYPE,		null, 								false,	null,			CDSubscriptionOption)
	};
	
	final static ASN1CustomComponent CDCallRerouteingArg [] = {
		new ASN1CustomComponent(ASN1_TAG_ENUM,						"rerouteingReason", 
				ENUM_TYPE,		null, 								false,	null,			CDDiversionReason),	
		new ASN1CustomComponent(ASN1_T_NUM_ADDRESS,					"calledAddress", 
				SEQ_TYPE, 		PTAddress, 							false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CF_REROUTEING_COUNTER,		"rerouteingnCounter", 
				INT_TYPE,		null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CCBS_Q931_INFO_ELE,			"q931InfoElement", 
				OCTET_STR_TYPE,	null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CF_LAST_REROUTEING_NR,		"lastRerouteingNr", 
				SEQ_TYPE, 		PresentedNumberUnscreened, 			false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CF_REROUTEING_SUBSCR_OPTION,	"subscriptionOption", 
				SEQ_TYPE, 		CDRerouteingubScriptionOption, 		false,	null,			null),
		new ASN1CustomComponent(ASN1_T_CF_REROUTEING_CALLING_SUBADD,"callingPartySubaddress", 
				SEQ_TYPE, 		PartySubaddress, 					true,	null,			null)
	};
	
	final static ASN1CustomComponent CDCallRerouteing [] = {
		new ASN1CustomComponent(ASN1_T_CF_CALL_REROUTEING,			"CallRerouteing", 
				SEQ_TYPE,		CDCallRerouteingArg, 				false,	null,			null)
	};
	
	final static ASN1CustomComponent CDDivertionInformationArg [] = {
		new ASN1CustomComponent(ASN1_TAG_ENUM,						"diversionReason", 
				ENUM_TYPE,		null, 								false,	null,			CDDiversionReason),	
		new ASN1CustomComponent(ASN1_TAG_ENUM,						"basicService", 
				ENUM_TYPE,		null, 								false,	null,			CDBasicService),
		new ASN1CustomComponent(ASN1_TAG_NONE,						"originalDiversionReason", 
				CHOICE_TYPE, 	PartySubaddress, 					true,	null,			null),
		new ASN1CustomComponent(ASN1_T_CF_DIV_INF_CALLED_ADDRESS,	"calledAddress", 
				SEQ_TYPE, 		PresentationAddress, 				true,	null,			null),
		new ASN1CustomComponent(ASN1_T_CF_DIV_INF_OR_CALLED_ADDRESS,"originalCalledAddress", 
				SEQ_TYPE,		PresentedNumberUnscreened, 			true,	null,			null),
		new ASN1CustomComponent(ASN1_T_CF_DIV_INF_LAST_DIV_NUM,		"lastDivertedNumber", 
				SEQ_TYPE,		PresentedNumberUnscreened, 			true,	null,			null),
		new ASN1CustomComponent(ASN1_T_CF_DIV_INF_LAST_DIV_RER_AD,	"lastDivertedReroutingAddress", 
				SEQ_TYPE, 		CDLastDivertedReroutingAddress, 	true,	null,			null),	
		new ASN1CustomComponent(ASN1_T_CF_DIV_INF_USER_INFORMATION,	"userInformation", 
				OCTET_STR_TYPE, null, 								true,	null,			null)
	};
	
	final static ASN1CustomComponent CDDivertionInformation [] = {
		new ASN1CustomComponent(ASN1_T_CF_DIVERTION_INFORMATION,	"DivertionInformation", 
				SEQ_TYPE,		CDDivertionInformationArg, 			false,	null,			null)
	};
}
