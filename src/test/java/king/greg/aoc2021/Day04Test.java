package king.greg.aoc2021;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day04Test {

  @Test
  public void testSample1() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day04/test.txt")).getPath());
    final Day04 day04 = new Day04(fileReader);
    Assertions.assertThat(day04.calculateWinningScore(false)).isEqualTo(4512);

  }

  @Test
  public void testSolution1() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day04/input.txt")).getPath());
    final Day04 day04 = new Day04(fileReader);
    Assertions.assertThat(day04.calculateWinningScore(false)).isEqualTo(2496);

  }

  @Test
  public void testSample2() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day04/test.txt")).getPath());
    final Day04 day04 = new Day04(fileReader);
    Assertions.assertThat(day04.calculateWinningScore(true)).isEqualTo(1924);

  }

  @Test
  public void testSolution2() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day04/input.txt")).getPath());
    final Day04 day04 = new Day04(fileReader);
    Assertions.assertThat(day04.calculateWinningScore(true)).isEqualTo(25925);

  }
  
}