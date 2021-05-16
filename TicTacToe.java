/*
* This program is a tic tac toe game against the computer.
*
* @author  Patrick Gemmell
* @version 1.0
* @since   2021-05-13
*/

import java.util.Scanner;  // Import the Scanner class

public final class TicTacToe {
    private TicTacToe() {
  }
  /**
  * the number negative three.
  */
  public static final int NTHREE = -3;
  /**
  * the number three.
  */
  public static final int THREE = 3;
   /**
  * the number four.
  */
  public static final int FOUR = 4;
  /**
  * the number five.
  */
  public static final int FIVE = 5;
   /**
  * the number six.
  */
  public static final int SIX = 6;
  /**
  * the number seven.
  */
  public static final int SEVEN = 7;
   /**
  * the number eight.
  */
  public static final int EIGHT = 8;
  /**
  * the number nine.
  */
  public static final int NINE = 9;
  /**
  * the number negative ten.
  */
  public static final int NTEN = -10;
  /**
  * the number ten.
  */
  public static final int TEN = 10;
  /**
  * the number forty nine.
  */
  public static final int FORTYNINE = 49;
  /**
  * the number thousand.
  */
  public static final int THOUSAND = 1000;
  /**
  * the number negative thousand.
  */
  public static final int NTHOUSAND = -1000;
  /**
   * This function prints the current iteration of the game board.
   * @param gameList
   */
  static void printBoard(final char[][] gameList) {
    // Setting parameters for the character array
    int stopper = 0;
    int adder = 0;

    // Printing the current iteration of the board
    for (char[] listCounter : gameList) {
      for (int printCounter : listCounter) {
        char listValue = (char) (printCounter);
        System.out.print(listValue);
        adder += 1;

        // Checking to see if the counter is on the last element of the array
        if (printCounter != THREE) {
          if (adder % THREE == 0) {
            // Printing a seperation of rows
            System.out.println("");
            adder = 0;
            stopper = stopper + 1;
            if (stopper != THREE) {
              System.out.println("---------");
            }
          } else {
            // Printing a seperation of columns
            System.out.print(" | ");
          }
        } else {
          continue;
        }
      }
    }
  }

  /**
   * This function figures out if the array passed in has a winning condition.
   * @param winBoard
   * @param playerToken
   * @param cpuToken
   * @return
   * returns board
   */
  static int winCondition(final char[][] winBoard,
                          final char playerToken, final char cpuToken) {
    // Checking for horizontal wins
    for (int horizontal = 0; horizontal < THREE; horizontal++) {
      if (winBoard[horizontal][0] == winBoard[horizontal][1]
          && winBoard[horizontal][0] == winBoard[horizontal][2]
          && winBoard[horizontal][1] == winBoard[horizontal][2]) {
        if (winBoard[horizontal][0] == playerToken) {
          return NTEN;
        } else if (winBoard[horizontal][0] == cpuToken) {
          return TEN;
        } else {
          continue;
        }
      }
    }

    // Checking for vertical wins
    for (int vertical = 0; vertical < THREE; vertical++) {
      if (winBoard[0][vertical] == winBoard[1][vertical]
          && winBoard[0][vertical] == winBoard[2][vertical]
          && winBoard[1][vertical] == winBoard[2][vertical]) {
        if (winBoard[0][vertical] == playerToken) {
          return NTEN;
        } else if (winBoard[0][vertical] == cpuToken) {
          return TEN;
        } else {
          continue;
        }
      }
    }

    // Checking for diagonal wins
    if (winBoard[0][0] == winBoard[1][1] && winBoard[0][0] == winBoard[2][2]
        && winBoard[1][1] == winBoard[2][2]) {
      if (winBoard[0][0] == playerToken) {
        return NTEN;
      } else if (winBoard[0][0] == cpuToken) {
        return TEN;
      }
    } else if (winBoard[0][2] == winBoard[1][1]
               && winBoard[0][2] == winBoard[2][0]
               && winBoard[1][1] == winBoard[2][0]) {
      if (winBoard[2][0] == playerToken) {
        return NTEN;
      } else if (winBoard[2][0] == cpuToken) {
        return TEN;
      }
    }

    // Returning 0 if no winner has been determined yet
    return 0;
  }

  /**
   * This function finds if there are any open spaces left on the board.
   * @param tieBoard
   * @param tokenX
   * @param tokenO
   * @return
   * returns board
   */
  static Boolean openSpaces(final char[][] tieBoard,
                            final char tokenX, final char tokenO) {
    // Looping through all indexes to find if there are empty spaces
    for (int drawCounter = 0; drawCounter < tieBoard.length; drawCounter++) {
      for (int tieCounter = 0; tieCounter < tieBoard.length; tieCounter++) {
        if (tieBoard[drawCounter][tieCounter] != tokenX
            && tieBoard[drawCounter][tieCounter] != tokenO) {
          return true;
        }
      }
    }

    // Returning false if there are still empty spaces
    return false;
  }

