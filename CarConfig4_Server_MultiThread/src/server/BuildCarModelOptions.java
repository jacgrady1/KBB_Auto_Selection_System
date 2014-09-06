package server;

import Adapter.BuildAuto;
import uitil.FileIO;
import model.Automobile;

public class BuildCarModelOptions {

	public void putAutoFromPropertyIntoGarage(String filename)
	{
		AutoServer auto = new BuildAuto();
        auto.putAutoFromPropertyIntoGarage(filename);		
	}
	
}
