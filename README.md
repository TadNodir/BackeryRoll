# Backery Walz / Bäckerwalz

(The elemets only of these packets are allowed to be used in this project: java.lang, java.util, java.util.regex, java.util.function and
java.util.stream. System.exit() and Runtime.exit() are not allowed)

In this task the game Bäckerwalz is to be designed and implemented. In this
game, two to four players compete against each other. The player to be the first to win gold
of 100 or more wins the turn-based game.

The circular playing field - beginning with a starting field - is always determined before the game begins.
The fields of the playing field are arranged according to rules and can variously range from 4 to 25. This order and number can be different for each game.

The different playing fields are listed below:
- Starting field (S)
One to eight fields each - on which certain raw materials may be produced and sent to the
market can be sold:
- a mill (M) with raw material flour (flour),
- a chicken coop (H) with raw material eggs (egg),
- cow pasture (C) with raw milk (milk).

There is also a central market where the active player can buy raw materials
and sell. At the start of the game, the market has two raw materials of each type
equipped.


A2. Game process and player turn. (See 1. picture)

Each player automatically has 20 gold pieces at the start of the game. A player's gold supply becomes
represented by a natural 32-bit integer greater than or equal to zero.
The first player, referred to as P1 in the following, always starts each time the game starts.
If a player has completed his actions in one turn, it is the next player's turn.
After the last player has completed his turn, it is again the first player's turn.
This turn-based game principle is carried out until one of the players wins the game.

During the game, the first step is to roll the dice and move to a new field. over
the number of spaces to be drawn per turn determines the number of pips on the six-sided die.
There is a train obligation. Players are only allowed to move forward in one direction. On a playing field
(M, H, C or S) there can be more than two and a maximum of four players at the same time.

If the active player lands on one of the playing fields M, H or C, the active player can either (i) use a resource - depending on the playing field on which he is located
- manufacture and sell it on the market or (ii) buy a commodity from the market at the current price or (iii) prepare dishes.

The active player can only produce and sell one resource once in the entire turn. Making a
raw material and its sale on the market is an atomic action. Buying raw materials is allowed several times in one turn.
If the active player lands on the starting field (S), he receives 5 gold pieces.

After rolling the dice and advancing on the field, the active player can (even before the
selling or buying raw materials) as often as necessary prepare dishes, provided that he
has enough resources in his purchased supply. A dish can be made according to one of the recipes
getting produced. For the preparation of a dish are used from the supply of the active player
the raw materials are taken, he receives the respective number of gold pieces. The active player can prepare dishes as often as he likes during an entire turn, 
provided that there are enough raw materials in its supply.
A move is made by the active player with the "turn" command.


A3. Recipes

The recipes indicate which raw materials a player needs to prepare a dish. As well each player receives a profit for the production, 
which is indicated in the number of gold pieces. The raw materials used (flour), eggs (egg), milk (milk)
come exclusively from the player's stock. (See the 2. picture)(Translation from german: Gericht - Food, Mehl - flour, Eier - eggs, Milch - milk, Gewinn - profit).
Every player who has prepared all 7 dishes receives 25 gold once.


A4. Market

The market determines the prices of each of the three raw materials (flour), eggs (egg), milk (milk).
3. picture shows the initial occupancy of the market at the start of the game. The active player can only buy or sell the raw material on the corresponding playing field after rolling the die. I.e. Flour on the mill, eggs on the chicken coop, milk on the cow pasture. It can be 0 to 5
Units of every commodity exist in the market.

Selling: 

If the active player has produced a resource and wants to sell it on the market,
he puts it on the market on the next free space in the column belonging to the raw material (see
picture 3)(Translation from german: aktueller Preis - current price, Goldstück - golden coin). The raw materials are placed from the bottom to up. Used in the table above
Now a milk is produced on the field cow pasture and sold at the market, this is placed on
the 3rd row of the milk column. Selling a raw material brings in the active player
gold piece as a profit. The production of a raw material and its sale on the market represent one
atomic action.

Buying:

If the active player buys a raw material from the market, the price of the purchase of the raw material
determines the current price of the row of the top, associated raw material. Provided
the active player has enough gold pieces and the raw material exists on the market, he can
buy the raw material at the current price. The raw materials purchased are top-down
taken down from the market. Example: If a milk is bought in 3. picture, it is
the top milk in row 4 and therefore costs 4 gold pieces. Then the next one costs 5 gold pieces.
The active player can only buy resources, if he has enough money for that and there are enough resources and if he does not produce any resources in the same turn
and has sold any.


A5. Command line arguments

Your program expects command line arguments.
The first command line argument defines the number of players x={2,3,4}.
Then, beginning with the starting field S, the order of the playing fields for the mill
M, hen house H and cow pasture C set. All playing fields are marked with exactly one semicolon
separated. So, the fields for the game are arranged in a circle starting from Starting field.

