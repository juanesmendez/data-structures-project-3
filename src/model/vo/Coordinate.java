package model.vo;

public class Coordinate implements Comparable<Coordinate>{
	
	public double latitude;
	public double longitude;
	
	public Coordinate(double latitude,double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Latitude: "+this.latitude+"\nLongitude: "+this.longitude;
	}
	@Override
	public int compareTo(Coordinate o) {
		// TODO Auto-generated method stub
		if(this.latitude < o.latitude) {
			return -1;
		}else if(this.latitude > o.latitude) {
			return 1;
		}else if(this.longitude < o.longitude) {
			return -1;
		}else if(this.longitude > o.longitude) {
			return 1;
		}
		
		return 0;
	}
	
}
