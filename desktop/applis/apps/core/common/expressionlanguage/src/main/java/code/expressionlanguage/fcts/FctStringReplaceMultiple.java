package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AliasReflection;
import code.expressionlanguage.structs.*;
import code.util.Replacement;
import code.util.core.StringUtil;

public final class FctStringReplaceMultiple implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return replaceMultiple((StringStruct) _instance,_args[0]);
    }

    private static Struct replaceMultiple(StringStruct _st, Struct _seps) {
        if (!(_seps instanceof ArrayStruct)) {
            return null;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        int lenSeps_ = arrSep_.getLength();
        Replacement[] seps_ = new Replacement[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrSep_.get(i);
            if (!(curSep_ instanceof ReplacementStruct)) {
                return null;
            }
            seps_[i] = NumParsers.getReplacement(curSep_).getInstance();
            if (seps_[i].getNewString() == null) {
                return null;
            }
            if (seps_[i].getOldString() == null) {
                return null;
            }
        }
        return new StringStruct(StringUtil.replaceMult(_st.getInstance(), seps_));
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return replaceMultiple((StringStruct) _instance,_firstArgs.getArgumentWrappers().get(0).getValue().getStruct(),_cont,_stackCall);
    }

    private static ArgumentWrapper replaceMultiple(StringStruct _st, Struct _seps, ContextEl _context, StackCall _stackCall) {
        if (!(_seps instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        int lenSeps_ = arrSep_.getLength();
        Replacement[] seps_ = new Replacement[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrSep_.get(i);
            if (!(curSep_ instanceof ReplacementStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_context, _stackCall)));
                return new ArgumentWrapper(NullStruct.NULL_VALUE);
            }
            seps_[i] = NumParsers.getReplacement(curSep_).getInstance();
            if (seps_[i].getNewString() == null) {
                _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_context, _stackCall)));
                return new ArgumentWrapper(NullStruct.NULL_VALUE);
            }
            if (seps_[i].getOldString() == null) {
                _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_context, _stackCall)));
                return new ArgumentWrapper(NullStruct.NULL_VALUE);
            }
        }
        return new ArgumentWrapper(new StringStruct(StringUtil.replaceMult(_st.getInstance(), seps_)));
    }
}
