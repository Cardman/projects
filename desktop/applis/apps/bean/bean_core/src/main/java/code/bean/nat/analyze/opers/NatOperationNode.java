package code.bean.nat.analyze.opers;

import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.sml.NatAnalyzingDoc;
import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.instr.NatElResolver;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class NatOperationNode {

    protected static final String EMPTY_STRING = "";

    private final MethodNatOperation parent;

    private NatOperationNode nextSibling;

    private final int indexInEl;
    private final int indexChild;
    private int order = IndexConstants.INDEX_NOT_FOUND_ELT;
    private String result;

    private final NatOperationsSequence operations;

    NatOperationNode(int _indexInEl, int _indexChild, MethodNatOperation _m, NatOperationsSequence _op) {
        indexInEl = _indexInEl;
        indexChild = _indexChild;
        result = "";
        parent = _m;
        operations = _op;
    }

    public abstract void analyze(NatAnalyzedCode _page);

    public static NatOperationNode createOperationNode(int _index,
                                                       int _indexChild, MethodNatOperation _m, NatOperationsSequence _op, NatAnalyzedCode _page, NatAnalyzingDoc _builder) {
        return createOperationNodeBis(_index, _indexChild, _m, _op, _page,_builder);
    }
    private static NatOperationNode createOperationNodeBis(int _index,
                                                           int _indexChild, MethodNatOperation _m, NatOperationsSequence _op, NatAnalyzedCode _page, NatAnalyzingDoc _builder) {
        if (_op.getOpersNat().isEmpty()) {
            String originalStr_ = _op.getValNat().getValue(IndexConstants.FIRST_INDEX);
            String str_ = originalStr_.trim();
            if (_builder.isInternGlobal() && StringUtil.quickEq(str_, NatRenderAnalysis.INTERN)) {
                return new InternGlobalNatOperation(_index, _indexChild, _m, _op, _builder.getInternGlobalClass());
            }
            return NatOperationNode.createLeaf(_index, _indexChild, _m, _op, _page);
        }
        if (_op.getPrioNat() == NatElResolver.FCT_OPER_PRIO && _op.isCallDbArray()) {
            String fctName_ = _op.getFctName().trim();
            _op.getValNat().remove(0);
            if (fctName_.isEmpty()) {
                return new IdNatOperation(_index, _indexChild, _m, _op);
            }
            return new FctNatOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPrioNat() == NatElResolver.FCT_OPER_PRIO) {
            return new DotNatOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPrioNat() == NatElResolver.UNARY_PRIO) {
            return new UnaryBooleanNatOperation(_index, _indexChild, _m, _op);
        }
        return new AffectationNatOperation(_index, _indexChild, _m, _op);
    }

    public static NatOperationNode createLeaf(int _index, int _indexChild, MethodNatOperation _m, NatOperationsSequence _op, NatAnalyzedCode _page) {
        String originalStr_ = _op.getValNat().getValue(IndexConstants.FIRST_INDEX);
        String str_ = originalStr_.trim();
        if (_m instanceof AbstractDotNatOperation) {
            NatOperationNode ch_ = _m.getFirstChild();
            if (ch_ != null) {
                return new SettableFieldNatOperation(_index, _indexChild, _m, _op,new NatStandardFieldOperation(_op));
            }
        }
        if (_op.isVarIndex()) {
            return new FinalVariableNatOperation(_index, _indexChild, _m, _op, true);
        }
        String val_ = _page.getInfosVars().getVal(str_);
        if (val_ != null) {
            return new FinalVariableNatOperation(_index, _indexChild, _m, _op,val_, false);
        }
        return new SettableFieldNatOperation(_index, _indexChild, _m, _op,new NatStandardFieldOperation(_op));
    }

    public abstract NatOperationNode getFirstChild();

    public final NatOperationNode getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(NatOperationNode _nextSibling) {
        nextSibling = _nextSibling;
    }

    public static NatFieldResult resolveDeclaredCustField(String _class, String _name, NatAnalyzedCode _page) {
        return getDeclaredCustFieldByContext(_class, _name, _page);
    }
    private static NatFieldResult getDeclaredCustFieldByContext(String _class,
                                                                String _name, NatAnalyzedCode _page) {
        CustList<NatFieldResult> ancestors_ = new CustList<NatFieldResult>();
        CustList<SpecialNatClass> typesGroup_= typeLists(_class, _page);
        for (SpecialNatClass t: typesGroup_) {
            fetchFieldsType(ancestors_,
                    t,
                    _name);
        }
        return ancestors_.first();
    }

    private static void fetchFieldsType(CustList<NatFieldResult> _ancestors,
                                        SpecialNatClass _scope, String _scopeField) {
//        if (_scope == null) {
//            return;
//        }
        for (StandardField f: _scope.getFields()) {
            if (StringUtil.quickEq(f.getFieldName(), _scopeField)) {
                String type_ = f.getImportedClassName();
                NatFieldResult res_ = new NatFieldResult();
                res_.getContent().setField(f);
                res_.setType(type_);
                _ancestors.add(res_);
                return;
            }
        }
    }

    protected static NatClassMethodIdReturn tryGetDeclaredCustMethod(String _classes, String _name,
                                                                  NatAnalyzedCode _page) {
        CustList<NatMethodInfo> methods_ = getDeclaredCustMethodByType(_classes, _page);
        return getCustResult(methods_, _name);
    }


    protected static CustList<NatMethodInfo>
    getDeclaredCustMethodByType(String _fromClasses, NatAnalyzedCode _page) {
        CustList<NatMethodInfo> methods_;
        methods_ = new CustList<NatMethodInfo>();
        fetchParamClassAncMethods(_fromClasses, methods_, _page);
        return methods_;
    }


    private static void fetchParamClassAncMethods(String _fromClasses,
                                                  CustList<NatMethodInfo> _methods, NatAnalyzedCode _page) {
        CustList<SpecialNatClass> typeInfosGroups_ = typeLists(_fromClasses, _page);
        for (SpecialNatClass t: typeInfosGroups_) {
//            if(t == null) {
//                continue;
//            }
            for (SpecNatMethod e: t.getMethods()) {
                _methods.add(getMethodInfo(e, e.getImportedReturnType()));
            }
        }
    }

    private static CustList<SpecialNatClass> typeLists(String _fromClasses, NatAnalyzedCode _page) {
        CustList<SpecialNatClass> typeInfos_ = new CustList<SpecialNatClass>();
        SpecialNatClass root_ = _page.getStds().getVal(_fromClasses);
        for (String m : addNotNull(typeInfos_, root_)) {
            SpecialNatClass sup_ = _page.getStds().getVal(m);
            addNotNull(typeInfos_, sup_);
        }
        return typeInfos_;
    }

    private static StringList addNotNull(CustList<SpecialNatClass> _typeInfos, SpecialNatClass _sup) {
        if (_sup == null) {
            return new StringList();
        }
        _typeInfos.add(_sup);
        return _sup.getAllSuperTypes();
    }

    private static NatMethodInfo getMethodInfo(SpecNatMethod _m, String _importedReturnType) {
        NatMethodInfo mloc_ = new NatMethodInfo();
        mloc_.types(_importedReturnType);
        mloc_.setStandardMethod(_m);
        return mloc_;
    }

    private static NatClassMethodIdReturn getCustResult(CustList<NatMethodInfo> _methods,
                                                     String _name) {
        CustList<NatMethodInfo> signatures_ = new CustList<NatMethodInfo>();
        for (NatMethodInfo e: _methods) {
            if (!StringUtil.quickEq(e.getStandardMethod().getName(), _name)) {
                continue;
            }
            signatures_.add(e);
        }
        NatMethodInfo m_ = signatures_.first();
        return buildResult(m_);
    }

    private static NatClassMethodIdReturn buildResult(NatMethodInfo _m) {
        NatClassMethodIdReturn res_ = new NatClassMethodIdReturn();
        res_.setReturnType(_m.getReturnType());
        res_.setStandardMethod(_m.getStandardMethod());
        return res_;
    }

    public final MethodNatOperation getParent() {
        return parent;
    }

    public final NatOperationsSequence getOperations() {
        return operations;
    }

    public final int getOrder() {
        return order;
    }
    public final void setOrder(int _order) {
        order = _order;
    }

    public final int getIndexInEl() {
        return indexInEl;
    }

    public final int getIndexChild() {
        return indexChild;
    }

    public String getNames() {
        return result;
    }

    public final void setResultClass(String _resultClass) {
        result = _resultClass;
    }

}
