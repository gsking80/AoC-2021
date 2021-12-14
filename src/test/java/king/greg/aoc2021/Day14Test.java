package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day14Test {

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day14/test.txt"))
            .toURI()));
    Assertions.assertThat(Day14.polymerValue(lines, 10)).isEqualTo(1588);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day14/input.txt"))
            .toURI()));
    Assertions.assertThat(Day14.polymerValue(lines, 10)).isEqualTo(3284);
  }

  @Test
  public void testSample2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day14/test.txt"))
            .toURI()));
    Assertions.assertThat(Day14.polymerValue(lines, 40)).isEqualTo(2188189693529L);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day14/input.txt"))
            .toURI()));
    Assertions.assertThat(Day14.polymerValue(lines, 40)).isEqualTo(4302675529689L);
  }
}