# HelloHydration
This application is design to help users track their daily water intake. 
It takes into account the user's height and weight to come up with the perfect daily water goal!
It also takes into account the user's daily activity and adjusts the goal accordingly.
<br>
###The Team
* Nick Behrje
  * Senior in Computer Science with a Linguistics Minor
* Alex Berlinger
  * Senior in Computer Science & Psychology
* Alex Boukhvalova
   * Senior in Computer Science & Biology
* Calvin Lu
   * Senior in Computer Science & Economics
<br>

###Video Link
TBA

###Features
* Toolbar
  * allows for easy transitions between Views
  
  ![Toolbar](https://github.com/alexisfat/HelloHydration/blob/master/screenshots/Toolbar.png)
* Water droplet
  * Graphical view with touchable bars to see your weekly hydration/activity.
  * Realistic animation of the water droplet filling up as you reach your goal!
  
  ![Water Droplet](https://github.com/alexisfat/HelloHydration/blob/master/screenshots/Hydration.png)
* Exercise tracker
  * deciphers whether an action is walking, running, or biking.
  * provides a running timer for the user as they track exercise.
  * displays cumulative tracking time for each exercise through various rounds of tracking.
  
  ![Activity Levels](https://github.com/alexisfat/HelloHydration/blob/master/screenshots/Activity_levels.png)
* Settings
  * automatically calculates the projected goal based on your personal information.
  
  ![Settings](https://github.com/alexisfat/HelloHydration/blob/master/screenshots/Settings.png)

<br>

###How to Use
Hello Hydration can be run on Android Emulator or any Android device. Run the code from Android studio to install.

![Icon](https://github.com/alexisfat/HelloHydration/blob/master/app/src/main/res/mipmap-xxxhdpi/hh.png)

Patient can open app and see their daily hydration progress and goal based on the numerical amount printed on the screen (goal) and the amount of the water droplet filled up (progress to goal). User can access weekly progress, exercise tracking, and settings pages through the toolbar. 

The weekly progress page can be accessed with the icon that looks like a bar graph. This page is interactive and can scroll between multiple weeks of progress. To receive details on past exercise tracking information, user can tap on individual bars. 

To access the exercise tracking page, user can tap on running icon in the tool bar. On this page, the user can see the amount of time they have been running, walking, or biking. In order to start tracking exercise, user can press the play button. The tracker will automatically detect user exercise based on accelerometer and update the main timer values accordingly.

To adjust settings, user can tap on the settings icon on the right most side of the toolbar. All fields can be edited by tapping on them.

###References
* Android's Dev Pages: 
  * Sensor Methods - https://developer.android.com/reference/android/hardware/SensorEvent.html#values
* Animation:
  * Progress Bar Animation - http://stackoverflow.com/questions/8035682/animate-progressbar-update-in-android
* Calculating Appropriate Goal:
  * Formula - https://www.umsystem.edu/newscentral/totalrewards/2014/06/19/how-to-calculate-how-much-water-you-should-drink/
* Graph Github and Functions:  
  * PhilJay/MPAndroidChart - https://github.com/PhilJay/MPAndroidChart
* Iconography 
  * Native to Android Studio - Vector Assets
* Timer for Activity Tracker:
  * http://www.shawnbe.com/index.php/tutorial/tutorial-3-a-simple-stopwatch-lets-add-the-code/
* Water Level Sensor
  * Milone 8 inch https://www.adafruit.com/products/463
  * Feather Huzzah Wifi Module https://www.adafruit.com/product/2821
<br>
