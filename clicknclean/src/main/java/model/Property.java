package model;

public class Property {
	private Address propertyAddress;
	private int propertySurface;

	public Property(Address propertyAddress, int propertySurface) {
		this.propertyAddress = propertyAddress;
		this.propertySurface = propertySurface;
	}

	public Address getPropertyAddress() {
		return propertyAddress;
	}

	public int getPropertySurface() {
		return propertySurface;
	}


}
