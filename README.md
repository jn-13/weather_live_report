#WeatherLiveReport

##Overview
WeatherLiveReport is an Android application that displays the current weather information of the user's location. It fetches data from the OpenWeatherMap API and provides a user-friendly interface with offline availability of the last seen weather information. The app is built using MVVM architecture, Retrofit for network operations, and Kotlin coroutines for asynchronous tasks.

##Features
Real-time Weather Data: Fetches the current weather for the user's location.
Offline Mode: Displays the last seen weather information when offline.
Creative UI: An aesthetically pleasing and intuitive user interface using Jetpack Compose/XML.
MVVM Architecture: Ensures separation of concerns and testability.
Secure API Key Handling: The API key is referenced in a single location and can be easily removed before sharing the code publicly.

##Prerequisites
Android Studio: Version 4.1 or higher.
Kotlin: 1.5.21 or higher.
Gradle: 6.5 or higher.
OpenWeatherMap API Key: You will need an API key to fetch weather data. Sign up here to get your free API key.

##Project Setup
1. Clone the Repository
   Clone the project from the repository to your local machine.

```git clone https://github.com/yourusername/WeatherLiveReport.git```
```cd WeatherLiveReport```

2. Add the OpenWeatherMap API Key
   To run the project, you need to insert your OpenWeatherMap API key.
   Open the gradle.properties file located in the root directory of your project.
   Add the following line, replacing your_api_key_here with your actual API key:

```OPENWEATHERMAP_API_KEY=your_api_key_here```

3. Sync the Project with Gradle Files
   Once the API key is added, sync the project to download all necessary dependencies.
   Open Android Studio.
   Select File > Sync Project with Gradle Files.

4. Build the Project
   Now, you can build the project:
   Select Build > Rebuild Project from the top menu in Android Studio.
   Ensure that the build completes without errors.

5. Run the Application
   To run the application on an emulator or physical device:
   Connect your device or start an Android emulator.
   Select Run > Run 'app' or press Shift + F10.
   The application will be installed and launched on your device.

##Architecture
The app follows the Model-View-ViewModel (MVVM) architecture:

Model: Handles the data layer, including API responses.
View: The UI layer, designed using XML or Jetpack Compose.
ViewModel: Acts as a bridge between the Model and View, handling UI-related data and logic.

##Libraries and Tools Used
Retrofit: For network operations.
Gson: For JSON serialization/deserialization.
Kotlin Coroutines: For asynchronous programming.
Glide: For image loading and caching.
Jetpack Compose/XML: For building the user interface.
Lifecycle Components: For managing UI-related data in a lifecycle-conscious way.


##Customization

You can customize the app by:

Changing the Location: Modify the location query in WeatherViewModel to fetch weather data for a different location.
Updating the UI: Use Jetpack Compose or XML to update the look and feel of the home screen.
Adding New Features: Implement additional weather features, like forecasts or weather alerts.
API Key Handling
Before pushing your code to a public repository (e.g., GitHub), remove your API key from the gradle.properties file to avoid exposing it publicly. You can add a placeholder or leave it empty, and include instructions in your README for others to insert their own API key.

```OPENWEATHERMAP_API_KEY=""```

##Troubleshooting
If you encounter issues during setup or development:

Ensure that your API key is correct and has the necessary permissions.
Make sure your Android Studio and dependencies are up to date.
Check the Gradle build output for any errors or warnings.
For further assistance, feel free to open an issue or reach out.

