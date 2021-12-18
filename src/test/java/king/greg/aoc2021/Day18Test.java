package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day18Test {

  @Test
  public void testReduce() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day18/testReduce.txt"))
            .toURI()));
    final var day18 = new Day18(lines);
    Assertions.assertThat(day18.magnitude()).isEqualTo(1384);
  }

  @Test
  public void testSmallSamples1a() {
    final Day18 day18 = new Day18(List.of("[[1,2],[[3,4],5]]"));
    Assertions.assertThat(day18.magnitude()).isEqualTo(143);
  }

  @Test
  public void testSmallSamples1b() {
    final Day18 day18 = new Day18(List.of("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"));
    Assertions.assertThat(day18.magnitude()).isEqualTo(1384);
  }

  @Test
  public void testSmallSamples1c() {
    final Day18 day18 = new Day18(List.of("[[[[1,1],[2,2]],[3,3]],[4,4]]"));
    Assertions.assertThat(day18.magnitude()).isEqualTo(445);
  }

  @Test
  public void testSmallSamples1d() {
    final Day18 day18 = new Day18(List.of("[[[[3,0],[5,3]],[4,4]],[5,5]]"));
    Assertions.assertThat(day18.magnitude()).isEqualTo(791);
  }

  @Test
  public void testSmallSamples1e() {
    final Day18 day18 = new Day18(List.of("[[[[5,0],[7,4]],[5,5]],[6,6]]"));
    Assertions.assertThat(day18.magnitude()).isEqualTo(1137);
  }

  @Test
  public void testSmallSamples1f() {
    final Day18 day18 = new Day18(List.of("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"));
    Assertions.assertThat(day18.magnitude()).isEqualTo(3488);
  }

  @Test
  public void testSmallSample1g() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day18/test1g.txt"))
            .toURI()));
    final var day18 = new Day18(lines);
    Assertions.assertThat(day18.magnitude()).isEqualTo(445);
  }

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day18/test.txt"))
            .toURI()));
    final var day18 = new Day18(lines);
    Assertions.assertThat(day18.magnitude()).isEqualTo(4140);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day18/input.txt"))
            .toURI()));
    final var day18 = new Day18(lines);
    Assertions.assertThat(day18.magnitude()).isEqualTo(3551);
  }

  @Test
  public void testSample2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day18/test.txt"))
            .toURI()));
    final var day18 = new Day18(lines);
    Assertions.assertThat(day18.largestMagnitudePair()).isEqualTo(3993);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day18/input.txt"))
            .toURI()));
    final var day18 = new Day18(lines);
    Assertions.assertThat(day18.largestMagnitudePair()).isEqualTo(4555);
  }
}