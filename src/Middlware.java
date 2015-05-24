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


public class Middlware {

    public static void main(String[] args) throws IOException {


        try {

            //Connecting with the client
            ServerSocket serverSocketmc1 = new ServerSocket(8000);
            System.out.println("SERVER IS LISTING TO PORT : 8000");

            //Accepting the client's request

            Socket clientSocketmc1 = serverSocketmc1.accept();
            System.out.println("NOW CLIENT IS CONNECTED. ");

            //Reading the recieved data

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocketmc1.getInputStream()));
            JSONObject clntObj = (JSONObject) JSONValue.parse(in.readLine());
            System.out.println(clntObj);
            //String client = in.readLine();
            // System.out.println(client);

            //Unmartial the data
            Double number = (Double) clntObj.get("number");
            Long type = (Long) clntObj.get("type");

            //System.out.println(number );
            //System.out.println(type );

            //close the connection
            in.close();
            clientSocketmc1.close();
            serverSocketmc1.close();

            //Routing to the correct server

            if (type==1){

                try {

                    //Creating the connection with the server
                    System.err.println(InetAddress.getLocalHost());
                    Socket clientSocketsq1 = new Socket("127.0.0.1",8081);

                    //Sending the data via tcp socket
                    String jsondatasq1 = clntObj.toJSONString();
                    PrintWriter outsq1 = new PrintWriter(clientSocketsq1.getOutputStream(),true);
                    outsq1.println(jsondatasq1);
                    System.out.println(jsondatasq1);

                    //Get the result from the server
                    BufferedReader insq1 = new BufferedReader(new InputStreamReader(clientSocketsq1.getInputStream()));
                    JSONObject resultobjsq1 = (JSONObject) JSONValue.parse(insq1.readLine());
                    System.out.println(resultobjsq1);

                    //close the connection with the server
                    outsq1.close();
                    insq1.close();
                    clientSocketsq1.close();

                    try {

                        //starting the connection with the client
                        System.err.println(InetAddress.getLocalHost());
                        Socket clientSocketsq1cl = new Socket("127.0.0.1",8010);

                        //Sending the data to client
                        PrintWriter outclntsq1 = new PrintWriter(clientSocketsq1cl.getOutputStream(),true);
                        String resultdatasq1 = resultobjsq1.toJSONString();
                        System.out.println(resultdatasq1);
                        outclntsq1.println(resultdatasq1);

                        outclntsq1.close();
                        clientSocketsq1cl.close();

                    }catch (IOException e){

                        System.out.println(e);
                        System.out.println("Sending answer to the client is failed.");
                    }



                }catch (IOException e){

                    System.out.println(e);
                    System.out.println("Connecting with the Square server 1 is failed.");
                }



            }else {

                try {

                    //Creating the connection with the server
                    System.err.println(InetAddress.getLocalHost());
                    Socket clientSocketsq1 = new Socket("127.0.0.1",8091);

                    //Sending the data via tcp socket
                    String jsondatasq1 = clntObj.toJSONString();
                    PrintWriter outsq1 = new PrintWriter(clientSocketsq1.getOutputStream(),true);
                    outsq1.println(jsondatasq1);
                    System.out.println(jsondatasq1);

                    //Get the result from the server
                    BufferedReader insq1 = new BufferedReader(new InputStreamReader(clientSocketsq1.getInputStream()));
                    JSONObject resultobjsq1 = (JSONObject) JSONValue.parse(insq1.readLine());
                    System.out.println(resultobjsq1);

                    //close the connection with the server
                    outsq1.close();
                    insq1.close();
                    clientSocketsq1.close();


                    try {

                        //starting the connection with the client
                        System.err.println(InetAddress.getLocalHost());
                        Socket clientSocketsq1cl = new Socket("127.0.0.1",8010);

                        //Sending the data to client
                        PrintWriter outclntsq1 = new PrintWriter(clientSocketsq1cl.getOutputStream(),true);
                        String resultdatasq1 = resultobjsq1.toJSONString();
                        System.out.println(resultdatasq1);
                        outclntsq1.println(resultdatasq1);

                        outclntsq1.close();
                        clientSocketsq1cl.close();

                    }catch (IOException e){


                        System.out.println(e);
                        System.out.println("Sending the answer to the client is failed.");
                    }







                }catch (IOException e){

                    System.out.println(e);
                    System.out.println("Connecting with the Root server 1 is failed.");
                }






            }

        }catch (IOException e){

            System.out.println(e);
            System.out.println("Connecting with the client is failed.");
        }


    }




}
