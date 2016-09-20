# README #

## Simple World Simulation Program version. 1.0 24/05/2014 ##

### Author Details: ###
Name: Andrew Dallow 

### Instructions for use ###

When the program is first started you will four panels with blank (white) list areas.
Pressing the 'Demo World' button at the bottom right of the widow will create a 
pre-configured demo world, while the 'New World' button will generate a new empty 
world.

Places Pane (upper left pane):
-------------------------------
The Places pane, located in the upper left corner of the window displays a list of
selectable Places in the world. Room and Forest are the only two types of place
available in this world.

Create a new Room:
-------------------
Look at the Room pane below the Places list and enter the
label, level, and description of the Room in the appropriate text fields. Then 
press the 'Edit/Add Room' button. The room will not show up in the Places list. 

Create a new Forest:
--------------------
Look at the Forest pane below the Places list and enter
the Forest name, type (e.g. alpine) and climate (warm, cold, etc.) of the Forest
in the appropriate text fields. Then click the 'Edit/Add Forest' button. The 
forest will now show up in the places pane. 

Edit Dimensions of Place:
-------------------------
First select the place you would like to  edit, then look at the "Placed Size' 
pane below the Places list and enter the width and depth in metres in the 
appropriate text fields. Then click the 'Set Size' button below it. The size for 
the place is now set.

Edit details of Places:
------------------------
The details of all places can be edited by selecting the place to edit, then
the Places details will be display in the text boxes relevant to the type of
Place you have selected. Once the you have edited the relevant field click the
'Add/Edit' button for the type of place you are editing. This will update the
details in the list above it. 

-----------------------------------------
Places Contents Pane (Bottom left pane):
-----------------------------------------
This pane is used to display a list of Things in the selected Place and add new 
things to the world in the selected Place.

Create a new Thing:
-------------------
Look at the Thing pane below the Place Contents list and 
fill in the text fields for Name and description. Select the Place you would like
to add the Thing to and then click the 'Add Thing' button. This will add the new
Thing to the world and selected Place's contents, thus it will now show up in 
that Place's contents list.

Create new Food:
-----------------
Look at the Food pane below the Place Contents list and fill in the text fields
for name, food type (e.g. fruit) and description. You can also check the 'Cooked'
check box if the food item is cooked. Then select the place for the Food to be
added to and click the 'Add Food' button. This will add the new Food item to the 
world and contents of the selected Place, thus it will now show up in 
that Place's contents list. 

Create a new Computer:
-----------------------
Look at the Computer pane below the Place Contents list and fill in the text fields
for Computer ID and description, where Computer ID needs to be an integer number.
Then select the place for the Computer to be added to and click the 'Add Computer' button. 
This will add the new Computer item to the world and contents of the selected Place, 
thus it will now show up in that Place's contents list. The 'Control Robot' button
below the 'Add Computer' button is used to set a computer to control a robot. This is
done by first selecting a Robot then selecting a place which contains a computer. Then
select the computer in the Place's contents and click the 'Control Robot' button, the 
selected computer is now able to control the robot, however, a person also needs to
be logged in (see Action buttons below).

Create a Plant:
---------------
Look at the Plant pane below the Place Contents list and fill in the text fields
for name, Type (e.g. Tree), description and quantity. 
Then select the place for the Plant to be added to and click the 'Add Plant' button. 
This will add the new Plant item to the world and contents of the selected Place, 
thus it will now show up in that Place's contents list.

--------------------------------
Actors Pane (upper right pane):
--------------------------------
The Actors pane, located in the upper right corner of the window displays a list 
of selectable Actors in the world. Person, Robot and Animal are the only types of
Actors available in the world. Actor details cannot be edited.

Create a Person:
----------------
To create a new Person, look at the Person pane and fill in the text field for
name gender and DOB (Date of Birth). Please make sure date of birth is entered 
in the dd-mm-yyyy format. Then click the 'Add Person' button and the Person will
be added to the world and show up in the Actors list. 

Create a Robot:
---------------
To create a new Robot, look at the Robot pane and fill in the text fields for Robot ID,
which must be an integer number, and the purpose of the robot. Then select the Place 
the robot is to start in and click the 'Add Robot' Button. The robot will be added to
the world and show up in the Actors pane.

Create an Animal:
-----------------
To create a new Animal, look at the Animal pane and fill in the text fields for name,
Type (e.g. mammal), Gender (e.g. Male) and number of legs (e.g. 4). Then click the 
'Add Animal' button to add the Animal to the world which will now show up in the Actors 
list.

--------------------------------------------
Actors History Pane (Bottom Right pane one):
--------------------------------------------
The history pane is a list of the past Places that the selected person has been to. 

----------------------------------------------
Actors Inventory Pane (Bottom Right pane two):
----------------------------------------------
The inventory pane shows a list of Things that the selected actor is currently holding.
The inventory can be use to drop the selected Thing from the actors inventory or take a
selected item from the Actors current location (see Drop and Take in Action buttons).

---------------------------------------
Action Buttons (bottom left of window):
---------------------------------------
These buttons are use to control Places, Thing and Actors in the World.

'Go To' Button:
---------------
The 'Go To' Button is used to move an actor from one Place to another. This is done by first
selecting an actor then the place you want to move them to. Then click the 'Go To' Button
and the Actors location will change to the selected Place. 
Restrictions: Animals can only move between forests
			  Robots can only move when controlled by a computer with a person logged on.

'Take' Button:
--------------
The 'Take' button is used to move a thing from a Place's contents to the inventory of an
actor. This is done by selecting an actor and also selecting a Thing in the contents of
the actor's current location. Then clicking the 'Take' button will remove the Thing from
the Place contents and add it to the actor's inventory.
Restrictions: Robots can only 'Take' when controlled by a computer with a person logged on.

'Drop' Button:
--------------
The 'Drop' button is used to move a thing from an actor's inventory to the contents of the
Place the actor is at. This is done by selecting an actor and also selecting a Thing in the 
actor's inventory. Then clicking the 'Drop' button will remove the Thing from
the actor's inventory and add it to the contents of the actor's current location.
Restrictions: Robots can only 'Drop' when controlled by a computer with a person logged on.

'Log On/Off' Button:
--------------------
The 'Log On/Off' button is used to log a person onto a computer in the actors current location.
This is done by first selecting a person, then select a computer in the contents of the
person's current Place. Then click the 'Log On/Off' button and the person will be logged of if
no one is already logged on. To log the person of select the computer again and click the 
'Log On/Off' button.


'Eat Food' Button:
------------------
The 'Eat Food' button is used for a person or animal to consume a Food item, removing it
from the world. For a person, this is done by selecting a Person and then selecting a Food
item in the actor's inventory. Then click the 'Eat Food' button to consume the Food.
For Animals, select the Animal and then select the Food item in the contents of the 
Animal's current location. Then click the 'Eat Food' button. In both case the Food item
will be remove from the world.

---------------------------------------
Other Buttons (Bottom Right of window):
---------------------------------------

'Remove' button:
----------------
This button is used to remove the selected Place, Actor or Thing from the world.

'Clear' button:
---------------
This button clear the selection of objects in all lists.

'Quit' button:
--------------
End the program and closes the windows. Warning: Not data is saved in this Program.

----------------------
World Listener Window:
----------------------
The World Listener Window provide text update on any changes in the current world.

---------------------------------------------------------------------------------------------