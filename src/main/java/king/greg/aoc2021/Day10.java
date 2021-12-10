package king.greg.aoc2021;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day10 {

  final List<String> lines;

  public Day10(final List<String> lines) {
    this.lines = lines;
  }

  public int scoreIllegalCharacters() {
    var score = 0;
    for (final var line : lines) {
      score += scoreLine(line);
    }
    return score;
  }

  public long scoreAutoComplete() {
    var scores = new ArrayList<Long>();
    for (final var line : lines) {
      if (scoreLine(line) == 0) {
        scores.add(scoreAutocompleteLine(line));
      }
    }
    scores.sort(Comparator.comparingLong(Long::longValue));
    return scores.get(scores.size() / 2);
  }

  private int scoreLine(final String line) {
    final var queue = new ArrayDeque<Character>();
    for (var i = 0; i < line.length(); i++) {
      final var character = line.charAt(i);
      switch (character) {
        case '(':
        case '[':
        case '{':
        case '<':
          queue.add(character);
          break;
        case ')':
          if (!Character.valueOf('(').equals(queue.removeLast())) {
            return 3;
          }
          break;
        case ']':
          if (!Character.valueOf('[').equals(queue.removeLast())) {
            return 57;
          }
          break;
        case '}':
          if (!Character.valueOf('{').equals(queue.removeLast())) {
            return 1197;
          }
          break;
        case '>':
          if (!Character.valueOf('<').equals(queue.removeLast())) {
            return 25137;
          }
          break;
        default:
          throw new IllegalStateException();
      }
    }
    return 0;
  }

  private long scoreAutocompleteLine(final String line) {
    final var queue = new ArrayDeque<Character>();
    for (var i = 0; i < line.length(); i++) {
      final var character = line.charAt(i);
      switch (character) {
        case '(':
        case '[':
        case '{':
        case '<':
          queue.add(character);
          break;
        case ')':
        case ']':
        case '}':
        case '>':
          queue.removeLast();
          break;
        default:
          throw new IllegalStateException();
      }
    }
    long score = 0;
    while (!queue.isEmpty()) {
      score *= 5;
      switch (queue.removeLast()) {
        case '(':
          score += 1;
          break;
        case '[':
          score += 2;
          break;
        case '{':
          score += 3;
          break;
        case '<':
          score += 4;
          break;
        default:
          throw new IllegalStateException();
      }
    }
    return score;
  }
}
