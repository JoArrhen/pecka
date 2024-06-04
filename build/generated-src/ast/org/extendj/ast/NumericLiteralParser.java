package org.extendj.ast;

import java.util.*;
import java.util.zip.*;
import java.io.*;
import org.jastadd.util.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import beaver.Symbol;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.stream.StreamSupport;
import java.net.URL;
import java.util.function.Predicate;
import java.lang.reflect.Field;
import org.jastadd.util.PrettyPrinter;
import java.io.BufferedInputStream;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.io.PrintStream;
import java.util.regex.Pattern;
import org.extendj.callgraph.AttributeTracer;
import java.util.concurrent.ConcurrentSkipListMap;
import java.io.InputStream;
import java.util.concurrent.ConcurrentMap;
import org.jastadd.util.PrettyPrintable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;
import org.extendj.Cat;
import java.util.Collection;
import java.io.File;
/**
 * A recognizer for the different kinds of numeric literals in Java 7.
 * 
 * <p>The purpose of this parser is to classify numeric literals
 * into one of the numeric literal kinds, or IllegalLiteral if it
 * contains any syntax error.
 * 
 * <p>The ExtendJ tokenizer is overly permissive to allow underscores in
 * literals, this class is needed to find illegal uses of underscores and
 * other errors.  If there is any syntax error, the parse method returns an
 * IllegalLiteral.
 * @ast class
 * @aspect Java7Literals
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:441
 */
public class NumericLiteralParser extends java.lang.Object {
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:443
   */
  

    private final String literal;
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:444
   */
  
    private final StringBuilder out;
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:445
   */
  
    private int idx = 0;
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:446
   */
  
    private NumericLiteralKind kind;
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:447
   */
  
    private boolean whole;
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:448
   */
   // Have whole part?
    private boolean fraction;
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:449
   */
   // Have fraction part?
    private boolean exponentSpecifier;
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:450
   */
   // Have exponent specifier?
    private boolean exponent;
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:451
   */
   // Have exponent part?
    private boolean floating;
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:452
   */
   // Is floating point?
    private boolean isFloat;
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:453
   */
   // If this is a single precision floating point type.
    private boolean isLong;
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:455
   */
  

    private NumericLiteralParser(String literal) {
      this.literal = literal;
      out = new StringBuilder(literal.length());
    }
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:460
   */
  

    public static Literal parse(String literal) {
      NumericLiteralParser parser = new NumericLiteralParser(literal);
      return parser.parse();
    }
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:465
   */
  

    @Override public String toString() {
      return literal;
    }
  /**
   * @return a name describing the literal, used in parsing error messages.
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:472
   */
  

    /**
     * @return a name describing the literal, used in parsing error messages.
     */
    private String name() {
      String name;
      switch (kind) {
        case DECIMAL:
          name = "decimal";
          break;
        case HEXADECIMAL:
          name = "hexadecimal";
          break;
        case OCTAL:
          name = "octal";
          break;
        case BINARY:
        default: // Needed to convince javac that all paths assign name.
          name = "binary";
          break;
      }
      if (floating) {
        return name + " floating point";
      } else {
        return name;
      }
    }
  /**
   * The next character in the literal is a significant character;
   * push it onto the buffer.
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:500
   */
  

    /**
     * The next character in the literal is a significant character;
     * push it onto the buffer.
     */
    private void pushChar() {
      out.append(literal.charAt(idx++));
    }
  /**
   * Skip ahead n chracters in the literal.
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:507
   */
  

    /**
     * Skip ahead n chracters in the literal.
     */
    private void skip(int n) {
      idx += n;
    }
  /**
   * @return {@code true} if there exists at least n more characters
   * in the literal
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:515
   */
  

    /**
     * @return {@code true} if there exists at least n more characters
     * in the literal
     */
    private boolean have(int n) {
      return literal.length() >= idx + n;
    }
  /**
   * Look at the n'th next character.
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:522
   */
  

    /**
     * Look at the n'th next character.
     */
    private char peek(int n) {
      return literal.charAt(idx + n);
    }
  /**
   * @return true if the character c is a decimal digit
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:529
   */
  

    /**
     * @return true if the character c is a decimal digit
     */
    private static final boolean isDecimalDigit(char c) {
      return c == '_' || c >= '0' && c <= '9';
    }
  /**
   * @return true if the character c is a hexadecimal digit
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:536
   */
  

    /**
     * @return true if the character c is a hexadecimal digit
     */
    private static final boolean isHexadecimalDigit(char c) {
      return c == '_' || c >= '0' && c <= '9' || c >= 'a' && c <= 'f' || c >= 'A' && c <= 'F';
    }
  /**
   * @return true if the character c is a binary digit
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:543
   */
  

    /**
     * @return true if the character c is a binary digit
     */
    private static final boolean isBinaryDigit(char c) {
      return c == '_' || c == '0' || c == '1';
    }
  /**
   * @return true if the character c is an underscore
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:550
   */
  

