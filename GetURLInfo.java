// This class based on the code from from the book _Java in a Nutshell_ by David Flanagan.
// Written by David Flanagan.  Copyright (c) 1996 O'Reilly & Associates.
package phase2;

import java.net.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

import static phase2.Phase2Main.*;

public class GetURLInfo{
		   
    public static void printURLInfo(String u) throws MalformedURLException, IOException{
        URL url = new URL(u);
        URLConnection uc = url.openConnection();
        
        writer.println(uc.getURL().toExternalForm() + ":");
        writer.println("Content Type: "+uc.getContentType());
        writer.println("Content Length: "+uc.getContentLength()); 
        writer.println("Last Modified: "+new Date(uc.getLastModified()));
        writer.println("Expiration: "+uc.getExpiration());
        writer.println("Content Encoding: "+uc.getContentEncoding()); //Which table of characters site uses (ASCII, Unicode...)
        writer.flush();
        
        if(uc.getContentType().contains("html"))
           saveFile(uc);
        else if(uc.getContentType().contains("jpeg"))
           saveImage(uc.getURL());
        
    }
    
    public static void saveFile(URLConnection uc) throws FileNotFoundException, IOException{
       String outFilename = uc.getURL().getFile().substring(uc.getURL().getFile().lastIndexOf("/")+1);
       File outFile = new File(outFilename);
       PrintWriter out = new PrintWriter(outFile);
       BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
       int count=0;
       for(int i=0; i<uc.getContentLength(); i++){
           String line = in.readLine();
           count++;
           if(line==null) break;
           out.println(line);
       }
       writer.println();
       writer.println(uc.getURL().toExternalForm());
       writer.println("Number of lines read: " + count);
       writer.println();
       writer.flush();
       out.close();
    }
       
    public static void saveImage(URL url) throws MalformedURLException, IOException{
       BufferedImage image = null;
       String outFilename = url.getFile().substring(url.getFile().lastIndexOf("/")+1);
       File imageFile = new File(outFilename);
       try {
          // Read from a URL
          image = ImageIO.read(url);
       } catch (IOException e) {
          e.printStackTrace();
       }

      ImageIO.write(image, "jpg", imageFile);
      writer.println();
      writer.println(url.toExternalForm());
      writer.println();
      writer.flush();
   }
}
		