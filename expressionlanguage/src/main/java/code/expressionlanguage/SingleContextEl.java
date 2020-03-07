package code.expressionlanguage;

import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;

public final class SingleContextEl extends ContextEl {

    private Initializer init;

    public SingleContextEl(int _stackOverFlow, DefaultLockingClass _lock,
              Initializer _init, Options _options, AnalysisMessages _mess, KeyWords _keyWords, LgNames _stds, int _tabWidth) {
        super(false,_stackOverFlow, _lock, _options, _mess, _keyWords, _stds, _tabWidth);
        init = _init;
    }
    @Override
    public void initError() {
        setMemoryError(new ErrorStruct(this, getStandards().getAliasError()));
    }
    @Override
    public Initializer getInit() {
        return init;
    }
}
