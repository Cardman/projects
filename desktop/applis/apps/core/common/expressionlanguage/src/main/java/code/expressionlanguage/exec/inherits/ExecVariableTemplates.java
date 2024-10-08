package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassArgumentMatching;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.StringMap;

public final class ExecVariableTemplates {
    private ExecVariableTemplates() {
    }

    public static Struct getArgValue(AbstractWrapper _w, ContextEl _context, StackCall _stackCall) {
        return getValue(_w, _context, _stackCall);
    }

    public static Struct getValue(AbstractWrapper _w, ContextEl _context, StackCall _stackCall) {
        if (_w == null) {
            return NullStruct.NULL_VALUE;
        }
        return ArgumentListCall.getNull(_w.getValue(_stackCall, _context));
    }

    public static Struct getIndexLoop(ContextEl _context, ExecVariableContent _varCont, StackCall _stackCall) {
        return getIndexLoop(_context, _varCont, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getVars(), _stackCall);
    }

    public static Struct getIndexLoop(ContextEl _context, ExecVariableContent _varCont, Cache _cache, StringMap<LoopVariable> _vars, StackCall _stackCall) {
        return getIndexLoop(_context, _varCont.getVariableName(), _varCont.getDeep(), _cache, _vars, _stackCall);
    }

    public static Struct getIndexLoop(ContextEl _context, String _val, int _deep, Cache _cache, StringMap<LoopVariable> _vars, StackCall _stackCall) {
        LgNames stds_ = _context.getStandards();
        if (_cache != null) {
            LoopVariable loopVar_ = _cache.getLoopVar(_val,_deep);
            if (loopVar_ != null) {
                byte cast_ = ClassArgumentMatching.getPrimitiveCast(loopVar_.getIndexClassName(), _context.getStandards().getPrimTypes());
                LongStruct str_ = new LongStruct(loopVar_.getIndex());
                return NumParsers.convertToInt(cast_, str_);
            }
        }
        LoopVariable locVar_ = _vars.getVal(_val);
        if (locVar_ == null) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, npe_, _stackCall)));
            return new IntStruct(0);
        }
        byte cast_ = ClassArgumentMatching.getPrimitiveCast(locVar_.getIndexClassName(), _context.getStandards().getPrimTypes());
        LongStruct str_ = new LongStruct(locVar_.getIndex());
        return NumParsers.convertToInt(cast_, str_);
    }

    public static void incrIndexLoop(ContextEl _context, String _val, int _deep, Cache _cache, StringMap<LoopVariable> _vars, StackCall _stackCall) {
        if (_context.callsOrException(_stackCall)) {
            return;
        }
        if (_cache != null) {
            LoopVariable loopVar_ = _cache.getLoopVar(_val, _deep);
            if (loopVar_ != null) {
                loopVar_.setIndex(loopVar_.getIndex() + 1);
                return;
            }
        }
        LoopVariable locVar_ = _vars.getVal(_val);
        LgNames stds_ = _context.getStandards();
        if (locVar_ == null) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, npe_, _stackCall)));
            return;
        }
        locVar_.setIndex(locVar_.getIndex() + 1);
    }

    public static Struct getWrapValue(ContextEl _context, String _val, int _deep, Cache _cache, StringMap<AbstractWrapper> _refParams, StackCall _stackCall) {
        AbstractWrapper wrapper_ = getWrapper(_val, _deep, _cache, _refParams);
        return getValue(wrapper_, _context, _stackCall);
    }

    public static AbstractWrapper getWrapper(boolean _set,ExecVariableContent _varCont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        return getWrapper(_set,_varCont, ip_.getCache(), _stack.getLastPage().getRefParams());
    }

    public static AbstractWrapper getWrapper(boolean _set,ExecVariableContent _varCont, Cache _cache, StringMap<AbstractWrapper> _refParams) {
        AbstractWrapper w_ = getWrapper(_varCont.getVariableName(), _varCont.getDeep(), _cache, _refParams);
        if (_set && w_ instanceof VariableWrapper && !_varCont.isRef() && _varCont.getDeep() < 0) {
            VariableWrapper ch_ = new VariableWrapper(LocalVariable.newLocalVariable(w_.getValue(null, null), w_.getClassName( null)));
            _refParams.set(_varCont.getVariableName(), ch_);
            return ch_;
        }
        return w_;
    }

    public static AbstractWrapper getWrapper(String _val, int _deep, Cache _cache, StringMap<AbstractWrapper> _refParams) {
        if (_cache != null) {
            AbstractWrapper wr_ = _cache.getLocalWrapper(_val, _deep);
            if (wr_ != null) {
                return wr_;
            }
        }
        return _refParams.getVal(_val);
    }

    public static Struct getValueVar(String _val, StringMap<LocalVariable> _valueVars, ContextEl _context, StackCall _stackCall) {
        LocalVariable locVar_ = _valueVars.getVal(_val);
        if (locVar_ == null) {
            LgNames stds_ = _context.getStandards();
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, npe_, _stackCall)));
            return NullStruct.NULL_VALUE;
        }
        return locVar_.getStruct();
    }

    public static Struct setWrapValue(ContextEl _context, String _val, Struct _value, int _deep, Cache _cache, StringMap<AbstractWrapper> _refParams, StackCall _stackCall) {
        AbstractWrapper wr_ = getWrapper(_val,_deep,_cache,_refParams);
        return trySetArgument(_context,_value,wr_, _stackCall);
    }

    public static boolean checkSet(ContextEl _conf, LocalVariable _loc, Struct _right, StackCall _stackCall) {
        String formattedClassVar_ = _loc.getClassName();
        if (!ExecInheritsAdv.checkQuick(formattedClassVar_, _right.getClassName(_conf), _conf, _stackCall)) {
            return false;
        }
        _loc.setStruct(_right);
        return true;
    }

    public static Struct trySetArgument(ContextEl _conf, Struct _res, ArgumentsPair _pair, StackCall _stackCall) {
        AbstractWrapper wrapper_ = _pair.getWrapper();
        return trySetArgument(_conf, _res, wrapper_, _stackCall);
    }

    private static Struct trySetArgument(ContextEl _conf, Struct _res, AbstractWrapper _wrapper, StackCall _stackCall) {
        if (_wrapper == null || _conf.callsOrException(_stackCall)) {
            return _res;
        }
        _wrapper.setValue(_stackCall, _conf, _res);
        return _res;
    }
}
