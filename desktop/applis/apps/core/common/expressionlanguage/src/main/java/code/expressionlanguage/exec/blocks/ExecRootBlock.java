package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecFunctionalInfo;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.expressionlanguage.fwd.blocks.ExecRootBlockContent;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public abstract class ExecRootBlock extends ExecBracedBlock implements GeneType,ExecAnnotableBlock {
    private final ExecRootBlockContent rootBlockContent;

    private final AccessEnum access;

    private final CustList<ExecRootBlock> staticInitImportedInterfaces = new CustList<ExecRootBlock>();
    private final CustList<ExecFormattedRootBlock> instanceInitImportedInterfaces = new CustList<ExecFormattedRootBlock>();

    private final CustList<ExecAnnotContent> annotationsOps = new CustList<ExecAnnotContent>();
    private final CustList<ExecFormattedRootBlock> allGenericSuperTypes = new CustList<ExecFormattedRootBlock>();
    private final CustList<ExecFunctionalInfo> functionalBodies = new CustList<ExecFunctionalInfo>();
    private ExecRootBlock uniqueType;
    private final StringList allSuperTypes = new StringList();
    private ExecRootBlock parentType;
    private final CustList<ExecRootBlock> childrenTypes = new CustList<ExecRootBlock>();
    private final CustList<ExecBlock> childrenOthers = new CustList<ExecBlock>();
    private final CustList<ExecFieldBlock> instanceFields = new CustList<ExecFieldBlock>();
    private final CustList<ExecAnnotationMethodBlock> annotationsFields = new CustList<ExecAnnotationMethodBlock>();
    private final CustList<ExecInnerTypeOrElement> enumElements = new CustList<ExecInnerTypeOrElement>();
    private ExecTypeFunction emptyCtorPair;
    private ExecFormattedRootBlock formattedSuperClass;
    private final CustList<ExecRootBlock> anonymousRoot = new CustList<ExecRootBlock>();
    private final CustList<ExecAnonymousFunctionBlock> anonymousRootLambda = new CustList<ExecAnonymousFunctionBlock>();
    private final CustList<ExecAbstractSwitchMethod> switchMethodsRoot = new CustList<ExecAbstractSwitchMethod>();
    private boolean withInstanceElements;
    private final CustList<ExecFieldBlock> allExpFields = new CustList<ExecFieldBlock>();
    private final CustList<ExecMemberCallingsBlock> allFct = new CustList<ExecMemberCallingsBlock>();
    private final CustList<ExecBlock> allInstanceMembers = new CustList<ExecBlock>();
    private final CustList<ExecBlock> allStaticMembers = new CustList<ExecBlock>();
    private final CustList<ExecInstanceBlock> allInstanceInits = new CustList<ExecInstanceBlock>();
    private final CustList<ExecStaticBlock> allStaticInits = new CustList<ExecStaticBlock>();
    private int numberType;

    ExecRootBlock(ExecRootBlockContent _rootBlockContent, AccessEnum _access) {
        rootBlockContent = _rootBlockContent;
        access = _access;
    }
    public static int numberType(ExecRootBlock _ex) {
        if (_ex == null) {
            return -1;
        }
        return _ex.getNumberType();
    }

    public int getIdRowCol() {
        return rootBlockContent.getIdRowCol();
    }
    @Override
    public StringList getParamTypesValues() {
        return rootBlockContent.getParamTypesValues();
    }
    @Override
    public CustList<ExecTypeVar> getParamTypesMapValues() {
        return rootBlockContent.getParamTypesMapValues();
    }
    @Override
    public CustList<StringList> getBoundAll() {
        return rootBlockContent.getBoundsAll();
    }

    public Ints getTypeVarCounts() {
        return rootBlockContent.getTypeVarCounts();
    }
    public String getWildCardElement() {
        StringList allElements_ = new StringList();
        for (ExecInnerTypeOrElement e: enumElements) {
            String type_ = e.getRealImportedClassName();
            allElements_.add(type_);
        }
        String className_;
        if (allElements_.onlyOneElt()) {
            className_ = allElements_.first();
        } else {
            className_ = getWildCardString();
        }
        return className_;
    }

    public final String getWildCardString() {
        return rootBlockContent.getWildCardString();
    }

    public final CustList<ExecFormattedRootBlock> getAllGenericSuperTypes() {
        return allGenericSuperTypes;
    }

    public CustList<ExecRootBlock> getStaticInitImportedInterfaces() {
        return staticInitImportedInterfaces;
    }

    public CustList<ExecFormattedRootBlock> getInstanceInitImportedInterfaces() {
        return instanceInitImportedInterfaces;
    }

    public final ExecRootBlock getParentType() {
        return parentType;
    }

    public final void setParentType(ExecRootBlock _parentType) {
        parentType = _parentType;
    }

    public final CustList<ExecRootBlock> getSelfAndParentTypes() {
        return rootBlockContent.getSelfAndParentTypes();
    }

    @Override
    public CustList<ExecAnnotContent> getAnnotationsOps() {
        return annotationsOps;
    }

    @Override
    public String getGenericString() {
        return rootBlockContent.getGenericString();
    }

    public AccessEnum getAccess() {
        return access;
    }

    @Override
    public String getFullName() {
        return rootBlockContent.getFullName();
    }


    public CustList<ExecFunctionalInfo> getFunctionalBodies() {
        return functionalBodies;
    }


    public String getImportedDirectGenericSuperClass() {
        return rootBlockContent.getImportedDirectSuperClass();
    }

    public StringList getImportedDirectGenericSuperInterfaces() {
        return rootBlockContent.getImportedDirectSuperInterfaces();
    }

    public final StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    public CustList<ExecRootBlock> getChildrenTypes() {
        return childrenTypes;
    }

    public CustList<ExecBlock> getChildrenOthers() {
        return childrenOthers;
    }

    public CustList<ExecFieldBlock> getInstanceFields() {
        return instanceFields;
    }

    public CustList<ExecAnnotationMethodBlock> getAnnotationsFields() {
        return annotationsFields;
    }

    public CustList<ExecInnerTypeOrElement> getEnumElements() {
        return enumElements;
    }

    public ExecRootBlock getUniqueType() {
        return uniqueType;
    }

    public void setUniqueType(ExecRootBlock _uniqueType) {
        uniqueType = _uniqueType;
    }

    public ExecTypeFunction getEmptyCtorPair() {
        return emptyCtorPair;
    }

    public void emptyCtorPair(ExecNamedFunctionBlock _emptyCtor) {
        this.emptyCtorPair = new ExecTypeFunction(this,_emptyCtor);
    }

    public ExecFormattedRootBlock getFormattedSuperClass() {
        return formattedSuperClass;
    }

    public void setFormattedSuperClass(ExecFormattedRootBlock _formattedSuperClass) {
        this.formattedSuperClass = _formattedSuperClass;
    }

    public CustList<ExecRootBlock> getAnonymousRoot() {
        return anonymousRoot;
    }

    public CustList<ExecAnonymousFunctionBlock> getAnonymousRootLambda() {
        return anonymousRootLambda;
    }

    public CustList<ExecAbstractSwitchMethod> getSwitchMethodsRoot() {
        return switchMethodsRoot;
    }

    public ExecRootBlockContent getRootBlockContent() {
        return rootBlockContent;
    }

    public boolean isWithInstanceElements() {
        return withInstanceElements;
    }

    public void setWithInstanceElements(boolean _withInstanceElements) {
        withInstanceElements = _withInstanceElements;
    }

    public CustList<ExecFieldBlock> getAllExpFields() {
        return allExpFields;
    }

    public CustList<ExecMemberCallingsBlock> getAllFct() {
        return allFct;
    }

    public CustList<ExecBlock> getAllInstanceMembers() {
        return allInstanceMembers;
    }

    public CustList<ExecBlock> getAllStaticMembers() {
        return allStaticMembers;
    }

    public CustList<ExecInstanceBlock> getAllInstanceInits() {
        return allInstanceInits;
    }

    public CustList<ExecStaticBlock> getAllStaticInits() {
        return allStaticInits;
    }

    public int getNumberType() {
        return numberType;
    }

    public void setNumberType(int _n) {
        this.numberType = _n;
    }
}
