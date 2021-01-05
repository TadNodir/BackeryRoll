package edu.kit.informatik;

import edu.kit.informatik.elements.Fields;
import edu.kit.informatik.elements.Market;
import edu.kit.informatik.elements.MoneyMargin;
import edu.kit.informatik.elements.Players;
import edu.kit.informatik.elements.field.Board;
import edu.kit.informatik.exceptions.SemanticException;
import edu.kit.informatik.exceptions.SyntaxException;

/**
 * This class represents functional part of the game, which runs the program
 *
 * @author Nodirjon Tadjiev
 * @version 1.0
 */
public class Main {
    // at the start the player has not rolled yet
    private static boolean rolled = false;
    /**
     * Roll command to be given on command line as an argument
     */
    private static final String ROLL = "roll";
    /**
     * Harvest command to be given on command line as an argument
     */
    private static final String HARVEST = "harvest";
    /**
     * Buy command to be given on command line as an argument
     */
    private static final String BUY = "buy";
    /**
     * Prepare command to be given on command line as an argument
     */
    private static final String PREPARE = "prepare";
    /**
     * Can-prepare? command to be given on command line as an argument
     */
    private static final String CAN_PREPARE = "can-prepare?";
    /**
     * Show-market command to be given on command line as an argument
     */
    private static final String SHOW_MARKET = "show-market";
    /**
     * Show-player command to be given on command line as an argument
     */
    private static final String SHOW_PLAYER = "show-player";
    /**
     * Turn command to be given on command line as an argument
     */
    private static final String TURN = "turn";
    /**
     * Quit command to be given on command line as an argument
     */
    private static final String QUIT = "quit";
    // integer to count start fields
    private static int j = 0;

    /**
     * Runs all commands and reads from the command line
     *
     * @param args array of command line arguments
     */
    public static void main(String[] args) {
        try {
            if (args.length != 2)
                throw new SemanticException("incorrect amount of command line arguments");
        } catch (SemanticException e) {
            Terminal.printError(e.getMessage());
        }
        Board board = new Board(arrangePlayers(args[0], args[1]), new Market());
        while (true) {
            String input = Terminal.readLine();
            String[] separate = input.split(" ");
            try {
                switch (separate[0]) {
                    case ROLL:
                        rolled = true;
                        provideRightAmountOfArguments(separate.length, 2);
                        int steps = Integer.parseInt(separate[1]);
                        board.roll(steps);
                        break;
                    case HARVEST:
                        checkIfRolled(rolled);
                        provideRightAmountOfArguments(separate.length, 1);
                        board.harvest();
                        break;
                    case BUY:
                        checkIfRolled(rolled);
                        provideRightAmountOfArguments(separate.length, 2);
                        board.buy(separate[1]);
                        break;
                    case PREPARE:
                        checkIfRolled(rolled);
                        provideRightAmountOfArguments(separate.length, 2);
                        board.prepare(separate[1]);
                        break;
                    case CAN_PREPARE:
                        provideRightAmountOfArguments(separate.length, 1);
                        board.canPrepare();
                        break;
                    case SHOW_MARKET:
                        provideRightAmountOfArguments(separate.length, 1);
                        Terminal.printLine(board.getMarket().toString());
                        break;
                    case SHOW_PLAYER:
                        provideRightAmountOfArguments(separate.length, 2);
                        String[] indexStr = separate[1].split("");
                        int index = Integer.parseInt(indexStr[1]);
                        board.showPlayer(separate[1], index);
                        break;
                    case TURN:
                        checkIfRolled(rolled);
                        provideRightAmountOfArguments(separate.length, 1);
                        board.turn();
                        rolled = false;
                        break;
                    case QUIT:
                        provideRightAmountOfArguments(separate.length, 1);
                        return;
                    default:
                        if (input.trim().length() == 0) {
                            Terminal.printError("No arguments added yet.");
                        } else {
                            Terminal.printError("unacceptable command given. Try again.");
                        }
                }
            } catch (SyntaxException | SemanticException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                Terminal.printError(e.getMessage());
            }
        }
    }

