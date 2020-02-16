import java.io.*;
import java.net.*;

class cl2 {
    public static Socket s;
 public static void main(String args[]) throws Exception {
     int i=0;
     try{
         s = new Socket("192.168.43.45", 5555); 
     }
     catch(Exception e)
     {
         e.printStackTrace();
     }
     while(i<100){
    try{
        // Creating new socket connection to the IP (first parameter) and its opened port (second parameter)
        
        // Initialize output stream to write message to the socket stream
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        String outMsg = "";
        outMsg = "This is Client";
        // Write message to stream
        out.write(outMsg);
        // Flush the data from the stream to indicate end of message
        out.flush();
        // Close the output stream
        out.close();
        // Close the socket connection
        s.close();
        }
        catch(Exception ex){
        //:TODO Handle exceptions
      i++;  }
     }
    }
}