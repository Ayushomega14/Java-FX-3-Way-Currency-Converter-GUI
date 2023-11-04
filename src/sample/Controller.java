package sample;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    // currencyOne, currencyTwo - holds the currency tickers (from and to)
    // apiKey - holds the API key to use the API
    private String currencyOne, currencyTwo, apiKey;

    private ArrayList<String> currencyList;

    @FXML
    private ImageView logoImageView;

    @FXML
    private TextField enterAmountField;

    @FXML
    private ComboBox<String> currencyOneBox, currencyTwoBox;

    @FXML
    private Label resultLabel;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/currencyconvertorjdbc";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mohitisop";
    private Connection connection;

    public void initialize() {
        // Retrieve API key
        getApiKey();

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }

        try {
            // Retrieve and store the list of currencies
            currencyList = loadCurrencyList();
            // Store the list in the combo boxes
            ObservableList<String> options = FXCollections.observableArrayList(currencyList);
            currencyOneBox.setItems(options);
            currencyTwoBox.setItems(options);

        } catch (IOException e) {
            System.out.println("Error: Failed to load currency list " + e);
        }
    }

    public void setCurrencyOne() {
        currencyOne = currencyOneBox.getValue();
    }

    public void setCurrencyTwo() {
        currencyTwo = currencyTwoBox.getValue();
    }

    private void getApiKey() {
        BufferedReader reader = null;
        try {
            // Load the API key from a resource file
            reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/apikey.txt")));
            // Store API key
            apiKey = reader.readLine();

        } catch (IOException e) {
            System.out.println("Error: " + e);
        } finally {
            // Close the buffered reader object to free up resources
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }
    }

    private ArrayList<String> loadCurrencyList() throws IOException {
        // Retrieve currency list through API call
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url("https://api.apilayer.com/currency_data/list")
                .addHeader("apikey", apiKey)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(response.body().charStream(), JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        ArrayList<String> currencyList = new ArrayList<>();
        for (String currency : jsonObject.getAsJsonObject("currencies").keySet()) {
            currencyList.add(currency);
        }
        return currencyList;
    }

    public void convertCurrency() throws IOException {
        if (enterAmountField.getText() == null || enterAmountField.getText().isEmpty()) return;
        if (currencyOne == null || currencyTwo == null) return;

        float conversionRate = getConversionRate();

        // Calculate conversion
        float conversionResult = Float.parseFloat(enterAmountField.getText()) * conversionRate;

        // Display result
        resultLabel.setText(conversionResult + " " + currencyTwo);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM currency");
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the query result here
            while (resultSet.next()) {
                // Retrieve data from the result set
                String columnValue = resultSet.getString("currencycol");
                // Process the data
            }

            // Close the result set and statement
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }

    public void closeConnection() {
        // Close the database connection when done
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            System.err.println("Error closing the database connection: " + e.getMessage());
        }
    }

    private float getConversionRate() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url("https://api.apilayer.com/currency_data/live?source=" + currencyOne + "&currencies=" + currencyTwo)
                .addHeader("apikey", apiKey)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(response.body().charStream(), JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        // Return the conversion rate
        String key = currencyOne + currencyTwo;
        return Float.parseFloat(jsonObject.getAsJsonObject("quotes").get(key).getAsString());
    }
}






















