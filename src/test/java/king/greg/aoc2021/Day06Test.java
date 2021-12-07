package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day06Test {

  @Test
  public void testSample1() {
    Assertions.assertThat(Day06.calculateNumberOfFish("3,4,3,1,2", 80)).isEqualTo(5934);
  }

  @Test
  public void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/input.txt"))
            .toURI()));
    Assertions.assertThat(Day06.calculateNumberOfFish(lines.get(0), 80)).isEqualTo(380758);
  }

  @Test
  public void testSample2() {
    Assertions.assertThat(Day06.calculateNumberOfFish("3,4,3,1,2", 256)).isEqualTo(26984457539L);
  }

  @Test
  public void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/input.txt"))
            .toURI()));
    Assertions.assertThat(Day06.calculateNumberOfFish(lines.get(0), 256)).isEqualTo(1710623015163L);
  }
}