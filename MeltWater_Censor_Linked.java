package easy_problems;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MeltWater_Censor_Linked {

	public static void main(String args[]) throws IOException
	{
		final String inputfile ="Input.txt";
		final String censoredfile = "Censored.txt";
		final String outputFile = "outputFile.txt";
		String final_Output;

		//Map<String, String> document_Input_Strings = Collections.synchronizedMap(new LinkedHashMap<String, String>());
		
		//Map<String, String> censored_Input_Strings = Collections.synchronizedMap(new LinkedHashMap<String, String>());
		
		LinkedList<String> document_Input_Strings = new LinkedList<String>();
		LinkedList<String> censored_Input_Strings = new LinkedList<String>();
		
		MeltWater_Censor_Linked mwObj = new MeltWater_Censor_Linked();
		
		//Read file function
		censored_Input_Strings = MeltWater_Censor_Linked.readWriteFile(censoredfile, "readCensored", null);
		
		document_Input_Strings = MeltWater_Censor_Linked.readWriteFile(inputfile, "readDocument", null);
		
		final_Output = MeltWater_Censor_Linked.censor_File(censored_Input_Strings, document_Input_Strings);
		
		MeltWater_Censor_Linked.readWriteFile(outputFile, "write", final_Output);
		
		//System.out.println("The final output string : " + final_Output);
		
		System.out.println("Document in put : " + document_Input_Strings);
		
		System.out.println("censored in put : " + censored_Input_Strings);

	}
	
	private static LinkedList<String> readWriteFile(String fileName, String operation, String data) throws IOException
	{
		List<String> output_String_List = new ArrayList<String>();
		//Map<String, String> output_String_Table = Collections.synchronizedMap(new LinkedHashMap<String, String>());
		
		LinkedList<String> output_String_Table = new LinkedList<String>();
	
		if(operation.equalsIgnoreCase("readDocument") || operation.equalsIgnoreCase("readCensored"))
		{
		BufferedReader br = null;

		try {
			String sCurrentLine;

			br = new BufferedReader(new FileReader(fileName));
			
			while ((sCurrentLine = br.readLine()) != null) {
				Matcher m = Pattern.compile("(\\S*([^\"|\'])\\S*|\".+?\"|\')(\\s*)").matcher(sCurrentLine);
				while (m.find())
					output_String_List.add(m.group(1)); 

				
				//System.out.println("The List" + output_String_List.toString());
				
				for(int i=0;i<output_String_List.size();i++)
				{
					
					 String clean = output_String_List.get(i);
					 clean = clean.replaceAll("\\”","");
					 clean = clean.replaceAll("\\“","");
					 clean = clean.replaceAll("\\’","");
					 clean = clean.replaceAll("\\’","");

					String[] temp_comma_sep = clean.replaceAll("^[,\\s]+", "").split("[,\\s]+");
					 
					 for(int k=0;k<temp_comma_sep.length;k++)
					 {
					output_String_Table.add(temp_comma_sep[k]);
					 }
				}
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		}
		
		else if(operation.equalsIgnoreCase("write"))
		{
			
			 BufferedWriter bw = null;
		      try {

		         //Specify the file name and path here
			 File file = new File(fileName);

			  if (!file.exists()) {
			     file.createNewFile();
			  }

			  FileWriter fw = new FileWriter(file);
			  bw = new BufferedWriter(fw);
			  bw.write(data);
		          System.out.println("File written Successfully");

		      } catch (IOException ioe) {
			   ioe.printStackTrace();
			}
			finally
			{ 
			   try{
			      if(bw!=null)
				 bw.close();
			   }catch(Exception ex){
			       System.out.println("Error in closing the BufferedWriter"+ex);
			    }
			}
		}
		else
		{
			System.out.println("Invalid File operation");
		}
		
		return output_String_Table;
	}
	
	private static String censor_File(LinkedList<String> censored_File, LinkedList<String> main_Document){
		
		StringBuffer final_Output = new StringBuffer("");
		
		//Iterate through document to find any censored words
		//Set<String> keys = main_Document.keySet();
		//Iterator<String> itr = keys.iterator();
		
		
		for(int i=0;i<main_Document.size();i++)
		{
			String checker = main_Document.get(i);
			
			if(censored_File.contains(checker))
			{
				  String censored_String = checker.replaceAll("(?s).", "X");
		    	  final_Output.append(" "+censored_String+" ");
			}
			else
			{
				final_Output.append(" "+ checker + " ");
				
			}
		
		}
		
		/*
		while (itr.hasNext()) { 
		       String str = itr.next();
		       if(censored_File.containsValue(str))
		       {
		    	  String censored_String = str.replaceAll("(?s).", "X");
		    	  final_Output.append(" "+censored_String+" ");
		       }
		       else
		       {
		    	   final_Output.append(" "+ str + " ");
		    	   
		       }
		    } 
		    */
		
		
		return final_Output.toString();
	}
	
	
	

}
