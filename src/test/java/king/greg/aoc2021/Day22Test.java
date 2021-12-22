package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day22Test {

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day22/test.txt"))
            .toURI()));
    final var day22 = new Day22(lines);
    Assertions.assertThat(day22.initializedCubes()).isEqualTo(590784);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day22/input.txt"))
            .toURI()));
    final var day22 = new Day22(lines);
    Assertions.assertThat(day22.initializedCubes()).isEqualTo(583636);
  }

  @Test
  public void testSample2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day22/test2.txt"))
            .toURI()));
    final var day22 = new Day22(lines);
    Assertions.assertThat(day22.rebootedCubes()).isEqualTo(2758514936282235L);
  }

  @Test
  public void testSample2a() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day22/testa.txt"))
            .toURI()));
    final var day22 = new Day22(lines);
    Assertions.assertThat(day22.rebootedCubes()).isEqualTo(39L);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day22/input.txt"))
            .toURI()));
    final var day22 = new Day22(lines);
    Assertions.assertThat(day22.rebootedCubes()).isEqualTo(1294137045134837L);
  }
  
}