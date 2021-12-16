package king.greg.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class Day16Test {

  @Test
  public void testSample1() throws IOException, URISyntaxException {
    final SoftAssertions softly = new SoftAssertions();
    softly.assertThat(Day16.versionSum("D2FE28")).isEqualTo(6);
    softly.assertThat(Day16.versionSum("38006F45291200")).isEqualTo(9);
    softly.assertThat(Day16.versionSum("8A004A801A8002F478")).isEqualTo(16);
    softly.assertThat(Day16.versionSum("620080001611562C8802118E34")).isEqualTo(12);
    softly.assertThat(Day16.versionSum("C0015000016115A2E0802F182340")).isEqualTo(23);
    softly.assertThat(Day16.versionSum("A0016C880162017C3686B18A3D4780")).isEqualTo(31);
    softly.assertAll();
  }

  @Test
  public void testSolution1() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day16/input.txt"))
            .toURI()));
    Assertions.assertThat(Day16.versionSum(lines.get(0))).isEqualTo(854);
  }

  @Test
  public void testSample2() throws IOException, URISyntaxException {
    final SoftAssertions softly = new SoftAssertions();
    softly.assertThat(Day16.value("C200B40A82")).isEqualTo(3);
    softly.assertThat(Day16.value("04005AC33890")).isEqualTo(54);
    softly.assertThat(Day16.value("880086C3E88112")).isEqualTo(7);
    softly.assertThat(Day16.value("CE00C43D881120")).isEqualTo(9);
    softly.assertThat(Day16.value("D8005AC2A8F0")).isEqualTo(1);
    softly.assertThat(Day16.value("F600BC2D8F")).isEqualTo(0);
    softly.assertThat(Day16.value("9C005AC2F8F0")).isEqualTo(0);
    softly.assertThat(Day16.value("9C0141080250320F1802104A08")).isEqualTo(1);
    softly.assertAll();
  }

  @Test
  public void testSolution2() throws IOException, URISyntaxException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day16/input.txt"))
            .toURI()));
    Assertions.assertThat(Day16.value(lines.get(0))).isEqualTo(186189840660L);
  }
}