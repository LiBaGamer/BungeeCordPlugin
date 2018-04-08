package de.libagamer.bungeecordplugin.main.util;


import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MojangAPI {

    public String getUuid(String name) {
        String url = "https://api.mojang.com/users/profiles/minecraft/"+name;
        try {
            @SuppressWarnings("deprecation")
            String UUIDJson = IOUtils.toString(new URL(url));
            if(UUIDJson.isEmpty()) return "invalid name";
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            String input = UUIDObject.get("id").toString();
            String output = parseUUID(input);
            return output;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return "error";
    }

    private String parseUUID(String input){

        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < 32; i++){

            if(i == 8 || i == 12 || i == 16 || i == 20){
                stringList.add("-");
                stringList.add(String.valueOf(input.charAt(i)));
            }
            else {
                stringList.add(String.valueOf(input.charAt(i)));
            }

        }

        String uuid = stringList.stream().collect(Collectors.joining(""));
        System.out.println(uuid);
        return uuid;

    }

}
