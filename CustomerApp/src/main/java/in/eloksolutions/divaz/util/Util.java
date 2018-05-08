package in.eloksolutions.divaz.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;
import java.util.Random;

import in.eloksolutions.divaz.dtoclasses.CompanyDTO;

/**
 * Created by welcome on 8/19/2017.
 */

public class Util {

    public static void setPreferances(Context mcontext, CompanyDTO registerDto){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Config.APP_PREFERENCES, mcontext.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("firstName", registerDto.getFirstName());
        edit.putString("lastName", registerDto.getLastName());
        edit.putString("loc", registerDto.getEmail());
        edit.putString("lat", registerDto.getAddress_1()+"");
        edit.putString("long",registerDto.getLatitude()+"");
        edit.putString("userId", registerDto.getId());
        edit.commit();

        Config.setFirstName(registerDto.getFirstName());
        Config.setLastName(registerDto.getLastName());
        Config.setUserId(registerDto.getId());

        System.out.println("out opf the shared prefrence is" +registerDto.getId()+ registerDto.getLastName()+registerDto.getFirstName());
    }


    public  static int getRandomNumbers(){
        int min=100;
        int max=1000;
        return (new Random().nextInt(max-min+1))+min;
    }

    public static boolean isEmpty(String str){
        return str==null||str.trim().length()==0;
    }
    public static boolean isFull(File file){
        return file==null;
    }
}
