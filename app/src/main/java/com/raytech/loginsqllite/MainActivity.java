package com.raytech.loginsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText txtName = (EditText) findViewById(R.id.edtTxtName);
        final EditText txtUserName = (EditText) findViewById(R.id.edtTxtUserName);
        final EditText txtPassword = (EditText) findViewById(R.id.edtTxtPassword);


        Button btnAprvd = (Button) findViewById(R.id.btnApproved);
        Button btnList = (Button) findViewById(R.id.btnList);

        btnAprvd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                String user = txtUserName.getText().toString();
                String pw = txtPassword.getText().toString();

                if (name.isEmpty() || user.isEmpty() || pw.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Boş Değer Gönderemezsiniz Kontrol Ediniz.", Toast.LENGTH_LONG).show();
                    return;
                }
                User users= new User(name,user,pw);

                Database db = new Database(getApplicationContext());
                long id = db.addUser(users);

                if(id>0)
                {
                    Toast.makeText(getApplicationContext(),"Kayıt Eklendi ID Numaranız ="+id,Toast.LENGTH_LONG).show();
                    //kayıt başarılıysa alanların temizlenmesi için
                    txtName.setText("");
                    txtUserName.setText("");
                    txtPassword.setText("");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Kayıt Başarısız=",Toast.LENGTH_LONG).show();

                }
            }
        });
btnList.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(),UserList.class);
        startActivity(intent);
    }
});

    }
}