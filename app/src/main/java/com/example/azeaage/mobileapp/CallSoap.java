package com.example.azeaage.mobileapp;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by AZeaage on 4/5/2017.
 */

class CallSoap {

    String WSDL_TARGET_NAMESPACE="http://tempuri.org/";
    SoapObject request;
    String SOAP_ADDRESS = "http://hr.alkaffary.com:664/AlKaffaryMobileService.svc";
    String  SOAP_ACTION;
    String OPERATION_NAME;


    private static final String JSON_ARRAY ="AuthenticateCustomerResponse";// the string must be the same as the name of the json object in the php file
    private static final String CustomerID = "CustomerID";// the string must be the same as the key name in the php file
    private static final String CustomerName= "CustomerName";// the string must be the same as the key name in the php file

    public String GetCompanyInfo(String tokenKey){
         SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetCompanyInfo";
        String OPERATION_NAME="GetCompanyInfo";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);

        //the first attribute

        request.addProperty("tokenKey",tokenKey);

        return connectToService();
    }
    public String RegisterUser(String tokenKey, String fullName, String email, String loginPassword, String phone1
            ,String phone2 , String address , String city , String gpsURL )
    {

         SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/RegisterUser";
         OPERATION_NAME="RegisterUser";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
       // PropertyInfo PI = new PropertyInfo();
        //set attributes
        System.out.println(fullName+" "+email+phone1+phone2+loginPassword+city+address+gpsURL+"*******&&&&&&&&");
        request.addProperty("tokenKey",tokenKey);
        request.addProperty("fullName",fullName);
        request.addProperty("email",email);
        request.addProperty("loginPassword",loginPassword);
        request.addProperty("phone1",phone1);
        request.addProperty("phone2",phone2);
        request.addProperty("address",address);
        request.addProperty("city",city);
        request.addProperty("gpsURL",gpsURL);

        return connectToService();

    }
   public String AuthenticateCustomer(String tokenKey, String email, String loginPassword){
       SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/AuthenticateCustomer";
       OPERATION_NAME="AuthenticateCustomer";

       request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);

       //set attributes

       request.addProperty("tokenKey",tokenKey);
       request.addProperty("email",email);
       request.addProperty("loginPassword",loginPassword);
       String response= connectToService();
               //"{\"AuthenticateCustomerResult\":[{\"CustomerID\":5,\"CustomerName\":\"norah abdulaziz\",\"CustomerAddress\":\"Riyadh\",\"CustomerCity\":\"Riyadh\",\"Latitude\":null,\"Longitude\":null,\"GPSURL\":\"24.703002, 46.723201\",\"Phone1\":\"0505050500\",\"Phone2\":\"0556693340\",\"Email\":\"norah@gmail.com\",\"LoginPassword\":\"123456\",\"isActive\":false,\"CreatedBy\":\"0DE4EA23-6420-43C2-B853-18E8D6B32837\",\"CreationDate\":\"\\/Date(1492415542107)\\/\",\"LastModifiedBy\":null,\"LastModificationDate\":null,\"Carts\":[],\"SalesOrders\":[],\"EntityState\":2,\"EntityKey\":{\"EntitySetName\":\"Customers\",\"EntityContainerName\":\"KMobileEntities\",\"EntityKeyValues\":[{\"Key\":\"CustomerID\",\"Value\":5}],\"IsTemporary\":false}}]}";
       /*String name="" , id ="";
       try {
           JSONObject jarray =new JSONObject(response);
           if (jarray.has("CustomerName")) {
           name=jarray.getString(CustomerName);}
           id=jarray.optString(CustomerID);

           System.out.println(name +"Customer name "+id);
           System.out.println(response);
       } catch (JSONException e) {
           e.printStackTrace();
       }*/
       return response;

   }
    private String connectToService(){
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.implicitTypes = true;//new add
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);
        String response =null;
        System.out.println("envelop ********  "+envelope.toString());
        try {

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            httpTransport.debug=true;
            // httpTransport.setXmlVersionTag("<?xmlx version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");

            httpTransport.call(SOAP_ACTION,envelope);
            //response=httpTransport.responseDump;
            //     SoapPrimitive response1= (SoapPrimitive)envelope.getResponse();
            SoapObject resultsReq=(SoapObject)envelope.bodyIn;
            response=resultsReq.toString();
        } catch (XmlPullParserException | IOException e)
        {
            e.printStackTrace();
            response= "" + e;
        }
        return response;
    }
}
