package king.greg.aoc2021;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day13Test {

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day13/test.txt"))
            .toURI()));
    final Day13 day13 = new Day13(lines);
    Assertions.assertThat(day13.pointsAfterFolds(1)).isEqualTo(17);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day13/input.txt"))
            .toURI()));
    final Day13 day13 = new Day13(lines);
    Assertions.assertThat(day13.pointsAfterFolds(1)).isEqualTo(759);
  }

  @Test
  public void testSample2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day13/test.txt"))
            .toURI()));
    final Day13 day13 = new Day13(lines);
    Assertions.assertThat(day13.printCode()).isEqualTo("\n"
        + "█████\n"
        + "█   █\n"
        + "█   █\n"
        + "█   █\n"
        + "█████");
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day13/input.txt"))
            .toURI()));
    final Day13 day13 = new Day13(lines);
    Assertions.assertThat(day13.printCode()).isEqualTo("\n"
        + "█  █ ████  ██  ███  ████ █  █ ███  ███ \n"
        + "█  █ █    █  █ █  █    █ █ █  █  █ █  █\n"
        + "████ ███  █    █  █   █  ██   █  █ █  █\n"
        + "█  █ █    █    ███   █   █ █  ███  ███ \n"
        + "█  █ █    █  █ █ █  █    █ █  █    █ █ \n"
        + "█  █ ████  ██  █  █ ████ █  █ █    █  █");
  }
}