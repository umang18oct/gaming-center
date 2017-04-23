# gaming-center
## MODULES
1. AUTHENTICATION MODULE
2. WEBSITE MODULE
3. REGISTRATION MODULE
4. ADMIN MODULE
5. LEADERBOARD MODULE
6. BATTLE TANKS GAME
7. KBC GAME
8. BRICK BREAKER GAME
### 1. AUTHENTICATION MODULE
It is used to validate the user and manage the tokens bought by the user. If they are older than 1 day, the tokens are reduced to zero. These tokens allow users to play the games in our fun World  i.e. The KBC GAME,  Battle Tanks and the Brick Breaker.
### 2. WEBSITE MODULE
This module is based on servlet and jsp and helps in publicising of the funWorld GAME Center. It displays all the games and allows any user to book for the games and helps to buy tokens. Provides a feature of scubscribing to our funWorld.
### 3. REGISTRATION MODULE
The registration module contains fiunctions to register users and the data is stored on a data base running on a postgresql server on the same nertwork with a fixed hostname. The fields in the registration are name , username , password, and tokens. These tokens are saved according to the money paid by the user online or on spot. This module is used both in the website for online registration and can also be used in the desktop applications for offline registrations and token transfer.
### 4. ADMIN MODULE
The admin module lists the user details and contains all essential features used by the admin pages. This module is only used by the admin app which is a JSP application and runs locally by the admin. This application is not visible to all the users. The functions of this method require special privileges. Some basic admin functionalities implemented are : 
View subscriptions
View Users
Modify tokens
Transfer tokens
Add/delete users
### 5. LEADERBOARD MODULE
Leaderboard module lists the leaderboard from all the games on the website . This does not require any privileges to view. Usage is very limited. It hanles the leaderboard tables. Deletes users periodically. This module has very limited functionality. But since this feature cannot be a part of a game module and also it shows data on the public website, it is a seperate module.
### 6. BATTLE TANKS GAME
This game has three modules - 
  - User controls
  - Environment simulations
  - Multiplayer synchronization
#### User Controls Module: This module is responsible to controlling a single robot using the keyboard arrow keys and space key to shoot.
#### Environment Module: This module is responsible for painting the environment this basically takes the current state of the game and paints a picture of it on the JPanel.
#### Multiplayer Synchronization Module: This module is responsible for reflecting the changes in other player robots position in the current environment. Whenever a player dies or anny specific event like that takes place the player who is the main cause of the event broadcasts that event to all the players so that they can paint that on their panels.
### 7. KBC Module:
This module is subdivided into different  modules and they are:
 - Main Frame (Swing) module.
 - File I/O module.
 - DBconnection module.
#### Main Frame Module: This module creates the complete UI of the application. It provides action Listeners for the dynamic change of content.
#### File I/O Module: This module manages the Input from a file where all the questions, answers, options, price of each question are stored in a particular format in .txt file.
#### DBconnection Module: This module is used to make the connection to the database and to insert,update and query data about the winnings of each user and later integrates with website module to display the content on KBC leaderboard.
### 8. Brick Breaker Module:
This module is subdivided into different  modules and they are:
 - Main Applet module.
 - Component module.
 - DBconnection module.
#### Main Applet Module: This module extends the applet class and all the component of components module are used and drawn here and the DBconnection is done on a separate thread.
#### Component Module: This module contains the components like bar ,ball and BrickMapper. Bar provides the playing slab and ball contains the specification and necessary functions for drawing ball. The BrickMapper is used to draw the bricks and remove them when needed.
#### DBconnection Module: This module is used to make the connection to the database and to insert,update and query data about the user.
