package protocols.etsi;

import protocols.common.AdviceOfCharge;
import protocols.common.NameOperations;
import protocols.common.OperationContainer;
import asn1.ASN1Component;
import asn1.ASN1CustomComponent;
import asn1.component.ASN1Integer;
import asn1.component.ASN1ObjectID;

public class OperationDecoder implements Operations, NameOperations, AdviceOfCharge, CallCompletion,
CallDiversion {

	private static OperationContainer invokeLocalOperations [] = {
		new OperationContainer(CallingName, 					ASN1_O_NO_CALLING_NAME),
		new OperationContainer(CDActivateDivertion, 			ASN1_O_CF_ACT_DIV),
		new OperationContainer(CDDeactivateDivertion, 			ASN1_O_CF_DEACT_DIV),
		new OperationContainer(CDInterrogateDivertion, 			ASN1_O_CF_INTERROGATION_DIV),
		new OperationContainer(CDDivertionInformation, 			ASN1_O_CF_DIVERSION_INFORMATION),
		new OperationContainer(CDCallRerouteing, 				ASN1_O_CF_CALL_REROUTING),
		new OperationContainer(CDDivertingLegInformation2S0, 	ASN1_O_CF_DIVERTING_LEG_INFO_2),
		new OperationContainer(CDDivertingLegInformation1S0, 	ASN1_O_CF_DIVERTING_LEG_INFO_1),
		new OperationContainer(CDDivertingLegInformation3S0, 	ASN1_O_CF_DIVERTING_LEG_INFO_3),
		new OperationContainer(AOCChargingRequest, 				ASN1_O_CHARGING_REQUEST),
		new OperationContainer(AOCSCurrency, 					ASN1_O_AOCS_CURRENCY),
		new OperationContainer(AOCSSpecialArr, 					ASN1_O_AOCS_SPECIAL_ARR),
		new OperationContainer(AOCDCurrency, 					ASN1_O_AOCD_CURRENCY),
		new OperationContainer(AOCDChargingUnit, 				ASN1_O_AOCD_CHARGING_UNIT),
		new OperationContainer(AOCECurrency, 					ASN1_O_AOCE_CURRENCY),
		new OperationContainer(AOCEChargingUnit, 				ASN1_O_AOCE_CHARGING_UNIT)
	};
	
	private static OperationContainer resultLocalOperations [] = {
		new OperationContainer(AOCChargingRequestResult, 		ASN1_O_CHARGING_REQUEST)
	};
	
	private static OperationContainer invokeGlobalOperations [] = {
		new OperationContainer(CCStatusRequest, 				ASN1_O_STATUS_REQUEST),
		new OperationContainer(CCBS_S_CallLinkageID, 			ASN1_O_CCBS_S_CALL_INFO_RETAIN),
		new OperationContainer(CCBS_S_CallLinkageID, 			ASN1_O_CCBS_S_REQUEST),
		new OperationContainer(CCBS_S_Reference, 				ASN1_O_CCBS_S_DEACTIVATE),
		new OperationContainer(CCBS_S_Interogate, 				ASN1_O_CCBS_S_INTERROGATE),
		new OperationContainer(CCBS_S_Erase, 					ASN1_O_CCBS_S_ERASE),
		new OperationContainer(CCBS_S_RemoteUserFree, 			ASN1_O_CCBS_S_REMOTE_USER_FREE),
		new OperationContainer(CCBS_S_Reference, 				ASN1_O_CCBS_S_CALL),
		new OperationContainer(CCBS_S_StatusRequest, 			ASN1_O_CCBS_S_STATUS_REQUEST),
		new OperationContainer(CCBS_S_BFree, 					ASN1_O_CCBS_S_B_FREE),
		new OperationContainer(CCBS_S_CallLinkageID, 			ASN1_O_CCBS_S_ERASE_CALL_LINKAGE_ID),
		new OperationContainer(CCBS_S_Reference, 				ASN1_O_CCBS_S_CCBS_STOP_ALERTING),
		new OperationContainer(CCBS_S_CallLinkageID, 			ASN1_O_CCNR_S_REQUEST),
		new OperationContainer(CCBS_S_Interogate, 				ASN1_O_CCNR_S_INTERROGATE),
		new OperationContainer(CCBS_T_Request, 					ASN1_O_CCBS_T_REQUEST)
	};
	
	private static OperationContainer resultGlobalOperations [] = {
		new OperationContainer(CCStatusRequestRes, 				ASN1_O_STATUS_REQUEST),
		new OperationContainer(CCBS_S_RequestRes, 				ASN1_O_CCBS_S_REQUEST),
		new OperationContainer(CCBS_S_InterogateRes, 			ASN1_O_CCBS_S_INTERROGATE),
		new OperationContainer(CCBS_S_StatusRequestRes, 		ASN1_O_CCBS_S_STATUS_REQUEST),
		new OperationContainer(CCBS_T_RequestRes, 				ASN1_O_CCNR_T_REQUEST),
		new OperationContainer(CCBS_S_RequestRes, 				ASN1_O_CCNR_S_REQUEST),
		new OperationContainer(CCBS_S_InterogateRes, 			ASN1_O_CCNR_S_INTERROGATE),
		new OperationContainer(CCBS_T_RequestRes, 				ASN1_O_CCBS_T_REQUEST),
	};
	
	public static ASN1CustomComponent [] getInvokeTable (ASN1Component component) {
		if (component instanceof ASN1Integer) {
			return getInvokeTable((ASN1Integer)component);
		} else if (component instanceof ASN1ObjectID) {
			return getInvokeTable((ASN1ObjectID)component);
		} else return null;
	}
	
	private static ASN1CustomComponent [] getInvokeTable (ASN1Integer asn1Integer) {
		for (int i = 0; i < OperationDecoder.invokeLocalOperations.length; i++) {
			if (asn1Integer.getValue() == OperationDecoder.invokeLocalOperations[i].getLongValue()) {
				return OperationDecoder.invokeLocalOperations[i].getAsn1CustomComponent();
			}
		}
		return null;
	}
	
	private static ASN1CustomComponent [] getInvokeTable (ASN1ObjectID asn1ObjectID) {
		for (int i = 0; i < OperationDecoder.invokeGlobalOperations.length; i++) {
			if (OperationDecoder.invokeGlobalOperations[i].getStringValue().equals(asn1ObjectID.getContents().toString())) {
				return OperationDecoder.invokeGlobalOperations[i].getAsn1CustomComponent();
			}
		}
		return null;
	}
	
	public static ASN1CustomComponent [] getReturnResultTable (ASN1Component component) {
		if (component instanceof ASN1Integer) {
			return getReturnResultTable((ASN1Integer)component);
		} else if (component instanceof ASN1ObjectID) {
			return getReturnResultTable((ASN1ObjectID)component);
		} else return null;
	}
	
	private static ASN1CustomComponent [] getReturnResultTable (ASN1Integer asn1Integer) {
		for (int i = 0; i < OperationDecoder.resultLocalOperations.length; i++) {
			if (asn1Integer.getValue() == OperationDecoder.resultLocalOperations[i].getLongValue()) {
				return OperationDecoder.resultLocalOperations[i].getAsn1CustomComponent();
			}
		}
		return null;
	}
	
	private static ASN1CustomComponent [] getReturnResultTable (ASN1ObjectID asn1ObjectID) {
		for (int i = 0; i < OperationDecoder.resultGlobalOperations.length; i++) {
			if (OperationDecoder.resultGlobalOperations[i].getStringValue().equals(asn1ObjectID.getContents().toString())) {
				return OperationDecoder.resultGlobalOperations[i].getAsn1CustomComponent();
			}
		}
		return null;
	}
}
