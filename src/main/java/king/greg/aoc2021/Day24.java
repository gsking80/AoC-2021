package king.greg.aoc2021;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public class Day24 {

  final List<String> program;
  final Set<Long> validModels;
  final int[] a;
  final int[] b;
  final int[] c;

  public Day24(List<String> lines) {
    program = lines;
    validModels = new HashSet<>();
    a = new int[14];
    b = new int[14];
    c = new int[14];
    for (var i = 0; i < 14; i++) {
      a[i] = Integer.parseInt(program.get((i * 18) + 4).split(" ")[2]);
      b[i] = Integer.parseInt(program.get((i * 18) + 5).split(" ")[2]);
      c[i] = Integer.parseInt(program.get((i * 18) + 15).split(" ")[2]);
    }
  }

  public long largestValidNumber() {
    var model = new int[14];
    for (final var pair : getPairs()) {
      //left was push, right was pop
      var offset = c[pair.getLeft()] + b[pair.getRight()];
      if (offset < 0) {
        model[pair.getLeft()] = 9;
        model[pair.getRight()] = 9 + offset;
      } else {
        model[pair.getLeft()] = 9 - offset;
        model[pair.getRight()] = 9;
      }
    }
    var modelNumber = 0L;
    for (var i = 0; i < 14; i++) {
      modelNumber = (modelNumber * 10) + model[i];
    }
    return modelNumber;
  }

  public long smallestValidNumber() {
    var model = new int[14];
    for (final var pair : getPairs()) {
      //left was push, right was pop
      var offset = c[pair.getLeft()] + b[pair.getRight()];
      if (offset < 0) {
        model[pair.getLeft()] = 1 - offset;
        model[pair.getRight()] = 1;
      } else {
        model[pair.getLeft()] = 1;
        model[pair.getRight()] = 1 + offset;
      }
    }
    var modelNumber = 0L;
    for (var i = 0; i < 14; i++) {
      modelNumber = (modelNumber * 10) + model[i];
    }
    return modelNumber;
  }

  private HashSet<Pair<Integer, Integer>> getPairs() {
    var pairs = new HashSet<Pair<Integer, Integer>>();
    var stack = new ArrayDeque<Integer>();
    for (var i = 0; i < 14; i++) {
      if (a[i] == 1) { //push onto the stack
        stack.addLast(i);
      } else { //pop off the stack
        pairs.add(Pair.of(stack.removeLast(), i));
      }
    }
    return pairs;
  }
}
