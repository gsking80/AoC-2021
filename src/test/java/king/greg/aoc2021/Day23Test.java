package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day23Test {

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day23/test.txt"))
            .toURI()));
    final var day23 = new Day23(lines);
    Assertions.assertThat(day23.minimumCostToArrange()).isEqualTo(12521);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day23/input.txt"))
            .toURI()));
    final var day23 = new Day23(lines);
    Assertions.assertThat(day23.minimumCostToArrange()).isEqualTo(15358);
  }

  @Test
  public void testSample2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day23/test2.txt"))
            .toURI()));
    final var day23 = new Day23(lines);
    Assertions.assertThat(day23.minimumCostToArrange()).isEqualTo(44169);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day23/input2.txt"))
            .toURI()));
    final var day23 = new Day23(lines);
    Assertions.assertThat(day23.minimumCostToArrange()).isEqualTo(51436);
  }
}