package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.common.Delimiters;

public interface AbstractProcessKeyWord {
    void processInternKeyWord(String _exp, int _fr, Delimiters _d, ResultAfterInstKeyWord _out);
}
