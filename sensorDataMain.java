
public class sensorData {

	private String sensorName;
	private String sensorValue;
	private String userID;
	
	public sensorData(String userID, String sensorName, String sensorValue) {
		super();
		this.sensorName = sensorName;
		this.sensorValue = sensorValue;
		this.userID = userID;
	}

	public sensorData(String userID, String sensorName){
		this.userID = userID;
		this.sensorName = sensorName;
	}
	
	public String getSensorName() {
		return sensorName;
	}
	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	
	public String getSensorValue() {
		return sensorValue;
	}
	public void setSensorValue(String i) {
		this.sensorValue = i;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String sensorID) {
		this.userID = sensorID;
	}

	
}
