package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.KeyWords;
import code.util.StringList;
import code.util.core.StringUtil;

public final class LambdaFieldChoice {
    private String fieldName;
    private int index;
    private int sum;
    private boolean accessBase = true;
    private boolean accessSuper = true;

    public LambdaFieldChoice(int _s, String _na) {
        sum = _s;
        fieldName = _na;
    }
    public void incr(StringList _args, int _len,
                     AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
        index = 3;
        if (index < _len && StringUtil.quickEq(fieldName, keyWordSuper_)) {
            fieldName = _args.get(index).trim();
            sum += _args.get(index-1).length() + 1;
            sum += StringExpUtil.getOffset(_args.get(index));
            index++;
            accessBase = false;
        } else if (index < _len && StringUtil.quickEq(fieldName, keyWordThat_) || index < _len && StringUtil.quickEq(fieldName, keyWordSuperaccess_)) {
            fieldName = _args.get(index).trim();
            sum += _args.get(index - 1).length() + 1;
            sum += StringExpUtil.getOffset(_args.get(index));
            index++;
        } else if (index < _len && StringUtil.quickEq(fieldName, keyWordClasschoice_)) {
            fieldName = _args.get(index).trim();
            sum += _args.get(index - 1).length() + 1;
            sum += StringExpUtil.getOffset(_args.get(index));
            index++;
            accessSuper = false;
        } else {
            sum += StringExpUtil.getOffset(_args.get(2));
        }
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getIndex() {
        return index;
    }

    public int getSum() {
        return sum;
    }

    public boolean isAccessSuper() {
        return accessSuper;
    }

    public boolean isAccessBase() {
        return accessBase;
    }
}
