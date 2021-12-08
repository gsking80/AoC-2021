package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day08Test {

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/test.txt"))
            .toURI()));
    Assertions.assertThat(Day08.countOneFourSevenEight(lines)).isEqualTo(26);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/input.txt"))
            .toURI()));
    Assertions.assertThat(Day08.countOneFourSevenEight(lines)).isEqualTo(409);
  }

  @Test
  public void testSingle2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/single.txt"))
            .toURI()));
    Assertions.assertThat(Day08.sum(lines)).isEqualTo(5353);
  }

  @Test
  public void testSample2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/test.txt"))
            .toURI()));
    Assertions.assertThat(Day08.sum(lines)).isEqualTo(61229);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/input.txt"))
            .toURI()));
    Assertions.assertThat(Day08.sum(lines)).isEqualTo(1024649);
  }
}