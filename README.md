# "Slice of Life"

## A Stimulation Game

Here are some *questions* you can ask yourself:
- *Have you ever thought about having a perfect life?*
- *Do you want to experience the sweet and biter of life?*
- *Are you ready for a new chapter of life?*

If you answered **yes**!   
"This is **the game** for you."

This game will let you create a new life that you have always 
wanted! There are a few thing that 
**"you"** can do just like in real life, such as learning
a new skill and leveling it up, go on a date and improve your
relationship with your love interest, and of course get married! I know
this would be so much fun because it's just like
how we play the sims when we are little and even now!

**{A Game For Inspiration and Your Childhood Dream}**

##User Stories
- As a user, I want to be able to add a new member to the list of HouseHold member.
- As a user, I want to be able to view all the activities that I can do.
- As a user, I want to be able to select an activity to go to.
- As a user, I want to be able to level up all my skills and relationships.
- As a user, I want to be able to view all the skills I have and with their level.
- As a user, I want to be able to view all the relationships I have and with their level.
- As a user, I want to be able to switch to another member in the list of the Household member.
- As a user, I want to be able to delete a member in the list of Household member.
- As a user, I want to be able to choose to save my game whenever and get a reminder when select quit in the main menu.
- As a user, I want to be given the option to load my saved game from file when I start the game.
- As a user, I want to be able to print the current save file while in the game.



**Credits :**
- background.jpg from 'Wotakoi: Love Is Hard for Otaku' by Fujita, 
picture get from https://www.pinterest.ca/pin/228346643598361763/

**Phase 4: Task 2 :**
- Wed Mar 30 13:50:26 PDT 2022: 'UAnn' added to current Household.
- Wed Mar 30 13:50:28 PDT 2022: 'fitness' added to UAnn's skills.
- Wed Mar 30 13:50:31 PDT 2022: 'dancing' added to UAnn's skills.
- Wed Mar 30 13:50:33 PDT 2022: 'boyfriend' added to UAnn's relationships.
- Wed Mar 30 13:50:48 PDT 2022: 'MoMo' added to current Household.
- Wed Mar 30 13:50:58 PDT 2022: 'friendship' added to UAnn's relationships.
- Wed Mar 30 13:51:03 PDT 2022: 'UAnn' removed from current Household.
- Wed Mar 30 13:51:05 PDT 2022: 'painting' added to MoMo's skills.
- Wed Mar 30 13:51:08 PDT 2022: 'boyfriend' added to MoMo's relationships.

**Phase 4: Task 3 :**
- Creating a class called ListOfPersonalInformation that has a  list of PersonalInformation (skills/relationships),
and abstract all the methods that are related in the HouseholdMember class to the new class.
- In the SliceOfLifeGame and SliceOfLifeGui class, there are a lot of duplications and similarities 
between these classes. By adding an abstract class SliceOfLife that has all the similar method, and let both 
SliceOfLifeGame and SliceOfLifeGui extends it to decrease duplications.
- Since for now , all exceptions extends RuntimeException, so I would like to make them extends Exception and catch them
in the main. I would also like to add more Exceptions, such as NegativeAgeException with entering negative age for
Household Member.
