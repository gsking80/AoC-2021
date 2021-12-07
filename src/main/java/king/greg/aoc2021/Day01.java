package king.greg.aoc2021;

import java.util.List;
import java.util.stream.Collectors;

public class Day01 {

  final List<Integer> numbers;

  public Day01(final List<String> input) {
    numbers = input.stream().map(Integer::parseInt).collect(Collectors.toList());
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
