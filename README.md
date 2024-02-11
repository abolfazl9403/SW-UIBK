# Gold Price Analysis Application

This Java application utilizes Java 17 and Gradle 8.5 to fetch gold prices data from an API, convert the prices to a desired currency, perform statistical analysis on the prices, and plot them on a time-series chart. Here's a detailed explanation of each file and its functionality:

## Files

### 1. `SWP1.java`
This is the main class of the application. It initializes the required services, fetches gold prices, converts them, and plots the data.

### 2. `GoldConvertService.java`
This service class is responsible for converting gold prices to a desired currency. It fetches the exchange rate from a currency exchange service and performs the conversion.

### 3. `CurrencyExchangeService.java`
This service class retrieves the exchange rate for the currency used in converting gold prices. It fetches the exchange rate from an external API.

### 4. `GoldApiService.java`
This service class fetches gold prices data from an external API based on specified start and end dates.

### 5. `GoldPlotService.java`
This service class generates a time-series chart of gold prices over time using the JFreeChart library.

### 6. `GoldPrice.java`
This class represents a single entry of gold price data, including the date and price.

## Running the Application

To run the application, follow these steps:

1. Clone the repository from GitHub.
2. Ensure you have Java 17 and Gradle 8.5 installed on your system.
3. Navigate to the root directory of the project in the terminal.
4. Run the command `gradle run`, which will execute the `SWP1.java` file.
5. View the output in the console, which includes statistical analysis of the gold prices and the path to the generated chart image.

## Dependencies

- This project utilizes Java 17 features.
- It relies on Gradle 8.5 for dependency management and building the project.
- The JFreeChart library is used for plotting the gold prices.
- The `org.json` library is used for handling JSON responses from APIs.

## Note

Make sure your system has internet connectivity as the application fetches data from external APIs.

Feel free to explore the code further and customize it according to your requirements! If you encounter any issues or have questions, don't hesitate to reach out.
