package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.util.Members;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecFunctionalInfo;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.expressionlanguage.exec.util.ClassMethodIdOverrides;
import code.expressionlanguage.fwd.blocks.ExecRootBlockContent;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.util.*;

public abstract class ExecRootBlock extends ExecBracedBlock implements GeneType, ExecAnnotableBlock {
    private ExecRootBlockContent rootBlockContent;

    private AccessEnum access;

    private final ClassMethodIdOverrides redirections = new ClassMethodIdOverrides();

    private StringList staticInitImportedInterfaces = new StringList();

    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();
    private final CustList<ExecFormattedRootBlock> allGenericSuperTypes = new CustList<ExecFormattedRootBlock>();
    private final CustList<ExecFunctionalInfo> functionalBodies = new CustList<ExecFunctionalInfo>();
    private String importedDirectSuperClass = "";
    private ExecRootBlock uniqueType;
    private StringList importedDirectSuperInterfaces = new StringList();
    private final StringList allSuperTypes = new StringList();
    private ExecRootBlock parentType;
    private final CustList<ExecRootBlock> childrenTypes = new CustList<ExecRootBlock>();
    private final CustList<ExecFieldBlock> instanceFields = new CustList<ExecFieldBlock>();
    private final CustList<ExecAnnotationMethodBlock> annotationsFields = new CustList<ExecAnnotationMethodBlock>();
    private final CustList<ExecInnerTypeOrElement> enumElements = new CustList<ExecInnerTypeOrElement>();
    private ExecNamedFunctionBlock emptyCtor;
    private CustList<ExecRootBlock> anonymousRoot = new CustList<ExecRootBlock>();
    private CustList<ExecAnonymousFunctionBlock> anonymousRootLambda = new CustList<ExecAnonymousFunctionBlock>();

    ExecRootBlock(RootBlock _offset) {
        super(_offset.getOffset());
        rootBlockContent = new ExecRootBlockContent(_offset.getRootBlockContent());
        access = _offset.getAccess();
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

    @Override
    public boolean withoutInstance() {
        return isStaticType();
    }

    @Override
    public abstract boolean isStaticType();

    public final CustList<ExecFormattedRootBlock> getAllGenericSuperTypes() {
        return allGenericSuperTypes;
    }

    public StringList getStaticInitImportedInterfaces() {
        return staticInitImportedInterfaces;
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
    public void reduce(ContextEl _context) {
        CustList<CustList<ExecOperationNode>> annotationsOps_;
        annotationsOps_ = new CustList<CustList<ExecOperationNode>>();
        for (CustList<ExecOperationNode> a: annotationsOps) {
            ExecOperationNode r_ = a.last();
            annotationsOps_.add(ExpressionLanguage.getReducedNodes(r_));
        }
        annotationsOps = annotationsOps_;
    }
    @Override
    public CustList<CustList<ExecOperationNode>> getAnnotationsOps() {
        return annotationsOps;
    }

    @Override
    public String getGenericString() {
        return rootBlockContent.getGenericString();
    }

    public void buildMapParamType(RootBlock _root) {
        ForwardInfos.updateExec(this,_root);
        importedDirectSuperClass = _root.getImportedDirectGenericSuperClass();
        importedDirectSuperInterfaces = _root.getImportedDirectSuperInterfaces();
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

    public boolean isSubTypeOf(String _fullName, ContextEl _an) {
        if (StringList.quickEq(getFullName(),_fullName)) {
            return true;
        }
        return StringList.contains(getAllSuperTypes(),_fullName);
    }

    public final void validateIds(RootBlock _key, IdMap<RootBlock, Members> _mapMembers) {
        Members mem_ = _mapMembers.getVal(_key);
        for (EntryCust<OverridableBlock,ExecOverridableBlock> e: mem_.getAllMethods().entryList()) {
            e.getValue().buildImportedTypes(e.getKey().getImportedReturnType(), e.getKey().getImportedParametersTypes());
            String returnTypeGet_ = e.getKey().getReturnTypeGet();
            if (!returnTypeGet_.isEmpty()) {
                e.getValue().setImportedReturnType(returnTypeGet_);
            }
        }
        for (EntryCust<ConstructorBlock,ExecConstructorBlock> e: mem_.getAllCtors().entryList()) {
            e.getValue().buildImportedTypes(e.getKey().getImportedReturnType(), e.getKey().getImportedParametersTypes());
        }
        for (EntryCust<AnnotationMethodBlock,ExecAnnotationMethodBlock> e: mem_.getAllAnnotMethods().entryList()) {
            e.getValue().buildImportedTypes(e.getKey());
        }
        for (EntryCust<InfoBlock,ExecInfoBlock> e: mem_.getAllFields().entryList()) {
            e.getValue().buildImportedTypes(e.getKey());
        }
    }

    public int getIdRowCol() {
        return rootBlockContent.getIdRowCol();
    }


    public String getImportedDirectGenericSuperClass() {
        return importedDirectSuperClass;
    }

    public StringList getImportedDirectGenericSuperInterfaces() {
        return importedDirectSuperInterfaces;
    }

    public final StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    public ClassMethodIdOverrides getRedirections() {
        return redirections;
    }

    public CustList<ExecRootBlock> getChildrenTypes() {
        return childrenTypes;
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

    public ExecNamedFunctionBlock getEmptyCtor() {
        return emptyCtor;
    }

    public void setEmptyCtor(ExecNamedFunctionBlock _emptyCtor) {
        emptyCtor = _emptyCtor;
    }

    public CustList<ExecRootBlock> getAnonymousRoot() {
        return anonymousRoot;
    }

    public CustList<ExecAnonymousFunctionBlock> getAnonymousRootLambda() {
        return anonymousRootLambda;
    }

    public ExecRootBlockContent getRootBlockContent() {
        return rootBlockContent;
    }
}
