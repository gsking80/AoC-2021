package king.greg.aoc2021;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day11 {

  final Map<Point, Integer> octopusEnergy;
  final Set<Point> flashtopi;

  public Day11(final List<String> lines) {
    octopusEnergy = new HashMap<>();
    for (var y = 0; y < 10; y++) {
      for (var x = 0; x < 10; x++) {
        octopusEnergy.put(new Point(x, y), lines.get(y).charAt(x) - '0');
      }
    }
    flashtopi = new HashSet<>();
  }

  public int flashes(final int steps) {
    var flashes = 0;
    for (var step = 0; step < steps; step++) {
      flashtopi.clear();
      for (final var octopus : octopusEnergy.keySet()) {
        increase(octopus);
      }
      for (final Point flashtopus : flashtopi) {
        flashes++;
        octopusEnergy.put(flashtopus, 0);
      }
    }
    return flashes;
  }

  public int simultaneousFlashes() {
    var steps = 0;
    while (true) {
      flashtopi.clear();
      steps++;
      for (final var octopus : octopusEnergy.keySet()) {
        increase(octopus);
      }
      if (flashtopi.size() == 100) {
        return steps;
      }
      for (final Point flashtopus : flashtopi) {
        octopusEnergy.put(flashtopus, 0);
      }
    }
  }

  private void increase(final Point octopus) {
    if (!octopusEnergy.containsKey(octopus)) {
      return;
    }
    var energy = octopusEnergy.get(octopus) + 1;
    octopusEnergy.put(octopus, energy);
    if (energy > 9) {
      flash(octopus);
    }
  }

  private void flash(final Point flashtopus) {
    if (flashtopi.contains(flashtopus)) {
      return;
    }
    flashtopi.add(flashtopus);
    for (var deltaX = -1; deltaX < 2; deltaX++) {
      for (var deltaY = -1; deltaY < 2; deltaY++) {
        if (deltaX == 0 && deltaY == 0) {
          continue;
        }
        increase(new Point(flashtopus.x + deltaX, flashtopus.y + deltaY));
      }
    }
  }
}
