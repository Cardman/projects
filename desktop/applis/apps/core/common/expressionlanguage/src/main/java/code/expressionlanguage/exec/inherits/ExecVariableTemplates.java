package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassArgumentMatching;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.StringMap;

public final class ExecVariableTemplates {
    private ExecVariableTemplates() {
    }

    public static Argument getArgValue(AbstractWrapper _w, ContextEl _context, StackCall _stackCall) {
        return new Argument(getValue(_w, _context, _stackCall));
    }

    public static Struct getValue(AbstractWrapper _w, ContextEl _context, StackCall _stackCall) {
        if (_w == null) {
            return NullStruct.NULL_VALUE;
        }
        return Argument.getNull(_w.getValue(_stackCall, _context));
    }

    public static Argument getIndexLoop(ContextEl _context, ExecVariableContent _varCont, StackCall _stackCall) {
        return getIndexLoop(_context, _varCont, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getVars(), _stackCall);
    }

    public static Argument getIndexLoop(ContextEl _context, ExecVariableContent _varCont, Cache _cache, StringMap<LoopVariable> _vars, StackCall _stackCall) {
        return getIndexLoop(_context, _varCont.getVariableName(), _varCont.getDeep(), _cache, _vars, _stackCall);
    }

    public static Argument getIndexLoop(ContextEl _context, String _val, int _deep, Cache _cache, StringMap<LoopVariable> _vars, StackCall _stackCall) {
        LgNames stds_ = _context.getStandards();
        if (_cache != null) {
            LoopVariable loopVar_ = _cache.getLoopVar(_val,_deep);
            if (loopVar_ != null) {
                byte cast_ = ClassArgumentMatching.getPrimitiveCast(loopVar_.getIndexClassName(), _context.getStandards().getPrimTypes());
                LongStruct str_ = new LongStruct(loopVar_.getIndex());
                Struct value_ = NumParsers.convertToInt(cast_, str_);
                return new Argument(value_);
            }
        }
        LoopVariable locVar_ = _vars.getVal(_val);
        if (locVar_ == null) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, npe_, _stackCall)));
            return new Argument(new IntStruct(0));
        }
        byte cast_ = ClassArgumentMatching.getPrimitiveCast(locVar_.getIndexClassName(), _context.getStandards().getPrimTypes());
        LongStruct str_ = new LongStruct(locVar_.getIndex());
        Struct value_ = NumParsers.convertToInt(cast_, str_);
        return new Argument(value_);
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

    public static Argument getWrapValue(ContextEl _context, String _val, int _deep, Cache _cache, StringMap<AbstractWrapper> _refParams, StackCall _stackCall) {
        AbstractWrapper wrapper_ = getWrapper(_val, _deep, _cache, _refParams);
        return new Argument(getValue(wrapper_, _context, _stackCall));
    }

    public static AbstractWrapper getWrapper(ExecVariableContent _varCont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        return getWrapper(_varCont, ip_.getCache(), _stack.getLastPage().getRefParams());
    }

    public static AbstractWrapper getWrapper(ExecVariableContent _varCont, Cache _cache, StringMap<AbstractWrapper> _refParams) {
        return getWrapper(_varCont.getVariableName(),_varCont.getDeep(), _cache, _refParams);
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

    public static Argument getValueVar(String _val, StringMap<LocalVariable> _valueVars, ContextEl _context, StackCall _stackCall) {
        LocalVariable locVar_ = _valueVars.getVal(_val);
        if (locVar_ == null) {
            LgNames stds_ = _context.getStandards();
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, npe_, _stackCall)));
            return new Argument();
        }
        return new Argument(locVar_.getStruct());
    }

    public static Argument setWrapValue(ContextEl _context, String _val, Argument _value, int _deep, Cache _cache, StringMap<AbstractWrapper> _refParams, StackCall _stackCall) {
        AbstractWrapper wr_ = getWrapper(_val,_deep,_cache,_refParams);
        return trySetArgument(_context,_value,wr_, _stackCall);
    }

    public static Argument checkSet(ContextEl _conf, LocalVariable _loc, Argument _right, StackCall _stackCall) {
        String formattedClassVar_ = _loc.getClassName();
        if (!ExecInheritsAdv.checkQuick(formattedClassVar_, _right.getStruct().getClassName(_conf), _conf, _stackCall)) {
            return Argument.createVoid();
        }
        _loc.setStruct(_right.getStruct());
        return _right;
    }

    public static Argument trySetArgument(ContextEl _conf, Argument _res, ArgumentsPair _pair, StackCall _stackCall) {
        AbstractWrapper wrapper_ = _pair.getWrapper();
        return trySetArgument(_conf, _res, wrapper_, _stackCall);
    }

    private static Argument trySetArgument(ContextEl _conf, Argument _res, AbstractWrapper _wrapper, StackCall _stackCall) {
        if (_wrapper == null || _conf.callsOrException(_stackCall)) {
            return _res;
        }
        _wrapper.setValue(_stackCall, _conf, _res);
        return _res;
    }
}
