# Gold Price Analysis Application

This Java application utilizes Java 17 and Gradle 8.5 to fetch gold prices data from an API, convert the prices to a desired currency, perform statistical analysis on the prices, and plot them on a time-series chart. Below is an explicit explanation of each file and its functionality:

## Files

### 1. `SWP1.java`
This is the main class of the application. It orchestrates the entire process by initializing the required services, fetching gold prices, converting them, and plotting the data. It contains the `main` method, serving as the entry point for the application.

### 2. `GoldConvertService.java`
This service class is responsible for converting gold prices to a desired currency. It receives a list of gold prices, fetches the exchange rate from a `CurrencyExchangeService`, and then performs the conversion. Additionally, it calculates statistics such as the maximum, minimum, and average gold prices.

### 3. `CurrencyExchangeService.java`
This service class retrieves the exchange rate for the currency used in converting gold prices. It fetches the exchange rate from an external API, specifically from the National Bank of Poland (NBP) API (`http://api.nbp.pl/api/exchangerates/rates/c/eur/`).

### 4. `GoldApiService.java`
This service class fetches gold prices data from an external API, specifically from the National Bank of Poland (NBP) API (`http://api.nbp.pl/api/cenyzlota`). It allows fetching gold prices for a specific date range by providing the start and end dates as parameters.

### 5. `GoldPlotService.java`
This service class generates a time-series chart of gold prices over time using the JFreeChart library. It receives a list of gold prices and plots them on a chart, which is then saved as an image file (`gold_prices_chart.jpg`).

### 6. `GoldPrice.java`
This class represents a single entry of gold price data. It contains attributes such as the date and price of gold. Objects of this class are used throughout the application to store and manipulate gold price data.

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
