package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.opers.ExecMethodOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.types.ExecPartTypeUtil;
import code.expressionlanguage.exec.types.ExecResultPartType;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.inherits.*;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.stds.StandardType;
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

    public static Struct newCustomArrayOrExc(String _className, Ints _dims, ContextEl _cont) {
        return newCustomArrayOrExc(new Ints(),_className,_dims,_cont);
    }
    public static Struct newCustomArrayOrExc(Ints _filter, String _className, Ints _dims, ContextEl _cont) {
        Ints dims_ = new Ints();
        String size_;
        LgNames lgNames_ = _cont.getStandards();
        size_ = lgNames_.getContent().getCoreNames().getAliasBadSize();
        if (_dims.isEmpty()) {
            return new ErrorStruct(_cont,size_);
        }
        int j_ = 0;
        for (int s: _dims) {
            if (s < 0) {
                if (_filter.isValidIndex(j_)) {
                    int off_ = _filter.get(j_);
                    _cont.setOffset(off_);
                }
                return new ErrorStruct(_cont, StringUtil.concat(Long.toString(s),"<0"),size_);
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
    public static Struct getParent(int _nbAncestors,String _required, Struct _current, ContextEl _an) {
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
            if (StringUtil.quickEq(dimReq_.getComponent(), _an.getStandards().getContent().getCoreNames().getAliasObject())) {
                if (dReq_ > dCurrent_) {
                    _an.setCallingState(new ErrorStruct(_an, StringUtil.concat(className_, RETURN_LINE,_required, RETURN_LINE),cast_));
                    return NullStruct.NULL_VALUE;
                }
                return _current;
            }
            if (dReq_ != dCurrent_) {
                _an.setCallingState(new ErrorStruct(_an, StringUtil.concat(className_, RETURN_LINE,_required, RETURN_LINE),cast_));
                return NullStruct.NULL_VALUE;
            }
            String dComp_ = dimCurrent_.getComponent();
            PrimitiveType pr_ = _an.getStandards().getPrimitiveTypes().getVal(dComp_);
            GeneType g_ = _an.getClassBody(dComp_);
            InheritedType in_ = null;
            boolean without_;
            if (g_ != null) {
                without_ = g_.withoutInstance();
            } else {
                without_ = false;
            }
            if (pr_ != null) {
                in_ = pr_;
            } else {
                if (cl_.startsWith(Templates.ARR_BEG_STRING) || without_) {
                    in_ = g_;
                }
            }
            if (in_ != null) {
                if (!in_.isSubTypeOf(dimReq_.getComponent(),_an)) {
                    _an.setCallingState(new ErrorStruct(_an, StringUtil.concat(className_, RETURN_LINE,_required, RETURN_LINE),cast_));
                    return NullStruct.NULL_VALUE;
                }
                return _current;
            }
            for (int i = 0; i < _nbAncestors; i++) {
                Struct enc_ = arg_;
                Struct par_ = enc_.getParent();
                _an.getInitializingTypeInfos().addSensibleField(enc_, par_);
                arg_=par_;
            }
        }
        String npe_ = lgNames_.getContent().getCoreNames().getAliasNullPe();
        if (arg_ == NullStruct.NULL_VALUE) {
            _an.setCallingState(new ErrorStruct(_an,npe_));
            return arg_;
        }
        Struct current_ = arg_;
        String className_ = current_.getClassName(_an);
        String cl_ = StringExpUtil.getIdFromAllTypes(className_);
        StringList list_ = new StringList();
        GeneType g_ = _an.getClassBody(cl_);
        while (hasToLookForParent(_an, id_, g_)) {
            if (StringUtil.contains(list_, cl_)) {
                _an.setCallingState(new ErrorStruct(_an, StringUtil.concat(className_, RETURN_LINE,_required, RETURN_LINE),cast_));
                break;
            }
            list_.add(cl_);
            if (!(current_ instanceof WithParentStruct)) {
                _an.setCallingState(new ErrorStruct(_an, StringUtil.concat(className_, RETURN_LINE,_required, RETURN_LINE),cast_));
                break;
            }
            Struct par_ = current_.getParent();
            _an.getInitializingTypeInfos().addSensibleField(current_, par_);
            className_ = ((WithParentStruct)current_).getParentClassName();
            current_ = par_;
            cl_ = StringExpUtil.getIdFromAllTypes(className_);
            g_ = _an.getClassBody(cl_);
        }
        return Argument.getNull(current_);
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
        if (isCorrectTemplateAllExec(className_, _context)) {
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

    public static boolean isCorrectExecute(String _a, String _p, ContextEl _context) {
        if (_p.isEmpty()) {
            return false;
        }
        CustList<Matching> matchs_ = new CustList<Matching>();
        Matching match_ = new Matching();
        match_.setArg(_a);
        match_.setParam(_p);
        matchs_.add(match_);
        boolean okTree_ = true;
        while (true) {
            CustList<Matching> new_ = new CustList<Matching>();
            for (Matching m: matchs_) {
                String a_ = m.getArg();
                String p_ = m.getParam();
                MappingPairs m_ = getExecutingCorrect(a_,p_, _context);
                if (m_ == null) {
                    okTree_ = false;
                    break;
                }
                for (Matching n: m_.getPairsArgParam()) {
                    if (n.getMatchEq() == MatchingEnum.EQ) {
                        if (!StringUtil.quickEq(n.getParam(), n.getArg())) {
                            okTree_ = false;
                            break;
                        }
                        continue;
                    }
                    if (StringUtil.quickEq(n.getParam(), n.getArg())) {
                        continue;
                    }
                    Matching n_ = new Matching();
                    if (n.getMatchEq() == MatchingEnum.SUB) {
                        n_.setArg(n.getArg());
                        n_.setParam(n.getParam());
                    } else {
                        n_.setArg(n.getParam());
                        n_.setParam(n.getArg());
                    }
                    new_.add(n_);
                }
                if (!okTree_) {
                    break;
                }
            }
            if (new_.isEmpty()) {
                break;
            }
            matchs_ = new_;
            if (!okTree_) {
                break;
            }
        }
        return okTree_;
    }

    private static MappingPairs getExecutingCorrect(String _arg, String _param, ContextEl _context) {
        StringList typesArg_ = StringExpUtil.getAllTypes(_arg);
        StringList typesParam_ = StringExpUtil.getAllTypes(_param);
        DimComp dArg_ = StringExpUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = StringExpUtil.getQuickComponentBaseType(_param);
        String baseArrayArg_ = dArg_.getComponent();
        String baseArrayParam_ = dParam_.getComponent();
        String fct_ = _context.getStandards().getContent().getReflect().getAliasFct();
        String obj_ = _context.getStandards().getContent().getCoreNames().getAliasObject();
        String idBaseArrayArg_ = StringExpUtil.getIdFromAllTypes(baseArrayArg_);
        String idBaseArrayParam_ = StringExpUtil.getIdFromAllTypes(baseArrayParam_);
        if (StringUtil.quickEq(idBaseArrayArg_, fct_)) {
            if (StringUtil.quickEq(idBaseArrayParam_, fct_)) {
                int dim_ = dArg_.getDim();
                if (dim_ != dParam_.getDim()) {
                    return null;
                }
                if (StringUtil.quickEq(baseArrayParam_, fct_)) {
                    return new MappingPairs();
                }
                return StringExpUtil.newMappingPairsFct(typesArg_, typesParam_, obj_);
            }
            return StringExpUtil.getMappingFctPairs(dArg_, dParam_, baseArrayParam_, obj_);
        }
        if (StringUtil.quickEq(idBaseArrayParam_, fct_)) {
            return null;
        }
        String generic_ = getFullTypeByBases(_arg, _param, _context);
        if (generic_.isEmpty()) {
            return null;
        }
        return StringExpUtil.newMappingPairs(generic_, typesParam_);
    }

    public static boolean checkObject(String _param, Argument _arg, ContextEl _context) {
        Struct str_ = _arg.getStruct();
        byte cast_ = ExecClassArgumentMatching.getPrimitiveWrapCast(_param, _context.getStandards());
        _arg.setStruct(NumParsers.convertObject(cast_, str_));
        return checkQuick(_param, _arg, _context);
    }

    public static boolean checkQuick(String _param, Argument _arg, ContextEl _context) {
        Struct ex_ = checkObjectEx(_param,_arg,_context);
        if (ex_ != null) {
            _context.setCallingState(ex_);
            return false;
        }
        return true;
    }

    public static Struct checkObjectEx(String _param, Argument _arg, ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        ErrorType err_ = safeObject(_param, _arg, _context);
        if (err_ == ErrorType.CAST) {
            String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
            return new ErrorStruct(_context, StringUtil.concat(_arg.getStruct().getClassName(_context), RETURN_LINE,_param, RETURN_LINE),cast_);
        }
        if (err_ == ErrorType.NPE) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            return new ErrorStruct(_context,npe_);
        }
        return null;
    }
    public static void setCheckedElements(CustList<Argument> _args, Struct _arr, ContextEl _context) {
        int len_ = _args.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            Argument chArg_ = _args.get(i);
            IntStruct ind_ = new IntStruct(i);
            setElement(_arr, ind_, chArg_.getStruct(), _context);
            if (_context.callsOrException()) {
                return;
            }
        }
    }

    public static String checkParams(ContextEl _conf, String _classNameFound, Identifiable _methodId,
                                     Argument _previous, CustList<Argument> _firstArgs) {
        LgNames stds_ = _conf.getStandards();
        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
        String classFormat_ = _classNameFound;
        if (!_methodId.isStaticMethod()) {
            String className_ = Argument.getNullableValue(_previous).getStruct().getClassName(_conf);
            classFormat_ = getQuickFullTypeByBases(className_, classFormat_, _conf);
            if (classFormat_.isEmpty()) {
                _conf.setCallingState(new ErrorStruct(_conf, StringUtil.concat(className_, RETURN_LINE,_classNameFound, RETURN_LINE),cast_));
                return "";
            }
        }
        if (!okArgs(_methodId, classFormat_,_firstArgs, _conf)) {
            return "";
        }
        return classFormat_;
    }

    public static FormattedParameters checkParams(ContextEl _conf, String _classNameFound, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _methodId,
                                                  Argument _previous, Cache _cache, CustList<Argument> _firstArgs,
                                                  Argument _right) {
        LgNames stds_ = _conf.getStandards();
        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
        String classFormat_ = _classNameFound;
        FormattedParameters f_ = new FormattedParameters();
        if (!(_methodId instanceof GeneMethod) || !((GeneMethod)_methodId).getId().isStaticMethod()) {
            String className_ = Argument.getNullableValue(_previous).getStruct().getClassName(_conf);
            classFormat_ = getQuickFullTypeByBases(className_, classFormat_, _conf);
            if (classFormat_.isEmpty()) {
                _conf.setCallingState(new ErrorStruct(_conf, StringUtil.concat(className_, RETURN_LINE,_classNameFound, RETURN_LINE),cast_));
                return f_;
            }
        }
        Parameters parameters_ = okArgs(_rootBlock, _methodId, false, classFormat_,_cache, _firstArgs, _conf, _right);
        if (parameters_.getError() != null) {
            return f_;
        }
        f_.setParameters(parameters_);
        f_.setFormattedClass(classFormat_);
        return f_;
    }


    private static Struct okArgsEx(Identifiable _id, String _classNameFound, CustList<Argument> _firstArgs, ContextEl _conf) {
        StringList params_ = new StringList();
        boolean hasFormat_;
        if (_id instanceof MethodId) {
            hasFormat_ = ((MethodId) _id).canAccessParamTypes();
        } else {
            hasFormat_ = true;
        }
        if (hasFormat_ && !correctNbParameters(_classNameFound,_conf)) {
            LgNames stds_ = _conf.getStandards();
            String npe_ = stds_.getContent().getCoreNames().getAliasIllegalArg();
            return new ErrorStruct(_conf,_classNameFound,npe_);
        }
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
            return new ErrorStruct(_conf,mess_.toString(),cast_);
        }
        i_ = IndexConstants.FIRST_INDEX;
        for (Argument a: _firstArgs) {
            String param_ = params_.get(i_);
            Struct ex_ = checkObjectEx(param_, a, _conf);
            if (ex_ != null) {
                return ex_;
            }
            i_++;
        }
        if (_id.isVararg()) {
            Struct str_ = _firstArgs.last().getStruct();
            if (str_ instanceof ArrayStruct) {
                ArrayStruct arr_ = (ArrayStruct) str_;
                for (Struct s: arr_.getInstance()) {
                    ErrorType state_ = checkElement(arr_, s, _conf);
                    if (state_ != ErrorType.NOTHING) {
                        return processError(_conf, arr_, s, state_);
                    }
                }
            }
        }
        return null;
    }
    private static Parameters okArgsEx(ExecRootBlock _rootBlock, ExecNamedFunctionBlock _id, boolean _format, String _classNameFound, Cache _cache, CustList<Argument> _firstArgs, ContextEl _conf, Argument _right) {
        boolean hasFormat_;
        if (_id instanceof GeneMethod) {
            hasFormat_ = ((GeneMethod)_id).getId().canAccessParamTypes() || _format;
        } else {
            hasFormat_ = true;
        }
        Parameters p_ = new Parameters();
        if (hasFormat_ && !correctNbParameters(_classNameFound,_conf)) {
            LgNames stds_ = _conf.getStandards();
            String npe_ = stds_.getContent().getCoreNames().getAliasIllegalArg();
            p_.setError(new ErrorStruct(_conf,_classNameFound,npe_));
            return p_;
        }
        if (_cache != null) {
            _cache.setCache(_rootBlock,_classNameFound);
            p_.setCache(_cache);
            Struct err_ = _cache.checkCache(_conf);
            if (err_ != null){
                p_.setError(err_);
                return p_;
            }
        }
        if (_id == null) {
            if (_firstArgs.size() != 0) {
                LgNames stds_ = _conf.getStandards();
                String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                StringBuilder mess_ = countDiff(_firstArgs.size(), 0);
                p_.setError(new ErrorStruct(_conf,mess_.toString(),cast_));
                return p_;
            }
            return p_;
        }
        StringList params_ = fetchParamTypes(_rootBlock, _id, _classNameFound, hasFormat_);
        if (_firstArgs.size() != params_.size()) {
            LgNames stds_ = _conf.getStandards();
            String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
            StringBuilder mess_ = countDiff(_firstArgs.size(), params_.size());
            p_.setError(new ErrorStruct(_conf,mess_.toString(),cast_));
            return p_;
        }
        int i_ = IndexConstants.FIRST_INDEX;
        for (Argument a: _firstArgs) {
            String param_ = params_.get(i_);
            Struct ex_ = checkObjectEx(param_, a, _conf);
            if (ex_ != null) {
                p_.setError(ex_);
                return p_;
            }
            Struct struct_ = a.getStruct();
            LocalVariable lv_ = LocalVariable.newLocalVariable(struct_,param_);
            p_.getParameters().addEntry(_id.getParametersNames().get(i_),lv_);
            i_++;
        }
        if (_id.isVarargs()) {
            Struct str_ = _firstArgs.last().getStruct();
            if (str_ instanceof ArrayStruct) {
                ArrayStruct arr_ = (ArrayStruct) str_;
                for (Struct s: arr_.getInstance()) {
                    ErrorType state_ = checkElement(arr_, s, _conf);
                    if (state_ != ErrorType.NOTHING) {
                        Struct struct_ = processError(_conf, arr_, s, state_);
                        p_.setError(struct_);
                        return p_;
                    }
                }
            }
        }
        if (_id instanceof GeneMethod) {
            if (_right != null) {
                String type_ = _id.getImportedReturnType();
                type_ = quickFormat(_rootBlock,_classNameFound, type_);
                Struct ex_ = checkObjectEx(type_, _right, _conf);
                if (ex_ != null) {
                    p_.setError(ex_);
                    return p_;
                }
                p_.setRight(_right);
                LocalVariable lv_ = LocalVariable.newLocalVariable(_right.getStruct(),type_);
                p_.getParameters().addEntry(_conf.getClasses().getKeyWordValue(),lv_);
            }
        }
        return p_;
    }

    public static StringBuilder countDiff(int _argsCount, int _paramsCount) {
        StringBuilder mess_ = new StringBuilder();
        mess_.append(_argsCount);
        mess_.append("!=");
        mess_.append(_paramsCount);
        return mess_;
    }

    private static StringList fetchParamTypes(ExecRootBlock _rootBlock, ExecNamedFunctionBlock _id, String _classNameFound, boolean _hasFormat) {
        StringList params_ = new StringList();
        if (_hasFormat) {
            int i_ = 0;
            for (String c: _id.getImportedParametersTypes()) {
                String c_ = c;
                c_ = quickFormat(_rootBlock,_classNameFound, c_);
                if (i_ + 1 == _id.getImportedParametersTypes().size() && _id.isVarargs()) {
                    c_ = StringExpUtil.getPrettyArrayType(c_);
                }
                params_.add(c_);
                i_++;
            }
        } else {
            int i_ = 0;
            for (String c: _id.getImportedParametersTypes()) {
                String c_ = c;
                if (i_ + 1 == _id.getImportedParametersTypes().size() && _id.isVarargs()) {
                    c_ = StringExpUtil.getPrettyArrayType(c_);
                }
                params_.add(c_);
                i_++;
            }
        }
        return params_;
    }

    public static Parameters wrapAndCall(ExecNamedFunctionBlock _id, ExecRootBlock _root, String _formatted, Argument _previous, CustList<Argument> _firstArgs, ContextEl _conf) {
        Parameters p_ = new Parameters();
        int i_ = IndexConstants.FIRST_INDEX;
        StringList params_ = fetchParamTypes(_root, _id, _formatted, true);
        for (Argument a: _firstArgs) {
            String param_ = params_.get(i_);
            Struct ex_ = checkObjectEx(param_, a, _conf);
            if (ex_ != null) {
                _conf.setCallingState(ex_);
                return p_;
            }
            LocalVariable lv_ = LocalVariable.newLocalVariable(a.getStruct(), param_);
            p_.getParameters().addEntry(_id.getParametersNames().get(i_),lv_);
            i_++;
        }
        _conf.setCallingState(new CustomFoundMethod(_previous,_formatted,_root,_id,p_));
        return p_;
    }

    private static Struct processError(ContextEl _conf, ArrayStruct _arr, Struct _s, ErrorType _state) {
        LgNames stds_ = _conf.getStandards();
        if (_state == ErrorType.NPE) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            return new ErrorStruct(_conf,npe_);
        } else {
            String arrType_ = _arr.getClassName();
            String param_ = StringExpUtil.getQuickComponentType(arrType_);
            String arg_ = _s.getClassName(_conf);
            String cast_ = stds_.getContent().getCoreNames().getAliasStore();
            StringBuilder mess_ = new StringBuilder();
            mess_.append(arg_);
            mess_.append("!=");
            mess_.append(param_);
            return new ErrorStruct(_conf,mess_.toString(),cast_);
        }
    }

    public static boolean okArgs(Identifiable _id, String _classNameFound, CustList<Argument> _firstArgs, ContextEl _conf) {
        Struct ex_ = okArgsSet(_id, _classNameFound, _firstArgs, _conf);
        return ex_ == null;
    }
    public static Parameters okArgs(ExecRootBlock _rootBlock,ExecNamedFunctionBlock _id, boolean _format, String _classNameFound, CustList<Argument> _firstArgs, ContextEl _conf, Argument _right) {
        return okArgs(_rootBlock,_id,_format,_classNameFound,null,_firstArgs,_conf,_right);
    }
    public static Parameters okArgs(ExecRootBlock _rootBlock,ExecNamedFunctionBlock _id, boolean _format, String _classNameFound, Cache _cache, CustList<Argument> _firstArgs, ContextEl _conf, Argument _right) {
        return okArgsSet(_rootBlock,_id, _format, _classNameFound, _cache, _firstArgs, _conf, _right);
    }
    private static Struct okArgsSet(Identifiable _id, String _classNameFound, CustList<Argument> _firstArgs, ContextEl _conf) {
        Struct ex_ = okArgsEx(_id, _classNameFound, _firstArgs, _conf);
        if (ex_ != null) {
            _conf.setCallingState(ex_);
        }
        return ex_;
    }

    public static Parameters okArgsSet(ExecRootBlock _rootBlock,ExecNamedFunctionBlock _id, boolean _format, String _classNameFound, Cache _cache, CustList<Argument> _firstArgs, ContextEl _conf, Argument _right) {
        Parameters ex_ = okArgsEx(_rootBlock,_id, _format, _classNameFound,_cache, _firstArgs, _conf, _right);
        if (ex_.getError() != null) {
            _conf.setCallingState(ex_.getError());
        }
        return ex_;
    }
    public static ErrorType safeObject(String _param, Argument _arg, ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        Struct str_ = _arg.getStruct();
        if (str_ != NullStruct.NULL_VALUE) {
            String a_ = str_.getClassName(_context);
            String param_ = toWrapper(_param, stds_);
            if (!isCorrectExecute(a_, param_, _context)) {
                return ErrorType.CAST;
            }
        }
        if (_param.isEmpty()) {
            return ErrorType.CAST;
        }
        if (primitiveTypeNullObject(_param, str_, _context)) {
            return ErrorType.NPE;
        }
        return ErrorType.NOTHING;
    }
    private static void gearErrorWhenContain(Struct _array, Struct _index, Struct _value, ContextEl _context) {
        ErrorType err_ = getErrorWhenContain(_array, _index, _value, _context);
        LgNames stds_ = _context.getStandards();
        if (err_ == ErrorType.NOTHING) {
            ArrayStruct arr_ = (ArrayStruct) _array;
            if (_context.getInitializingTypeInfos().isContainedSensibleFields(arr_)) {
                _context.getInitializingTypeInfos().failInitEnums();
                return;
            }
            int index_ = NumParsers.convertToNumber(_index).intStruct();
            arr_.set(index_, _value);
            return;
        }
        if (err_ == ErrorType.NPE) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _context.setCallingState(new ErrorStruct(_context,npe_));
            return;
        }
        if (err_ == ErrorType.BAD_INDEX) {
            String cast_ = stds_.getContent().getCoreNames().getAliasBadIndex();
            ArrayStruct arr_ = (ArrayStruct) _array;
            int index_ = NumParsers.convertToNumber(_index).intStruct();
            StringBuilder mess_ = new StringBuilder();
            if (index_ < 0) {
                mess_.append(index_);
                mess_.append("<0");
            } else {
                mess_.append(index_);
                mess_.append(">=");
                mess_.append(arr_.getLength());
            }
            _context.setCallingState(new ErrorStruct(_context,mess_.toString(),cast_));
            return;
        }
        if (err_ == ErrorType.CAST) {
            String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
            String type_ = _array.getClassName(_context);
            _context.setCallingState(new ErrorStruct(_context,type_,cast_));
            return;
        }
        ArrayStruct arr_ = (ArrayStruct) _array;
        String arrType_ = arr_.getClassName();
        String param_ = StringExpUtil.getQuickComponentType(arrType_);
        String arg_ = _value.getClassName(_context);
        String cast_ = stds_.getContent().getCoreNames().getAliasStore();
        StringBuilder mess_ = new StringBuilder();
        mess_.append(arg_);
        mess_.append("!=");
        mess_.append(param_);
        _context.setCallingState(new ErrorStruct(_context,mess_.toString(),cast_));
    }
    public static ErrorType getErrorWhenContain(Struct _array, Struct _index, Struct _value, ContextEl _context) {
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
        String arrType_ = arr_.getClassName();
        String param_ = StringExpUtil.getQuickComponentType(arrType_);
        LgNames stds_ = _context.getStandards();
        if (primitiveTypeNullObject(param_, _value, stds_)) {
            return ErrorType.NPE;
        }
        if (_value != NullStruct.NULL_VALUE) {
            String arg_ = _value.getClassName(_context);
            param_ = toWrapper(param_, stds_);
            if (!isCorrectExecute(arg_, param_, _context)) {
                return ErrorType.STORE;
            }
        }
        return ErrorType.NOTHING;
    }
    private static ErrorType checkElement(ArrayStruct _arr, Struct _value, ContextEl _context) {
        String arrType_ = _arr.getClassName();
        String param_ = StringExpUtil.getQuickComponentType(arrType_);
        LgNames stds_ = _context.getStandards();
        if (primitiveTypeNullObject(param_, _value, stds_)) {
            return ErrorType.NPE;
        }
        if (_value != NullStruct.NULL_VALUE) {
            String arg_ = _value.getClassName(_context);
            param_ = toWrapper(param_, stds_);
            if (!isCorrectExecute(arg_, param_, _context)) {
                return ErrorType.STORE;
            }
        }
        return ErrorType.NOTHING;
    }
    private static Struct gearErrorWhenIndex(Struct _array, Struct _index, ContextEl _context) {
        ErrorType err_ = getErrorWhenIndex(_array, _index);
        LgNames stds_ = _context.getStandards();
        if (err_ == ErrorType.NOTHING) {
            ArrayStruct arr_ = (ArrayStruct) _array;
            int index_ = NumParsers.convertToNumber(_index).intStruct();
            return arr_.get(index_);
        }
        if (err_ == ErrorType.NPE) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _context.setCallingState(new ErrorStruct(_context,npe_));
            return NullStruct.NULL_VALUE;
        }
        if (err_ == ErrorType.BAD_INDEX) {
            String cast_ = stds_.getContent().getCoreNames().getAliasBadIndex();
            ArrayStruct arr_ = (ArrayStruct) _array;
            int index_ = NumParsers.convertToNumber(_index).intStruct();
            StringBuilder mess_ = new StringBuilder();
            if (index_ < 0) {
                mess_.append(index_);
                mess_.append("<0");
            } else {
                mess_.append(index_);
                mess_.append(">=");
                mess_.append(arr_.getLength());
            }
            _context.setCallingState(new ErrorStruct(_context,mess_.toString(),cast_));
            return NullStruct.NULL_VALUE;
        }
        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
        String type_ = _array.getClassName(_context);
        _context.setCallingState(new ErrorStruct(_context,type_,cast_));
        return NullStruct.NULL_VALUE;
    }
    public static ErrorType getErrorWhenIndex(Struct _array, Struct _index) {
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
                    parts_.add(_context.getStandards().getContent().getCoreNames().getAliasObject());
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
                String param_ = format(root_,formatted_, b);
                if (!isCorrectExecute(arg_,param_,_context)) {
                    return null;
                }
            }
        }
        return formatted_;
    }

    private static boolean isCorrectTemplateAllExec(ExecResultPartType _className, ContextEl _context) {
        return ExecPartTypeUtil.processAnalyzeConstraintsExec(_className,_context);
    }


    public static String getQuickFullTypeByBases(String _subType, String _superType, ContextEl _context) {
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        DimComp dBaseParam_ = StringExpUtil.getQuickComponentBaseType(idSuperType_);
        String classParam_ = dBaseParam_.getComponent();
        int dim_ = dBaseParam_.getDim();
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        DimComp dBaseArg_ = StringExpUtil.getQuickComponentBaseType(idArg_);
        int dimArg_ = dBaseArg_.getDim();
        if (StringUtil.quickEq(classParam_, _context.getStandards().getContent().getCoreNames().getAliasObject())) {
            if (dimArg_ < dim_) {
                return "";
            }
            return _superType;
        }
        if (dimArg_ != dim_) {
            return "";
        }
        return getFullObject(_subType, _superType,_context);
    }
    public static String getSuperGeneric(String _arg, String _classParam, ContextEl _context) {
        if (!correctNbParameters(_arg,_context)) {
            return "";
        }
        String idArg_ = StringExpUtil.getIdFromAllTypes(_arg);
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_classParam);
        if (StringUtil.quickEq(idArg_,idSuperType_)) {
            return _arg;
        }
        GeneType classBody_ = _context.getClassBody(idArg_);
        String generic_ = getSuperGeneric(classBody_, _context, 0, _classParam);
        return quickFormat(classBody_,_arg, generic_);
    }
    public static String getFullObject(String _subType, String _superType, ContextEl _context) {
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        DimComp dBaseParam_ = StringExpUtil.getQuickComponentBaseType(idSuperType_);
        int dim_ = dBaseParam_.getDim();
        String classParam_ = dBaseParam_.getComponent();
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        DimComp dBaseArg_ = StringExpUtil.getQuickComponentBaseType(idArg_);
        String baseArr_ = dBaseArg_.getComponent();
        if (!correctNbParameters(_subType,_context)) {
            return "";
        }
        if (StringUtil.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        GeneType classBody_ = _context.getClassBody(baseArr_);
        String generic_ = getSuperGeneric(classBody_,_context, dim_, classParam_);
        return quickFormat(classBody_,_subType, generic_);
    }

    public static String reflectFormat(String _first, String _second, ContextEl _context) {
        StringMap<String> varTypes_ = getVarTypes(_first, _context);
        return StringExpUtil.getReflectFormattedType(_second, varTypes_);
    }

    private static boolean primitiveTypeNullObject(String _className, Struct _instance, ContextEl _context) {
        return primitiveTypeNullObject(_className, _instance, _context.getStandards());
    }

    private static boolean primitiveTypeNullObject(String _className, Struct _instance, LgNames _stds) {
        if (!_stds.getPrimitiveTypes().contains(_className)) {
            return false;
        }
        return _instance == NullStruct.NULL_VALUE;
    }

    public static boolean hasBlockBreak(AbstractPageEl _ip, String _label) {
        if (!_ip.hasBlock()) {
            _ip.setNullReadWrite();
            return false;
        }
        AbstractStask bl_ = _ip.getLastStack();
        if (_label.isEmpty()) {
            if (bl_ instanceof LoopBlockStack || bl_ instanceof SwitchBlockStack) {
                ExecBlock forLoopLoc_ = bl_.getLastBlock();
                _ip.setBlock(forLoopLoc_);
                if (bl_ instanceof LoopBlockStack) {
                    _ip.setLastLoop((LoopBlockStack) bl_);
                    ((LoopBlockStack)bl_).setFinished(true);
                }
                return false;
            }
        } else {
            if (StringUtil.quickEq(_label, bl_.getLabel())){
                ExecBlock forLoopLoc_ = bl_.getLastBlock();
                _ip.setBlock(forLoopLoc_);
                if (bl_ instanceof LoopBlockStack) {
                    _ip.setLastLoop((LoopBlockStack) bl_);
                    ((LoopBlockStack)bl_).setFinished(true);
                }
                if (bl_ instanceof IfBlockStack) {
                    _ip.setLastIf((IfBlockStack) bl_);
                }
                if (bl_ instanceof TryBlockStack) {
                    _ip.setLastTry((TryBlockStack) bl_);
                }
                return false;
            }
        }
        return true;
    }
    public static boolean hasBlockContinue(ContextEl _conf,AbstractPageEl _ip, String _label) {
        if (!_ip.hasBlock()) {
            _ip.setNullReadWrite();
            return false;
        }
        AbstractStask bl_ = _ip.getLastStack();
        if (bl_ instanceof LoopBlockStack) {
            ExecBracedBlock br_ = bl_.getBlock();
            if (_label.isEmpty()) {
                LoopBlockStack lSt_;
                lSt_ = (LoopBlockStack) bl_;
                br_.removeLocalVars(_ip);
                ExecLoop loop_;
                loop_ = ((LoopBlockStack) bl_).getExecLoop();
                _ip.setBlock(br_);
                loop_.processLastElementLoop(_conf,lSt_);
                return false;
            }
            if (StringUtil.quickEq(_label, bl_.getLabel())){
                LoopBlockStack lSt_;
                lSt_ = (LoopBlockStack) bl_;
                br_.removeLocalVars(_ip);
                ExecLoop loop_;
                loop_ = ((LoopBlockStack) bl_).getExecLoop();
                _ip.setBlock(br_);
                loop_.processLastElementLoop(_conf,lSt_);
                return false;
            }
        }
        return true;
    }
    public static void setVisited(AbstractPageEl _ip, ExecBracedBlock _block) {
        if (!_ip.hasBlock()) {
            _ip.setNullReadWrite();
            return;
        }
        _ip.getLastStack().setCurrentVisitedBlock(_block);
    }
    public static void processFinally(ContextEl _cont,ExecBracedBlock _block) {
        AbstractPageEl ip_ = _cont.getLastPage();
        TryBlockStack ts_ = ip_.getLastTry();
        if (ts_ == null) {
            ip_.setNullReadWrite();
            return;
        }
        ts_.setCurrentVisitedBlock(_block);
        if (ts_.isVisitedFinally()) {
            _block.processBlockAndRemove(_cont);
            return;
        }
        ts_.setVisitedFinally(true);
        ip_.setBlock(_block.getFirstChild());
    }
    public static void processElseIf(ContextEl _cont,ExecCondition _cond) {
        AbstractPageEl ip_ = _cont.getLastPage();
        IfBlockStack if_ = ip_.getLastIf();
        if (if_ == null) {
            ip_.setNullReadWrite();
            return;
        }
        if_.setCurrentVisitedBlock(_cond);
        if (!if_.isEntered()) {
            ConditionReturn assert_ = _cond.evaluateCondition(_cont);
            if (assert_ == ConditionReturn.CALL_EX) {
                return;
            }
            if (assert_ == ConditionReturn.YES) {
                if_.setEntered(true);
                ip_.setBlock(_cond.getFirstChild());
                return;
            }
        }
        if (ExecBracedBlock.isNextIfParts(_cond.getNextSibling())) {
            ip_.setBlock(_cond.getNextSibling());
            return;
        }
        _cond.processBlockAndRemove(_cont);
    }
    public static void processElse(ContextEl _cont,ExecBracedBlock _cond) {
        AbstractPageEl ip_ = _cont.getLastPage();
        IfBlockStack if_ = ip_.getLastIf();
        if (if_ == null) {
            ip_.setNullReadWrite();
            return;
        }
        if_.setCurrentVisitedBlock(_cond);
        if (!if_.isEntered()) {
            if_.setEntered(true);
            ip_.setBlock(_cond.getFirstChild());
            return;
        }
        _cond.processBlockAndRemove(_cont);
    }
    public static void processDo(ContextEl _cont,ExecCondition _cond) {
        AbstractPageEl ip_ = _cont.getLastPage();
        LoopBlockStack l_ = ip_.getLastLoop();
        if (l_ == null) {
            ip_.setNullReadWrite();
            return;
        }
        l_.setEvaluatingKeepLoop(true);
        ConditionReturn keep_ = _cond.evaluateCondition(_cont);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (keep_ == ConditionReturn.NO) {
            l_.setFinished(true);
        }
        l_.setEvaluatingKeepLoop(false);
        ip_.setBlock(_cond.getPreviousSibling());
    }
    public static void processBlockAndRemove(ContextEl _context,ExecBlock _bl) {
        AbstractPageEl ip_ = _context.getLastPage();
        if (!ip_.hasBlock()) {
            ip_.setNullReadWrite();
            return;
        }
        ip_.removeLastBlock();
        _bl.processBlock(_context);
    }
    public static Argument getIndexLoop(ContextEl _context, String _val, PageEl _lastPage, int _deep) {
        LgNames stds_ = _context.getStandards();
        Cache cache_ = _lastPage.getCache();
        if (cache_ != null) {
            LoopVariable loopVar_ = cache_.getLoopVar(_val,_deep);
            if (loopVar_ != null) {
                byte cast_ = ClassArgumentMatching.getPrimitiveCast(loopVar_.getIndexClassName(), _context.getStandards().getPrimTypes());
                LongStruct str_ = new LongStruct(loopVar_.getIndex());
                Struct value_ = NumParsers.convertToInt(cast_, str_);
                return new Argument(value_);
            }
        }
        LoopVariable locVar_ = _lastPage.getVars().getVal(_val);
        if (locVar_ == null) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _context.setCallingState(new ErrorStruct(_context,npe_));
            return new Argument(new IntStruct(0));
        }
        byte cast_ = ClassArgumentMatching.getPrimitiveCast(locVar_.getIndexClassName(), _context.getStandards().getPrimTypes());
        LongStruct str_ = new LongStruct(locVar_.getIndex());
        Struct value_ = NumParsers.convertToInt(cast_, str_);
        return new Argument(value_);
    }

    public static void incrIndexLoop(ContextEl _context, String _val, PageEl _lastPage, int _deep) {
        if (_context.callsOrException()) {
            return;
        }
        Cache cache_ = _lastPage.getCache();
        if (cache_ != null) {
            LoopVariable loopVar_ = cache_.getLoopVar(_val, _deep);
            if (loopVar_ != null) {
                loopVar_.setIndex(loopVar_.getIndex() + 1);
                return;
            }
        }
        LoopVariable locVar_ = _lastPage.getVars().getVal(_val);
        LgNames stds_ = _context.getStandards();
        if (locVar_ == null) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _context.setCallingState(new ErrorStruct(_context,npe_));
            return;
        }
        locVar_.setIndex(locVar_.getIndex() + 1);
    }

    public static Argument getValue(ContextEl _context, String _val, PageEl _lastPage, int _deep) {
        Cache cache_ = _lastPage.getCache();
        if (cache_ != null) {
            LocalVariable loopVar_ = cache_.getLocalVar(_val,_deep);
            if (loopVar_ != null) {
                return new Argument(loopVar_.getStruct());
            }
        }
        StringMap<LocalVariable> valueVars_ = _lastPage.getValueVars();
        return getValueVar(_val, valueVars_, _context);
    }

    public static Argument getValueVar(String _val, StringMap<LocalVariable> _valueVars, ContextEl _context) {
        LocalVariable locVar_ = _valueVars.getVal(_val);
        if (locVar_ == null) {
            LgNames stds_ = _context.getStandards();
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _context.setCallingState(new ErrorStruct(_context,npe_));
            return new Argument();
        }
        return new Argument(locVar_.getStruct());
    }

    public static Argument setValue(ContextEl _context, String _val, PageEl _lastPage, Argument _value, int _deep) {
        if (_context.callsOrException()) {
            return new Argument();
        }
        LgNames stds_ = _context.getStandards();
        Cache cache_ = _lastPage.getCache();
        if (cache_ != null) {
            LocalVariable loopVar_ = cache_.getLocalVar(_val,_deep);
            if (loopVar_ != null) {
                return checkSet(_context,loopVar_,_value);
            }
        }
        LocalVariable locVar_ = _lastPage.getValueVars().getVal(_val);
        if (locVar_ == null) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _context.setCallingState(new ErrorStruct(_context,npe_));
            return new Argument();
        }
        return checkSet(_context,locVar_,_value);
    }

    private static Argument checkSet(ContextEl _conf, LocalVariable _loc, Argument _right) {
        String formattedClassVar_ = _loc.getClassName();
        if (!checkQuick(formattedClassVar_, _right, _conf)) {
            return Argument.createVoid();
        }
        _loc.setStruct(_right.getStruct());
        return _right;
    }

    /**parameriterized sub type (possibly array) - super type<br/>
     Let this code:<br/>
     <code><pre>public class my.pkg.MyClass&lt;A,B&gt;:MySecondClass&lt;A,B&gt;{}</pre>
     <pre>public class my.pkg.MySecondClass&lt;C,D&gt;{}</pre></code><br/>
     Sample 1: "my.pkg.MyClass&lt;long,int&gt;" - "my.pkg.MySecondClass" => "my.pkg.MySecondClass&lt;long,int&gt;"<br/>
     Sample 2: "my.pkg.MyClass" - "my.pkg.MySecondClass" => null<br/>
     Sample 3: "my.pkg.MySecondClass&lt;long,int&gt;" - "my.pkg.MyClass" => null<br/>
     Sample 4: "my.pkg.MyClass&lt;long,int&gt;" - "my.pkg.MySecondClass[]" => "my.pkg.MySecondClass&lt;long,int&gt;[]"<br/>
     */
    public static String getFullTypeByBases(String _subType, String _superType, ContextEl _context) {
        if (!correctNbParameters(_subType,_context)) {
            return "";
        }
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        if (StringUtil.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        DimComp dBaseParam_ = StringExpUtil.getQuickComponentBaseType(idSuperType_);
        int dim_ = dBaseParam_.getDim();
        String classParam_ = dBaseParam_.getComponent();
        DimComp dBaseArg_ = StringExpUtil.getQuickComponentBaseType(idArg_);
        String baseArr_ = dBaseArg_.getComponent();
        int dimArg_ = dBaseArg_.getDim();
        if (StringUtil.quickEq(classParam_, _context.getStandards().getContent().getCoreNames().getAliasObject())) {
            if (dimArg_ < dim_) {
                return "";
            }
            return _superType;
        }
        if (dimArg_ != dim_) {
            return "";
        }
        if (ExecClassArgumentMatching.isPrimitive(baseArr_,_context)) {
            PrimitiveType pr_ = _context.getStandards().getPrimitiveTypes().getVal(baseArr_);
            if (StringUtil.contains(pr_.getAllSuperType(_context), classParam_)) {
                return _superType;
            }
            return "";
        }
        if (StringUtil.quickEq(_subType, _context.getStandards().getContent().getCoreNames().getAliasVoid())) {
            return "";
        }
        if (StringUtil.quickEq(_superType, _context.getStandards().getContent().getCoreNames().getAliasVoid())) {
            return "";
        }
        GeneType classBody_ = _context.getClassBody(baseArr_);
        String generic_ = getSuperGeneric(classBody_,_context, dim_, classParam_);
        return format(classBody_,_subType, generic_);
    }

    public static String getOverridingFullTypeByBases(String _subType, String _superType, ContextEl _context) {
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        if (StringUtil.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        GeneType classBody_ = _context.getClassBody(idArg_);
        String generic_ = getSuperGeneric(classBody_,_context, 0, idSuperType_);
        return quickFormat(classBody_,_subType, generic_);
    }

    private static String getSuperGeneric(GeneType _subType, ContextEl _context, int _dim, String _classParam) {
        String generic_ = "";
        String param_ = StringExpUtil.getIdFromAllTypes(_classParam);
        if (_subType instanceof ExecAnnotationBlock) {
            if (StringUtil.quickEq(param_, _context.getStandards().getContent().getReflect().getAliasAnnotationType())) {
                return StringExpUtil.getPrettyArrayType(param_,_dim);
            }
        }
        if (_subType instanceof ExecRootBlock) {
            for (ExecFormattedRootBlock e: ((ExecRootBlock)_subType).getAllGenericSuperTypes()) {
                String g = e.getFormatted();
                if (StringUtil.quickEq(StringExpUtil.getIdFromAllTypes(g),param_)) {
                    generic_ = g;
                    break;
                }
            }
        }
        if (_subType instanceof StandardType) {
            for (String g: ((StandardType)_subType).getAllGenericSuperTypes()) {
                 if (StringUtil.quickEq(StringExpUtil.getIdFromAllTypes(g),param_)) {
                    generic_ = g;
                    break;
                }
            }
        }
        if (generic_.isEmpty()) {
            return "";
        }
        return StringExpUtil.getPrettyArrayType(generic_,_dim);
    }

    /**Returns a formatted string (variables present in second type are defined in the scope of the first type id)<br/>
     Let this code:<br/>
     <code><pre>public class my.pkg.MyClass&lt;K,V&gt;{}</pre>
     <pre>public class my.pkg.MySecondClass&lt;K&gt;{</pre>
     <pre>     public class Inner&lt;V&gt;{}</pre>
     <pre>}</pre></code><br/>
     <pre>public class my.pkg.MyThirdClass&lt;K&gt;{</pre>
     <pre>     public static class Inner&lt;V&gt;{}</pre></code>
     <pre>}</pre></code><br/>
     Sample 1: "my.pkg.MyClass&lt;long,int&gt;" - "?#K" => null<br/>
     Sample 2: "my.pkg.MyClass&lt;?long,int&gt;" - "#K" => "long"<br/>
     Sample 3: "my.pkg.MyClass&lt;?long,int&gt;" - "?#K" => null<br/>
     */
    public static String format(String _first, String _second, ContextEl _context) {
        StringMap<String> varTypes_ = getVarTypes(_first, _context);
        return StringExpUtil.getFormattedType(_second, varTypes_);
    }
    public static String format(GeneType _type, String _first, String _second) {
        StringMap<String> varTypes_ = getVarTypes(_first, _type);
        return StringExpUtil.getFormattedType(_second, varTypes_);
    }

    /**Returns a formatted string (variables present in second type are defined in the scope of the first type id)<br/>
     Let this code:<br/>
     <code><pre>public class my.pkg.MyClass&lt;K,V&gt;{}</pre>
     <pre>public class my.pkg.MySecondClass&lt;K&gt;{</pre>
     <pre>     public class Inner&lt;V&gt;{}</pre>
     <pre>}</pre></code><br/>
     <pre>public class my.pkg.MyThirdClass&lt;K&gt;{</pre>
     <pre>     public static class Inner&lt;V&gt;{}</pre></code>
     <pre>}</pre></code><br/>
     Sample 1: "my.pkg.MyClass&lt;long,int&gt;" - "#K" => "long"<br/>
     Sample 2: "my.pkg.MyClass&lt;long,int&gt;" - "#V" => "int"<br/>
     Sample 3: "my.pkg.MySecondClass&lt;long&gt;..Inner&lt;int&gt;" - "#K" => "long"<br/>
     Sample 4: "my.pkg.MyThirdClass..Inner&lt;int&gt;" - "#V" => "int"<br/>
     */
    public static String quickFormat(String _first, String _second, ContextEl _context) {
        return quickFormat(_context.getClassBody(StringExpUtil.getIdFromAllTypes(_first)),_first,_second);
    }
    public static String quickFormat(GeneType _type, String _first, String _second) {
        StringMap<String> varTypes_ = getVarTypes(_first,_type);
        return StringExpUtil.getQuickFormattedType(_second, varTypes_);
    }

    /**Returns the map of variables of a paramethized type<br/>
      Let this code:<br/>
           <code><pre>public class my.pkg.MyClass&lt;K,V&gt;{}</pre>
           <pre>public class my.pkg.MySecondClass&lt;K&gt;{</pre>
           <pre>     public class Inner&lt;V&gt;{}</pre>
           <pre>}</pre></code><br/>
           <pre>public class my.pkg.MyThirdClass&lt;K&gt;{</pre>
           <pre>     public static class Inner&lt;V&gt;{}</pre></code>
           <pre>}</pre></code><br/>
     Sample 1: "my.pkg.MyClass&lt;long,int&gt;" => ["K"-"long","V"-"int"]<br/>
     Sample 2: "my.pkg.MySecondClass&lt;long&gt;..Inner&lt;int&gt;" => ["K"-"long","V"-"int"]<br/>
     Sample 3: "my.pkg.MyThirdClass..Inner&lt;int&gt;" => ["V"-"int"]<br/>
     Sample 4: "my.pkg.MyClass&lt;long,int&gt;[]" => ["K"-"long","V"-"int"]<br/>
     */
    private static StringMap<String> getVarTypes(String _className, ContextEl _context) {
        StringList types_ = StringExpUtil.getAllTypes(_className);
        String className_ = StringExpUtil.getQuickComponentBaseType(types_.first()).getComponent();
        GeneType root_ = _context.getClassBody(className_);
        return getVarTypes(types_, root_);
    }

    private static StringMap<String> getVarTypes(String _className, GeneType _root) {
        StringList types_ = StringExpUtil.getAllTypes(_className);
        return getVarTypes(types_,_root);
    }
    private static StringMap<String> getVarTypes(StringList _types, GeneType _root) {
        StringMap<String> varTypes_ = new StringMap<String>();
        if (_root == null) {
            return varTypes_;
        }
        int i_ = IndexConstants.FIRST_INDEX;
        for (String t: _root.getParamTypesValues()) {
            i_++;
            if (!_types.isValidIndex(i_)) {
                return varTypes_;
            }
            String arg_ = _types.get(i_);
            varTypes_.put(t, arg_);
        }
        return varTypes_;
    }

    /**Checks nb parameters of the most wrapping type<br/>
     Let this code:<br/>
     <code><pre>public class my.pkg.MySimpleClass{}</pre>
     <pre>public class my.pkg.MyClass&lt;K&gt;{}</pre>
     <pre>public class my.pkg.MySecondClass&lt;K&gt;{</pre>
     <pre>     public class Inner&lt;V&gt;{}</pre>
     <pre>}</pre></code><br/>
     <pre>public class my.pkg.MyThirdClass&lt;K&gt;{</pre>
     <pre>     public static class Inner&lt;V&gt;{}</pre></code>
     <pre>}</pre></code><br/>
    Sample 1: int => true<br/>
    Sample 2: my.pkg.MySimpleClass => true<br/>
    Sample 3: my.pkg.MyClass => false<br/>
    Sample 4: my.pkg.MyClass&lt;Integer&gt; => true<br/>
    Sample 5: my.pkg.MyClass&lt;my.pkg.MyClass&gt; => true<br/>
    Sample 6: my.pkg.MyClass&lt;Integer,Integer&gt; => false<br/>
    Sample 7: my.pkg.MySecondClass..Inner&lt;Integer&gt; => false<br/>
    Sample 8: my.pkg.MySecondClass&lt;Integer&gt;..Inner&lt;Integer&gt; => true<br/>
    Sample 9: my.pkg.MySecondClass..Inner&lt;Integer,Integer&gt; => false<br/>
    Sample 10: my.pkg.MyThirdClass..Inner&lt;Integer,Integer&gt; => true<br/>
    Sample 11: my.pkg.MyThirdClass&lt;Integer&gt;..Inner&lt;Integer&gt; => false<br/>
    Sample 12: my.pkg.MyThirdClass..Inner&lt;Integer,Integer&gt; => false<br/>
    */
    public static boolean correctNbParameters(String _genericClass, ContextEl _context) {
        //From analyze
        String idCl_ = StringExpUtil.getIdFromAllTypes(_genericClass);
        String compo_ = StringExpUtil.getQuickComponentBaseType(idCl_).getComponent();
        GeneType info_ = _context.getClassBody(compo_);
        if (info_ == null) {
            if (ExecClassArgumentMatching.isPrimitive(compo_,_context)) {
                return true;
            }
            return StringUtil.quickEq(compo_, _context.getStandards().getContent().getCoreNames().getAliasVoid());
        }
        String fct_ = _context.getStandards().getContent().getReflect().getAliasFct();
        Ints rep_ = info_.getTypeVarCounts();
        return StringExpUtil.commonCorrectType(_genericClass,compo_,fct_,rep_);
    }

    public static Argument getField(FieldMetaInfo _meta, Argument _previous, ContextEl _conf) {
        String baseClass_ = _meta.getDeclaringClass();
        baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
        String fieldName_ = _meta.getName();
        boolean isStaticField_ = _meta.isStaticField();
        String type_ = _meta.getType();
        return getField(new DefaultSetOffset(_conf),_conf.getExiting(),baseClass_, fieldName_, isStaticField_,type_, _previous, _conf, -1);
    }

    public static Argument getField(AbstractSetOffset _setOffset, AbstractExiting _exit, String _className, String _fieldName, boolean _isStaticField, String _ret, Argument _previous, ContextEl _conf, int _possibleOffset) {
        if (_possibleOffset > -1) {
            _setOffset.setOffset(_possibleOffset);
        }

        if (_isStaticField) {
            return getStaticField(_exit,_className, _fieldName, _ret, _conf);
        }
        return getInstanceField(_className, _fieldName, _previous, _conf);
    }

    public static Argument getInstanceField(String _className, String _fieldName, Argument _previous, ContextEl _conf) {
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
                _conf.setCallingState(new ErrorStruct(_conf,npe_));
                return Argument.createVoid();
            }
            _conf.setCallingState(new ErrorStruct(_conf, StringUtil.concat(argClassName_,RETURN_LINE,_className, RETURN_LINE),cast_));
            return _previous;
        }
        ClassFieldStruct entry_ = ((FieldableStruct) previous_).getEntryStruct(fieldId_);
        if (entry_ == null) {
            _conf.setCallingState(new ErrorStruct(_conf, StringUtil.concat(argClassName_, RETURN_LINE,_className, RETURN_LINE),cast_));
            return _previous;
        }
        Struct struct_ = entry_.getStruct();
        _conf.getInitializingTypeInfos().addSensibleField(previous_, struct_);
        return new Argument(struct_);
    }

    public static Argument getStaticField(AbstractExiting _exit, String _className, String _fieldName, String _ret, ContextEl _conf) {
        Classes classes_ = _conf.getClasses();
        ClassField fieldId_ = new ClassField(_className, _fieldName);
        if (_exit.hasToExit(_className)) {
            return Argument.createVoid();
        }
        Struct struct_ = classes_.getStaticField(fieldId_,_ret, _conf);
        _conf.getInitializingTypeInfos().addSensibleField(_conf,fieldId_.getClassName(), struct_);
        return new Argument(struct_);
    }

    public static Argument setField(FieldMetaInfo _meta, Argument _previous, Argument _right, ContextEl _conf) {
        String baseClass_ = _meta.getDeclaringClass();
        baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
        String fieldName_ = _meta.getName();
        boolean isStaticField_ = _meta.isStaticField();
        boolean isFinalField_ = _meta.isFinalField();
        String type_ = _meta.getType();
        return setField(new DefaultSetOffset(_conf),_conf.getExiting(),_meta.getDeclaring(),baseClass_, fieldName_, isStaticField_, isFinalField_, true, type_, _previous, _right, _conf, -1);
    }

    public static Argument setField(AbstractSetOffset _setOffset, AbstractExiting _exit, ExecRootBlock _rootBlock, String _className, String _fieldName,
                                    boolean _isStaticField, boolean _finalField, boolean _failIfFinal,
                                    String _returnType, Argument _previous,
                                    Argument _right, ContextEl _conf, int _possibleOffset) {
        if (_possibleOffset > -1) {
            _setOffset.setOffset(_possibleOffset);
        }
        if (_isStaticField) {
            return setStaticField(_exit,_className, _fieldName, _finalField, _failIfFinal, _returnType, _right, _conf);
        }
        return setInstanceField(_rootBlock,_className, _fieldName, _returnType, _previous, _right, _conf);
    }

    public static Argument setInstanceField(ExecRootBlock _rootBlock, String _className, String _fieldName, String _returnType, Argument _previous, Argument _right, ContextEl _conf) {
        ClassField fieldId_ = new ClassField(_className, _fieldName);
        LgNames stds_ = _conf.getStandards();
        Struct previous_ = _previous.getStruct();
        String argClassName_ = previous_.getClassName(_conf);
        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
        if (!(previous_ instanceof FieldableStruct)) {
            if (previous_ == NullStruct.NULL_VALUE) {
                String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
                _conf.setCallingState(new ErrorStruct(_conf,npe_));
                return Argument.createVoid();
            }
            _conf.setCallingState(new ErrorStruct(_conf, StringUtil.concat(argClassName_, RETURN_LINE,_className, RETURN_LINE),cast_));
            return Argument.createVoid();
        }
        ClassFieldStruct entry_ = ((FieldableStruct) previous_).getEntryStruct(fieldId_);
        if (entry_ == null) {
            _conf.setCallingState(new ErrorStruct(_conf, StringUtil.concat(argClassName_, RETURN_LINE,_className, RETURN_LINE),cast_));
            return Argument.createVoid();
        }
        String classNameFound_ = getSuperGeneric(argClassName_, _className, _conf);
        String fieldType_ = quickFormat(_rootBlock,classNameFound_, _returnType);
        if (!checkQuick(fieldType_, _right, _conf)) {
            return Argument.createVoid();
        }
        if (_conf.getInitializingTypeInfos().isContainedSensibleFields(previous_)) {
            _conf.getInitializingTypeInfos().failInitEnums();
            return _right;
        }
        entry_.setStruct(_right.getStruct());
        return _right;
    }

    private static Argument setStaticField(AbstractExiting _exit, String _className, String _fieldName, boolean _finalField, boolean _failIfFinal, String _returnType, Argument _right, ContextEl _conf) {
        Classes classes_ = _conf.getClasses();
        ClassField fieldId_ = new ClassField(_className, _fieldName);
        LgNames stds_ = _conf.getStandards();
        if (_finalField && _failIfFinal) {
            String npe_;
            npe_ = stds_.getContent().getCoreNames().getAliasIllegalArg();
            _conf.setCallingState(new ErrorStruct(_conf,npe_));
            return Argument.createVoid();
        }
        if (_exit.hasToExit(_className)) {
            return Argument.createVoid();
        }
        if (!checkQuick(_returnType, _right, _conf)) {
            return Argument.createVoid();
        }
        if (_conf.getInitializingTypeInfos().isSensibleField(_conf,fieldId_.getClassName())) {
            _conf.getInitializingTypeInfos().failInitEnums();
            return _right;
        }
        Classes.getStaticFieldMap(fieldId_.getClassName(), classes_.getStaticFields()).set(fieldId_.getFieldName(), _right.getStruct());
        return _right;
    }

    public static Struct getElement(Struct _struct, Struct _index, ContextEl _conf) {
        Struct elt_ = gearErrorWhenIndex(_struct, _index, _conf);
        _conf.getInitializingTypeInfos().addSensibleField(_struct, elt_);
        return elt_;
    }

    public static void setElement(Struct _struct, Struct _index, Struct _value, ContextEl _conf) {
        gearErrorWhenContain(_struct, _index, _value, _conf);
    }

    public static String toWrapper(String _class, LgNames _stds) {
        for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
            if (StringUtil.quickEq(e.getKey(), _class)) {
                return e.getValue().getWrapper();
            }
        }
        return _class;
    }

    public static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }

    public static CustList<Argument> getArgs(Struct... _args) {
        int len_ = _args.length;
        CustList<Argument> classes_ = new CustList<Argument>();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            classes_.add(new Argument(_args[i]));
        }
        return classes_;
    }

    public static Argument getFirstArgument(CustList<Argument> _list) {
        return getArgument(_list,0);
    }
    public static Argument getLastArgument(CustList<Argument> _list) {
        return getArgument(_list,_list.size()-1);
    }
    public static Argument getArgument(CustList<Argument> _list, int _index) {
        if (_list.isValidIndex(_index)) {
            return Argument.getNullableValue(_list.get(_index));
        }
        return Argument.createVoid();
    }
    public static ExecMethodOperation getParentOrNull(ExecOperationNode _node) {
        if (_node == null) {
            return null;
        }
        return _node.getParent();
    }
    public static ExecOperationNode getMainNode(ExecOperationNode _node) {
        ExecMethodOperation parent_ = _node.getParent();
        return getFirstNode(parent_);
    }

    public static ExecOperationNode getFirstNode(ExecMethodOperation _parent) {
        if (_parent == null) {
            return null;
        }
        return getNode(_parent.getChildrenNodes(),0);
    }

    public static ExecOperationNode getLastNode(ExecMethodOperation _parent) {
        CustList<ExecOperationNode> childrenNodes_ = _parent.getChildrenNodes();
        return getNode(childrenNodes_,childrenNodes_.size()-1);
    }
    public static ExecOperationNode getNextNode(ExecOperationNode _node) {
        ExecMethodOperation par_ = _node.getParent();
        if (par_ == null) {
            return null;
        }
        return getNode(par_.getChildrenNodes(),_node.getIndexChild()+1);
    }
    public static ArgumentsPair getArgumentPair(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ExecOperationNode _node) {
        int order_ = getOrder(_node);
        if (!_nodes.isValidIndex(order_)) {
            ArgumentsPair pair_ = new ArgumentsPair();
            pair_.setArgument(Argument.createVoid());
            return pair_;
        }
        return _nodes.getValue(order_);
    }
    public static int getOrder(ExecOperationNode _node) {
        if (_node == null) {
            return 0;
        }
        return _node.getOrder();
    }
    public static ExecOperationNode getNode(CustList<ExecOperationNode> _nodes, int _index) {
        if (_nodes.isValidIndex(_index)) {
            return _nodes.get(_index);
        }
        return null;
    }
    public static String getGenericTypeNameOrObject(ContextEl _ctx, String _id) {
        GeneType classBody_ = _ctx.getClassBody(_id);
        if (classBody_ != null) {
            return classBody_.getGenericString();
        }
        return _ctx.getStandards().getCoreNames().getAliasObject();
    }
}
