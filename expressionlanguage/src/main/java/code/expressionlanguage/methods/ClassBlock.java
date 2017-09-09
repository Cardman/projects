package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.exceptions.CyclicCallingException;
import code.expressionlanguage.methods.exceptions.UndefinedSuperConstructorException;
import code.expressionlanguage.methods.util.BadInheritedClass;
import code.expressionlanguage.methods.util.ConstructorEdge;
import code.expressionlanguage.methods.util.FinalMethod;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.FctConstraints;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.graphs.Graph;
import code.xml.RowCol;

public final class ClassBlock extends RootBlock implements UniqueRootedBlock {

    private final String superClass;
    private final StringList allSuperClasses = new StringList();

    private final StringList allSuperTypes = new StringList();

    private final StringList directInterfaces = new StringList();

    private final StringList allDirectInterfaces = new StringList();

    private final StringList allInterfaces = new StringList();

    private final StringList allSortedInterfaces = new StringList();

    private final StringList allNeededSortedInterfaces = new StringList();

    private final ObjectMap<FctConstraints, String> defaultMethods = new ObjectMap<FctConstraints, String>();

    private final boolean finalType;
    private final boolean abstractType;

    public ClassBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
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
        String modifier_ = _el.getAttribute(ATTRIBUTE_MODIFIER);
        finalType = StringList.quickEq(modifier_, VALUE_FINAL);
        abstractType = StringList.quickEq(modifier_, VALUE_ABSTRACT);
    }

    @Override
    public void setupBasicOverrides(ContextEl _context) {
        StringList all_ = getAllSuperClasses();
        String fullName_ = getFullName();
        StringList direct_ = getDirectSuperClasses();
        for (String b: direct_) {
            if (StringList.quickEq(b, Object.class.getName())) {
                continue;
            }
            ClassBlock bBl_ = (ClassBlock) _context.getClasses().getClassBody(b);
            AccessEnum acc_ = bBl_.getMaximumAccessConstructors(_context);
            if (acc_.ordinal() <= AccessEnum.PROTECTED.ordinal()) {
                continue;
            }
            if (acc_ == AccessEnum.PACKAGE) {
                if (StringList.quickEq(getPackageName(), bBl_.getPackageName())) {
                    continue;
                }
            }
            BadInheritedClass inherit_;
            inherit_ = new BadInheritedClass();
            inherit_.setClassName(fullName_);
            inherit_.setFileName(fullName_);
            inherit_.setRc(new RowCol());
            _context.getClasses().getErrorsDet().add(inherit_);
        }
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock mCl_ = (MethodBlock) b;
                if (mCl_.isStaticMethod()) {
                    continue;
                }
                FctConstraints mDer_ = mCl_.getConstraints(_context.getClasses());
                for (String s: all_) {
                    if (StringList.quickEq(s, Object.class.getName())) {
                        continue;
                    }
                    MethodBlock m_ = _context.getClasses().getMethodBody(s, mDer_);
                    if (m_ == null) {
                        continue;
                    }
                    if (m_.isStaticMethod()) {
                        continue;
                    }
                    if (!_context.getClasses().canAccessMethod(getFullName(), s, mDer_)) {
                        continue;
                    }
                    if (m_.isFinalMethod()) {
                        FinalMethod err_;
                        err_ = new FinalMethod();
                        err_.setFileName(getFullName());
                        err_.setRc(mCl_.getAttributes().getVal(ATTRIBUTE_NAME));
                        err_.setClassName(s);
                        err_.setId(mCl_.getId());
                        _context.getClasses().getErrorsDet().add(err_);
                    }
                    ((MethodBlock) b).getOverridenClasses().add(s);
                    break;
                }
            }
        }
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock mDer_ = (MethodBlock) b;
                if (mDer_.isStaticMethod()) {
                    continue;
                }
                mDer_.getAllOverridenClasses().addAllElts(mDer_.getOverridenClasses());
                for (String s: mDer_.getOverridenClasses()) {
                    MethodBlock mBase_ = _context.getClasses().getMethodBody(s, mDer_.getConstraints(_context.getClasses()));
                    if (mBase_.isStaticMethod()) {
                        continue;
                    }
                    mDer_.getAllOverridenClasses().addAllElts(mBase_.getAllOverridenClasses());
                }
            }
        }
    }

    @Override
    public StringList getDirectSuperTypes() {
        StringList superTypes_ = new StringList();
        superTypes_.add(superClass);
        superTypes_.addAllElts(directInterfaces);
        return superTypes_;
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
    public StringList getAllSuperTypes() {
        return allSuperTypes;
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

    @Override
    public ObjectMap<FctConstraints, String> getDefaultMethods() {
        return defaultMethods;
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
    public RootBlock belong() {
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

    @Override
    public boolean mustImplement() {
        return !isAbstractType();
    }
}
