package test;

import asn1.ASN1Data;
import asn1.com.ASN1Exception;
import asn1.com.ASN1Message;
import protocols.etsi.ETSIMessage;

public class Decode {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		String msg = "a10b0201df0606040082670206a112020117020122300aa1053003020100820101";
		
		try {
			ASN1Message message = new ETSIMessage(new ASN1Data(msg));
			message.printMessage();
		} catch (ASN1Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
