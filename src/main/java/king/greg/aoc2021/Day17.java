package king.greg.aoc2021;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class Day17 {

  private Day17() {
  }

  public static int maximumY(final int minX, final int maxX, final int minY, final int maxY) {
    var maxHeight = 0;
    var possibleVelocityX = 0;
    while (possibleVelocityX <= maxX) {
      var currentX = 0;
      for (var steps = 1; steps <= possibleVelocityX; steps++) {
        currentX += ((possibleVelocityX - steps) + 1);
        if (currentX >= minX && currentX <= maxX) {
          var possibleVelocityY = 1;
          do {
            var currentY = (steps * possibleVelocityY) - ((steps * (steps - 1)) / 2);
            if (currentY >= minY && currentY <= maxY) {
              var currentMax = (possibleVelocityY * (possibleVelocityY + 1)) / 2;
              if (currentMax > maxHeight) {
                maxHeight = currentMax;
              }
            } else if (steps == possibleVelocityX) {
              var extraSteps = steps;
              while (currentY >= minY) {
                currentY = (extraSteps * possibleVelocityY) - ((extraSteps * (extraSteps + 1)) / 2);
                if (currentY >= minY && currentY <= maxY) {
                  var currentMax = (possibleVelocityY * (possibleVelocityY - 1)) / 2;
                  if (currentMax > maxHeight) {
                    maxHeight = currentMax;
                  }
                }
                extraSteps++;
              }
            }
            possibleVelocityY++;
          } while (possibleVelocityY <= Math.abs(minY));
        }
      }
      possibleVelocityX++;
    }
    return maxHeight;
  }

  public static int possibleVelocities(final int minX, final int maxX, final int minY,
      final int maxY) {
    final Set<Point> velocities = new HashSet<>();
    var possibleVelocityX = 0;
    while (possibleVelocityX <= maxX) {
      var currentX = 0;
      for (var steps = 1; steps <= possibleVelocityX; steps++) {
        currentX += ((possibleVelocityX - steps) + 1);
        if (currentX >= minX && currentX <= maxX) {
          var possibleVelocityY = minY;
          do {
            var currentY = (steps * possibleVelocityY) - ((steps * (steps - 1)) / 2);
            if (currentY >= minY && currentY <= maxY) {
              velocities.add(new Point(possibleVelocityX, possibleVelocityY));
            } else if (steps == possibleVelocityX) {
              var extraSteps = steps;
              while (currentY >= minY) {
                currentY = (extraSteps * possibleVelocityY) - ((extraSteps * (extraSteps - 1)) / 2);
                if (currentY >= minY && currentY <= maxY) {
                  velocities.add(new Point(possibleVelocityX, possibleVelocityY));
                  break;
                }
                extraSteps++;
              }
            }
            possibleVelocityY++;
          } while (possibleVelocityY <= Math.abs(minY));
        }
      }
      possibleVelocityX++;
    }
    return velocities.size();
  }
}
