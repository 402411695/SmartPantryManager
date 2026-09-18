# Smart Pantry Manager

## Project Description

Smart Pantry Manager is an Android application developed in Java to help users manage the ingredients available in their pantry and discover recipes based on those ingredients.

The application provides a simple interface for adding, viewing, editing and deleting pantry ingredients.  It also provides a recipe collection and a Suggested Recipes feature that compares the ingredients and quantities required by a
recipe against the ingredients currently stored in the pantry.

The application is designed to make pantry management easier while helping users identify recipes that can be prepared using ingredients they already have available.

## Key Features
 - Add pantry ingredients
 - View pantry ingredients
 - Edit pantry ingredients
 - Delete pantry ingredients
 - Store ingredient quantities and units
 - Store ingredient expiry dates where applicable
 - View available recipes
 - View detailed recipe information
 - Display recipe ingredients and preparation instructions
 - Suggested Recipes based on pantry contents
 - Strict recipe ingredient and quantity matching
 - Feedback when no recipes match the available pantry ingredients
 - Settings/Profile screen
 - Expiry reminder preferance
 - Persistent local data storage using SQLite

## Application Screens

The application includes the following main screens:

1. **My Pantry** - Displays ingredients currently stored in the pantry.
2. **Add/Edit Ingredient** - Allows pantry ingredients to be added and existing ingredients to be edited.
3. **Recipe List** - Displays the ingredients and preparation instructions for a selected recipe.
4. **Recipe Details** - Displays the ingredients and preparation instructions for a selected recipe.
5. **Suggested Recipes** - Displays recipes that can be prepared using the available pantry ingredients and required quantities.
6. **Settings/Profile** - Provides application information and an expiry reminder preference.

## Recipe Matching

The Suggested Recipes feature uses the ingredients stored in the pantry to determine which recipes can be prepared.

A recipe is considered a match only when:

- Every required ingredient is available in the pantry.
- The available quantity is greater than or equal to the quantity required by the recipe.
- Ingredient names are normalised to improve matching, including basic singular and plural handling.

Partial matches are not displayed as suggested recipes.

If no recipes satisfy the matching requirements, the application provides feedback indicating that no matching recipes were found.

## Database

### SQLite

The application uses **SQLite** for local data persistence.

SQLite was selected because the application is designed to manage structured local data such as pantry ingredients, recipes and recipe ingredients. It provides persistent storage directly within the Android application without requiring a separate database server or an internet connection.

The database contains data for:

- Pantry ingredients
- Recipes
- Recipe ingredients

SQLite also supports the CRUD operations required by the application.

## Technologies Used

- Java
- Android Studio
- Android SDK
- SQLite
- Android RecyclerView
- Git and GitHub
- XML layouts

## Project Structure

The application uses separate classes for the main application functions, including:

- `MainActivity`
- `AddEditIngredientActivity`
- `SuggestedRecipesActivity`
- `RecipeListActivity`
- `RecipeDetailsActivity`
- `SettingsActivity`
- `PantryDAO`
- `RecipeDAO`
- `RecipeIngredientDAO`
- `DatabaseHelper`
- `PantryItem`
- `Recipe`
- `RecipeIngredient`
- `PantryAdapter`
- `RecipeAdapter`

The DAO classes provide access to the SQLite database, while the Activity classes provide the different application screens and user interactions.

## Setup and Running the Application

### Requirements

- Android Studio
- Android SDK
- Java
- Android emulator or compatible Android device

### Steps

1. Clone or download the Smart Pantry Manager repository from GitHub.
2. Open the project in Android Studio.
3. Allow Android Studio to complete Gradle synchronisation.
4. Connect an Android device or start an Android emulator.
5. Select the Smart Pantry Manager application configuration.
6. Click **Run** in Android Studio.
7. The application will install and launch on the selected Android device or emulator.

## Using the Application

### Managing Pantry Ingredients

From the **My Pantry** screen:

1. Select **Add Ingredient** to add a new pantry item.
2. Enter the ingredient information.
3. Save the ingredient.
4. Use **Edit** to modify an existing pantry item.
5. Use **Delete** to remove an ingredient.

### Finding Suggested Recipes

1. Add ingredients to the pantry.
2. Select **Suggested Recipes**.
3. The application compares the pantry ingredients with the required ingredients for each recipe.
4. Recipes that satisfy all required ingredients and quantities are displayed.
5. If no recipe satisfies the requirements, a message is displayed.

### Viewing Recipes

Select **View Recipes** to browse the available recipes.

Selecting a recipe opens the **Recipe Details** screen, where the complete ingredient list and preparation instructions are displayed.

### Settings

The **Settings / Profile** screen provides application information and includes an **Enable expiry reminders** preference.

The selected preference is saved locally and remains available when the Settings screen is reopened.

## Version Control

The project is maintained using Git and GitHub.

Development was completed through multiple meaningful commits covering database development, recipe functionality, recipe matching, user interface improvements, recipe details and navigation enhancements.

## Author

Angela Dias 
Mobile Application Development 700
Richfield Graduate Institute of Technology
