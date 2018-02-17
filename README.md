# Concentration:

## Problem

The problem consists of matching two shapes. Once the shapes are matched, they shouldn't be able to be chosen again. Realistically,
the faces of the "cards" should be turned around to avoid confusing the user. The user has a score that needs to be kept.
With each new pair that is matched a scored should be incremented, the user should be able to see their score from the play window.
Once all cards are matched, a new game should be initiated, allowing the user to play again. A tally of the games played should also be 
displayed in the game window. Average number of turns taken to complete a game will also be displayed.

## Solution

The shapes are assigned to buttons. After the first button is selected, it is disabled to avoid causing confusion to the user. 
Once the second card is selected the value of the buttons are compared to see if they are equal. If they are, the second button 
will also be disabled and both cards will be flipped back around, but will remain unclickable. The option for a new game and exit
is given within a drop down menu. Displayed at the bottom of the play screen is a tally of number of turns, number of games, total
games played, and average moves used per game.
