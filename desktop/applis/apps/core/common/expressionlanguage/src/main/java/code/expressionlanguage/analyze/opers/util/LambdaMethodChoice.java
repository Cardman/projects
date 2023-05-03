package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.opers.AnaLambdaMethodContent;
import code.expressionlanguage.options.KeyWords;
import code.util.StringList;
import code.util.core.StringUtil;

public final class LambdaMethodChoice {
    private String name;
    private int index;
    private boolean staticChoiceMethod;
    private boolean baseAccess = true;
    private boolean accessSuper = true;
    private final AnaLambdaMethodContent lambdaMethodContent;
    private int sum;
    public LambdaMethodChoice(int _s, String _na, AnaLambdaMethodContent _cont) {
        sum = _s;
        name = _na;
        lambdaMethodContent = _cont;
    }
    public void incr(StringList _args, int _len,
                     AnalyzedPageEl _page) {
        index = 2;
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
        if (index < _len && StringUtil.quickEq(name, keyWordSuper_)) {
            name = _args.get(index).trim();
            sum += _args.get(index-1).length() + 1;
            sum += StringExpUtil.getOffset(_args.get(index));
            index++;
            staticChoiceMethod = true;
            baseAccess = false;
        } else if (index < _len && StringUtil.quickEq(name, keyWordThat_) || index < _len && StringUtil.quickEq(name, keyWordSuperaccess_)) {
            name = _args.get(index).trim();
            sum += _args.get(index-1).length() + 1;
            sum += StringExpUtil.getOffset(_args.get(index));
            index++;
            staticChoiceMethod = true;
        } else if (index < _len && StringUtil.quickEq(name, keyWordClasschoice_)) {
            name = _args.get(index).trim();
            sum += _args.get(index-1).length() + 1;
            sum += StringExpUtil.getOffset(_args.get(index));
            index++;
            staticChoiceMethod = true;
            accessSuper = false;
        } else {
            lambdaMethodContent.setPolymorph(true);
            sum += StringExpUtil.getOffset(_args.get(1));
        }
    }

    public String getName() {
        return name;
    }

    public int getSum() {
        return sum;
    }

    public int getIndex() {
        return index;
    }

    public boolean isAccessSuper() {
        return accessSuper;
    }

    public boolean isBaseAccess() {
        return baseAccess;
    }

    public boolean isStaticChoiceMethod() {
        return staticChoiceMethod;
    }
}
