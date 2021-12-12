package king.greg.aoc2021;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {

  public static final String START = "start";
  public static final String END = "end";
  public static final String TRUE = "true";
  final Map<String, List<String>> paths;

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
  }

  final int distinctPaths() {
    final var fullPaths = new ArrayDeque<List<String>>();
    final var seedList = new ArrayList<String>();
    seedList.add(START);
    fullPaths.add(seedList);
    var distinctPaths = 0;
    while (!fullPaths.isEmpty()) {
      final var currentPath = fullPaths.remove();
      final var currentPosition = currentPath.get(currentPath.size() - 1);
      final var nextSteps = paths.get(currentPosition);
      for (final var nextStep : nextSteps) {
        switch (nextStep) {
          case END:
            distinctPaths++;
            break;
          case START:
            break;
          default:
            if (nextStep.equals(nextStep.toLowerCase()) && currentPath.contains(nextStep)) {
              break;
            }
            final var newPath = new ArrayList<>(currentPath);
            newPath.add(nextStep);
            fullPaths.add(newPath);
        }
      }
    }
    return distinctPaths;
  }

  final int distinctPathsWithTime() {
    final var fullPaths = new ArrayDeque<List<String>>();
    final var seedList = new ArrayList<String>();
    seedList.add(TRUE);
    seedList.add(START);
    fullPaths.add(seedList);
    var distinctPaths = 0;
    while (!fullPaths.isEmpty()) {
      final var currentPath = fullPaths.remove();
      final var currentPosition = currentPath.get(currentPath.size() - 1);
      final var nextSteps = paths.get(currentPosition);
      for (final var nextStep : nextSteps) {
        switch (nextStep) {
          case END:
            distinctPaths++;
            break;
          case START:
            break;
          default:
            if (nextStep.equals(nextStep.toLowerCase()) && currentPath.contains(nextStep) && !TRUE
                .equals(currentPath.get(0))) {
              break;
            }
            final var newPath = new ArrayList<>(currentPath);
            newPath.add(nextStep);
            if ((nextStep.equals(nextStep.toLowerCase()) && currentPath.contains(nextStep))) {
              newPath.set(0, "false");
            }
            fullPaths.add(newPath);
        }
      }
    }
    return distinctPaths;
  }
}
