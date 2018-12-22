package code.expressionlanguage;

import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;

public final class SingleContextEl extends ContextEl {

    private Initializer init;

    public SingleContextEl(LgNames _stds) {
        super(_stds, DEFAULT_TAB_WIDTH);
    }

    public SingleContextEl(int _stackOverFlow, DefaultLockingClass _lock,
            Initializer _init, Options _options, KeyWords _keyWords, LgNames _stds) {
        super(_stackOverFlow, _lock, _options, _keyWords, _stds, DEFAULT_TAB_WIDTH);
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
