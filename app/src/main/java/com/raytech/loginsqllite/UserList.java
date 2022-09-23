package com.raytech.loginsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class UserList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        final EditText edtTxtUserList = (EditText) findViewById(R.id.edtTxtUserList);
        Database db = new Database(getApplicationContext());
        List<User> listUser= new ArrayList<User>();
        listUser= db.getirUserList();

        StringBuilder sb= new StringBuilder();


        for(User _user: listUser){
            String contents="";
            contents="ID: "+_user.getId()+"İsim: "+_user.getName()+"Kullanıcı Adı: "+_user.getUserName()+"Şifre: "+_user.getPassword()+"\n\n\n\n";
           sb.append(contents);
        }
        edtTxtUserList.setText(sb);
    }
}