# Weather Forecast
 Simple App for fetching weather data from Open weather APIs.


## App Features
1. User can tap search 3 to 7 cities at a time.
2. User can show his current city forecast for 5 Days.
3. The app can show min and max tempreture, wind speed and weather description.

## App architecture
Based on mvvm architecture and repository pattern.

### The app includes the following main components:
 
* A web API service.
* A repository that works with the API service to provide a unified data interface.
* A utils class to handle user input.
* ViewModels that provide data specific to the UI using RxJava and LiveData.
* The UI, which shows a visual representation of the data in the ViewModel.

### App Packages:
* **data** - contains:
  * **repo** -  app repository classes for handling data.
  * **remote** - contains classes needed for making API calls to OpenWeather server using Retrofit.
* **di** - contains dependency injection classes, using Dagger2.
* **ui** - contains classes needed to display Activity and Fragment.
* **utils** - contains app constants and Utils classes.


### App Specs
* Minimum SDK 16.
* Kotlin.
* MVVM Architecture.
* Android Architecture Components (LiveData, Lifecycle, ViewModel, ConstraintLayout, Navigation component)
* Dagger 2 for dependency injection.
* Retrofit 2 for API integration.
* RxJava for making API calls.
* Gson for serialisation.
* Junit 4 for testing.

### Notes 
* Unit tests are generated for SearchInputUtils.
* For generating covarage reports launch gradle window from android studio's side bar and run the following command:
   gradle :app:createDebugCoverageReport
 
