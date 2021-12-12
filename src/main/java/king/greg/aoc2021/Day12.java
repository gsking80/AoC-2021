package king.greg.aoc2021;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {

  public static final String START = "start";
  public static final String END = "end";
  final Map<String, List<String>> paths;
  final Deque<String> path;

  public Day12(final List<String> lines) {
    paths = new HashMap<>();
    for (final String line : lines) {
      final String[] parts = line.split("-");
      final var nextSteps1 = paths.getOrDefault(parts[0], new ArrayList<>());
      nextSteps1.add(parts[1]);
      paths.put(parts[0], nextSteps1);
      final var nextSteps2 = paths.getOrDefault(parts[1], new ArrayList<>());
      nextSteps2.add(parts[0]);
      paths.put(parts[1], nextSteps2);
    }
    path = new ArrayDeque<>();
  }

  final int distinctPaths() {
    path.add(START);
    return pathfinder(false);
  }

  final int distinctPathsWithTime() {
    path.add(START);
    return pathfinder(true);
  }

  final int pathfinder(final boolean hasTime) {
    var distinctPaths = 0;
    for (final var nextStep : paths.get(path.peekLast())) {
      switch (nextStep) {
        case END:
          distinctPaths++;
          break;
        case START:
          break;
        default:
          if (nextStep.equals(nextStep.toLowerCase()) && path.contains(nextStep) && !hasTime) {
            break;
          }
          var stillHasTime =
              hasTime && !(nextStep.equals(nextStep.toLowerCase()) && path.contains(nextStep));
          path.add(nextStep);
          distinctPaths += pathfinder(stillHasTime);
          path.removeLast();
      }
    }
    return distinctPaths;
  }
}
