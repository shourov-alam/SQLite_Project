package com.shourov.sqlite_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.DefaultTaskExecutor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentList extends AppCompatActivity {

    ListView listView;
    ArrayList<Model> arrayList;

    public static final int DELETE=0;
    public static final int UPDATE=1;

    DatabaseSource databaseSource = new DatabaseSource(this);
    StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        listView=findViewById(R.id.listViewID);




        arrayList=databaseSource.getAllStudent();

        studentAdapter = new StudentAdapter(this,arrayList);
        listView.setAdapter(studentAdapter);
        registerForContextMenu(listView);


       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Model model = arrayList.get(position);

                Intent intent = new Intent(StudentList.this,MainActivity.class);
                intent.putExtra("STUDENT",model);
                startActivity(intent);


            }
        });   */

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

      /*  MenuInflater menuInflater = getMenuInflater();
                     menuInflater.inflate(R.menu.menu,menu);
                     menu.setHeaderTitle("Delete Student");  */

        // menu.setHeaderTitle("Delete/Update Here");

        menu.add(0,DELETE,Menu.NONE,"Delete");
        menu.add(0,UPDATE,Menu.NONE,"Update");


    }



    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //  return super.onContextItemSelected(item);


        //DatabaseSource databaseSource = new DatabaseSource(this);
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();



        if(item.getItemId() == DELETE){

            boolean status =databaseSource .deleteStudent(arrayList.get(adapterContextMenuInfo.position));
            // arrayList=databaseSource.getAllStudent();

            // studentAdapter = new StudentAdapter(this,arrayList);
            listView.setAdapter(studentAdapter);
            registerForContextMenu(listView);

            if (status) {

                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();


            } else {

                Toast.makeText(getApplicationContext(), "Not Deleted", Toast.LENGTH_LONG).show();
            }

        }

        else  {

            final Model model = arrayList.get(adapterContextMenuInfo.position);


          /*  Intent intent = new Intent(StudentList.this,MainActivity.class);
            intent.putExtra("STUDENT",model);
            startActivity(intent);  */
            LayoutInflater layoutInflater =LayoutInflater.from(getApplicationContext());

            final View view = layoutInflater.inflate(R.layout.update_row, null);
            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Update Here");
            // alertDialog.setIcon("Icon id here");
            alertDialog.setCancelable(false);
            //  Constant.alertDialog.setMessage("Your Message Here");


            final EditText name = (EditText) view.findViewById(R.id.etName);
            final EditText age = (EditText) view.findViewById(R.id.etAge);
            final EditText address = (EditText) view.findViewById(R.id.etAddress);

            if(model != null){

                //   addStudent.setText("Update Student");

                name.setText(model.getName());
                age.setText(model.getAge()+"");            // OR  age.setText(String.valueOf(model.getAge()));
                address.setText(model.getAddress());

            }

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (model != null) {

                        int id = model.getId();
                        Model model = new Model(name.getText().toString(), address.getText().toString(), Integer.valueOf(age.getText().toString()), id);

                        Boolean updatedStatus = databaseSource.updateStudent(model);

                        if (updatedStatus) {

                            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                            // arrayList=databaseSource.getAllStudent();

                            //studentAdapter = new StudentAdapter(getApplicationContext(),arrayList);
                            listView.setAdapter(studentAdapter);
                            registerForContextMenu(listView);


                        } else {

                            Toast.makeText(getApplicationContext(), "Not Updated", Toast.LENGTH_LONG).show();
                        }

                    }

                }
            });


            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });


            alertDialog.setView(view);
            alertDialog.show();







            // ....................................

        }

        return super.onContextItemSelected(item);
    }
}
