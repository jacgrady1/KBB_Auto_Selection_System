package uitil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

import CustomizedException.MissItemException;
import model.Automobile;
import model.Optionset;
import model.Optionset.Option;

public class ReadSource implements Serializable{
	/*
	 * in ReadSouce,readFile is used to setup 
	 * a automobile from input txt file
	 */

	public static Automobile readFile(String filename) {
		StringBuffer data = new StringBuffer();
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (line.equals("end"))
					eof = true;
				else
					data.append(line + "\n");
			}
			buff.close();
		} catch (IOException e) {
			System.out.println("Error--" + e.toString());
		}
	    Automobile auto=new Automobile();
		
	    String st = data.toString();
		StringTokenizer allStr = new StringTokenizer(st, "\n");
		
		
		
		for (int i=0;allStr.hasMoreTokens();i++){ // i is the number of line
			String line = allStr.nextToken();
			if (i==0) 
			{
				try {
					if (line.trim().length() == 0) 
					{throw new MissItemException("miss Make");}
					else auto.setMake(line);	
				}
				catch (Exception e)
				{   auto.setMake("null");
					System.out.println(e);}
			}
			else if (i==1){
				try{
					if (line.trim().length()==0){throw new MissItemException("miss Model");}
					else {auto.setModel(line);}
				}
				catch (Exception e)
				{   
					auto.setModel("missing");
					System.out.println(e);
				}
				
			}
			else if  (i==2){
				try
				{if(line.trim().length()==0){throw new MissItemException("miss BasePrice");}
				else auto.setBasePrice(Integer.parseInt(line));
				}
				catch(Exception e)
				{ auto.setBasePrice(0);
				System.out.println(e);
					}
				}
			
			else{ // starting from line 3
                int indexSet=i-3;
				auto.addOptionSet();
				StringTokenizer lineStr=new StringTokenizer(line,":"); //every line
				
				for (int j=0;lineStr.hasMoreTokens();j++){
					String linePart=lineStr.nextToken();
					
					if (j==0){
						auto.setOptionsetName(indexSet, linePart);} // set color as optionset name
					
					
					else{
						StringTokenizer lineBack=new StringTokenizer(linePart,",");
						for (int k=0;lineBack.hasMoreTokens();k++){ //optionindex
							auto.addOption(indexSet);
							String option_price=lineBack.nextToken();
							StringTokenizer op=new StringTokenizer(option_price,"|");
							for (int m=0;op.hasMoreTokens();m++){
								String str=op.nextToken();
								if (m==0)
								{   
									try{
										
									if (str.trim().length()==0){throw new MissItemException("miss option");}
									else{
									    auto.setOptionName(indexSet, str, k);	
									} // setYellow
									}
									catch(Exception e)
									{
										
									auto.setOptionName(indexSet,str,k); 
									System.out.println("please enter the name of option");

								
									}
								
								}
								else
								{
									try{
										if (str.trim().length()==0){throw new MissItemException("miss price");}
										else{
										    auto.setOptionPrice(indexSet, Float.parseFloat(str), k);	
										}
										}
										catch(Exception e)
										{
										System.out.println("Please enter price for option");

										}
								}
							}
						}
					}
				}
			}
		}
        return auto;
	}	
}
