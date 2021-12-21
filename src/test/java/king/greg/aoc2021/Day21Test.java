package king.greg.aoc2021;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day21Test {

  @Test
  public void testSample1() {
    Assertions.assertThat(Day21.finalScore(4, 8)).isEqualTo(739785);
  }

  @Test
  public void testSolution1() {
    Assertions.assertThat(Day21.finalScore(8, 1)).isEqualTo(518418L);
  }

  @Test
  public void testSample2() {
    Assertions.assertThat(Day21.diracWins(4, 8)).isEqualTo(444356092776315L);
  }

  @Test
  public void testSolution2() {
    Assertions.assertThat(Day21.diracWins(8, 1)).isEqualTo(116741133558209L);
  }
}