  /**
   * This function finds the best possible score or outcome of a move through
   * recursion for the computer.
   * @param gameBoard
   * @param length
   * @param maxValue
   * @return
   * returns board
   */
  static int moveScore(final char[][] gameBoard,
                       final int length, final Boolean maxValue) {
    // Defining user and computer tokens
    char user = 'X';
    char cpu = 'O';

    // Checking to see if someone has won or tied the game
    int score = winCondition(gameBoard, user, cpu);
    if (score == TEN) {
      return score;
    } else if (score == NTEN) {
      return score;
    } else if (openSpaces(gameBoard, user, cpu)) {
      return 0;
    }

    // Checking to see if the max value still needs calculating
    if (maxValue) {
      // Defining a variable that holds the best possible score
      int best = NTHOUSAND;

      // Iterating through all possible moves
      for (int rowCounter = 0; rowCounter < THREE; rowCounter++) {
        for (int columnCounter = 0; columnCounter < THREE; columnCounter++) {
          // Checking to see what spaces are open
          if (gameBoard[rowCounter][columnCounter] != user
              && gameBoard[rowCounter][columnCounter] != cpu) {
            // Filling the empty spot for now
            char tempValue = gameBoard[rowCounter][columnCounter];
            gameBoard[rowCounter][columnCounter] = cpu;

            // Best possible score for the player
            best = Math.max(best, moveScore(gameBoard, length + 1, !maxValue));

            // Undoing the filled spaces
            gameBoard[rowCounter][columnCounter] = tempValue;
          }
        }
      }
      return best;
    } else {
      // Defining a variable that holds the best possible score
      int best = THOUSAND;

      // Iterating through all possible moves
      for (int rowCounter = 0; rowCounter < THREE; rowCounter++) {
        for (int columnCounter = 0; columnCounter < THREE; columnCounter++) {
          // Checking to see what spaces are open
          if (gameBoard[rowCounter][columnCounter] != user
              && gameBoard[rowCounter][columnCounter] != cpu) {
            // Filling the empty spot for now
            char tempValue = gameBoard[rowCounter][columnCounter];
            gameBoard[rowCounter][columnCounter] = user;

            // Best possible score for the player
            best = Math.min(best, moveScore(gameBoard, length + 1, !maxValue));

            // Undoing the filled spaces
            gameBoard[rowCounter][columnCounter] = tempValue;
          }
        }
      }
      return best;
    }
  }

  /**
   * This function controls the computer's moves.
   * @param moveBoard
   * @return
   * return board
   */
  static int computerMove(final char[][] moveBoard) {
    // Initializing some base coordinates to be used in determining the move
    int moveRow = -1;
    int moveColumn = -1;

    // Setting up the user and player tokens
    char userSymbol = 'X';
    char cpuSymbol = 'O';

    // Setting up a variable to keep track of the move's score
    int bestScore = NTHOUSAND;

    // Iterating through the board to find the best move
    for (int bestRow = 0; bestRow < THREE; bestRow++) {
      for (int bestColumn = 0; bestColumn < THREE; bestColumn++) {
        if (moveBoard[bestRow][bestColumn] != userSymbol
            && moveBoard[bestRow][bestColumn] != cpuSymbol) {
          // Temporarily filling the empty space
          char placeholdValue = moveBoard[bestRow][bestColumn];
          moveBoard[bestRow][bestColumn] = cpuSymbol;

          // Calculating the score of the computer's move
          int moveTotal = moveScore(moveBoard, 0, false);

          // Removing the placeholder
          moveBoard[bestRow][bestColumn] = placeholdValue;

          // Finding if the current or previously tested score is higher
          if (moveTotal > bestScore) {
            moveRow = bestRow;
            moveColumn = bestColumn;
            bestScore = moveTotal;
          }
        }
      }
    }

    // Finding the optimal move based on the score and returning it
    if (moveRow == 0 && moveColumn == 0) {
      return 1;
    } else if (moveRow == 0 && moveColumn == 1) {
      return 2;
    } else if (moveRow == 0 && moveColumn == 2) {
      return THREE;
    } else if (moveRow == 1 && moveColumn == 0) {
      return FOUR;
    } else if (moveRow == 1 && moveColumn == 1) {
      return FIVE;
    } else if (moveRow == 1 && moveColumn == 2) {
      return SIX;
    } else if (moveRow == 2 && moveColumn == 0) {
      return SEVEN;
    } else if (moveRow == 2 && moveColumn == 1) {
      return EIGHT;
    } else if (moveRow == 2 && moveColumn == 2) {
      return NINE;
    } else {
      throw null;
    }
  }

