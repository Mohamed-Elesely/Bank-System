# Bank System Documentation

## Overview
The Bank System is a simple Android application that allows users to create an account, log in, manage their bank account balance, withdraw funds, and deposit funds. The application is designed to provide basic banking functionalities for individual users.

## Features
The Bank System application includes the following features:

1. Sign Up: Allows users to create a new account by providing their name, email, and password. The registration process validates user inputs, checks for duplicate accounts, and stores the user data securely in the database.

2. Sign In: Allows users to log in to their account using their email and password. The authentication process verifies the user's credentials against the stored data in the database.

3. Account Management: Provides a user interface to view the account balance and perform transactions such as withdrawal and deposit. The user can enter the withdrawal or deposit amount, and the application updates the account balance accordingly.

4. Session Management: Maintains a session for each logged-in user to ensure their authentication status across different activities. The SessionManager class handles the creation, retrieval, and removal of session information.

5. Database Storage: Utilizes a SQLite database to store user account data, including card number, name, email, password, PIN key, and balance. The DatabaseHelper class manages the creation, insertion, retrieval, and updating of user data in the database.

## Architecture and Design

The Bank System application follows the Model-View-Controller (MVC) architectural pattern. Here's an overview of the key components:

1. Models:
   - UserModel: Represents a user account and contains attributes such as card number, name, email, password, PIN key, and balance. The model class provides methods to access and modify the user data.

2. Views:
   - SignUp Activity: Displays a sign-up form where users can enter their details to create a new account.
   - SignIn Activity: Displays a sign-in form where users can enter their credentials to log in to their account.
   - AccountManagement Activity: Provides a user interface to view the account balance and perform transactions (withdrawal and deposit) on the account.

3. Controllers:
   - DatabaseHelper: Manages the SQLite database operations, including creating the database, inserting user data, retrieving user data, and updating the account balance.
   - SessionManager: Handles the management of user sessions, including creating and storing session information, retrieving session data, and removing session information when the user logs out.

## Usage

1. Sign Up:
   - Launch the application and navigate to the Sign Up screen.
   - Enter your name, email, and password in the provided fields.
   - Click the "Sign Up" button to create a new account.
   - If the registration is successful, you will see a success message and be redirected to the User Data screen.

2. Sign In:
   - Launch the application and navigate to the Sign In screen.
   - Enter your registered email and password in the provided fields.
   - Click the "Sign In" button to log in to your account.
   - If the authentication is successful, you will be redirected to the Account Management screen.

3. Account Management:
   - After signing in, you will see the Account Management screen.
   - The current account balance will be displayed.
   - To perform a withdrawal, enter the withdrawal amount in the provided field and click the "Withdraw" button.
   - To make a deposit, enter the deposit amount in the provided field and click the "Deposit" button.
   - The account balance will be updated accordingly, and a success message will be displayed.

4. Log Out:
   - To log out of the application, click the "Log Out" button on the

 Account Management screen.
   - You will be redirected to the Sign In screen, and your session will be terminated.

## Future Enhancements

The Bank System application can be further enhanced with the following features:

1. Transaction History: Implement a feature to display the transaction history of the user, including details such as transaction type, amount, and date/time.

2. Account Settings: Add functionality for users to update their account information, such as name and password.

3. User Authentication Enhancements: Implement more secure authentication mechanisms, such as password hashing and encryption, to enhance user data security.

4. UI Improvements: Enhance the user interface with better layouts, styles, and visual feedback for a more intuitive user experience.

5. Error Handling: Implement better error handling and validation to provide informative error messages to the users in case of invalid inputs or failed operations.

## Conclusion

The Bank System project provides a basic banking application that allows users to create accounts, log in, and manage their account balances. It demonstrates fundamental concepts of Android application development, including user authentication, database storage, session management, and basic UI interactions. With further enhancements, it can be expanded into a more robust banking application with additional features and improved security.