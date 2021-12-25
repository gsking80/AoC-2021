package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day25Test {

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day25/test.txt"))
            .toURI()));
    final var day25 = new Day25(lines);
    Assertions.assertThat(day25.deadlockStep()).isEqualTo(58);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day25/input.txt"))
            .toURI()));
    final var day25 = new Day25(lines);
    Assertions.assertThat(day25.deadlockStep()).isEqualTo(509);
  }
}