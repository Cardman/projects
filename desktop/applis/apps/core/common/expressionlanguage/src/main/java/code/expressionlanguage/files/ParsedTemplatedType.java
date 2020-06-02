package code.expressionlanguage.files;

public final class ParsedTemplatedType {
    private StringBuilder instruction;
    private int current;

    ParsedTemplatedType(StringBuilder _instruction, int _current) {
        instruction = _instruction;
        current = _current;
    }
    public void parse(String _file) {
        int len_ = _file.length();
        int count_ = 0;
        while (current < len_) {
            char ch_ = _file.charAt(current);
            instruction.append(ch_);
            if (ch_ == '<') {
                count_++;
            }
            if (ch_ == '>') {
                count_--;
            }
            current++;
            if (count_ == 0) {
                break;
            }
        }
    }

    StringBuilder getInstruction() {
        return instruction;
    }

    public int getCurrent() {
        return current;
    }
}
