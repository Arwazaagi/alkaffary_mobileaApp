package com.example.azeaage.mobileapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by AZahrani on 3/26/2017.
 */

public class ExpandableListDataPump {
    public static HashMap<String,List<String>> getData()
    {
        HashMap<String,List<String>> expandableListDetail = new HashMap <String,List<String>>();
                List<String> Twitter_medai = new ArrayList<String>(); //** add string between <>
                Twitter_medai.add("Follow us on Alkaffary Twitter");
                Twitter_medai.add("Follow us on HighPoint Twitter");
                Twitter_medai.add("Follow us on ModernPlace Twitter");
                List<String>Instgram_medai = new ArrayList<String>();
                Instgram_medai.add("Follow us on Alkaffary instgram");
                Instgram_medai.add("Follow us on HighPoint instgram");
                Instgram_medai.add("Follow us on ModernPlace istgram");
                List<String>Facebook_medai = new ArrayList<String>();
                Facebook_medai.add("like us on Facebook");
                List<String>Snapchat_medai =new ArrayList<String>();
                Snapchat_medai.add("Follow us on snapchat");
                List<String> Whatsapp_medai =new ArrayList<String>();
                Whatsapp_medai.add("+966 543535777");
                expandableListDetail.put("Twitter",Twitter_medai);
                expandableListDetail.put("Instgram",Instgram_medai);
                expandableListDetail.put("Snapchat",Snapchat_medai);
                expandableListDetail.put("Facebook",Facebook_medai);
                expandableListDetail.put("Whatsapp",Whatsapp_medai);
       return expandableListDetail ;

    }
}
