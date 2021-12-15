package king.greg.aoc2021;

import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Day15 {

  final Map<Point, Integer> map;
  final Point target;

  static final List<Point> transforms = Arrays.asList(new Point(0, -1), new Point(1, 0),
      new Point(0, 1), new Point(-1, 0));

  public Day15(final List<String> lines) {
    this(lines, 1);
  }

  public Day15(final List<String> lines, final int multiplier) {
    map = new HashMap<>();
    final var box = lines.size();
    for (var yIncrease = 0; yIncrease < multiplier; yIncrease++) {
      for (var xIncrease = 0; xIncrease < multiplier; xIncrease++) {
        for (var y = 0; y < box; y++) {
          final var currentLine = lines.get(y);
          for (var x = 0; x < box; x++) {
            var value = currentLine.charAt(x) - '0' + xIncrease + yIncrease;
            while (value > 9) {
              value -= 9;
            }
            map.put(new Point(x + (xIncrease * box), y + (yIncrease * box)), value);
          }
        }
      }
    }
    target = new Point((multiplier * box) - 1, (multiplier * box) - 1);
  }

  public int lowestRisk() {
    final var visited = new HashSet<Node>();
    final var priorityQueue = initQueue();
    final var start = new Node(new Point(0, 0), map.get(new Point(0, 0)) * -1);
    priorityQueue.add(start);

    while (!priorityQueue.isEmpty()) {
      var current = priorityQueue.remove();

      if (!visited.contains(current)) {
        visited.add(current);
        if (target.equals(current.location)) {
          return current.riskTaken;
        }
        for (var neighbor : current.findNeighbors()) {
          if (!visited.contains(neighbor)) {
            priorityQueue.add(neighbor);
          }
        }
      }
    }

    return 0;
  }

  class Node {

    @Override
    public int hashCode() {
      final var prime = 31;
      var result = 1;
      result = prime * result + getOuterType().hashCode();
      result = prime * result + ((location == null) ? 0 : location.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      Node other = (Node) obj;
      if (!getOuterType().equals(other.getOuterType())) {
        return false;
      }
      if (location == null) {
        return other.location == null;
      } else {
        return location.equals(other.location);
      }
    }

    private Day15 getOuterType() {
      return Day15.this;
    }

    Point location;
    int riskTaken;

    Node(final Point point, final int riskBeforeEntering) {
      location = point;
      riskTaken = riskBeforeEntering + map.get(point);
    }

    public int predictedTotalRiskToTarget() {
      return riskTaken + estimatedRemainingRiskToTarget();
    }

    public int estimatedRemainingRiskToTarget() {
      return (Math.abs(location.x - target.x) + Math.abs(location.y - target.y));
    }

    public Set<Node> findNeighbors() {
      final Set<Node> neighbors = new HashSet<>();

      for (final var transform : transforms) {
        final var neighborPoint = new Point(location.x + transform.x, location.y + transform.y);
        if (map.containsKey(neighborPoint)) {
          neighbors.add(new Node(neighborPoint, riskTaken));
        }
      }
      return neighbors;
    }
  }

  private PriorityQueue<Node> initQueue() {
    return new PriorityQueue<>(Comparator.comparingInt(Node::predictedTotalRiskToTarget));
  }
}