Keep the following rules for the order of the playing fields:
• The first field must be the starting field S. There is only one starting field.
• There must also be at least one mill M, a hen house H and a cow pasture C.
• No two adjacent fields may be the same. The starting field S is skipped.
Example: Sequence M; M is prohibited, M; C; M is permitted.
• Four-field rule: Each field may not have more than 4 fields of a field of the same type
be distant. The starting field S is skipped.
Example: Sequence M; C; H; C; H; M is forbidden, M; C; H; C; M is allowed.

Examples of valid compositions of circular playing fields:
• S; M; C; H; C; M; C; H
• S; M; C; H
Examples of invalid combinations of circular playing fields:
• S; M; H; M; H (cow pasture C missing)
• S; M; C; H; H (two chicken coops H next to each other)
• S; H; M; C; H (two chicken coops H next to each other)
• S; M; C; H; C; H (four-field rule violated)
• S; H; M; C; H; C (four-field rule violated)

Example: java BakeryRoll 4 S;M;C;H;C;M;C;H
If an error occurs while processing the command line argument or if the input corresponds
not in the format specified here, a meaningful error message starting with
"Error, " is output and the program is terminated.

A6. Interactive user interface

After starting, your program takes over the console using the terminal. readLine () commands. After processing a command program waits
for further commands until the program is terminated at some point by entering the string "quit".

Make sure that executing the following commands does not violate the rules of the game
and issue a meaningful error message in these cases. Even if the user input does not correspond to the specified format, an error message is output.
After issuing an error message, the program should continue as expected and then open again
wait for the next input. Every error message must begin with "Error, " and must not have any line breaks. 

End of the game

The first player to generate 100 or more gold wins the
Game Bakery Walz. In this case, the output of the current command is, "Px wins"
with x={2,3,4} output on a separate line.

Commands:

The roll command

With this command the active player can transfer a number <number> within the framework of the rules,
which is interpreted as the result of a die roll.
In this case, <number> is a natural number from the closed interval [1,6].

Input: roll <number>

Output: The output of the command is the abbreviation of the playing field (S, M, H or C) if successful
which the player has now landed, followed by his current gold supply. Both will issue
separated by exactly one semicolon.

The harvest command

With the parameterless harvest command, the active player can produce a raw material and
sell on the market. It can only be the raw material <resource>, that corresponds to the to the playing field and on which the player is currently located after throwing the dice.

Input: harvest

Output: <resource>;<gold>  If successful, the sold raw material (flour,
egg or milk) and the player's current gold supply. Both editions will
separated by exactly one semicolon.

The buy command

With the buy command the active player can buy raw materials from the market, provided he has enough gold pieces available.

Input:  buy <resource>
Output:  <price>;<gold>   If successful, the current price of the raw material is output
as well as the current gold supply of the player after purchasing the associated resource.

The prepare command

With the prepare command the active player can prepare a dish <recipe> (yoghurt, meringue,
bread, bun, crepe, pudding or cake), provided there are enough raw materials available. His gold supply is increased with the respective profit.

Input: prepare <recipe>

Output: <gold>   If successful, the player's current gold supply is issued.
The raw materials used for this are taken from his raw material supply.

The can - prepare? Command

With the "can-prepare?" Command the active player can determine which dishes he is based on
can prepare on his raw material supply.

Input: can-prepare?

Output: If successful, all dishes (yoghurt, meringue, bread, bun,
crepe, pudding or cake) that the active player can prepare - sorted in ascending order
the possible profit - output line by line.

The show-market command

The parameterless show-market command lists the three raw materials.

Input: show-market

Output: <number>;<resource>   If successful, the raw materials are labeled line by line with the
existing number <number> as a natural number in the closed interval [0.5] and with the
Identifiers. All outputs in one line are separated by exactly one semicolon.

The show-player command

The show - player command shows off the supply of gold pieces and raw materials of a player Px
x={2,3,4}.

Input: show-player <Px>

Output: <gold>;<flour>;<egg>;<milk>   If successful, the number of
gold pieces, then the number of raw materials flour, eggs and milk of a player Px in a row
issued. All outputs in one line are separated by exactly one semicolon.

The turn command

The parameterless turn command completes the active player's turn and then changes
the active player. This command is only permitted after the currently active player has
completed the turn.

Input turn

Output: If successful, Px is output if player Px with x={2,3,4} is the new active
player. Example: P2 is issued if player 1 made his turn with the turn command
has completed. I.e. on the next turn, player 2 is the active player.

The quit command

The parameterless quit command makes it possible to completely terminate your program at any time. Note
using methods like System.exit() or runtime.exit() is not allowed.

Input: quit

Output: If successful, there is no issue. In the event of an error, a meaningful error message is displayed
starting with error, output.



The Picture4 is an example sequence of a game round with 3 players and one playing field
with the configuration S; M; C; H. There are three complete moves.


Thanks for your interest and reading this till the end! Good luck!
