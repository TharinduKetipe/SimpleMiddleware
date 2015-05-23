/**
 * Created by tharindu on 5/23/15.
 */
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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




               //Converting inputs to json object

               JSONObject data = new JSONObject();
               data.put("number", num);
               data.put("type", type);
               //System.out.println(data);

               //Sending the data via tcp socket
               String jsondata = data.toJSONString();
               PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
               out.println(jsondata);
               //System.out.println(jsondata);

               //Get the result from the server
               BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
               JSONObject resultobj = (JSONObject) JSONValue.parse(in.readLine());
               //System.out.println(resultobj);

               //Unmartial the results
               String cal = (String) resultobj.get("calculation");
               Double result = (Double) resultobj.get("result");
               //System.out.println(cal);
               //System.out.println(result);

               //Print the result
               System.out.println("             The " + cal + " of the number " + num + " is: " + result + "\n");
               System.out.println("***********************   All rights reserved    ***********************");


           }else {

               System.err.print("Please enter the 1 or 2 only.");
           }


       }




}
