package protocols.common;

import asn1.ASN1CustomComponent;
import asn1.component.decoder.ASN1EnumeratedDecoder;


public interface AdviceOfCharge extends PartyNumber {

	final static ASN1CustomComponent AOCChargingAssocNumber [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE,						"chargeNumber", 
				CHOICE_TYPE, 	PartyNumber, 						false,	null,			null)
	};
	
	final static ASN1CustomComponent AOCChargingAssociation [] = {
		new ASN1CustomComponent(ASN1_T_AOC_CHAR_ASS_NUMBER,			"chargeNumber", 
				SEQ_TYPE, 		AOCChargingAssocNumber, 			true,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOC_CHAR_ASS_ID,				"chargeIdentifier", 
				INT_TYPE, 		null, 								true,	null,			null)
	};
	
	final static  ASN1EnumeratedDecoder AOCMultiplier = new ASN1EnumeratedDecoder(
			new String[] {"oneThousandth", "oneHundredth", "oneTenth", "one", "ten", "hundred", "thousand"},
			new long[] {0, 1, 2, 3, 4, 5, 6}
	);
	
	final static ASN1CustomComponent AOCAmount [] = {
		new ASN1CustomComponent(ASN1_T_AOC_AMOUNT_CURR_AMOUNT,		"currencyAmount", 
				INT_TYPE, 		null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOC_AMOUNT_MULTIPLIER,		"multiplier", 
				ENUM_TYPE, 		null,			 					false,	null,			AOCMultiplier)
	};
	
	final static ASN1CustomComponent AOCRecordedUnitsChoice [] = {
		new ASN1CustomComponent(ASN1_T_AOC_NUMBER_OF_UNITS,			"recordedNumberOfUnits", 
				INT_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOC_NOT_AVAILABLE,			"notAvailable", 
				NULL_TYPE, 		null, 								true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCRecordedUnits [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE,						"RecordedUnitsChoice", 
				CHOICE_TYPE, 	AOCRecordedUnitsChoice, 			false,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOC_TYPE_OF_UNIT,			"recordedTypeOfUnitsTypeOfUnit", 
				INT_TYPE, 		null, 								true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCRecordedUnitsList [] = {
		new ASN1CustomComponent(ASN1_T_AOC_REC_UNITS_LIST,			"RecordedUnits", 
				SEQ_TYPE, 		AOCRecordedUnits, 					false,	null,			null)
	};
	
	final static ASN1CustomComponent AOCRecordedCurrency [] = {
		new ASN1CustomComponent(ASN1_T_AOC_REC_CURR_RCURR,			"rCurrency", 
				IA5_STR_TYPE, 	null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOC_REC_CURR_RAMOUNT,		"rAmount", 
				SEQ_TYPE, 		AOCAmount,			 				false,	null,			null)
	};
	
	final static  ASN1EnumeratedDecoder AOCScale = new ASN1EnumeratedDecoder(
			new String[] {"oneHundredthSecond", "oneTenthSecond", "oneSecond", "tenSeconds", 
					"oneMinute", "oneHour", "twentyFourHours"},
			new long[] {0, 1, 2, 3, 4, 5, 6}
	);
	
	final static ASN1CustomComponent AOCTime [] = {
		new ASN1CustomComponent(ASN1_T_AOC_LENGTH_OF_TIME_UNIT,		"lengthOfTimeUnit", 
				INT_TYPE, 		null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOC_SCALE,					"scale", 
				ENUM_TYPE, 		null,			 					false,	null,			AOCScale)
	};
	
	final static  ASN1EnumeratedDecoder AOCVolumeUnit = new ASN1EnumeratedDecoder(
			new String[] {"octet", "segment", "message"},
			new long[] {0, 1, 2}
	);
	
	final static ASN1CustomComponent AOCSVolumeRateCurrency [] = {
		new ASN1CustomComponent(ASN1_T_AOCS_VR_CURR,				"vRCurrency", 
				IA5_STR_TYPE, 	null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCS_VR_AMOUNT,				"vRAmount", 
				SEQ_TYPE, 		AOCAmount,			 				false,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCS_VR_VOLUME_UNIT,			"vRVolumeUnit", 
				ENUM_TYPE, 		null,			 					false,	null,			AOCVolumeUnit)
	};
	
	final static ASN1CustomComponent AOCSFlatRateCurrency [] = {
		new ASN1CustomComponent(ASN1_T_AOCS_FR_CURR,				"fRCurrency", 
				IA5_STR_TYPE, 	null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCS_FR_AMOUNT,				"fRAmount", 
				SEQ_TYPE, 		AOCAmount,			 				false,	null,			null)
	};

	final static  ASN1EnumeratedDecoder AOCChargingType = new ASN1EnumeratedDecoder(
			new String[] {"continuousCharging", "stepFunction"},
			new long[] {0, 1}
	);
	
	final static ASN1CustomComponent AOCSDurationCurrency [] = {
		new ASN1CustomComponent(ASN1_T_AOCS_D_CURR,					"dCurrency", 
				IA5_STR_TYPE, 	null, 								false,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCS_D_AMOUNT,				"dAmount", 
				SEQ_TYPE, 		AOCAmount,			 				false,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCS_D_CHARGING_TYPE,		"dChargingType", 
				ENUM_TYPE, 		null,			 					false,	null,			AOCChargingType),
		new ASN1CustomComponent(ASN1_T_AOCS_D_TIME,					"dTime", 
				SEQ_TYPE, 		AOCTime,			 				false,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCS_D_GRANULARITY,			"dGranularity", 
				SEQ_TYPE, 		AOCTime,			 				true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCSSpecificCurrency [] = {
		new ASN1CustomComponent(ASN1_T_AOCS_DURATION_CURR,			"durationCurrency", 
				SEQ_TYPE, 		AOCSDurationCurrency, 				true,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCS_FLAT_RATE_CURR,			"flatRateCurrency", 
				SEQ_TYPE, 		AOCSFlatRateCurrency,			 	true,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCS_VOLUME_RATE_CURR,		"volumeRateCurrency", 
				SEQ_TYPE, 		AOCSVolumeRateCurrency,			 	true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCSCurrencyInfoChoice [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE,						"specificCurrency", 
				CHOICE_TYPE, 	AOCSSpecificCurrency, 				true,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_INT,						"specialChargingCode", 
				INT_TYPE, 		null,			 					true,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCS_FREE_OF_CHARGE,			"freeOfCharge", 
				NULL_TYPE, 		null,			 					true,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCS_CURR_INFO_NOT_AVAIL,	"currencyInfoNotAvailable", 
				NULL_TYPE, 		null,			 					true,	null,			null)
	};
	
	final static  ASN1EnumeratedDecoder AOCChargedItem = new ASN1EnumeratedDecoder(
			new String[] {"basicCommunication", "callAttempt", "callSetup", "userToUserInfo", "operationOfSupplementaryServ"},
			new long[] {0, 1, 2, 3, 4}
	);
	
	final static ASN1CustomComponent AOCSCurrencyInfo [] = {
		new ASN1CustomComponent(ASN1_TAG_ENUM,						"chargedItem", 
				ENUM_TYPE, 		null, 								false,	null,			AOCChargedItem),
		new ASN1CustomComponent(ASN1_TAG_NONE,						"AOCSCurrencyInfoChoice", 
				CHOICE_TYPE, 	AOCSCurrencyInfoChoice,			 	false,	null,			null)
	};
	
	final static ASN1CustomComponent AOCSCurrencyInfoList [] = {
		new ASN1CustomComponent(ASN1_T_AOCS_CURR_INFO,				"AOCSCurrencyInfo", 
				SEQ_TYPE, 		AOCSCurrencyInfo, 					false,	null,			null)
	};
	
	final static ASN1CustomComponent AOCSSpecialArrArg [] = {
		new ASN1CustomComponent(ASN1_TAG_NULL,						"chargeNotAvailable", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_INT,						"AOCSSpecialArrInfo", 
				INT_TYPE, 		null,			 					true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCSSpecialArr [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE,						"AOCSSpecialArr", 
				CHOICE_TYPE, 	AOCSSpecialArrArg, 					false,	null,			null)
	};
	
	final static ASN1CustomComponent AOCSCurrencyArg [] = {
		new ASN1CustomComponent(ASN1_TAG_NULL,						"chargeNotAvailable", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCS_CURR_INFO_LIST,			"AOCSCurrencyInfoList", 
				SEQ_OF_TYPE, 	AOCSCurrencyInfoList,			 	true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCSCurrency [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE,						"AOCSCurrency", 
				CHOICE_TYPE, 	AOCSCurrencyArg, 					false,	null,			null)
	};
	
	final static ASN1CustomComponent AOCChargingRequestResultChoice [] = {
		new ASN1CustomComponent(ASN1_T_AOCS_CURR_INFO_LIST,			"AOCSCurrencyInfoList", 
				SEQ_TYPE, 		AOCSCurrencyInfoList, 				true,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_INT,						"AOCSSpecialArrInfo", 
				INT_TYPE, 		null,			 					true,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NULL,						"chargingInfoFollows", 
				NULL_TYPE, 		null,			 					true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCChargingRequestResult [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE,						"ChargingRequestResult", 
				CHOICE_TYPE, 	AOCChargingRequestResultChoice, 	false,	null,			null)
	};
	
	final static  ASN1EnumeratedDecoder AOCDChargingCase = new ASN1EnumeratedDecoder(
			new String[] {"chargingInformationAtCallSetup", "chargingDuringACall", "chargingAtTheEndOfACall"},
			new long[] {0, 1, 2}
	);
	
	final static ASN1CustomComponent AOCChargingRequest [] = {
		new ASN1CustomComponent(ASN1_TAG_ENUM,						"ChargingRequest", 
				ENUM_TYPE, 		null, 								false,	null,			AOCDChargingCase)
	};
	
	final static  ASN1EnumeratedDecoder AOCTypeOfChargingInfo = new ASN1EnumeratedDecoder(
			new String[] {"subTotal", "total"},
			new long[] {0, 1}
	);
	
	final static  ASN1EnumeratedDecoder AOCDBillingId = new ASN1EnumeratedDecoder(
			new String[] {"normalCharging", "reverseCharging", "creditCardCharging"},
			new long[] {0, 1, 2}
	);
	
	final static ASN1CustomComponent AOCDSpecificCurrency [] = {
		new ASN1CustomComponent(ASN1_T_AOCD_SPEC_CURR_REC_CURR,		"recordedCurrency", 
				SEQ_TYPE, 		AOCRecordedCurrency, 				false,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCD_SPEC_CURR_TYPE,			"typeOfChargingInfo", 
				ENUM_TYPE, 		null,			 					false,	null,			AOCTypeOfChargingInfo),
		new ASN1CustomComponent(ASN1_T_AOCD_SPEC_CURR_BILL_ID,		"aOCDBillingId", 
				ENUM_TYPE, 		null,			 					true,	null,			AOCDBillingId)
	};
	
	final static ASN1CustomComponent AOCDCurrencyInfo [] = {
		new ASN1CustomComponent(ASN1_T_AOCD_CURR_INFO_SPEC_CURR,	"specificCurrency", 
				SEQ_TYPE, 		AOCDSpecificCurrency, 				true,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCD_CURR_INFO_FREE,			"freeOfCharge", 
				NULL_TYPE, 		null,			 					true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCDCurrencyArg [] = {
		new ASN1CustomComponent(ASN1_T_AOC_NOT_AVAILABLE,			"chargeNotAvailable", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NONE,						"AOCDCurrencyInfo", 
				CHOICE_TYPE, 	AOCDCurrencyInfo,			 		true,	null,			null)
	};

	final static ASN1CustomComponent AOCDCurrency [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE,						"AOCDCurrency", 
				CHOICE_TYPE, 	AOCDCurrencyArg, 					false,	null,			null)
	};
	
	final static ASN1CustomComponent AOCDChargingUnitInfoSeq [] = {
		new ASN1CustomComponent(ASN1_T_AOCD_C_U_I_REC_UNIT_LIST,	"recordedUnitsList", 
				SEQ_TYPE, 		AOCRecordedUnitsList, 				false,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCD_C_U_I_TYPE_OF_CHAR,		"typeOfChargingInfo", 
				ENUM_TYPE, 		null,			 					false,	null,			AOCTypeOfChargingInfo),
		new ASN1CustomComponent(ASN1_T_AOCD_C_U_I_BILLING_ID,		"aOCDBillingId", 
				ENUM_TYPE, 		null,			 					true,	null,			AOCDBillingId)
	};
	
	final static ASN1CustomComponent AOCDChargingUnitInfo [] = {
		new ASN1CustomComponent(ASN1_T_AOCD_CHAR_UNIT_INFO_SPEC,	"specificChargingUnits", 
				SEQ_TYPE, 		AOCDChargingUnitInfoSeq, 			true,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCD_CHAR_UNIT_INFO_FREE,	"AOCDChargingUnitInfo", 
				NULL_TYPE, 		null,			 					true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCDChargingUnitArg [] = {
		new ASN1CustomComponent(ASN1_T_AOC_NOT_AVAILABLE,			"chargeNotAvailable", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NONE,						"AOCDChargingUnitInfo", 
				CHOICE_TYPE, 	AOCDChargingUnitInfo,			 	true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCDChargingUnit [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE,						"AOCDChargingUnit", 
				CHOICE_TYPE, 	AOCDChargingUnitArg, 				false,	null,			null)
	};
	
	final static  ASN1EnumeratedDecoder AOCEBillingId = new ASN1EnumeratedDecoder(
			new String[] {"normalCharging", "reverseCharging", "creditCardCharging", "callForwardingUnconditional", 
					"callForwardingBusy", "callForwardingNoReply", "callDeflection", "callTransfer"},
			new long[] {0, 1, 2, 3, 4, 5, 6, 7}
	);
	
	final static ASN1CustomComponent AOCESpecificCurrency [] = {
		new ASN1CustomComponent(ASN1_T_AOCE_C_I_REC_CURR,			"recordedCurrency", 
				SEQ_TYPE, 		AOCRecordedCurrency, 				false,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCD_C_I_BILLING_ID,			"aOCEBillingId", 
				ENUM_TYPE, 		null,			 					true,	null,			AOCEBillingId)
	};
	
	final static ASN1CustomComponent AOCECurrencyInfoChoice [] = {
		new ASN1CustomComponent(ASN1_T_AOCE_C_I_SPEC_CURR,			"specificCurrency", 
				SEQ_TYPE, 		AOCESpecificCurrency, 				true,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCE_C_I_FREE,				"freeOfCharge", 
				NULL_TYPE, 		null,			 					true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCECurrencyInfo [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE,						"currencyInfoChoice", 
				CHOICE_TYPE, 	AOCECurrencyInfoChoice, 			false,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NONE,						"chargingAssociation", 
				CHOICE_TYPE, 	AOCChargingAssociation,			 	true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCECurrencyArg [] = {
		new ASN1CustomComponent(ASN1_T_AOC_NOT_AVAILABLE,			"chargeNotAvailable", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCE_CURR_INFO,				"AOCECurrencyInfo", 
				SEQ_TYPE, 		AOCECurrencyInfo, 					true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCECurrency [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE,						"AOCECurrency", 
				CHOICE_TYPE, 	AOCECurrencyArg, 					false,	null,			null)
	};
	
	final static ASN1CustomComponent AOCSpecificChargingUnits [] = {
		new ASN1CustomComponent(ASN1_T_AOCE_C_U_I_REC_UNIT_LIST,	"recordedUnitsList", 
				SEQ_TYPE, 		AOCRecordedUnitsList, 				false,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCE_C_U_I_BILLING_ID,		"aOCEBillingId", 
				ENUM_TYPE, 		null, 								true,	null,			AOCEBillingId)
	};
	
	final static ASN1CustomComponent AOCEChargingUnitInfoChoice [] = {
		new ASN1CustomComponent(ASN1_T_AOCE_C_U_I_SPEC_CURR,		"specificChargingUnits", 
				SEQ_TYPE, 		AOCSpecificChargingUnits, 			true,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCE_C_U_I_FREE,				"freeOfCharge", 
				NULL_TYPE, 		null, 								true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCEChargingUnitInfo [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE,						"chargingUnitInfoChoice", 
				CHOICE_TYPE, 	AOCEChargingUnitInfoChoice, 		false,	null,			null),
		new ASN1CustomComponent(ASN1_TAG_NONE,						"chargingAssociation", 
				CHOICE_TYPE, 	AOCChargingAssociation, 			true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCEChargeUnitArg [] = {
		new ASN1CustomComponent(ASN1_T_AOC_NOT_AVAILABLE,			"chargeNotAvailable", 
				NULL_TYPE, 		null, 								true,	null,			null),
		new ASN1CustomComponent(ASN1_T_AOCE_CHARGING_UNIT,			"AOCEChargingUnitInfo", 
				SEQ_TYPE, 		AOCEChargingUnitInfo, 				true,	null,			null)
	};
	
	final static ASN1CustomComponent AOCEChargingUnit [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE,						"AOCEChargingUnit", 
				CHOICE_TYPE, 	AOCEChargeUnitArg, 					false,	null,			null)
	};
	
	final static ASN1CustomComponent AOCIdentOfCharge [] = {
		new ASN1CustomComponent(ASN1_TAG_NONE,						"IdentityOfCharge", 
				CHOICE_TYPE, 	AOCChargingAssociation, 			false,	null,			null)
	};
}
