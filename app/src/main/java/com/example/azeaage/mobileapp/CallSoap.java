package com.example.azeaage.mobileapp;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by AZeaage on 4/5/2017.
 */

class CallSoap {


  /*  public String GetCompanyInfo(String tokenKey){
         SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetCompanyInfo";
        String OPERATION_NAME="GetCompanyInfo";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        PropertyInfo PI = new PropertyInfo();
        //the first attribute
        PI.setName("tokenKey");
        PI.setValue(tokenKey);
        PI.setType(String.class);
        request.addProperty(PI);

        return connectToService();
    }*/
    public String RegisterUser(String tokenKey, String fullName, String email, String loginPassword, String phone1
            ,String phone2 , String address , String city , String gpsURL )
    {
      String WSDL_TARGET_NAMESPACE="http://schemas.microsoft.com/2003/10/Serialization/";
         SoapObject request;

        String  SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/RegisterUser";
        String OPERATION_NAME="RegisterUser";
        String SOAP_ADDRESS = "http://hr.alkaffary.com:664/AlKaffaryMobileService.svc";
        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        PropertyInfo PI = new PropertyInfo();
        //set attributes
        PI.setName("tokenKey");
        PI.setValue(tokenKey);
        PI.setType(String.class);
        request.addProperty(PI);
      request.addProperty("fullName",fullName);
      request.addProperty("email",email);
      request.addProperty("loginPassword",loginPassword);
      request.addProperty("phone1",phone1);
      request.addProperty("phone2",phone2);
      request.addProperty("address",address);
      request.addProperty("city",city);
      request.addProperty("gpsURL","567899");


     /*   PropertyInfo nameProp=new PropertyInfo();
        nameProp.setName("fullName");
        nameProp.setValue(fullName);
        nameProp.setType(String.class);
        request.addProperty(nameProp);

        PropertyInfo emailProp=new PropertyInfo();
        emailProp.setName("email");
        emailProp.setValue(email);
        emailProp.setType(String.class);
        request.addProperty(emailProp);

        PropertyInfo passProp=new PropertyInfo();
        passProp.setName("loginPassword");
        passProp.setValue(loginPassword);
        passProp.setType(String.class);
        request.addProperty(passProp);

        PropertyInfo phone1Prop=new PropertyInfo();
        phone1Prop.setName("phone1");
        phone1Prop.setValue(phone1);
        phone1Prop.setType(String.class);
        request.addProperty(phone1Prop);

       /* PropertyInfo phone2Prop=new PropertyInfo();
        phone2Prop.setName("phone2");
        phone2Prop.setValue(phone2);
        phone2Prop.setType(String.class);
        request.addProperty(phone2Prop);

        PropertyInfo addressProp=new PropertyInfo();
        addressProp.setName("address");
        addressProp.setValue(address);
        addressProp.setType(String.class);
        request.addProperty(addressProp);

        PropertyInfo cityProp=new PropertyInfo();
        cityProp.setName("city");
        cityProp.setValue(city);
        cityProp.setType(String.class);
        request.addProperty(cityProp);

        PropertyInfo gpsProp=new PropertyInfo();
        gpsProp.setName("gpsURL");
        gpsProp.setValue(gpsURL);
        gpsProp.setType(String.class);
        request.addProperty(gpsProp);*/

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
            SoapPrimitive response1= (SoapPrimitive)envelope.getResponse();
            response=response1.toString();
        } catch (XmlPullParserException | IOException e)
        {
            e.printStackTrace();
            response= "" + e;
        }
        return response;
    }
    private String connectToService(){
        return null;
    }
}
