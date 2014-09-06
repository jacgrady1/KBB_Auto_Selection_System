package server;

import java.util.Properties;

public interface AutoServer {

	public void putAutoFromPropertyIntoGarage(String filename);
	public void putSerialIntoGarage(String filename);
	public void putAutoFromPropertyObjectIntoGarage(Properties prop1);

}
