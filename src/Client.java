/**
 * Created by tharindu on 5/23/15.
 */
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

       public static void main (String[] args) throws IOException {

       //Open the TCP connection
           System.err.println(InetAddress.getLocalHost());
           Socket clientSocket = new Socket("127.0.0.1",8888);

       //Get the input
           System.out.println("***********************Square and Root Calculator***********************");
           System.out.println("****************       Enter the number:         ***********************");
           Scanner scanner = new Scanner(System.in);
           Double num = scanner.nextDouble();
           System.out.println("*******  Enter 1 to view the square , 2 to view the squareroot *********");
           Integer type = scanner.nextInt();


           if (type == 1 || type == 2){

               System.out.println("***********************   All rights reserved    ***********************");


               //Converting inputs to json object

               JSONObject data = new JSONObject();
               data.put("number", new Double(num));
               data.put("type", new Integer(type));
               System.out.println(data);

               //Sending the data via tcp socket
               String jsondata = data.toJSONString();
               PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
               out.println(jsondata);
               System.out.println(jsondata);

           }else {

               System.err.print("Please enter the 1 or 2 only.");
           }


       }




}
