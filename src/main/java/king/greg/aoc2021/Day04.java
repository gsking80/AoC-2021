package king.greg.aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day04 {

  final List<String> numbers;
  final List<String[][]> bingoCards;

  public Day04(final List<String> lines) {

    numbers = new ArrayList<>();
    bingoCards = new ArrayList<>();

    final String numberList = lines.get(0);
    numbers.addAll(Arrays.asList(numberList.split(",")));

    for (var x = 1; x < lines.size(); x += 6) {
      final var bingoCard = new String[5][5];
      for (var i = 0; i < 5; i++) {
        final String line = lines.get(x + i + 1);
        for (var j = 0; j < 5; j++) {
          bingoCard[i][j] = line.substring(j * 3, j * 3 + 2).trim();
        }
      }
      bingoCards.add(bingoCard);
    }
  }

  public int calculateWinningScore(final boolean part2) {
    var lastWinner = new String[5][5];
    var lastNumber = "";
    for (final String numberCalled : numbers) {
      callNumber(numberCalled);

      final List<String[][]> winningCards = findWinners();
      if (!winningCards.isEmpty()) {
        if (part2) {
          for (final String[][] winningCard : winningCards) {
            bingoCards.remove(winningCard);
            lastWinner = winningCard;
            lastNumber = numberCalled;
          }
        } else {
          return score(winningCards.get(0), numberCalled);
        }
      }
    }
    return score(lastWinner, lastNumber);
  }

  private void callNumber(String numberCalled) {
    for (final String[][] bingoCard : bingoCards) {
      for (var i = 0; i < 5; i++) {
        for (var j = 0; j < 5; j++) {
          if (bingoCard[i][j].equals(numberCalled)) {
            bingoCard[i][j] = "X";
          }
        }
      }
    }
  }

  private int score(final String[][] winningCard, final String winningNumber) {
    var sum = 0;
    for (var i = 0; i < 5; i++) {
      for (var j = 0; j < 5; j++) {
        sum += winningCard[i][j].equals("X") ? 0 : Integer.parseInt(winningCard[i][j]);
      }
    }
    return sum * Integer.parseInt(winningNumber);
  }

  private List<String[][]> findWinners() {
    final List<String[][]> winners = new ArrayList<>();
    for (final String[][] bingoCard : bingoCards) {
      for (var i = 0; i < 5; i++) {
        var rowValid = true;
        var columnValid = true;
        for (var j = 0; (rowValid || columnValid) && j < 5; j++) {
          if (rowValid && !bingoCard[i][j].equals("X")) {
            rowValid = false;
          }
          if (columnValid && !bingoCard[j][i].equals("X")) {
            columnValid = false;
          }
        }
        if (rowValid || columnValid) {
          winners.add(bingoCard);
        }
      }
    }
    return winners;
  }
}
