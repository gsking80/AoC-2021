package king.greg.aoc2021;

import java.util.Arrays;

public class Day07 {

  private Day07() {}

  public static long minFuelRequired(final String crabLocations, final boolean variableBurn) {
    final var crabs = Arrays.stream(crabLocations.split(",")).mapToInt(Integer::parseInt).toArray();
    var min = Arrays.stream(crabs).min().orElse(0);
    var max = Arrays.stream(crabs).max().orElse(0);
    var minFuelRequired = variableBurn ? fuelRequiredVariable(crabs,max) : fuelRequiredConstant(crabs,max);
    for (var x = min; x < max; x++) {
      var fuelRequired = variableBurn ? fuelRequiredVariable(crabs,x) :fuelRequiredConstant(crabs, x);
      if (fuelRequired < minFuelRequired) {
        minFuelRequired = fuelRequired;
      }
    }
    return minFuelRequired;
  }

  private static long fuelRequiredConstant(final int[] crabs, final int target) {
    var fuel = 0;
    for (final int crab : crabs) {
      fuel += Math.abs(crab - target);
    }
    return fuel;
  }

  private static long fuelRequiredVariable(final int[] crabs, final int target) {
    var fuel = 0;
    for (final int crab : crabs) {
      var absValue = Math.abs(crab - target);
      fuel += (absValue*(absValue + 1))/2;
    }
    return fuel;
  }

}
