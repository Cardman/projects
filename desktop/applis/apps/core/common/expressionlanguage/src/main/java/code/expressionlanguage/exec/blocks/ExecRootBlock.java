package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.util.Members;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.OverridingMethod;
import code.util.*;

public abstract class ExecRootBlock extends ExecBracedBlock implements GeneType,AccessedBlock, ExecAnnotableBlock {

    private String name;

    private String packageName;

    private AccessEnum access;

    private StringList imports;

    private CustList<OverridingMethod> allOverridingMethods = new CustList<OverridingMethod>();

    private StringList paramTypes;

    private StringMap<ExecTypeVar> paramTypesMap = new StringMap<ExecTypeVar>();

    private final StringList importedDirectBaseSuperTypes = new StringList();

    private int idRowCol;

    private StringList staticInitImportedInterfaces = new StringList();

    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();
    private final StringList allGenericSuperTypes = new StringList();
    private final StringList allGenericClasses = new StringList();
    private final CustList<ClassMethodId> functional = new CustList<ClassMethodId>();
    private String importedDirectSuperClass = "";
    private StringList importedDirectSuperInterfaces = new StringList();

    ExecRootBlock(RootBlock _offset) {
        super(_offset.getOffset());
        imports = _offset.getImports();
        packageName = _offset.getPackageName();
        name = _offset.getName();
        access = _offset.getAccess();
        idRowCol = _offset.getIdRowCol();
        paramTypes = _offset.getParamTypesAsStringList();
    }

    public ExecRootBlock getOuter() {
        ExecRootBlock t = this;
        ExecRootBlock o = this;
        while (t != null) {
            o = t;
            t = t.getParentType();
        }
        return o;
    }

    @Override
    public StringList getParamTypesValues() {
        StringList l_ = new StringList();
        for (ExecRootBlock r: getSelfAndParentTypes()) {
            for (String t: r.paramTypes) {
                l_.add(t);
            }
        }
        return l_;
    }
    @Override
    public CustList<ExecTypeVar> getParamTypesMapValues() {
        return paramTypesMap.values();
    }
    @Override
    public CustList<StringList> getBoundAll() {
        CustList<StringList> boundsAll_ = new CustList<StringList>();
        for (ExecTypeVar t: getParamTypesMapValues()) {
            boolean contained_ = false;
            for (String u: paramTypes) {
                if (!StringList.quickEq(t.getName(),u)) {
                    continue;
                }
                contained_ = true;
            }
            if (!contained_) {
                continue;
            }
            StringList localBound_ = new StringList();
            for (String b: t.getConstraints()) {
                localBound_.add(b);
            }
            boundsAll_.add(localBound_);
        }
        return boundsAll_;
    }

