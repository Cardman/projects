package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.types.AnaPartTypeUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.util.CustList;

public final class ParsedType {
    private final StringBuilder instruction = new StringBuilder();
    private int delta;
    private boolean ok;

    public void parse(String _found) {
        int len_ = _found.length();
        int i_ = 0;
        while (i_ < len_) {
            char ch_ = _found.charAt(i_);
            if (ch_ == '<') {
                break;
            }
            if (ch_ == '[') {
                break;
            }
            if (ch_ == '(') {
                break;
            }
            if (_found.startsWith("...",i_)) {
                break;
            }
            instruction.append(ch_);
            i_++;
            if (StringExpUtil.isTypeLeafChar(ch_)) {
                int next_ = StringExpUtil.nextPrintChar(i_, len_, _found);
                if (next_ > i_ && StringExpUtil.isTypeLeafChar(_found.charAt(next_))) {
                    ok = true;
                    break;
                }
            }
        }
        if (ok) {
            return;
        }
        int count_ = 0;
        while (i_ < len_) {
            if (count_ == 0) {
                if (_found.startsWith("...",i_)) {
                    instruction.append("...");
                    delta = "...".length();
                    ok = true;
                    break;
                }
            }
            char ch_ = _found.charAt(i_);
            if (ch_ == '<') {
                count_++;
            }
            if (ch_ == '>') {
                count_--;
            }
            if (count_ == 0) {

                if (ch_ == '.') {
                    i_++;
                    int next_ = StringExpUtil.nextPrintChar(i_, len_, _found);
                    if (next_ > -1) {
                        instruction.append(ch_);
                        instruction.append(_found,i_,next_);
                        i_ = next_;
                        continue;
                    }
                }
                if (StringExpUtil.isTypeLeafChar(ch_)) {
                    i_++;
                    instruction.append(ch_);
                    int next_ = StringExpUtil.nextPrintChar(i_, len_, _found);
                    if (next_ > i_) {
                        char nextCh_ = _found.charAt(next_);
                        if (StringExpUtil.isTypeLeafChar(nextCh_)) {
                            ok = true;
                            break;
                        }
                        if (nextCh_ == '.'
                            || nextCh_ == '['
                            || nextCh_ == '<') {
                            instruction.append(_found,i_,next_);
                            i_ = next_;
                        }
                    }
                    continue;
                }
                if (ch_ == '[') {
                    i_++;
                    int next_ = StringExpUtil.nextPrintChar(i_, len_, _found);
                    instruction.append(ch_);
                    if (_found.startsWith("]",next_)) {
                        instruction.append(_found,i_,next_+1);
                        int nextTwo_ = StringExpUtil.nextPrintChar(next_+1, len_, _found);
                        if (_found.startsWith("...",nextTwo_) || StringExpUtil.nextCharIs(_found, nextTwo_, len_, '[')) {
                            instruction.append(_found,next_+1,nextTwo_);
                            i_ = nextTwo_;
                            continue;
                        }
                        if (nextTwo_ >= 0) {
                            if (StringExpUtil.isTypeLeafChar(_found.charAt(nextTwo_))) {
                                ok = true;
                            }
                            break;
                        }
                        i_ = next_+1;
                        continue;
                    }
                }
                if (ch_ == '>') {
                    i_++;
                    int next_ = StringExpUtil.nextPrintChar(i_, len_, _found);
                    instruction.append(ch_);
                    if (_found.startsWith(".",next_) ||_found.startsWith("[",next_)) {
                        instruction.append(_found,i_,next_);
                        i_ = next_;
                        continue;
                    }
                    if (next_ > -1) {
                        if (StringExpUtil.isTypeLeafChar(_found.charAt(next_))) {
                            ok = true;
                            break;
                        }
                    }
                }
                break;
            }
            i_++;
            instruction.append(ch_);
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