    /**
     * @return true if the character c is an underscore
     */
    private static final boolean isUnderscore(char c) {
      return c == '_';
    }
  /**
   * Parse a literal. If there is a syntax error in the literal,
   * an IllegalLiteral will be returned.
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:558
   */
  

    /**
     * Parse a literal. If there is a syntax error in the literal,
     * an IllegalLiteral will be returned.
     */
    public Literal parse() {
      if (idx != 0 || out.length() != 0) {
        throw new IllegalStateException("Already parsed literal.");
      }
      if (literal.length() == 0) {
        throw new IllegalStateException("Can't parse empty literal.");
      }
      kind = classifyLiteral();
      if (!floating) {
        return parseDigits();
      } else {
        return parseFractionPart();
      }
    }
  /**
   * Classify the literal.
   * 
   * @return either DECIMAL, HEXADECIMAL or BINARY
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:578
   */
  

    /**
     * Classify the literal.
     *
     * @return either DECIMAL, HEXADECIMAL or BINARY
     */
    private NumericLiteralKind classifyLiteral() {
      if (peek(0) == '.') {
        floating = true;
        return NumericLiteralKind.DECIMAL;
      } else if (peek(0) == '0') {
        if (!have(2)) {
          // This is the only 1-length string that starts with 0!
          return NumericLiteralKind.DECIMAL;
        } else if (peek(1) == 'x' || peek(1) == 'X') {
          skip(2);
          return NumericLiteralKind.HEXADECIMAL;
        } else if (peek(1) == 'b' || peek(1) == 'B') {
          skip(2);
          return NumericLiteralKind.BINARY;
        } else {
          return NumericLiteralKind.DECIMAL;
        }
      } else {
        return NumericLiteralKind.DECIMAL;
      }
    }
  /**
   * If the current character is an underscore, the previous and next
   * characters need to be valid digits or underscores.
   * 
   * @return true if the underscore is misplaced
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:606
   */
  

    /**
     * If the current character is an underscore, the previous and next
     * characters need to be valid digits or underscores.
     *
     * @return true if the underscore is misplaced
     */
    private boolean misplacedUnderscore() {
      // The first and last characters are never allowed to be underscores.
      if (idx == 0 || idx + 1 == literal.length()) {
        return true;
      }

      switch (kind) {
        case DECIMAL:
          return !(isDecimalDigit(peek(-1)) && isDecimalDigit(peek(1)));
        case HEXADECIMAL:
          return !(isHexadecimalDigit(peek(-1)) && isHexadecimalDigit(peek(1)));
        case BINARY:
          return !(isBinaryDigit(peek(-1)) && isBinaryDigit(peek(1)));
      }
      throw new IllegalStateException("Unexpected literal kind");
    }
  /**
   * Report an illegal digit.
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:626
   */
  

    /**
     * Report an illegal digit.
     */
    private Literal syntaxError(String msg) {
      return new IllegalLiteral(String.format("in %s literal \"%s\": %s", name(), literal, msg));
    }
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:630
   */
  

    private Literal unexpectedCharacter(char c) {
      return syntaxError("unexpected character '" + c + "'; not a valid digit");
    }
  /**
   * Returns a string of only the lower case digits of the
   * parsed numeric literal.
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:638
   */
  

    /**
     * Returns a string of only the lower case digits of the
     * parsed numeric literal.
     */
    private String getLiteralString() {
      return out.toString().toLowerCase();
    }
  /**
   * Parse and build an IntegerLiteral, LongLiteral, FloatingPointLiteral or
   * DoubleLiteral. Returns an IllegalLiteral if the numeric literal cannot
   * be parsed.
   * 
   * <p>Note: does not perform bounds checks.
   * 
   * @return a concrete literal on success, or an IllegalLiteral if there is a syntax error
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:651
   */
  

    /**
     * Parse and build an IntegerLiteral, LongLiteral, FloatingPointLiteral or
     * DoubleLiteral. Returns an IllegalLiteral if the numeric literal cannot
     * be parsed.
     *
     * <p>Note: does not perform bounds checks.
     *
     * @return a concrete literal on success, or an IllegalLiteral if there is a syntax error
     */
    private Literal buildLiteral() {
      String digits = out.toString().toLowerCase();

      if (!floating) {
        if (!whole) {
          return syntaxError("at least one digit is required");
        }

        // Check if the literal is octal, and if so report illegal digits.
        if (kind == NumericLiteralKind.DECIMAL) {
          if (digits.charAt(0) == '0') {
            kind = NumericLiteralKind.OCTAL;
            for (int idx = 1; idx < digits.length(); ++idx) {
              char c = digits.charAt(idx);
              if (c < '0' || c > '7') {
                return unexpectedCharacter(c);
              }
            }
          }
        }

        if (isLong) {
          return new LongLiteral(literal);
        } else {
          return new IntegerLiteral(literal);
        }
      } else {
        if (kind == NumericLiteralKind.HEXADECIMAL && !exponent) {
          return syntaxError("exponent is missing");
        }

        if (exponentSpecifier && !exponent) {
          return syntaxError("expected exponent after exponent specifier");
        }

        if (!(whole || fraction)) {
          return syntaxError("at least one digit is required in "
              + "either the whole or fraction part");
        }

        if (isFloat) {
          return new FloatingPointLiteral(literal);
        } else {
          return new DoubleLiteral(literal);
        }
      }
    }
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:699
   */
  

