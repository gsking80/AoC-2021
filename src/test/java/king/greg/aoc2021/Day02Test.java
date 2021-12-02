package king.greg.aoc2021;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day02Test {

  @Test
  public void testSolution1() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day02/input.txt")).getPath());
    final Day02 day02 = new Day02(fileReader);
    Assertions.assertThat(day02.calculatePosition()).isEqualTo(2102357);

  }

  @Test
  public void testSolution2() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day02/input.txt")).getPath());
    final Day02 day02 = new Day02(fileReader);
    Assertions.assertThat(day02.calculatePosition2()).isEqualTo(2101031224);

  }

}