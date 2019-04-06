package com.example.anand.magnum_chat_app.discussion_chat;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public  class generateContactList {
    private static String[][] arr=null;
    private static final  String CONTACTLIST="contactList";


    public static String[][] getList(){

        FirebaseDatabase.getInstance().getReference().child(CONTACTLIST).child(getID())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        HashMap<String,String> map = new HashMap<String,String>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                           String str = snapshot.getKey();
                            Log.i("debug", str);
                            if(str.compareTo(getID()) < 0){
                                map.put(getID()+"_"+str,getName(str));
                            }else{
                                map.put(str+"_"+getID(),getName(str));
                            }
                        }
                        ArrayList<String> list = new ArrayList<>(map.keySet());
                        arr=new String[2][list.size()];

                        for(int i=0;i<list.size();++i){
                            arr[0][i]=list.get(i);
                            arr[1][i]=map.get(arr[0][i]);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

        if(arr==null){
            arr= new String[2][0];
            return arr ;
        }else{
            return arr;
        }


    }

    public static  String getName(String id){

        //return the name of the person corresponding the given id
        return id;
    }

    private static String getID(){
        //dont call api here , this is getting called multiple times
        return "+919953168959";
    }
}
