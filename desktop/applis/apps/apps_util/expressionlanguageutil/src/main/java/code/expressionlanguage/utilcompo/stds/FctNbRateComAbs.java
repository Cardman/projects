package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.CharSequenceStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RateStruct;

public abstract class FctNbRateComAbs implements AnaStdCaller {
    private final AbsFctRate manageStr;

    protected FctNbRateComAbs(AbsFctRate _m) {
        this.manageStr = _m;
    }

    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return parseAsInfo(_args[0]);
    }

    public RateStruct parseAsInfo(Struct _arg) {
        if (!(_arg instanceof CharSequenceStruct)) {
            return null;
        }
        String one_ = NumParsers.getCharSeq(_arg).toStringInstance();
        if (!manageStr.valid(one_)) {
            return null;
        }
        return manageStr.build(one_);
    }

    public AbsFctRate getManageStr() {
        return manageStr;
    }
}
