package code.expressionlanguage.methods;

import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.exceptions.CyclicCallingException;
import code.expressionlanguage.methods.util.ConstructorEdge;
import code.expressionlanguage.methods.util.ReservedMethod;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.FctConstraints;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.graphs.Graph;

public final class EnumBlock extends RootBlock implements UniqueRootedBlock {

    private final String name;

    private final String packageName;

    private final StringList allSuperClasses = new StringList();

    private final StringList allSuperTypes = new StringList();

    private final StringList directInterfaces = new StringList();

    private final StringList allDirectInterfaces = new StringList();

    private final StringList allInterfaces = new StringList();

    private final StringList allSortedInterfaces = new StringList();

    private final StringList allNeededSortedInterfaces = new StringList();

    private final ObjectNotNullMap<ClassMethodId, Boolean> availableMethods = new ObjectNotNullMap<ClassMethodId, Boolean>();

    private final ObjectMap<FctConstraints, String> defaultMethods = new ObjectMap<FctConstraints, String>();

    private final AccessEnum access;

    public EnumBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        name = _el.getAttribute(ATTRIBUTE_NAME);
        packageName = _el.getAttribute(ATTRIBUTE_PACKAGE);
        access = AccessEnum.valueOf(_el.getAttribute(ATTRIBUTE_ACCESS));
        int i_ = CustList.FIRST_INDEX;
        while (_el.hasAttribute(ATTRIBUTE_CLASS+i_)) {
            directInterfaces.add(_el.getAttribute(ATTRIBUTE_CLASS+i_));
            i_++;
        }
    }

    @Override
    public void setupBasicOverrides(ContextEl _context) {
        CustList<Block> ch_ = Classes.getDirectChildren(this);
        for (Block c: ch_) {
            if (!(c instanceof MethodBlock)) {
                continue;
            }
            MethodBlock m_ = (MethodBlock) c;
            if (m_.getId().eq(new MethodId(OperationNode.METH_NAME, new EqList<ClassName>()))) {
                ReservedMethod r_ = new ReservedMethod();
                r_.setFileName(getFullName());
                r_.setRc(m_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                r_.setMethodeId(m_.getId());
                _context.getClasses().getErrorsDet().add(r_);
            }
            if (m_.getId().eq(new MethodId(OperationNode.METH_ORDINAL, new EqList<ClassName>()))) {
                ReservedMethod r_ = new ReservedMethod();
                r_.setFileName(getFullName());
                r_.setRc(m_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                r_.setMethodeId(m_.getId());
                _context.getClasses().getErrorsDet().add(r_);
            }
            if (m_.getId().eq(new MethodId(OperationNode.METH_VALUES, new EqList<ClassName>()))) {
                ReservedMethod r_ = new ReservedMethod();
                r_.setFileName(getFullName());
                r_.setRc(m_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                r_.setMethodeId(m_.getId());
                _context.getClasses().getErrorsDet().add(r_);
            }
            if (m_.getId().eq(new MethodId(OperationNode.METH_VALUEOF, new EqList<ClassName>(new ClassName(String.class.getName(), false))))) {
                ReservedMethod r_ = new ReservedMethod();
                r_.setFileName(getFullName());
                r_.setRc(m_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                r_.setMethodeId(m_.getId());
                _context.getClasses().getErrorsDet().add(r_);
            }
        }
        StringList all_ = getAllSuperClasses();
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock mCl_ = (MethodBlock) b;
                if (mCl_.isStaticMethod()) {
                    continue;
                }
                for (String s: all_) {
                    if (StringList.quickEq(s, Object.class.getName())) {
                        continue;
                    }
                    FctConstraints mDer_ = ((MethodBlock) b).getConstraints();
                    MethodBlock m_ = _context.getClasses().getMethodBody(s, mDer_);
                    if (m_ == null) {
                        continue;
                    }
                    if (m_.isStaticMethod()) {
                        continue;
                    }
                    if (_context.getClasses().canAccessMethod(getFullName(), s, mDer_)) {
                        ((MethodBlock) b).getOverridenClasses().add(s);
                        break;
                    }
                }
            }
        }
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock mDer_ = (MethodBlock) b;
                mDer_.getAllOverridenClasses().addAllElts(mDer_.getOverridenClasses());
                for (String s: mDer_.getOverridenClasses()) {
                    MethodBlock mBase_ = _context.getClasses().getMethodBody(s, mDer_.getConstraints());
                    mDer_.getAllOverridenClasses().addAllElts(mBase_.getAllOverridenClasses());
                }
            }
        }
    }

    @Override
    public StringList getDirectSuperTypes() {
        StringList superTypes_ = new StringList();
        superTypes_.add(getSuperClass());
        superTypes_.addAllElts(directInterfaces);
        return superTypes_;
    }

    @Override
    public AccessEnum getAccess() {
        return access;
    }
    @Override
    public StringList getAllInterfaces() {
        return allInterfaces;
    }
    @Override
    public StringList getDirectInterfaces() {
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
    public ObjectMap<FctConstraints, String> getDefaultMethods() {
        return defaultMethods;
    }
    @Override
    public void validateConstructors(ContextEl _cont) {
        ClassMetaInfo curMeta_ = _cont.getClasses().getClassMetaInfo(getFullName());
        ObjectNotNullMap<FctConstraints, ConstructorMetaInfo> c_;
        c_ = curMeta_.getConstructors();
        for (EntryCust<FctConstraints, ConstructorMetaInfo> e: c_.entryList()) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBody(getFullName(), e.getKey());
            b_.setupInstancingStep(_cont);
        }
        EqList<FctConstraints> l_ = new EqList<FctConstraints>();
        for (EntryCust<FctConstraints, ConstructorMetaInfo> e: c_.entryList()) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBody(getFullName(), e.getKey());
            if (b_.getConstIdSameClass() != null) {
                l_.add(e.getKey());
            }
        }
        Graph<ConstructorEdge> graph_;
        graph_ = new Graph<ConstructorEdge>();
        for (FctConstraints f: l_) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBody(getFullName(), f);
            FctConstraints co_ = b_.getConstIdSameClass();
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
        int i_ = 0;
        for (String t: directInterfaces) {
            tr_.put(ATTRIBUTE_CLASS+i_, t);
            i_++;
        }
        return tr_;
    }

    @Override
    public String getFullName() {
        return packageName+DOT+name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPackageName() {
        return packageName;
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
        classes_.add(getSuperClass());
        return classes_;
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
}
