package code.expressionlanguage.analyze.files;



final class ParseDelimitersState {
    private static final char BEGIN_BLOCK = '{';
    private static final char END_BLOCK = '}';
    private static final char BEGIN_ARRAY = '[';
    private static final char END_ARRAY = ']';
    private static final char BEGIN_CALLING = '(';
    private static final char END_CALLING = ')';
    private int braces;
    private int parentheses;
    private boolean exitLoop;
    ParseDelimitersState(int _braces, int _parentheses) {
        braces = _braces;
        parentheses = _parentheses;
    }
    void parse(int _curChar, boolean _endInstruction) {
        if (_curChar == BEGIN_CALLING) {
            parentheses++;
        }
        if (_curChar == BEGIN_ARRAY) {
            parentheses++;
        }
        if (_curChar == END_CALLING) {
            checkPars();
        }
        if (_curChar == END_ARRAY) {
            checkPars();
        }
        if (_curChar == BEGIN_BLOCK) {
            if (_endInstruction) {
                braces++;
            } else {
                parentheses++;
            }
        }
        if (_curChar == END_BLOCK) {
            if (_endInstruction) {
                if (braces <= 0) {
                    exitLoop = true;
                    return;
                }
                braces--;
            } else {
                checkPars();
            }
        }
    }
    private void checkPars() {
        if (parentheses <= 0) {
            exitLoop = true;
            return;
        }
        parentheses--;
    }

    int getParentheses() {
        return parentheses;
    }

    int getBraces() {
        return braces;
    }

    boolean isExitLoop() {
        return exitLoop;
    }
}
