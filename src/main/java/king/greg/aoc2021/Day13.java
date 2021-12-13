package king.greg.aoc2021;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day13 {

  Set<Point> paper;
  final List<String> instructions;

  public Day13(final List<String> lines) {
    paper = new HashSet<>();
    instructions = new ArrayList<>();
    for (final String currentLine : lines) {
      if (currentLine.startsWith("fold")) {
        instructions.add(currentLine);
      } else if (!currentLine.isBlank()) {
        final var pieces = currentLine.split(",");
        paper.add(new Point(Integer.parseInt(pieces[0]), Integer.parseInt(pieces[1])));
      }
    }
  }

  public int pointsAfterFolds(final int folds) {
    for (var i = 0; i < folds; i++) {
      var currentInstruction = instructions.get(i);
      var pieces = currentInstruction.split(" ")[2].split("=");
      if ("x".equals(pieces[0])) {
        foldLeft(Integer.parseInt(pieces[1]));
      } else {
        foldUp(Integer.parseInt(pieces[1]));
      }
    }
    return paper.size();
  }

  public void printCode() {
    pointsAfterFolds(instructions.size());
    final var maxX = paper.stream().max(Comparator.comparing(Point::getX))
        .orElse(new Point(0, 0)).x;
    final var maxY = paper.stream().max(Comparator.comparing(Point::getY))
        .orElse(new Point(0, 0)).y;
    final var sb = new StringBuilder();
    for (var y = 0; y <= maxY; y++) {
      sb.append('\n');
      for (var x = 0; x <= maxX; x++) {
        sb.append(paper.contains(new Point(x, y)) ? '█' : ' ');
      }
    }
    System.out.println(sb.toString());
  }

  private void foldLeft(final int foldLine) {
    final var newPaper = new HashSet<Point>();
    for (final var point : paper) {
      newPaper.add(new Point(point.x < foldLine ? point.x : (2 * foldLine) - point.x, point.y));
    }
    paper = newPaper;
  }

  private void foldUp(final int foldLine) {
    final var newPaper = new HashSet<Point>();
    for (final var point : paper) {
      newPaper.add(new Point(point.x, point.y < foldLine ? point.y : (2 * foldLine) - point.y));
    }
    paper = newPaper;
  }
}
