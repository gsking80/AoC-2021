package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day20Test {

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day20/test.txt"))
            .toURI()));
    final var day20 = new Day20(lines);
    Assertions.assertThat(day20.litPixels(2)).isEqualTo(35);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day20/input.txt"))
            .toURI()));
    final var day20 = new Day20(lines);
    Assertions.assertThat(day20.litPixels(2)).isEqualTo(5622);
  }

  @Test
  public void testSample2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day20/test.txt"))
            .toURI()));
    final var day20 = new Day20(lines);
    Assertions.assertThat(day20.litPixels(50)).isEqualTo(3351);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day20/input.txt"))
            .toURI()));
    final var day20 = new Day20(lines);
    Assertions.assertThat(day20.litPixels(50)).isEqualTo(20395);
  }
}