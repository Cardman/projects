package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetAccessInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.util.BadAccessMethod;
import code.expressionlanguage.methods.util.BadInheritedClass;
import code.expressionlanguage.methods.util.BadReturnTypeInherit;
import code.expressionlanguage.methods.util.DuplicateParamMethod;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.sml.Element;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class ClassBlock extends RootBlock implements UniqueRootedBlock {

    private final StringList allSuperClasses = new StringList();

    private final StringList allSuperTypes = new StringList();

    private final StringList allInterfaces = new StringList();

    private final boolean finalType;
    private final boolean abstractType;

    public ClassBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        String superClass_ = _el.getAttribute(ATTRIBUTE_SUPER_CLASS);
        if (!superClass_.trim().isEmpty()) {
            getDirectSuperTypes().add(superClass_);
        }
        int i_ = CustList.FIRST_INDEX;
        while (_el.hasAttribute(StringList.concatNbs(ATTRIBUTE_CLASS,i_))) {
            getDirectSuperTypes().add(_el.getAttribute(StringList.concatNbs(ATTRIBUTE_CLASS,i_)));
            i_++;
        }
        String modifier_ = _el.getAttribute(ATTRIBUTE_MODIFIER);
        finalType = StringList.quickEq(modifier_, VALUE_FINAL);
        abstractType = StringList.quickEq(modifier_, VALUE_ABSTRACT);
    }

    public ClassBlock(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, int _idRowCol, int _categoryOffset ,String _name, String _packageName, OffsetAccessInfo _access,
            String _templateDef, NatTreeMap<Integer, String> _directSuperTypes,
            boolean _finalType,
            boolean _abstractType, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _idRowCol, _categoryOffset, _name, _packageName, _access, _templateDef, _directSuperTypes, _offset);
        finalType = _finalType;
        abstractType = _abstractType;
    }

    @Override
    public void setupBasicOverrides(ContextEl _context) {
        Classes classesRef_ = _context.getClasses();
        StringList classNames_ = getAllGenericSuperClasses(_context);
        String fullName_ = getFullName();
        for (String b: getCustomDirectSuperClasses(_context)) {
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
            inherit_.setRc(getRowCol(0, getIdRowCol()));
            classesRef_.getErrorsDet().add(inherit_);
        }
        for (Block b: Classes.getDirectChildren(this)) {
            if (!(b instanceof ConstructorBlock)) {
                continue;
            }
            ConstructorBlock c_ = (ConstructorBlock) b;
            for (String s: classNames_) {
                if (TypeUtil.getConstructorBodiesByFormattedId(_context, s, c_.getId(_context)).size() > 1) {
                    DuplicateParamMethod duplicate_ = new DuplicateParamMethod();
                    duplicate_.setFileName(getFullName());
                    duplicate_.setRc(c_.getRowCol(0, c_.getAccessOffset()));
                    duplicate_.setCommonSignature(c_.getId(_context).getSignature());
                    duplicate_.setOtherType(s);
                    classesRef_.getErrorsDet().add(duplicate_);
                }
            }
        }
        useSuperTypesOverrides(_context);
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMap().values()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        String gene_ = getGenericString();
        StringList classes_ = new StringList(gene_);
        classes_.addAllElts(classNames_);
        LgNames stds_ = _context.getStandards();
        String void_ = stds_.getAliasVoid();
        for (String s: getAllGenericInterfaces(_context)) {
            String base_ = Templates.getIdFromAllTypes(s);
            RootBlock r_ = classesRef_.getClassBody(base_);
            for (MethodBlock m: Classes.getMethodBlocks(r_)) {
                if (m.isStaticMethod()) {
                    continue;
                }
                String formattedSuper_ = Templates.getFullTypeByBases(gene_, s, _context);
                MethodId id_ = m.getId(_context).format(formattedSuper_, _context);
                for (String c: classes_) {
                    CustList<MethodBlock> mBases_ = Classes.getMethodBodiesById(_context, c, id_);
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
                        err_.setRc(m.getRowCol(0, mBase_.getAccessOffset()));
                        err_.setId(m.getId(_context));
                        classesRef_.getErrorsDet().add(err_);
                    }
                    String retBase_ = m.getReturnType(_context);
                    String retDerive_ = mBase_.getReturnType(_context);
                    String formattedRetDer_ = Templates.format(c, retDerive_, _context);
                    String formattedRetBase_ = Templates.format(s, retBase_, _context);
                    Mapping mapping_ = new Mapping();
                    mapping_.getMapping().putAllMap(vars_);
                    mapping_.setArg(formattedRetDer_);
                    mapping_.setParam(formattedRetBase_);
                    if (StringList.quickEq(retBase_, void_)) {
                        if (!StringList.quickEq(retDerive_, void_)) {
                            BadReturnTypeInherit err_;
                            err_ = new BadReturnTypeInherit();
                            err_.setFileName(getFullName());
                            err_.setRc(mBase_.getRowCol(0, mBase_.getReturnTypeOffset()));
                            err_.setReturnType(retDerive_);
                            err_.setMethod(mBase_.getId(_context));
                            err_.setParentClass(c);
                            classesRef_.getErrorsDet().add(err_);
                        }
                    } else if (!Templates.isCorrect(mapping_, _context)) {
                        BadReturnTypeInherit err_;
                        err_ = new BadReturnTypeInherit();
                        err_.setFileName(getFullName());
                        err_.setRc(mBase_.getRowCol(0, mBase_.getReturnTypeOffset()));
                        err_.setReturnType(retDerive_);
                        err_.setMethod(mBase_.getId(_context));
                        err_.setParentClass(c);
                        classesRef_.getErrorsDet().add(err_);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public StringList getDirectGenericSuperTypesBuild(Analyzable _classes) {
        return new StringList(getDirectSuperTypes());
    }

    @Override
    public StringList getDirectGenericSuperTypes(Analyzable _classes) {
        StringList interfaces_ = new StringList();
        for (String s: getDirectSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
            if (isAccessibleType(base_, _classes)) {
                interfaces_.add(s);
            }
        }
        return interfaces_;
    }

    @Override
    public String getSuperClass(Analyzable _context) {
        for (String s: getDirectSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
            RootBlock r_ = _context.getClasses().getClassBody(base_);
            if (!(r_ instanceof ClassBlock)) {
                continue;
            }
            if (isAccessibleType(base_, _context)) {
                return base_;
            }
        }
        return _context.getStandards().getAliasObject();
    }

    @Override
    public String getGenericSuperClass(Analyzable _classes) {
        for (String s: getDirectSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
            RootBlock r_ = _classes.getClasses().getClassBody(base_);
            if (!(r_ instanceof ClassBlock)) {
                continue;
            }
            if (isAccessibleType(base_, _classes)) {
                return s;
            }
        }
        return _classes.getStandards().getAliasObject();
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
    public StringList getDirectGenericInterfaces(Analyzable _classes) {
        StringList interfaces_ = new StringList();
        for (String s: getDirectSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
            RootBlock r_ = _classes.getClasses().getClassBody(base_);
            if (!(r_ instanceof InterfaceBlock)) {
                continue;
            }
            if (isAccessibleType(base_, _classes)) {
                interfaces_.add(s);
            }
        }
        return interfaces_;
    }

    @Override
    public StringList getDirectInterfaces(Analyzable _classes) {
        StringList interfaces_ = new StringList();
        for (String s: getDirectSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
            RootBlock r_ = _classes.getClasses().getClassBody(base_);
            if (!(r_ instanceof InterfaceBlock)) {
                continue;
            }
            if (isAccessibleType(base_, _classes)) {
                interfaces_.add(base_);
            }
        }
        return interfaces_;
    }

    public AccessEnum getMaximumAccessConstructors(ContextEl _cont) {
        CustList<ConstructorBlock> ctors_ = new CustList<ConstructorBlock>();
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof ConstructorBlock) {
                ctors_.add((ConstructorBlock) b);
            }
        }
        if (ctors_.isEmpty()) {
            return getAccess();
        }
        AccessEnum a_ = AccessEnum.PRIVATE;
        for (ConstructorBlock c: ctors_) {
            if (c.getAccess().ordinal() < a_.ordinal()) {
                a_ = c.getAccess();
            }
        }
        return a_;
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
    public StringList getDirectGenericSuperClasses(Analyzable _classes) {
        StringList classes_ = new StringList();
        classes_.add(getGenericSuperClass(_classes));
        return classes_;
    }

    @Override
    public StringList getDirectSuperClasses(Analyzable _context) {
        StringList classes_ = new StringList();
        String superClass_ = getGenericSuperClass(_context);
        int index_ = superClass_.indexOf(LT);
        if (index_ > CustList.INDEX_NOT_FOUND_ELT) {
            classes_.add(superClass_.substring(CustList.FIRST_INDEX, index_));
        } else {
            classes_.add(superClass_);
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
    public StringList getAllGenericSuperClasses(Analyzable _classes) {
        StringList allSuperTypes_ = getAllGenericSuperTypes(_classes);
        StringList allGenericSuperClasses_ = new StringList();
        Classes classes_ = _classes.getClasses();
        for (String s: allSuperTypes_) {
            String base_ = Templates.getIdFromAllTypes(s);
            if (classes_.getClassBody(base_) instanceof ClassBlock) {
                allGenericSuperClasses_.add(s);
            }
        }
        return allGenericSuperClasses_;
    }

    @Override
    public StringList getAllGenericInterfaces(Analyzable _classes) {
        StringList allSuperTypes_ = getAllGenericSuperTypes(_classes);
        Classes classes_ = _classes.getClasses();
        StringList allGenericInterfaces_ = new StringList();
        for (String s: allSuperTypes_) {
            String base_ = Templates.getIdFromAllTypes(s);
            if (classes_.getClassBody(base_) instanceof InterfaceBlock) {
                allGenericInterfaces_.add(s);
            }
        }
        return allGenericInterfaces_;
    }

    @Override
    public StringList getAllSuperClasses(Analyzable _classes) {
        StringList superClasses_ = new StringList();
        String current_ = getFullName();
        String objectAlias_ = _classes.getStandards().getAliasObject();
        while (!StringList.quickEq(current_, objectAlias_)) {
            UniqueRootedBlock r_ = (UniqueRootedBlock) _classes.getClasses().getClassBody(current_);
            String superClass_ = r_.getSuperClass(_classes);
            superClasses_.add(superClass_);
            current_ = superClass_;
        }
        return superClasses_;
    }
}
