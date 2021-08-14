package code.bean.nat.analyze.opers;

import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.*;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.common.ConstType;
import code.bean.nat.analyze.instr.NatElResolver;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.expressionlanguage.fwd.opers.AnaOperationContent;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class NatOperationNode {

    protected static final String EMPTY_STRING = "";

    private final MethodNatOperation parent;

    private NatOperationNode nextSibling;

    private final AnaOperationContent content;
    private String result;

    private final NatOperationsSequence operations;

    NatOperationNode(int _indexInEl, int _indexChild, MethodNatOperation _m, NatOperationsSequence _op) {
        content = new AnaOperationContent(_indexInEl,_indexChild);
        result = "";
        parent = _m;
        operations = _op;
    }

    /** Returns the id of a type<br/>
     Sample 1: "int" => ["int"]<br/>
     Sample 2: "Pair&lt;int,long&gt;" => ["Pair"]<br/>
     Sample 3: "Solo&lt;Pair&lt;int,long&gt;&gt;" => ["Solo"]<br/>
     */
    public static String getIdFromAllTypes(String _type) {
        return StringExpUtil.getAllTypes(_type).first();
    }

    public abstract void analyze(AnalyzedPageEl _page);

    public static void setRelativeOffsetPossibleAnalyzable(int _offset, AnalyzedPageEl _page) {
        _page.setOffset(_offset);
    }

    public static NatOperationNode createOperationNode(int _index,
                                                       int _indexChild, MethodNatOperation _m, NatOperationsSequence _op, AnalyzedPageEl _page) {
        NatOperationNode res_ = createOperationNodeBis(_index, _indexChild, _m, _op, _page);
        if (res_ instanceof MethodNatOperation) {
            ((MethodNatOperation)res_).calculateChildren();
        }
        return res_;
    }
    private static NatOperationNode createOperationNodeBis(int _index,
                                                           int _indexChild, MethodNatOperation _m, NatOperationsSequence _op, AnalyzedPageEl _page) {
        if (_op.getOperators().isEmpty()) {
            return createLeaf(_index, _indexChild, _m, _op, _page);
        }
        if (_op.getPriority() == NatElResolver.FCT_OPER_PRIO && _op.isCallDbArray()) {
            String fctName_ = _op.getFctName().trim();
            if (fctName_.isEmpty()) {
                return new IdNatOperation(_index, _indexChild, _m, _op);
            }
            return new FctNatOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == NatElResolver.FCT_OPER_PRIO) {
            return new DotNatOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == NatElResolver.UNARY_PRIO) {
            return new UnaryBooleanNatOperation(_index, _indexChild, _m, _op);
        }
        return new AffectationNatOperation(_index, _indexChild, _m, _op);
    }

    private static NatOperationNode createLeaf(int _index, int _indexChild, MethodNatOperation _m, NatOperationsSequence _op, AnalyzedPageEl _page) {
        ConstType ct_ = _op.getConstType();
        String originalStr_ = _op.getValues().getValue(IndexConstants.FIRST_INDEX);
        String str_ = originalStr_.trim();
        if (_m instanceof AbstractDotNatOperation) {
            NatOperationNode ch_ = _m.getFirstChild();
            if (ch_ != null) {
                return new SettableFieldNatOperation(_index, _indexChild, _m, _op,new StandardFieldOperation(_op));
            }
        }
        if (ct_ == ConstType.LOOP_INDEX) {
            return new FinalVariableNatOperation(_index, _indexChild, _m, _op);
        }
        AnaLocalVariable val_ = _page.getInfosVars().getVal(str_);
        if (val_ != null) {
            val_.setUsed(true);
            return new FinalVariableNatOperation(_index, _indexChild, _m, _op,val_.getClassName());
        }
        return new SettableFieldNatOperation(_index, _indexChild, _m, _op,new StandardFieldOperation(_op));
    }

    public abstract NatOperationNode getFirstChild();

    public final NatOperationNode getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(NatOperationNode _nextSibling) {
        nextSibling = _nextSibling;
    }

    public static NatFieldResult resolveDeclaredCustField(String _class, String _name, AnalyzedPageEl _page) {
        return getDeclaredCustFieldByContext(_class, _name, _page);
    }
    private static NatFieldResult getDeclaredCustFieldByContext(String _class,
                                                                String _name, AnalyzedPageEl _page) {
        StringMap<NatFieldResult> ancestors_ = new StringMap<NatFieldResult>();
        CustList<TypeInfo> typesGroup_= typeLists(_class, MethodAccessKind.INSTANCE, _page);
        StringList baseTypes_ = new StringList();
        StringMap<String> superTypesBaseAncBis_ = new StringMap<String>();
        feedTypes(typesGroup_, baseTypes_, superTypesBaseAncBis_);
        for (TypeInfo t: typesGroup_) {
            fetchFieldsType(ancestors_,
                    new NatScopeFilterType(t),
                    new NatScopeFilterField(_name));
        }
        return ancestors_.firstValue();
    }

    private static void feedTypes(CustList<TypeInfo> _list, StringList _baseTypes, StringMap<String> _superTypesBaseAnc) {
        for (TypeInfo t: _list) {
            if (t.isBase()) {
                String id_ = t.getTypeId();
                _baseTypes.add(id_);
                _superTypesBaseAnc.put(id_, id_);
                for (String m: t.getSuperTypes()) {
                    _superTypesBaseAnc.put(m, id_);
                }
            }
        }
    }

    private static void fetchFieldsType(StringMap<NatFieldResult> _ancestors,
                                        NatScopeFilterType _scope, NatScopeFilterField _scopeField) {
        AnaGeneType root_ = _scope.getTypeInfo().getRoot();
        String name_ = _scopeField.getName();
        for (StandardField f: ((SpecialNatClass) root_).getFields()) {
            if (StringUtil.quickEq(f.getFieldName(), name_)) {
                String type_ = f.getImportedClassName();
                NatFieldResult res_ = new NatFieldResult();
                String declaringBaseClass_ = getIdFromAllTypes(root_.getFullName());
                ClassField classField_ = new ClassField(declaringBaseClass_, name_);
                res_.getContent().setClassField(classField_);
                root_.getFullName();
                res_.setType(type_);
//                res_.getContent().setRealType(type_);
                _ancestors.addEntry(_scope.getFullName(),res_);
                return;
            }
        }
    }

    protected static String voidToObject(String _original, AnalyzedPageEl _page) {
        if (StringUtil.quickEq(_original, _page.getAliasVoid())) {
            return _page.getAliasObject();
        }
        return _original;
    }

    protected static ClassMethodIdReturn tryGetDeclaredCustMethod(MethodAccessKind _staticContext,
                                                                  String _classes, String _name,
                                                                  AnalyzedPageEl _page) {
        CustList<NatMethodInfo> methods_ = getDeclaredCustMethodByType(_staticContext, _classes, _page);
        return getCustResult(methods_, _name);
    }


    protected static CustList<NatMethodInfo>
    getDeclaredCustMethodByType(MethodAccessKind _staticContext,
                                String _fromClasses, AnalyzedPageEl _page) {
        CustList<NatMethodInfo> methods_;
        methods_ = new CustList<NatMethodInfo>();
        fetchParamClassAncMethods(_fromClasses,_staticContext, methods_, _page);
        return methods_;
    }


    private static void fetchParamClassAncMethods(String _fromClasses, MethodAccessKind _staticContext,
                                                  CustList<NatMethodInfo> _methods, AnalyzedPageEl _page) {
        CustList<TypeInfo> typeInfosGroups_ = typeLists(_fromClasses,_staticContext, _page);
        StringList baseTypes_ = new StringList();
        StringMap<String> superTypesBaseAncBis_ = new StringMap<String>();
        feedTypes(typeInfosGroups_, baseTypes_, superTypesBaseAncBis_);
        for (TypeInfo t: typeInfosGroups_) {
            NatScopeFilterType scType_ = new NatScopeFilterType(t);
            for (StandardMethod e: ((StandardType) scType_.getTypeInfo().getRoot()).getMethods()) {
                _methods.add(NatOperationNode.getMethodInfo(e, 0, scType_.getFormatted().getFormatted(), e.getId(), e.getImportedReturnType()));
            }
        }
    }

    public static CustList<TypeInfo> typeLists(String _fromClasses, MethodAccessKind _staticContext, AnalyzedPageEl _page) {
        CustList<TypeInfo> typeInfos_ = new CustList<TypeInfo>();
        String baseCurName_ = getIdFromAllTypes(_fromClasses);
        StandardType root_ = _page.getStandardsTypes().getVal(baseCurName_);
        String gene_ = root_.getGenericString();
        addToList(typeInfos_,_staticContext, root_,gene_, true);
        for (String m : root_.getAllGenericSuperTypes()) {
            StandardType sup_ = _page.getStandardsTypes().getVal(m);
            addToList(typeInfos_,_staticContext, sup_,m, false);
        }
        return typeInfos_;
    }

    private static void addToList(CustList<TypeInfo> _list, MethodAccessKind _k, StandardType _secondType, String _second, boolean _base) {
        if (_secondType == null) {
            return;
        }
        TypeInfo t_ = new TypeInfo(_secondType, _second, _k,_base);
        _list.add(t_);
    }

    public static NatMethodInfo getMethodInfo(StandardMethod _m, int _anc, String _formattedClass, MethodId _id, String _importedReturnType) {
        NatMethodInfo mloc_ = new NatMethodInfo();
        mloc_.types(_importedReturnType);
        mloc_.setStandardMethod(_m);
        mloc_.classMethodId(_formattedClass,_id);
        mloc_.setAncestor(_anc);
        mloc_.format(false);
        return mloc_;
    }

    private static ClassMethodIdReturn getCustResult(CustList<NatMethodInfo> _methods,
                                                     String _name) {
        CustList<NatMethodInfo> signatures_ = new CustList<NatMethodInfo>();
        for (NatMethodInfo e: _methods) {
            MethodId id_ = e.getConstraints();
            if (!StringUtil.quickEq(id_.getName(), _name)) {
                continue;
            }
            signatures_.add(e);
        }
        NatMethodInfo m_ = signatures_.first();
        MethodId id_ = m_.getFormatted();
        return buildResult(m_, id_);
    }

    public static ClassMethodIdReturn buildResult(NatMethodInfo _m, MethodId _id) {
        ClassMethodIdReturn res_ = new ClassMethodIdReturn();
        MethodId constraints_ = _m.getConstraints();
        String baseClassName_ = _m.getClassName();
        res_.setId(new ClassMethodId(baseClassName_, _id));
        res_.setRealId(constraints_);
        res_.setRealClass(baseClassName_);
        res_.setReturnType(_m.getReturnType());
        res_.setOriginalReturnType(_m.getOriginalReturnType());
        res_.setFileName("");
        res_.setStandardMethod(_m.getStandardMethod());
        res_.setAncestor(_m.getAncestor());
        res_.setAbstractMethod(false);
        return res_;
    }

    public final MethodNatOperation getParent() {
        return parent;
    }

    public final NatOperationsSequence getOperations() {
        return operations;
    }

    public final void setOrder(int _order) {
        content.setOrder(_order);
    }

    public final int getIndexInEl() {
        return content.getIndexInEl();
    }

    public final int getIndexChild() {
        return content.getIndexChild();
    }

    public String getNames() {
        return result;
    }

    public final void setResultClass(String _resultClass) {
        result = _resultClass;
    }

    public AnaOperationContent getContent() {
        return content;
    }

}
