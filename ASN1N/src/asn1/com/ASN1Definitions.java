package asn1.com;

public interface ASN1Definitions {
	
	static final int TAG_INDEX 				= 0;
	static final int LENGTH_INDEX 			= 1;
	static final int DATA_INDEX 			= 2;
	
	static final int MASK_EXTENDED	 		= 0x80;
	static final int EXTENDED_LENGTH 		= 0x80;
	
	static final short MASK_CLASS			= 0xC0;
	static final short UNIVERSAL			= 0x00;
	static final short APPLICATION			= 0x40;
	static final short CONTEXT				= 0x80;
	static final short PRIVATE				= 0xC0;
	
	static final short MASK_CONSTRUCTED		= 0x20;
	static final short PRIMITIVE			= 0x00;
	static final short CONSTRUCTED			= 0x20;
	
	
	/*        Standard ASN.1 Tags (X.208)        */
	static final short ASN1_TAG_NONE		= 0x20;
	static final short ASN1_TAG_BOOLEAN		= 0x01;
	static final short ASN1_TAG_INT			= 0x02;
	static final short ASN1_TAG_BIT_STR		= 0x03;
	static final short ASN1_TAG_OCTET_STR 	= 0x04;
	static final short ASN1_TAG_NULL		= 0x05;
	static final short ASN1_TAG_OBJ_ID		= 0x06;
	static final short ASN1_TAG_OBJ_DESC	= 0x07;
	static final short ASN1_TAG_EXTERNAL	= 0x08;
	static final short ASN1_TAG_REAL		= 0x09;
	static final short ASN1_TAG_ENUM		= 0x0a;
	static final short ASN1_TAG_SEQ 		= 0x10;
	static final short ASN1_TAG_SEQ_OF		= 0x10;
	static final short ASN1_TAG_SET			= 0x11;
	static final short ASN1_TAG_SET_OF		= 0x11;
	static final short ASN1_TAG_NUM_STR		= 0x12;
	static final short ASN1_TAG_PRINT_STR	= 0x13;
	static final short ASN1_TAG_TTX_STR		= 0x14;
	static final short ASN1_TAG_VTX_STR		= 0x15;
	static final short ASN1_TAG_IA5_STR		= 0x16;
	static final short ASN1_TAG_GEN_TIME	= 0x18;
	static final short ASN1_TAG_GRPH_STR	= 0x19;
	static final short ASN1_TAG_VIS_STR		= 0x1a;
	static final short ASN1_TAG_GEN_STR		= 0x1b;
	
	/*        Possible Tag values 0 - 31         */
	static final short MASK_ASN1_TAG		= 0x1f;
	static final short ASN1_TAG_0			= 0x00;
	static final short ASN1_TAG_1			= 0x01;
	static final short ASN1_TAG_2			= 0x02;
	static final short ASN1_TAG_3			= 0x03;
	static final short ASN1_TAG_4			= 0x04;
	static final short ASN1_TAG_5			= 0x05;
	static final short ASN1_TAG_6			= 0x06;
	static final short ASN1_TAG_7			= 0x07;
	static final short ASN1_TAG_8			= 0x08;
	static final short ASN1_TAG_9			= 0x09;
	static final short ASN1_TAG_10			= 0x0a;
	static final short ASN1_TAG_11			= 0x0b;
	static final short ASN1_TAG_12			= 0x0c;
	static final short ASN1_TAG_13			= 0x0d;
	static final short ASN1_TAG_14			= 0x0e;
	static final short ASN1_TAG_15			= 0x0f;
	static final short ASN1_TAG_16			= 0x10;
	static final short ASN1_TAG_17			= 0x11;
	static final short ASN1_TAG_18			= 0x12;
	static final short ASN1_TAG_19			= 0x13;
	static final short ASN1_TAG_20			= 0x14;
	static final short ASN1_TAG_21			= 0x15;
	static final short ASN1_TAG_22			= 0x16;
	static final short ASN1_TAG_23			= 0x17;
	static final short ASN1_TAG_24			= 0x18;
	static final short ASN1_TAG_25			= 0x19;
	static final short ASN1_TAG_26			= 0x1a;
	static final short ASN1_TAG_27			= 0x1b;
	static final short ASN1_TAG_28			= 0x1c;
	static final short ASN1_TAG_29			= 0x1d;
	static final short ASN1_TAG_30			= 0x1e;
	static final short ASN1_TAG_31			= 0x1f;
	
	/*              Component types              */
	static final short NONE_TYPE			= 0;
	static final short BOOLEAN_TYPE			= 1;
	static final short INT_TYPE				= 2;
	static final short BIT_STR_TYPE			= 3;
	static final short OCTET_STR_TYPE 		= 4;
	static final short NULL_TYPE			= 5;
	static final short OBJ_ID_TYPE			= 6;
	static final short OBJ_DESC_TYPE		= 7;
	static final short EXTERNAL_TYPE		= 8;
	static final short REAL_TYPE			= 9;
	static final short ENUM_TYPE			= 10;
	static final short SEQ_TYPE 			= 11;
	static final short SEQ_OF_TYPE			= 12;
	static final short SET_TYPE				= 13;
	static final short SET_OF_TYPE			= 14;
	static final short NUM_STR_TYPE			= 15;
	static final short PRINT_STR_TYPE		= 16;
	static final short TTX_STR_TYPE			= 17;
	static final short VTX_STR_TYPE			= 18;
	static final short IA5_STR_TYPE			= 19;
	static final short GEN_TIME_TYPE		= 20;
	static final short GRPH_STR_TYPE		= 21;
	static final short VIS_STR_TYPE			= 22;
	static final short GEN_STR_TYPE			= 23;
	static final short CHOICE_TYPE			= 24;
	static final short ANY_BY_TYPE			= 25;
	static final short ANY_INV_TYPE			= 26;
	static final short ANY_RES_TYPE			= 27;
	static final short ANY_ERR_TYPE			= 28;
	
	static final String TABS				= "   ";
}
