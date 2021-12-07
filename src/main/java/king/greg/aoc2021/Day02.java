package king.greg.aoc2021;

import java.util.List;

public class Day02 {

  final List<String> course;

  public Day02(final List<String> course) {
    this.course = course;
  }

  public int calculatePosition() {
    var horizontal = 0;
    var depth = 0;

    for (final String instruction : course) {
      final String[] parts = instruction.split(" ");
      switch (parts[0]) {
        case "forward":
          horizontal += Integer.parseInt(parts[1]);
          break;
        case "down":
          depth += Integer.parseInt(parts[1]);
          break;
        case "up":
          depth -= Integer.parseInt(parts[1]);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + parts[0]);
      }
    }
    return horizontal * depth;
  }

  public int calculatePosition2() {
    var horizontal = 0;
    var depth = 0;
    var aim = 0;

    for (final String instruction : course) {
      final String[] parts = instruction.split(" ");
      switch (parts[0]) {
        case "forward":
          horizontal += Integer.parseInt(parts[1]);
          depth += (aim * Integer.parseInt(parts[1]));
          break;
        case "down":
          aim += Integer.parseInt(parts[1]);
          break;
        case "up":
          aim -= Integer.parseInt(parts[1]);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + parts[0]);
      }
    }
    return horizontal * depth;
  }
}
