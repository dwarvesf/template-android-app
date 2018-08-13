# mvvm clean architecture - app


The sample demonstrates an alternative implementation of the mvp-app sample using the [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)(MVVM) architecture and Clean Architecture


### What you need

Before exploring this sample, you might find it useful to familiarize yourself with the following topics:

-   The  [project README](https://github.com/dwarvesf/template-android-app/blob/master/README.md)
-   The  [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)  architecture

The todo-mvp sample uses the following dependencies:

-   [RxJava](https://github.com/ReactiveX/RxJava)  - Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.
-   [Retrofit](http://square.github.io/retrofit/)  - A type-safe HTTP client for Android and Java.
-   [Glide](https://bumptech.github.io/glide/)  - Glide is a fast and efficient image loading library for Android focused on smooth scrolling.
-   [Joda Time](http://www.joda.org/joda-time/)  - Joda-Time provides a quality replacement for the Java date and time classes.
-   [Anko Commons](https://github.com/Kotlin/anko/wiki/Anko-Commons-%E2%80%93-Intents)  - a lightweight library full of helpers for intents, dialogs, logging and so on.
-   [Google Maps](https://developers.google.com/maps/documentation/android-sdk/intro)  -With the Maps SDK for Android, you can add maps based on Google Maps data to your application.
-  [Room](https://developer.android.com/topic/libraries/architecture/room)  - The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
-  [Lottie](https://github.com/airbnb/lottie-android)  - Lottie is a mobile library for Android and iOS that parses Adobe After Effects animations exported as json with Bodymovin and renders them natively on mobile!
### Designing the app

All versions of the template android app include the same common features in a simple to-do type app. The app consists of five UI screens:

-   Splash - Used to show welcome
-   Login - Used to login to app
-   Map - Used to show map
-   List - Used to show list data
-   Detail - Displays detail of items

In this version of the app, as well as other versions based on it, each screen is implemented using the following classes and interfaces:


-   An  [Activity](https://developer.android.com/reference/android/app/Activity.html)  which creates fragments.
-   A  [Fragment](https://developer.android.com/reference/android/app/Fragment.html)  which implements viewmodel
-   A Viewmodel which implements the input and output interface

Inputs is a set of actions and events that have impacts on viewModel such as the tap action on a button, or the viewDidLoad event. 

Outputs represents changes that views should reflect. Since ouputs may change over time, it’s best to return an Observable for each ouput. Behaviors defined in inputs should not be expressed as Variable because we don’t need the inputs to be obseravable.

And in this project we use Clean Architecture
[![](https://github.com/dwarvesf/template-android-app/blob/master/mvvm/ModulesDetails.png)](https://github.com/dwarvesf/template-android-app/blob/master/mvvm/ModulesDetails.png)



### Implementing the app

Each version of the app implements the same features using a different approach to showcase and contrast a variety of architectural designs. For example, this version takes the following approaches to solving common implementation questions:

-   This sample uses  [product flavors](https://developer.android.com/studio/build/build-variants.html)  to replace modules at compile time, providing fake data for both manual and automated testing.
-   This version uses Rx to handle asynchronous.

This version of the app includes a number of unit tests which cover presenters, repositories. The sample also includes UI tests, that rely on fake data, and are facilitated by dependency injection to provide fake modules. For more information on using dependency injection to facilitate testing, see  [Leveraging product flavors in Android Studio for hermetic testing](https://android-developers.googleblog.com/2015/12/leveraging-product-flavors-in-android.html).
  

