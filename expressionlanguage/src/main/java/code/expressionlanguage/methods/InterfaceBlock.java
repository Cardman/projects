package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetAccessInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneInterface;
import code.sml.Element;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

public final class InterfaceBlock extends RootBlock implements GeneInterface {

    private final StringList allSuperClasses = new StringList();

    private final StringList allSuperTypes = new StringList();

    public InterfaceBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        int i_ = CustList.FIRST_INDEX;
        while (_el.hasAttribute(StringList.concatNbs(ATTRIBUTE_CLASS,i_))) {
            getDirectSuperTypes().add(_el.getAttribute(StringList.concatNbs(ATTRIBUTE_CLASS,i_)));
            i_++;
        }
    }

    public InterfaceBlock(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, int _idRowCol, int _categoryOffset ,String _name, String _packageName, OffsetAccessInfo _access,
            String _templateDef, NatTreeMap<Integer, String> _directSuperTypes, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _idRowCol, _categoryOffset, _name, _packageName, _access, _templateDef, _directSuperTypes, _offset);
    }

    @Override
    public void setupBasicOverrides(ContextEl _context) {
        useSuperTypesOverrides(_context);
    }

    @Override
    public StringList getDirectGenericSuperTypesBuild(Analyzable _classes) {
        return new StringList(getDirectSuperTypes());
    }

    @Override
    public StringList getDirectGenericSuperTypes(Analyzable _classes) {
        StringList classes_ = new StringList();
        for (String s: getDirectSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
            if (isAccessibleType(base_, _classes)) {
                classes_.add(s);
            }
        }
        return classes_;
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
        return TAG_INTERFACE;
    }

    @Override
    public StringList getDirectGenericSuperClasses(Analyzable _classes) {
        StringList classes_ = new StringList();
        for (String s: getDirectSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
            if (isAccessibleType(base_, _classes)) {
                classes_.add(s);
            }
        }
        if (classes_.isEmpty()) {
            classes_.add(_classes.getStandards().getAliasObject());
        }
        return classes_;
    }

    @Override
    public StringList getDirectSuperClasses(Analyzable _classes) {
        StringList classes_ = new StringList();
        for (String s: getDirectSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
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
    public RootBlock belong() {
        return this;
    }

    @Override
    public boolean isFinalType() {
        return false;
    }

    @Override
    public boolean isAbstractType() {
        return true;
    }

    @Override
    public boolean mustImplement() {
        return false;
    }

    @Override
    public StringList getAllInterfaces() {
        return getAllSuperClasses();
    }

    @Override
    public StringList getAllGenericSuperClasses(Analyzable _classes) {
        Classes classes_ = _classes.getClasses();
        StringList allSuperTypes_ = getAllGenericSuperTypes(_classes);
        StringList allGenericSuperClasses_ = new StringList();
        for (String s: allSuperTypes_) {
            String base_ = Templates.getIdFromAllTypes(s);
            if (classes_.getClassBody(base_) instanceof InterfaceBlock) {
                allGenericSuperClasses_.add(s);
            }
        }
        return allGenericSuperClasses_;
    }

    @Override
    public StringList getAllGenericInterfaces(Analyzable _classes) {
        return getAllGenericSuperClasses(_classes);
    }

    public StringList getDirectSuperInterfaces() {
        StringList classes_ = new StringList();
        for (String s: getDirectSuperTypes()) {
            int index_ = s.indexOf(LT);
            if (index_ > CustList.INDEX_NOT_FOUND_ELT) {
                classes_.add(s.substring(CustList.FIRST_INDEX, index_));
            } else {
                classes_.add(s);
            }
        }
        return classes_;
    }
}
