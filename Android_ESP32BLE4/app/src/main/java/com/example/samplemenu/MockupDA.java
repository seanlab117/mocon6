package com.example.samplemenu;

import java.util.ArrayList;
import java.util.List;

public class MockupDA {

    List<Drink> drinks;

    public MockupDA(){
        drinks = new ArrayList<>();
        drinks.add(new Drink("scenario 1", 12, "Moter"));
        drinks.add(new Drink("scenario 2", 12, "Moter"));
        drinks.add(new Drink("scenario 3", 12, "Moter"));
        drinks.add(new Drink("scenario 4", 3.00, "RGB"));
        drinks.add(new Drink("scenario 5", 2.00, "RGB"));
        drinks.add(new Drink("scenario 6", 1.75, "RGB"));
        drinks.add(new Drink("scenario 7", 2.75, "Display"));
        drinks.add(new Drink("scenario 8", 3.50, "Display"));
        drinks.add(new Drink("scenario 9", 2.25, "Display"));
//        drinks.add(new Drink("Mango Juice", 2.50, "fruit juice"));
//        drinks.add(new Drink("Milk", 1.00, "dairy drink"));
//        drinks.add(new Drink("Yogurt Drink (Ayran)", 2.00, "dairy drink"));
//        drinks.add(new Drink("Lassi", 2.50, "dairy drink"));
//        drinks.add(new Drink("Coconut Water", 3.00, "cold drink"));
//        drinks.add(new Drink("Mint Lemonade", 2.75, "mocktail"));
//        drinks.add(new Drink("Virgin Mojito", 3.00, "mocktail"));
//        drinks.add(new Drink("Pineapple Juice", 2.50, "fruit juice"));
//        drinks.add(new Drink("Banana Milkshake", 3.00, "dairy drink"));
//        drinks.add(new Drink("Energy Drink (Red Bull)", 2.75, "energy drink"));
//        drinks.add(new Drink("Ginger Lemonade", 2.50, "mocktail"));
    }

    public List<Drink> getAllDrinks(){
        return drinks;
    }

    public List<String> getDrinkTypes(){
        List<String> types = new ArrayList<>();
        types.add("All Scenario");
        for (Drink drink : drinks) {
            if (!types.contains(drink.getType())){
                types.add(drink.getType());
            }
        }
        return  types;
    }

    public List<Drink> getDrinkByType(String type){
        List<Drink> drinkList = new ArrayList<>();
        for (Drink drink : drinks) {
            if (drink.getType().equals(type)) {
                drinkList.add(drink);
            }
        }
        return drinkList;
    }

}
