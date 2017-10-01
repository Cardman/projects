package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.exceptions.CyclicCallingException;
import code.expressionlanguage.methods.exceptions.UndefinedSuperConstructorException;
import code.expressionlanguage.methods.util.BadAccessMethod;
import code.expressionlanguage.methods.util.BadInheritedClass;
import code.expressionlanguage.methods.util.ConstructorEdge;
import code.expressionlanguage.methods.util.DuplicateParamMethod;
import code.expressionlanguage.methods.util.FinalMethod;
import code.expressionlanguage.opers.util.ClassMetaInfo;
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
        Classes classesRef_ = _context.getClasses();
        StringList all_ = getAllGenericSuperTypes(classesRef_);
        StringList classNames_ = new StringList();
        for (String s: all_) {
            String base_ = StringList.getAllTypes(s).first();
            if (classesRef_.getClassBody(base_) instanceof ClassBlock) {
                classNames_.add(s);
            }
        }
        String fullName_ = getFullName();
        StringList direct_ = getDirectSuperClasses();
        for (String b: direct_) {
            if (StringList.quickEq(b, Object.class.getName())) {
                continue;
            }
            ClassBlock bBl_ = (ClassBlock) classesRef_.getClassBody(b);
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
            classesRef_.getErrorsDet().add(inherit_);
        }
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock mCl_ = (MethodBlock) b;
                if (mCl_.isStaticMethod()) {
                    continue;
                }
                addClass(getAllOverridingMethods(), mCl_.getId(), getFullDefinition());
            }
        }
        for (Block b: Classes.getDirectChildren(this)) {
            if (!(b instanceof MethodBlock)) {
                continue;
            }
            MethodBlock mDer_ = (MethodBlock) b;
            if (mDer_.isStaticMethod()) {
                continue;
            }
            for (String s: getAllGenericSuperTypes(classesRef_)) {
                CustList<MethodBlock> mBases_ = classesRef_.getMethodBodiesByFormattedId(s, mDer_.getId());
                if (mBases_.isEmpty()) {
                    continue;
                }
                if (mBases_.size() > 1) {
                    DuplicateParamMethod duplicate_ = new DuplicateParamMethod();
                    duplicate_.setFileName(getFullName());
                    duplicate_.setRc(new RowCol());
                    duplicate_.setCommonSignature(mDer_.getId().getSignature());
                    duplicate_.setOtherType(s);
                    classesRef_.getErrorsDet().add(duplicate_);
                }
                MethodBlock mBase_ = mBases_.first();
                if (mBase_.isStaticMethod()) {
                    continue;
                }
                if (!classesRef_.canAccess(getFullName(), mBase_)) {
                    continue;
                }
                if (mBase_.isFinalMethod()) {
                    FinalMethod err_;
                    err_ = new FinalMethod();
                    err_.setFileName(getFullName());
                    err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_NAME));
                    err_.setClassName(s);
                    err_.setId(mDer_.getId());
                    classesRef_.getErrorsDet().add(err_);
                }
                addClass(getAllOverridingMethods(), mDer_.getId(), s);
            }
        }
        StringList classes_ = new StringList(getFullDefinition());
        classes_.addAllElts(classNames_);
        for (String s: classNames_) {
            String base_ = StringList.getAllTypes(s).first();
            RootBlock r_ = classesRef_.getClassBody(base_);
            for (Block b: Classes.getDirectChildren(r_)) {
                if (!(b instanceof MethodBlock)) {
                    continue;
                }
                MethodBlock mDer_ = (MethodBlock) b;
                if (mDer_.isStaticMethod()) {
                    continue;
                }
                MethodId id_ = mDer_.getFormattedId(s, classesRef_);
                CustList<MethodBlock> mBases_ = classesRef_.getMethodBodiesByFormattedId(getFullDefinition(), id_);
                if (!mBases_.isEmpty()) {
                    continue;
                }
                MethodId idReal_ = mDer_.getFormattedId(s, classesRef_);
                addClass(getAllOverridingMethods(), idReal_, s);
            }
        }
        for (String s: getAllGenericInterfaces(classesRef_)) {
            String base_ = StringList.getAllTypes(s).first();
            RootBlock r_ = classesRef_.getClassBody(base_);
            for (Block b: Classes.getDirectChildren(r_)) {
                if (!(b instanceof MethodBlock)) {
                    continue;
                }
                MethodBlock mDer_ = (MethodBlock) b;
                if (mDer_.isStaticMethod()) {
                    continue;
                }
                MethodId id_ = mDer_.getFormattedId(s, classesRef_);
                for (String c: classes_) {
                    CustList<MethodBlock> mBases_ = classesRef_.getMethodBodiesByFormattedId(c, id_);
                    if (mBases_.isEmpty()) {
                        continue;
                    }
                    MethodBlock mBase_ = mBases_.first();
                    if (mBase_.isStaticMethod()) {
                        continue;
                    }
                    if (mDer_.getAccess().ordinal() > mBase_.getAccess().ordinal()) {
                        BadAccessMethod err_;
                        err_ = new BadAccessMethod();
                        err_.setFileName(getFullName());
                        err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_ACCESS));
                        err_.setId(mDer_.getId());
                        classesRef_.getErrorsDet().add(err_);
                    }
                    addClass(getAllOverridingMethods(), id_, c);
                    addClass(getAllOverridingMethods(), id_, s);
                    break;
                }
            }
        }
    }

    @Override
    public StringList getDirectGenericSuperTypes() {
        StringList superTypes_ = new StringList();
        superTypes_.add(superClass);
        superTypes_.addAllElts(directInterfaces);
        return superTypes_;
    }

    @Override
    public String getSuperClass() {
        int index_ = superClass.indexOf(LT);
        if (index_ > CustList.INDEX_NOT_FOUND_ELT) {
            return superClass.substring(CustList.FIRST_INDEX, index_);
        }
        return superClass;
    }

    @Override
    public String getGenericSuperClass() {
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
    public StringList getDirectGenericInterfaces() {
        return directInterfaces;
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
        tr_.put(ATTRIBUTE_NAME, getFullDefinition());
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
    public StringList getDirectGenericSuperClasses() {
        StringList classes_ = new StringList();
        classes_.add(superClass);
        return classes_;
    }

    @Override
    public StringList getDirectSuperClasses() {
        StringList classes_ = new StringList();
        int index_ = superClass.indexOf(LT);
        if (index_ > CustList.INDEX_NOT_FOUND_ELT) {
            classes_.add(superClass.substring(CustList.FIRST_INDEX, index_));
        } else {
            classes_.add(superClass);
        }
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
