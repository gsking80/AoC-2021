package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day05Test {

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/test.txt")).toURI()));
    final Day05 day05 = new Day05(lines);
    Assertions.assertThat(day05.countOrthogonalOverlaps()).isEqualTo(5);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/input.txt"))
            .toURI()));
    final Day05 day05 = new Day05(lines);
    Assertions.assertThat(day05.countOrthogonalOverlaps()).isEqualTo(8622);
  }

  @Test
  public void testSample2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/test.txt")).toURI()));
    final Day05 day05 = new Day05(lines);
    Assertions.assertThat(day05.countAllOverlaps()).isEqualTo(12);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/input.txt"))
            .toURI()));
    final Day05 day05 = new Day05(lines);
    Assertions.assertThat(day05.countAllOverlaps()).isEqualTo(22037);
  }
}