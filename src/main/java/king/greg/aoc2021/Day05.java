package king.greg.aoc2021;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day05 {

  final Set<List<Point>> ventLines;

  public Day05(FileReader fileReader) {
    ventLines = new HashSet<>();
    try {
      final var buf = new BufferedReader(fileReader);

      while (true) {
        final var lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        } else {
          var parts = lineJustFetched.split(" -> ");
          var part1 = parts[0].split(",");
          var part2 = parts[1].split(",");
          var pointList = new ArrayList<Point>();
          pointList.add(new Point(Integer.parseInt(part1[0]), Integer.parseInt(part1[1])));
          pointList.add(new Point(Integer.parseInt(part2[0]), Integer.parseInt(part2[1])));
          ventLines.add(pointList);
        }
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public int countOrthogonalOverlaps() {
    final Map<Point, Integer> vents = new HashMap<>();
    for (final List<Point> entry : ventLines) {
      var deltaX = entry.get(0).x - entry.get(1).x;
      var deltaY = entry.get(0).y - entry.get(1).y;
      if (deltaX == 0 || deltaY == 0) {
        var count = vents.getOrDefault(entry.get(1), 0) + 1;
        vents.put(entry.get(1), count);
        fillIn(vents, entry, deltaX, deltaY);
      }
    }
//    printMap(vents);
    var count = 0;
    for (final Integer intersections : vents.values()) {
      if (intersections > 1) {
        count++;
      }
    }
    return count;
  }

  private void fillIn(Map<Point, Integer> vents, List<Point> entry, int deltaX, int deltaY) {
    int count;
    while (deltaX != 0 || deltaY != 0) {
      var point = new Point(entry.get(1).x + deltaX, entry.get(1).y + deltaY);
      count = vents.getOrDefault(point, 0) + 1;
      vents.put(point, count);
      if (deltaX > 0) {
        deltaX--;
      } else if (deltaX < 0) {
        deltaX++;
      }
      if (deltaY > 0) {
        deltaY--;
      } else if (deltaY < 0) {
        deltaY++;
      }
    }
  }

  public int countAllOverlaps() {
    final Map<Point, Integer> vents = new HashMap<>();
    for (final List<Point> entry : ventLines) {
      var count = vents.getOrDefault(entry.get(1), 0) + 1;
      vents.put(entry.get(1), count);
      var deltaX = entry.get(0).x - entry.get(1).x;
      var deltaY = entry.get(0).y - entry.get(1).y;
      fillIn(vents, entry, deltaX, deltaY);
    }
//    printMap(vents);
    var count = 0;
    for (final Integer intersections : vents.values()) {
      if (intersections > 1) {
        count++;
      }
    }
    return count;
  }

  private void printMap(final Map<Point, Integer> map) {
    for (var y = 0; y < 10; y++) {
      for (var x = 0; x < 10; x++) {
        var count = map.getOrDefault(new Point(x, y), 0);
        System.out.print(count > 0 ? count.toString() : '.');
      }
      System.out.println();
    }
  }
}
