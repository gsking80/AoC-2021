package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day24Test {

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day24/input.txt"))
            .toURI()));
    final var day24 = new Day24(lines);
    Assertions.assertThat(day24.largestValidNumber()).isEqualTo(99911993949684L);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day24/input.txt"))
            .toURI()));
    final var day24 = new Day24(lines);
    Assertions.assertThat(day24.smallestValidNumber()).isEqualTo(62911941716111L);
  }
}