package king.greg.aoc2021;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day19 {

  final List<Scanner> scanners;

  public Day19(List<String> lines) {
    scanners = new ArrayList<>();
    var scanner = new Scanner();
    for (final var line : lines) {
      if (line.startsWith("---")) {
        scanner = new Scanner();
        scanners.add(scanner);
      } else if (line.isBlank()) {
        //do nothing
      } else {
        var coords = line.split(",");
        scanner.beacons.add(new Point3d(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]),
            Integer.parseInt(coords[2])));
      }
    }
  }

  public int beaconCount() {
    relocateScanners();
    final Set<Point3d> beacons = new HashSet<>();
    for (final var scanner : scanners) {
      beacons.addAll(scanner.beacons);
    }
    return beacons.size();
  }

  public int scannerSpace() {
    relocateScanners();
    int maxDistance = 0;
    for (final var scannerA : scanners) {
      for (final var scannerB : scanners) {
        var distance = Math.abs(scannerA.position.x - scannerB.position.x) + Math.abs(
            scannerA.position.y - scannerB.position.y) + Math.abs(
            scannerA.position.z - scannerB.position.z);
        if (distance > maxDistance) {
          maxDistance = distance;
        }
      }
    }
    return maxDistance;
  }

  private void relocateScanners() {
    scanners.get(0).position = new Point3d(0, 0, 0);
    var beaconsLeftToLocate = true;
    while (beaconsLeftToLocate) {
      beaconsLeftToLocate = false;
      for (final var scanner : scanners) {
        if (null != scanner.position) {
          continue;
        }
        var found = false;
        for (final var knownScanner : scanners) {
          if (null == knownScanner.position) {
            continue;
          }
          for (var facing = 0; facing < 6 && !found; facing++) {
            for (var rotation = 0; rotation < 4 && !found; rotation++) {
              var rotatedBeacons = scanner.getRotatedBeacons(facing, rotation);
              for (final var knownBeacon : knownScanner.beacons) {
                for (final var testBeacon : rotatedBeacons) {
                  final var deltaX = knownBeacon.x - testBeacon.x;
                  final var deltaY = knownBeacon.y - testBeacon.y;
                  final var deltaZ = knownBeacon.z - testBeacon.z;
                  var count = 0;
                  for (final var beaconA : knownScanner.beacons) {
                    for (final var beaconB : rotatedBeacons) {
                      if (beaconA.x - beaconB.x == deltaX && beaconA.y - beaconB.y == deltaY
                          && beaconA.z - beaconB.z == deltaZ) {
                        count++;
                      }
                      if (count >= 12) {
                        break;
                      }
                    }
                    if (count >= 12) {
                      break;
                    }
                  }
                  if (count >= 12) { // It's a match!
                    found = true;
                    scanner.update(new Point3d(deltaX, deltaY, deltaZ), facing, rotation);
                    break;
                  }
                }
                if (found) {
                  break;
                }
              }
            }
          }
          if (found) {
            break;
          }
        }
        if (!found) {
          beaconsLeftToLocate = true;
        }
      }
    }
  }

  class Scanner {

    Point3d position;
    List<Point3d> beacons;

    public Scanner() {
      beacons = new ArrayList<>();
    }

    public void update(final Point3d location, final int facing, final int rotation) {
      beacons = getRotatedBeacons(facing, rotation);
      for (final var beacon : beacons) {
        beacon.x += location.x;
        beacon.y += location.y;
        beacon.z += location.z;
      }
      position = location;
    }

    public List<Point3d> getRotatedBeacons(final int facing, final int rotation) {
      final List<Point3d> rotatedBeacons = new ArrayList<>();
      for (final var beacon : beacons) {
        final Point3d facedBeacon;
        switch (facing) {
          case 0: // +x
            facedBeacon = new Point3d(beacon.x, beacon.y, beacon.z);
            break;
          case 1: // -x
            facedBeacon = new Point3d(beacon.x * -1, beacon.y, beacon.z * -1);
            break;
          case 2: // +y
            facedBeacon = new Point3d(beacon.y, beacon.x * -1, beacon.z);
            break;
          case 3: // -y
            facedBeacon = new Point3d(beacon.y * -1, beacon.x, beacon.z);
            break;
          case 4: // +z
            facedBeacon = new Point3d(beacon.z, beacon.y, beacon.x * -1);
            break;
          case 5: // -z
            facedBeacon = new Point3d(beacon.z * -1, beacon.y, beacon.x);
            break;
          default:
            throw new UnsupportedOperationException();
        }
        final Point3d rotatedBeacon;
        switch (rotation) {
          case 0:
            rotatedBeacon = new Point3d(facedBeacon.x, facedBeacon.y, facedBeacon.z);
            break;
          case 1:
            rotatedBeacon = new Point3d(facedBeacon.x, facedBeacon.z, facedBeacon.y * -1);
            break;
          case 2:
            rotatedBeacon = new Point3d(facedBeacon.x, facedBeacon.y * -1, facedBeacon.z * -1);
            break;
          case 3:
            rotatedBeacon = new Point3d(facedBeacon.x, facedBeacon.z * -1, facedBeacon.y);
            break;
          default:
            throw new UnsupportedOperationException();
        }
        rotatedBeacons.add(rotatedBeacon);
      }
      return rotatedBeacons;
    }
  }

  class Point3d {

    @Override
    public int hashCode() {
      final var prime = 31;
      var result = 1;
      result = prime * result + getOuterType().hashCode();
      result = prime * result + x;
      result = prime * result + y;
      result = prime * result + z;
      return result;
    }

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

    int x;
    int y;
    int z;

    Point3d(final int x, final int y, final int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    private Day19 getOuterType() {
      return Day19.this;
    }
  }
}
