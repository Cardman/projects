package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetAccessInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.methods.util.BadAccessMethod;
import code.expressionlanguage.methods.util.BadInheritedClass;
import code.expressionlanguage.methods.util.BadReturnTypeInherit;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.sml.RowCol;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class ClassBlock extends RootBlock implements UniqueRootedBlock {

    private final StringList allSuperClasses = new StringList();

    private final StringList allSuperTypes = new StringList();

    private final StringList allInterfaces = new StringList();

    private String importedDirectSuperClass = "";
    private StringList importedDirectSuperInterfaces = new StringList();
    private final boolean finalType;
    private final boolean abstractType;
    private final boolean staticType;

    public ClassBlock(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, int _idRowCol, int _categoryOffset ,String _name, String _packageName, OffsetAccessInfo _access,
            String _templateDef, NatTreeMap<Integer, String> _directSuperTypes,
            boolean _finalType,
            boolean _abstractType, boolean _staticType,
            OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _idRowCol, _categoryOffset, _name, _packageName, _access, _templateDef, _directSuperTypes, _offset);
        finalType = _finalType;
        abstractType = _abstractType;
        staticType = _staticType;
    }

    @Override
    public boolean isStaticType() {
        return staticType;
    }
    @Override
    public void setupBasicOverrides(ContextEl _context) {
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
        for (String b: getImportedDirectSuperTypes()) {
            String id_ = Templates.getIdFromAllTypes(b);
            RootBlock bBl_ = classesRef_.getClassBody(id_);
            if (!(bBl_ instanceof ClassBlock)) {
                continue;
            }
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
        useSuperTypesOverrides(_context);
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
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
            for (GeneMethod m: Classes.getMethodBlocks(r_)) {
                if (m.isStaticMethod()) {
                    continue;
                }
                String formattedSuper_ = Templates.getFullTypeByBases(gene_, s, _context);
                MethodId id_ = m.getId().quickFormat(formattedSuper_, _context);
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
                        err_.setRc(((MethodBlock)m).getRowCol(0, mBase_.getAccessOffset()));
                        err_.setId(m.getId());
                        classesRef_.addError(err_);
                    }
                    String retBase_ = m.getImportedReturnType();
                    String retDerive_ = mBase_.getImportedReturnType();
                    String formattedRetDer_ = Templates.quickFormat(c, retDerive_, _context);
                    String formattedRetBase_ = Templates.quickFormat(s, retBase_, _context);
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
                    } else if (!Templates.isReturnCorrect(formattedRetBase_, formattedRetDer_, vars_, _context)) {
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
        if (!StringList.quickEq(importedDirectSuperClass, _classes.getStandards().getAliasObject())) {
            interfaces_.add(importedDirectSuperClass);
        }
        interfaces_.addAllElts(importedDirectSuperInterfaces);
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
    public StringList getAllInterfaces() {
        return allInterfaces;
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
    public void buildDirectGenericSuperTypes(ContextEl _classes) {
        NatTreeMap<Integer, String> rcs_;
        rcs_ = getRowColDirectSuperTypes();
        int i_ = 0;
        importedDirectSuperInterfaces.clear();
        for (String s: getDirectSuperTypes()) {
            int index_ = rcs_.getKey(i_);
            RowCol rc_ = getRowCol(0,index_);
            String s_ = _classes.resolveTypeInherits(s, this,rc_,i_);
            i_++;
            String base_ = Templates.getIdFromAllTypes(s_);
            RootBlock r_ = _classes.getClasses().getClassBody(base_);
            if (!(r_ instanceof ClassBlock)) {
                importedDirectSuperInterfaces.add(s_);
            } else {
                importedDirectSuperClass = s_;
            }
        }
        if (importedDirectSuperClass.isEmpty()) {
            importedDirectSuperClass = _classes.getStandards().getAliasObject();
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

    @Override
    public StringList getImportedDirectSuperTypes() {
        StringList l_ = new StringList(importedDirectSuperClass);
        l_.addAllElts(importedDirectSuperInterfaces);
        return l_;
    }
}
