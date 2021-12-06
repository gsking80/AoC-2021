package king.greg.aoc2021;

import java.util.Arrays;

public class Day06 {

  private Day06() {}

  public static long calculateNumberOfFish(final String start, final int days) {

    var numbers = start.split(",");
    var fish = new long[9];
    for (var number : numbers) {
      fish[Integer.parseInt(number)]++;
    }
    for (var i = 0; i < days; i++) {
      var nextfish = new long[9];
      System.arraycopy(fish, 1, nextfish, 0, 8);
      nextfish[8] = fish[0];
      nextfish[6] += fish[0];
      fish = nextfish;
    }
    return Arrays.stream(fish).sum();
  }

}
