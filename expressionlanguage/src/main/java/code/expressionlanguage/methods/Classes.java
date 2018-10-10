package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ClassDeepCmp;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultLockingClass;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.FileResolver;
import code.expressionlanguage.InitClassState;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.Options;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.common.GeneInterface;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.util.BadClassName;
import code.expressionlanguage.methods.util.BadInheritedClass;
import code.expressionlanguage.methods.util.BadMethodName;
import code.expressionlanguage.methods.util.BadParamName;
import code.expressionlanguage.methods.util.ClassEdge;
import code.expressionlanguage.methods.util.DuplicateGenericSuperTypes;
import code.expressionlanguage.methods.util.DuplicateMethod;
import code.expressionlanguage.methods.util.DuplicateParamName;
import code.expressionlanguage.methods.util.DuplicateType;
import code.expressionlanguage.methods.util.EmptyTagName;
import code.expressionlanguage.methods.util.ErrorList;
import code.expressionlanguage.methods.util.FoundErrorInterpret;
import code.expressionlanguage.methods.util.FoundWarningInterpret;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.expressionlanguage.methods.util.WarningList;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodMetaInfo;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.opers.util.UnassignedFinalField;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.types.ParserType;
import code.expressionlanguage.types.PartTypeUtil;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.RowCol;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.graphs.Graph;
import code.util.graphs.SortedGraph;

public final class Classes {

    public static final String EXT = "cdm";
    public static final String TEMP_PREFIX = "tmp";
    private static final char DOT = '.';
    private static final String LOC_VAR = ".";

    private static final String PARS = "()";
    private static final String EMPTY_STRING = "";
    private static final String VARARG = "...";

    private final StringMap<RootBlock> classesBodies;
    private final StringMap<FileBlock> filesBodies;

    private final StringMap<StringMap<Struct>> staticFields;

    private final ErrorList errorsDet;
    private final WarningList warningsDet;
    private DefaultLockingClass locks;
    private String iteratorVar;
    private String hasNextVar;
    private String nextVar;
    private String iteratorVarCust;
    private String hasNextVarCust;
    private String nextVarCust;
    private CustList<OperationNode> expsIterator;
    private CustList<OperationNode> expsHasNext;
    private CustList<OperationNode> expsNext;
    private CustList<OperationNode> expsIteratorCust;
    private CustList<OperationNode> expsHasNextCust;
    private CustList<OperationNode> expsNextCust;
    private CustList<OperatorBlock> operators;

    public Classes(){
        classesBodies = new StringMap<RootBlock>();
        filesBodies = new StringMap<FileBlock>();
        errorsDet = new ErrorList();
        warningsDet = new WarningList();
        staticFields = new StringMap<StringMap<Struct>>();
        operators = new CustList<OperatorBlock>();
    }
    
    public void putFileBlock(String _fileName, FileBlock _fileBlock) {
        filesBodies.put(_fileName, _fileBlock);
    }
    public StringMap<FileBlock> getFilesBodies() {
        return filesBodies;
    }

