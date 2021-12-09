package king.greg.aoc2021;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Day09 {

  final List<String> heightMap;
  final Set<Point> usedPoints;

  public Day09(final List<String> lines) {
    heightMap = lines;
    usedPoints = new HashSet<>();
  }

  public int sumRiskLowPoints() {
    var risk = 0;
    for (var y = 0; y < heightMap.size(); y++) {
      for (var x = 0; x < heightMap.get(y).length(); x++) {
        if (lowPoint(x, y)) {
          risk += (heightMap.get(y).charAt(x) - '0' + 1);
        }
      }
    }
    return risk;
  }

  private boolean lowPoint(final int x, final int y) {
    final var point = heightMap.get(y).charAt(x);
    var lowPoint = (x == 0 || point < heightMap.get(y).charAt(x - 1));
    lowPoint =
        lowPoint && (x == heightMap.get(y).length() - 1 || point < heightMap.get(y).charAt(x + 1));
    lowPoint = lowPoint && (y == 0 || point < heightMap.get(y - 1).charAt(x));
    lowPoint = lowPoint && (y == heightMap.size() - 1 || point < heightMap.get(y + 1).charAt(x));
    return lowPoint;
  }

  public long productThreeLargestBasins() {
    final var basinSizes = initQueue();
    for (var y = 0; y < heightMap.size(); y++) {
      for (var x = 0; x < heightMap.get(y).length(); x++) {
        final var basinSize = pointsInBasin(new Point(x, y));
        if (basinSize != 0) {
          basinSizes.add(basinSize);
        }
      }
    }
    var largestBasins = 1;
    for (var i = 0; i < 3; i++) {
      largestBasins *= basinSizes.remove();
    }
    return largestBasins;
  }

  private PriorityQueue<Integer> initQueue() {
    return new PriorityQueue<>(10,
        (arg0, arg1) -> Comparator.comparing(Integer::intValue).compare(arg1, arg0));
  }

  private Set<Point> neighbors(final Point currentPoint) {
    final var neighbors = new HashSet<Point>();
    if (currentPoint.x > 0) {
      neighbors.add(new Point(currentPoint.x - 1, currentPoint.y));
    }
    if (currentPoint.x < heightMap.get(currentPoint.y).length() - 1) {
      neighbors.add(new Point(currentPoint.x + 1, currentPoint.y));
    }
    if (currentPoint.y > 0) {
      neighbors.add(new Point(currentPoint.x, currentPoint.y - 1));
    }
    if (currentPoint.y < heightMap.size() - 1) {
      neighbors.add(new Point(currentPoint.x, currentPoint.y + 1));
    }
    return neighbors;
  }

  private int pointsInBasin(final Point startingPoint) {
    var points = 0;
    final var pointsToCheck = new ArrayDeque<Point>();
    pointsToCheck.add(startingPoint);
    while (!pointsToCheck.isEmpty()) {
      var currentPoint = pointsToCheck.remove();
      if (!usedPoints.contains(currentPoint) && (
          heightMap.get(currentPoint.y).charAt(currentPoint.x) != '9')) {
        usedPoints.add(currentPoint);
        points++;
        pointsToCheck.addAll(neighbors(currentPoint));
      }
    }
    return points;
  }
}
