package com.shdp.websockets.startup;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class converImgToBase64 {
	
	
	
	    public static void main(String[] args) throws Exception {
	    	Path originalPath = Paths.get("c:/root_pf/Plunker/SpinnerWithTimeOut2", "spin.gif");		//C:\root_pf\Plunker\SpinnerWithTimeOut2\spin.gif
	    	Path targetPath = Paths.get("c:/root_pf/Plunker/SpinnerWithTimeOut2", "spinEncoded64.txt");
	    	Base64.Encoder mimeEncoder = Base64.getMimeEncoder();
	    	try(OutputStream output = Files.newOutputStream(targetPath)){
	    	    //Copy the encoded file content to target file
	    	    Files.copy(originalPath, mimeEncoder.wrap(output));
		}

}
}
