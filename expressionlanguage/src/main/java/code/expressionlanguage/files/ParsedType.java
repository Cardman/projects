package code.expressionlanguage.files;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.types.PartTypeUtil;
import code.util.CustList;

public final class ParsedType {
    private StringBuilder instruction = new StringBuilder();
    private int delta;
    private boolean ok;

    public void parse(String _file) {
        int len_ = _file.length();
        int i_ = 0;
        while (i_ < len_) {
            char ch_ = _file.charAt(i_);
            if (ch_ == '<') {
                break;
            }
            if (ch_ == '[') {
                break;
            }
            if (ch_ == '(') {
                break;
            }
            if (_file.startsWith("...",i_)) {
                break;
            }
            instruction.append(ch_);
            i_++;
            if (ch_ == '.') {
                continue;
            }
            if (StringExpUtil.isTypeLeafChar(ch_)) {
                int next_ = StringExpUtil.nextPrintChar(i_, len_, _file);
                if (next_ > i_ && StringExpUtil.isTypeLeafChar(_file.charAt(next_))) {
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
                if (_file.startsWith("...",i_)) {
                    instruction.append("...");
                    delta = "...".length();
                    ok = true;
                    break;
                }
            }
            char ch_ = _file.charAt(i_);
            if (ch_ == '<') {
                count_++;
            }
            if (ch_ == '>') {
                count_--;
            }
            if (count_ == 0) {

                if (ch_ == '.') {
                    i_++;
                    int next_ = StringExpUtil.nextPrintChar(i_, len_, _file);
                    if (next_ > -1) {
                        instruction.append(ch_);
                        instruction.append(_file,i_,next_);
                        i_ = next_;
                        continue;
                    }
                }
                if (StringExpUtil.isTypeLeafChar(ch_)) {
                    i_++;
                    instruction.append(ch_);
                    int next_ = StringExpUtil.nextPrintChar(i_, len_, _file);
                    if (next_ > i_ && StringExpUtil.isTypeLeafChar(_file.charAt(next_))) {
                        ok = true;
                        break;
                    }
                    continue;
                }
                if (ch_ == '[') {
                    i_++;
                    int next_ = StringExpUtil.nextPrintChar(i_, len_, _file);
                    instruction.append(ch_);
                    if (_file.startsWith("]",next_)) {
                        instruction.append(_file,i_,next_+1);
                        int nextTwo_ = StringExpUtil.nextPrintChar(next_+1, len_, _file);
                        if (nextTwo_ >= 0) {
                            if (_file.startsWith("...",nextTwo_)) {
                                instruction.append(_file,next_+1,nextTwo_);
                                i_ = nextTwo_;
                                continue;
                            }
                            if (!StringExpUtil.nextCharIs(_file, nextTwo_, len_, '[')) {
                                if (StringExpUtil.isTypeLeafChar(_file.charAt(nextTwo_))) {
                                    ok = true;
                                }
                                break;
                            }
                            instruction.append(_file,next_+1,nextTwo_);
                            i_ = nextTwo_;
                            continue;
                        }
                        i_ = next_+1;
                        continue;
                    }
                }
                if (ch_ == '>') {
                    i_++;
                    int next_ = StringExpUtil.nextPrintChar(i_, len_, _file);
                    instruction.append(ch_);
                    if (_file.startsWith(".",next_) ||_file.startsWith("[",next_)) {
                        instruction.append(_file,i_,next_);
                        i_ = next_;
                        continue;
                    }
                    if (next_ > -1) {
                        if (StringExpUtil.isTypeLeafChar(_file.charAt(next_))) {
                            ok = true;
                            break;
                        }
                    }
                }
                ok = false;
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
        return PartTypeUtil.isCorrectType(instruction.substring(0,instruction.length()-delta),_excludedWords);
    }

    public boolean isOk() {
        return ok;
    }

    StringBuilder getInstruction() {
        return instruction;
    }

    public int getCurrent() {
        return instruction.length();
    }

}
