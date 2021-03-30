package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.types.ExecPartTypeUtil;
import code.expressionlanguage.exec.types.ExecResultPartType;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.expressionlanguage.inherits.*;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.structs.*;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ExecTemplates {

    public static final String ARR_BEG_STRING = "[";
    public static final String TEMPLATE_SEP = ",";
    public static final String TEMPLATE_END = ">";
    public static final String TEMPLATE_BEGIN = "<";
    public static final String PREFIX_VAR_TYPE = "#";
    public static final String SUB_TYPE = "?";
    public static final String SUP_TYPE = "!";

    private static final String RETURN_LINE = "\n";

    private ExecTemplates(){
    }

    public static Struct newCustomArrayOrExc(String _className, Ints _dims, ContextEl _cont, StackCall _stackCall) {
        return newCustomArrayOrExc(new Ints(),_className,_dims,_cont, _stackCall);
    }
    public static Struct newCustomArrayOrExc(Ints _filter, String _className, Ints _dims, ContextEl _cont, StackCall _stackCall) {
        Ints dims_ = new Ints();
        String size_;
        LgNames lgNames_ = _cont.getStandards();
        size_ = lgNames_.getContent().getCoreNames().getAliasBadSize();
        if (_dims.isEmpty()) {
            return new ErrorStruct(_cont,size_, _stackCall);
        }
        int j_ = 0;
        for (int s: _dims) {
            if (s < 0) {
                if (_filter.isValidIndex(j_)) {
                    int off_ = _filter.get(j_);
                    _stackCall.setOffset(off_);
                }
                return new ErrorStruct(_cont, StringUtil.concat(Long.toString(s),"<0"),size_, _stackCall);
            }
            dims_.add(s);
            j_++;
        }
        return newCustomArray(_className, dims_, _cont);
    }
    public static ArrayStruct newCustomArray(String _className, Ints _dims, ContextEl _cont) {
        TreeMap<Ints,Struct> indexesArray_;
        indexesArray_ = new TreeMap<Ints,Struct>(new IndexesComparator());
        ArrayStruct output_ = new ArrayStruct(_dims.first(), StringExpUtil.getPrettyArrayType(_className, _dims.size()));
        Ints dims_ = new Ints();
        indexesArray_.put(new Ints(), output_);
        int glDim_ = _dims.size();
        int i_ = IndexConstants.FIRST_INDEX;
        Struct defClass_ = ExecClassArgumentMatching.defaultValue(_className, _cont);
        for (int i : _dims) {
            dims_.add(i);
            int nextIndex_ = i_ + 1;
            if (nextIndex_ >= glDim_) {
                for (Ints k: dims_.getAllIndexes()) {
                    indexesArray_.put(k, defClass_);
                }
                continue;
            }
            int curDim_ = _dims.get(nextIndex_);
            String formattedClass_ = StringExpUtil.getPrettyArrayType(_className, glDim_-nextIndex_);
            for (Ints k: dims_.getAllIndexes()) {
                Struct value_ = new ArrayStruct(curDim_, formattedClass_);
                indexesArray_.put(k, value_);
            }
            i_++;
        }
        for (EntryCust<Ints,Struct> e: indexesArray_.entryList()) {
            Ints key_ = e.getKey();
            Struct value_ = e.getValue();
            if (key_.isEmpty()) {
                continue;
            }
            Ints ind_ = new Ints(key_);
            ind_.removeQuicklyLast();
            int lastIndex_ = key_.last();
            Struct str_ = indexesArray_.getVal(ind_);
            ArrayStruct arr_ = ExecArrayFieldOperation.getArray(str_, _cont);
            trySet(arr_,lastIndex_,value_);
        }
        return output_;
    }
    static void trySet(ArrayStruct _arr, int _index, Struct _value) {
        if (_index < 0 || _index >= _arr.getLength()) {
            return;
        }
        _arr.set(_index, _value);
    }
    /** nb calls of getParent - super type - arg object
     use class parent of object
     */
    public static Struct getParent(int _nbAncestors, String _required, Struct _current, ContextEl _an, StackCall _stackCall) {
        String id_ = StringExpUtil.getIdFromAllTypes(_required);
        LgNames lgNames_ = _an.getStandards();
        Struct arg_ = _current;
        String cast_ = lgNames_.getContent().getCoreNames().getAliasCastType();
        if (_current != NullStruct.NULL_VALUE) {
            String className_ = _current.getClassName(_an);
            String cl_ = StringExpUtil.getIdFromAllTypes(className_);
            DimComp dimReq_ = StringExpUtil.getQuickComponentBaseType(id_);
            DimComp dimCurrent_ = StringExpUtil.getQuickComponentBaseType(cl_);
            int dCurrent_ = dimCurrent_.getDim();
            int dReq_ = dimReq_.getDim();
            String componentDim_ = dimReq_.getComponent();
            if (StringUtil.quickEq(componentDim_, _an.getStandards().getContent().getCoreNames().getAliasObject())) {
                if (dReq_ > dCurrent_) {
                    _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_an, getBadCastMessage(_required, className_), cast_, _stackCall)));
                    return NullStruct.NULL_VALUE;
                }
                return _current;
            }
            if (dReq_ != dCurrent_) {
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_an, getBadCastMessage(_required, className_), cast_, _stackCall)));
                return NullStruct.NULL_VALUE;
            }
            String dComp_ = dimCurrent_.getComponent();
            InheritedType in_ = getInheritedType(_an, cl_, dComp_);
            if (in_ != null) {
                if (!in_.isSubTypeOf(componentDim_,_an)) {
                    _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_an, getBadCastMessage(_required, className_), cast_, _stackCall)));
                    return NullStruct.NULL_VALUE;
                }
                return _current;
            }
            for (int i = 0; i < _nbAncestors; i++) {
                Struct enc_ = arg_;
                Struct par_ = enc_.getParent();
                _stackCall.getInitializingTypeInfos().addSensibleField(enc_, par_);
                arg_=par_;
            }
        }
        String npe_ = lgNames_.getContent().getCoreNames().getAliasNullPe();
        if (arg_ == NullStruct.NULL_VALUE) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_an, npe_, _stackCall)));
            return arg_;
        }
        Struct current_ = arg_;
        String className_ = current_.getClassName(_an);
        String cl_ = StringExpUtil.getIdFromAllTypes(className_);
        StringList list_ = new StringList();
        GeneType g_ = _an.getClassBody(cl_);
        while (hasToLookForParent(_an, id_, g_)) {
            if (StringUtil.contains(list_, cl_)) {
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_an, getBadCastMessage(_required, className_), cast_, _stackCall)));
                break;
            }
            list_.add(cl_);
            if (!(current_ instanceof WithParentStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_an, getBadCastMessage(_required, className_), cast_, _stackCall)));
                break;
            }
            Struct par_ = current_.getParent();
            _stackCall.getInitializingTypeInfos().addSensibleField(current_, par_);
            className_ = ((WithParentStruct)current_).getParentClassName();
            current_ = par_;
            cl_ = StringExpUtil.getIdFromAllTypes(className_);
            g_ = _an.getClassBody(cl_);
        }
        return Argument.getNull(current_);
    }

    private static InheritedType getInheritedType(ContextEl _an, String _cl, String _dComp) {
        PrimitiveType pr_ = _an.getStandards().getPrimitiveTypes().getVal(_dComp);
        GeneType g_ = _an.getClassBody(_dComp);
        return getInheritedType(_cl, pr_, g_);
    }

    private static InheritedType getInheritedType(String _cl, PrimitiveType _pr, GeneType _g) {
        boolean without_ = withoutInstance(_g);
        return getInheritedType(_cl, _pr, _g, without_);
    }

    private static InheritedType getInheritedType(String _cl, PrimitiveType _pr, GeneType _g, boolean _without) {
        InheritedType in_ = null;
        if (_pr != null) {
            in_ = _pr;
        } else {
            if (_cl.startsWith(Templates.ARR_BEG_STRING) || _without) {
                in_ = _g;
            }
        }
        return in_;
    }

    private static boolean withoutInstance(GeneType _g) {
        return _g != null && _g.withoutInstance();
    }

    static boolean hasToLookForParent(ContextEl _an, String _id, GeneType _g) {
        return _g!= null && !_g.isSubTypeOf(_id,_an);
    }

    /**Calls Templates.isCorrect*/
    public static String correctClassPartsDynamic(String _className, ContextEl _context) {
        ExecResultPartType className_ = ExecPartTypeUtil.processExec(_className, _context);
        String res_ = className_.getResult();
        if (res_.isEmpty()) {
            return "";
        }
        if (!ExecPartTypeUtil.checkParametersCount(className_, _context)){
            return "";
        }
        if (ExecPartTypeUtil.processAnalyzeConstraintsExec(className_, _context)) {
            return res_;
        }
        return "";
    }
    public static String correctClassPartsDynamicWildCard(String _className, ContextEl _context) {
        if (StringExpUtil.isWildCard(_className)) {
            return "";
        }
        CustList<String> allArgTypes_ = StringExpUtil.getAllTypes(_className).mid(1);
        return getMade(_className, _context, allArgTypes_);
    }
    public static String correctClassPartsDynamicNotWildCard(String _className, ContextEl _context) {
        CustList<String> allArgTypes_ = StringExpUtil.getAllTypes(_className).mid(1);
        return getMade(_className, _context, allArgTypes_);
    }

    private static String getMade(String _className, ContextEl _context, CustList<String> _allArgTypes) {
        String madeVarTypes_ = getMadeVarTypes(_className, new StringList(_allArgTypes), _context);
        if (madeVarTypes_ == null) {
            return "";
        }
        return madeVarTypes_;
    }

    public static boolean checkObject(String _param, Argument _arg, ContextEl _context, StackCall _stackCall) {
        Struct str_ = _arg.getStruct();
        byte cast_ = ExecClassArgumentMatching.getPrimitiveWrapCast(_param, _context.getStandards());
        _arg.setStruct(NumParsers.convertObject(cast_, str_));
        return checkQuick(_param, _arg, _context, _stackCall);
    }

    public static boolean checkQuick(String _param, Argument _arg, ContextEl _context, StackCall _stackCall) {
        Struct ex_ = checkObjectEx(_param,_arg,_context, _stackCall);
        if (ex_ != null) {
            _stackCall.setCallingState(new CustomFoundExc(ex_));
            return false;
        }
        return true;
    }

    public static Struct checkObjectEx(String _param, Argument _arg, ContextEl _context, StackCall _stackCall) {
        LgNames stds_ = _context.getStandards();
        ErrorType err_ = safeObject(_param, _arg.getStruct(), _context);
        if (err_ == ErrorType.CAST) {
            String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
            return new ErrorStruct(_context, getBadCastMessage(_param, _arg.getStruct().getClassName(_context)),cast_, _stackCall);
        }
        if (err_ == ErrorType.NPE) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            return new ErrorStruct(_context,npe_, _stackCall);
        }
        return null;
    }
    public static void setCheckedElements(CustList<Argument> _args, Struct _arr, ContextEl _context, StackCall _stackCall) {
        int len_ = _args.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            Argument chArg_ = _args.get(i);
            IntStruct ind_ = new IntStruct(i);
            setElement(_arr, ind_, chArg_.getStruct(), _context, _stackCall);
            if (_context.callsOrException(_stackCall)) {
                return;
            }
        }
    }

    public static String checkParams(ContextEl _conf, String _classNameFound, Identifiable _methodId,
                                     Argument _previous, CustList<Argument> _firstArgs, StackCall _stackCall) {
        LgNames stds_ = _conf.getStandards();
        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
        String classFormat_ = _classNameFound;
        if (!_methodId.isStaticMethod()) {
            String className_ = Argument.getNullableValue(_previous).getStruct().getClassName(_conf);
            classFormat_ = ExecInherits.getQuickFullTypeByBases(className_, classFormat_, _conf);
            if (classFormat_.isEmpty()) {
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(_classNameFound, className_), cast_, _stackCall)));
                return "";
            }
        }
        if (okArgsSet(_methodId, _firstArgs, _conf, _stackCall) != null) {
            return "";
        }
        return classFormat_;
    }

    private static String getBadCastMessage(String _classNameFound, String _className) {
        return StringUtil.concat(_className, RETURN_LINE, _classNameFound, RETURN_LINE);
    }

    public static FormattedParameters checkParams(ContextEl _conf, String _classNameFound, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _methodId,
                                                  Argument _previous, Cache _cache, ArgumentListCall _firstArgs,
                                                  Argument _right, MethodAccessKind _kind, StackCall _stackCall) {
        LgNames stds_ = _conf.getStandards();
        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
        String classFormat_ = _classNameFound;
        FormattedParameters f_ = new FormattedParameters();
        if (_kind == MethodAccessKind.INSTANCE) {
            String className_ = Argument.getNullableValue(_previous).getStruct().getClassName(_conf);
            classFormat_ = ExecInherits.getQuickFullTypeByBases(className_, classFormat_, _conf);
            if (classFormat_.isEmpty()) {
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(_classNameFound, className_), cast_, _stackCall)));
                return f_;
            }
        }
        Parameters parameters_ = okArgsSet(_rootBlock, _methodId, classFormat_, _cache, _firstArgs, _conf, _right, _kind != MethodAccessKind.STATIC, _stackCall);
        if (parameters_.getError() != null) {
            return f_;
        }
        f_.setParameters(parameters_);
        f_.setFormattedClass(classFormat_);
        return f_;
    }

    public static FormattedParameters checkParamsSw(ContextEl _conf, String _classNameFound, ExecRootBlock _rootBlock, ExecAbstractSwitchMethod _methodId,
                                                    Argument _previous, Cache _cache, ArgumentListCall _firstArgs,
                                                    MethodAccessKind _kind, StackCall _stackCall) {
        LgNames stds_ = _conf.getStandards();
        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
        String classFormat_ = _classNameFound;
        FormattedParameters f_ = new FormattedParameters();
        if (_kind == MethodAccessKind.INSTANCE) {
            String className_ = Argument.getNullableValue(_previous).getStruct().getClassName(_conf);
            classFormat_ = ExecInherits.getQuickFullTypeByBases(className_, classFormat_, _conf);
            if (classFormat_.isEmpty()) {
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(_classNameFound, className_), cast_, _stackCall)));
                return f_;
            }
        }
        Parameters parameters_ = okArgsSetSw(_rootBlock, _methodId, classFormat_, _cache, _conf, _stackCall,_firstArgs);
        if (parameters_.getError() != null) {
            return f_;
        }
        f_.setParameters(parameters_);
        f_.setFormattedClass(classFormat_);
        return f_;
    }

    public static Struct okArgsSet(Identifiable _id, CustList<Argument> _firstArgs, ContextEl _conf, StackCall _stackCall) {
        Struct ex_ = okArgsEx(_id, _firstArgs, _conf, _stackCall);
        if (ex_ != null) {
            _stackCall.setCallingState(new CustomFoundExc(ex_));
        }
        return ex_;
    }

    public static Parameters okArgsSet(ExecRootBlock _rootBlock, ExecNamedFunctionBlock _id, String _classNameFound, Cache _cache, ArgumentListCall _firstArgs, ContextEl _conf, Argument _right, boolean _hasFormat, StackCall _stackCall) {
        Parameters ex_ = okArgsEx(_rootBlock,_id, _classNameFound,_cache, _firstArgs, _conf, _right, _hasFormat, _stackCall);
        if (ex_.getError() != null) {
            _stackCall.setCallingState(new CustomFoundExc(ex_.getError()));
        }
        return ex_;
    }

    public static Parameters okArgsSetSw(ExecRootBlock _rootBlock, ExecAbstractSwitchMethod _id, String _classNameFound, Cache _cache, ContextEl _conf, StackCall _stackCall, ArgumentListCall _list) {
        CustList<Argument> arguments_ = _list.getArguments();
        if (arguments_.isEmpty()) {
            Parameters p_ = new Parameters();
            LgNames stds_ = _conf.getStandards();
            String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
            StringBuilder mess_ = countDiff(0, 1);
            ErrorStruct error_ = new ErrorStruct(_conf, mess_.toString(), cast_, _stackCall);
            _stackCall.setCallingState(new CustomFoundExc(error_));
            p_.setError(error_);
            return p_;
        }
        Parameters ex_ = okArgsExSw(_rootBlock,_id, _classNameFound,_cache, _conf, _stackCall, arguments_.first());
        if (ex_.getError() != null) {
            _stackCall.setCallingState(new CustomFoundExc(ex_.getError()));
        }
        return ex_;
    }

    private static Struct okArgsEx(Identifiable _id, CustList<Argument> _firstArgs, ContextEl _conf, StackCall _stackCall) {
        StringList params_ = new StringList();
        int i_ = 0;
        for (String c: _id.getParametersTypes()) {
            String c_ = c;
            if (i_ + 1 == _id.getParametersTypes().size() && _id.isVararg()) {
                c_ = StringExpUtil.getPrettyArrayType(c_);
            }
            params_.add(c_);
            i_++;
        }
        if (_firstArgs.size() != params_.size()) {
            LgNames stds_ = _conf.getStandards();
            String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
            StringBuilder mess_ = countDiff(_firstArgs.size(), params_.size());
            return new ErrorStruct(_conf,mess_.toString(),cast_, _stackCall);
        }
        i_ = IndexConstants.FIRST_INDEX;
        for (Argument a: _firstArgs) {
            String param_ = params_.get(i_);
            Struct ex_ = checkObjectEx(param_, a, _conf, _stackCall);
            if (ex_ != null) {
                return ex_;
            }
            i_++;
        }
        if (!_firstArgs.isEmpty()&&_id.isVararg()) {
            Struct str_ = _firstArgs.last().getStruct();
            if (str_ instanceof ArrayStruct) {
                ArrayStruct arr_ = (ArrayStruct) str_;
                for (Struct s: arr_.list()) {
                    ErrorType state_ = safeObjectArr(s, _conf, arr_);
                    if (state_ != ErrorType.NOTHING) {
                        return processError(_conf, arr_, s, state_, _stackCall);
                    }
                }
            }
        }
        return null;
    }
    private static Parameters okArgsEx(ExecRootBlock _rootBlock, ExecNamedFunctionBlock _id, String _classNameFound, Cache _cache, ArgumentListCall _firstArgs, ContextEl _conf, Argument _right, boolean _hasFormat, StackCall _stackCall) {
        Parameters p_ = new Parameters();
        possibleCheck(_rootBlock, _classNameFound, _cache, _conf, _stackCall, p_);
        if (p_.getError() != null) {
            return p_;
        }
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
//        CustList<Argument> args_ = _firstArgs.getArguments();
//        CustList<AbstractWrapper> wrappers_ = _firstArgs.getWrappers();
        if (_id == null) {
            if (argumentWrappers_.size() != 0) {
                LgNames stds_ = _conf.getStandards();
                String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                StringBuilder mess_ = countDiff(argumentWrappers_.size(), 0);
                p_.setError(new ErrorStruct(_conf,mess_.toString(),cast_, _stackCall));
                return p_;
            }
            return p_;
        }
        ParametersTypes params_ = fetchParamTypes(_rootBlock, _id, _classNameFound, _hasFormat);
//        for (Sizes s: new CustList<Sizes>(
//                new Sizes(args_.size(), params_.getTypes().size()),
//                new Sizes(wrappers_.size(), params_.getTypesRef().size())
//                )) {
//            if (s.getArg() != s.getParam()) {
//                LgNames stds_ = _conf.getStandards();
//                String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
//                StringBuilder mess_ = countDiff(s.getArg(), s.getParam());
//                p_.setError(new ErrorStruct(_conf,mess_.toString(),cast_, _stackCall));
//                return p_;
//            }
//
//        }
        for (Sizes s: new CustList<Sizes>(
                new Sizes(argumentWrappers_.size(), params_.getTypesAll().size())
        )) {
            if (s.getArg() != s.getParam()) {
                LgNames stds_ = _conf.getStandards();
                String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                StringBuilder mess_ = countDiff(s.getArg(), s.getParam());
                p_.setError(new ErrorStruct(_conf,mess_.toString(),cast_, _stackCall));
                return p_;
            }

        }
        int i_ = IndexConstants.FIRST_INDEX;
        CustList<Struct> values_ = new CustList<Struct>();
        for (ArgumentWrapper a:argumentWrappers_) {
            String param_ = params_.getTypesAll().get(i_);
            Argument a_ = a.getValue();
            if (a_ != null) {
                Struct ex_ = checkObjectEx(param_, a_, _conf, _stackCall);
                if (ex_ != null) {
                    p_.setError(ex_);
                    return p_;
                }
                Struct struct_ = a_.getStruct();
                values_.add(struct_);
                LocalVariable lv_ = LocalVariable.newLocalVariable(struct_,param_);
                p_.getRefParameters().addEntry(params_.getNamesAll().get(i_),new VariableWrapper(lv_));
            } else {
                AbstractWrapper w_ = a.getWrapper();
                Struct value_ = getValue(w_, _conf, _stackCall);
                values_.add(value_);
                Struct ex_ = checkObjectEx(param_, new Argument(value_), _conf, _stackCall);
                if (ex_ != null) {
                    p_.setError(ex_);
                    return p_;
                }
                p_.getRefParameters().addEntry(params_.getNamesAll().get(i_),getWrap(w_));
            }
            i_++;
        }
//        i_ = IndexConstants.FIRST_INDEX;
//        for (Argument a: args_) {
//            String param_ = params_.getTypes().get(i_);
//            Struct ex_ = checkObjectEx(param_, a, _conf, _stackCall);
//            if (ex_ != null) {
//                p_.setError(ex_);
//                return p_;
//            }
//            Struct struct_ = a.getStruct();
//            LocalVariable lv_ = LocalVariable.newLocalVariable(struct_,param_);
//            p_.getRefParameters().addEntry(params_.getNames().get(i_),new VariableWrapper(lv_));
//            i_++;
//        }
//        i_ = IndexConstants.FIRST_INDEX;
//        for (AbstractWrapper w: wrappers_) {
//            String param_ = params_.getTypesRef().get(i_);
//            Struct value_ = getValue(w, _conf, _stackCall);
//            Struct ex_ = checkObjectEx(param_, new Argument(value_), _conf, _stackCall);
//            if (ex_ != null) {
//                p_.setError(ex_);
//                return p_;
//            }
//            p_.getRefParameters().addEntry(params_.getNamesRef().get(i_),getWrap(w));
//            i_++;
//        }
        Struct str_ = null;
        if (!values_.isEmpty()&&params_.isVarargAll()) {
            str_ = values_.last();
        }
//        if (!args_.isEmpty()&&params_.isVararg()) {
//            str_ = args_.last().getStruct();
//        }
//        if (!wrappers_.isEmpty()&&params_.isVarargRef()) {
//            str_ = getValue(wrappers_.last(), _conf, _stackCall);
//        }
        if (str_ instanceof ArrayStruct) {
            ArrayStruct arr_ = (ArrayStruct) str_;
            for (Struct s: arr_.list()) {
                ErrorType state_ = safeObjectArr(s, _conf, arr_);
                if (state_ != ErrorType.NOTHING) {
                    Struct struct_ = processError(_conf, arr_, s, state_, _stackCall);
                    p_.setError(struct_);
                    return p_;
                }
            }
        }
        if (_right != null) {
            String type_ = _id.getImportedReturnType();
            type_ = ExecInherits.quickFormat(_rootBlock,_classNameFound, type_);
            Struct ex_ = checkObjectEx(type_, _right, _conf, _stackCall);
            if (ex_ != null) {
                p_.setError(ex_);
                return p_;
            }
            p_.setRight(_right);
            LocalVariable lv_ = LocalVariable.newLocalVariable(_right.getStruct(),type_);
            p_.getRefParameters().addEntry(_conf.getClasses().getKeyWordValue(),new VariableWrapper(lv_));
        }
        return p_;
    }
    private static Parameters okArgsExSw(ExecRootBlock _rootBlock, ExecAbstractSwitchMethod _id, String _classNameFound, Cache _cache, ContextEl _conf, StackCall _stackCall, Argument _value) {
        Parameters p_ = new Parameters();
        possibleCheck(_rootBlock, _classNameFound, _cache, _conf, _stackCall, p_);
        if (p_.getError() != null) {
            return p_;
        }
        String c_ = _id.getImportedParamType();
        c_ = ExecInherits.quickFormat(_rootBlock,_classNameFound, c_);
        Struct ex_ = checkObjectEx(c_, _value, _conf, _stackCall);
        if (ex_ != null) {
            p_.setError(ex_);
            return p_;
        }
        LocalVariable lv_ = LocalVariable.newLocalVariable(_value.getStruct(),_conf.getStandards().getCoreNames().getAliasObject());
        p_.getRefParameters().addEntry("",new VariableWrapper(lv_));
        return p_;
    }

    private static void possibleCheck(ExecRootBlock _rootBlock, String _classNameFound, Cache _cache, ContextEl _conf, StackCall _stackCall, Parameters _p) {
        if (_cache != null) {
            _cache.setCache(_rootBlock, _classNameFound, _conf, _stackCall);
            _p.setCache(_cache);
            Struct err_ = _cache.checkCache(_conf, _stackCall);
            if (err_ != null) {
                _p.setError(err_);
            }
        }
    }

    public static AbstractWrapper getWrap(AbstractWrapper _w) {
        if (_w == null) {
            return new VariableWrapper(LocalVariable.newLocalVariable(NullStruct.NULL_VALUE,""));
        }
        return _w;
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
    public static StringBuilder countDiff(int _argsCount, int _paramsCount) {
        StringBuilder mess_ = new StringBuilder();
        mess_.append(_argsCount);
        mess_.append("!=");
        mess_.append(_paramsCount);
        return mess_;
    }

    private static ParametersTypes fetchParamTypes(ExecRootBlock _rootBlock, ExecNamedFunctionBlock _id, String _classNameFound, boolean _hasFormat) {
        ParametersTypes parametersTypes_ = new ParametersTypes();
        StringList paramsAll_ = new StringList();
        StringList namesAll_ = new StringList();
        int i_ = 0;
        if (_hasFormat) {
            for (String c: _id.getImportedParametersTypes()) {
                String c_ = c;
                c_ = ExecInherits.quickFormat(_rootBlock,_classNameFound, c_);
                if (i_ + 1 == _id.getImportedParametersTypes().size() && _id.isVarargs()) {
                    c_ = StringExpUtil.getPrettyArrayType(c_);
                }
                paramsAll_.add(c_);
                namesAll_.add(_id.getParametersName(i_));
                i_++;
            }
        } else {
            for (String c: _id.getImportedParametersTypes()) {
                String c_ = c;
                if (i_ + 1 == _id.getImportedParametersTypes().size() && _id.isVarargs()) {
                    c_ = StringExpUtil.getPrettyArrayType(c_);
                }
                paramsAll_.add(c_);
                namesAll_.add(_id.getParametersName(i_));
                i_++;
            }
        }
        parametersTypes_.setNamesAll(namesAll_);
        parametersTypes_.setTypesAll(paramsAll_);
        if (_id.isVarargs()) {
            parametersTypes_.setVarargAll(true);
        }
        return parametersTypes_;
    }

    public static ArgumentListCall wrapAndCallDirect(ExecTypeFunction _pair, String _formatted, Argument _previous, CustList<Argument> _firstArgs, ContextEl _conf, MethodAccessKind _kind) {
        ArgumentListCall out_ = new ArgumentListCall();
        String classFormat_ = _formatted;
        if (_kind != null && !_previous.isNull()) {
            classFormat_ = ExecInherits.getQuickFullTypeByBases(_previous.getStruct().getClassName(_conf), classFormat_, _conf);
        }
        ExecRootBlock type_ = _pair.getType();
        ExecNamedFunctionBlock fct_ = _pair.getFct();
        if (fct_ == null) {
            return out_;
        }
        int i_ = 0;
        for (String c: fct_.getImportedParametersTypes()) {
            String c_ = c;
            c_ = ExecInherits.quickFormat(type_,classFormat_, c_);
            if (i_ + 1 == fct_.getImportedParametersTypes().size() && fct_.isVarargs()) {
                c_ = StringExpUtil.getPrettyArrayType(c_);
            }
            if (fct_.getParametersRef(i_)) {
                Struct struct_ = _firstArgs.get(i_).getStruct();
                LocalVariable local_ = LocalVariable.newLocalVariable(struct_, c_);
                VariableWrapper v_ = new VariableWrapper(local_);
//                out_.getWrappers().add(v_);
                out_.getArgumentWrappers().add(new ArgumentWrapper(null,v_));
            } else {
//                out_.getArguments().add(_firstArgs.get(i_));
                out_.getArgumentWrappers().add(new ArgumentWrapper(_firstArgs.get(i_),null));
            }
            i_++;
        }
        return out_;
    }

    public static ArgumentListCall wrapAndCallDirectSw(CustList<Argument> _firstArgs) {
        ArgumentListCall out_ = new ArgumentListCall();
        out_.addAllArgs(_firstArgs);
        return out_;
    }
    public static Parameters wrapAndCall(ExecTypeFunction _pair, String _formatted, Argument _previous, ContextEl _conf, StackCall _stackCall, ArgumentListCall _argList, Argument _right) {
        ExecNamedFunctionBlock fct_ = _pair.getFct();
        ExecRootBlock type_ = _pair.getType();
        Parameters ex_ = okArgsEx(type_, fct_, _formatted, null, _argList, _conf, _right, true, _stackCall);
        if (ex_.getError() != null) {
            _stackCall.setCallingState(new CustomFoundExc(ex_.getError()));
        } else {
            _stackCall.setCallingState(new CustomFoundMethod(_previous,_formatted, _pair, ex_));
        }
        return ex_;
    }

    private static Struct processError(ContextEl _conf, ArrayStruct _arr, Struct _s, ErrorType _state, StackCall _stackCall) {
        LgNames stds_ = _conf.getStandards();
        if (_state == ErrorType.NPE) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            return new ErrorStruct(_conf,npe_, _stackCall);
        }
        String cast_ = stds_.getContent().getCoreNames().getAliasStore();
        StringBuilder mess_ = buildStoreError(_s, _conf, _arr);
        return new ErrorStruct(_conf,mess_.toString(),cast_, _stackCall);
    }
    public static ErrorType safeObject(String _param, Struct _arg, ContextEl _context) {
        return ExecInherits.safeObject(ErrorType.CAST,_param,_arg,_context);
    }

    public static Struct getElement(Struct _struct, Struct _index, ContextEl _conf, StackCall _stackCall) {
        Struct elt_ = gearErrorWhenIndex(_struct, _index, _conf, _stackCall);
        _stackCall.getInitializingTypeInfos().addSensibleField(_struct, elt_);
        return elt_;
    }

    public static ErrorType setElement(Struct _struct, Struct _index, Struct _value, ContextEl _conf, StackCall _stackCall) {
        return gearErrorWhenContain(_struct, _index, _value, _conf, _stackCall);
    }

    private static ErrorType gearErrorWhenContain(Struct _array, Struct _index, Struct _value, ContextEl _context, StackCall _stackCall) {
        ErrorType err_ = getErrorWhenContain(_array, _index, _value, _context);
        LgNames stds_ = _context.getStandards();
        if (err_ == ErrorType.NOTHING) {
            ArrayStruct arr_ = (ArrayStruct) _array;
            if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(arr_)) {
                _stackCall.getInitializingTypeInfos().failInitEnums();
                return err_;
            }
            int index_ = NumParsers.convertToNumber(_index).intStruct();
            arr_.set(index_, _value);
            return err_;
        }
        if (err_ == ErrorType.NPE) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, npe_, _stackCall)));
            return err_;
        }
        if (err_ == ErrorType.BAD_INDEX) {
            String cast_ = stds_.getContent().getCoreNames().getAliasBadIndex();
            ArrayStruct arr_ = (ArrayStruct) _array;
            StringBuilder mess_ = getIndexMessage(_index, arr_);
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, mess_.toString(), cast_, _stackCall)));
            return err_;
        }
        if (err_ == ErrorType.CAST) {
            String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
            String type_ = _array.getClassName(_context);
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, type_, cast_, _stackCall)));
            return err_;
        }
        String cast_ = stds_.getContent().getCoreNames().getAliasStore();
        ArrayStruct arr_ = (ArrayStruct) _array;
        StringBuilder mess_ = buildStoreError(_value, _context, arr_);
        _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, mess_.toString(), cast_, _stackCall)));
        return err_;
    }

    private static ErrorType getErrorWhenContain(Struct _array, Struct _index, Struct _value, ContextEl _context) {
        if (_array == NullStruct.NULL_VALUE) {
            return ErrorType.NPE;
        }
        if (!(_array instanceof ArrayStruct)) {
            return ErrorType.CAST;
        }
        if (_index == NullStruct.NULL_VALUE) {
            return ErrorType.NPE;
        }
        if (!(_index instanceof NumberStruct)) {
            return ErrorType.CAST;
        }
        ArrayStruct arr_ = (ArrayStruct) _array;
        int index_ = NumParsers.convertToNumber(_index).intStruct();
        if (index_ < 0 || index_ >= arr_.getLength()) {
            return ErrorType.BAD_INDEX;
        }
        return safeObjectArr(_value, _context, arr_);
    }

    private static ErrorType safeObjectArr(Struct _value, ContextEl _context, ArrayStruct _arr) {
        String arrType_ = _arr.getClassName();
        String param_ = StringUtil.nullToEmpty(StringExpUtil.getQuickComponentType(arrType_));
        return ExecInherits.safeObject(ErrorType.STORE,param_,_value,_context);
    }

    private static Struct gearErrorWhenIndex(Struct _array, Struct _index, ContextEl _context, StackCall _stackCall) {
        ErrorType err_ = getErrorWhenIndex(_array, _index);
        LgNames stds_ = _context.getStandards();
        if (err_ == ErrorType.NOTHING) {
            ArrayStruct arr_ = (ArrayStruct) _array;
            int index_ = NumParsers.convertToNumber(_index).intStruct();
            return arr_.get(index_);
        }
        if (err_ == ErrorType.NPE) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, npe_, _stackCall)));
            return NullStruct.NULL_VALUE;
        }
        if (err_ == ErrorType.BAD_INDEX) {
            String cast_ = stds_.getContent().getCoreNames().getAliasBadIndex();
            ArrayStruct arr_ = (ArrayStruct) _array;
            StringBuilder mess_ = getIndexMessage(_index, arr_);
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, mess_.toString(), cast_, _stackCall)));
            return NullStruct.NULL_VALUE;
        }
        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
        String type_ = _array.getClassName(_context);
        _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, type_, cast_, _stackCall)));
        return NullStruct.NULL_VALUE;
    }

    private static ErrorType getErrorWhenIndex(Struct _array, Struct _index) {
        if (_array == NullStruct.NULL_VALUE) {
            return ErrorType.NPE;
        }
        if (!(_array instanceof ArrayStruct)) {
            return ErrorType.CAST;
        }
        if (_index == NullStruct.NULL_VALUE) {
            return ErrorType.NPE;
        }
        if (!(_index instanceof NumberStruct)) {
            return ErrorType.CAST;
        }
        ArrayStruct arr_ = (ArrayStruct) _array;
        int index_ = NumParsers.convertToNumber(_index).intStruct();
        if (index_ < 0 || index_ >= arr_.getLength()) {
            return ErrorType.BAD_INDEX;
        }
        return ErrorType.NOTHING;
    }
    private static StringBuilder buildStoreError(Struct _value, ContextEl _context, ArrayStruct _arr) {
        String arrType_ = _arr.getClassName();
        String param_ = StringUtil.nullToEmpty(StringExpUtil.getQuickComponentType(arrType_));
        String arg_ = _value.getClassName(_context);
        StringBuilder mess_ = new StringBuilder();
        mess_.append(arg_);
        mess_.append("!=");
        mess_.append(param_);
        return mess_;
    }

    private static StringBuilder getIndexMessage(Struct _index, ArrayStruct _arr) {
        int index_ = NumParsers.convertToNumber(_index).intStruct();
        StringBuilder mess_ = new StringBuilder();
        if (index_ < 0) {
            mess_.append(index_);
            mess_.append("<0");
        } else {
            mess_.append(index_);
            mess_.append(">=");
            mess_.append(_arr.getLength());
        }
        return mess_;
    }
    public static String getMadeVarTypes(String _className, StringList _classNames,ContextEl _context) {
        String type_ = StringExpUtil.getIdFromAllTypes(_className);
        String fct_ = _context.getStandards().getContent().getReflect().getAliasFct();
        if (StringUtil.quickEq(type_, fct_)) {
            if (_classNames.isEmpty()) {
                return null;
            }
            StringList parts_ = new StringList();
            for (String s: _classNames) {
                if (StringUtil.quickEq(s, Templates.SUB_TYPE)) {
                    parts_.add(s);
                    continue;
                }
                if (s.startsWith(Templates.SUB_TYPE)) {
                    parts_.add(s.substring(Templates.SUB_TYPE.length()));
                    continue;
                }
                if (s.startsWith(Templates.SUP_TYPE)) {
                    parts_.add(s.substring(Templates.SUP_TYPE.length()));
                    continue;
                }
                parts_.add(s);
            }
            StringBuilder str_ = new StringBuilder(fct_);
            str_.append(Templates.TEMPLATE_BEGIN);
            str_.append(StringUtil.join(parts_, Templates.TEMPLATE_SEP));
            str_.append(Templates.TEMPLATE_END);
            return str_.toString();
        }
        GeneType root_ = _context.getClassBody(type_);
        if (root_ == null) {
            return null;
        }
        String pref_ = root_.getGenericString();
        StringMap<String> varTypes_ = new StringMap<String>();
        CustList<ExecTypeVar> typeVar_ = root_.getParamTypesMapValues();
        int len_ = typeVar_.size();
        if (len_ != _classNames.size()) {
            return null;
        }
        int i_ = IndexConstants.FIRST_INDEX;
        for (ExecTypeVar t: typeVar_) {
            String arg_ = _classNames.get(i_);
            if (arg_.contains(PREFIX_VAR_TYPE)) {
                return null;
            }
            if (arg_.startsWith("~")) {
                arg_ = arg_.substring(1);
            }

            varTypes_.addEntry(t.getName(), arg_);
            i_++;
        }
        String formatted_ = StringExpUtil.getQuickFormattedType(pref_, varTypes_);
        for (int i = 0; i < len_; i++) {
            ExecTypeVar t = typeVar_.get(i);
            for (String b:t.getConstraints()) {
                String arg_ = _classNames.get(i);
                if (arg_.startsWith("?")) {
                    continue;
                }
                if (arg_.startsWith("!")) {
                    continue;
                }
                if (arg_.startsWith("~")) {
                    arg_ = arg_.substring(1);
                }
                String param_ = ExecInherits.format(root_,formatted_, b);
                if (!ExecInherits.isCorrectExecute(arg_,param_,_context)) {
                    return null;
                }
            }
        }
        return formatted_;
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
        return ExecTemplates.getWrapper(_varCont.getVariableName(),_varCont.getDeep(), ip_.getCache(), _stack.getLastPage().getRefParams());
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

    public static Argument setWrapValue(ContextEl _context, ExecVariableContent _varCont, Argument _value, StackCall _stackCall) {
        return setWrapValue(_context,_varCont,_value, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getRefParams(), _stackCall);
    }
    public static Argument setWrapValue(ContextEl _context, ExecVariableContent _varCont, Argument _value, Cache _cache, StringMap<AbstractWrapper> _vars, StackCall _stackCall) {
        return setWrapValue(_context,_varCont.getVariableName(),_value,_varCont.getDeep(), _cache, _vars, _stackCall);
    }
    public static Argument setWrapValue(ContextEl _context, String _val, Argument _value, int _deep, Cache _cache, StringMap<AbstractWrapper> _refParams, StackCall _stackCall) {
        if (_context.callsOrException(_stackCall)) {
            return new Argument();
        }
        if (_cache != null) {
            AbstractWrapper wr_ = _cache.getLocalWrapper(_val, _deep);
            if (wr_ != null) {
                wr_.setValue(_stackCall, _context,_value);
                return _value;
            }
        }
        AbstractWrapper wr_ = _refParams.getVal(_val);
        return trySetArgument(_context,_value,wr_, _stackCall);
    }

    public static Argument checkSet(ContextEl _conf, LocalVariable _loc, Argument _right, StackCall _stackCall) {
        String formattedClassVar_ = _loc.getClassName();
        if (!checkQuick(formattedClassVar_, _right, _conf, _stackCall)) {
            return Argument.createVoid();
        }
        _loc.setStruct(_right.getStruct());
        return _right;
    }

    public static Argument getField(FieldMetaInfo _meta, Argument _previous, ContextEl _conf, StackCall _stackCall) {
        String baseClass_ = _meta.getDeclaringClass();
        baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
        String fieldName_ = _meta.getName();
        boolean isStaticField_ = _meta.isStaticField();
        String type_ = _meta.getType();
        return getField(new DefaultSetOffset(),_conf.getExiting(),baseClass_, fieldName_, isStaticField_,type_, _previous, _conf, -1, _stackCall);
    }

    public static Argument getField(AbstractSetOffset _setOffset, AbstractExiting _exit, String _className, String _fieldName, boolean _isStaticField, String _ret, Argument _previous, ContextEl _conf, int _possibleOffset, StackCall _stackCall) {
        if (_possibleOffset > -1) {
            _setOffset.setOffset(_stackCall, _possibleOffset);
        }

        if (_isStaticField) {
            return getStaticField(_exit,_className, _fieldName, _ret, _conf, _stackCall);
        }
        return getInstanceField(_className, _fieldName, _previous, _conf, _stackCall);
    }

    public static Argument getInstanceField(String _className, String _fieldName, Argument _previous, ContextEl _conf, StackCall _stackCall) {
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getContent().getCoreNames().getAliasCastType();
        ClassField fieldId_ = new ClassField(_className, _fieldName);
        Struct previous_ = _previous.getStruct();
        String argClassName_ = previous_.getClassName(_conf);
        if (!(previous_ instanceof FieldableStruct)) {
            if (previous_ == NullStruct.NULL_VALUE) {
                String npe_;
                npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
                return Argument.createVoid();
            }
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(_className, argClassName_), cast_, _stackCall)));
            return _previous;
        }
        ClassFieldStruct entry_ = ((FieldableStruct) previous_).getEntryStruct(fieldId_);
        if (entry_ == null) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(_className, argClassName_), cast_, _stackCall)));
            return _previous;
        }
        Struct struct_ = entry_.getStruct();
        _stackCall.getInitializingTypeInfos().addSensibleField(previous_, struct_);
        return new Argument(struct_);
    }

    public static Argument getStaticField(AbstractExiting _exit, String _className, String _fieldName, String _ret, ContextEl _conf, StackCall _stackCall) {
        Classes classes_ = _conf.getClasses();
        ClassField fieldId_ = new ClassField(_className, _fieldName);
        if (_exit.hasToExit(_stackCall, _className)) {
            return Argument.createVoid();
        }
        Struct struct_ = classes_.getStaticField(fieldId_,_ret, _conf);
        _stackCall.getInitializingTypeInfos().addSensibleField(fieldId_.getClassName(), struct_, _stackCall);
        return new Argument(struct_);
    }

    public static Argument setField(FieldMetaInfo _meta, Argument _previous, Argument _right, ContextEl _conf, StackCall _stackCall) {
        String baseClass_ = _meta.getDeclaringClass();
        baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
        String fieldName_ = _meta.getName();
        boolean isStaticField_ = _meta.isStaticField();
        boolean isFinalField_ = _meta.isFinalField();
        String type_ = _meta.getType();
        return setField(new DefaultSetOffset(),_conf.getExiting(),_meta.getDeclaring(),baseClass_, fieldName_, isStaticField_, isFinalField_, true, type_, _previous, _right, _conf, -1, _stackCall);
    }

    public static Argument setField(AbstractSetOffset _setOffset, AbstractExiting _exit, ExecRootBlock _rootBlock, String _className, String _fieldName,
                                    boolean _isStaticField, boolean _finalField, boolean _failIfFinal,
                                    String _returnType, Argument _previous,
                                    Argument _right, ContextEl _conf, int _possibleOffset, StackCall _stackCall) {
        if (_possibleOffset > -1) {
            _setOffset.setOffset(_stackCall, _possibleOffset);
        }
        if (_isStaticField) {
            return setStaticField(_exit,_className, _fieldName, _finalField, _failIfFinal, _returnType, _right, _conf, _stackCall);
        }
        return setInstanceField(_rootBlock,_className, _fieldName, _returnType, _previous, _right, _conf, _stackCall);
    }

    public static Argument setInstanceField(ExecRootBlock _rootBlock, String _className, String _fieldName, String _returnType, Argument _previous, Argument _right, ContextEl _conf, StackCall _stackCall) {
        ClassField fieldId_ = new ClassField(_className, _fieldName);
        LgNames stds_ = _conf.getStandards();
        Struct previous_ = _previous.getStruct();
        String argClassName_ = previous_.getClassName(_conf);
        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
        if (!(previous_ instanceof FieldableStruct)) {
            if (previous_ == NullStruct.NULL_VALUE) {
                String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
                return Argument.createVoid();
            }
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(_className, argClassName_), cast_, _stackCall)));
            return Argument.createVoid();
        }
        ClassFieldStruct entry_ = ((FieldableStruct) previous_).getEntryStruct(fieldId_);
        if (entry_ == null) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(_className, argClassName_), cast_, _stackCall)));
            return Argument.createVoid();
        }
        String classNameFound_ = ExecInherits.getSuperGeneric(argClassName_, _className, _conf);
        String fieldType_ = ExecInherits.quickFormat(_rootBlock,classNameFound_, _returnType);
        if (!checkQuick(fieldType_, _right, _conf, _stackCall)) {
            return Argument.createVoid();
        }
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(previous_)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return _right;
        }
        entry_.setStruct(_right.getStruct());
        return _right;
    }

    private static Argument setStaticField(AbstractExiting _exit, String _className, String _fieldName, boolean _finalField, boolean _failIfFinal, String _returnType, Argument _right, ContextEl _conf, StackCall _stackCall) {
        Classes classes_ = _conf.getClasses();
        ClassField fieldId_ = new ClassField(_className, _fieldName);
        LgNames stds_ = _conf.getStandards();
        if (_finalField && _failIfFinal) {
            String npe_;
            npe_ = stds_.getContent().getCoreNames().getAliasIllegalArg();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return Argument.createVoid();
        }
        if (_exit.hasToExit(_stackCall, _className)) {
            return Argument.createVoid();
        }
        if (!checkQuick(_returnType, _right, _conf, _stackCall)) {
            return Argument.createVoid();
        }
        String className_ = fieldId_.getClassName();
        if (_stackCall.getInitializingTypeInfos().isSensibleField(className_, _stackCall)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return _right;
        }
        NumParsers.getStaticFieldMap(className_, classes_.getStaticFields()).set(fieldId_.getFieldName(), _right.getStruct());
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
