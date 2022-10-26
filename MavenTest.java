package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MavenTest {

	static public String[] runCommand(String cmd)throws IOException
	{

	                List<String> list = new ArrayList<String>();
	                Process proc = Runtime.getRuntime().exec(cmd);
	                InputStream istr = proc.getInputStream();
	                BufferedReader br = new BufferedReader(new InputStreamReader(istr));
	                String str; // Temporary String variable
	                while ((str = br.readLine()) != null) 
	                    list.add(str);
	                        try { 
	                            proc.waitFor(); 
	                            }
	                        catch (InterruptedException e) {
	                            System.err.println("Process was interrupted"); 
	                            }
	                        br.close(); // Done.
	                        return list.stream().toArray(String[]::new);
	}
	
	            public static void main(String args[]) throws IOException
	            {
	            	List<String> pomFilePaths = new ArrayList<>();
	            	File[] directories = new File("C:\\Users\\manis\\Demo").listFiles(File::isDirectory);
	            	for(File f: directories) {
	            		if(f.isDirectory()) {
	            			boolean check = new File(f.getAbsolutePath(), "pom.xml").exists();
	            			if(check) {
	            				String pomPath = f.getAbsolutePath()+"\\"+"pom.xml";
	            				System.out.println(pomPath);
	            				pomFilePaths.add(pomPath);
	            			}
	            		}
	            	}
	            	for(String path:pomFilePaths) {
	            		 try
	 	                {
	 	                    String outlist[] = runCommand("cmd.exe /c mvn -f"+path+"dependency:tree -Dverbose");
	 	                    for (int i = 0; i < outlist.length; i++) {
	 	                    	if(outlist[i].contains("log4j-1.2")) {
	 	                    		System.out.println("Found in "+path);
	 	                    	}
	 	                    }
	 	                        
	 	                }   
	 	                catch (IOException e) { 
	 	                    System.err.println(e); 
	 	                }
	            	}
	            }
}
