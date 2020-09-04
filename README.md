### MAD 2 Example
This example is not using view binding to demonstrate what steps need
to be taken if not using it and to help understand code. Example
does not use view binding but using *synthetics* and findViewById.
*Note: synthetics are no longer a recommended practice(they have issues) but given for historic perspective*  
see this discussion on [Stackoverflow](https://stackoverflow.com/questions/58351239/viewbinding-vs-kotlin-android-extensions-with-synthetic-views)


<b>Also some standard code has been kotlinfied just to show what is  
possible with Kotlin</b>

#### Using mutableListOf in place of arrayListOf
This is to communicate the intent of the list, but currently an
ArrayList is used bij de Kotlin libraries

#### Using by lazy to initialize the list
The list gets initialized on first use. In this example this does not
make much of a difference since the list created early in onCreate

#### replaced regular if statement with some kotlin library/extension functions
[MainActivity.kt](https://github.com/remideboerhva/MadLevel2Example/blob/ea5e9ccb9c1fe335ee88372a04bf18cb41209beb/app/src/main/java/com/remideboer/madlevel2example/MainActivity.kt#L33)
