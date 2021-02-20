package code.util.core;

public final class SpaceEscapingFilter extends AbstractEscapingFilter {
    private static final char SPACE_CHAR = ' ';
    @Override
    protected boolean isEscSpaceChar(String _input, int _next) {
        return _input.charAt(_next) == SPACE_CHAR || _input.charAt(_next) == ESCAPING_CHAR;
    }
}
