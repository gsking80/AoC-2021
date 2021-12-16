package king.greg.aoc2021;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day16 {

  private Day16() {
  }

  public static long versionSum(final String operatorPacket) {
    return buildOuterPacket(operatorPacket).versionSum();
  }

  public static long value(final String operatorPacket) {
    return buildOuterPacket(operatorPacket).value();
  }

  private static Packet buildOuterPacket(String operatorPacket) {
    final var sb = new StringBuilder();
    for (final var hexit : operatorPacket.toCharArray()) {
      sb.append(String.format("%4s",
              Integer.toBinaryString(new BigInteger(String.valueOf(hexit), 16).intValue()))
          .replace(' ', '0'));
    }
    return new Packet(sb.toString());
  }

  static class Packet {

    int version;
    int type;
    int length;
    long literalValue;
    List<Packet> subPackets;

    public Packet(final String binaryString) {
      subPackets = new ArrayList<>();
      version = new BigInteger(binaryString.substring(0, 3), 2).intValue();
      type = new BigInteger(binaryString.substring(3, 6), 2).intValue();
      if (type == 4) { //literal
        length = 6;
        var lastPacket = false;
        var literalBits = new StringBuilder();
        while (!lastPacket) {
          lastPacket = (binaryString.charAt(length) == '0');
          literalBits.append(binaryString, length + 1, length + 5);
          length += 5;
        }
        literalValue = new BigInteger(literalBits.toString(), 2).longValue();
      } else { //operator
        final var lengthType = new BigInteger(binaryString.substring(6, 7), 2).intValue();
        if (lengthType == 0) {
          length = 22;
          final var subPacketsLength = new BigInteger(binaryString.substring(7, length),
              2).intValue();
          while ((length - 22) < subPacketsLength) {
            var subPacket = new Packet(binaryString.substring(length));
            length += subPacket.length;
            subPackets.add(subPacket);
          }
        } else {
          length = 18;
          final var subpacketCount = new BigInteger(binaryString.substring(7, length),
              2).intValue();
          for (var i = 0; i < subpacketCount; i++) {
            var subPacket = new Packet(binaryString.substring(length));
            length += subPacket.length;
            subPackets.add(subPacket);
          }
        }
      }
    }

    public int versionSum() {
      var versionSum = version;
      versionSum += subPackets.stream().mapToInt(Packet::versionSum).sum();
      return versionSum;
    }

    public long value() {
      switch (type) {
        case 0: //sum
          return subPackets.stream().mapToLong(Packet::value).sum();
        case 1: //product
          return subPackets.stream().mapToLong(Packet::value).reduce(1, (a, b) -> a * b);
        case 2: //minimum
          return subPackets.stream().mapToLong(Packet::value).min().orElseThrow();
        case 3: //maximum
          return subPackets.stream().mapToLong(Packet::value).max().orElseThrow();
        case 4: //literal
          return literalValue;
        case 5: //greater than
          return subPackets.get(0).value() > subPackets.get(1).value() ? 1 : 0;
        case 6: //less than
          return subPackets.get(0).value() < subPackets.get(1).value() ? 1 : 0;
        case 7: //equal to
          return subPackets.get(0).value() == subPackets.get(1).value() ? 1 : 0;
        default:
          throw new UnsupportedOperationException("Type: " + type);
      }
    }
  }
}
