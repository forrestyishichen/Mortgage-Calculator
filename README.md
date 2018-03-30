# Mortagage_Calculator


### Fetures

UI: Main Views
Calculation View:  allows user to provide property and loan data, do the calculation, and optionally save it.
Map View: shows all saved mortgage calculations on the Map. 
The navigation between the two views must be implemented through
 (1) Navigation Drawer, or 
 (2) Swipe Views with Tabs.
The default view, when the app starts for the first time, must be the Calculation View.
Mortgage monthly payment calculation
Input:
Property Info
Property Type: House, Townhouse, or Condo  
Street Address 
City
State (Provide a list of States for user to select)
Zipcode
Loan Info
Property price
Down payment
Annual Percentage Rate (APR)
Terms(number of years to pay, either 15 or 30 years)
The loan amount will be the property price less the down payment. The property info is optional unless the user wants to save the calculation.
Output:
Monthly payment
Formula:
http://www.wikihow.com/Calculate-Mortgage-Payments

Start a new mortgage calculation:
A New button must be provided for the user to start a new mortgage calculation. The current input, if there is any, will be cleared so that it is a fresh start for a new mortgage.

Save a mortgage:
User must have an option to save a mortgage calculation. The app must validate the address, and persist all the input and the output from feature 1. If the address is invalid, the user cannot save the calculation. 
Saved data must be persisted in Shared Preferences, Internal Storage, or SQLite Databases.

Browse mortgage:
App must render all saved mortgage calculations as markers through the Google Maps Android API.
User must be able to navigate through the map and browse mortgage markers. 
A dialog with the following information must show up when user tap on a specific marker. While the dialog must not cover the whole map, it contains the following info:
Property Type: House / Apartment
Street Address
City
Loan amount
APR
Monthly payment
A Delete button allow user to remove this mortgage calculation, it must be removed from both Map and the local database.
An Edit button to bring the user to the Calculation view to make changes. 
Device & screen size
We are going to check the assignments on a Nexus 6 emulator. View layout optimization for tablets and rotation is not required, but the content needs to be clearly viewable in any case and you app should not crash. 
Error handling
Your app must have proper error handling for missing required fields or invalid data type.
Example: APR is mandatory for calculation, and Address is mandatory for showing up on the Map.



