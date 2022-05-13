package code.expressionlanguage.analyze.files;

import code.util.core.StringUtil;

public final class ParsedLoopArrIndex {
    private static final char BEGIN_ARRAY = '[';
    private static final char END_ARRAY = ']';
    private int indexClassOffest;
    private String indexClassName = "";
    private String exp;
    private boolean okIndex = true;

    public ParsedLoopArrIndex(String _e, int _i) {
        exp = _e;
        indexClassOffest = _i;
        parse();
    }

    private void parse() {
        if (exp.trim().indexOf(BEGIN_ARRAY) == 0) {
            int endArr_ = exp.indexOf(END_ARRAY);
            if (endArr_ < 0) {
                okIndex = false;
            } else {
                int begin_ = exp.indexOf(BEGIN_ARRAY);
                indexClassName = exp.substring(begin_ + 1, endArr_);
                indexClassOffest += begin_ + 1;
                indexClassOffest += StringUtil.getFirstPrintableCharIndex(indexClassName);
                exp = exp.substring(endArr_ + 1);
            }
        } else {
            indexClassOffest += StringUtil.getFirstPrintableCharIndex(exp) + 1;
        }
    }

    public int getIndexClassOffest() {
        return indexClassOffest;
    }

    public String getExp() {
        return exp;
    }

    public String getIndexClassName() {
        return indexClassName;
    }

    public boolean isOkIndex() {
        return okIndex;
    }
}