    public void processBracedClass(RootBlock _root, boolean _predefined, ContextEl _context) {
        String fullName_ = _root.getFullName();
        if (classesBodies.contains(fullName_)) {
            DuplicateType d_ = new DuplicateType();
            d_.setId(fullName_);
            d_.setFileName(_root.getFile().getFileName());
            d_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
            addError(d_);
        }
        String packageName_;
        packageName_ = _root.getPackageName();
        LgNames lgNames_ = _context.getStandards();
        if (!_predefined) {
            if (packageName_.isEmpty()) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(fullName_);
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
                addError(badCl_);
            }
            StringList elements_ = StringList.splitChars(packageName_, DOT);
            for (String e: elements_) {
                if (!StringList.isWord(e)) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullName_);
                    badCl_.setFileName(_root.getFile().getFileName());
                    badCl_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
                    addError(badCl_);
                }
            }
            String className_;
            className_ = _root.getName();
            if (!StringList.isWord(className_)) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(fullName_);
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
                addError(badCl_);
            }
        }
        String fullDef_ = _root.getFullDefinition();
        StringList varTypes_ = new StringList();
        String objectClassName_ = _context.getStandards().getAliasObject();
        Options options_ = _context.getOptions();
        if (ParserType.getIndexes(fullDef_, options_) != null) {
            StringList params_ = Templates.getAllTypes(fullDef_);
            StringList namesFromParent_ = new StringList();
            RootBlock r_ = _root;
            if (!r_.isStaticType()) {
                while (r_.getParent() instanceof RootBlock) {
                    r_ = (RootBlock) r_.getParent();
                    for (TypeVar t: r_.getParamTypes()) {
                        namesFromParent_.add(t.getName());
                    }
                    if (r_.isStaticType()) {
                        break;
                    }
                }
            }
            namesFromParent_.removeDuplicates();
            for (String p: params_.mid(CustList.SECOND_INDEX)) {
                if (p.isEmpty()) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(_root.getFile().getFileName());
                    badCl_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
                    addError(badCl_);
                    continue;
                }
                if (!p.startsWith(Templates.PREFIX_VAR_TYPE)) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(_root.getFile().getFileName());
                    badCl_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
                    addError(badCl_);
                }
                String name_ = p.substring(Templates.PREFIX_VAR_TYPE.length());
                TypeVar type_ = new TypeVar();
                int indexDef_ = name_.indexOf(Templates.EXTENDS_DEF);
                StringList parts_ = StringList.splitInTwo(name_, indexDef_);
                if (!StringList.isWord(parts_.first())) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(_root.getFile().getFileName());
                    badCl_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
                    addError(badCl_);
                }
                if (varTypes_.containsStr(parts_.first())) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(_root.getFile().getFileName());
                    badCl_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
                    addError(badCl_);
                }
                if (namesFromParent_.containsStr(parts_.first())) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(_root.getFile().getFileName());
                    badCl_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
                    addError(badCl_);
                }
                varTypes_.add(parts_.first());
                StringList constraints_ = new StringList();
                if (indexDef_ != CustList.INDEX_NOT_FOUND_ELT) {
                    for (String b: StringList.splitChars(parts_.last().substring(1), Templates.SEP_BOUNDS)) {
                        if (!isCorrectTemplate(b, _context, _root)) {
                            BadClassName badCl_ = new BadClassName();
                            badCl_.setClassName(b);
                            badCl_.setFileName(_root.getFile().getFileName());
                            badCl_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
                            addError(badCl_);
                        }
                        constraints_.add(b);
                    }
                } else {
                    constraints_.add(objectClassName_);
                }
                type_.setConstraints(constraints_);
                type_.setName(parts_.first());
                _root.getParamTypes().add(type_);
            }
        } else {
            BadClassName badCl_ = new BadClassName();
            badCl_.setClassName(fullDef_);
            badCl_.setFileName(_root.getFile().getFileName());
            badCl_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
            addError(badCl_);
        }
        if (_root instanceof EnumBlock) {
            StringBuilder generic_ = new StringBuilder(fullName_);
            if (!_root.getParamTypes().isEmpty()) {
                StringList vars_ = new StringList();
                for (TypeVar t:_root.getParamTypes()) {
                    vars_.add(StringList.concat(Templates.PREFIX_VAR_TYPE,t.getName()));
                }
                generic_.append(Templates.TEMPLATE_BEGIN);
                generic_.append(vars_.join(Templates.TEMPLATE_SEP));
                generic_.append(Templates.TEMPLATE_END);
            }
            String type_ = StringList.concat(_context.getStandards().getAliasEnumParam(),Templates.TEMPLATE_BEGIN,generic_,Templates.TEMPLATE_END);
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        int indexSuperType_= -1;
        for (String s: _root.getDirectGenericSuperTypesBuild(_context)) {
            indexSuperType_++;
            if (!isCorrectTemplate(s, _context, _root)) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(s);
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setRc(_root.getRowCol(0, _root.getRowColDirectSuperTypes().getKey(indexSuperType_)));
                addError(badCl_);
            }
        }
        CustList<TypeVar> tvs_ = _root.getParamTypes();
        for (TypeVar t: tvs_) {
            for (String u: t.getConstraints()) {
                if (!u.startsWith(Templates.PREFIX_VAR_TYPE)) {
                    continue;
                }
                boolean found_ = false;
                for (TypeVar v: tvs_) {
                    if (StringList.quickEq(v.getName(), u.substring(1))) {
                        found_ = true;
                        break;
                    }
                }
                if (!found_) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(u);
                    badCl_.setFileName(_root.getFile().getFileName());
                    badCl_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
                    addError(badCl_);
                }
            }
        }
        if (lgNames_.getStandards().contains(fullName_)) {
            DuplicateType d_ = new DuplicateType();
            d_.setId(fullName_);
            d_.setFileName(_root.getFile().getFileName());
            d_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
            addError(d_);
        }
        if (PrimitiveTypeUtil.isPrimitive(fullName_, _context)) {
            DuplicateType d_ = new DuplicateType();
            d_.setId(fullName_);
            d_.setFileName(_root.getFile().getFileName());
            d_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
            addError(d_);
        }
        classesBodies.put(fullName_, _root);
    }

    private static boolean isCorrectTemplate(String _temp, ContextEl _context, RootBlock _type) {
        RowCol rc_ = new RowCol();
        String temp_ = PartTypeUtil.processTypeHeaders(_temp, _context, _type, rc_);
        if (PrimitiveTypeUtil.isPrimitive(temp_, _context)) {
            return false;
        }
        return true;
    }
    public boolean isEmptyErrors() {
        return errorsDet.isEmpty();
    }
    public String displayErrors() {
        return errorsDet.display();
    }
    public void addError(FoundErrorInterpret _error) {
        errorsDet.add(_error);
    }
    public ErrorList getErrorsDet() {
        return errorsDet;
    }
    public boolean isEmptyWarnings() {
        return warningsDet.isEmpty();
    }
    public void addWarning(FoundWarningInterpret _warning) {
        warningsDet.add(_warning);
    }
    public String displayWarnings() {
        return warningsDet.display();
    }
    public WarningList getWarningsDet() {
        return warningsDet;
    }
    public static void validateAll(StringMap<String> _files, ContextEl _context) {
        Classes classes_ = _context.getClasses();
        Classes.buildPredefinedBracesBodies(_context);
        Classes.tryBuildBracedClassesBodies(_files, _context);
        if (!classes_.isEmptyErrors()) {
            return;
        }
        classes_.validateInheritingClasses(_context, false);
        if (!classes_.isEmptyErrors()) {
            return;
        }
        classes_.validateSingleParameterizedClasses(_context);
        classes_.validateIds(_context);
        classes_.validateOverridingInherit(_context);
        if (!classes_.isEmptyErrors()) {
            return;
        }
        classes_.validateEl(_context);
        TypeUtil.checkInterfaces(_context, classes_.classesBodies.getKeys());
        if (!classes_.isEmptyErrors()) {
            return;
        }
        _context.setAnalyzing(null);
        Classes cl_ = _context.getClasses();
        cl_.getLocks().init(_context);
    }
    
    public static void buildPredefinedBracesBodies(ContextEl _context) {
        _context.setAnalyzing(new AnalyzedPageEl());
        LgNames stds_ = _context.getStandards();
        for (EntryCust<String, String> e: stds_.buildFiles(_context).entryList()) {
            String name_ = e.getKey();
            String content_ = e.getValue();
            FileResolver.parseFile(name_, content_, true, _context);
        }
        Classes cl_ = _context.getClasses();
        cl_.validateInheritingClasses(_context, true);
        for (RootBlock t: cl_.classesBodies.values()) {
            TypeUtil.buildOverrides(t, _context);
        }
        for (RootBlock t: cl_.classesBodies.values()) {
            t.validateIds(_context);
        }
        //local names
        _context.getAnalyzing().setCurrentBlock(null);
        _context.getAnalyzing().setEnabledInternVars(true);
        String locName_ = _context.getNextTempVar();
        String exp_;
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasSimpleIterableType());
        _context.getInternVars().put(locName_, locVar_);
        cl_.iteratorVar = locName_;
        String simpleIterator_ = stds_.getAliasSimpleIterator();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(simpleIterator_,PARS));
        cl_.expsIterator = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasSimpleIteratorType());
        _context.getInternVars().put(locName_, locVar_);
        cl_.hasNextVar = locName_;
        String hasNext_ = stds_.getAliasHasNext();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNext_,PARS));
        cl_.expsHasNext = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasSimpleIteratorType());
        _context.getInternVars().put(locName_, locVar_);
        cl_.nextVar = locName_;
        String next_ = stds_.getAliasNext();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(next_,PARS));
        cl_.expsNext = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(stds_.getAliasIterable(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.iteratorVarCust = locName_;
        String iterator_ = stds_.getAliasSimpleIterator();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(iterator_,PARS));
        cl_.expsIteratorCust = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(stds_.getAliasIteratorType(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.hasNextVarCust = locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNext_,PARS));
        cl_.expsHasNextCust = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(stds_.getAliasIteratorType(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.nextVarCust = locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(next_,PARS));
        cl_.expsNextCust = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
    }
    public static void tryBuildBracedClassesBodies(StringMap<String> _files, ContextEl _context) {
        _context.setAnalyzing(null);
        for (EntryCust<String,String> f: _files.entryList()) {
            String file_ = f.getKey();
            String content_ = f.getValue();
            FileResolver.parseFile(file_, content_, false, _context);
        }
    }
    
    public static CustList<Block> getSortedDescNodes(Block _root) {
        CustList<Block> list_ = new CustList<Block>();
        if (_root == null) {
            return list_;
        }
        Block c_ = _root;
        Block f_ = c_.getFirstChild();
        list_.add(c_);
        if (f_ == null) {
            return list_;
        }
        c_ = f_;
        while (true) {
            if (c_ == null) {
                break;
            }
            list_.add(c_);
            c_ = getNext(c_, _root);
        }
        return list_;
    }

    public static Block getNext(Block _current, Block _root) {
        Block n_ = _current.getFirstChild();
        if (n_ != null) {
            return n_;
        }
        n_ = _current.getNextSibling();
        if (n_ != null) {
            return n_;
        }
        n_ = _current.getParent();
        if (n_ == _root) {
            return null;
        }
        if (n_ != null) {
            Block next_ = n_.getNextSibling();
            while (next_ == null) {
                Block par_ = n_.getParent();
                if (par_ == _root) {
                    break;
                }
                if (par_ == null) {
                    break;
                }
                next_ = par_.getNextSibling();
                n_ = par_;
            }
            if (next_ != null) {
                return next_;
            }
        }
        return null;
    }
    public static CustList<GeneMethod> getMethodBlocks(RootBlock _element) {
        CustList<GeneMethod> methods_ = new CustList<GeneMethod>();
        for (Block b: Classes.getDirectChildren(_element)) {
            if (b instanceof MethodBlock) {
                methods_.add((MethodBlock) b);
            }
            if (b instanceof AnnotationMethodBlock) {
                methods_.add((AnnotationMethodBlock) b);
            }
        }
        return methods_;
    }
    public static CustList<Block> getDirectChildren(Block _element) {
        CustList<Block> list_ = new CustList<Block>();
        if (_element == null) {
            return list_;
        }
        Block firstChild_ = _element.getFirstChild();
        Block elt_ = firstChild_;
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }
    public void validateInheritingClasses(ContextEl _context, boolean _predefined) {
        _context.setAnalyzing(new AnalyzedPageEl());
        String objectClassName_ = _context.getStandards().getAliasObject();
        CustList<RootBlock> clBodies_ = classesBodies.values();
        if (clBodies_.isEmpty()) {
            return;
        }
        validateInheritingClassesId(_context, _predefined);
        Classes classes_ = _context.getClasses();
        StringList sorted_ =  _context.getSortedTypes(_predefined);
        if (sorted_ == null) {
            return;
        }
        for (String s: sorted_) {
            RootBlock c_ = getClassBody(s);
            _context.getAnalyzing().setCurrentBlock(c_);
            c_.buildDirectGenericSuperTypes(_context);
        }
        for (RootBlock c: classes_.getClassBodies()) {
            if (c.getFile().isPredefined() != _predefined) {
                continue;
            }
            RootBlock bl_ = c;
            _context.getAnalyzing().setCurrentBlock(bl_);
            bl_.buildMapParamType(_context);
        }
        for (RootBlock c: classes_.getClassBodies()) {
            if (c.getFile().isPredefined() != _predefined) {
                continue;
            }
            StringList allPossibleDirectSuperTypes_ = new StringList();
            StringList allDirectSuperTypes_ = new StringList();
            StringList allAncestors_ = new StringList();
            RootBlock p_ = c.getParentType();
            while (p_ != null) {
                allAncestors_.add(p_.getFullName());
                p_ = p_.getParentType();
            }
            for (String s: c.getImportedDirectSuperTypes()) {
                GeneType s_ = _context.getClassBody(s);
                if (!(s_ instanceof RootBlock)) {
                    continue;
                }
                if (((RootBlock)s_).isStaticType()) {
                    continue;
                }
                allDirectSuperTypes_.add(s_.getFullName());
            }
            for (String a: allAncestors_) {
                RootBlock a_ = classes_.getClassBody(a);
                for (Block m: Classes.getDirectChildren(a_)) {
                    if (!(m instanceof RootBlock)) {
                        continue;
                    }
                    RootBlock m_ = (RootBlock) m;
                    allPossibleDirectSuperTypes_.add(m_.getFullName());
                }
                for (String s: a_.getAllSuperTypes()) {
                    GeneType g_ = _context.getClassBody(s);
                    if (!(g_ instanceof RootBlock)) {
                        continue;
                    }
                    RootBlock s_ = (RootBlock) g_;
                    for (Block m: Classes.getDirectChildren(s_)) {
                        if (!(m instanceof RootBlock)) {
                            continue;
                        }
                        RootBlock m_ = (RootBlock) m;
                        allPossibleDirectSuperTypes_.add(m_.getFullName());
                    }
                }
            }
            if (!allPossibleDirectSuperTypes_.containsAllObj(allDirectSuperTypes_)) {
                for (String s: allDirectSuperTypes_) {
                    if (!allPossibleDirectSuperTypes_.containsObj(s)) {
                        BadInheritedClass enum_;
                        enum_ = new BadInheritedClass();
                        enum_.setClassName(s);
                        enum_.setFileName(c.getFullName());
                        enum_.setRc(new RowCol());
                        classes_.addError(enum_);
                    }
                }
            }
        }
        if (!isEmptyErrors()) {
            return;
        }
        checkTemplatesDef(_context, _predefined, objectClassName_);
    }
    public void validateInheritingClassesId(ContextEl _context, boolean _predefined) {
        _context.setAnalyzing(new AnalyzedPageEl());
        String objectClassName_ = _context.getStandards().getAliasObject();
        String enumClassName_ = _context.getStandards().getAliasEnum();
        String enumParamClassName_ = _context.getStandards().getAliasEnumParam();
        CustList<RootBlock> clBodies_ = classesBodies.values();
        if (clBodies_.isEmpty()) {
            return;
        }
        CustList<RootBlock> staticClBodies_ = new CustList<RootBlock>();
        CustList<RootBlock> instClBodies_ = new CustList<RootBlock>();
        for (RootBlock r: clBodies_) {
            if (r.getFile().isPredefined() != _predefined) {
                continue;
            }
            if (r.isStaticType()) {
                staticClBodies_.add(r);
            } else {
                instClBodies_.add(r);
            }
        }
        StringMap<Boolean> builtTypes_ = new StringMap<Boolean>();
        StringList stClNames_ = new StringList();
        if (!_predefined) {
            for (RootBlock r: clBodies_) {
                String k_ = r.getFullName();
                if (!r.getFile().isPredefined()) {
                    continue;
                }
                stClNames_.add(k_);
                builtTypes_.put(k_, true);
            }
        }
        for (RootBlock r: staticClBodies_) {
            String k_ = r.getFullName();
            stClNames_.add(k_);
            if (_predefined) {
                builtTypes_.put(k_, false);
            } else {
                builtTypes_.put(k_, r.getFile().isPredefined());
            }
        }
        while (true) {
            StringList next_ = new StringList();
            for (String c: stClNames_) {
                boolean ready_ = true;
                RootBlock r_ = getClassBody(c);
                if (r_.getFile().isPredefined() != _predefined) {
                    continue;
                }
                int index_ = 0;
                StringList foundNames_ = new StringList();
                for (EntryCust<Integer, String> e: r_.getRowColDirectSuperTypes().entryList()) {
                    String s = e.getValue();
                    String idSuper_ = Templates.getIdFromAllTypes(s);
                    int offset_ = r_.getRowColDirectSuperTypes().getKey(index_);
                    RowCol rc_ = r_.getRowCol(0, offset_);
                    if (StringList.quickEq(idSuper_, objectClassName_)) {
                        UnknownClassName undef_;
                        undef_ = new UnknownClassName();
                        undef_.setClassName(idSuper_);
                        undef_.setFileName(r_.getFile().getFileName());
                        undef_.setRc(rc_);
                        addError(undef_);
                        index_++;
                        continue;
                    }
                    StringList inners_ = Templates.getAllInnerTypes(idSuper_);
                    String base_ = inners_.first();
                    if (base_.isEmpty()) {
                        if (inners_.size() == 1) {
                            //ERROR
                            UnknownClassName undef_;
                            undef_ = new UnknownClassName();
                            undef_.setClassName(base_);
                            undef_.setFileName(r_.getFile().getFileName());
                            undef_.setRc(rc_);
                            addError(undef_);
                            index_++;
                            continue;
                        }
                        String baseInn_ = inners_.get(1).trim();
                        CustList<RootBlock> allAncestors_ = new CustList<RootBlock> ();
                        RootBlock p_ = r_.getParentType();
                        while (p_ != null) {
                            allAncestors_.add(p_);
                            p_ = p_.getParentType();
                        }
                        String name_ = EMPTY_STRING;
                        for (RootBlock a: allAncestors_) {
                            String id_ = a.getFullName();
                            if (!builtTypes_.contains(id_)) {
                                continue;
                            }
                            if (!builtTypes_.getVal(id_)) {
                                break;
                            }
                            StringList builtInners_ = TypeUtil.getBuiltInners(c,id_, baseInn_,true, _context);
                            if (builtInners_.size() == 1) {
                                name_ = builtInners_.first();
                                break;
                            }
                        }
                        if (name_.isEmpty()) {
                            ready_ = false;
                            break;
                        }
                        foundNames_.add(name_);
                        index_++;
                        continue;
                    }
                    String res_ = _context.resolveBaseType(base_, r_, rc_, inners_.size() == 1);
                    if (res_.isEmpty()) {
                        //ERROR
                        UnknownClassName undef_;
                        undef_ = new UnknownClassName();
                        undef_.setClassName(base_);
                        undef_.setFileName(r_.getFile().getFileName());
                        undef_.setRc(rc_);
                        addError(undef_);
                        index_++;
                        continue;
                    }
                    boolean err_ = false;
                    for (String i: inners_.mid(1)) {
                        if (!builtTypes_.getVal(res_)) {
                            ready_ = false;
                            break;
                        }
                        StringList builtInners_ = TypeUtil.getBuiltInners(c,res_, i.trim(), true, _context);
                        if (builtInners_.size() != 1) {
                            err_ = true;
                            //ERROR
                            UnknownClassName undef_;
                            undef_ = new UnknownClassName();
                            undef_.setClassName(base_);
                            undef_.setFileName(r_.getFile().getFileName());
                            undef_.setRc(rc_);
                            addError(undef_);
                            break;
                        }
                        res_ = builtInners_.first();
                    }
                    if (err_) {
                        index_++;
                        continue;
                    }
                    if (!builtTypes_.getVal(res_)) {
                        ready_ = false;
                        break;
                    }
                    foundNames_.add(res_);
                    index_++;
                }
                if (!ready_) {
                    continue;
                }
                StringList dup_ = new StringList(foundNames_);
                int oldSize_ = dup_.size();
                dup_.removeDuplicates();
                int newSize_ = dup_.size();
                if (oldSize_ != newSize_) {
                    UnknownClassName undef_;
                    undef_ = new UnknownClassName();
                    undef_.setClassName(r_.getFullName());
                    undef_.setFileName(r_.getFile().getFileName());
                    undef_.setRc(r_.getRowCol(0, 0));
                    addError(undef_);
                    continue;
                }
                int indexType_ = -1;
                int nbDirectSuperClass_ = 0;
                for (String f: foundNames_) {
                    indexType_++;
                    RootBlock s_ = getClassBody(f);
                    int offset_ = r_.getRowColDirectSuperTypes().getKey(indexType_);
                    RowCol rc_ = r_.getRowCol(0, offset_);
                    if (s_ == null) {
                        BadInheritedClass enum_;
                        enum_ = new BadInheritedClass();
                        enum_.setClassName(f);
                        enum_.setFileName(r_.getFile().getFileName());
                        enum_.setRc(rc_);
                        addError(enum_);
                        continue;
                    }
                    if (s_ instanceof UniqueRootedBlock) {
                        nbDirectSuperClass_++;
                    }
                    if (r_ instanceof InterfaceBlock) {
                        if (!(s_ instanceof InterfaceBlock)) {
                            BadInheritedClass enum_;
                            enum_ = new BadInheritedClass();
                            enum_.setClassName(f);
                            enum_.setFileName(r_.getFile().getFileName());
                            enum_.setRc(rc_);
                            addError(enum_);
                            continue;
                        }
                    } else if (s_.isFinalType()) {
                        BadInheritedClass enum_;
                        enum_ = new BadInheritedClass();
                        enum_.setClassName(f);
                        enum_.setFileName(r_.getFile().getFileName());
                        enum_.setRc(rc_);
                        addError(enum_);
                        continue;
                    }
                    if (StringList.quickEq(f, enumParamClassName_)) {
                        Boolean exp_ = r_.getExplicitDirectSuperTypes().getVal(offset_);
                        if (exp_) {
                            UnknownClassName undef_;
                            undef_ = new UnknownClassName();
                            undef_.setClassName(f);
                            undef_.setFileName(r_.getFile().getFileName());
                            undef_.setRc(rc_);
                            addError(undef_);
                        } else {
                            r_.getImportedDirectBaseSuperTypes().add(f);
                        }
                        continue;
                    }
                    if (StringList.quickEq(f, enumClassName_) && !StringList.quickEq(c, enumParamClassName_)) {
                        Boolean exp_ = r_.getExplicitDirectSuperTypes().getVal(offset_);
                        if (exp_) {
                            UnknownClassName undef_;
                            undef_ = new UnknownClassName();
                            undef_.setClassName(f);
                            undef_.setFileName(r_.getFile().getFileName());
                            undef_.setRc(rc_);
                            addError(undef_);
                        } else {
                            r_.getImportedDirectBaseSuperTypes().add(f);
                        }
                        continue;
                    }
                    r_.getImportedDirectBaseSuperTypes().add(f);
                }
                if (nbDirectSuperClass_ > 1) {
                    BadInheritedClass enum_;
                    enum_ = new BadInheritedClass();
                    enum_.setClassName(EMPTY_STRING);
                    enum_.setFileName(r_.getFile().getFileName());
                    enum_.setRc(r_.getRowCol(0, r_.getIdRowCol()));
                    addError(enum_);
                }
                r_.getAllSuperTypes().addAllElts(foundNames_);
                String dirSuper_ = objectClassName_;
                for (String f: foundNames_) {
                    RootBlock s_ = getClassBody(f);
                    if (s_ == null) {
                        continue;
                    }
                    if (s_ instanceof UniqueRootedBlock) {
                        dirSuper_ = s_.getFullName();
                    }
                    r_.getAllSuperTypes().addAllElts(s_.getAllSuperTypes());
                }
                r_.getAllSuperTypes().add(objectClassName_);
                if (nbDirectSuperClass_ <= 1) {
                    String s_ = dirSuper_;
                    while (!StringList.quickEq(s_, objectClassName_)) {
                        r_.getAllSuperClasses().add(s_);
                        RootBlock sup_ = getClassBody(s_);
                        String dirSup_ = objectClassName_;
                        for (String d: sup_.getImportedDirectBaseSuperTypes()) {
                            RootBlock sTwo_ = getClassBody(d);
                            if (sTwo_ instanceof UniqueRootedBlock) {
                                dirSup_ = sTwo_.getFullName();
                            }
                        }
                        s_ = dirSup_;
                    }
                    r_.getAllSuperClasses().add(s_);
                }
                for (String t: r_.getAllSuperTypes()) {
                    GeneType g_ = _context.getClassBody(t);
                    if (g_ instanceof GeneInterface) {
                        r_.getAllInterfaces().add(t);
                    }
                }
                r_.getAllInterfaces().removeDuplicates();
                r_.getAllSuperTypes().removeDuplicates();
                builtTypes_.set(c, true);
                next_.add(c);
            }
            if (next_.isEmpty()) {
                for (String c: stClNames_) {
                    RootBlock r_ = getClassBody(c);
                    if (r_.getFile().isPredefined() != _predefined) {
                        continue;
                    }
                    UnknownClassName undef_;
                    undef_ = new UnknownClassName();
                    undef_.setClassName(c);
                    undef_.setFileName(r_.getFile().getFileName());
                    undef_.setRc(r_.getRowCol(0, 0));
                    addError(undef_);
                }
                break;
            }
            stClNames_.removeAllElements(next_);
        }
        if (!isEmptyErrors()) {
            return;
        }
        if (instClBodies_.isEmpty()) {
            return;
        }
        instClBodies_.sortElts(new ClassDeepCmp());
        int rk_ = instClBodies_.first().getSelfAndParentTypes().size();
        int rkMax_ = instClBodies_.last().getSelfAndParentTypes().size();
        StringList possibleDirectBase_ = new StringList();
        StringList possibleQualifiers_ = new StringList();
        for (RootBlock s: staticClBodies_) {
            possibleQualifiers_.add(s.getFullName());
            possibleDirectBase_.add(s.getFullName());
        }
        StringMap<StringList> dirSuperTypes_ = new StringMap<StringList>();
        Graph<ClassEdge> inherit_;
        for (int i = rk_; i <= rkMax_; i++) {
            inherit_ = new Graph<ClassEdge>();
            CustList<RootBlock> curList_ = new CustList<RootBlock>();
            for (RootBlock c: instClBodies_) {
                int rkLoc_ = c.getSelfAndParentTypes().size();
                if (rkLoc_ != i) {
                    continue;
                }
                possibleDirectBase_.add(c.getFullName());
            }
            for (RootBlock c: instClBodies_) {
                int rkLoc_ = c.getSelfAndParentTypes().size();
                if (rkLoc_ != i) {
                    continue;
                }
                curList_.add(c);
                if (_predefined) {
                    continue;
                }
                String d_ = c.getFullName();
                RootBlock bl_ = c;
                _context.getAnalyzing().setCurrentBlock(bl_);
                boolean int_ = bl_ instanceof InterfaceBlock;
                int nbDirectSuperClass_ = 0;
                int index_ = -1;
                StringList names_ = new StringList();
                for (EntryCust<Integer, String> t: bl_.getRowColDirectSuperTypes().entryList()) {
                    index_++;
                    String v_ = t.getValue();
                    v_ = ContextEl.removeDottedSpaces(v_);
                    String base_ = Templates.getIdFromAllTypes(v_);
                    int offset_ = bl_.getRowColDirectSuperTypes().getKey(index_);
                    RowCol rc_ = bl_.getRowCol(0, offset_);
                    if (StringList.quickEq(base_, enumParamClassName_)) {
                        Boolean exp_ = bl_.getExplicitDirectSuperTypes().getVal(offset_);
                        if (exp_) {
                            UnknownClassName undef_;
                            undef_ = new UnknownClassName();
                            undef_.setClassName(base_);
                            undef_.setFileName(bl_.getFile().getFileName());
                            undef_.setRc(rc_);
                            addError(undef_);
                        } else {
                            names_.add(base_);
                        }
                        continue;
                    }
                    if (StringList.quickEq(base_, enumClassName_) && !StringList.quickEq(d_, enumParamClassName_)) {
                        Boolean exp_ = bl_.getExplicitDirectSuperTypes().getVal(offset_);
                        if (exp_) {
                            UnknownClassName undef_;
                            undef_ = new UnknownClassName();
                            undef_.setClassName(base_);
                            undef_.setFileName(bl_.getFile().getFileName());
                            undef_.setRc(rc_);
                            addError(undef_);
                        } else {
                            names_.add(base_);
                        }
                        continue;
                    }
                    if (StringList.quickEq(base_, objectClassName_)) {
                        UnknownClassName undef_;
                        undef_ = new UnknownClassName();
                        undef_.setClassName(base_);
                        undef_.setFileName(bl_.getFile().getFileName());
                        undef_.setRc(rc_);
                        addError(undef_);
                        index_++;
                        continue;
                    }
                    String type_ = base_;
                    base_ = _context.resolveBaseTypeInherits(base_, bl_, rc_, possibleQualifiers_);
                    if (base_.isEmpty()) {
                        UnknownClassName undef_;
                        undef_ = new UnknownClassName();
                        undef_.setClassName(type_);
                        undef_.setFileName(bl_.getFile().getFileName());
                        undef_.setRc(rc_);
                        addError(undef_);
                    } else {
                        RootBlock super_ = classesBodies.getVal(base_);
                        if (super_ instanceof UniqueRootedBlock) {
                            nbDirectSuperClass_++;
                        }
                        if (int_) {
                            if (!(super_ instanceof InterfaceBlock)) {
                                BadInheritedClass enum_;
                                enum_ = new BadInheritedClass();
                                String n_ = base_;
                                enum_.setClassName(n_);
                                enum_.setFileName(bl_.getFile().getFileName());
                                enum_.setRc(rc_);
                                addError(enum_);
                            } else {
                                names_.add(base_);
                            }
                        } else if (super_.isFinalType()) {
                            BadInheritedClass enum_;
                            enum_ = new BadInheritedClass();
                            String n_ = base_;
                            enum_.setClassName(n_);
                            enum_.setFileName(bl_.getFile().getFileName());
                            enum_.setRc(rc_);
                            addError(enum_);
                        } else {
                            names_.add(base_);
                            RootBlock par_ = bl_.getParentType();
                            while (par_ != null) {
                                if (par_.isStaticType()) {
                                    break;
                                }
                                inherit_.addSegment(new ClassEdge(d_), new ClassEdge(par_.getFullName()));
                                par_ = par_.getParentType();
                            }
                        }
                        inherit_.addSegment(new ClassEdge(d_), new ClassEdge(base_));
                    }
                }
                StringList dup_ = new StringList(names_);
                int oldSize_ = dup_.size();
                dup_.removeDuplicates();
                int newSize_ = dup_.size();
                if (oldSize_ != newSize_) {
                    UnknownClassName undef_;
                    undef_ = new UnknownClassName();
                    undef_.setClassName(bl_.getFullName());
                    undef_.setFileName(bl_.getFile().getFileName());
                    undef_.setRc(bl_.getRowCol(0, 0));
                    addError(undef_);
                    continue;
                }
                dirSuperTypes_.put(d_, names_);
                if (nbDirectSuperClass_ > 1) {
                    BadInheritedClass enum_;
                    enum_ = new BadInheritedClass();
                    enum_.setClassName(EMPTY_STRING);
                    enum_.setFileName(bl_.getFile().getFileName());
                    enum_.setRc(bl_.getRowCol(0, bl_.getIdRowCol()));
                    addError(enum_);
                }
            }
            if (!isEmptyErrors()) {
                return;
            }
            EqList<ClassEdge> cycle_ = inherit_.elementsCycle();
            if (!cycle_.isEmpty()) {
                for (ClassEdge c: cycle_) {
                    BadInheritedClass b_;
                    b_ = new BadInheritedClass();
                    String n_ = c.getId();
                    RootBlock type_ = classesBodies.getVal(n_);
                    b_.setClassName(n_);
                    b_.setFileName(n_);
                    b_.setRc(type_.getRowCol(0, type_.getIdRowCol()));
                    addError(b_);
                }
                return;
            }
            for (EntryCust<String, StringList> e: dirSuperTypes_.entryList()) {
                RootBlock r_ = _context.getClasses().getClassBody(e.getKey());
                StringList foundNames_ = e.getValue();
                r_.getImportedDirectBaseSuperTypes().addAllElts(foundNames_);
                r_.getAllSuperTypes().addAllElts(foundNames_);
                for (String f: foundNames_) {
                    RootBlock s_ = getClassBody(f);
                    if (s_ == null) {
                        continue;
                    }
                    r_.getAllSuperTypes().addAllElts(s_.getAllSuperTypes());
                }
                r_.getAllSuperTypes().add(objectClassName_);
                String dirSuper_ = objectClassName_;
                int nbDirectSuperClass_ = 0;
                for (String f: foundNames_) {
                    RootBlock s_ = getClassBody(f);
                    if (s_ == null) {
                        continue;
                    }
                    if (s_ instanceof UniqueRootedBlock) {
                        nbDirectSuperClass_++;
                        dirSuper_ = s_.getFullName();
                    }
                    r_.getAllSuperTypes().addAllElts(s_.getAllSuperTypes());
                }
                if (nbDirectSuperClass_ <= 1) {
                    String s_ = dirSuper_;
                    while (!StringList.quickEq(s_, objectClassName_)) {
                        r_.getAllSuperClasses().add(s_);
                        RootBlock sup_ = getClassBody(s_);
                        String dirSup_ = objectClassName_;
                        for (String d: sup_.getImportedDirectBaseSuperTypes()) {
                            RootBlock sTwo_ = getClassBody(d);
                            if (sTwo_ instanceof UniqueRootedBlock) {
                                dirSup_ = sTwo_.getFullName();
                            }
                        }
                        s_ = dirSup_;
                    }
                    r_.getAllSuperClasses().add(s_);
                }
                for (String t: r_.getAllSuperTypes()) {
                    GeneType g_ = _context.getClassBody(t);
                    if (g_ instanceof GeneInterface) {
                        r_.getAllInterfaces().add(t);
                    }
                }
                r_.getAllInterfaces().removeDuplicates();
                r_.getAllSuperTypes().removeDuplicates();
            }
            for (RootBlock c: instClBodies_) {
                int rkLoc_ = c.getSelfAndParentTypes().size();
                if (rkLoc_ != i) {
                    continue;
                }
                possibleQualifiers_.add(c.getFullName());
            }
        }
    }

    private void checkTemplatesDef(ContextEl _context, boolean _predefined,
            String _objectClassName) {
        LgNames stds_ = _context.getStandards();
        for (EntryCust<String, RootBlock> s: classesBodies.entryList()) {
            String c = s.getKey();
            RootBlock dBl_ = s.getValue();
            if (dBl_.getFile().isPredefined() != _predefined) {
                continue;
            }
            Mapping mapping_ = new Mapping();
            StringMap<StringList> cts_ = new StringMap<StringList>();
            StringList variables_ = new StringList();
            boolean ok_ = true;
            for (TypeVar t: dBl_.getParamTypesMapValues()) {
                cts_.put(t.getName(), t.getConstraints());
                variables_.add(t.getName());
            }
            if (!variables_.isEmpty() && dBl_ instanceof AnnotationBlock) {
                BadInheritedClass b_;
                b_ = new BadInheritedClass();
                b_.setClassName(c);
                b_.setFileName(c);
                b_.setRc(dBl_.getRowCol(0, dBl_.getIdRowCol()));
                addError(b_);
                continue;
            }
            mapping_.setMapping(cts_);
            if (!ok_) {
                continue;
            }
            if (mapping_.isCyclic(_objectClassName)) {
                BadInheritedClass b_;
                b_ = new BadInheritedClass();
                //TODO better message
                b_.setClassName(c);
                b_.setFileName(c);
                b_.setRc(dBl_.getRowCol(0, dBl_.getIdRowCol()));
                addError(b_);
                continue;
            }
            for (TypeVar t: dBl_.getParamTypesMapValues()) {
                boolean existNative_ = false;
                boolean existCustom_ = false;
                StringList upper_ = Mapping.getAllUpperBounds(cts_, t.getName(),_objectClassName);
                StringList upperNotObj_ = new StringList();
                for (String b: upper_) {
                    if (b.startsWith("[")) {
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(c);
                        un_.setFileName(c);
                        un_.setRc(dBl_.getRowCol(0, dBl_.getIdRowCol()));
                        addError(un_);
                    }
                    String baseParams_ = Templates.getIdFromAllTypes(b);
                    String base_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseParams_).getComponent();
                    upperNotObj_.add(b);
                    if (classesBodies.contains(base_)) {
                        existCustom_ = true;
                    } else {
                        existNative_ = true;
                    }
                }
                boolean okLoc_ = true;
                if (existNative_ && existCustom_) {
                    UnknownClassName un_ = new UnknownClassName();
                    //TODO all conflicting classes
                    un_.setClassName(c);
                    un_.setFileName(c);
                    un_.setRc(dBl_.getRowCol(0, dBl_.getIdRowCol()));
                    addError(un_);
                    okLoc_ = false;
                    ok_ = false;
                }
                StringMap<StringList> baseParams_ = getBaseParams(upper_);
                for (EntryCust<String, StringList> e: baseParams_.entryList()) {
                    if (e.getValue().size() > 1) {
                        DuplicateGenericSuperTypes duplicate_;
                        duplicate_ = new DuplicateGenericSuperTypes();
                        duplicate_.setFileName(c);
                        duplicate_.setRc(dBl_.getRowCol(0, dBl_.getIdRowCol()));
                        duplicate_.setGenericSuperTypes(e.getValue());
                        addError(duplicate_);
                    }
                }
                if (okLoc_) {
                    int nbAbs_ = 0;
                    int nbFinal_ = 0;
                    if (existNative_) {
                        for (String b: upperNotObj_) {
                            String baseParamsUpp_ = Templates.getIdFromAllTypes(b);
                            String base_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseParamsUpp_).getComponent();
                            StandardType type_ = stds_.getStandards().getVal(base_);
                            if (!(type_ instanceof StandardClass)) {
                                continue;
                            }
                            StandardClass class_ = (StandardClass) type_;
                            if (class_.isFinalType()) {
                                nbFinal_++;
                            }
                            if (class_.isAbstractType()) {
                                nbAbs_++;
                            }
                        }
                    } else {
                        for (String b: upperNotObj_) {
                            String baseParamsUpp_ = Templates.getIdFromAllTypes(b);
                            String base_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseParamsUpp_).getComponent();
                            RootBlock r_ = getClassBody(base_);
                            if (!(r_ instanceof UniqueRootedBlock)) {
                                continue;
                            }
                            if (r_ instanceof EnumBlock) {
                                nbFinal_++;
                                continue;
                            }
                            if (r_.isAbstractType()) {
                                nbAbs_++;
                            }
                            if (r_.isFinalType()) {
                                nbFinal_++;
                            }
                        }
                    }
                    if (nbAbs_ > 1 || nbFinal_ > 0) {
                        //error
                        BadInheritedClass inh_;
                        inh_ = new BadInheritedClass();
                        inh_.setFileName(c);
                        inh_.setRc(dBl_.getRowCol(0, dBl_.getIdRowCol()));
                        inh_.setClassName(c);
                        addError(inh_);
                        ok_ = false;
                    }
                }
            }
            if (!ok_) {
                continue;
            }
            StringMap<StringList> map_;
            map_ = new StringMap<StringList>();
            for (TypeVar t: dBl_.getParamTypesMapValues()) {
                map_.put(t.getName(), t.getConstraints());
            }
            for (TypeVar t: dBl_.getParamTypesMapValues()) {
                for (String b: t.getConstraints()) {
                    if (!Templates.isCorrectTemplateAll(b, map_, _context, true)) {
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(b);
                        un_.setFileName(c);
                        un_.setRc(dBl_.getRowCol(0, dBl_.getIdRowCol()));
                        addError(un_);
                    }
                }
            }
            for (String t: dBl_.getDirectGenericSuperTypes(_context)) {
                if (!Templates.isCorrectTemplateAll(t, map_, _context, true)) {
                    UnknownClassName un_ = new UnknownClassName();
                    un_.setClassName(t);
                    un_.setFileName(c);
                    un_.setRc(dBl_.getRowCol(0, dBl_.getIdRowCol()));
                    addError(un_);
                }
            }
        }
    }
    public CustList<OperatorBlock> getOperators() {
        return operators;
    }
    public void validateSingleParameterizedClasses(ContextEl _context) {
        for (EntryCust<String, RootBlock> i: classesBodies.entryList()) {
            RootBlock r_ = i.getValue();
            if (r_ instanceof AnnotationBlock) {
                continue;
            }
            StringList genericSuperTypes_ = r_.getAllGenericSuperTypes(_context);
            StringMap<StringList> baseParams_ = getBaseParams(genericSuperTypes_);
            for (EntryCust<String, StringList> e: baseParams_.entryList()) {
                if (e.getValue().size() > 1) {
                    DuplicateGenericSuperTypes duplicate_;
                    duplicate_ = new DuplicateGenericSuperTypes();
                    duplicate_.setFileName(i.getKey());
                    duplicate_.setRc(r_.getRowCol(0, r_.getIdRowCol()));
                    duplicate_.setGenericSuperTypes(e.getValue());
                    addError(duplicate_);
                }
            }
        }
    }
    public static StringMap<StringList> getBaseParams(StringList _genericSuperTypes) {
        StringMap<StringList> baseParams_ = new StringMap<StringList>();
        for (String t: _genericSuperTypes) {
            String key_ = Templates.getIdFromAllTypes(t);
            if (baseParams_.contains(key_)) {
                baseParams_.getVal(key_).add(t);
                baseParams_.getVal(key_).removeDuplicates();
            } else {
                baseParams_.put(key_, new StringList(t));
            }
        }
        return baseParams_;
    }

    public void validateIds(ContextEl _context) {
        _context.setAnalyzing(new AnalyzedPageEl());
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            RootBlock bl_ = c.getValue();
            _context.setGlobalClass(bl_.getGenericString());
            bl_.validateIds(_context);
        }
        _context.setGlobalClass("");
        EqList<MethodId> idMethods_ = new EqList<MethodId>();
        for (OperatorBlock o: operators) {
            String name_ = o.getName();
            o.buildImportedTypes(_context);
            if (!isOper(name_)) {
                RowCol r_ = o.getRowCol(0, o.getNameOffset());
                BadMethodName badMeth_ = new BadMethodName();
                badMeth_.setFileName(_context.getCurrentFileName());
                badMeth_.setRc(r_);
                badMeth_.setName(name_);
                _context.getClasses().addError(badMeth_);
            }
            MethodId id_ = o.getId();
            for (MethodId m: idMethods_) {
                if (m.eq(id_)) {
                    RowCol r_ = o.getRowCol(0, o.getOffset().getOffsetTrim());
                    DuplicateMethod duplicate_;
                    duplicate_ = new DuplicateMethod();
                    duplicate_.setRc(r_);
                    duplicate_.setFileName(_context.getCurrentFileName());
                    duplicate_.setId(id_);
                    _context.getClasses().addError(duplicate_);
                }
            }
            idMethods_.add(id_);
            StringList l_ = o.getParametersNames();
            StringList seen_ = new StringList();
            for (String v: l_) {
                if (!StringList.isWord(v)) {
                    BadParamName b_;
                    b_ = new BadParamName();
                    b_.setFileName(_context.getCurrentFileName());
                    b_.setRc(o.getRowCol(0, o.getOffset().getOffsetTrim()));
                    b_.setParamName(v);
                    _context.getClasses().addError(b_);
                } else if (seen_.containsStr(v)){
                    DuplicateParamName b_;
                    b_ = new DuplicateParamName();
                    b_.setFileName(_context.getCurrentFileName());
                    b_.setRc(o.getRowCol(0, o.getOffset().getOffsetTrim()));
                    b_.setParamName(v);
                    _context.getClasses().addError(b_);
                } else {
                    seen_.add(v);
                }
            }
        }
    }
    private static boolean isOper(String _op) {
        if(StringList.quickEq(_op, "+")) {
            return true;
        }
        if(StringList.quickEq(_op, "-")) {
            return true;
        }
        if(StringList.quickEq(_op, "*")) {
            return true;
        }
        if(StringList.quickEq(_op, "/")) {
            return true;
        }
        if(StringList.quickEq(_op, "%")) {
            return true;
        }
        if(StringList.quickEq(_op, "==")) {
            return true;
        }
        if(StringList.quickEq(_op, "!=")) {
            return true;
        }
        if(StringList.quickEq(_op, "<=")) {
            return true;
        }
        if(StringList.quickEq(_op, ">")) {
            return true;
        }
        if(StringList.quickEq(_op, ">=")) {
            return true;
        }
        if(StringList.quickEq(_op, "<")) {
            return true;
        }
        return false;
    }
    public void validateOverridingInherit(ContextEl _context) {
        _context.setAnalyzing(new AnalyzedPageEl());
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            RootBlock bl_ = c.getValue();
            if (bl_.getFile().isPredefined()) {
                continue;
            }
            bl_.setupBasicOverrides(_context);
        }
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            RootBlock bl_ = c.getValue();
            if (bl_.getFile().isPredefined()) {
                continue;
            }
            if (bl_ instanceof AnnotationBlock) {
                continue;
            }
            bl_.checkCompatibility(_context);
            bl_.checkImplements(_context);
        }
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            RootBlock bl_ = c.getValue();
            if (bl_.getFile().isPredefined()) {
                continue;
            }
            if (bl_ instanceof AnnotationBlock) {
                continue;
            }
            bl_.checkCompatibilityBounds(_context);
        }
    }

    public static boolean canAccessField(String _className, String _accessedClass, String _name, Analyzable _context) {
        String baseClass_ = Templates.getIdFromAllTypes(_accessedClass);
        GeneType access_ = _context.getClassBody(baseClass_);
        if (access_ instanceof RootBlock) {
            CustList<Block> bl_ = getDirectChildren((Block) access_);
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    if (StringList.quickEq(((InfoBlock)b).getFieldName(), _name)) {
                        return canAccess(_className,(InfoBlock)b, _context);
                    }
                }
            }
            return false;
        }
        for (StandardField f: _context.getStandards().getStandards().getVal(baseClass_).getFields().values()) {
            if (StringList.quickEq(f.getFieldName(), _name)) {
                return canAccess(_className,f, _context);
            }
        }
        return false;
    }
    public static CustList<RootBlock> accessedClassMembers(boolean _protectedInc,String _className, String _glClass,RootBlock _clOwner, Analyzable _context) {
        String idRoot_ = Templates.getIdFromAllTypes(_className);
        GeneType root_ = _context.getClassBody(idRoot_);
        String pkgRoot_ = root_.getPackageName();
        String pkgOwner_ = _clOwner.getPackageName();
        String ownerName_ = _clOwner.getFullName();
        CustList<RootBlock> inners_ = new CustList<RootBlock>();
        String outOwner_ = _clOwner.getOuter().getFullName();
        String outRoot_ = root_.getOuter().getFullName();
        
        for (Block b: Classes.getDirectChildren(_clOwner)) {
            if (!(b instanceof RootBlock)) {
                continue;
            }
            RootBlock r_ = (RootBlock) b;
            if (r_.getAccess() == AccessEnum.PUBLIC) {
                inners_.add(r_);
                continue;
            }
            if (_glClass == null) {
                continue;
            }
            String idGl_ = Templates.getIdFromAllTypes(_glClass);
            RootBlock rGl_ = _context.getClasses().getClassBody(idGl_);
            String outGl_ = rGl_.getOuter().getFullName();
            String pkgGl_ = rGl_.getPackageName();
            if (r_.getAccess() == AccessEnum.PROTECTED) {
                boolean okRoot_ = false;
                if (_protectedInc) {
                    if (PrimitiveTypeUtil.canBeUseAsArgument(ownerName_, idRoot_, _context)) {
                        okRoot_ = true;
                    }
                }
                if (StringList.quickEq(pkgOwner_, pkgRoot_)){
                    okRoot_ = true;
                }
                boolean okGl_ = false;
                if (_protectedInc) {
                    if (PrimitiveTypeUtil.canBeUseAsArgument(ownerName_, idGl_, _context)) {
                        okGl_ = true;
                    }
                }
                if (StringList.quickEq(pkgOwner_, pkgGl_)){
                    okGl_ = true;
                }
                if (okGl_ && okRoot_) {
                    inners_.add(r_);
                    continue;
                }
            }
            if (r_.getAccess() == AccessEnum.PACKAGE) {
                if (StringList.quickEq(pkgOwner_, pkgRoot_)) {
                    if (StringList.quickEq(pkgOwner_, pkgGl_)) {
                        inners_.add(r_);
                        continue;
                    }
                }
            }
            if (r_.getAccess() == AccessEnum.PRIVATE) {
                if (StringList.quickEq(outRoot_, outOwner_)) {
                    if (StringList.quickEq(outGl_, outOwner_)) {
                        inners_.add(r_);
                    }
                }
            }
        }
        return inners_;
    }

    public static boolean canAccessClass(String _className, String _accessedClass, Analyzable _context) {
        String baseClass_ = Templates.getIdFromAllTypes(_accessedClass);
        GeneType access_ = _context.getClassBody(baseClass_);
        return canAccess(_className, access_, _context);
    }

    public static boolean canAccess(String _className, AccessibleBlock _block, Analyzable _context) {
        if (_block.getAccess() == AccessEnum.PUBLIC) {
            return true;
        }
        if (_className == null) {
            return false;
        }
        String baseClass_ = Templates.getIdFromAllTypes(_className);
        GeneType root_ = _context.getClassBody(baseClass_);
        GeneType belong_ = _block.belong();
        if (_block.getAccess() == AccessEnum.PROTECTED) {
            if (PrimitiveTypeUtil.canBeUseAsArgument(belong_.getFullName(), baseClass_, _context)) {
                return true;
            }
            if (StringList.quickEq(belong_.getPackageName(), root_.getPackageName())) {
                return true;
            }
            return false;
        }
        if (_block.getAccess() == AccessEnum.PACKAGE) {
            if (StringList.quickEq(belong_.getPackageName(), root_.getPackageName())) {
                return true;
            }
            return false;
        }
        GeneType outBelong_ = belong_.getOuter();
        GeneType outRoot_ = root_.getOuter();
        if (StringList.quickEq(outBelong_.getFullName(), outRoot_.getFullName())) {
            return true;
        }
        return false;
    }

    public String getIteratorVar(boolean _native) {
        if (_native) {
            return iteratorVar;
        }
        return iteratorVarCust;
    }

    public String getHasNextVar(boolean _native) {
        if (_native) {
            return hasNextVar;
        }
        return hasNextVarCust;
    }

    public String getNextVar(boolean _native) {
        if (_native) {
            return nextVar;
        }
        return nextVarCust;
    }

    public ExpressionLanguage getEqIterator(boolean _native) {
        if (_native) {
            return new ExpressionLanguage(expsIterator);
        }
        return new ExpressionLanguage(expsIteratorCust);
    }

    public ExpressionLanguage getEqHasNext(boolean _native) {
        if (_native) {
            return new ExpressionLanguage(expsHasNext);
        }
        return new ExpressionLanguage(expsHasNextCust);
    }

    public ExpressionLanguage getEqNext(boolean _native) {
        if (_native) {
            return new ExpressionLanguage(expsNext);
        }
        return new ExpressionLanguage(expsNextCust);
    }

    //validate el and its possible returned type
    public void validateEl(ContextEl _context) {
        EqList<ClassField> cstFields_ = initStaticFields(_context);
        AnalyzedPageEl page_ = new AnalyzedPageEl();
        _context.setAnalyzing(page_);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            StringMap<AssignmentBefore> ass_;
            ass_ = new StringMap<AssignmentBefore>();
            for (Block b: bl_) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock f_ = (InfoBlock) b;
                if (!f_.isStaticField()) {
                    continue;
                }
                AssignmentBefore as_ = new AssignmentBefore();
                String clDecl_ = c.getKey();
                String fieldName_ = f_.getFieldName();
                if (staticFields.getVal(clDecl_).getVal(fieldName_) != null) {
                    as_.setAssignedBefore(true);
                } else {
                    if (!c.getValue().isStaticType()) {
                        //ERROR
                        ClassField id_ = new ClassField(clDecl_, fieldName_);
                        UnassignedFinalField un_ = new UnassignedFinalField(id_);
                        un_.setFileName(c.getValue().getFile().getFileName());
                        un_.setRc(c.getValue().getRowCol(0,c.getValue().getOffset().getOffsetTrim()));
                        _context.getClasses().addError(un_);
                    }
                    as_.setUnassignedBefore(true);
                }
                ass_.put(fieldName_, as_);
            }
            AssignedVariablesBlock asBlock_ = page_.getAssignedVariables();
            StringMap<AssignmentBefore> b_ = asBlock_.getFinalVariablesGlobal().getFieldsRootBefore();
            b_.clear();
            asBlock_.getFinalVariablesGlobal().getFields().clear();
            asBlock_.getFinalVariablesGlobal().getFieldsRoot().clear();
            asBlock_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
            asBlock_.getFinalVariablesGlobal().getFieldsBefore().clear();
            b_.putAllMap(ass_);
            StringMap<SimpleAssignment> assAfter_;
            assAfter_ = new StringMap<SimpleAssignment>();
            for (Block b: bl_) {
                if (b instanceof FieldBlock) {
                    FieldBlock f_ = (FieldBlock) b;
                    if (cstFields_.containsObj(new ClassField(c.getKey(), f_.getFieldName()))) {
                        page_.setCurrentBlock(b);
                        b.setAssignmentBefore(_context, null);
                        b.setAssignmentAfter(_context, null);
                        assAfter_.putAllMap(asBlock_.getFinalVariables().getVal(f_).getFieldsRoot());
                        continue;
                    }
                }
                if (b instanceof InfoBlock) {
                    page_.setGlobalClass(c.getValue().getGenericString());
                    InfoBlock method_ = (InfoBlock) b;
                    if (!method_.isStaticField()) {
                        continue;
                    }
                    page_.setCurrentBlock(b);
                    b.setAssignmentBefore(_context, null);
                    method_.buildExpressionLanguage(_context);
                    b.setAssignmentAfter(_context, null);
                    assAfter_.putAllMap(asBlock_.getFinalVariables().getVal(b).getFieldsRoot());
                }
                if (b instanceof StaticBlock) {
                    page_.setGlobalClass(c.getValue().getGenericString());
                    StaticBlock method_ = (StaticBlock) b;
                    if (b.getFirstChild() == null) {
                        page_.setGlobalOffset(b.getOffset().getOffsetTrim());
                        page_.setOffset(0);
                        EmptyTagName un_ = new EmptyTagName();
                        un_.setFileName(b.getFile().getFileName());
                        un_.setRc(b.getRowCol(0, b.getOffset().getOffsetTrim()));
                        addError(un_);
                    }
                    method_.buildFctInstructions(_context);
                    assAfter_.putAllMap(asBlock_.getFinalVariables().getVal(b).getFieldsRoot());
                    page_.clearAllLocalVars();
                }
            }
            for (EntryCust<String, SimpleAssignment> a: assAfter_.entryList()) {
                String key_ = a.getKey();
                ClassField id_ = new ClassField(c.getKey(), key_);
                FieldInfo finfo_ = _context.getFieldInfo(id_);
                if (!finfo_.isFinalField()) {
                    continue;
                }
                if (!finfo_.isStaticField()) {
                    continue;
                }
                if (!a.getValue().isAssignedAfter()) {
                    //error
                    UnassignedFinalField un_ = new UnassignedFinalField(id_);
                    un_.setFileName(c.getValue().getFile().getFileName());
                    un_.setRc(c.getValue().getRowCol(0,c.getValue().getOffset().getOffsetTrim()));
                    _context.getClasses().addError(un_);
                }
            }
        }
        page_.setAssignedStaticFields(true);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            StringMap<AssignmentBefore> ass_;
            ass_ = new StringMap<AssignmentBefore>();
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    if (method_.isStaticField()) {
                        continue;
                    }
                }
                if (b instanceof FieldBlock) {
                    InfoBlock f_ = (InfoBlock) b;
                    AssignmentBefore as_ = new AssignmentBefore();
                    String fieldName_ = f_.getFieldName();
                    as_.setUnassignedBefore(true);
                    ass_.put(fieldName_, as_);
                }
            }
            AssignedVariablesBlock asBlock_ = page_.getAssignedVariables();
            StringMap<AssignmentBefore> b_ = asBlock_.getFinalVariablesGlobal().getFieldsRootBefore();
            asBlock_.getFinalVariablesGlobal().getFields().clear();
            asBlock_.getFinalVariablesGlobal().getFieldsRoot().clear();
            asBlock_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
            asBlock_.getFinalVariablesGlobal().getFieldsBefore().clear();
            b_.clear();
            b_.putAllMap(ass_);
            StringMap<SimpleAssignment> assAfter_;
            assAfter_ = new StringMap<SimpleAssignment>();
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    if (method_.isStaticField()) {
                        continue;
                    }
                }
                if (b instanceof FieldBlock) {
                    page_.setGlobalClass(c.getValue().getGenericString());
                    FieldBlock method_ = (FieldBlock) b;
                    page_.setCurrentBlock(b);
                    b.setAssignmentBefore(_context, null);
                    method_.buildExpressionLanguage(_context);
                    b.setAssignmentAfter(_context, null);
                    assAfter_.putAllMap(asBlock_.getFinalVariables().getVal(method_).getFieldsRoot());
                }
                if (b instanceof InstanceBlock) {
                    page_.setGlobalClass(c.getValue().getGenericString());
                    InstanceBlock method_ = (InstanceBlock) b;
                    if (b.getFirstChild() == null) {
                        page_.setGlobalOffset(b.getOffset().getOffsetTrim());
                        page_.setOffset(0);
                        EmptyTagName un_ = new EmptyTagName();
                        un_.setFileName(b.getFile().getFileName());
                        un_.setRc(b.getRowCol(0, b.getOffset().getOffsetTrim()));
                        addError(un_);
                    }
                    method_.buildFctInstructions(_context);
                    assAfter_.putAllMap(asBlock_.getFinalVariables().getVal(method_).getFieldsRoot());
                    page_.clearAllLocalVars();
                }
            }
            b_.clear();
            boolean hasCtor_ = false;
            for (Block b: bl_) {
                if (b instanceof ConstructorBlock) {
                    hasCtor_ = true;
                    break;
                }
            }
            if (!hasCtor_) {
                for (EntryCust<String, SimpleAssignment> a: assAfter_.entryList()) {
                    String fieldName_ = a.getKey();
                    ClassField key_ = new ClassField(c.getKey(), fieldName_);
                    FieldInfo finfo_ = _context.getFieldInfo(key_);
                    if (!finfo_.isFinalField()) {
                        continue;
                    }
                    if (!a.getValue().isAssignedAfter()) {
                        //error
                        for (Block b: bl_) {
                            if (b instanceof InfoBlock) {
                                if (StringList.quickEq(((InfoBlock)b).getFieldName(), fieldName_)) {
                                    UnassignedFinalField un_ = new UnassignedFinalField(key_);
                                    un_.setFileName(c.getValue().getFile().getFileName());
                                    un_.setRc(b.getRowCol(0, ((InfoBlock) b).getFieldNameOffset()));
                                    _context.getClasses().addError(un_);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            for (EntryCust<String, SimpleAssignment> a: assAfter_.entryList()) {
                b_.put(a.getKey(), a.getValue().assignBefore());
            }
            RootBlock block_ = c.getValue();
            StringList filteredCtor_ = new StringList();
            if (block_ instanceof UniqueRootedBlock) {
                Classes classes_ = _context.getClasses();
                UniqueRootedBlock un_ = (UniqueRootedBlock)block_;
                StringList all_ = block_.getAllInterfaces();
                StringList allCopy_ = new StringList(all_);
                allCopy_.removeAllElements(_context.getStandards().getPredefinedInterfacesInitOrder());
                String superClass_ = un_.getImportedDirectGenericSuperClass();
                String superClassId_ = Templates.getIdFromAllTypes(superClass_);
                RootBlock superType_ = classes_.getClassBody(superClassId_);
                if (superType_ != null) {
                    allCopy_.removeAllElements(superType_.getAllInterfaces());
                }
                for (String i: allCopy_) {
                    RootBlock int_ = classes_.getClassBody(i);
                    for (Block b: Classes.getDirectChildren(int_)) {
                        if (b instanceof NamedFunctionBlock) {
                            continue;
                        }
                        if (b instanceof GeneField) {
                            GeneField a_ = (GeneField) b;
                            if (!a_.isStaticField()) {
                                filteredCtor_.add(i);
                            }
                        }
                        if (b instanceof AloneBlock) {
                            AloneBlock a_ = (AloneBlock) b;
                            if (!a_.isStaticContext()) {
                                filteredCtor_.add(i);
                            }
                        }
                    }
                }
            }
            _context.getNeedInterfaces().clear();
            _context.getNeedInterfaces().addAllElts(filteredCtor_);
            if (!hasCtor_ && !filteredCtor_.isEmpty()) {
                BadInheritedClass undef_;
                undef_ = new BadInheritedClass();
                undef_.setClassName(block_.getFullName());
                undef_.setFileName(block_.getFile().getFileName());
                undef_.setRc(block_.getRowCol(0, 0));
                _context.getClasses().addError(undef_);
            }
            for (Block b: bl_) {
                if (b instanceof ConstructorBlock) {
                    page_.setGlobalClass(c.getValue().getGenericString());
                    ConstructorBlock method_ = (ConstructorBlock) b;
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    int len_ = params_.size();
                    if (!method_.isVarargs()) {
                        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                            String p_ = params_.get(i);
                            String c_ = types_.get(i);
                            LocalVariable lv_ = new LocalVariable();
                            lv_.setClassName(c_);
                            page_.getParameters().put(p_, lv_);
                        }
                    } else {
                        for (int i = CustList.FIRST_INDEX; i < len_ - 1; i++) {
                            String p_ = params_.get(i);
                            String c_ = types_.get(i);
                            LocalVariable lv_ = new LocalVariable();
                            lv_.setClassName(c_);
                            page_.getParameters().put(p_, lv_);
                        }
                        String p_ = params_.last();
                        String c_ = types_.last();
                        LocalVariable lv_ = new LocalVariable();
                        lv_.setClassName(StringList.concat(c_,VARARG));
                        page_.getParameters().put(p_, lv_);
                    }
                    method_.buildFctInstructions(_context);
                    page_.getParameters().clear();
                    page_.clearAllLocalVars();
                }
            }
        }
        page_.setAssignedFields(true);
        AssignedVariablesBlock asBlock_ = page_.getAssignedVariables();
        asBlock_.getFinalVariablesGlobal().getFields().clear();
        asBlock_.getFinalVariablesGlobal().getFieldsRoot().clear();
        asBlock_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
        asBlock_.getFinalVariablesGlobal().getFieldsBefore().clear();
        StringMap<AssignmentBefore> b_ = asBlock_.getFinalVariablesGlobal().getFieldsRootBefore();
        b_.clear();
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof MethodBlock) {
                    page_.setGlobalClass(c.getValue().getGenericString());
                    MethodBlock method_ = (MethodBlock) b;
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    int len_ = params_.size();
                    if (!method_.isVarargs()) {
                        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                            String p_ = params_.get(i);
                            String c_ = types_.get(i);
                            LocalVariable lv_ = new LocalVariable();
                            lv_.setClassName(c_);
                            page_.getParameters().put(p_, lv_);
                        }
                    } else {
                        for (int i = CustList.FIRST_INDEX; i < len_ - 1; i++) {
                            String p_ = params_.get(i);
                            String c_ = types_.get(i);
                            LocalVariable lv_ = new LocalVariable();
                            lv_.setClassName(c_);
                            page_.getParameters().put(p_, lv_);
                        }
                        String p_ = params_.last();
                        String c_ = types_.last();
                        LocalVariable lv_ = new LocalVariable();
                        lv_.setClassName(StringList.concat(c_,VARARG));
                        page_.getParameters().put(p_, lv_);
                    }
                    method_.buildFctInstructions(_context);
                    page_.getParameters().clear();
                    page_.clearAllLocalVars();
                }
            }
        }
        _context.setGlobalClass("");
        for (OperatorBlock o : operators) {
            StringList params_ = o.getParametersNames();
            StringList types_ = o.getImportedParametersTypes();
            int len_ = params_.size();
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                String p_ = params_.get(i);
                String c_ = types_.get(i);
                LocalVariable lv_ = new LocalVariable();
                lv_.setClassName(c_);
                page_.getParameters().put(p_, lv_);
            }
            o.buildFctInstructions(_context);
            page_.getParameters().clear();
            page_.clearAllLocalVars();
        }
        _context.setAnnotAnalysis(true);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            _context.setGlobalClass(c.getValue().getGenericString());
            for (Block b:getSortedDescNodes(c.getValue())) {
                _context.getAnalyzing().setCurrentBlock(b);
                if (b instanceof AnnotationMethodBlock) {
                    ((AnnotationMethodBlock)b).buildExpressionLanguage(_context);
                }
                b.buildAnnotations(_context);
            }
        }
        //init annotations here
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            RootBlock clblock_ = c.getValue();
            clblock_.validateConstructors(_context);
        }
    }
    public EqList<ClassField> initStaticFields(ContextEl _context) {
        AnalyzedPageEl page_ = new AnalyzedPageEl();
        _context.setAnalyzing(page_);
        page_.setGearConst(true);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            StringMap<Struct> cl_ = new StringMap<Struct>();
            for (Block b: bl_) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock i_ = (InfoBlock)b;
                if (!i_.isStaticField()) {
                    continue;
                }
                cl_.put(i_.getFieldName(), null);
            }
            staticFields.put(c.getKey(), cl_);
        }
        EqList<ClassField> success_ = new EqList<ClassField>();
        EqList<ClassField> cstFields_ = new EqList<ClassField>();
        page_.getAssignedVariables().getFinalVariablesGlobal().initVars();
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            StringMap<AssignmentBefore> ass_;
            ass_ = new StringMap<AssignmentBefore>();
            for (Block b: bl_) {
                if (!(b instanceof FieldBlock)) {
                    continue;
                }
                FieldBlock f_ = (FieldBlock) b;
                if (!f_.isStaticField()) {
                    continue;
                }
                if (!f_.isFinalField()) {
                    continue;
                }
                AssignmentBefore as_ = new AssignmentBefore();
                as_.setUnassignedBefore(true);
                ass_.put(f_.getFieldName(), as_);
            }
            AssignedVariablesBlock asBlock_ = page_.getAssignedVariables();
            StringMap<AssignmentBefore> b_ = asBlock_.getFinalVariablesGlobal().getFieldsRootBefore();
            b_.clear();
            b_.putAllMap(ass_);
            for (Block b: bl_) {
                if (!(b instanceof FieldBlock)) {
                    continue;
                }
                FieldBlock f_ = (FieldBlock) b;
                if (!f_.isStaticField()) {
                    continue;
                }
                if (!f_.isFinalField()) {
                    continue;
                }
                if (f_.getValue().trim().isEmpty()) {
                    f_.setAssignmentBefore(_context, null);
                    f_.setAssignmentAfter(_context, null);
                    continue;
                }
                page_.setGlobalClass(c.getValue().getGenericString());
                page_.setCurrentBlock(f_);
                f_.setAssignmentBefore(_context, null);
                f_.buildExpressionLanguage(_context);
                f_.setAssignmentAfter(_context, null);
                cstFields_.add(new ClassField(c.getKey(), f_.getFieldName()));
                success_.add(cstFields_.last());
            }
        }
        EqList<ClassField> filteredCstFields_ = new EqList<ClassField>();
        SortedGraph<SortedClassField> gr_ = new SortedGraph<SortedClassField>();
        EqList<SortedClassField> absDeps_ = new EqList<SortedClassField>();
        for (ClassField c: cstFields_) {
            RootBlock r_ = classesBodies.getVal(c.getClassName());
            CustList<Block> bl_ = getDirectChildren(r_);
            for (Block b: bl_) {
                if (!(b instanceof FieldBlock)) {
                    continue;
                }
                FieldBlock f_ = (FieldBlock) b;
                if (StringList.quickEq(f_.getFieldName(), c.getFieldName())) {
                    if (!f_.isSimpleStaticConstant()) {
                        continue;
                    }
                    filteredCstFields_.add(new ClassField(c.getClassName(), f_.getFieldName()));
                    EqList<ClassField> deps_ = f_.getStaticConstantDependencies(_context);
                    if (deps_.isEmpty()) {
                        absDeps_.add(new SortedClassField(c));
                    }
                    for (ClassField d: deps_) {
                        gr_.addSegment(new SortedClassField(c), new SortedClassField(d));
                    }
                }
            }
        }
        while (true) {
            EqList<SortedClassField> cycle_ = gr_.elementsCycle();
            if (cycle_.isEmpty()) {
                break;
            }
            EqList<SortedClassField> cpy_ = gr_.getElementsListCopy();
            Graph<SortedClassField> rev_ = gr_.getReverse();
            EqList<SortedClassField> r_ = new EqList<SortedClassField>();
            for (SortedClassField e: cycle_) {
                r_.addAllElts(rev_.getTreeFrom(e));
            }
            cpy_.removeAllElements(r_);
            gr_ = new SortedGraph<SortedClassField>();
            rev_ = rev_.getReverse();
            for (SortedClassField e: cpy_) {
                for (SortedClassField d: rev_.getChildren(e)) {
                    gr_.addSegment(e, d);
                }
            }
        }
        EqList<SortedClassField> sort_;
        sort_ = new EqList<SortedClassField>();
        sort_.addAllElts(absDeps_);
        for (SortedClassField e: gr_.process()) {
            if (!sort_.containsObj(e)) {
                sort_.add(e);
            }
        }
        for (SortedClassField e: sort_) {
            ClassField c_ = e.getClassField();
            RootBlock r_ = classesBodies.getVal(c_.getClassName());
            CustList<Block> bl_ = getDirectChildren(r_);
            for (Block b: bl_) {
                if (!(b instanceof FieldBlock)) {
                    continue;
                }
                FieldBlock f_ = (FieldBlock) b;
                if (StringList.quickEq(f_.getFieldName(), c_.getFieldName())) {
                    ElUtil.tryCalculate(f_, _context, sort_);
                }
            }
        }
        _context.setAnalyzing(null);
        return success_;
    }
    

    public CustList<RootBlock> getClassBodies() {
        return classesBodies.values();
    }

    public StringMap<RootBlock> getClassesBodies() {
        return classesBodies;
    }

    public RootBlock getClassBody(String _className) {
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            if (!StringList.quickEq(c.getKey(), _className)) {
                continue;
            }
            return c.getValue();
        }
        return null;
    }

    public static CustList<MethodBlock> getMethodBodiesById(ContextEl _context,String _genericClassName, MethodId _id) {
        CustList<MethodBlock> methods_ = new CustList<MethodBlock>();
        String base_ = Templates.getIdFromAllTypes(_genericClassName);
        Classes classes_ = _context.getClasses();
        RootBlock r_ = classes_.getClassBody(base_);
        for (GeneMethod m: Classes.getMethodBlocks(r_)) {
            if (m.getId().eq(_id)) {
                methods_.add((MethodBlock)m);
                break;
            }
        }
        return methods_;
    }
    public static CustList<OperatorBlock> getOperatorsBodiesById(ContextEl _context,MethodId _id) {
        CustList<OperatorBlock> methods_ = new CustList<OperatorBlock>();
        Classes classes_ = _context.getClasses();
        for (GeneMethod m: classes_.getOperators()) {
            if (m.getId().eq(_id)) {
                methods_.add((OperatorBlock)m);
                break;
            }
        }
        return methods_;
    }
    public static CustList<GeneConstructor> getConstructorBodiesById(Analyzable _context,String _genericClassName, ConstructorId _id) {
        return getConstructorBodiesById(_context, _genericClassName, _id.getParametersTypes(), _id.isVararg());
    }
    public static CustList<GeneConstructor> getConstructorBodies(Analyzable _context,String _genericClassName) {
        CustList<GeneConstructor> methods_ = new CustList<GeneConstructor>();
        String base_ = Templates.getIdFromAllTypes(_genericClassName);
        Classes classes_ = _context.getClasses();
        for (EntryCust<String, StandardType> c: _context.getStandards().getStandards().entryList()) {
            if (!StringList.quickEq(c.getKey(), base_)) {
                continue;
            }
            for (StandardConstructor s: c.getValue().getConstructors()) {
                methods_.add(s);
            }
        }
        for (EntryCust<String, RootBlock> c: classes_.classesBodies.entryList()) {
            if (!StringList.quickEq(c.getKey(), base_)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (!(b instanceof ConstructorBlock)) {
                    continue;
                }
                ConstructorBlock method_ = (ConstructorBlock) b;
                methods_.add(method_);
            }
        }
        return methods_;
    }
    private static CustList<GeneConstructor> getConstructorBodiesById(Analyzable _context,String _genericClassName, StringList _parametersTypes, boolean _vararg) {
        CustList<GeneConstructor> methods_ = new CustList<GeneConstructor>();
        String base_ = Templates.getIdFromAllTypes(_genericClassName);
        int nbParams_ = _parametersTypes.size();
        Classes classes_ = _context.getClasses();
        for (EntryCust<String, StandardType> c: _context.getStandards().getStandards().entryList()) {
            if (!StringList.quickEq(c.getKey(), base_)) {
                continue;
            }
            for (StandardConstructor s: c.getValue().getConstructors()) {
                StringList list_ = s.getId().getParametersTypes();
                if (list_.size() != nbParams_) {
                    continue;
                }
                if (nbParams_ > 0 && _vararg) {
                    if (!s.isVarargs()) {
                        continue;
                    }
                } else {
                    if (s.isVarargs()) {
                        continue;
                    }
                }
                boolean all_ = true;
                for (int i = CustList.FIRST_INDEX; i < nbParams_; i++) {
                    String type_ = list_.get(i);
                    if (!StringList.quickEq(type_, _parametersTypes.get(i))) {
                        all_ = false;
                        break;
                    }
                }
                if (!all_) {
                    continue;
                }
                methods_.add(s);
            }
        }
        for (EntryCust<String, RootBlock> c: classes_.classesBodies.entryList()) {
            if (!StringList.quickEq(c.getKey(), base_)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (!(b instanceof ConstructorBlock)) {
                    continue;
                }
                ConstructorBlock method_ = (ConstructorBlock) b;
                StringList list_ = method_.getId().getParametersTypes();
                if (list_.size() != nbParams_) {
                    continue;
                }
                if (nbParams_ > 0 && _vararg) {
                    if (!method_.isVarargs()) {
                        continue;
                    }
                } else {
                    if (method_.isVarargs()) {
                        continue;
                    }
                }
                boolean all_ = true;
                for (int i = CustList.FIRST_INDEX; i < nbParams_; i++) {
                    String type_ = list_.get(i);
                    if (!StringList.quickEq(type_, _parametersTypes.get(i))) {
                        all_ = false;
                        break;
                    }
                }
                if (!all_) {
                    continue;
                }
                methods_.add(method_);
            }
        }
        return methods_;
    }

    public boolean isInitialized(String _name) {
        return getLocks().getState(_name) != InitClassState.NOT_YET;
    }
    public boolean isSuccessfulInitialized(String _name) {
        return getLocks().getState(_name) == InitClassState.SUCCESS;
    }

    public void preInitializeStaticFields(String _className, ContextEl _context) {
        String base_ = Templates.getIdFromAllTypes(_className);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String k_ = c.getKey();
            if (!StringList.quickEq(k_, base_)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof FieldBlock) {
                    FieldBlock method_ = (FieldBlock) b;
                    if (!method_.isStaticField()) {
                        continue;
                    }
                    String m_ = method_.getFieldName();
                    String c_ = method_.getImportedClassName();
                    for (EntryCust<String, Struct> f: staticFields.getVal(base_).entryList()) {
                        if (f.getValue() != null) {
                            continue;
                        }
                        if (StringList.quickEq(f.getKey(), m_)) {
                            f.setValue(StdStruct.defaultClass(c_, _context));
                            break;
                        }
                    }
                }
            }
        }
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String k_ = c.getKey();
            if (!StringList.quickEq(k_, base_)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof ElementBlock) {
                    ElementBlock method_ = (ElementBlock) b;
                    String m_ = method_.getFieldName();
                    for (EntryCust<String, Struct> f: staticFields.getVal(base_).entryList()) {
                        if (StringList.quickEq(f.getKey(), m_)) {
                            f.setValue(NullStruct.NULL_VALUE);
                        }
                    }
                }
            }
        }
    }
    public void initializeStaticField(ClassField _clField, Struct _str) {
        staticFields.getVal(_clField.getClassName()).set(_clField.getFieldName(), _str);
    }
    public int staticFieldCount() {
        int sum_ = 0;
        for (EntryCust<String, StringMap<Struct>> c: staticFields.entryList()) {
            for (EntryCust<String, Struct> e: c.getValue().entryList()) {
                if (e.getValue() == null) {
                    continue;
                }
                sum_++;
            }
        }
        return sum_;
    }
    public Struct getStaticField(ClassField _clField) {
        return staticFields.getVal(_clField.getClassName()).getVal(_clField.getFieldName());
    }

    public boolean isCustomType(String _name) {
        String base_ = Templates.getIdFromAllTypes(_name);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String k_ = c.getKey();
            if (!StringList.quickEq(k_, base_)) {
                continue;
            }
            return true;
        }
        return false;
    }

    public ClassMetaInfo getClassMetaInfo(String _name, ContextEl _context) {
        String base_ = Templates.getIdFromAllTypes(_name);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            ObjectNotNullMap<MethodId, MethodMetaInfo> infos_;
            infos_ = new ObjectNotNullMap<MethodId, MethodMetaInfo>();
            StringMap<FieldMetaInfo> infosFields_;
            infosFields_ = new StringMap<FieldMetaInfo>();
            ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> infosConst_;
            infosConst_ = new ObjectNotNullMap<ConstructorId, ConstructorMetaInfo>();
            String k_ = c.getKey();
            if (!StringList.quickEq(k_, base_)) {
                continue;
            }
            RootBlock clblock_ = c.getValue();
            CustList<Block> bl_ = getDirectChildren(clblock_);
            StringList inners_ = new StringList();
            for (Block b: bl_) {
                if (b instanceof RootBlock) {
                    inners_.add(((RootBlock) b).getFullName());
                }
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    String m_ = method_.getFieldName();
                    String ret_ = method_.getImportedClassName();
                    boolean enumElement_ = b instanceof ElementBlock;
                    boolean staticElement_ = method_.isStaticField();
                    boolean finalElement_ = method_.isFinalField();
                    AccessEnum acc_ = method_.getAccess();
                    FieldMetaInfo met_ = new FieldMetaInfo(_name, m_, ret_, staticElement_, finalElement_, enumElement_, acc_);
                    infosFields_.put(m_, met_);
                }
                if (b instanceof MethodBlock) {
                    MethodBlock method_ = (MethodBlock) b;
                    MethodId id_ = method_.getId();
                    String ret_ = method_.getImportedReturnType();
                    AccessEnum acc_ = method_.getAccess();
                    String formatRet_;
                    MethodId fid_;
                    String formCl_ = method_.getDeclaringType();
                    if (Templates.correctNbParameters(_name, _context)) {
                        formatRet_ = Templates.wildCardFormat(_name, ret_, _context, true);
                        fid_ = id_.reflectFormat(_name, _context);
                    } else {
                        formatRet_ = ret_;
                        fid_ = id_;
                    }
                    MethodMetaInfo met_ = new MethodMetaInfo(acc_,method_.getDeclaringType(), id_, method_.getModifier(), ret_, fid_, formatRet_,formCl_);
                    infos_.put(id_, met_);
                }
                if (b instanceof AnnotationMethodBlock) {
                    AnnotationMethodBlock method_ = (AnnotationMethodBlock) b;
                    MethodId id_ = method_.getId();
                    String ret_ = method_.getImportedReturnType();
                    AccessEnum acc_ = method_.getAccess();
                    String formatRet_;
                    MethodId fid_;
                    String formCl_ = method_.getDeclaringType();
                    formatRet_ = ret_;
                    fid_ = id_;
                    MethodMetaInfo met_ = new MethodMetaInfo(acc_,method_.getDeclaringType(), id_, method_.getModifier(), ret_, fid_, formatRet_,formCl_);
                    infos_.put(id_, met_);
                }
                if (b instanceof ConstructorBlock) {
                    ConstructorBlock method_ = (ConstructorBlock) b;
                    ConstructorId id_ = method_.getGenericId();
                    AccessEnum acc_ = method_.getAccess();
                    String formatRet_;
                    ConstructorId fid_;
                    String ret_ = method_.getImportedReturnType();
                    String formCl_ = method_.getDeclaringType();
                    if (Templates.correctNbParameters(_name, _context)) {
                        formatRet_ = Templates.wildCardFormat(_name, ret_, _context,true);
                        fid_ = id_.reflectFormat(_name, _context);
                    } else {
                        formatRet_ = ret_;
                        fid_ = id_;
                    }
                    ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, acc_, id_, ret_, fid_, formatRet_,formCl_);
                    infosConst_.put(id_, met_);
                }
            }
            RootBlock par_ = clblock_.getParentType();
            String format_;
            if (par_ != null) {
                String gene_ = par_.getGenericString();
                if (Templates.correctNbParameters(_name, _context)) {
                    format_ = Templates.quickFormat(_name, gene_, _context);
                } else {
                    format_ = par_.getFullName();
                }
            } else {
                format_ = "";
            }
            AccessEnum acc_ = clblock_.getAccess();
            boolean st_ = clblock_.isStaticType();
            if (clblock_ instanceof InterfaceBlock) {
                return new ClassMetaInfo(_name, ((InterfaceBlock)clblock_).getImportedDirectSuperInterfaces(), format_, inners_,
                        infosFields_,infos_, infosConst_, ClassCategory.INTERFACE,st_,acc_);
            }
            if (clblock_ instanceof AnnotationBlock) {
                return new ClassMetaInfo(_name, new StringList(), format_, inners_,
                        infosFields_,infos_, infosConst_, ClassCategory.ANNOTATION,st_,acc_);
            }
            ClassCategory cat_ = ClassCategory.CLASS;
            if (clblock_ instanceof EnumBlock) {
                cat_ = ClassCategory.ENUM;
            }
            boolean abs_ = clblock_.isAbstractType();
            boolean final_ = clblock_.isFinalType();
            String superClass_ = ((UniqueRootedBlock) clblock_).getImportedDirectGenericSuperClass();
            StringList superInterfaces_ = ((UniqueRootedBlock) clblock_).getImportedDirectGenericSuperInterfaces();
            return new ClassMetaInfo(_name, superClass_, superInterfaces_, format_, inners_,
                    infosFields_,infos_, infosConst_, cat_, abs_, st_, final_,acc_);
        }
        return null;
    }
    public DefaultLockingClass getLocks() {
        return locks;
    }
    public void setLocks(DefaultLockingClass _locks) {
        locks = _locks;
    }
}
