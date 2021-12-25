package king.greg.aoc2021;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day25 {

  Set<Point> cucumbersEast;
  Set<Point> cucumbersSouth;
  final int xBound;
  final int yBound;

  public Day25(final List<String> lines) {
    cucumbersEast = new HashSet<>();
    cucumbersSouth = new HashSet<>();
    for (var y = 0; y < lines.size(); y++) {
      var line = lines.get(y);
      for (var x = 0; x < line.length(); x++) {
        final var cucumber = line.charAt(x);
        if (cucumber == '>') {
          cucumbersEast.add(new Point(x, y));
        }
        if (cucumber == 'v') {
          cucumbersSouth.add(new Point(x, y));
        }
      }
    }
    yBound = lines.size();
    xBound = lines.get(0).length();
  }

  final int deadlockStep() {
    var moveCount = 0;
    var stillMoving = true;
    while (stillMoving) {
      stillMoving = false;
      moveCount++;
      var newCucumbersEast = new HashSet<Point>();
      for (var cucumber : cucumbersEast) {
        var hopefulMove = new Point((cucumber.x + 1) % xBound, cucumber.y);
        if (cucumbersEast.contains(hopefulMove) || cucumbersSouth.contains(hopefulMove)) {
          newCucumbersEast.add(cucumber);
        } else {
          newCucumbersEast.add(hopefulMove);
          stillMoving = true;
        }
      }
      cucumbersEast = newCucumbersEast;
      var newCucumbersSouth = new HashSet<Point>();
      for (var cucumber : cucumbersSouth) {
        var hopefulMove = new Point(cucumber.x, (cucumber.y + 1) % yBound);
        if (cucumbersEast.contains(hopefulMove) || cucumbersSouth.contains(hopefulMove)) {
          newCucumbersSouth.add(cucumber);
        } else {
          newCucumbersSouth.add(hopefulMove);
          stillMoving = true;
        }
      }
      cucumbersSouth = newCucumbersSouth;
    }
    return moveCount;
  }
}
