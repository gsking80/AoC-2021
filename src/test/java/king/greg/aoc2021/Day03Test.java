package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day03Test {

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/test.txt")).toURI()));
    final Day03 day03 = new Day03(lines);
    Assertions.assertThat(day03.calculatePowerConsumption()).isEqualTo(198);
  }

  @Test
  public void testSample2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/test.txt")).toURI()));
    final Day03 day03 = new Day03(lines);
    Assertions.assertThat(day03.calculateLifeSupport()).isEqualTo(230);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/input.txt"))
            .toURI()));
    final Day03 day03 = new Day03(lines);
    Assertions.assertThat(day03.calculatePowerConsumption()).isEqualTo(4103154);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/input.txt"))
            .toURI()));
    final Day03 day03 = new Day03(lines);
    Assertions.assertThat(day03.calculateLifeSupport()).isEqualTo(4245351);
  }
}