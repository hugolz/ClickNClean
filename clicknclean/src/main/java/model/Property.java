package model;

public class Property {
	private Address propertyAddress;
	private int propertySurface;
	private String accesCode;
	private String keyBoxCode;
	private String specialInstruction;
	private int ownerId;
	private int propertyId;

	public Property(Address propertyAddress, int propertySurface, String accesCode,
			String keyBoxCode, String specialInstruction, int ownerId, int propertyId) {
		this.propertyAddress = propertyAddress;
		this.propertySurface = propertySurface;
		this.accesCode = accesCode;
		this.keyBoxCode = keyBoxCode;
		this.specialInstruction = specialInstruction;
		this.ownerId = ownerId;
		this.propertyId = propertyId;
	}

	public Address getPropertyAddress() {
		return this.propertyAddress;
	}

	public int getPropertySurface() {
		return this.propertySurface;
	}

	public String getAccesCode() {
		return this.accesCode;
	}

	public String getKeyBoxCode() {
		return this.keyBoxCode;
	}

	public String getSpecialInstruction() {
		return this.specialInstruction;
	}

	public int getOwnerId() {
		return this.ownerId;
	}

	public int getPropertyId() {
		return this.propertyId;
	}

	public void setAccesCode(String accesCode) {
		this.accesCode = accesCode;
	}

	public void setKeyBoxCode(String keyBoxCode) {
		this.keyBoxCode = keyBoxCode;
	}

	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	} 
}
