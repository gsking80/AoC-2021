package king.greg.aoc2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day03 {

  final List<String> input;

  public Day03(FileReader fileReader) {
    try {
      final BufferedReader buf = new BufferedReader(fileReader);
      input = new ArrayList<>();

      while (true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        } else {
          input.add(lineJustFetched);
        }
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public int calculatePowerConsumption() {
    final int[] counts = new int[input.get(0).length()];
    for (final String line : input) {
      for (var i = 0; i < line.length(); i++) {
        if (line.charAt(i) == '1') {
          counts[i]++;
        }
      }
    }
    final StringBuilder gammaBuilder = new StringBuilder();
    final StringBuilder epsilonBuilder = new StringBuilder();
    for (final int count : counts) {
      gammaBuilder.append((count > input.size() / 2) ? '1' : '0');
      epsilonBuilder.append((count > input.size() / 2) ? '0' : '1');
    }
    return Integer.parseInt(gammaBuilder.toString(), 2) * Integer
        .parseInt(epsilonBuilder.toString(), 2);
  }

  public int calculateLifeSupport() {
    final List<String> oxygens = new ArrayList<>(input);
    for (var i = 0; i < input.get(0).length(); i++) {
      if (oxygens.size() > 1) {
        var count = 0;
        for (final String line : oxygens) {
          if (line.charAt(i) == '1') {
            count++;
          }
        }
        var finalCount = count;
        var finalI = i;
        oxygens
            .removeIf(p -> p.charAt(finalI) == (((finalCount * 2) >= oxygens.size()) ? '0' : '1'));
      }
      if (input.size() > 1) {
        var count = 0;
        for (final String line : input) {
          if (line.charAt(i) == '1') {
            count++;
          }
        }
        var finalCount = count;
        var finalI = i;
        input.removeIf(p -> p.charAt(finalI) == (((finalCount * 2) >= input.size()) ? '1' : '0'));
      }
    }
    return Integer.parseInt(oxygens.get(0), 2) * Integer.parseInt(input.get(0), 2);
  }
}
