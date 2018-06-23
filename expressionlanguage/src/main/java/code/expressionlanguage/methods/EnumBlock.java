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

public final class EnumBlock extends RootBlock implements UniqueRootedBlock {

    private final StringList allSuperClasses = new StringList();

    private final StringList allSuperTypes = new StringList();

    private final StringList allInterfaces = new StringList();

    private String importedDirectSuperClass = "";
    private StringList importedDirectSuperInterfaces = new StringList();

    public EnumBlock(Element _el, ContextEl _importingPage, int _indexChild,
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
        getDirectSuperTypes().add(StringList.concat(_importingPage.getStandards().getAliasEnumParam(),LT,getFullName(),GT));
    }

    public EnumBlock(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, int _idRowCol, int _categoryOffset ,String _name, String _packageName, OffsetAccessInfo _access,
            String _templateDef, NatTreeMap<Integer, String> _directSuperTypes, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _idRowCol, _categoryOffset, _name, _packageName, _access, _templateDef, _directSuperTypes, _offset);
        String type_ = StringList.concat(_importingPage.getStandards().getAliasEnumParam(),LT,getFullName(),GT);
        getDirectSuperTypes().add(type_);
        getExplicitDirectSuperTypes().put(-1, false);
        getRowColDirectSuperTypes().put(-1, type_);
    }

    @Override
    public void setupBasicOverrides(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        Classes classesRef_ = _context.getClasses();
        StringList allSuperTypes_ = getAllGenericSuperTypes(_context);
        StringList allGenericSuperClasses_ = new StringList();
        for (String s: allSuperTypes_) {
            String base_ = Templates.getIdFromAllTypes(s);
            if (classesRef_.getClassBody(base_) instanceof ClassBlock) {
                allGenericSuperClasses_.add(s);
            }
        }
        StringList classNames_ = allGenericSuperClasses_;
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
            classesRef_.addError(inherit_);
        }
        for (Block b: Classes.getDirectChildren(this)) {
            if (!(b instanceof ConstructorBlock)) {
                continue;
            }
            ConstructorBlock c_ = (ConstructorBlock) b;
            for (String s: classNames_) {
                if (TypeUtil.getConstructorBodiesByFormattedId(_context, s, c_.getId()).size() > 1) {
                    DuplicateParamMethod duplicate_ = new DuplicateParamMethod();
                    duplicate_.setFileName(getFullName());
                    duplicate_.setRc(c_.getRowCol(0, c_.getAccessOffset()));
                    duplicate_.setCommonSignature(c_.getId().getSignature());
                    duplicate_.setOtherType(s);
                    classesRef_.addError(duplicate_);
                }
            }
        }
        useSuperTypesOverrides(_context);
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        String gene_ = getGenericString();
        StringList classes_ = new StringList(gene_);
        classes_.addAllElts(classNames_);
        String void_ = stds_.getAliasVoid();
        for (String s: getAllGenericInterfaces(_context)) {
            String base_ = Templates.getIdFromAllTypes(s);
            RootBlock r_ = classesRef_.getClassBody(base_);
            for (MethodBlock m: Classes.getMethodBlocks(r_)) {
                if (m.isStaticMethod()) {
                    continue;
                }
                String formattedSuper_ = Templates.getFullTypeByBases(gene_, s, _context);
                MethodId id_ = m.getId().format(formattedSuper_, _context);
                for (String c: classes_) {
                    CustList<MethodBlock> mBases_ = Classes.getMethodBodiesById(_context,c, id_);
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
                        err_.setRc(m.getRowCol(0, m.getAccessOffset()));
                        err_.setId(m.getId());
                        classesRef_.addError(err_);
                    }
                    String retBase_ = m.getImportedReturnType();
                    String retDerive_ = mBase_.getImportedReturnType();
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
                            err_.setMethod(mBase_.getId());
                            err_.setParentClass(c);
                            classesRef_.addError(err_);
                        }
                    } else if (!Templates.isCorrect(mapping_, _context)) {
                        BadReturnTypeInherit err_;
                        err_ = new BadReturnTypeInherit();
                        err_.setFileName(getFullName());
                        err_.setRc(mBase_.getRowCol(0, mBase_.getReturnTypeOffset()));
                        err_.setReturnType(retDerive_);
                        err_.setMethod(mBase_.getId());
                        err_.setParentClass(c);
                        classesRef_.addError(err_);
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
            base_ = _classes.resolveBaseTypeBuildInherits(base_, this);
            if (isAccessibleType(base_, _classes)) {
                interfaces_.add(_classes.resolveDynamicTypeBuildInherits(s, this));
            }
        }
        return interfaces_;
    }

    @Override
    public StringList getAllInterfaces() {
        return allInterfaces;
    }
    @Override
    public StringList getDirectInterfaces(Analyzable _classes) {
        StringList interfaces_ = new StringList();
        for (String s: getDirectSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
            base_ = _classes.resolveBaseTypeBuildInherits(base_, this);
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

    @Override
    public StringList getAllSuperClasses() {
        return allSuperClasses;
    }

    @Override
    public StringList getAllSuperTypes() {
        return allSuperTypes;
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
    public StringList getDirectSuperClasses(Analyzable _classes) {
        StringList classes_ = new StringList();
        for (String s: getDirectSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
            base_=_classes.resolveBaseTypeBuildInherits(base_,this);
            RootBlock r_ = _classes.getClasses().getClassBody(base_);
            if (!(r_ instanceof ClassBlock)) {
                continue;
            }
            if (isAccessibleType(base_, _classes)) {
                classes_.add(base_);
            }
        }
        if (classes_.isEmpty()) {
            classes_.add(_classes.getStandards().getAliasObject());
        }
        return classes_;
    }

    @Override
    public String getSuperClass(Analyzable _classes) {
        for (String s: getDirectSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
            base_ = _classes.resolveBaseTypeBuildInherits(base_, this);
            RootBlock r_ = _classes.getClasses().getClassBody(base_);
            if (!(r_ instanceof ClassBlock)) {
                continue;
            }
            if (isAccessibleType(base_, _classes)) {
                return base_;
            }
        }
        return _classes.getStandards().getAliasObject();
    }

    @Override
    public String getGenericSuperClass(Analyzable _classes) {
        for (String s: getDirectSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
            base_=_classes.resolveBaseTypeBuildInherits(base_,this);
            RootBlock r_ = _classes.getClasses().getClassBody(base_);
            if (!(r_ instanceof ClassBlock)) {
                continue;
            }
            if (isAccessibleType(base_, _classes)) {
                return _classes.resolveDynamicTypeBuildInherits(s,this);
            }
        }
        return _classes.getStandards().getAliasObject();
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

    @Override
    public void buildDirectGenericSuperTypes(Analyzable _classes) {
        for (String s: getDirectSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
            base_=_classes.resolveDynamicType(base_,this);
            RootBlock r_ = _classes.getClasses().getClassBody(base_);
            if (!(r_ instanceof ClassBlock)) {
                continue;
            }
            if (isAccessibleType(base_, _classes)) {
                importedDirectSuperClass = _classes.resolveDynamicType(s,this);
            }
        }
        if (importedDirectSuperClass.isEmpty()) {
            importedDirectSuperClass = _classes.getStandards().getAliasObject();
        }
        importedDirectSuperInterfaces.clear();
        for (String s: getDirectSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
            base_ = _classes.resolveDynamicType(base_, this);
            RootBlock r_ = _classes.getClasses().getClassBody(base_);
            if (!(r_ instanceof InterfaceBlock)) {
                continue;
            }
            if (isAccessibleType(base_, _classes)) {
                importedDirectSuperInterfaces.add(_classes.resolveDynamicType(s, this));
            }
        }
    }

    @Override
    public String getImportedDirectGenericSuperClass() {
        return importedDirectSuperClass;
    }

    @Override
    public StringList getImportedDirectGenericSuperInterfaces() {
        return importedDirectSuperInterfaces;
    }
}
