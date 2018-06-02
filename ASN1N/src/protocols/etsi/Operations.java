package protocols.etsi;

public interface Operations {

	/**********************************    LOCAL VALUE DSS1 OPERATIONS   ***********************************/
	final static long ASN1_O_NO_CALLING_NAME				= 0x00;
	final static long ASN1_O_MCID_REQUEST					= 0x03;
	final static long ASN1_O_3PTY_BEGIN						= 0x04;
	final static long ASN1_O_3PTY_END						= 0x05;
	final static long ASN1_O_ECT_EXECUTE					= 0x06;
	final static long ASN1_O_CF_ACT_DIV						= 0x07;
	final static long ASN1_O_CF_DEACT_DIV					= 0x08;
	final static long ASN1_O_CF_ACT_STATUS_NOTI_DIV			= 0x09;
	final static long ASN1_O_CF_DEACT_STATUS_NOTI_DIV		= 0x0a;
	final static long ASN1_O_CF_INTERROGATION_DIV			= 0x0b;
	final static long ASN1_O_CF_DIVERSION_INFORMATION		= 0x0c;
	final static long ASN1_O_CF_CALL_DEFLECTION				= 0x0d;
	final static long ASN1_O_CF_CALL_REROUTING				= 0x0e;
	final static long ASN1_O_CF_DIVERTING_LEG_INFO_2		= 0x0f;
	final static long ASN1_O_CF_INVOKE_STATUS				= 0x10;
	final static long ASN1_O_CF_INTERROGATE					= 0x11;
	final static long ASN1_O_CF_DIVERTING_LEG_INFO_1		= 0x12;
	final static long ASN1_O_CF_DIVERTING_LEG_INFO_3		= 0x13;
	final static long ASN1_O_CHARGING_REQUEST				= 0x1e;
	final static long ASN1_O_AOCS_CURRENCY					= 0x1f;
	final static long ASN1_O_AOCS_SPECIAL_ARR				= 0x20;
	final static long ASN1_O_AOCD_CURRENCY					= 0x21;
	final static long ASN1_O_AOCD_CHARGING_UNIT           	= 0x22; 
	final static long ASN1_O_AOCE_CURRENCY           		= 0x23; 
	final static long ASN1_O_AOCE_CHARGING_UNIT				= 0x24;
	final static long ASN1_O_CONF_BEGIN						= 0x28;
	final static long ASN1_O_CONF_ADD						= 0x29;
	final static long ASN1_O_CONF_PARTY_DISC				= 0x2e;
	
	/**********************************    GLOBAL VALUE DSS1 OPERATIONS   **********************************/
	final static String ASN1_O_STATUS_REQUEST				= "040081440901";
	final static String ASN1_O_CCBS_S_CALL_INFO_RETAIN		= "040082670101";
	final static String ASN1_O_CCBS_S_REQUEST				= "040082670102";
	final static String ASN1_O_CCBS_S_DEACTIVATE			= "040082670103";
	final static String ASN1_O_CCBS_S_INTERROGATE			= "040082670104";
	final static String ASN1_O_CCBS_S_ERASE					= "040082670105";
	final static String ASN1_O_CCBS_S_REMOTE_USER_FREE		= "040082670106";
	final static String ASN1_O_CCBS_S_CALL					= "040082670107";
	final static String ASN1_O_CCBS_S_STATUS_REQUEST		= "040082670108";
	final static String ASN1_O_CCBS_S_B_FREE				= "040082670109";
	final static String ASN1_O_CCBS_S_ERASE_CALL_LINKAGE_ID	= "04008267010a";
	final static String ASN1_O_CCBS_S_CCBS_STOP_ALERTING	= "04008267010b";
	final static String ASN1_O_CSTA_SERVIVE_DATA			= "040082670150";
	final static String ASN1_O_CCNR_T_REQUEST				= "040082670201";
	final static String ASN1_O_MWI_ACTIVATE					= "040085690101";
	final static String ASN1_O_MWI_DEACTIVATE				= "040085690102";
	final static String ASN1_O_MWI_INDICATE					= "040085690103";
	final static String ASN1_O_CCNR_S_REQUEST				= "040088290101";
	final static String ASN1_O_CCNR_S_INTERROGATE			= "040088290102";
	final static String ASN1_O_CCBS_T_REQUEST				= "040082670201";
	final static String ASN1_O_CCBS_T_CALL					= "040082670202";
	final static String ASN1_O_CCBS_T_SUSPEND				= "040082670203";
	final static String ASN1_O_CCBS_T_RESUME				= "040082670204";
	final static String ASN1_O_CCBS_T_REMOTE_USER_FREE		= "040082670205";
	final static String ASN1_O_CCBS_T_AVAILABLE				= "040082670206";
}
