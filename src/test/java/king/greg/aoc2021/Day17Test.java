package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day17Test {

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    Assertions.assertThat(Day17.maximumY(20, 30, -10, -5)).isEqualTo(45);
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    Assertions.assertThat(Day17.maximumY(88, 125, -157, -103)).isEqualTo(12246);
  }

  @Test
  public void testSample2() throws IOException, URISyntaxException {
    Assertions.assertThat(Day17.possibleVelocities(20, 30, -10, -5)).isEqualTo(112);
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    Assertions.assertThat(Day17.possibleVelocities(88, 125, -157, -103)).isEqualTo(3528);
  }
}