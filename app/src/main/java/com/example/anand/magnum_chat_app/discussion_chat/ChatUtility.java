package com.example.anand.magnum_chat_app.discussion_chat;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;

public  class ChatUtility {
    private static String[][] arr=null;
    private static final  String CONTACTLIST="contactList";


    public static String[][] getList(){

        FirebaseDatabase.getInstance().getReference().child(CONTACTLIST).child(getID())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        HashMap<String,String> map = new HashMap<>();

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

    private static  String getName(String id){

        //return the name of the person corresponding the given id
        return id;
    }

    private static String getID(){
        //dont call api here , this is getting called multiple times
        return "id1";
    }

    public static void initiateNewChat(String id1,String id2){
        // Initialize Firebase components
        FirebaseDatabase   mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = mFirebaseDatabase.getReference().child(CONTACTLIST);
        usersRef.child(id1).child(id2).setValue(new contactListModel("yes"));
        usersRef.child(id2).child(id1).setValue(new contactListModel("yes"));


    }
}
