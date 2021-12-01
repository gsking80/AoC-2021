package king.greg.aoc2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day01 {

  final List<Integer> numbers;

  public Day01(FileReader fileReader) {
    try {
      final BufferedReader buf = new BufferedReader(fileReader);
      numbers = new ArrayList<>();

      while (true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        } else {
          numbers.add(Integer.valueOf(lineJustFetched));
        }
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public int countIncreases() {
    var increases = 0;
    for (int i = 1; i < numbers.size(); i++) {
      if (numbers.get(i) > numbers.get(i - 1)) {
        increases++;
      }
    }
    return increases;
  }

  public int countSumIncreases() {
    var increases = 0;
    for (int i = 3; i < numbers.size(); i++) {
      if (numbers.get(i) > numbers.get(i - 3)) {
        increases++;
      }
    }
    return increases;
  }
}
