package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ExecInheritsAdv {
    public static final String PREFIX_VAR_TYPE = "#";

    private ExecInheritsAdv() {
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

    public static Struct checkObject(String _param, Struct _arg, ContextEl _context, StackCall _stackCall) {
        byte cast_ = ExecClassArgumentMatching.getPrimitiveWrapCast(_param, _context.getStandards());
        Struct object_ = NumParsers.convertObject(cast_, _arg);
        checkQuick(_param, object_.getClassName(_context), _context, _stackCall);
        return object_;
    }

    public static boolean checkQuick(String _param, String _arg, ContextEl _context, StackCall _stackCall) {
        Struct ex_ = checkObjectEx(_param,_arg,_context, _stackCall);
        if (ex_ != null) {
            _stackCall.setCallingState(new CustomFoundExc(ex_));
            return false;
        }
        return true;
    }

    public static Struct checkObjectEx(String _param, String _arg, ContextEl _context, StackCall _stackCall) {
        LgNames stds_ = _context.getStandards();
        ErrorType err_ = ExecInherits.safeObject(_param, _arg, _context);
        if (err_ == ErrorType.CAST) {
            String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
            return new ErrorStruct(_context, ExecFieldTemplates.getBadCastMessage(_param, _arg),cast_, _stackCall);
        }
        if (err_ == ErrorType.NPE) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            return new ErrorStruct(_context,npe_, _stackCall);
        }
        return null;
    }

    public static String getMadeVarTypes(String _className, StringList _classNames, ContextEl _context) {
        String type_ = StringExpUtil.getIdFromAllTypes(_className);
        String fct_ = _context.getStandards().getContent().getReflect().getAliasFct();
        if (StringUtil.quickEq(type_, fct_)) {
            return fctMadeVarTypes(_classNames, fct_);
        }
        return defMadeVarTypes(_classNames, _context, type_);
    }

    private static String defMadeVarTypes(StringList _classNames, ContextEl _context, String _type) {
        GeneType root_ = _context.getClassBody(_type);
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
            arg_ = extractRef(arg_);

            varTypes_.addEntry(t.getName(), arg_);
            i_++;
        }
        String formatted_ = StringExpUtil.getQuickFormattedType(pref_, varTypes_);
        for (int i = 0; i < len_; i++) {
            ExecTypeVar t = typeVar_.get(i);
            for (String b:t.getConstraints()) {
                String arg_ = _classNames.get(i);
                if (arg_.startsWith("?") || arg_.startsWith("!")) {
                    continue;
                }
                arg_ = extractRef(arg_);
                String param_ = ExecInherits.format(root_,formatted_, b);
                if (!ExecInherits.isCorrectExecute(arg_,param_,_context)) {
                    return null;
                }
            }
        }
        return formatted_;
    }

    private static String extractRef(String _part) {
        if (_part.startsWith("~")) {
            return _part.substring(1);
        }
        return _part;
    }

    private static String fctMadeVarTypes(StringList _classNames, String _fct) {
        if (_classNames.isEmpty()) {
            return null;
        }
        StringList parts_ = new StringList();
        for (String s: _classNames) {
            parts_.add(extractWc(s));
        }
        StringBuilder str_ = new StringBuilder(_fct);
        str_.append(StringExpUtil.TEMPLATE_BEGIN);
        str_.append(StringUtil.join(parts_, StringExpUtil.TEMPLATE_SEP));
        str_.append(StringExpUtil.TEMPLATE_END);
        return str_.toString();
    }

    private static String extractWc(String _part) {
        if (StringUtil.quickEq(_part, StringExpUtil.SUB_TYPE)) {
            return _part;
        }
        if (_part.startsWith(StringExpUtil.SUB_TYPE)) {
            return _part.substring(StringExpUtil.SUB_TYPE.length());
        }
        if (_part.startsWith(StringExpUtil.SUP_TYPE)) {
            return _part.substring(StringExpUtil.SUP_TYPE.length());
        }
        return _part;
    }
}
