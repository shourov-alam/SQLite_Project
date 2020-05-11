package com.shourov.sqlite_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,age,address;
    Button addStudent,showStudents;
    DatabaseSource databaseSource;
  //  Model model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseSource=new DatabaseSource(this);


        name=findViewById(R.id.nameTV);
        age=findViewById(R.id.ageTV);
        address=findViewById(R.id.addressTV);
        addStudent=findViewById(R.id.button);
        showStudents=findViewById(R.id.button1);


        //this below part is done in the Student List using a dialog
      /*  model = (Model) getIntent().getSerializableExtra("STUDENT");

        if(model != null){

            addStudent.setText("Update Student");

            name.setText(model.getName());
            age.setText(model.getAge()+"");            // OR  age.setText(String.valueOf(model.getAge()));
            address.setText(model.getAddress());

        }     */


        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //this below part is done in the Student List using a dialog
             /*   if (model != null) {

                    int id = model.getId();
                    Model model = new Model(name.getText().toString(), address.getText().toString(), Integer.valueOf(age.getText().toString()), id);

                    Boolean updatedStatus = databaseSource.updateStudent(model);

                    if (updatedStatus) {

                        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();

                    } else {

                        Toast.makeText(getApplicationContext(), "Not Updated", Toast.LENGTH_LONG).show();
                    }


                } else {  */


                Model model = new Model(name.getText().toString(), Integer.valueOf(age.getText().toString()), address.getText().toString());

                Boolean status = databaseSource.addStudent(model);

                if (status) {

                    Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_LONG).show();
                    name.setText("");
                    age.setText("");            // OR  age.setText(String.valueOf(model.getAge()));
                    address.setText("");

                } else {

                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }


            }

            // }
        });


        showStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StudentList.class);
                startActivity(intent);



            }
        });






    }
}
