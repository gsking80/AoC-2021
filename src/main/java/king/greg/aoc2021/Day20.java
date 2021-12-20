package king.greg.aoc2021;

import java.awt.Point;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class Day20 {

  final char[] algorithm;
  Set<Point> image;
  char defaultValue = '.';

  public Day20(final List<String> lines) {
    algorithm = lines.get(0).toCharArray();
    image = new HashSet<>();

    for (var y = 2; y < lines.size(); y++) {
      final var line = lines.get(y);
      for (var x = 0; x < line.length(); x++) {
        final var value = line.charAt(x);
        if (value != defaultValue) {
          image.add(new Point(x, y));
        }
      }
    }
  }

  public int litPixels(final int enhancements) {

    for (var i = 0; i < enhancements; i++) {
      var nextDefaultValue = defaultValue == '.' ? algorithm[0] : algorithm[511];
      var minX =
          image.stream().mapToInt(v -> v.x).min().orElseThrow(NoSuchElementException::new) - 1;
      var maxX =
          image.stream().mapToInt(v -> v.x).max().orElseThrow(NoSuchElementException::new) + 1;
      var minY =
          image.stream().mapToInt(v -> v.y).min().orElseThrow(NoSuchElementException::new) - 1;
      var maxY =
          image.stream().mapToInt(v -> v.y).max().orElseThrow(NoSuchElementException::new) + 1;

      Set<Point> nextImage = new HashSet<>();
      for (int x = minX; x <= maxX; x++) {
        for (int y = minY; y <= maxY; y++) {
          var pixel = new Point(x, y);
          var nextValue = nextValue(pixel);
          if (nextValue != nextDefaultValue) {
            nextImage.add(pixel);
          }
        }
      }
      image = nextImage;
      defaultValue = nextDefaultValue;
    }

    if (defaultValue == '#') {
      throw new IllegalStateException();
    }
    return image.size();
  }

  private char nextValue(final Point pixel) {
    final var sb = new StringBuilder();
    for (int y = pixel.y - 1; y <= pixel.y + 1; y++) {
      for (int x = pixel.x - 1; x <= pixel.x + 1; x++) {
        sb.append((image.contains(new Point(x, y)) ^ defaultValue == '#') ? '1' : '0');
      }
    }
    var index = new BigInteger(sb.toString(), 2).intValue();
    return algorithm[index];
  }
}
