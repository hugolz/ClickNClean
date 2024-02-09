package model;

public class Property {
	private String propertyAddress;
	private int propertySurface;
	
	public Property(String propertyAddress, int propertySurface) {
		this.propertyAddress = propertyAddress;
		this.propertySurface = propertySurface;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public int getPropertySurface() {
		return propertySurface;
	} 
	

}
