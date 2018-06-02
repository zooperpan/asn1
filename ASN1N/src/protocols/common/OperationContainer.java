package protocols.common;

import asn1.ASN1CustomComponent;

public class OperationContainer {

	private ASN1CustomComponent [] asn1CustomComponent;
	private long longValue;
	private String stringValue;
	
	public OperationContainer (ASN1CustomComponent [] asn1CustomComponent, long value) {
		this.asn1CustomComponent = asn1CustomComponent;
		this.longValue = value;
	}
	
	public OperationContainer (ASN1CustomComponent [] asn1CustomComponent, String value) {
		this.asn1CustomComponent = asn1CustomComponent;
		this.stringValue = value;
	}
	
	public ASN1CustomComponent[] getAsn1CustomComponent() {
		return this.asn1CustomComponent;
	}

	public void setAsn1CustomComponent(ASN1CustomComponent[] asn1CustomComponent) {
		this.asn1CustomComponent = asn1CustomComponent;
	}

	public long getLongValue() {
		return this.longValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	public String getStringValue() {
		return this.stringValue;
	}

	public void setIntValue(String stringValue) {
		this.stringValue = stringValue;
	}
}