package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.types.AnaPartTypeUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.util.CustList;

public final class ParsedType {
    private final StringBuilder instruction = new StringBuilder();
    private int delta;
    private boolean ok;
    private int index;
    private boolean stop;
    private int count;

    public void parse(String _found) {
        firstChars(_found);
        if (ok) {
            return;
        }
        int len_ = _found.length();
        while (index < len_) {
            tryIncr(_found);
            if (stop) {
                return;
            }
        }
    }
    private void tryIncr(String _found) {
        int len_ = _found.length();
        if (count == 0 && _found.startsWith("...", index)) {
            instruction.append("...");
            delta = "...".length();
            ok = true;
            stop = true;
            return;
        }
        char ch_ = _found.charAt(index);
        incrOrDecr(ch_);
        if (count != 0) {
            index++;
            instruction.append(ch_);
            return;
        }

        if (ch_ == '.') {
            index++;
            int next_ = StringExpUtil.nextPrintChar(index, len_, _found);
            if (next_ > -1) {
                instruction.append(ch_);
                instruction.append(_found, index, next_);
                index = next_;
                return;
            }
        }
        if (StringExpUtil.isTypeLeafChar(ch_)) {
            typerLeafChar(_found, ch_);
            return;
        }
        if (ch_ == '[') {
            index++;
            int next_ = StringExpUtil.nextPrintChar(index, len_, _found);
            instruction.append(ch_);
            if (_found.startsWith("]", next_)) {
                array(_found, next_);
                return;
            }
        }
        if (ch_ != '>') {
            stop = true;
            return;
        }
        index++;
        int next_ = StringExpUtil.nextPrintChar(index, len_, _found);
        instruction.append(ch_);
        if (_found.startsWith(".", next_) || _found.startsWith("[", next_)) {
            instruction.append(_found, index, next_);
            index = next_;
            return;
        }
        if (next_ > -1 && StringExpUtil.isTypeLeafChar(_found.charAt(next_))) {
            ok = true;
            stop = true;
            return;
        }
        stop = true;
    }

    private void array(String _found, int _next) {
        int len_ = _found.length();
        instruction.append(_found, index, _next + 1);
        int nextTwo_ = StringExpUtil.nextPrintChar(_next + 1, len_, _found);
        if (_found.startsWith("...", nextTwo_) || StringExpUtil.nextCharIs(_found, nextTwo_, len_, '[')) {
            instruction.append(_found, _next + 1, nextTwo_);
            index = nextTwo_;
            return;
        }
        if (nextTwo_ >= 0) {
            if (StringExpUtil.isTypeLeafChar(_found.charAt(nextTwo_))) {
                ok = true;
            }
            stop = true;
            return;
        }
        index = _next + 1;
    }

    private void typerLeafChar(String _found, char _ch) {
        int len_ = _found.length();
        index++;
        instruction.append(_ch);
        int next_ = StringExpUtil.nextPrintChar(index, len_, _found);
        if (next_ > index) {
            char nextCh_ = _found.charAt(next_);
            if (StringExpUtil.isTypeLeafChar(nextCh_)) {
                ok = true;
                stop = true;
                return;
            }
            if (nextCh_ == '.'
                    || nextCh_ == '['
                    || nextCh_ == '<') {
                instruction.append(_found,index,next_);
                index = next_;
            }
        }
    }

    private void incrOrDecr(char _ch) {
        if (_ch == '<') {
            count++;
        }
        if (_ch == '>') {
            count--;
        }
    }

    private void firstChars(String _found) {
        int len_ = _found.length();
        while (index < len_) {
            char ch_ = _found.charAt(index);
            if (ch_ == '<' || ch_ == '[' || ch_ == '(' || _found.startsWith("...", index)) {
                return;
            }
            instruction.append(ch_);
            index++;
            if (StringExpUtil.isTypeLeafChar(ch_)) {
                int next_ = StringExpUtil.nextPrintChar(index, len_, _found);
                if (next_ > index && StringExpUtil.isTypeLeafChar(_found.charAt(next_))) {
                    ok = true;
                    return;
                }
            }
        }
    }

    public boolean isOk(CustList<String> _excludedWords) {
        if (!ok) {
            return false;
        }
        return AnaPartTypeUtil.isCorrectType(instruction.substring(0,instruction.length()-delta),_excludedWords);
    }

    public boolean isOk() {
        return ok;
    }

    public StringBuilder getInstruction() {
        return instruction;
    }

    public int getCurrent() {
        return instruction.length();
    }

}
