package king.greg.aoc2021;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Day22 {

  static final Pattern stepPattern = Pattern.compile(
      "^(\\w*) x=(-?\\d*)\\.\\.(-?\\d*),y=(-?\\d*)\\.\\.(-?\\d*),z=(-?\\d*)\\.\\.(-?\\d*)");
  final List<String> lines;

  public Day22(final List<String> lines) {
    this.lines = lines;
  }

  public int initializedCubes() {
    final var cubes = new HashSet<Point3d>();
    for (final var line : lines) {
      final var matcher = stepPattern.matcher(line);
      if (!matcher.find()) {
        break;
      }
      final var command = matcher.group(1);
      final var minX = Long.parseLong(matcher.group(2));
      final var maxX = Long.parseLong(matcher.group(3));
      final var minY = Long.parseLong(matcher.group(4));
      final var maxY = Long.parseLong(matcher.group(5));
      final var minZ = Long.parseLong(matcher.group(6));
      final var maxZ = Long.parseLong(matcher.group(7));
      final var cuboid = new HashSet<Point3d>();
      for (var x = Math.max(minX, -50); x <= Math.min(maxX, 50); x++) {
        for (var y = Math.max(minY, -50); y <= Math.min(maxY, 50); y++) {
          for (var z = Math.max(minZ, -50); z <= Math.min(maxZ, 50); z++) {
            cuboid.add(new Point3d(x, y, z));
          }
        }
      }
      if ("on".equals(command)) {
        cubes.addAll(cuboid);
      } else {
        cubes.removeAll(cuboid);
      }
    }
    return cubes.size();
  }

  public long rebootedCubes() {
    var cuboids = new ArrayList<Cuboid>();
    for (final var line : lines) {
      final var matcher = stepPattern.matcher(line);
      if (!matcher.find()) {
        break;
      }
      final var command = matcher.group(1);
      final var minX = Long.parseLong(matcher.group(2));
      final var maxX = Long.parseLong(matcher.group(3));
      final var minY = Long.parseLong(matcher.group(4));
      final var maxY = Long.parseLong(matcher.group(5));
      final var minZ = Long.parseLong(matcher.group(6));
      final var maxZ = Long.parseLong(matcher.group(7));
      final var newCuboid = new Cuboid(minX, maxX, minY, maxY, minZ, maxZ);

      var newCuboids = new ArrayList<Cuboid>();
      for (final var testCuboid : cuboids) {
        newCuboids.addAll(slices(newCuboid, testCuboid));
      }
      if ("on".equals(command)) {
        newCuboids.add(newCuboid);
      }
      cuboids = newCuboids;
    }
    var volume = 0L;
    for (final var cuboid : cuboids) {
      volume += cuboid.volume();
    }
    return volume;
  }

  private List<Cuboid> slices(Cuboid newCuboid, Cuboid testCuboid) {
    final var newCuboids = new ArrayList<Cuboid>();
    var xOverlap = newCuboid.maxX >= testCuboid.minX && newCuboid.minX <= testCuboid.maxX;
    var yOverlap = newCuboid.maxY >= testCuboid.minY && newCuboid.minY <= testCuboid.maxY;
    var zOverlap = newCuboid.maxZ >= testCuboid.minZ && newCuboid.minZ <= testCuboid.maxZ;
    if (xOverlap && yOverlap && zOverlap) {
      if (testCuboid.minX < newCuboid.minX) {
        var cuboidSlice = new Cuboid(testCuboid.minX, newCuboid.minX - 1, testCuboid.minY,
            testCuboid.maxY, testCuboid.minZ, testCuboid.maxZ);
        newCuboids.add(cuboidSlice);
        testCuboid.minX = newCuboid.minX;
      }
      if (testCuboid.maxX > newCuboid.maxX) {
        var cuboidSlice = new Cuboid(newCuboid.maxX + 1, testCuboid.maxX, testCuboid.minY,
            testCuboid.maxY, testCuboid.minZ, testCuboid.maxZ);
        newCuboids.add(cuboidSlice);
        testCuboid.maxX = newCuboid.maxX;
      }
      if (testCuboid.minY < newCuboid.minY) {
        var cuboidSlice = new Cuboid(testCuboid.minX, testCuboid.maxX, testCuboid.minY,
            newCuboid.minY - 1, testCuboid.minZ, testCuboid.maxZ);
        newCuboids.add(cuboidSlice);
        testCuboid.minY = newCuboid.minY;
      }
      if (testCuboid.maxY > newCuboid.maxY) {
        var cuboidSlice = new Cuboid(testCuboid.minX, testCuboid.maxX, newCuboid.maxY + 1,
            testCuboid.maxY, testCuboid.minZ, testCuboid.maxZ);
        newCuboids.add(cuboidSlice);
        testCuboid.maxY = newCuboid.maxY;
      }
      if (testCuboid.minZ < newCuboid.minZ) {
        var cuboidSlice = new Cuboid(testCuboid.minX, testCuboid.maxX, testCuboid.minY,
            testCuboid.maxY, testCuboid.minZ, newCuboid.minZ - 1);
        newCuboids.add(cuboidSlice);
        testCuboid.minZ = newCuboid.minZ;
      }
      if (testCuboid.maxZ > newCuboid.maxZ) {
        var cuboidSlice = new Cuboid(testCuboid.minX, testCuboid.maxX, testCuboid.minY,
            testCuboid.maxY, newCuboid.maxZ + 1, testCuboid.maxZ);
        newCuboids.add(cuboidSlice);
        testCuboid.maxZ = newCuboid.maxZ;
      }
    } else { // no overlap
      newCuboids.add(testCuboid);
    }
    return newCuboids;
  }

  static class Cuboid {

    long minX;
    long maxX;
    long minY;
    long maxY;
    long minZ;
    long maxZ;

    public Cuboid(long minX, long maxX, long minY, long maxY, long minZ, long maxZ) {
      this.minX = minX;
      this.maxX = maxX;
      this.minY = minY;
      this.maxY = maxY;
      this.minZ = minZ;
      this.maxZ = maxZ;
    }

    public long volume() {
      return (1 + maxX - minX) * (1 + maxY - minY) * (1 + maxZ - minZ);
    }

    @Override
    public String toString() {
      return "Cuboid{x=" +
          minX + ".." + maxX + ",y=" +
          minY + ".." + maxY + ",z=" +
          minZ + ".." + maxZ + '}';
    }
  }

  class Point3d {

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      Point3d other = (Point3d) obj;
      if (!getOuterType().equals(other.getOuterType())) {
        return false;
      }
      if (x != other.x) {
        return false;
      }
      if (y != other.y) {
        return false;
      }
      return z == other.z;
    }

    long x;
    long y;
    long z;

    Point3d(final long x, final long y, final long z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    private Day22 getOuterType() {
      return Day22.this;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y, z);
    }
  }
}
