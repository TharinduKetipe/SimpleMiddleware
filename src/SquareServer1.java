/**
 * Created by tharindu on 5/23/15.
 */

import java.io.*;
import java.net.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import sun.org.mozilla.javascript.json.JsonParser;


public class SquareServer1 {



    public static void main(String[] args) throws IOException {

        //Creating the server Socket

        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("SERVER IS LISTING TO PORT : 8888");

        //Accepting the client's request

        Socket clientSocket = serverSocket.accept();
        System.out.println("NOW CLIENT IS CONNECTED. ");

        //Reading the recieved data

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        JSONObject clntObj = (JSONObject) JSONValue.parse(in.readLine());
        System.out.println(clntObj);
        //String client = in.readLine();
       // System.out.println(client);

        //Unmartial the data
        Double number = (Double) clntObj.get("number");
        //Integer type = (Integer) clntObj.get("type");

        System.out.print(number );


    }
}
