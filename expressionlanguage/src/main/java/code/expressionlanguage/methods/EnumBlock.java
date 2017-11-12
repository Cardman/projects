package code.expressionlanguage.methods;

import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.exceptions.CyclicCallingException;
import code.expressionlanguage.methods.util.BadAccessMethod;
import code.expressionlanguage.methods.util.ConstructorEdge;
import code.expressionlanguage.methods.util.ReservedMethod;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.graphs.Graph;

public final class EnumBlock extends RootBlock implements UniqueRootedBlock {

    private final StringList allSuperClasses = new StringList();

    private final StringList allSuperTypes = new StringList();

    private final StringList directInterfaces = new StringList();

    private final StringList allDirectInterfaces = new StringList();

    private final StringList allInterfaces = new StringList();

    private final StringList allSortedInterfaces = new StringList();

    private final StringList allNeededSortedInterfaces = new StringList();

    private final ObjectNotNullMap<ClassMethodId, Boolean> availableMethods = new ObjectNotNullMap<ClassMethodId, Boolean>();

    public EnumBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        int i_ = CustList.FIRST_INDEX;
        while (_el.hasAttribute(ATTRIBUTE_CLASS+i_)) {
            directInterfaces.add(_el.getAttribute(ATTRIBUTE_CLASS+i_));
            i_++;
        }
    }

    @Override
    public void setupBasicOverrides(ContextEl _context) {
        for (MethodBlock m: Classes.getMethodBlocks(this)) {
            if (m.getId().eq(new MethodId(false, OperationNode.METH_NAME, new EqList<ClassName>()))) {
                ReservedMethod r_ = new ReservedMethod();
                r_.setFileName(getFullName());
                r_.setRc(m.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                r_.setMethodeId(m.getId());
                _context.getClasses().getErrorsDet().add(r_);
            }
            if (m.getId().eq(new MethodId(false, OperationNode.METH_ORDINAL, new EqList<ClassName>()))) {
                ReservedMethod r_ = new ReservedMethod();
                r_.setFileName(getFullName());
                r_.setRc(m.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                r_.setMethodeId(m.getId());
                _context.getClasses().getErrorsDet().add(r_);
            }
            if (m.getId().eq(new MethodId(true, OperationNode.METH_VALUES, new EqList<ClassName>()))) {
                ReservedMethod r_ = new ReservedMethod();
                r_.setFileName(getFullName());
                r_.setRc(m.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                r_.setMethodeId(m.getId());
                _context.getClasses().getErrorsDet().add(r_);
            }
            if (m.getId().eq(new MethodId(true, OperationNode.METH_VALUEOF, new EqList<ClassName>(new ClassName(String.class.getName(), false))))) {
                ReservedMethod r_ = new ReservedMethod();
                r_.setFileName(getFullName());
                r_.setRc(m.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                r_.setMethodeId(m.getId());
                _context.getClasses().getErrorsDet().add(r_);
            }
        }
        useSuperTypesOverrides(_context);
        Classes classesRef_ = _context.getClasses();
        StringList classNames_ = getAllGenericSuperClasses(classesRef_);
        StringList classes_ = new StringList(getGenericString());
        classes_.addAllElts(classNames_);
        for (String s: getAllGenericInterfaces(classesRef_)) {
            String base_ = StringList.getAllTypes(s).first();
            RootBlock r_ = classesRef_.getClassBody(base_);
            for (MethodBlock m: Classes.getMethodBlocks(r_)) {
                if (m.isStaticMethod()) {
                    continue;
                }
                MethodId id_ = m.getFormattedId(s, classesRef_);
                for (String c: classes_) {
                    CustList<MethodBlock> mBases_ = classesRef_.getMethodBodiesByFormattedId(c, id_);
                    if (mBases_.isEmpty()) {
                        continue;
                    }
                    MethodBlock mBase_ = mBases_.first();
                    if (mBase_.isStaticMethod()) {
                        continue;
                    }
                    if (m.getAccess().ordinal() > mBase_.getAccess().ordinal()) {
                        BadAccessMethod err_;
                        err_ = new BadAccessMethod();
                        err_.setFileName(getFullName());
                        err_.setRc(m.getAttributes().getVal(ATTRIBUTE_ACCESS));
                        err_.setId(m.getId());
                        classesRef_.getErrorsDet().add(err_);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public StringList getDirectGenericSuperTypes() {
        StringList superTypes_ = new StringList();
        if (!StringList.quickEq(getSuperClass(), Object.class.getName())) {
            superTypes_.add(getSuperClass());
        }
        superTypes_.addAllElts(directInterfaces);
        return superTypes_;
    }

    @Override
    public StringList getAllInterfaces() {
        return allInterfaces;
    }
    @Override
    public StringList getDirectInterfaces() {
        StringList direct_ = new StringList();
        for (String s: directInterfaces) {
            int index_ = s.indexOf(LT);
            if (index_ > CustList.INDEX_NOT_FOUND_ELT) {
                direct_.add(s.substring(CustList.FIRST_INDEX, index_));
            } else {
                direct_.add(s);
            }
        }
        return direct_;
    }

    @Override
    public StringList getDirectGenericInterfaces() {
        return directInterfaces;
    }

    @Override
    public StringList getAllDirectInterfaces() {
        return allDirectInterfaces;
    }

    @Override
    public StringList getAllNeededSortedInterfaces() {
        return allNeededSortedInterfaces;
    }

    @Override
    public StringList getAllSortedInterfaces() {
        return allSortedInterfaces;
    }

    public ObjectNotNullMap<ClassMethodId, Boolean> getAvailableMethods() {
        return availableMethods;
    }

    @Override
    public void validateConstructors(ContextEl _cont) {
        String idType_ = getFullName();
        ClassMetaInfo curMeta_ = _cont.getClasses().getClassMetaInfo(idType_);
        ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> c_;
        c_ = curMeta_.getConstructors();
        for (EntryCust<ConstructorId, ConstructorMetaInfo> e: c_.entryList()) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBodiesById(idType_, e.getKey()).first();
            b_.setupInstancingStep(_cont);
        }
        EqList<ConstructorId> l_ = new EqList<ConstructorId>();
        for (EntryCust<ConstructorId, ConstructorMetaInfo> e: c_.entryList()) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBodiesById(idType_, e.getKey()).first();
            if (b_.getConstIdSameClass() != null) {
                l_.add(e.getKey());
            }
        }
        Graph<ConstructorEdge> graph_;
        graph_ = new Graph<ConstructorEdge>();
        for (ConstructorId f: l_) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBodiesById(idType_, f).first();
            ConstructorId co_ = b_.getConstIdSameClass();
            ConstructorEdge f_ = new ConstructorEdge(f);
            ConstructorEdge t_ = new ConstructorEdge(co_);
            graph_.addSegment(f_, t_);
        }
        EqList<ConstructorEdge> cycle_ = graph_.elementsCycle();
        if (!cycle_.isEmpty()) {
            //TODO souligner
            throw new CyclicCallingException(_cont.joinPages());
        }
    }

    @Override
    public StringList getAllSuperClasses() {
        return allSuperClasses;
    }

    @Override
    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        tr_.put(ATTRIBUTE_NAME, getFullDefinition());
        int i_ = 0;
        for (String t: directInterfaces) {
            tr_.put(ATTRIBUTE_CLASS+i_, t);
            i_++;
        }
        return tr_;
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public String getTagName() {
        return TAG_ENUM;
    }

    @Override
    public StringList getDirectSuperClasses() {
        StringList classes_ = new StringList();
        int index_ = getSuperClass().indexOf(LT);
        if (index_ > CustList.INDEX_NOT_FOUND_ELT) {
            classes_.add(getSuperClass().substring(CustList.FIRST_INDEX, index_));
        } else {
            classes_.add(getSuperClass());
        }
        return classes_;
    }

    @Override
    public StringList getDirectGenericSuperClasses() {
        StringList classes_ = new StringList();
        classes_.add(getSuperClass());
        return classes_;
    }

    @Override
    public String getGenericSuperClass() {
        return Object.class.getName();
    }

    @Override
    public String getSuperClass() {
        return Object.class.getName();
    }

    @Override
    public RootBlock belong() {
        return this;
    }

    @Override
    public boolean isFinalType() {
        return true;
    }

    @Override
    public boolean isAbstractType() {
        return true;
    }

    @Override
    public boolean mustImplement() {
        return true;
    }

    @Override
    public StringList getAllGenericSuperClasses(Classes _classes) {
        StringList allSuperTypes_ = getAllGenericSuperTypes(_classes);
        StringList allGenericSuperClasses_ = new StringList();
        for (String s: allSuperTypes_) {
            String base_ = StringList.getAllTypes(s).first();
            if (_classes.getClassBody(base_) instanceof ClassBlock) {
                allGenericSuperClasses_.add(s);
            }
        }
        return allGenericSuperClasses_;
    }

    @Override
    public StringList getAllGenericInterfaces(Classes _classes) {
        StringList allSuperTypes_ = getAllGenericSuperTypes(_classes);
        StringList allGenericInterfaces_ = new StringList();
        for (String s: allSuperTypes_) {
            String base_ = StringList.getAllTypes(s).first();
            if (_classes.getClassBody(base_) instanceof InterfaceBlock) {
                allGenericInterfaces_.add(s);
            }
        }
        return allGenericInterfaces_;
    }
}
