package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day09Test {

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/test.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.sumRiskLowPoints()).isEqualTo(15);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/input.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.sumRiskLowPoints()).isEqualTo(588);
  }

  @Test
  public void testSample2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/test.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.productThreeLargestBasins()).isEqualTo(1134);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/input.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.productThreeLargestBasins()).isEqualTo(964712);
  }
}