package com.example.azeaage.mobileapp;

import android.content.Intent;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StreamCorruptedException;

/**
 * Created by AZeaage on 4/5/2017.
 */

class CallSoap {

    String WSDL_TARGET_NAMESPACE="http://tempuri.org/";
    SoapObject request;
    String SOAP_ADDRESS = "http://hr.alkaffary.com:664/AlKaffaryMobileService.svc";
    String  SOAP_ACTION;
    String OPERATION_NAME;
    private final String tokenKey="0DE4EA23-6420-43C2-B853-18E8D6B32837";


    private static final String JSON_ARRAY ="AuthenticateCustomerResponse";// the string must be the same as the name of the json object in the php file
    private static final String CustomerID = "CustomerID";// the string must be the same as the key name in the php file
    private static final String CustomerName= "CustomerName";// the string must be the same as the key name in the php file
    public String SaveGPSLocation( String docNum, String latitude, String longitude)
    {
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/SaveGPSLocation";
        String OPERATION_NAME="SaveGPSLocation";
        int DocNum= Integer.parseInt(docNum);
        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);
        PropertyInfo pi ;
        pi = new PropertyInfo();
        pi.setName("docNum");
        pi.setValue(DocNum);
        pi.setType(Integer.class);
        request.addProperty(pi);
        request.addProperty("latitude",latitude);
        request.addProperty("longitude",longitude);
        return connectToService();
    }
    public String GetBranchesList(String tokenKey){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetBranchesList";
        String OPERATION_NAME="GetBranchesList";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);

        //the first attribute

        request.addProperty("tokenKey",tokenKey);

        return connectToService();
    }
    public String GetCompanyInfo(String tokenKey){
         SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetCompanyInfo";
        String OPERATION_NAME="GetCompanyInfo";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);

        return connectToService();
    }
    public String RegisterUser(String tokenKey, String fullName, String email, String loginPassword, String phone1
            ,String phone2 , String address , String city , String gpsURL )
    {
         SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/RegisterUser";
         OPERATION_NAME="RegisterUser";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
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
       return connectToService();

   }
   public String GetInvoiceByDocNum(String  tokenKey, String docNum){

       SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetInvoiceByDocNum";
       OPERATION_NAME="GetInvoiceByDocNum";
        int DocNum=Integer.parseInt(docNum);
       request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
       //set attributes
       request.addProperty("tokenKey",tokenKey);

       PropertyInfo pi ;
       pi = new PropertyInfo();
       pi.setName("docNum");
       pi.setValue(DocNum);
       pi.setType(Integer.class);
       request.addProperty(pi);
       return connectToService();
   }
   public String GetProductsList(String tokenKey, String productGroupCode, String producOriginCode, String salePriceFrom, String salePriceTo){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetProductsList";
        OPERATION_NAME="GetProductsList";

       request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
       double priceFrom=100;
       double priceTo=10000;
        //set attributes
       request.addProperty("tokenKey",tokenKey);
       request.addProperty("productGroupCode","5");
       request.addProperty("producOriginCode","11");
       PropertyInfo pi ;
       pi = new PropertyInfo();
       pi.setName("salePriceFrom");
       pi.setValue(priceFrom);
       pi.setType(Double.class);
       request.addProperty(pi);
       PropertyInfo pi1 ;
       pi1 = new PropertyInfo();
       pi1.setName("salePriceTo");
       pi1.setValue(priceTo);
       pi1.setType(Double.class);
       request.addProperty(pi1);

       return connectToService();

   }
   public String GetInvoicesByPhone(String tokenKey, String phone){
       SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetInvoicesByPhone";
       OPERATION_NAME="GetInvoicesByPhone";

       request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);

       request.addProperty("tokenKey",tokenKey);
        request.addProperty("phone",phone);

       return connectToService();

   }
    private String connectToService(){

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.implicitTypes = true;//new add
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);
        String response =null;

        try {

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            httpTransport.debug=true;
           //  httpTransport.setXmlVersionTag("<?xmlx version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");

            httpTransport.call(SOAP_ACTION,envelope);
            //response=httpTransport.responseDump;
            //SoapPrimitive response1= (SoapPrimitive)envelope.getResponse();
            SoapObject resultsReq=(SoapObject)envelope.bodyIn;
            response=resultsReq.toString();
        } catch (XmlPullParserException | IOException e)
        {
            e.printStackTrace();
            response= "" + e;
        }
        System.out.println("response ********  "+response);
        return response;
    }
}
