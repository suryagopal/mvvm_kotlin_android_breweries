# Breweries
An Android application which will get the list of breweries https://api.openbrewerydb.org

The app is built with Kotlin and mvvm architecture. It also features dependancy injection via koin and networking with retrofit. Room is used for the database.

## Getting Started
1. Download and install android studio

2. Clone the repo:
- `https://github.com/suryagopal/mvvm_kotlin_android_breweries.git`

3. Snyc gradle 

4. Run the app on an emulator or device

# Architecture
- The app was built with an mvvm architecture. 
- This keeps UI code simple and free of app logic in order to make it easier to manage and test. 
- An RX mindset was used to keep the app scalable going forward. 
- Depenandancy injection was done via koin so that components can be tested indepenantly without strong references. 
- For networking I used retrofit, a strong yet lightwieght client. 
- To manage databetween the screens a room database was used. This keeps the data synced between screens.
