package com.example.anand.magnum_chat_app.discussion_chat;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashSet;

public  class generateContactList {
    public static String[] arr=null;
private static final  String CONTACTLIST="contactList";
    public static String[] getList(){


        FirebaseDatabase.getInstance().getReference().child(CONTACTLIST).child(getID())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        HashSet<String> set = new HashSet<>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                           String str = snapshot.getKey();
                            Log.i("debug", str);
                            if(str.compareTo(getID()) < 0){
                                set.add(getID()+"_"+str);
                            }else{
                                set.add(str+"_"+getID());
                            }
                            if(set.contains(str)){
                                Log.i("contains", str);

                            }
                        }
                       duplicateData(set);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

        //if size ==0 , display no chats
//        ArrayList<String> list = new ArrayList<>(set.);
//        String[] arr =new String[set.size()];
//        int i=0;
//        for(String str:set){
//            arr[i]=str;
//            ++i;
//        }
        if(arr==null){
            arr= new String[0];
            return arr ;
        }else{
            return arr;
        }


    }

    private static void duplicateData(HashSet<String> set){

        arr =new String[set.size()];
        int i=0;
        for(String str:set){
            arr[i]=str;
            ++i;
        }
    }


    private static String getID(){
        //dont call api here , this is getting called multiple times
        return "+919953168959";
    }
}