    public Ints getTypeVarCounts() {
        Ints generic_ = new Ints();
        CustList<ExecRootBlock> pars_ = getSelfAndParentTypes();
        CustList<ExecRootBlock> stPars_ = pars_.first().getAllParentTypesReverse();
        int len_ = stPars_.size();
        for (int i = 0; i < len_; i++) {
            generic_.add(0);
        }
        for (ExecRootBlock r: pars_) {
            generic_.add(r.paramTypes.size());
        }
        return generic_;
    }
    public String getWildCardElement() {
        StringList allElements_ = new StringList();
        for (ExecBlock e: getDirectChildren(this)) {
            if (e instanceof ExecInnerTypeOrElement) {
                String type_ = ((ExecInnerTypeOrElement)e).getRealImportedClassName();
                allElements_.add(type_);
            }
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
        String pkg_ = getPackageName();
        StringBuilder generic_ = new StringBuilder();
        RootBlock.addPkgIfNotEmpty(pkg_, generic_);
        CustList<ExecRootBlock> pars_ = getSelfAndParentTypes();
        ExecRootBlock previous_ = null;
        for (ExecRootBlock r: pars_.first().getAllParentTypesReverse()) {
            appendParts(generic_, previous_, r, "-", "..");
            generic_.append(r.getName());
            previous_ = r;
        }
        for (ExecRootBlock r: pars_) {
            appendParts(generic_, previous_, r, "-", "..");
            generic_.append(r.getName());
            if (!r.paramTypes.isEmpty()) {
                StringList vars_ = new StringList();
                int count_ = r.paramTypes.size();
                for (int i = 0; i < count_; i++) {
                    vars_.add(Templates.SUB_TYPE);
                }
                generic_.append(Templates.TEMPLATE_BEGIN);
                generic_.append(StringList.join(vars_, Templates.TEMPLATE_SEP));
                generic_.append(Templates.TEMPLATE_END);
            }
            previous_ = r;
        }
        return generic_.toString();
    }

    private static void appendParts(StringBuilder generic_, ExecRootBlock previous_, ExecRootBlock r, String _sepInn, String _sep) {
        if (previous_ != null) {
            if (r instanceof ExecInnerElementBlock) {
                generic_.append(_sepInn);
            } else {
                generic_.append(_sep);
            }
        }
    }

    @Override
    public boolean withoutInstance() {
        return isStaticType();
    }

    public abstract boolean isFinalType();
    public abstract boolean isAbstractType();

    @Override
    public abstract boolean isStaticType();

    public final StringList getAllGenericSuperTypes() {
        return allGenericSuperTypes;
    }

    public StringList getAllGenericClasses() {
        return allGenericClasses;
    }

    public StringList getStaticInitImportedInterfaces() {
        return staticInitImportedInterfaces;
    }
    public final String getParentFullName() {
        ExecRootBlock par_ = getParentType();
        if (par_ == null) {
            return "";
        }
        return par_.getFullName();
    }
    public final ExecRootBlock getParentType() {
        ExecBracedBlock p_ = getParent();
        while (!(p_ instanceof ExecRootBlock)) {
            if (p_ == null) {
                return null;
            }
            p_ = p_.getParent();
        }
        return (ExecRootBlock) p_;
    }
    public final CustList<ExecRootBlock> getAllParentTypesReverse() {
        return getAllParentTypes().getReverse();
    }
    public final CustList<ExecRootBlock> getAllParentTypes() {
        CustList<ExecRootBlock> pars_ = new CustList<ExecRootBlock>();
        ExecRootBlock c_ = getParentType();
        while (c_ != null) {
            pars_.add(c_);
            c_ = c_.getParentType();
        }
        return pars_;
    }
    public final CustList<ExecRootBlock> getSelfAndParentTypes() {
        CustList<ExecRootBlock> pars_ = new CustList<ExecRootBlock>();
        ExecRootBlock c_ = this;
        boolean add_ = true;
        while (c_ != null) {
            if (add_) {
                pars_.add(c_);
            }
            if (c_.withoutInstance()) {
                add_ = false;
            }
            c_ = c_.getParentType();
        }
        return pars_.getReverse();
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
    public StringList getImports() {
        return imports;
    }

    @Override
    public StringList getFileImports() {
        return getFile().getImports();
    }

    @Override
    public String getGenericString() {
        String pkg_ = getPackageName();
        StringBuilder generic_ = new StringBuilder();
        RootBlock.addPkgIfNotEmpty(pkg_, generic_);
        CustList<ExecRootBlock> pars_ = getSelfAndParentTypes();
        ExecRootBlock previous_ = null;
        for (ExecRootBlock r: pars_.first().getAllParentTypesReverse()) {
            appendParts(generic_, previous_, r, "-", "..");
            generic_.append(r.getName());
            previous_ = r;
        }
        for (ExecRootBlock r: pars_) {
            appendParts(generic_, previous_, r, "-", "..");
            generic_.append(r.getName());
            if (!r.paramTypes.isEmpty()) {
                StringList vars_ = new StringList();
                for (String t:r.paramTypes) {
                    vars_.add(StringList.concat(Templates.PREFIX_VAR_TYPE,t));
                }
                generic_.append(Templates.TEMPLATE_BEGIN);
                generic_.append(StringList.join(vars_, Templates.TEMPLATE_SEP));
                generic_.append(Templates.TEMPLATE_END);
            }
            previous_ = r;
        }
        return generic_.toString();
    }

    public void buildMapParamType(RootBlock _root) {
        paramTypes = _root.getParamTypesAsStringList();
        paramTypesMap = _root.getParamTypesMapAsExec();
    }

    public String getName() {
        return name;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public AccessEnum getAccess() {
        return access;
    }
    /**
     @return a map with formatted id from super types as key
     and a list of (formatted super types and id) as value
     */
    public CustList<OverridingMethod> getAllOverridingMethods() {
        return allOverridingMethods;
    }

    @Override
    public String getFullName() {
        return formatName();
    }

    private String formatName() {
        CustList<ExecRootBlock> all_ = new CustList<ExecRootBlock>(this);
        all_.addAllElts(getAllParentTypes());
        ExecRootBlock p_ = null;
        StringBuilder strBuilder_ = new StringBuilder();
        RootBlock.addPkgIfNotEmpty(packageName,strBuilder_);
        for (ExecRootBlock r: all_.getReverse()) {
            appendParts(strBuilder_,p_,r,"-","..");
            strBuilder_.append(r.getName());
            p_ = r;
        }
        return strBuilder_.toString();
    }


    public CustList<ClassMethodId> getFunctional() {
        return functional;
    }

    public boolean isSubTypeOf(String _fullName, ContextEl _an) {
        if (StringList.quickEq(getFullName(),_fullName)) {
            return true;
        }
        return StringList.contains(getAllSuperTypes(),_fullName);
    }

    public StringList getImportedDirectBaseSuperTypes() {
        return importedDirectBaseSuperTypes;
    }

    public final void validateIds(RootBlock _key, IdMap<RootBlock, Members> _mapMembers) {
        Members mem_ = _mapMembers.getVal(_key);
        for (EntryCust<OverridableBlock,ExecOverridableBlock> e: mem_.getAllMethods().entryList()) {
            e.getValue().buildImportedTypes(e.getKey());
        }
        for (EntryCust<ConstructorBlock,ExecConstructorBlock> e: mem_.getAllCtors().entryList()) {
            e.getValue().buildImportedTypes(e.getKey());
        }
        for (EntryCust<AnnotationMethodBlock,ExecAnnotationMethodBlock> e: mem_.getAllAnnotMethods().entryList()) {
            e.getValue().buildImportedTypes(e.getKey());
        }
        for (EntryCust<InfoBlock,ExecInfoBlock> e: mem_.getAllFields().entryList()) {
            e.getValue().buildImportedTypes(e.getKey());
        }
    }

    public String getOuterFullName() {
        return getOuter().getFullName();
    }

    public int getIdRowCol() {
        return idRowCol;
    }


    public StringList getImportedDirectSuperTypes() {
        StringList l_ = new StringList(importedDirectSuperClass);
        l_.addAllElts(importedDirectSuperInterfaces);
        return l_;
    }

    public String getImportedDirectGenericSuperClass() {
        return importedDirectSuperClass;
    }

    public StringList getImportedDirectGenericSuperInterfaces() {
        return importedDirectSuperInterfaces;
    }

    public void setImportedDirectSuperClass(String importedDirectSuperClass) {
        this.importedDirectSuperClass = importedDirectSuperClass;
    }

}
