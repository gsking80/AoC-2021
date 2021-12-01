package king.greg.aoc2021;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day01Test {

  @Test
  public void testSolution1() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day01/input.txt").getPath());
    final Day01 day01 = new Day01(fileReader);
    Assertions.assertThat(day01.countIncreases()).isEqualTo(1226);

  }

  @Test
  public void testSolution2() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day01/input.txt").getPath());
    final Day01 day01 = new Day01(fileReader);
    Assertions.assertThat(day01.countSumIncreases()).isEqualTo(1252);

  }

}