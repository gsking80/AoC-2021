package king.greg.aoc2021;

import java.util.HashMap;
import java.util.List;

public class Day14 {

  private Day14() {
  }

  public static long polymerValue(final List<String> lines, final int reactions) {
    var counts = new HashMap<String, Long>();
    final var frequencies = new HashMap<Character, Long>();
    final var template = lines.get(0);
    for (var i = 0; i < template.length() - 1; i++) {
      var segment = template.substring(i, i + 2);
      counts.put(segment, counts.getOrDefault(segment, 0L) + 1);
    }
    frequencies.put(template.charAt(0), 1L);
    frequencies.put(template.charAt(template.length() - 1), 1L);
    final var rules = new HashMap<String, Character>();
    for (var i = 2; i < lines.size(); i++) {
      var parts = lines.get(i).split(" -> ");
      rules.put(parts[0], parts[1].charAt(0));
    }
    for (var i = 0; i < reactions; i++) {
      var nextCounts = new HashMap<String, Long>();
      for (final var entry : counts.entrySet()) {
        var middle = rules.get(entry.getKey());
        var start = String.valueOf(entry.getKey().charAt(0)) + middle;
        var end = String.valueOf(middle) + entry.getKey().charAt(1);
        nextCounts.put(start, nextCounts.getOrDefault(start, 0L) + entry.getValue());
        nextCounts.put(end, nextCounts.getOrDefault(end, 0L) + entry.getValue());
      }
      counts = nextCounts;
    }
    for (final var entry : counts.entrySet()) {
      frequencies.put(entry.getKey().charAt(0),
          frequencies.getOrDefault(entry.getKey().charAt(0), 0L) + entry.getValue());
      frequencies.put(entry.getKey().charAt(1),
          frequencies.getOrDefault(entry.getKey().charAt(1), 0L) + entry.getValue());
    }
    return (frequencies.values().stream().mapToLong(l -> l).max().orElse(0L)
        - frequencies.values().stream().mapToLong(l -> l).min().orElse(0L)) / 2;
  }
}
