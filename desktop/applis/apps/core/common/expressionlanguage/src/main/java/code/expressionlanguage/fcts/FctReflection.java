package code.expressionlanguage.fcts;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.DimComp;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.AbstractMethodCriteria;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.AliasReflection;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class FctReflection implements StdCaller {
    protected FctReflection(){
    }
    public static CustList<MethodMetaInfo> listAnonLambda(ContextEl _cont, AnnotatedStruct _annot) {
        CustList<MethodMetaInfo> methods_ = new CustList<MethodMetaInfo>();
        for (ExecAnonymousFunctionBlock f: _annot.getAnonymousLambda()) {
            MethodMetaInfo met_ = new MethodMetaInfo(_cont, f, _annot.getFormatted());
            methods_.add(met_);
        }
        return methods_;
    }

    public static CustList<MethodMetaInfo> listSwitchMethod(ContextEl _cont, AnnotatedStruct _annot) {
        CustList<MethodMetaInfo> methods_ = new CustList<MethodMetaInfo>();
        for (ExecAbstractSwitchMethod f: _annot.getSwitchMethods()) {
            MethodMetaInfo met_ = new MethodMetaInfo(_cont, f, _annot.getFormatted());
            methods_.add(met_);
        }
        return methods_;
    }

    public static ErrorStruct getNonInvokableError(ContextEl _cont, AnnotatedStruct _instance, StackCall _stackCall) {
        return new ErrorStruct(_cont, _instance.getDisplayedString(_cont).getInstance(), _cont.getStandards().getContent().getCoreNames().getAliasNonInvokable(), _stackCall);
    }

    public static ArrayStruct getTypes(ContextEl _cont, StringList _typesNames) {
        return buildArrClass(_cont,ClassMetaInfo.getTypes(_cont, _typesNames));
    }

    public static ArrayStruct getFidParamsFct(ContextEl _cont, AnnotatedParamStruct _declaring) {
        return buildArrClass(_cont,ClassMetaInfo.getFidParamsFct(_cont, _declaring));
    }

    public static ArrayStruct getRealIdParamsFct(ContextEl _cont, AnnotatedParamStruct _declaring) {
        return buildArrClass(_cont,ClassMetaInfo.getRealIdParamsFct(_cont, _declaring));
    }

    public static ErrorStruct getNpe(ContextEl _cont, StackCall _stackCall) {
        return new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall);
    }

    public static ErrorStruct getClassIssue(ContextEl _cont, String _clDyn, String _errorType, StackCall _stackCall) {
        return new ErrorStruct(_cont, _clDyn, _errorType, _stackCall);
    }

    public static ArrayStruct getLowerWildCardBounds(ContextEl _cont, ClassMetaInfo _cl) {
        CustList<ClassMetaInfo> list_ = ClassMetaInfo.getLowerWildCardBoundsList(_cont, _cl);
        return buildArrClass(_cont, list_);
    }

    public static ArrayStruct getUpperWildCardBounds(ContextEl _cont, ClassMetaInfo _cl) {
        CustList<ClassMetaInfo> list_ = ClassMetaInfo.getUpperWildCardBoundsList(_cont, _cl);
        return buildArrClass(_cont, list_);
    }

    public static ArrayStruct getFormattedClassesMeta(ContextEl _cont, ClassMetaInfo _cl, ExecFormattedRootBlock _clName) {
        CustList<ClassMetaInfo> list_ = ClassMetaInfo.getFormattedInterfacesMetaList(_cont, _cl, _clName);
        return buildArrClass(_cont, list_);
    }

    public static ArrayStruct fetchBoundsClassesMeta(ContextEl _cont, ClassMetaInfo _cl) {
        CustList<ClassMetaInfo> list_ = ClassMetaInfo.fetchBoundsClassesMetaList(_cont, _cl);
        return buildArrClass(_cont, list_);
    }

    public static ArrayStruct fetchGenericBoundsClassesMeta(ContextEl _cont, ClassMetaInfo _cl) {
        CustList<ClassMetaInfo> list_ = ClassMetaInfo.fetchGenericBoundsClassesMetaList(_cont, _cl);
        return buildArrClass(_cont, list_);
    }

    public static CustList<MethodMetaInfo> filterMethods(ContextEl _cont, CustList<MethodMetaInfo> _methods, Struct _name, Struct _static, Struct _vararg, Struct _params) {
        AbstractMethodCriteria abs_ = _cont.getDefCriteria();
        return filterMethods(_methods, _name, _static, _vararg, _params, abs_);
    }

    public static CustList<MethodMetaInfo> filterMethods(CustList<MethodMetaInfo> _methods, Struct _name, Struct _static, Struct _vararg, Struct _params, AbstractMethodCriteria _abs) {
        CustList<MethodMetaInfo> candidates_ = new CustList<MethodMetaInfo>();
        for (MethodMetaInfo e : _methods) {
            MethodId id_ = e.getFid();
            if (eqStatic(id_, _name, _static, _vararg, _params, _abs.matches(id_))) {
                candidates_.add(e);
            }
        }
        return candidates_;
    }

    public static ArrayStruct buildArrClass(ContextEl _cont, CustList<ClassMetaInfo> _list) {
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String aliasClass_ = ref_.getAliasClassType();
        String className_= StringExpUtil.getPrettyArrayType(aliasClass_);
        int len_ = _list.size();
        ArrayStruct arr_ = new ArrayStruct(len_, className_);
        for (int i = 0; i < len_; i++) {
            arr_.set(i, _list.get(i));
        }
        return arr_;
    }

    public static ArrayStruct buildArrFields(ContextEl _cont, CustList<FieldMetaInfo> _fields) {
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String aliasClass_ = ref_.getAliasField();
        String className_= StringExpUtil.getPrettyArrayType(aliasClass_);
        ArrayStruct str_ = new ArrayStruct(_fields.size(), className_);
        int index_ = 0;
        for (FieldMetaInfo e: _fields) {
            str_.set(index_, e);
            index_++;
        }
        return str_;
    }

    public static ArrayStruct getConstructorsMeta(ContextEl _cont, CustList<ConstructorMetaInfo> _candidates) {
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String aliasClass_ = ref_.getAliasConstructor();
        String className_= StringExpUtil.getPrettyArrayType(aliasClass_);
        ArrayStruct str_ = new ArrayStruct(_candidates.size(), className_);
        int index_ = 0;
        for (ConstructorMetaInfo c: _candidates) {
            str_.set(index_, c);
            index_++;
        }
        return str_;
    }

    public static ArrayStruct getMethodsMeta(ContextEl _cont, CustList<MethodMetaInfo> _candidates) {
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String aliasClass_ = ref_.getAliasMethod();
        String className_= StringExpUtil.getPrettyArrayType(aliasClass_);
        ArrayStruct str_ = new ArrayStruct(_candidates.size(), className_);
        int index_ = 0;
        for (MethodMetaInfo c: _candidates) {
            str_.set(index_, c);
            index_++;
        }
        return str_;
    }

    public static void fetchLocalTypes(StringList _methods, ExecMemberCallingsBlock _callee) {
        if (_callee != null) {
            for (ExecRootBlock c: _callee.getReserved()) {
                _methods.add(c.getFullName());
            }
        }
    }

    public static void fetchAnonymous(StringList _methods, ExecMemberCallingsBlock _callee) {
        if (_callee != null) {
            for (ExecRootBlock c: _callee.getAnonymous()) {
                _methods.add(c.getFullName());
            }
        }
    }

    public static void fetchAnonymous(StringList _methods, ExecRootBlock _callee) {
        if (_callee instanceof ExecInfoBlock) {
            for (ExecRootBlock c: ((ExecInfoBlock)_callee).getElementContent().getContainer().getAnonymous()) {
                _methods.add(c.getFullName());
            }
        } else if (_callee != null) {
            for (ExecRootBlock c: _callee.getAnonymousRoot()) {
                _methods.add(c.getFullName());
            }
        }
    }

    public static String getReturnTypeClone(ContextEl _cont, String _instClass, String _idCl) {
        String ret_;
        if (ExecInherits.correctNbParameters(_instClass, _cont)) {
            DimComp dc_ = StringExpUtil.getQuickComponentBaseType(_idCl);
            String compo_ = dc_.getComponent();
            String geneForm_ = ExecHelper.getGenericTypeNameOrObject(_cont,StringExpUtil.getIdFromAllTypes(compo_));
            ret_ = StringExpUtil.getPrettyArrayType(geneForm_,dc_.getDim());
        } else {
            ret_ = _instClass;
        }
        return ret_;
    }

    public static boolean eqStatic(Identifiable _id, Struct _name,
                                   Struct _static, Struct _vararg, Struct _params, BooleanStruct _stMeth) {
        if (_name instanceof StringStruct) {
            StringStruct name_ = (StringStruct) _name;
            if (!StringUtil.quickEq(name_.getInstance(), _id.getName())) {
                return false;
            }
        }
        if (_static instanceof BooleanStruct && !_static.sameReference(_stMeth)) {
            return false;
        }
        if (_vararg instanceof BooleanStruct && !_vararg.sameReference(BooleanStruct.of(_id.isVararg()))) {
            return false;
        }
        return params(_id, _params);
    }

    private static boolean params(Identifiable _id, Struct _params) {
        if (_params instanceof ArrayStruct) {
            ArrayStruct params_ = (ArrayStruct) _params;
            int parLen_ = _id.getParametersTypesLength();
            if (params_.getLength() != parLen_) {
                return false;
            }
            for (int i = 0; i < parLen_; i++) {
                Struct par_ = params_.get(i);
                if (par_ instanceof ClassMetaInfo) {
                    ClassMetaInfo p_ = NumParsers.getClass(par_);
                    if (!StringUtil.quickEq(p_.getFormatted().getFormatted(), _id.getParametersType(i))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected static ArgumentWrapper buildStrArr(ContextEl _cont, StringList _localVars) {
        String arrStr_ = _cont.getStandards().getContent().getCharSeq().getAliasString();
        arrStr_ = StringExpUtil.getPrettyArrayType(arrStr_);
        int size_ = _localVars.size();
        ArrayStruct array_ = new ArrayStruct(size_, arrStr_);
        for (int i = 0; i < size_; i++) {
            array_.set(i, Argument.wrapStr(_localVars.get(i)));
        }
        return new ArgumentWrapper(array_);
    }
}
