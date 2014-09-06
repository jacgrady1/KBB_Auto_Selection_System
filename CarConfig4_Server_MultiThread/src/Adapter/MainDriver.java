package Adapter;

import model.Automobile;
import scale.EditOptions;
import uitil.ReadSource;

public class MainDriver {
    /*
     * here I put the main method to test all circumstances
     */
	public static void main(String[] args) {
		
		BuildAuto newCar=new BuildAuto();
		
		BuildAuto newCar1=new BuildAuto();
		
		BuildAuto newCar2=new BuildAuto();
		
//		try {
//			newCar.buildAuto("FordZTW.txt"); // This car is a good car with no exception raised
//		    newCar.printAuto("Wagon ZTW");}
//		
//		catch(Exception e){
//			System.out.println("miss File no cars inputed");
//  		}
//		
//		try {
//			newCar1.buildAuto("FordABC.txt"); // This car is a good car with no exception raised
//		    newCar1.printAuto("Wagon ABC");}
//		
//		catch(Exception e){
//			System.out.println("miss File no cars inputed");
//  		}

		
			newCar2.buildAuto("PropertiesAuto1.properties"); // This car is a good car with no exception raised
		    newCar2.printAuto("Prius");
		
				
		System.out.println();
        
//		newCar.getGarage().get("Wagon ZTW").setOptionChoice("Color", "Liquid Grey Clearcoat Metallic");
//		
//		System.out.println(newCar.getGarage().get("Wagon ZTW").getOptionChoice("Color"));
//		
//        newCar.getGarage().get("Wagon ZTW").setOptionChoice("Transmission", "automatic");
//		
//		System.out.println(newCar.getGarage().get("Wagon ZTW").getOptionChoice("Transmission"));

		
		
		
//		Thread t1 = new Thread(new EditOptions("Wagon ZTW","Color","Fort Knox Gold Clearcoat Metallic",newCar.getGarage()));  
//	    t1.start();  
//	     
//		Thread t2 = new Thread(new EditOptions("Wagon ZTW","Color","Liquid Grey Clearcoat Metallic",newCar.getGarage()));  
//	    t2.start(); 
		
		
//		EditOptions t1=new EditOptions("Wagon ZTW","Color","Fort Knox Gold Clearcoat Metallic",newCar.getGarage());
//		
//		new Thread(t1,"Thread User1  ").start();
//		
//		EditOptions t2=new EditOptions("Wagon ZTW","Transmission","standard",newCar.getGarage());
//		
//	    new Thread(t2,"Thread User2  ").start();
//
//	    
//	    
//        EditOptions t3=new EditOptions("Wagon ABC","Color","red",newCar1.getGarage());
//		
//		new Thread(t3,"Thread User3  ").start();
//		
//		EditOptions t4=new EditOptions("Wagon ABC","Color","green",newCar1.getGarage());
//		
//	    new Thread(t4,"Thread User4  ").start();
		
	}

	
}
