package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day11Test {

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day11/test.txt"))
            .toURI()));
    final Day11 day11 = new Day11(lines);
    Assertions.assertThat(day11.flashes(100)).isEqualTo(1656);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day11/input.txt"))
            .toURI()));
    final Day11 day11 = new Day11(lines);
    Assertions.assertThat(day11.flashes(100)).isEqualTo(1603);
  }

  @Test
  public void testSample2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day11/test.txt"))
            .toURI()));
    final Day11 day11 = new Day11(lines);
    Assertions.assertThat(day11.simultaneousFlashes()).isEqualTo(195);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day11/input.txt"))
            .toURI()));
    final Day11 day11 = new Day11(lines);
    Assertions.assertThat(day11.simultaneousFlashes()).isEqualTo(222);
  }
}