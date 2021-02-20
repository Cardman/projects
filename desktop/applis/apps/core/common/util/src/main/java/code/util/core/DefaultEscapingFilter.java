package code.util.core;

public final class DefaultEscapingFilter extends AbstractEscapingFilter {
    private static final char CHARACTER = '.';
    private static final char STRING = '*';
    private static final char POSSIBLE_CHAR = '?';
    @Override
    protected boolean isEscSpaceChar(String _input, int _next) {
        return _input.charAt(_next) == CHARACTER || _input.charAt(_next) == STRING || _input.charAt(_next) == POSSIBLE_CHAR || _input.charAt(_next) == ESCAPING_CHAR;
    }
}
