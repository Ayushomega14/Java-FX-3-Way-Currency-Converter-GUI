# Three-Way Currency Converter (JavaFX)

## Table of Contents
- [Overview](#overview)
- [Project Structure](#project-structure)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [API Integration](#api-integration)
- [Screenshots](#screenshots)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---

## Overview
This project is a **Three-Way Currency Converter** built using JavaFX. It allows users to seamlessly convert values between **fiat currency, cryptocurrency, and ETFs (Exchange-Traded Funds)**. The application features an intuitive graphical user interface (GUI) to select the desired conversion type and input amounts.

Additionally, it integrates an **API** to fetch real-time exchange rates from the internet, ensuring accurate and up-to-date conversions.

---

## Project Structure
```plaintext
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── converter/      # Core conversion logic
│   │   │   ├── gui/            # JavaFX UI components
│   │   │   ├── api/            # API integration for fetching exchange rates
│   │   ├── resources/
│   │   │   ├── fxml/           # FXML files for UI layout
│   │   │   ├── images/         # UI icons and assets
│   ├── test/                   # Unit tests (if applicable)
├── pom.xml (for Maven dependencies, if used)
├── README.md                   # Project documentation
└── LICENSE                      # License details
```

---

## Features

- **Three-Way Conversion**: Convert between currency, cryptocurrency, and ETFs.
- **Real-Time Exchange Rates**: Fetches live data from an API for accurate conversions.
- **Intuitive GUI**: Built with JavaFX, allowing easy selection of conversion types.
- **Multi-Currency Support**: Supports multiple international fiat currencies.
- **Offline Mode**: Retains the last fetched exchange rates for offline usage.

---

## Technologies Used

- **JavaFX**: For creating the graphical user interface.
- **Java**: Core logic implementation.
- **FXML**: For defining UI structure.
- **HTTP Client**: To fetch exchange rates from an online API.
- **Maven/Gradle** (Optional): For dependency management.

---

## Installation

1. **Clone the Repository**:
```sh
 git clone https://github.com/yourusername/Three-Way-Currency-Converter.git
 cd Three-Way-Currency-Converter
```

2. **Install Dependencies** (if using Maven or Gradle):
   - Maven:
     ```sh
     mvn clean install
     ```
   - Gradle:
     ```sh
     gradle build
     ```

3. **Run the Application**:
```sh
 java -jar target/CurrencyConverter.jar  # If using a JAR file
```

Or, if running directly from an IDE (like IntelliJ or Eclipse), run the `Main.java` file.

---

## Usage

1. Open the application.
2. Select the type of conversion (Currency, Crypto, or ETFs).
3. Choose the input and output currencies from the dropdown.
4. Enter the amount to be converted.
5. Click the **Convert** button to get real-time results.

---

## API Integration
The application fetches real-time exchange rates using an external financial API.

- **API Used**: (e.g., Open Exchange Rates, CoinGecko, Alpha Vantage)
- **Response Format**: JSON
- **Example API Call**:
  ```sh
  https://api.exchangerate-api.com/v4/latest/USD
  ```

The application automatically updates exchange rates every few minutes to ensure accuracy.

---

## Screenshots
*(Add relevant screenshots of the application UI here)*

---

## Future Enhancements
- **Historical Data Analysis**: View past exchange rates.
- **Multi-Language Support**: Add support for multiple languages.
- **Dark Mode UI**: Option for a dark theme interface.
- **Mobile Version**: Adapt the application for mobile devices.

---

## Contributing
Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new feature branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Submit a pull request.

---

## License
This project is licensed under the MIT License. See the `LICENSE` file for more details.

---

## Contact
For questions or suggestions, reach out to:

- **Mohit Kumar & Ayush Raj**
- **Email**: mk1695@srmist.edu.in & ar5787@srmist.edu.in
- **GitHub**: [mohit0825](https://github.com/mohit0825) & [Ayushomega14](https://github.com/Ayushomega14)

Feel free to improve and expand the project! 🚀

