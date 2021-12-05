package king.greg.aoc2021;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day05Test {

  @Test
  public void testSample1() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/test.txt")).getPath());
    final Day05 day05 = new Day05(fileReader);
    Assertions.assertThat(day05.countOrthogonalOverlaps()).isEqualTo(5);

  }

  @Test
  public void testSolution1() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/input.txt")).getPath());
    final Day05 day05 = new Day05(fileReader);
    Assertions.assertThat(day05.countOrthogonalOverlaps()).isEqualTo(8622);

  }

  @Test
  public void testSample2() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/test.txt")).getPath());
    final Day05 day05 = new Day05(fileReader);
    Assertions.assertThat(day05.countAllOverlaps()).isEqualTo(12);

  }

  @Test
  public void testSolution2() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/input.txt")).getPath());
    final Day05 day05 = new Day05(fileReader);
    Assertions.assertThat(day05.countAllOverlaps()).isEqualTo(22037);

  }

}