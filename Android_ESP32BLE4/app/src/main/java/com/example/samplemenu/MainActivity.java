package com.example.samplemenu;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hishri.fnarduino.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //sean_0227


    public String packageName0="de.kai_morich.serial_bluetooth_terminal";
    public String className0="de.kai_morich.serial_bluetooth_terminal.MainActivity";

    public String packageName1="de.kai_morich.serial_bluetooth_terminal";
    public String className1="de.kai_morich.serial_bluetooth_terminal.MainActivity";

    public String packageName3="com.teraunits.myapplication";
    public String className3="com.teraunits.myapplication.MainActivity";


    private Spinner types;
    private Button show;
    private ListView drinks;
    private MockupDA da;
    //sean
    Button blescan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        types = findViewById(R.id.drinkTypes);
        show = findViewById(R.id.show);
        drinks = findViewById(R.id.drinks);

        blescan=findViewById(R.id.buttonscan);


        da = new MockupDA();

        bindSpinner();

        List<Drink> allDrink;
        allDrink = da.getAllDrinks();
        showDrinksList(allDrink);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedType = types.getSelectedItem().toString();
                List<Drink> drinkList;

                if (selectedType.equals("All Devices")) {
                    drinkList = da.getAllDrinks();

                    Log.d("haha", "drinkList 1");

                } else {
                    drinkList = da.getDrinkByType(selectedType);

                    Log.d("haha", "else drinkList");
                }

                showDrinksList(drinkList);

            }
        });
        blescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openBTSCAN();

            }
        });






    }

    private void bindSpinner() {
        List<String> Dtypes = da.getDrinkTypes();
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Dtypes);
        types.setAdapter(spnAdapter);
    }

    private void showDrinksList(List<Drink> drinkList){
        List<String> displayList = new ArrayList<>();
        for (Drink drink : drinkList) {
            String displayString = drink.toString();
            displayList.add(displayString);
            Log.d("haha", "showDrinksList"+displayString);
            //openBTSCAN2(drinkList);

        }

        // Create an ArrayAdapter to display the list of drinks in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, displayList);
        drinks.setAdapter(adapter);

        drinks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = displayList.get(position);
                Toast.makeText(MainActivity.this, "클릭한 아이템: " + selectedItem, Toast.LENGTH_SHORT).show();
                openBTSCAN2(position);
            }
        });

    }


    private final androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.getData() != null) {
//                        Uri imageUri = data.getData();
//                        imageView.setImageURI(imageUri); // Example: Set image to an ImageView
                        Toast.makeText(getApplicationContext(), "connected", Toast.LENGTH_LONG).show();
                    }
                }
            });
    // Call this function to open the gallery
    private void openBTSCAN() {
        Intent intent = new Intent(this, MyActivity.class);
        ActivityResultLauncher.launch(intent);
        //imagePickerLauncher.launch(intent);
    }
    private void openBTSCAN2( int position) {

        // Toast.makeText(getApplicationContext(), "openBTSCAN2", Toast.LENGTH_LONG).show();
        // Intent intent = new Intent(this, MainActivity4.class);
        //de.kai_morich.simple_bluetooth_terminal
        // ActivityResultLauncher.launch(intent);
        //imagePickerLauncher.launch(intent);
      switch (position){
          case 0:
          {
              Intent intent = new Intent(this, MainActivity.class);
              intent.setClassName(packageName0, className0);
              intent.putExtra("challenge", "123456");
              ActivityResultLauncher.launch(intent);
          }
              break;
          case 1:
          {
              Intent intent = new Intent(this, MainActivity.class);
              intent.setClassName(packageName1, className1);
              intent.putExtra("challenge", "123456");
              ActivityResultLauncher.launch(intent);
              Toast.makeText(getApplicationContext(), "position 1", Toast.LENGTH_LONG).show();
          }
              break;
          case 2:
          {    Intent intent = new Intent(this, MyActivity.class);
              ActivityResultLauncher.launch(intent);
              Toast.makeText(getApplicationContext(), "position 2", Toast.LENGTH_LONG).show();
          }
              break;
          case 3:
          {
              Intent intent = new Intent(this, MainActivity.class);
              intent.setClassName(packageName3, className3);
              ActivityResultLauncher.launch(intent);
              Toast.makeText(getApplicationContext(), "position 3", Toast.LENGTH_LONG).show();
          }
              break;
          case 4:
          {
              Intent intent = new Intent(this, MainActivity7.class);
              ActivityResultLauncher.launch(intent);
              Toast.makeText(getApplicationContext(), "position 4", Toast.LENGTH_LONG).show();
          }
              break;
          case 5:
          {
              Intent intent = new Intent(this, MainActivity8.class);
              ActivityResultLauncher.launch(intent);
              Toast.makeText(getApplicationContext(), "position 5", Toast.LENGTH_LONG).show();
          }
          break;
          case 6:
          {
              Intent intent = new Intent(this, MainActivity2.class);
              ActivityResultLauncher.launch(intent);
              Toast.makeText(getApplicationContext(), "position 6", Toast.LENGTH_LONG).show();
          }
              break;
          default:
          {
              Intent intent = new Intent(this, MainActivity10.class);
              ActivityResultLauncher.launch(intent);
              Toast.makeText(getApplicationContext(), "position default", Toast.LENGTH_LONG).show();
          }
              break;
      }

//       if(position==0) {
//           Intent intent = new Intent(this, MyActivity.class);
//           intent.setClassName(packageName, className);
//           intent.putExtra("challenge", "123456");
//           ActivityResultLauncher.launch(intent);
//       }
//       else  if (position==1)
//        {
//            Toast.makeText(getApplicationContext(), "position 1", Toast.LENGTH_LONG).show();
//        }
//       else  if (position==2)
//       {
//           Toast.makeText(getApplicationContext(), "position 2", Toast.LENGTH_LONG).show();
//       }
//       else  if (position==3)
//       {
//           Toast.makeText(getApplicationContext(), "position 3", Toast.LENGTH_LONG).show();
//       }
//       else  if (position==4)
//       {
//           Toast.makeText(getApplicationContext(), "position 4", Toast.LENGTH_LONG).show();
//       }




    }




}