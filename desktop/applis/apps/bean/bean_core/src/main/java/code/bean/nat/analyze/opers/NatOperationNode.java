package code.bean.nat.analyze.opers;

import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.*;
import code.bean.nat.analyze.instr.NatElResolver;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.expressionlanguage.fwd.opers.AnaOperationContent;
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

    public abstract void analyze(NatAnalyzedCode _page);

    public static NatOperationNode createOperationNode(int _index,
                                                       int _indexChild, MethodNatOperation _m, NatOperationsSequence _op, NatAnalyzedCode _page) {
        NatOperationNode res_ = createOperationNodeBis(_index, _indexChild, _m, _op, _page);
        if (res_ instanceof MethodNatOperation) {
            ((MethodNatOperation)res_).calculateChildren();
        }
        return res_;
    }
    private static NatOperationNode createOperationNodeBis(int _index,
                                                           int _indexChild, MethodNatOperation _m, NatOperationsSequence _op, NatAnalyzedCode _page) {
        if (_op.getOpersNat().isEmpty()) {
            return createLeaf(_index, _indexChild, _m, _op, _page);
        }
        if (_op.getPrioNat() == NatElResolver.FCT_OPER_PRIO && _op.isCallDbArray()) {
            String fctName_ = _op.getFctName().trim();
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

    private static NatOperationNode createLeaf(int _index, int _indexChild, MethodNatOperation _m, NatOperationsSequence _op, NatAnalyzedCode _page) {
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
        AnaLocalVariable val_ = _page.getInfosVars().getVal(str_);
        if (val_ != null) {
            val_.setUsed(true);
            return new FinalVariableNatOperation(_index, _indexChild, _m, _op,val_.getClassName(), false);
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
        if (_scope == null) {
            return;
        }
        for (StandardField f: _scope.getFields()) {
            if (StringUtil.quickEq(f.getFieldName(), _scopeField)) {
                String type_ = f.getImportedClassName();
                NatFieldResult res_ = new NatFieldResult();
                String declaringBaseClass_ = getIdFromAllTypes(_scope.getFullName());
                ClassField classField_ = new ClassField(declaringBaseClass_, _scopeField);
                res_.getContent().setClassField(classField_);
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
            if(t == null) {
                continue;
            }
            SpecialNatClass root_ = t;
            for (SpecNatMethod e: root_.getMethods()) {
                _methods.add(getMethodInfo(e, root_.getFullName(), e.getImportedReturnType()));
            }
        }
    }

    private static CustList<SpecialNatClass> typeLists(String _fromClasses, NatAnalyzedCode _page) {
        CustList<SpecialNatClass> typeInfos_ = new CustList<SpecialNatClass>();
        String baseCurName_ = getIdFromAllTypes(_fromClasses);
        SpecialNatClass root_ = _page.getStds().getVal(baseCurName_);
        typeInfos_.add(root_);
        for (String m : root_.getAllSuperTypes()) {
            SpecialNatClass sup_ = _page.getStds().getVal(m);
            typeInfos_.add(sup_);
        }
        return typeInfos_;
    }

    private static NatMethodInfo getMethodInfo(SpecNatMethod _m, String _formattedClass, String _importedReturnType) {
        NatMethodInfo mloc_ = new NatMethodInfo();
        mloc_.types(_importedReturnType);
        mloc_.setStandardMethod(_m);
        mloc_.classMethodId(_formattedClass);
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
        String baseClassName_ = _m.getClassName();
        res_.setRealClass(baseClassName_);
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
