package phase2;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Phase2Main {
   
   public static PrintWriter writer;
   
   public static void main(String[] args){
      
      File inFile, outFile;
      
      if(args.length>1)
         outFile = new File(args[1]); 
      else
         outFile = new File("./phase2Output.txt");
      
      if(args.length>0)
         inFile = new File(args[0]);
      else{
         JOptionPane.showMessageDialog(null, "Choose an input file.");
         inFile = chooseFile();
      }
      
      try{      
         writer = new PrintWriter(outFile); 
         Scanner in = new Scanner(new FileReader(inFile));
         
         while(in.hasNext())
            try {
               GetURLInfo.printURLInfo(in.nextLine());
            } catch (MalformedURLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         
         in.close(); 

      }catch(FileNotFoundException e){
         JOptionPane.showMessageDialog(null, "File not found.");
      }
        
   }
   
   public static File chooseFile(){
      JFileChooser chooser;
      int status;
      chooser = new JFileChooser();
      status = chooser.showOpenDialog(null);

      if (status == JFileChooser.APPROVE_OPTION) 
          return chooser.getSelectedFile();
      else
         JOptionPane.showMessageDialog(null, "Open File dialog canceled.");
      
      return null;
   }
}
