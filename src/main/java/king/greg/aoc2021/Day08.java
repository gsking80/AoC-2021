package king.greg.aoc2021;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Day08 {

  private Day08() {
  }

  public static int countOneFourSevenEight(final List<String> lines) {
    var count = 0;
    for (final var line : lines) {
      count += Arrays.stream(line.split(" \\| ")[1].split(" ")).filter(
              p -> (p.length() == 2) || (p.length() == 3) || (p.length() == 4) || (p.length() == 7))
          .count();
    }
    return count;
  }

  public static int sum(final List<String> lines) {
    var sum = 0;
    for (final var line : lines) {
      var parts = line.split(" \\| ");
      var signals = Arrays.stream(parts[0].split(" "))
          .map(p -> p.chars().mapToObj(c -> (char) c).collect(
              Collectors.toList())).collect(Collectors.toList());

      var decoder = decoder(signals);
      var outputValues = Arrays.stream(parts[1].split(" ")).map(
          p -> p.chars().mapToObj(c -> (char) c).sorted().collect(
              Collector.of(StringBuilder::new, StringBuilder::append, StringBuilder::append,
                  StringBuilder::toString))).collect(Collectors.toList());
      var output = 0;
      for (final var digit : outputValues) {
        output *= 10;
        output += decoder.get(digit);
      }
      sum += output;
    }
    return sum;
  }

  private static Map<String, Integer> decoder(final List<List<Character>> signals) {
    var one = signals.stream().filter(p -> p.size() == 2).findFirst()
        .orElse(Collections.emptyList());
    var four = signals.stream().filter(p -> p.size() == 4).findFirst()
        .orElse(Collections.emptyList());
    var seven = signals.stream().filter(p -> p.size() == 3).findFirst()
        .orElse(Collections.emptyList());
    var eight = signals.stream().filter(p -> p.size() == 7).findFirst()
        .orElse(Collections.emptyList());
    var three = signals.stream().filter(p -> (p.size() == 5) && p.containsAll(one)).findFirst()
        .orElse(Collections.emptyList());
    var nine = signals.stream().filter(p -> (p.size() == 6) && p.containsAll(four)).findFirst()
        .orElse(Collections.emptyList());
    var zero = signals.stream()
        .filter(p -> (p.size() == 6) && !p.containsAll(nine) && p.containsAll(one)).findFirst()
        .orElse(Collections.emptyList());
    var six = signals.stream()
        .filter(p -> (p.size() == 6) && !p.containsAll(nine) && !p.containsAll(zero)).findFirst()
        .orElse(Collections.emptyList());
    var five = signals.stream()
        .filter(p -> (p.size() == 5) && !p.containsAll(three) && nine.containsAll(p)).findFirst()
        .orElse(Collections.emptyList());
    var two = signals.stream()
        .filter(p -> (p.size() == 5) && !p.containsAll(three) && !p.containsAll(five)).findFirst()
        .orElse(Collections.emptyList());
    final var map = new HashMap<String, Integer>();

    map.put(sorted(zero), 0);
    map.put(sorted(one), 1);
    map.put(sorted(two), 2);
    map.put(sorted(three), 3);
    map.put(sorted(four), 4);
    map.put(sorted(five), 5);
    map.put(sorted(six), 6);
    map.put(sorted(seven), 7);
    map.put(sorted(eight), 8);
    map.put(sorted(nine), 9);
    return map;
  }

  private static String sorted(final List<Character> characters) {
    return characters.stream().sorted()
        .collect(Collector.of(StringBuilder::new, StringBuilder::append, StringBuilder::append,
            StringBuilder::toString));
  }
}
