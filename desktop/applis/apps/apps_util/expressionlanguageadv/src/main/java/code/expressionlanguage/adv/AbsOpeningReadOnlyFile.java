package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;

public interface AbsOpeningReadOnlyFile {
    void openFile(AbsDebuggerGui _curr, ResultContext _res, String _str, String _content);
}
