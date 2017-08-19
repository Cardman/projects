package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.exceptions.CyclicCallingException;
import code.expressionlanguage.methods.exceptions.UndefinedSuperConstructorException;
import code.expressionlanguage.methods.util.ConstructorEdge;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.FctConstraints;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.graphs.Graph;

public final class ClassBlock extends BracedBlock implements UniqueRootedBlock {

    private final String name;

    private final String packageName;

    private final String superClass;

    private final StringList allSuperClasses = new StringList();

    private final StringList directInterfaces = new StringList();

    private final StringList allInterfaces = new StringList();

    private final AccessEnum access;

    private final boolean finalType;
    
    private final boolean abstractType;

    public ClassBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        name = _el.getAttribute(ATTRIBUTE_NAME);
        packageName = _el.getAttribute(ATTRIBUTE_PACKAGE);
        String superClass_ = _el.getAttribute(ATTRIBUTE_SUPER_CLASS);
        if (superClass_.trim().isEmpty()) {
            superClass_ = Object.class.getName();
        }
        int i_ = CustList.FIRST_INDEX;
        while (_el.hasAttribute(ATTRIBUTE_CLASS+i_)) {
            directInterfaces.add(_el.getAttribute(ATTRIBUTE_CLASS+i_));
            i_++;
        }
        superClass = superClass_;
        access = AccessEnum.valueOf(_el.getAttribute(ATTRIBUTE_ACCESS));
        String modifier_ = _el.getAttribute(ATTRIBUTE_MODIFIER);
        finalType = StringList.quickEq(modifier_, VALUE_FINAL);
        abstractType = StringList.quickEq(modifier_, VALUE_ABSTRACT);
    }

    @Override
    public AccessEnum getAccess() {
        return access;
    }

    @Override
    public String getSuperClass() {
        return superClass;
    }

    @Override
    public StringList getAllSuperClasses() {
        return allSuperClasses;
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
    public void validateConstructors(ContextEl _cont) {
        boolean opt_ = optionalCallConstr(_cont);
        ClassMetaInfo curMeta_ = _cont.getClasses().getClassMetaInfo(getFullName());
        ObjectNotNullMap<FctConstraints, ConstructorMetaInfo> c_;
        c_ = curMeta_.getConstructors();
        for (EntryCust<FctConstraints, ConstructorMetaInfo> e: c_.entryList()) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBody(getFullName(), e.getKey());
            b_.setupInstancingStep(_cont);
        }
        for (EntryCust<FctConstraints, ConstructorMetaInfo> e: c_.entryList()) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBody(getFullName(), e.getKey());
            if (b_.implicitConstr() && !opt_) {
                throw new UndefinedSuperConstructorException(_cont.joinPages());
            }
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

    public AccessEnum getMaximumAccessConstructors(ContextEl _cont) {
        ClassMetaInfo curMeta_ = _cont.getClasses().getClassMetaInfo(getFullName());
        ObjectNotNullMap<FctConstraints, ConstructorMetaInfo> c_;
        c_ = curMeta_.getConstructors();
        if (c_.isEmpty()) {
            return AccessEnum.PUBLIC;
        }
        AccessEnum a_ = AccessEnum.PRIVATE;
        for (EntryCust<FctConstraints, ConstructorMetaInfo> e: c_.entryList()) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBody(getFullName(), e.getKey());
            if (b_.getAccess().ordinal() < a_.ordinal()) {
                a_ = b_.getAccess();
            }
        }
        return a_;
    }

    private boolean optionalCallConstr(ContextEl _cont) {
        ClassMetaInfo clMeta_ = _cont.getClasses().getClassMetaInfo(superClass);
        if (clMeta_ == null) {
            return true;
        }
        ObjectNotNullMap<FctConstraints, ConstructorMetaInfo> m_;
        m_ = clMeta_.getConstructors();
        if (m_.isEmpty()) {
            return true;
        }
        for (EntryCust<FctConstraints, ConstructorMetaInfo> e: m_.entryList()) {
            if (!_cont.getClasses().canAccessConstructor(getFullName(), superClass, e.getKey())) {
                continue;
            }
            if (e.getKey().getConstraints().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        tr_.put(ATTRIBUTE_SUPER_CLASS, superClass);
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
        return TAG_CLASS;
    }

    @Override
    public StringList getDirectSuperClasses() {
        StringList classes_ = new StringList();
        classes_.add(superClass);
        return classes_;
    }

    @Override
    public RootedBlock belong() {
        return this;
    }

    @Override
    public boolean isFinalType() {
        return finalType;
    }

    @Override
    public boolean isAbstractType() {
        return abstractType;
    }
}
