package asn1;

public class BitContainer  {

	private short value						= 0;
	private String name						= null;
	private String valueName				= null;
	private int numOfBits					= 0;
	private int firstBitIndex				= 0;
	
	public BitContainer(short value, String name, String valueName, int numOfBits, int firstBitIndex) {
		this.value = value;
		this.name = name;
		this.valueName = valueName;
		this.numOfBits = numOfBits;
		this.firstBitIndex = firstBitIndex;
	}

	public short getValue () {
		return value;
	}
	
	public int getNumOfBits () {
		return numOfBits;
	}
	
	public String getName () {
		return name;
	}
	
	public String getValueName () {
		
		String result = "";
		for (int i = 0; i < 8 ; i++) {
			if ((i >= firstBitIndex) && (i <= firstBitIndex + numOfBits - 1)) {
				if ((value & (1 << i)) == (1 << i)) {
					result = "1" + result;
				} else result = "0" + result;
			} else result = "." + result;
		}
		return  "(" + result + ") " + valueName;
	}
}
