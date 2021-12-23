package king.greg.aoc2021;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;

public class Day21 {

  final Map<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>, Pair<Long, Long>> memo = new HashMap<>();

  private static final int[] rollDistributions = {1, 3, 6, 7, 6, 3, 1};

  public static long finalScore(final int playerOneStart, final int playerTwoStart) {
    var lastRoll = 0;
    var scores = new int[2];
    var positions = new int[]{playerOneStart, playerTwoStart};
    var currentPlayer = 0;

    while (scores[0] < 1000 && scores[1] < 1000) {
      positions[currentPlayer] = ((positions[currentPlayer] + (lastRoll * 3) + 5) % 10) + 1;
      lastRoll = (lastRoll + 3) % 1000;
      scores[currentPlayer] += positions[currentPlayer];
      currentPlayer = (currentPlayer + 1) % 2;
    }
    return (long) lastRoll * (Math.min(scores[0], scores[1]));
  }

  public static long diracWins(final int playerOneStart, final int playerTwoStart) {
    Map<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>, Long> states = new HashMap<>();
    states.put(Pair.of(Pair.of(playerOneStart, 0), Pair.of(playerTwoStart, 0)), 1L);
    var currentPlayer = 0;
    var wins = new long[2];
    while (!states.isEmpty()) {
      final Map<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>, Long> newStates = new HashMap<>();
      for (var state : states.entrySet()) {
        var currentPlayerState = state.getKey().getLeft();
        var otherPlayerState = state.getKey().getRight();
        for (var rolls = 3; rolls < 10; rolls++) {
          long count = state.getValue() * rollDistributions[rolls - 3];
          var newPlayerState = newState(currentPlayerState, rolls);
          if (newPlayerState.getRight() >= 21) {
            wins[currentPlayer] += count;
          } else {
            var newGameState = Pair.of(otherPlayerState, newPlayerState);
            newStates.put(newGameState, newStates.getOrDefault(newGameState, 0L) + count);
          }
        }
      }
      currentPlayer = (currentPlayer + 1) % 2;
      states = newStates;
    }
    return Arrays.stream(wins).max().getAsLong();
  }

  public long diracWinsAlternate(final int playerOneStart, final int playerTwoStart) {
    final var initialState = Pair.of(Pair.of(playerOneStart, 0), Pair.of(playerTwoStart, 0));
    var value = memoLookup(initialState);
    return Math.max(value.getLeft(), value.getRight());
  }

  private Pair<Long, Long> memoLookup(
      final Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> currentState) {
    var value = memo.get(currentState);
    if (null == value) {
      var currentPlayerState = currentState.getLeft();
      var otherPlayerState = currentState.getRight();
      var currentPlayerWins = 0L;
      var otherPlayerWins = 0L;
      if (otherPlayerState.getRight() >= 2) {
        value = Pair.of(0L, 1L);
      } else {
        for (var rolls = 3; rolls < 10; rolls++) {
          var newCurrentPlayerState = newState(currentPlayerState, rolls);
          var wins = memoLookup(Pair.of(otherPlayerState, newCurrentPlayerState));
          long count = rollDistributions[rolls - 3];
          currentPlayerWins += count * wins.getRight();
          otherPlayerWins += count * wins.getLeft();
        }
        value = Pair.of(currentPlayerWins, otherPlayerWins);
      }
      memo.put(currentState, value);
    }
    return value;
  }

  private static Pair<Integer, Integer> newState(final Pair<Integer, Integer> currentState,
      int rolls) {
    var newPosition = (((currentState.getLeft() - 1) + rolls) % 10) + 1;
    var newScore = currentState.getRight() + newPosition;
    return Pair.of(newPosition, newScore);
  }
}
