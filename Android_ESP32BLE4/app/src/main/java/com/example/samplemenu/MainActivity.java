package com.example.samplemenu;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public String packageName="de.kai_morich.simple_bluetooth_terminal";
    public String className="de.kai_morich.simple_bluetooth_terminal.MainActivity";

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

                if (selectedType.equals("All scenario")) {
                    drinkList = da.getAllDrinks();

                    Log.d("haha", "drinkList");

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

                openBTSCAN2();

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

        }

        // Create an ArrayAdapter to display the list of drinks in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, displayList);
        drinks.setAdapter(adapter);




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
    private void openBTSCAN2() {

        // Toast.makeText(getApplicationContext(), "openBTSCAN2", Toast.LENGTH_LONG).show();
        // Intent intent = new Intent(this, MainActivity4.class);
        //de.kai_morich.simple_bluetooth_terminal
        // ActivityResultLauncher.launch(intent);
        //imagePickerLauncher.launch(intent);

        Intent intent = new Intent(this, MyActivity.class);
        intent.setClassName(packageName, className);
        intent.putExtra("challenge", "123456");
        ActivityResultLauncher.launch(intent);

    }




}