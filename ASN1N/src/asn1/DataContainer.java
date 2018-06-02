package asn1;

import java.util.ArrayList;

public class DataContainer {

	private short[] value					= null;
	private String name						= null;
	private String valueName				= null;
	private boolean isConstructed			= false;
	private boolean isBitstring				= false;
	private ArrayList<DataContainer> data	= null;
	private ArrayList<BitContainer> bits	= null;
	private int length						= 0;
	
	
	public DataContainer (short[] value, String name, String valueName, int length, boolean isConstructed) {
		this.value = value;
		this.name = name;
		this.valueName = valueName;
		this.length = length;
		this.isConstructed = isConstructed;
		
		if (isConstructed) data = new ArrayList<DataContainer>();
	}
	
	public DataContainer (short[] value, int length, boolean isBitstring) {
		this.isBitstring = isBitstring;
		this.value = value;
		this.length = length;
		bits = new ArrayList<BitContainer>();
	}
	
	public short[] getValue () {
		return value;
	}
	
	public short getValueAtIndex (int i) {
		if ((value != null) && (i < value.length)) return value[i];
		else return -1;
	}
	
	public int getValueCount () {
		if (value != null) return value.length;
		else return 0;
	}
	
	public String getName () {
		return name;
	}
	
	public String getValueName () {
		return valueName;
	}
	
	public int getLength () {
		return length;
	}
	
	public boolean isContructed () {
		return isConstructed;
	}
	
	public boolean isBitstring () {
		return isBitstring;
	}
	
	public ArrayList<DataContainer> getData () {
		return data;
	}
	
	public DataContainer getDataAtIndex (int index) {
		if (data != null && index < data.size()) {
			return data.get(index);
		} else return null;
	}
	
	public void addData (DataContainer data) {
		if (this.data != null) this.data.add(data);
	}
	
	public int getDataCount () {
		if (data != null) return data.size();
		else return 0;
	}
	
	public void addBits (BitContainer bits) {
		if (this.bits != null) this.bits.add(bits);
	}
	
	public int getBitsCount () {
		if (bits != null) return bits.size();
		else return 0;
	}
	
	public BitContainer getBitAtIndex (int index) {
		if (bits != null && index < bits.size()) {
			return bits.get(index);
		} else return null;
	}
	
	public String toString () {
		return name;
	}
}
