package code.expressionlanguage.instr;

import code.util.IntTreeMap;
import code.util.StringList;

public final class ResultAfterDoubleDotted {

    private int nextIndex;

    private int lastDoubleDot;

    private boolean callCtor;

    private StringList declaring;

    private IntTreeMap<Character> stack;

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
        return StringList.contains(declaring,_var);
    }
    public StringList getDeclaring() {
        return declaring;
    }

    public void setDeclaring(StringList _declaring) {
        declaring = _declaring;
    }

    public IntTreeMap<Character> getStack() {
        return stack;
    }

    public void setStack(IntTreeMap<Character> _stack) {
        stack = _stack;
    }

}