    private Literal parseDigits() {
      while (have(1)) { // Continue while there is at least one more character/digit.
        char c = peek(0);
        switch (c) {
          case '_':
            if (misplacedUnderscore()) {
              return syntaxError("misplaced underscore - underscores may only "
                  + "be used within sequences of digits");
            }
            skip(1);
            continue;
          case '.':
            if (kind != NumericLiteralKind.DECIMAL && kind != NumericLiteralKind.HEXADECIMAL) {
              return unexpectedCharacter(c);
            }
            return parseFractionPart();
          case 'l':
          case 'L':
            if (have(2)) {
              return syntaxError("extra digits/characters after suffix " + c);
            }
            isLong = true;
            skip(1);
            continue;
        }

        switch (kind) {
          case DECIMAL:
            if (c == 'e' || c == 'E') {
              return parseExponentPart();
            } else if (c == 'f' || c == 'F') {
              if (have(2)) {
                return syntaxError("extra digits/characters after type suffix " + c);
              }
              floating = true;
              isFloat = true;
              skip(1);
            } else if (c == 'd' || c == 'D') {
              if (have(2)) {
                return syntaxError("extra digits/characters after type suffix " + c);
              }
              floating = true;
              skip(1);
            } else {
              if (!isDecimalDigit(c)) {
                return unexpectedCharacter(c);
              }
              whole = true;
              pushChar();
            }
            continue;
          case HEXADECIMAL:
            if (c == 'p' || c == 'P') {
              return parseExponentPart();
            }

            if (!isHexadecimalDigit(c)) {
              return unexpectedCharacter(c);
            }
            whole = true;
            pushChar();
            continue;
          case BINARY:
            if (!isBinaryDigit(c)) {
              return unexpectedCharacter(c);
            }
            whole = true;
            pushChar();
            continue;
        }
      }
      return buildLiteral();
    }
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:773
   */
  

    private Literal parseFractionPart() {
      floating = true;

      // Current char is the decimal period.
      pushChar();

      // While we have at least one more character/digit.
      while (have(1)) {
        char c = peek(0);
        switch (c) {
          case '_':
            if (misplacedUnderscore()) {
              return syntaxError("misplaced underscore - underscores may only "
                  + "be used as separators within sequences of valid digits");
            }
            skip(1);
            continue;
          case '.':
            return syntaxError("multiple decimal periods are not allowed");
        }

        if (kind == NumericLiteralKind.DECIMAL) {
          if (c == 'e' || c == 'E') {
            return parseExponentPart();

          } else if (c == 'f' || c == 'F') {
            if (have(2)) {
              return syntaxError("extra digits/characters after type suffix " + c);
            }
            floating = true;
            isFloat = true;
            skip(1);
          } else if (c == 'd' || c == 'D') {
            if (have(2)) {
              return syntaxError("extra digits/characters after type suffix " + c);
            }
            floating = true;
            skip(1);
          } else {
            if (!isDecimalDigit(c)) {
              return unexpectedCharacter(c);
            }
            pushChar();
            fraction = true;
          }
        } else { // kind == HEXADECIMAL
          if (c == 'p' || c == 'P') {
            return parseExponentPart();
          }

          if (!isHexadecimalDigit(c)) {
            return unexpectedCharacter(c);
          }
          fraction = true;
          pushChar();
        }
      }

      return buildLiteral();
    }
  /**
   * @aspect Java7Literals
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Literals.jrag:834
   */
  

    private Literal parseExponentPart() {
      floating = true;

      // Current char is the exponent specifier char.
      pushChar();

      exponentSpecifier = true;

      // Exponent sign.
      if (have(1) && (peek(0) == '+' || peek(0) == '-')) {
        pushChar();
      }

      // While we have at least one more character/digit.
      while (have(1)) {
        char c = peek(0);
        switch (c) {
          case '_':
            if (misplacedUnderscore()) {
              return syntaxError("misplaced underscore - underscores may only "
                  + "be used as separators within sequences of valid digits");
            }
            skip(1);
            continue;
          case '-':
          case '+':
            return syntaxError("exponent sign character is only allowed as "
                + "the first character of the exponent part of a "
                + "floating point literal");
          case '.':
            return syntaxError("multiple decimal periods are not allowed");
          case 'p':
          case 'P':
          case 'e':
          case 'E':
            return syntaxError("multiple exponent specifiers are not allowed");
          case 'f':
          case 'F':
            isFloat = true;
          case 'd':
          case 'D':
            if (have(2)) {
              return syntaxError("extra digits/characters after type suffix " + c);
            }
            skip(1);
            continue;
        }

        // Exponent is a signed integer.
        if (!isDecimalDigit(c)) {
          return unexpectedCharacter(c);
        }
        pushChar();
        exponent = true;
      }

      return buildLiteral();
    }

}
