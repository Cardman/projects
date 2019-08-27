package code.expressionlanguage.instr;

import code.util.IntTreeMap;
import code.util.StringMap;

public final class ResultAfterDoubleDotted {

    private int nextIndex;

    private int lastDoubleDot;

    private boolean callCtor;

    private StringMap<Boolean> declaring;

    private IntTreeMap<Character> stack;

    private String currentWord = "";

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int _nextIndex) {
        nextIndex = _nextIndex;
    }

    public int getLastDoubleDot() {
        return lastDoubleDot;
    }

    public void setLastDoubleDot(int _lastDoubleDot) {
        lastDoubleDot = _lastDoubleDot;
    }

    public boolean isCallCtor() {
        return callCtor;
    }

    public void setCallCtor(boolean _callCtor) {
        callCtor = _callCtor;
    }

    boolean isDeclared(String _var) {
        if (!declaring.contains(_var)) {
            return false;
        }
        return declaring.getVal(_var);
    }
    public StringMap<Boolean> getDeclaring() {
        return declaring;
    }

    public void setDeclaring(StringMap<Boolean> _declaring) {
        declaring = _declaring;
    }

    public IntTreeMap<Character> getStack() {
        return stack;
    }

    public void setStack(IntTreeMap<Character> _stack) {
        stack = _stack;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String _currentWord) {
        currentWord = _currentWord;
    }
}
