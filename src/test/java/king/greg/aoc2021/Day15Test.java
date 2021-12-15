package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day15Test {

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day15/test.txt"))
            .toURI()));
    final var day15 = new Day15(lines);
    Assertions.assertThat(day15.lowestRisk()).isEqualTo(40);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day15/input.txt"))
            .toURI()));
    final var day15 = new Day15(lines);
    Assertions.assertThat(day15.lowestRisk()).isEqualTo(527);
  }

  @Test
  public void testSample2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day15/test.txt"))
            .toURI()));
    final var day15 = new Day15(lines, 5);
    Assertions.assertThat(day15.lowestRisk()).isEqualTo(315);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day15/input.txt"))
            .toURI()));
    final var day15 = new Day15(lines, 5);
    Assertions.assertThat(day15.lowestRisk()).isEqualTo(2887);
  }
}