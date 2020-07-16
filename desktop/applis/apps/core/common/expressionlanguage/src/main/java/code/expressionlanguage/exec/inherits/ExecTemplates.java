package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.IndexesComparator;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.types.ExecPartTypeUtil;
import code.expressionlanguage.exec.types.ExecResultPartType;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.inherits.*;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.structs.*;
import code.util.*;

public final class ExecTemplates {

    public static final String ARR_BEG_STRING = "[";
    public static final String TEMPLATE_SEP = ",";
    public static final String TEMPLATE_END = ">";
    public static final String TEMPLATE_BEGIN = "<";
    public static final String PREFIX_VAR_TYPE = "#";
    public static final String SUB_TYPE = "?";
    public static final String SUP_TYPE = "!";

    public static final char LT = '<';

    public static final char GT = '>';

    private ExecTemplates(){
    }

    public static ArrayStruct newCustomArray(String _className, Ints _dims, ContextEl _cont) {
        TreeMap<Ints,Struct> indexesArray_;
        indexesArray_ = new TreeMap<Ints,Struct>(new IndexesComparator());
        Struct[] instanceGl_ = new Struct[_dims.first()];
        ArrayStruct output_ = new ArrayStruct(instanceGl_, StringExpUtil.getPrettyArrayType(_className, _dims.size()));
        Ints dims_ = new Ints();
        indexesArray_.put(new Ints(), output_);
        int glDim_ = _dims.size();
        int i_ = CustList.FIRST_INDEX;
        Struct defClass_ = PrimitiveTypeUtil.defaultClass(_className, _cont);
        for (int i : _dims) {
            dims_.add(i);
            glDim_--;
            if (glDim_ == 0) {
                for (Ints k: dims_.getAllIndexes()) {
                    indexesArray_.put(k, defClass_);
                }
                continue;
            }
            String formattedClass_ = StringExpUtil.getPrettyArrayType(_className, glDim_);
            for (Ints k: dims_.getAllIndexes()) {
                Struct[] instance_ = new Struct[_dims.get(i_ + 1)];
                Struct value_ = new ArrayStruct(instance_, formattedClass_);
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
            ind_.removeLast();
            int lastIndex_ = key_.last();
            Struct str_ = indexesArray_.getVal(ind_);
            Struct[] array_ = ExecArrayFieldOperation.getArray(str_,_cont).getInstance();
            array_[lastIndex_] = value_;
        }
        return output_;
    }
    /** nb calls of getParent - super type - arg object
     use class parent of object
     */
    public static Struct getParent(int _nbAncestors,String _required, Struct _current, ContextEl _an) {
        String id_ = StringExpUtil.getIdFromAllTypes(_required);
        LgNames lgNames_ = _an.getStandards();
        Argument arg_ = new Argument();
        String cast_ = lgNames_.getAliasCastType();
        if (_current != NullStruct.NULL_VALUE) {
            String className_ = lgNames_.getStructClassName(_current, _an);
            String cl_ = StringExpUtil.getIdFromAllTypes(className_);
            DimComp dimReq_ = StringExpUtil.getQuickComponentBaseType(id_);
            DimComp dimCurrent_ = StringExpUtil.getQuickComponentBaseType(cl_);
            if (StringList.quickEq(dimReq_.getComponent(),_an.getStandards().getAliasObject())) {
                if (dimReq_.getDim() > dimCurrent_.getDim()) {
                    _an.setException(new ErrorStruct(_an,cast_));
                    return NullStruct.NULL_VALUE;
                }
                return _current;
            }
            if (dimReq_.getDim() != dimCurrent_.getDim()) {
                _an.setException(new ErrorStruct(_an,cast_));
                return NullStruct.NULL_VALUE;
            }
            PrimitiveType pr_ = _an.getStandards().getPrimitiveTypes().getVal(dimCurrent_.getComponent());
            GeneType g_ = _an.getClassBody(dimCurrent_.getComponent());
            InheritedType in_ = null;
            if (pr_ != null) {
                in_ = pr_;
            } else {
                if (cl_.startsWith(Templates.ARR_BEG_STRING) || g_.withoutInstance()) {
                    in_ = g_;
                }
            }
            if (in_ != null) {
                if (!in_.isSubTypeOf(dimReq_.getComponent(),_an)) {
                    _an.setException(new ErrorStruct(_an,cast_));
                    return NullStruct.NULL_VALUE;
                }
                return _current;
            }
            arg_.setStruct(_current);
            for (int i = 0; i < _nbAncestors; i++) {
                Struct enc_ = arg_.getStruct();
                Struct par_ = enc_.getParent();
                _an.getInitializingTypeInfos().addSensibleField(enc_, par_);
                arg_.setStruct(par_);
            }
        }
        String npe_ = lgNames_.getAliasNullPe();
        if (arg_.isNull()) {
            _an.setException(new ErrorStruct(_an,npe_));
            return arg_.getStruct();
        }
        Struct current_ = arg_.getStruct();
        String className_ = lgNames_.getStructClassName(current_, _an);
        String cl_ = StringExpUtil.getIdFromAllTypes(className_);
        StringList list_ = new StringList();
        GeneType g_ = _an.getClassBody(cl_);
        while (!g_.isSubTypeOf(id_,_an)) {
            if (StringList.contains(list_, cl_)) {
                _an.setException(new ErrorStruct(_an,cast_));
                break;
            }
            list_.add(cl_);
            if (!(current_ instanceof WithParentStruct)) {
                _an.setException(new ErrorStruct(_an,cast_));
                break;
            }
            Struct par_ = current_.getParent();
            _an.getInitializingTypeInfos().addSensibleField(current_, par_);
            className_ = ((WithParentStruct)current_).getParentClassName();
            current_ = par_;
            cl_ = StringExpUtil.getIdFromAllTypes(className_);
            g_ = _an.getClassBody(cl_);
        }
        return current_;
    }
    /**Calls Templates.isCorrect*/
    public static String correctClassPartsDynamic(String _className, ContextEl _context, boolean _wildCard) {
        ExecResultPartType className_ = ExecPartTypeUtil.processExec(_className, _context);
        String res_ = className_.getResult();
        if (res_.isEmpty()) {
            return "";
        }
        if (_wildCard) {
            CustList<String> allArgTypes_ = StringExpUtil.getAllTypes(res_).mid(1);
            for (String m: allArgTypes_) {
                if (m.startsWith(SUB_TYPE)) {
                    return "";
                }
                if (m.startsWith(SUP_TYPE)) {
                    return "";
                }
            }
        }
        if (!ExecPartTypeUtil.checkParametersCount(className_, _context)){
            return "";
        }
        if (isCorrectTemplateAllExec(className_, _context)) {
            return res_;
        }
        return "";
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
                        if (!StringList.quickEq(n.getParam(), n.getArg())) {
                            okTree_ = false;
                            break;
                        }
                        continue;
                    }
                    if (StringList.quickEq(n.getParam(), n.getArg())) {
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
        String fct_ = _context.getStandards().getAliasFct();
        String obj_ = _context.getStandards().getAliasObject();
        String idBaseArrayArg_ = StringExpUtil.getIdFromAllTypes(baseArrayArg_);
        String idBaseArrayParam_ = StringExpUtil.getIdFromAllTypes(baseArrayParam_);
        if (StringList.quickEq(idBaseArrayArg_, fct_)) {
            if (StringList.quickEq(idBaseArrayParam_, fct_)) {
                int dim_ = dArg_.getDim();
                if (dim_ != dParam_.getDim()) {
                    return null;
                }
                if (StringList.quickEq(baseArrayParam_, fct_)) {
                    return new MappingPairs();
                }
                int len_ = typesParam_.size();
                if (typesArg_.size() != len_) {
                    return null;
                }
                return StringExpUtil.newMappingPairsFct(typesArg_, typesParam_, obj_);
            }
            return StringExpUtil.getMappingFctPairs(dArg_, dParam_, baseArrayParam_, obj_);
        }
        if (StringList.quickEq(idBaseArrayParam_, fct_)) {
            return null;
        }
        String generic_;
        generic_ = Templates.getFullTypeByBases(_arg, _param, _context);
        if (generic_.isEmpty()) {
            return null;
        }
        return StringExpUtil.newMappingPairs(generic_, typesParam_);
    }

    public static boolean checkObject(String _param, Argument _arg, ContextEl _context) {
        Struct str_ = _arg.getStruct();
        LgNames stds_ = _context.getStandards();
        ClassArgumentMatching cl_ = new ClassArgumentMatching(_param);
        _arg.setStruct(PrimitiveTypeUtil.convertObject(cl_, str_, stds_));
        return checkQuick(_param, _arg, _context);
    }

    public static boolean checkQuick(String _param, Argument _arg, ContextEl _context) {
        Struct ex_ = checkObjectEx(_param,_arg,_context);
        if (ex_ != null) {
            _context.setException(ex_);
            return false;
        }
        return true;
    }

    public static Struct checkObjectEx(String _param, Argument _arg, ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        ErrorType err_ = safeObject(_param, _arg, _context);
        if (err_ == ErrorType.CAST) {
            String cast_ = stds_.getAliasCastType();
            return new ErrorStruct(_context,cast_);
        }
        if (err_ == ErrorType.NPE) {
            String npe_ = stds_.getAliasNullPe();
            return new ErrorStruct(_context,npe_);
        }
        return null;
    }
    public static void setCheckedElements(CustList<Argument> _args, Struct _arr, ContextEl _context) {
        int len_ = _args.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            Argument chArg_ = _args.get(i);
            IntStruct ind_ = new IntStruct(i);
            ExecInvokingOperation.setElement(_arr, ind_, chArg_.getStruct(), _context);
            if (_context.callsOrException()) {
                return;
            }
        }
    }
    public static void setElements(CustList<Argument> _args, ArrayStruct _arr) {
        int len_ = _args.size();
        Struct[] arr_ = _arr.getInstance();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            arr_[i] = _args.get(i).getStruct();
        }
    }
    public static Struct okArgsEx(Identifiable _id, boolean _format, String _classNameFound, CustList<Argument> _firstArgs, ContextEl _conf, Argument _right) {
        StringList params_ = new StringList();
        boolean hasFormat_;
        if (_id instanceof MethodId) {
            hasFormat_ = ((MethodId)_id).canAccessParamTypes() || _format;
        } else {
            hasFormat_ = true;
        }
        if (hasFormat_ && !Templates.correctNbParameters(_classNameFound,_conf)) {
            LgNames stds_ = _conf.getStandards();
            String npe_ = stds_.getAliasIllegalArg();
            return new ErrorStruct(_conf,npe_);
        }
        if (hasFormat_) {
            int i_ = 0;
            for (String c: _id.getParametersTypes()) {
                String c_ = c;
                c_ = Templates.quickFormat(_classNameFound, c_, _conf);
                if (i_ + 1 == _id.getParametersTypes().size() && _id.isVararg()) {
                    c_ = StringExpUtil.getPrettyArrayType(c_);
                }
                params_.add(c_);
                i_++;
            }
        } else {
            int i_ = 0;
            for (String c: _id.getParametersTypes()) {
                String c_ = c;
                if (i_ + 1 == _id.getParametersTypes().size() && _id.isVararg()) {
                    c_ = StringExpUtil.getPrettyArrayType(c_);
                }
                params_.add(c_);
                i_++;
            }
        }
        if (_firstArgs.size() != params_.size()) {
            LgNames stds_ = _conf.getStandards();
            String cast_ = stds_.getAliasBadIndex();
            StringBuilder mess_ = new StringBuilder();
            mess_.append(_firstArgs.size());
            mess_.append(">=");
            mess_.append(params_.size());
            return new ErrorStruct(_conf,mess_.toString(),cast_);
        }
        int i_ = CustList.FIRST_INDEX;
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
                        LgNames stds_ = _conf.getStandards();
                        if (state_ == ErrorType.NPE) {
                            String npe_ = stds_.getAliasNullPe();
                            return new ErrorStruct(_conf,npe_);
                        } else {
                            String arrType_ = arr_.getClassName();
                            String param_ = StringExpUtil.getQuickComponentType(arrType_);
                            String arg_ = stds_.getStructClassName(s, _conf);
                            String cast_ = stds_.getAliasStore();
                            StringBuilder mess_ = new StringBuilder();
                            mess_.append(arg_);
                            mess_.append("!=");
                            mess_.append(param_);
                            return new ErrorStruct(_conf,mess_.toString(),cast_);
                        }
                    }
                }
            }
        }
        if (_id instanceof MethodId) {
            String name_ = _id.getName();
            if (StringList.quickEq("[]=", name_)) {
                String id_ = StringExpUtil.getIdFromAllTypes(_classNameFound);
                for (ExecOverridableBlock g: ExecBlock.getDeepMethodExecBlocks(_conf.getClasses().getClassBody(id_))) {
                    if (!StringList.quickEq("[]",g.getId().getName())) {
                        continue;
                    }
                    if(!g.getId().eqPartial((MethodId) _id)) {
                        continue;
                    }
                    String type_ = g.getImportedReturnType();
                    type_ = Templates.quickFormat(_classNameFound, type_, _conf);
                    Struct ex_ = checkObjectEx(type_, _right, _conf);
                    if (ex_ != null) {
                        return ex_;
                    }
                }
            }
        }
        return null;
    }
    public static boolean okArgs(Identifiable _id, boolean _format, String _classNameFound, CustList<Argument> _firstArgs, ContextEl _conf, Argument _right) {
        Struct ex_ = okArgsSet(_id, _format, _classNameFound, _firstArgs, _conf, _right);
        return ex_ == null;
    }
    public static Struct okArgsSet(Identifiable _id, boolean _format, String _classNameFound, CustList<Argument> _firstArgs, ContextEl _conf, Argument _right) {
        Struct ex_ = okArgsEx(_id, _format, _classNameFound, _firstArgs, _conf, _right);
        if (ex_ != null) {
            _conf.setException(ex_);
        }
        return ex_;
    }
    public static ErrorType safeObject(String _param, Argument _arg, ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        Struct str_ = _arg.getStruct();
        if (str_ != NullStruct.NULL_VALUE) {
            String a_ = stds_.getStructClassName(str_, _context);
            String param_ = PrimitiveTypeUtil.toWrapper(_param, stds_);
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
    public static void gearErrorWhenContain(Struct _array, Struct _index, Struct _value, ContextEl _context) {
        ErrorType err_ = getErrorWhenContain(_array, _index, _value, _context);
        LgNames stds_ = _context.getStandards();
        if (err_ == ErrorType.NOTHING) {
            ArrayStruct arr_ = (ArrayStruct) _array;
            Struct[] inst_ = arr_.getInstance();
            int index_ = ClassArgumentMatching.convertToNumber(_index).intStruct();
            String arrType_ = arr_.getClassName();
            String param_ = StringExpUtil.getQuickComponentType(arrType_);
            ClassArgumentMatching cl_ = new ClassArgumentMatching(param_);
            Struct conv_ = PrimitiveTypeUtil.convertStrictObject(cl_, _value, stds_);
            if (_context.getInitializingTypeInfos().isContainedSensibleFields(arr_)) {
                _context.getInitializingTypeInfos().failInitEnums();
                return;
            }
            inst_[index_] = conv_;
            return;
        }
        if (err_ == ErrorType.NPE) {
            String npe_ = stds_.getAliasNullPe();
            _context.setException(new ErrorStruct(_context,npe_));
            return;
        }
        if (err_ == ErrorType.BAD_INDEX) {
            String cast_ = stds_.getAliasBadIndex();
            ArrayStruct arr_ = (ArrayStruct) _array;
            Struct[] inst_ = arr_.getInstance();
            int index_ = ClassArgumentMatching.convertToNumber(_index).intStruct();
            StringBuilder mess_ = new StringBuilder();
            if (index_ < 0) {
                mess_.append(index_);
                mess_.append("<0");
            } else {
                mess_.append(index_);
                mess_.append(">=");
                mess_.append(inst_.length);
            }
            _context.setException(new ErrorStruct(_context,mess_.toString(),cast_));
            return;
        }
        if (err_ == ErrorType.CAST) {
            String cast_ = stds_.getAliasCastType();
            String type_ = _array.getClassName(_context);
            _context.setException(new ErrorStruct(_context,type_,cast_));
            return;
        }
        ArrayStruct arr_ = (ArrayStruct) _array;
        String arrType_ = arr_.getClassName();
        String param_ = StringExpUtil.getQuickComponentType(arrType_);
        String arg_ = stds_.getStructClassName(_value, _context);
        String cast_ = stds_.getAliasStore();
        StringBuilder mess_ = new StringBuilder();
        mess_.append(arg_);
        mess_.append("!=");
        mess_.append(param_);
        _context.setException(new ErrorStruct(_context,mess_.toString(),cast_));
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
        Struct[] inst_ = arr_.getInstance();
        int index_ = ClassArgumentMatching.convertToNumber(_index).intStruct();
        if (index_ < 0 || index_ >= inst_.length) {
            return ErrorType.BAD_INDEX;
        }
        String arrType_ = arr_.getClassName();
        String param_ = StringExpUtil.getQuickComponentType(arrType_);
        LgNames stds_ = _context.getStandards();
        if (primitiveTypeNullObject(param_, _value, stds_)) {
            return ErrorType.NPE;
        }
        if (_value != NullStruct.NULL_VALUE) {
            ClassArgumentMatching cl_ = new ClassArgumentMatching(param_);
            Struct conv_ = PrimitiveTypeUtil.convertStrictObject(cl_, _value, stds_);
            String arg_ = stds_.getStructClassName(conv_, _context);
            param_ = PrimitiveTypeUtil.toWrapper(param_, stds_);
            if (!isCorrectExecute(arg_, param_, _context)) {
                return ErrorType.STORE;
            }
        }
        return ErrorType.NOTHING;
    }
    public static ErrorType checkElement(ArrayStruct _arr, Struct _value, ContextEl _context) {
        String arrType_ = _arr.getClassName();
        String param_ = StringExpUtil.getQuickComponentType(arrType_);
        LgNames stds_ = _context.getStandards();
        if (primitiveTypeNullObject(param_, _value, stds_)) {
            return ErrorType.NPE;
        }
        if (_value != NullStruct.NULL_VALUE) {
            String arg_ = stds_.getStructClassName(_value, _context);
            param_ = PrimitiveTypeUtil.toWrapper(param_, stds_);
            if (!isCorrectExecute(arg_, param_, _context)) {
                return ErrorType.STORE;
            }
        }
        return ErrorType.NOTHING;
    }
    public static Struct gearErrorWhenIndex(Struct _array, Struct _index, ContextEl _context) {
        ErrorType err_ = getErrorWhenIndex(_array, _index);
        LgNames stds_ = _context.getStandards();
        if (err_ == ErrorType.NOTHING) {
            ArrayStruct arr_ = (ArrayStruct) _array;
            Struct[] inst_ = arr_.getInstance();
            int index_ = ClassArgumentMatching.convertToNumber(_index).intStruct();
            return inst_[index_];
        }
        if (err_ == ErrorType.NPE) {
            String npe_ = stds_.getAliasNullPe();
            _context.setException(new ErrorStruct(_context,npe_));
            return NullStruct.NULL_VALUE;
        }
        if (err_ == ErrorType.BAD_INDEX) {
            String cast_ = stds_.getAliasBadIndex();
            ArrayStruct arr_ = (ArrayStruct) _array;
            Struct[] inst_ = arr_.getInstance();
            int index_ = ClassArgumentMatching.convertToNumber(_index).intStruct();
            StringBuilder mess_ = new StringBuilder();
            if (index_ < 0) {
                mess_.append(index_);
                mess_.append("<0");
            } else {
                mess_.append(index_);
                mess_.append(">=");
                mess_.append(inst_.length);
            }
            _context.setException(new ErrorStruct(_context,mess_.toString(),cast_));
            return NullStruct.NULL_VALUE;
        }
        String cast_ = stds_.getAliasCastType();
        String type_ = _array.getClassName(_context);
        _context.setException(new ErrorStruct(_context,type_,cast_));
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
        Struct[] inst_ = arr_.getInstance();
        int index_ = ClassArgumentMatching.convertToNumber(_index).intStruct();
        if (index_ < 0 || index_ >= inst_.length) {
            return ErrorType.BAD_INDEX;
        }
        return ErrorType.NOTHING;
    }

    public static String getMadeVarTypes(String _className, StringList _classNames,ContextEl _context) {
        String type_ = StringExpUtil.getIdFromAllTypes(_className);
        String fct_ = _context.getStandards().getAliasFct();
        if (StringList.quickEq(type_, fct_)) {
            if (_classNames.isEmpty()) {
                return null;
            }
            StringList parts_ = new StringList();
            for (String s: _classNames) {
                if (StringList.quickEq(s, Templates.SUB_TYPE)) {
                    parts_.add(_context.getStandards().getAliasObject());
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
            str_.append(StringList.join(parts_, Templates.TEMPLATE_SEP));
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
        int i_ = CustList.FIRST_INDEX;
        for (ExecTypeVar t: typeVar_) {
            String arg_ = _classNames.get(i_);
            if (arg_.contains(PREFIX_VAR_TYPE)) {
                return null;
            }

            varTypes_.put(t.getName(), arg_);
            i_++;
        }
        String formatted_ = StringExpUtil.getQuickFormattedType(pref_, varTypes_);
        for (int i = 0; i < len_; i++) {
            ExecTypeVar t = typeVar_.get(i);
            for (String b:t.getConstraints()) {
                String param_ = Templates.format(formatted_, b, _context);
                if (!isCorrectExecute(_classNames.get(i),param_,_context)) {
                    return null;
                }
            }
        }
        return formatted_;
    }

    public static boolean isCorrectTemplateAllExec(ExecResultPartType _className, ContextEl _context) {
        return ExecPartTypeUtil.processAnalyzeConstraintsExec(_className,_context);
    }


    public static String getQuickFullTypeByBases(String _subType, String _superType, ContextEl _context) {
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        DimComp dBaseParam_ = StringExpUtil.getQuickComponentBaseType(idSuperType_);
        String classParam_ = dBaseParam_.getComponent();
        int dim_ = dBaseParam_.getDim();
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        DimComp dBaseArg_ = StringExpUtil.getQuickComponentBaseType(idArg_);
        if (StringList.quickEq(classParam_, _context.getStandards().getAliasObject())) {
            if (dBaseArg_.getDim() < dim_) {
                return "";
            }
            return _superType;
        }
        if (dBaseArg_.getDim() != dim_) {
            return "";
        }
        return getFullObject(_subType, _superType,_context);
    }
    public static String getSuperGeneric(String _arg, String _classParam, ContextEl _context) {
        if (!Templates.correctNbParameters(_arg,_context)) {
            return "";
        }
        String generic_ = "";
        String idArg_ = StringExpUtil.getIdFromAllTypes(_arg);
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_classParam);
        if (StringList.quickEq(idArg_,idSuperType_)) {
            return _arg;
        }
        String geneSubType_ = _context.getClassBody(idArg_).getGenericString();
        StringList curClasses_ = new StringList(geneSubType_);
        StringList visitedClasses_ = new StringList(geneSubType_);
        while (true) {
            StringList nextClasses_ = new StringList();
            for (String c : curClasses_) {
                String baseClass_ = StringExpUtil.getIdFromAllTypes(c);
                for (String s : Templates.getSuperInterfaceNames(baseClass_, _context)) {
                    String id_ = StringExpUtil.getIdFromAllTypes(s);
                    String geneSuperInterface_;
                    geneSuperInterface_ = Templates.quickFormat(c, s, _context);
                    if (StringList.quickEq(id_, idSuperType_)) {
                        generic_ = geneSuperInterface_;
                        break;
                    }
                    Templates.addTypeIdNotYet(visitedClasses_, nextClasses_, geneSuperInterface_);
                }
                if (!generic_.isEmpty()) {
                    break;
                }
            }
            if (!generic_.isEmpty()) {
                break;
            }
            if (nextClasses_.isEmpty()) {
                break;
            }
            curClasses_ = nextClasses_;
        }
        return Templates.quickFormat(_arg, generic_, _context);
    }
    public static String getFullObject(String _subType, String _superType, ContextEl _context) {
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        DimComp dBaseParam_ = StringExpUtil.getQuickComponentBaseType(idSuperType_);
        int dim_ = dBaseParam_.getDim();
        String classParam_ = dBaseParam_.getComponent();
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        DimComp dBaseArg_ = StringExpUtil.getQuickComponentBaseType(idArg_);
        String baseArr_ = dBaseArg_.getComponent();
        if (!Templates.correctNbParameters(_subType,_context)) {
            return "";
        }
        if (StringList.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        String geneSubType_ = _context.getClassBody(baseArr_).getGenericString();
        String generic_ = Templates.getSuperGeneric(_context, dim_, classParam_, geneSubType_);
        return Templates.quickFormat(_subType, generic_, _context);
    }

    public static String reflectFormat(String _first, String _second, ContextEl _context) {
        StringMap<String> varTypes_ = Templates.getVarTypes(_first, _context);
        return StringExpUtil.getReflectFormattedType(_second, varTypes_);
    }

    public static boolean primitiveTypeNullObject(String _className, Struct _instance, ContextEl _context) {
        return primitiveTypeNullObject(_className, _instance, _context.getStandards());
    }

    public static boolean primitiveTypeNullObject(String _className, Struct _instance, LgNames _stds) {
        if (!PrimitiveTypeUtil.isPrimitive(_className, _stds)) {
            return false;
        }
        return _instance == NullStruct.NULL_VALUE;
    }

    public static Argument getIndexLoop(ContextEl _context, String _val, PageEl _lastPage) {
        LoopVariable locVar_ = _lastPage.getVars().getVal(_val);
        LgNames stds_ = _context.getStandards();
        if (locVar_ == null) {
            String npe_ = stds_.getAliasNullPe();
            _context.setException(new ErrorStruct(_context,npe_));
            return new Argument(new IntStruct(0));
        }
        Argument a_ = new Argument();
        ClassArgumentMatching clArg_ = new ClassArgumentMatching(locVar_.getIndexClassName());
        LongStruct str_ = new LongStruct(locVar_.getIndex());
        Struct value_ = PrimitiveTypeUtil.convertStrictObject(clArg_, str_, stds_);
        a_.setStruct(value_);
        return a_;
    }

    public static void incrIndexLoop(ContextEl _context, String _val, PageEl _lastPage) {
        if (_context.callsOrException()) {
            return;
        }
        LoopVariable locVar_ = _lastPage.getVars().getVal(_val);
        LgNames stds_ = _context.getStandards();
        if (locVar_ == null) {
            String npe_ = stds_.getAliasNullPe();
            _context.setException(new ErrorStruct(_context,npe_));
            return;
        }
        locVar_.setIndex(locVar_.getIndex() + 1);
    }

    public static Argument getValue(ContextEl _context, String _val, PageEl _lastPage) {
        LocalVariable locVar_ = _lastPage.getValueVars().getVal(_val);
        LgNames stds_ = _context.getStandards();
        if (locVar_ == null) {
            String npe_ = stds_.getAliasNullPe();
            _context.setException(new ErrorStruct(_context,npe_));
            return new Argument();
        }
        Argument a_ = new Argument();
        a_.setStruct(locVar_.getStruct());
        return a_;
    }

    public static Argument setValue(ContextEl _context, String _val, PageEl _lastPage, Argument _value) {
        if (_context.callsOrException()) {
            return new Argument();
        }
        LocalVariable locVar_ = _lastPage.getValueVars().getVal(_val);
        LgNames stds_ = _context.getStandards();
        if (locVar_ == null) {
            String npe_ = stds_.getAliasNullPe();
            _context.setException(new ErrorStruct(_context,npe_));
            return new Argument();
        }
        return checkSet(_context,locVar_,_value);
    }

    public static Argument checkSet(ContextEl _conf, LocalVariable _loc, Argument _right) {
        String formattedClassVar_ = _loc.getClassName();
        if (!checkQuick(formattedClassVar_, _right, _conf)) {
            return Argument.createVoid();
        }
        _loc.setStruct(_right.getStruct());
        return _right;
    }
}
