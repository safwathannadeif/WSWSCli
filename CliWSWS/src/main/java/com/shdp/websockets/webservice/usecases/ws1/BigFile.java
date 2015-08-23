package com.shdp.websockets.webservice.usecases.ws1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.util.Optional;
import java.util.stream.Stream;

//C:\temp\LargeFile>fsutil file createnew 1.5GB.test 1073741824
//It needs about 2 G  of memory. Need some more work for such big files
public class BigFile {
	
	

public static  String[]  readFileAsStreamOfString (String path, String fileName) throws IOException
{
    //Stream<String> lines = Files.lines(Paths.get("c:/temp", "data.txt"));
    Stream<String> lines = Files.lines(Paths.get(path, fileName));
  
   // System.out.println("NoOfLines=" + lines.count() ) ;
    ///////////////
   
    //String[] arrayOfStrs  = lines.toArray(String[]::new);
    String[] arrayOfStrs  = lines.toArray(size -> new String[size]);
    
   /*
    //lines.forEach(strx -> {    System.out.println("lineNo=["  + strx+"]");}); 
    Optional<String> hasSeq = lines.filter(s -> s.contains("123457")).findFirst();
    if(hasSeq.isPresent()){
        System.out.println(hasSeq.get());
    }
    //Close the stream and it's underlying file as well
*/   lines.close();
   return arrayOfStrs ;
 
}

public static void main(String[] args) throws IOException {
	
	System.out.println("Start Big File ...");
	String path = "C:/temp/LargeFile" ;
	String filename= "LargeTextFile.txt";
	
	 String[] retStrAry = BigFile.readFileAsStreamOfString(path,filename) ;
	 System.out.println("Array Size= "  +  retStrAry.length) ;
	 int i=0 ;
	 for (String str: retStrAry )
	 {
		 System.out.println("[" + ++i + "] ==>" + str ) ;
	 }
	
	
			

	
}

}