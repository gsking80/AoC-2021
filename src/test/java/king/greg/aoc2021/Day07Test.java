package king.greg.aoc2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day07Test {

  @Test
  public void testSample1() {
    Assertions.assertThat(Day07.minFuelRequired("16,1,2,0,4,2,7,1,2,14", false)).isEqualTo(37);
  }

  @Test
  public void testSolution1() throws IOException {
    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/input.txt"))
            .getPath());
    final var buf = new BufferedReader(fileReader);
    final var lineJustFetched = buf.readLine();
    Assertions.assertThat(Day07.minFuelRequired(lineJustFetched, false)).isEqualTo(342534);
  }

  @Test
  public void testSample2() {
    Assertions.assertThat(Day07.minFuelRequired("16,1,2,0,4,2,7,1,2,14", true)).isEqualTo(168);
  }

  @Test
  public void testSolution2() throws IOException {
    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/input.txt"))
            .getPath());
    final var buf = new BufferedReader(fileReader);
    final var lineJustFetched = buf.readLine();
    Assertions.assertThat(Day07.minFuelRequired(lineJustFetched, true)).isEqualTo(94004208);
  }
}