package king.greg.aoc2021;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day03Test {

  @Test
  public void testSample1() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/test.txt")).getPath());
    final Day03 day03 = new Day03(fileReader);
    Assertions.assertThat(day03.calculatePowerConsumption()).isEqualTo(198);

  }

  @Test
  public void testSample2() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/test.txt")).getPath());
    final Day03 day03 = new Day03(fileReader);
    Assertions.assertThat(day03.calculateLifeSupport()).isEqualTo(230);

  }

  @Test
  public void testSolution1() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/input.txt")).getPath());
    final Day03 day03 = new Day03(fileReader);
    Assertions.assertThat(day03.calculatePowerConsumption()).isEqualTo(4103154);

  }

  @Test
  public void testSolution2() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/input.txt")).getPath());
    final Day03 day03 = new Day03(fileReader);
    Assertions.assertThat(day03.calculateLifeSupport()).isEqualTo(4245351);

  }
  
}