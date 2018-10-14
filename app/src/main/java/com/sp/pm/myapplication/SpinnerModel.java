package com.sp.pm.myapplication;


public class SpinnerModel {
	
	private  String CompanyName="";
	private  String VehicleName="";
	private  String CabName="";
	private  String Image=""; 
	private  String Url="";
	
	/*********** Set Methods ******************/
	public void setCompanyName(String CompanyName)
	{
		this.CompanyName = CompanyName;
	}
	public void setVehicleName(String VehicleName)
	{
		this.VehicleName = VehicleName;
	}
	public void setCabName(String CabName)
	{
		this.CabName = CabName;
	}
	//public void setImage(String Image)
	//{
	//	this.Image = Image;
	//}
	
	//public void setUrl(String Url)
	//{
	//	this.Url = Url;
	//}
	
	/*********** Get Methods ****************/
	public String getCompanyName()
	{
		return this.CompanyName;
	}
	public String getVehicleName()
	{
		return this.VehicleName;
	}
	public String getCabName()
	{
		return this.CabName;
	}
	//public String getImage()
	//{
	//	return this.Image;
	//}

	//public String getUrl(){return this.Url;}
}
