package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day07Test {

  @Test
  public void testSample1() {
    Assertions.assertThat(Day07.minFuelRequired("16,1,2,0,4,2,7,1,2,14", false)).isEqualTo(37);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/input.txt"))
            .toURI()));
    Assertions.assertThat(Day07.minFuelRequired(lines.get(0), false)).isEqualTo(342534);
  }

  @Test
  public void testSample2() {
    Assertions.assertThat(Day07.minFuelRequired("16,1,2,0,4,2,7,1,2,14", true)).isEqualTo(168);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/input.txt"))
            .toURI()));
    Assertions.assertThat(Day07.minFuelRequired(lines.get(0), true)).isEqualTo(94004208);
  }
}