# Polish draughts

## Story

In this project, our job was to implement a variation of [Polish draughts](https://en.wikipedia.org/wiki/International_draughts) for two players.

### Main rules:
- There are two players with white and black pawns. White moves first.
- The size of the board can be picked before the game starts, but it must be an integer between 10 and 20.
- All moves and captures are made diagonally.
- Pawns can also capture backward.
- Multiple successive jumps forward or backward in a single turn can and must be made if there is an unoccupied square immediately beyond the enemy pawn after each jump.
- A piece is crowned if it stops on the far edge of the board at the end of its turn (that is, if it reaches the edge, and is not required to jump backward).

### Winning and draws:
- A player with no valid moves remaining loses.
- Optional: The game is considered a draw when the same position repeats itself for the third time (not necessarily consecutive), with the same player having the move each time.
- A king-versus-king endgame is automatically declared a draw.

## Build with 

- Java

## How to run
Type the following commands to the command line in the project source folder(.../polish-draughts-java-szabinalovas):

```sh
$ mvn clean compile package
```

```sh
$ java -jar target/polish-draughts-1.0-SNAPSHOT.jar
```

# ... and let the game begin!







