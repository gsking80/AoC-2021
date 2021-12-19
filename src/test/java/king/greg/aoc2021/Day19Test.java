package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day19Test {

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day19/test.txt"))
            .toURI()));
    final var day19 = new Day19(lines);
    Assertions.assertThat(day19.beaconCount()).isEqualTo(79);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day19/input.txt"))
            .toURI()));
    final var day19 = new Day19(lines);
    Assertions.assertThat(day19.beaconCount()).isEqualTo(320);
  }

  @Test
  public void testSample2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day19/test.txt"))
            .toURI()));
    final var day19 = new Day19(lines);
    Assertions.assertThat(day19.scannerSpace()).isEqualTo(3621);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day19/input.txt"))
            .toURI()));
    final var day19 = new Day19(lines);
    Assertions.assertThat(day19.scannerSpace()).isEqualTo(9655);
  }
}