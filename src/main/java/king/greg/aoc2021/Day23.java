package king.greg.aoc2021;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

public class Day23 {

  static final Map<Character, Integer> COSTS = Map.of(
      'A', 1,
      'B', 10,
      'C', 100,
      'D', 1000
  );

  static final List<Integer> roomIndices = Arrays.asList(2, 4, 6, 8);

  final State start;
  final State goal;

  public Day23(final List<String> lines) {
    final var hallway = StringUtils.repeat('.', 11);
    var roomsStart = new String[]{"", "", "", ""};
    for (var line : lines) {
      var roomIndex = 0;
      for (var x = 3; x < 10; x++) {
        var current = line.charAt(x);
        if (current >= 'A' && current <= 'D') {
          roomsStart[roomIndex] = roomsStart[roomIndex] + current;
          roomIndex++;
        }
      }
    }
    start = new State(hallway, roomsStart);
    final var roomsGoal = new String[4];
    for (var i = 0; i < 4; i++) {
      roomsGoal[i] = StringUtils.repeat((char) ('A' + i), start.rooms[0].length());
    }
    goal = new State(hallway, roomsGoal);
  }

  public int minimumCostToArrange() {
    final var visited = new HashSet<State>();
    final var priorityQueue = initQueue();
    priorityQueue.add(start);

    while (!priorityQueue.isEmpty()) {
      var current = priorityQueue.remove();
      if (!visited.contains(current)) {
        visited.add(current);
        if (current.equals(goal)) {
          return current.expendedEnergy;
        }
        for (final var nextState : current.nextStates()) {
          if (!visited.contains(nextState)) {
            priorityQueue.add(nextState);
          }
        }
      }
    }
    return 0;
  }


  private PriorityQueue<State> initQueue() {
    return new PriorityQueue<>(Comparator.comparingInt(State::predictedTotalEnergy));
  }


  static class State {

    String hallway;
    String[] rooms;
    int expendedEnergy;

    State(final String hallway, final String[] rooms) {
      this.hallway = hallway;
      this.rooms = rooms;
    }

    State(final String hallway, final String[] rooms, final int expendedEnergy) {
      this.hallway = hallway;
      this.rooms = rooms;
      this.expendedEnergy = expendedEnergy;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      var state = (State) o;
      return hallway.equals(state.hallway) && Arrays.equals(rooms, state.rooms);
    }

    @Override
    public int hashCode() {
      int result = Objects.hash(hallway);
      result = 31 * result + Arrays.hashCode(rooms);
      return result;
    }

    int predictedTotalEnergy() {
      return expendedEnergy; //A* this?
    }

    public Set<State> nextStates() {
      final var nextStates = new HashSet<State>();
      for (var roomNumber = 0; roomNumber < 4; roomNumber++) {
        // Attempt to move to hallway
        var room = rooms[roomNumber];
        var amphipodFound = false;
        for (var depth = 0; depth < room.length() && !amphipodFound; depth++) {
          var amphipod = room.charAt(depth);
          if (amphipod == '.') {
            continue;
          }
          amphipodFound = true;
          var sbRoom = new StringBuilder(room);
          sbRoom.setCharAt(depth, '.');
          for (final var openHallSpace : openHallSpaces(roomNumber)) {
            var sbHallway = new StringBuilder(hallway);
            sbHallway.setCharAt(openHallSpace, amphipod);
            final var newRooms = Arrays.copyOf(rooms, 4);
            newRooms[roomNumber] = sbRoom.toString();
            var energy =
                (depth + 1 + Math.abs(openHallSpace - roomIndices.get(roomNumber))) * COSTS.get(
                    amphipod);
            nextStates.add(new State(sbHallway.toString(), newRooms, expendedEnergy + energy));
          }
        }
      }

      for (var hallSpace = 0; hallSpace < hallway.length(); hallSpace++) {
        var amphipod = hallway.charAt(hallSpace);
        if (amphipod == '.') {
          continue;
        }
        final var targetRoomNumber = amphipod - 'A';
        final var targetRoom = rooms[targetRoomNumber];
        var reachable = true;
        var depth = targetRoom.length() - 1;
        while (reachable && depth >= 0) {
          var space = targetRoom.charAt(depth);
          if (space == '.') {
            break;
          }
          if (space == amphipod) {
            depth--;
          } else {
            reachable = false;
          }
        }
        if (depth < 0) {
          reachable = false;
        }
        for (var checkSpace = Math.min(hallSpace, roomIndices.get(targetRoomNumber)) + 1;
            checkSpace < Math.max(hallSpace, roomIndices.get(targetRoomNumber));
            checkSpace++) {
          if (hallway.charAt(checkSpace) != '.') {
            reachable = false;
            break;
          }
        }
        if (reachable) {
          var sbRoom = new StringBuilder(targetRoom);
          sbRoom.setCharAt(depth, amphipod);
          var sbHallway = new StringBuilder(hallway);
          sbHallway.setCharAt(hallSpace, '.');
          final var newRooms = Arrays.copyOf(rooms, 4);
          newRooms[targetRoomNumber] = sbRoom.toString();
          var energy =
              (depth + 1 + Math.abs(hallSpace - roomIndices.get(targetRoomNumber))) * COSTS.get(
                  amphipod);
          nextStates.add(new State(sbHallway.toString(), newRooms, expendedEnergy + energy));
        }
      }

      return nextStates;
    }

    Set<Integer> openHallSpaces(final int roomNumber) {
      final var openSpaces = new HashSet<Integer>();
      final var roomIndex = roomIndices.get(roomNumber);
      for (var possibleSpace = roomIndex + 1;
          possibleSpace < hallway.length() && hallway.charAt(possibleSpace) == '.';
          possibleSpace++) {
        if (!roomIndices.contains(possibleSpace)) {
          openSpaces.add(possibleSpace);
        }
      }
      for (var possibleSpace = roomIndex - 1;
          possibleSpace >= 0 && hallway.charAt(possibleSpace) == '.'; possibleSpace--) {
        if (!roomIndices.contains(possibleSpace)) {
          openSpaces.add(possibleSpace);
        }
      }
      return openSpaces;
    }
  }
}