    /**
     * @param field string representation taken from command line
     * @return returns the field object, which embodies the circulating fields
     * @throws SemanticException when semantic demands are not fulfilled
     */
    private static Fields arrangeField(String field) throws SemanticException, SyntaxException {
        for (int i = 1; i < field.length() - 1; i++) {
            if (field.charAt(i - 1) == field.charAt(i) && field.charAt(i) == ';') {
                throw new SyntaxException("invalid field input.");
            }
        }
        String[] fieldsArray = field.split("[; ]");
        String allFields = String.join("", fieldsArray);
        checkFields(allFields);
        String repeatedFields = allFields.repeat(100);
        for (int i = 1; i < repeatedFields.length() - 1; i++) {
            if (repeatedFields.charAt(i) == 'S') {
                if (repeatedFields.charAt(i - 1) == repeatedFields.charAt(i + 1)) {
                    throw new SemanticException("the two equal fields cannot stay together");
                }
            }
        }
        return new Fields(repeatedFields);
    }

    /**
     * checks if the player has already rolled
     *
     * @param rolled true if rolled, false else
     * @throws SyntaxException if the player has not rolled yet
     */
    private static void checkIfRolled(boolean rolled) throws SyntaxException {
        if (!rolled) {
            throw new SyntaxException("the player has not rolled yet");
        }
    }

    /**
     * @param pPlayer first argument taken from command line
     * @param fields  second argument taken from command line
     * @return returns the player array, which embodies the players, who are ready for the game
     */
    private static Players[] arrangePlayers(String pPlayer, String fields) {
        try {
            int playersNumber = Integer.parseInt(pPlayer);
            if (playersNumber < 2 || playersNumber > 4) {
                throw new SemanticException("incorrect number of players");
            }
            final Players[] players = new Players[playersNumber];
            for (int i = 0; i < playersNumber; i++) {
                players[i] = new Players(i, new MoneyMargin(), arrangeField(fields));
            }
            return players;
        } catch (SemanticException | NumberFormatException | SyntaxException e) {
            Terminal.printError(e.getMessage());
        }
        return null;
    }

    /**
     * Checks, if correct amount of arguments are given
     *
     * @param written  amount of given argument
     * @param expected required amount of arguments
     * @throws SyntaxException is thrown, if the incorrect amount of arguments are given
     */
    private static void provideRightAmountOfArguments(final int written, final int expected) throws SyntaxException {
        if (written != expected && expected == 1) {
            throw new SyntaxException("the command does not accept any arguments, but some were added.");
        } else if (written != expected && expected == 2) {
            throw new SyntaxException("the command accepts only one argument. Try once again.");
        }
    }

    /**
     * Checks pre-game demands for the fields, such as amount, given characters, start field,
     * two neighbouring fields
     *
     * @param fieldStr string array of the fields, free from semicolons
     * @throws SemanticException when semantic demands are not fulfilled
     */
    private static void checkFields(String fieldStr) throws SemanticException, SyntaxException {
        j = 0;
        if (fieldStr.length() < 4) {
            throw new SemanticException("given incorrect amount of fields");
        }
        if (fieldStr.length() > 25) {
            throw new SemanticException("total amount of fields should be less than 25.");
        }
        for (int i = 0; i < fieldStr.length(); i++) {
            char c = fieldStr.charAt(i);
            if (!(c == 'S' || c == 'H' || c == 'C' || c == 'M')) {
                throw new SemanticException("incorrect field type");
            }
        }
        char firstChar = fieldStr.charAt(0);
        if (firstChar != 'S') {
            throw new SemanticException("the first field must be the start field.");
        }
        for (int i = 0; i < fieldStr.length(); i++) {
            char c = fieldStr.charAt(i);
            if (c == 'S') {
                j++;
            }
        }
        if (j > 1) {
            throw new SyntaxException("it can be only one start field.");
        }
        for (int i = 1; i < fieldStr.length() - 1; i++) {
            if (fieldStr.charAt(i - 1) == fieldStr.charAt(i)) {
                throw new SemanticException("the two equal fields cannot stay together");
            }
        }
    }
}
