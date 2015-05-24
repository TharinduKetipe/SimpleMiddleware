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


public class SquareServer2 {



    public static void main(String[] args) throws IOException {

        try{

            //Creating the server Socket

            ServerSocket serverSocket = new ServerSocket(8081);
            System.out.println("SERVER IS LISTING TO PORT : 8081");

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
            Long type = (Long) clntObj.get("type");

            //System.out.println(number );
            //System.out.println(type );

            //Calculation
            Double result = Math.pow(number,2);

            //Martialing
            JSONObject resultobj = new JSONObject();
            resultobj.put("calculation", new String("Square"));
            resultobj.put("result", result);

            //System.out.println(resultobj);

            //Sending the data to client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            String resultdata = resultobj.toJSONString();
            System.out.println(resultdata);
            out.println(resultdata);

        }catch (IOException e){

            System.out.println(e);
            System.out.println("Starting the Square server 2 is failed.");
        }







    }
}
