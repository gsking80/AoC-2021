package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day02Test {

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day02/input.txt"))
            .toURI()));
    final Day02 day02 = new Day02(lines);
    Assertions.assertThat(day02.calculatePosition()).isEqualTo(2102357);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day02/input.txt"))
            .toURI()));
    final Day02 day02 = new Day02(lines);
    Assertions.assertThat(day02.calculatePosition2()).isEqualTo(2101031224);
  }
}