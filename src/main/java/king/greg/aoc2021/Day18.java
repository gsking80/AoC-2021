package king.greg.aoc2021;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class Day18 {

  final List<SnailFishNumber> numbers;

  public Day18(final List<String> input) {
    numbers = new ArrayList<>();
    for (final var line : input) {
      numbers.add(new SnailFishNumber(line));
    }
  }

  public long magnitude() {
    var sum = numbers.get(0);
    for (var i = 1; i < numbers.size(); i++) {
      sum = new SnailFishNumber(sum, numbers.get(i));
    }
    return sum.magnitude();
  }

  public long largestMagnitudePair() {
    long maxMagnitudePair = 0;
    for (var i = 0; i < numbers.size() - 1; i++) {
      for (var j = i + 1; j < numbers.size(); j++) {
        var magnitude = new SnailFishNumber(numbers.get(i), numbers.get(j)).magnitude();
        if (magnitude > maxMagnitudePair) {
          maxMagnitudePair = magnitude;
        }
        magnitude = new SnailFishNumber(numbers.get(j), numbers.get(i)).magnitude();
        if (magnitude > maxMagnitudePair) {
          maxMagnitudePair = magnitude;
        }
      }
    }
    return maxMagnitudePair;
  }

  static class SnailFishNumber {

    Integer value;
    SnailFishNumber left;
    SnailFishNumber right;

    private Integer addLeftMost(final Integer valueToAdd) {
      if (valueToAdd == null) {
        return null;
      }
      if (value != null) {
        value += valueToAdd;
        return null;
      }
      return right.addLeftMost(left.addLeftMost(valueToAdd));
    }

    private Integer addRightMost(final Integer valueToAdd) {
      if (valueToAdd == null) {
        return null;
      }
      if (value != null) {
        value += valueToAdd;
        return null;
      }
      return left.addRightMost(right.addRightMost(valueToAdd));
    }

    public boolean split() {
      if (null != value) {
        if (value < 10) {
          return false;
        }
        //Split it!
        Integer newLeft = value / 2;
        Integer newRight = value - newLeft;
        left = new SnailFishNumber(newLeft);
        right = new SnailFishNumber(newRight);
        value = null;
        return true;
      }
      var success = left.split();
      if (!success) {
        success = right.split();
      }
      return success;
    }

    public Pair<Boolean, Pair<Integer, Integer>> explode(final int depth) {
      if (null != value) {
        return Pair.of(false, null);
      } else if (depth == 4) { //Kaboom!
        var values = Pair.of(left.value, right.value);
        value = 0;
        left = null;
        right = null;
        return Pair.of(true, values);
      } else {
        Pair<Boolean, Pair<Integer, Integer>> result;
        result = left.explode(depth + 1);
        if (Boolean.TRUE.equals(result.getLeft())) {
          var values = result.getRight();
          return Pair.of(true, Pair.of(values.getLeft(), right.addLeftMost(values.getRight())));
        }
        result = right.explode(depth + 1);
        if (Boolean.TRUE.equals(result.getLeft())) {
          var values = result.getRight();
          return Pair.of(true, Pair.of(left.addRightMost(values.getLeft()), values.getRight()));
        }
      }
      return Pair.of(false, null);
    }

    public long magnitude() {
      return null == value ? (3 * left.magnitude() + 2 * right.magnitude()) : value;
    }

    SnailFishNumber(final SnailFishNumber snailFishNumber) {
      if (snailFishNumber.left != null) {
        this.left = new SnailFishNumber(snailFishNumber.left);
      }
      if (snailFishNumber.right != null) {
        this.right = new SnailFishNumber(snailFishNumber.right);
      }
      if (snailFishNumber.value != null) {
        this.value = snailFishNumber.value;
      }
    }

    SnailFishNumber(final SnailFishNumber left, final SnailFishNumber right) {
      this.left = new SnailFishNumber(left);
      this.right = new SnailFishNumber(right);
      var keepReducing = true;
      while (keepReducing) {
        keepReducing = explode(0).getLeft();
        if (keepReducing) {
          continue;
        }
        keepReducing = split();
      }
    }

    SnailFishNumber(final Integer input) {
      value = input;
    }

    SnailFishNumber(final String input) {
      if (input.length() == 1) {
        value = Integer.parseInt(input);
        return;
      }
      // find the middle
      var depth = 0;
      var middleFound = false;
      var index = 0;
      while (!middleFound) {
        index++;
        switch (input.charAt(index)) {
          case '[':
            depth++;
            break;
          case ']':
            depth--;
            break;
          case ',':
            if (depth == 0) {
              middleFound = true;
            }
            break;
          default:
            //do nothing
        }
      }
      left = new SnailFishNumber(input.substring(1, index));
      right = new SnailFishNumber(input.substring(index + 1, input.length() - 1));
    }

    @Override
    public String toString() {
      return (null == value) ? "[" + left + "," + right + "]" : value.toString();
    }
  }
}
