package com.example.moneyspendcalculator.data_manage;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataLoaderAndSaver<T> {
    public String jsonFileName;
    private Context context;
    private File pathFile;

    private Class<T> Class;
    public DataLoaderAndSaver(String jsonFileName,Class<T> Class, Context context){
        this.Class = Class;
        this.context = context;
        pathFile = context.getFilesDir();
        String[] parsed = jsonFileName.split(".");
        if(parsed.length > 1 && parsed[parsed.length - 1] != "txt")
        {
            jsonFileName += ".txt";
        }
        else {
            jsonFileName += ".txt";
        }
        this.jsonFileName = jsonFileName;
    }

    public ArrayList<T> LoadData(){
        Gson gson = new GsonBuilder().setLenient()
        .create();

        ArrayList<T> dat = new ArrayList<>();
        try {
            File ff = new File(pathFile, jsonFileName);
            FileReader fr = new FileReader(ff);

            BufferedReader br = new BufferedReader(fr);
            String data = "";
            String line = null;
            while((line = br.readLine()) != null)
            {
                if (line != null) {
                    dat.add((T) gson.fromJson(line, Class));
                }
            }

            br.close();
        } catch(IOException ie) {
            System.out.println("Data is not loaded");
            ie.printStackTrace();
            return null;
        }


        return dat;
    }

    public void SaveData(T[] data){
        Gson gson = new Gson();
        String json = "";
        for(int i = 0; i < data.length;i++){
            json += gson.toJson(data[i]);
            if(i < data.length - 1){
                json += "\n";
            }
        }

        File ff = new File(pathFile, jsonFileName);
        try (FileWriter fileWriter = new FileWriter(ff)) {
            fileWriter.write(json);
            System.out.println("String saved to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
