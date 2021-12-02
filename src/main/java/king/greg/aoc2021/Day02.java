package king.greg.aoc2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day02 {

  final List<String> course;

  public Day02(FileReader fileReader) {
    try {
      final BufferedReader buf = new BufferedReader(fileReader);
      course = new ArrayList<>();

      while (true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        } else {
          course.add(lineJustFetched);
        }
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
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
      }
    }
    return horizontal * depth;
  }

}