  /**
   * This function allows the user to play against the computer in a game of
   * tic tac toe.
   * @param args
   */
  public static void main(final String[] args) {
    // Creating an array and adding information about the board to it
    char[][] userList = new char[THREE][THREE];
    int infoAsNumber = NTHREE;
    char newInfo;
    for (int infoCounter = 0; infoCounter < THREE; infoCounter++) {
      infoAsNumber = infoAsNumber + THREE;
      for (int newCounter = 0; newCounter < THREE; newCounter++) {
        newInfo = (char) (FORTYNINE + infoAsNumber + newCounter);
        userList[infoCounter][newCounter] = newInfo;
      }
    }
    // Creating an array to hold used spots
    int[] usedSpots = new int[NINE];
    // Printing the current iteration of the board
    System.out.println("Welcome to Tic Tac Toe!");
    printBoard(userList);
    // Stating the tokens for each player
    System.out.println("");
    System.out.println("Your token is X, the computer is O");
    // This allows for user input
    Scanner userInput = new Scanner(System.in);
    try {
      // Setting up the game counter
      int gameCounter = 0;
      // Looping through the game
      while (gameCounter < NINE) {
        // Getting input for the user's move
        System.out.println("");
        System.out.print("Enter a move you would like to make (1 to 9): ");
        int userNumber = userInput.nextInt();
        // Ensuring the user played a number that has not been used
        for (int usedCounter = 0; usedCounter < usedSpots.length - 1;
             usedCounter++) {
          if (userNumber > NINE || userNumber < 1) {
            throw null;
          } else if (usedSpots[usedCounter] == userNumber) {
            throw null;
          } else {
            continue;
          }
        }
        usedSpots[gameCounter] = userNumber;
        switch (userNumber) {
          case 1:
            userList[0][0] = 'X';
            break;
          case 2:
            userList[0][1] = 'X';
            break;
          case THREE:
            userList[0][2] = 'X';
            break;
          case FOUR:
            userList[1][0] = 'X';
            break;
          case FIVE:
            userList[1][1] = 'X';
            break;
          case SIX:
            userList[1][2] = 'X';
            break;
          case SEVEN:
            userList[2][0] = 'X';
            break;
          case EIGHT:
            userList[2][1] = 'X';
            break;
          case NINE:
            userList[2][2] = 'X';
            break;
          default:
            throw null;
        }
        // Printing the board now that the user has gone
        System.out.println("");
        printBoard(userList);
        // Checking to see if a win occured
        if (winCondition(userList, 'X', 'O') == NTEN) {
          System.out.println("");
          System.out.println("You Win!");
          break;
        } else if (winCondition(userList, 'X', 'O') == TEN) {
          System.out.println("");
          System.out.println("The Computer Wins!");
          break;
        }
        gameCounter += 1;
        if (gameCounter == NINE) {
          System.out.println("");
          System.out.println("Tied Game");
          break;
        }
        // Determining where the computer will put its token
        System.out.println("");
        int computerChoice = computerMove(userList);
        System.out.println("Computer's Move: " + computerChoice);
        usedSpots[gameCounter] = computerChoice;
        switch (computerChoice) {
          case 1:
            userList[0][0] = 'O';
            break;
          case 2:
            userList[0][1] = 'O';
            break;
          case THREE:
            userList[0][2] = 'O';
            break;
          case FOUR:
            userList[1][0] = 'O';
            break;
          case FIVE:
            userList[1][1] = 'O';
            break;
          case SIX:
            userList[1][2] = 'O';
            break;
          case SEVEN:
            userList[2][0] = 'O';
            break;
          case EIGHT:
            userList[2][1] = 'O';
            break;
          case NINE:
            userList[2][2] = 'O';
            break;
          default:
            throw null;
        }
        printBoard(userList);
        gameCounter += 1;
        // Checking to see if a win occured
        if (winCondition(userList, 'X', 'O') == NTEN) {
          System.out.println("");
          System.out.println("You Win!");
          break;
        } else if (winCondition(userList, 'X', 'O') == TEN) {
          System.out.println("");
          System.out.println("The Computer Wins!");
          break;
        }
      }
    } catch (NullPointerException e) {
      System.out.println("");
      System.out.println("ERROR: Invalid Input");
    } catch (Exception e) {
      System.out.println("");
      System.out.println("ERROR: Invalid Input");
    }
  }
}
