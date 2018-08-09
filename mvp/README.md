# mvp-app


This version of the app is called todo-mvp, and provides a foundation for other samples in this project. The sample aims to:

- Provide a basic Model-View-Presenter (MVP) architecture without using any architectural frameworks.
- Act as a reference point for comparing and contrasting the other samples in this project.


### What you need

Before exploring this sample, you might find it useful to familiarize yourself with the following topics:

-   The  [project README](https://github.com/dwarvesf/template-android-app/blob/master/README.md)
-   The  [MVP](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter)  architecture

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

-   A contract class which defines the connection between the view and the presenter.
-   An  [Activity](https://developer.android.com/reference/android/app/Activity.html)  which creates fragments.
-   A  [Fragment](https://developer.android.com/reference/android/app/Fragment.html)  which implements the view interface, presenters
-   A presenter which implements the presenter interface in the corresponding contract.

A presenter typically hosts business logic associated with a particular feature, and the corresponding view handles the Android UI work. The view contains almost no logic; it converts the presenter's commands to UI actions, and listens for user actions, which are then passed to the presenter.

### Implementing the app

Each version of the app implements the same features using a different approach to showcase and contrast a variety of architectural designs. For example, this version takes the following approaches to solving common implementation questions:

-   This sample uses  [product flavors](https://developer.android.com/studio/build/build-variants.html)  to replace modules at compile time, providing fake data for both manual and automated testing.
-   This version uses Rx to handle asynchronous.
-   The data is stored locally in a SQLite database, using  [Room](https://developer.android.com/topic/libraries/architecture/room.html).

Notice also in the following illustration that this version of the app uses fragments, and this is for two reasons:

-   The use of both  [activities](https://developer.android.com/guide/components/activities/index.html)  and  [fragments](https://developer.android.com/guide/components/fragments.html)  allows for a better separation of concerns which complements this implementation of MVP. In this version of the app, the Fragment is the overall controller which creates and connects views and presenters.

[![Illustration of the MVP architecture for this version of the app.](https://github.com/dwarvesf/template-android-app/blob/master/mvp/mvp.png)](https://github.com/dwarvesf/template-android-app/blob/master/mvp/mvp.png)

This version of the app includes a number of unit tests which cover presenters, repositories. The sample also includes UI tests, that rely on fake data, and are facilitated by dependency injection to provide fake modules. For more information on using dependency injection to facilitate testing, see  [Leveraging product flavors in Android Studio for hermetic testing](https://android-developers.googleblog.com/2015/12/leveraging-product-flavors-in-android.html).
  

