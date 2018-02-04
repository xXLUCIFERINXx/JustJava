package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int numberOfCoffees = 0;
    private boolean hasWhippedCream, hasChocoChip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * this is the intent to open the Email App.
     *
     * @param compose is the body of the email
     */
    public void composeEmail(String compose) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "justjava@coffee.com", null));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
        intent.putExtra(Intent.EXTRA_TEXT, compose);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * on checking the check box this happens.
     */
    public void onCheck(View v) {
        CheckBox whippedCream = findViewById(R.id.check);
        CheckBox chocoChip = findViewById(R.id.check2);
        hasWhippedCream = whippedCream.isChecked();
        hasChocoChip = chocoChip.isChecked();
    }

    /**
     * This method is used for increment in number of coffee.
     */
    public void increment(View view) {
        numberOfCoffees = numberOfCoffees + 1;
        display(numberOfCoffees);
    }

    /**
     * This method is used for decrement in number of coffee.
     */
    public void decrement(View view) {
        numberOfCoffees = numberOfCoffees - 1;
        if (numberOfCoffees < 0) {
            Toast.makeText(this, "NOT A VALID INPUT", Toast.LENGTH_SHORT).show();
            numberOfCoffees = 0;
        }
        display(numberOfCoffees);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int num) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(num));
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        calculatePrice(numberOfCoffees);
    }

    /**
     * this method calculates price.
     */
    public void calculatePrice(int num) {
        int price = 10;
        if (hasChocoChip) {
            price = price + 1;
        }
        if (hasWhippedCream) {
            price = price + 2;
        }
        int priceOfCoffee = num * price;
        createOrderSummary(priceOfCoffee, hasWhippedCream, hasChocoChip);
    }

    /**
     * This method is to make order summary
     */
    private void createOrderSummary(int number, boolean hasWhipCream, boolean hasChip) {
        EditText name = findViewById(R.id.nameOfCustomer);
        String message;
        message = name.getText().toString();
        displayMessage("Name : " + message + "\n\nQuantity : " + numberOfCoffees + "\n\nToppings : " + "\nWhipped cream : " + hasWhipCream + "\nChocolate chip : " + hasChip + "\n\nprice : $" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        Toast.makeText(this, "Thank you!... visit again!", Toast.LENGTH_SHORT).show();
        composeEmail(message);

    }
}