package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultLockingClass;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.FileResolver;
import code.expressionlanguage.InitClassState;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.util.BadClassName;
import code.expressionlanguage.methods.util.BadFileName;
import code.expressionlanguage.methods.util.BadInheritedClass;
import code.expressionlanguage.methods.util.ClassEdge;
import code.expressionlanguage.methods.util.DeadCodeMethod;
import code.expressionlanguage.methods.util.DuplicateGenericSuperTypes;
import code.expressionlanguage.methods.util.DuplicateType;
import code.expressionlanguage.methods.util.EmptyTagName;
import code.expressionlanguage.methods.util.ErrorList;
import code.expressionlanguage.methods.util.FoundErrorInterpret;
import code.expressionlanguage.methods.util.MissingReturnMethod;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassName;
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
import code.expressionlanguage.stds.StandardInterface;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.types.PartTypeUtil;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.sml.Element;
import code.sml.ElementOffsetsNext;
import code.sml.Node;
import code.sml.RowCol;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.graphs.Graph;
import code.util.graphs.SortedGraph;

public final class Classes {

    public static final String EXT = "cdm";
    public static final String TEMP_PREFIX = "tmp";
    private static final String EXT_PRO = "pro";
    private static final char SEP_FILE = '/';
    private static final char DOT = '.';
    private static final String LOC_VAR = ".";

    private static final String PARS = "()";
    private static final String EMPTY_STRING = "";
    private static final String VARARG = "...";

    private final StringMap<RootBlock> classesBodies;
    private final StringMap<FileBlock> filesBodies;

    private final StringMap<StringMap<Struct>> staticFields;

    private final ErrorList errorsDet;
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

    public Classes(){
        classesBodies = new StringMap<RootBlock>();
        filesBodies = new StringMap<FileBlock>();
        errorsDet = new ErrorList();
        staticFields = new StringMap<StringMap<Struct>>();
    }
    private void processPredefinedClass(String _fileName,String _content, ContextEl _context) {
        DocumentResult res_ = DocumentBuilder.parseSaxHtmlRowCol(_content);
        Document doc_ = res_.getDocument();
        _context.setHtml(_content);
        _context.setElements(new ElementOffsetsNext(new RowCol(), 0, 0));
        Element root_ = doc_.getDocumentElement();
        FileBlock fileBlock_ = new FileBlock();
        fileBlock_.setFileName(_fileName);
        Block bl_ = Block.createOperationNode(root_, _context, 0, fileBlock_);
        fileBlock_.appendChild(bl_);
        int tabWidth_ = _context.getTabWidth();
        RootBlock cl_ = (RootBlock) bl_;
        ElementOffsetsNext e_ = _context.getElements();
        ElementOffsetsNext ne_ = DocumentBuilder.getIndexesOfElementOrAttribute(_content, e_, root_, tabWidth_);
        processCustomClass(_fileName, fileBlock_, cl_, true, _content, _context, ne_);
    }
    private void processCustomClass(String _fileName,FileBlock _fileBlock, RootBlock _root, boolean _predefined, String _content, ContextEl _context, ElementOffsetsNext _elt) {
        if (classesBodies.contains(_root.getFullName())) {
            DuplicateType d_ = new DuplicateType();
            d_.setId(_root.getFullName());
            d_.setFileName(_root.getFile().getFileName());
            d_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
            addError(d_);
        }
        filesBodies.put(_fileName, _fileBlock);
        RootBlock bl_ = _root;
        ElementOffsetsNext ne_ = _elt;
        bl_.setAttributes(ne_.getAttributes());
        bl_.setEndHeader(ne_.getEndHeader());
        bl_.setTabs(ne_.getTabs());
        bl_.setOffsets(ne_.getOffsets());
        RootBlock cl_ = bl_;
        String packageName_;
        packageName_ = cl_.getPackageName();
        LgNames lgNames_ = _context.getStandards();
        if (!_predefined) {
            if (packageName_.isEmpty()) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(cl_.getFullName());
                badCl_.setFileName(cl_.getFile().getFileName());
                badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                addError(badCl_);
            }
            StringList elements_ = StringList.splitChars(packageName_, DOT);
            for (String e: elements_) {
                if (!StringList.isWord(e)) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(cl_.getFullName());
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                }
            }
            String className_;
            className_ = cl_.getName();
            if (!StringList.isWord(className_)) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(cl_.getFullName());
                badCl_.setFileName(cl_.getFile().getFileName());
                badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                addError(badCl_);
            }
        }
        String fullDef_ = cl_.getFullDefinition();
        StringList params_ = Templates.getAllTypes(fullDef_);
        StringList varTypes_ = new StringList();
        String objectClassName_ = _context.getStandards().getAliasObject();
        if (params_ != null) {
            for (String p: params_.mid(CustList.SECOND_INDEX)) {
                if (p.isEmpty()) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                    continue;
                }
                if (!p.startsWith(Templates.PREFIX_VAR_TYPE)) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                }
                String name_ = p.substring(Templates.PREFIX_VAR_TYPE.length());
                TypeVar type_ = new TypeVar();
                int indexDef_ = name_.indexOf(Templates.EXTENDS_DEF);
                StringList parts_ = StringList.splitInTwo(name_, indexDef_);
                if (!StringList.isWord(parts_.first())) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                }
                if (varTypes_.containsStr(parts_.first())) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                }
                varTypes_.add(parts_.first());
                StringList constraints_ = new StringList();
                if (indexDef_ != CustList.INDEX_NOT_FOUND_ELT) {
                    for (String b: StringList.splitChars(parts_.last().substring(1), Templates.SEP_BOUNDS)) {
                        if (!isCorrectTemplate(b, _context, cl_)) {
                            BadClassName badCl_ = new BadClassName();
                            badCl_.setClassName(fullDef_);
                            badCl_.setFileName(cl_.getFile().getFileName());
                            badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                            addError(badCl_);
                        }
                        constraints_.add(b);
                    }
                } else {
                    constraints_.add(objectClassName_);
                }
                type_.setConstraints(constraints_);
                type_.setName(parts_.first());
                cl_.getParamTypes().add(type_);
            }
        } else {
            BadClassName badCl_ = new BadClassName();
            badCl_.setClassName(fullDef_);
            badCl_.setFileName(cl_.getFile().getFileName());
            badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
            addError(badCl_);
        }
        int indexSuperType_= -1;
        for (String s: cl_.getDirectGenericSuperTypesBuild(_context)) {
            indexSuperType_++;
            if (!isCorrectTemplate(s, _context, cl_)) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(s);
                badCl_.setFileName(cl_.getFile().getFileName());
                badCl_.setRc(cl_.getRowCol(0, cl_.getRowColDirectSuperTypes().getKey(indexSuperType_)));
                addError(badCl_);
            }
        }
        for (TypeVar t: cl_.getParamTypes()) {
            for (String u: t.getConstraints()) {
                if (!u.startsWith(Templates.PREFIX_VAR_TYPE)) {
                    continue;
                }
                if (!cl_.getParamTypesMap().contains(u.substring(1))) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(u);
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                }
            }
        }
        if (lgNames_.getStandards().contains(cl_.getFullName())) {
            DuplicateType d_ = new DuplicateType();
            d_.setId(cl_.getFullName());
            d_.setFileName(cl_.getFile().getFileName());
            d_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
            addError(d_);
        }
        if (PrimitiveTypeUtil.isPrimitive(cl_.getFullName(), _context)) {
            DuplicateType d_ = new DuplicateType();
            d_.setId(cl_.getFullName());
            d_.setFileName(cl_.getFile().getFileName());
            d_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
            addError(d_);
        }
        Block rootBl_ = cl_;
        CustList<Block> all_ = getSortedDescNodesRoot(rootBl_, _context);
        for (Block b: all_) {
            b.setupChars(_content);
            b.setCompleteGroup();
            b.setNullAssociateElement();
        }
        String fullName_ = cl_.getFullName();
        classesBodies.put(fullName_, cl_);
    }
    public void putFileBlock(String _fileName, FileBlock _fileBlock) {
        filesBodies.put(_fileName, _fileBlock);
    }
    public StringMap<FileBlock> getFilesBodies() {
        return filesBodies;
    }
    //TODO remainder
    public void processBracedClass(RootBlock _root, boolean _predefined, ContextEl _context) {
        if (classesBodies.contains(_root.getFullName())) {
            DuplicateType d_ = new DuplicateType();
            d_.setId(_root.getFullName());
            d_.setFileName(_root.getFile().getFileName());
            d_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
            addError(d_);
        }
        RootBlock bl_ = _root;
        RootBlock cl_ = bl_;
        String packageName_;
        packageName_ = cl_.getPackageName();
        LgNames lgNames_ = _context.getStandards();
        if (!_predefined) {
            if (packageName_.isEmpty()) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(cl_.getFullName());
                badCl_.setFileName(cl_.getFile().getFileName());
                badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                addError(badCl_);
            }
            StringList elements_ = StringList.splitChars(packageName_, DOT);
            for (String e: elements_) {
                if (!StringList.isWord(e)) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(cl_.getFullName());
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                }
            }
            String className_;
            className_ = cl_.getName();
            if (!StringList.isWord(className_)) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(cl_.getFullName());
                badCl_.setFileName(cl_.getFile().getFileName());
                badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                addError(badCl_);
            }
        }
        String fullDef_ = cl_.getFullDefinition();
        StringList params_ = Templates.getAllTypes(fullDef_);
        StringList varTypes_ = new StringList();
        String objectClassName_ = _context.getStandards().getAliasObject();
        if (params_ != null) {
            for (String p: params_.mid(CustList.SECOND_INDEX)) {
                if (p.isEmpty()) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                    continue;
                }
                if (!p.startsWith(Templates.PREFIX_VAR_TYPE)) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                }
                String name_ = p.substring(Templates.PREFIX_VAR_TYPE.length());
                TypeVar type_ = new TypeVar();
                int indexDef_ = name_.indexOf(Templates.EXTENDS_DEF);
                StringList parts_ = StringList.splitInTwo(name_, indexDef_);
                if (!StringList.isWord(parts_.first())) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                }
                if (varTypes_.containsStr(parts_.first())) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                }
                varTypes_.add(parts_.first());
                StringList constraints_ = new StringList();
                if (indexDef_ != CustList.INDEX_NOT_FOUND_ELT) {
                    for (String b: StringList.splitChars(parts_.last().substring(1), Templates.SEP_BOUNDS)) {
                        if (!isCorrectTemplate(b, _context, cl_)) {
                            BadClassName badCl_ = new BadClassName();
                            badCl_.setClassName(b);
                            badCl_.setFileName(cl_.getFile().getFileName());
                            badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                            addError(badCl_);
                        }
                        constraints_.add(b);
                    }
                } else {
                    constraints_.add(objectClassName_);
                }
                type_.setConstraints(constraints_);
                type_.setName(parts_.first());
                cl_.getParamTypes().add(type_);
            }
        } else {
            BadClassName badCl_ = new BadClassName();
            badCl_.setClassName(fullDef_);
            badCl_.setFileName(cl_.getFile().getFileName());
            badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
            addError(badCl_);
        }
        int indexSuperType_= -1;
        for (String s: cl_.getDirectGenericSuperTypesBuild(_context)) {
            indexSuperType_++;
            if (!isCorrectTemplate(s, _context, cl_)) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(s);
                badCl_.setFileName(cl_.getFile().getFileName());
                badCl_.setRc(cl_.getRowCol(0, cl_.getRowColDirectSuperTypes().getKey(indexSuperType_)));
                addError(badCl_);
            }
        }
        CustList<TypeVar> tvs_ = cl_.getParamTypes();
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
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                }
            }
        }
        if (lgNames_.getStandards().contains(cl_.getFullName())) {
            DuplicateType d_ = new DuplicateType();
            d_.setId(cl_.getFullName());
            d_.setFileName(cl_.getFile().getFileName());
            d_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
            addError(d_);
        }
        if (PrimitiveTypeUtil.isPrimitive(cl_.getFullName(), _context)) {
            DuplicateType d_ = new DuplicateType();
            d_.setId(cl_.getFullName());
            d_.setFileName(cl_.getFile().getFileName());
            d_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
            addError(d_);
        }
        String fullName_ = cl_.getFullName();
        classesBodies.put(fullName_, cl_);
    }
    //TODO remainer
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
    public static void validateAll(StringMap<String> _files, ContextEl _context) {
        Classes classes_ = _context.getClasses();
        Classes.buildPredefinedBracesBodies(_context);
        Classes.tryBuildBracedClassesBodies(_files, _context);
        if (!classes_.isEmptyErrors()) {
            return;
        }
        classes_.validateInheritingClasses(_context);
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
    public void tryBuildClassesBodies(StringMap<String> _files, ContextEl _context) {
        for (EntryCust<String,String> f: _files.entryList()) {
            String file_ = f.getKey();
            try {
                if (file_.isEmpty()) {
                    BadFileName b_ = new BadFileName();
                    b_.setClassName(file_);
                    b_.setFileName(file_);
                    b_.setRc(new RowCol());
                    addError(b_);
                }
                for (char c: file_.toCharArray()) {
                    if (StringList.isWordChar(c)) {
                        continue;
                    }
                    if (c == DOT) {
                        continue;
                    }
                    if (c == SEP_FILE) {
                        continue;
                    }
                    BadFileName b_ = new BadFileName();
                    b_.setClassName(file_);
                    b_.setFileName(file_);
                    b_.setRc(new RowCol());
                    addError(b_);
                }
                if (StringList.indexesOfChar(file_, DOT).size() != 1) {
                    BadFileName b_ = new BadFileName();
                    b_.setClassName(file_);
                    b_.setFileName(file_);
                    b_.setRc(new RowCol());
                    addError(b_);
                }
                if (file_.lastIndexOf(SEP_FILE) > file_.indexOf(DOT)) {
                    BadFileName b_ = new BadFileName();
                    b_.setClassName(file_);
                    b_.setFileName(file_);
                    b_.setRc(new RowCol());
                    addError(b_);
                }
                if (!file_.endsWith(StringList.concat(String.valueOf(DOT),EXT))) {
                    BadFileName b_ = new BadFileName();
                    b_.setClassName(file_);
                    b_.setFileName(file_);
                    b_.setRc(new RowCol());
                    addError(b_);
                }
                for (String s: StringList.splitChars(file_, SEP_FILE)) {
                    if (StringList.isWord(s)) {
                        continue;
                    }
                    if (s.isEmpty()) {
                        BadFileName b_ = new BadFileName();
                        b_.setClassName(file_);
                        b_.setFileName(file_);
                        b_.setRc(new RowCol());
                        addError(b_);
                    }
                    for (String e: StringList.splitChars(s, DOT)) {
                        if (StringList.isWord(e)) {
                            continue;
                        }
                        BadFileName b_ = new BadFileName();
                        b_.setClassName(file_);
                        b_.setFileName(file_);
                        b_.setRc(new RowCol());
                        addError(b_);
                    }
                }
                String content_ = f.getValue();
                DocumentResult res_ = DocumentBuilder.parseSaxHtmlRowCol(content_);
                Document doc_ = res_.getDocument();
                if (doc_ == null) {
                    BadFileName bad_ = new BadFileName();
                    bad_.setRc(res_.getLocation());
                    bad_.setFileName(file_);
                    addError(bad_);
                    continue;
                }
                _context.setHtml(content_);
                _context.setElements(new ElementOffsetsNext(new RowCol(), 0, 0));
                Element root_ = doc_.getDocumentElement();
                FileBlock fileBlock_ = new FileBlock();
                fileBlock_.setFileName(file_);
                Block bl_ = Block.createOperationNode(root_, _context, 0, fileBlock_);
                if (!(bl_ instanceof RootBlock)) {
                    UnexpectedTagName un_ = new UnexpectedTagName();
                    un_.setFileName(bl_.getFile().getFileName());
                    un_.setRc(bl_.getRowCol(0, bl_.getOffset().getOffsetTrim()));
                    addError(un_);
                }
                int tabWidth_ = _context.getTabWidth();
                RootBlock cl_ = (RootBlock) bl_;
                ElementOffsetsNext e_ = _context.getElements();
                ElementOffsetsNext ne_ = DocumentBuilder.getIndexesOfElementOrAttribute(content_, e_, root_, tabWidth_);
                fileBlock_.appendChild(cl_);
                processCustomClass(file_, fileBlock_, cl_, false, content_, _context, ne_);
            } catch (RuntimeException _0) {
                BadClassName bad_ = new BadClassName();
                bad_.setClassName(_0.getMessage());
                bad_.setRc(new RowCol());
                bad_.setFileName(file_);
                addError(bad_);
            }
        }
        LgNames stds_ = _context.getStandards();
        String content_ = PredefinedClasses.getIterableType(_context);
        processPredefinedClass(stds_.getAliasIterable(), content_, _context);
        content_ = PredefinedClasses.getIteratorType(_context);
        processPredefinedClass(stds_.getAliasIteratorType(), content_, _context);
        content_ = PredefinedClasses.getEnumType(_context);
        processPredefinedClass(stds_.getAliasEnum(), content_, _context);
        content_ = PredefinedClasses.getEnumParamType(_context);
        processPredefinedClass(stds_.getAliasEnumParam(), content_, _context);
        _context.setHtml(EMPTY_STRING);
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
        TypeUtil.buildInherits(_context, cl_.classesBodies.getKeys(), true);
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
        locVar_.setClassName(stds_.getAliasIterable());
        _context.getInternVars().put(locName_, locVar_);
        cl_.iteratorVarCust = locName_;
        String iterator_ = stds_.getAliasSimpleIterator();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(iterator_,PARS));
        cl_.expsIteratorCust = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasIteratorType());
        _context.getInternVars().put(locName_, locVar_);
        cl_.hasNextVarCust = locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNext_,PARS));
        cl_.expsHasNextCust = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasIteratorType());
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
    public static CustList<Block> getSortedDescNodesRoot(Block _root, ContextEl _context) {
        CustList<Block> list_ = new CustList<Block>();
        if (_root == null) {
            return list_;
        }
        Block c_ = _root;
        while (true) {
            if (c_ == null) {
                break;
            }
            list_.add(c_);
            Block next_ = createFirstChild(c_, _context);
            if (next_ != null) {
                next_.setupMetrics(_context);
                ((BracedBlock) c_).appendChild(next_);
                c_ = next_;
                continue;
            }
            next_ = createNextSibling(c_, _context);
            if (next_ != null) {
                next_.getParent().appendChild(next_);
                next_.setupMetrics(_context);
                c_ = next_;
                continue;
            }
            next_ = c_.getParent();
            if (next_ == _root) {
                c_ = null;
                continue;
            }
            if (next_ != null) {
                Block nextAfter_ = createNextSibling(next_, _context);
                while (nextAfter_ == null) {
                    Block par_ = next_.getParent();
                    if (par_ == _root) {
                        break;
                    }
                    if (par_ == null) {
                        break;
                    }
                    nextAfter_ = createNextSibling(par_, _context);
                    next_ = par_;
                }
                if (nextAfter_ != null) {
                    nextAfter_.getParent().appendChild(nextAfter_);
                    nextAfter_.setupMetrics(_context);
                    c_ = nextAfter_;
                    continue;
                }
            }
            c_ = null;
        }
        return list_;
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

    private static Block createFirstChild(Block _block, ContextEl _context) {
        if (!(_block instanceof BracedBlock)) {
            return null;
        }
        BracedBlock block_ = (BracedBlock) _block;
        Element elt_ = block_.getAssociateElement();
        Node first_ = elt_.getFirstChild();
        while (first_ != null) {
            if (first_ instanceof Element) {
                break;
            }
            first_ = first_.getNextSibling();
        }
        if (first_ == null) {
            return null;
        }
        Element eltFirst_ = (Element) first_;
        return Block.createOperationNode(eltFirst_, _context, CustList.FIRST_INDEX, block_);
    }

    private static Block createNextSibling(Block _block, ContextEl _context) {
        BracedBlock p_ = _block.getParent();
        if (p_ == null) {
            return null;
        }
        Node n_ = _block.getAssociateElement().getNextSibling();
        while (n_ != null) {
            if (n_ instanceof Element) {
                break;
            }
            n_ = n_.getNextSibling();
        }
        if (n_ == null) {
            return null;
        }
        Element next_ = (Element) n_;
        return Block.createOperationNode(next_, _context, _block.getIndexChild() + 1, p_);
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
    public static CustList<MethodBlock> getMethodBlocks(RootBlock _element) {
        CustList<MethodBlock> methods_ = new CustList<MethodBlock>();
        for (Block b: Classes.getDirectChildren(_element)) {
            if (b instanceof MethodBlock) {
                methods_.add((MethodBlock) b);
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
    public static CustList<InterfaceNode> getSortedDescNodes(InterfaceNode _root) {
        CustList<InterfaceNode> list_ = new CustList<InterfaceNode>();
        if (_root == null) {
            return list_;
        }
        InterfaceNode c_ = _root;
        if (c_.getFirstChild() == null) {
            list_.add(c_);
            return list_;
        }
        while (true) {
            if (c_ == null) {
                break;
            }
            list_.add(c_);
            c_ = getNext(c_, _root);
        }
        return list_;
    }

    public static InterfaceNode getNext(InterfaceNode _current, InterfaceNode _root) {
        InterfaceNode n_ = _current.getFirstChild();
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
            InterfaceNode next_ = n_.getNextSibling();
            while (next_ == null) {
                InterfaceNode par_ = n_.getParent();
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
    public static CustList<InterfaceNode> getDirectChildren(InterfaceNode _element) {
        CustList<InterfaceNode> list_ = new CustList<InterfaceNode>();
        if (_element == null) {
            return list_;
        }
        InterfaceNode firstChild_ = _element.getFirstChild();
        InterfaceNode elt_ = firstChild_;
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }
    public void validateInheritingClasses(ContextEl _context) {
        _context.setAnalyzing(new AnalyzedPageEl());
        Graph<ClassEdge> inherit_;
        inherit_ = new Graph<ClassEdge>();
        String objectClassName_ = _context.getStandards().getAliasObject();
        String enumClassName_ = _context.getStandards().getAliasEnum();
        String enumParamClassName_ = _context.getStandards().getAliasEnumParam();
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String d_ = c.getKey();
            RootBlock bl_ = c.getValue();
            _context.getAnalyzing().setCurrentBlock(bl_);
            boolean int_ = bl_ instanceof InterfaceBlock;
            int nbDirectSuperClass_ = 0;
            int index_ = -1;
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
                        undef_.setFileName(d_);
                        undef_.setRc(rc_);
                        addError(undef_);
                    }
                    continue;
                }
                if (StringList.quickEq(base_, enumClassName_) && !StringList.quickEq(d_, enumParamClassName_)) {
                    Boolean exp_ = bl_.getExplicitDirectSuperTypes().getVal(offset_);
                    if (exp_) {
                        UnknownClassName undef_;
                        undef_ = new UnknownClassName();
                        undef_.setClassName(base_);
                        undef_.setFileName(d_);
                        undef_.setRc(rc_);
                        addError(undef_);
                    }
                    continue;
                }
                String type_ = base_;
                base_ = _context.resolveBaseType(base_, bl_, rc_);
                if (!classesBodies.contains(base_)) {
                    UnknownClassName undef_;
                    undef_ = new UnknownClassName();
                    undef_.setClassName(type_);
                    undef_.setFileName(d_);
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
                            enum_.setFileName(d_);
                            enum_.setRc(rc_);
                            addError(enum_);
                        }
                    } else if (super_.isFinalType()) {
                        BadInheritedClass enum_;
                        enum_ = new BadInheritedClass();
                        String n_ = base_;
                        enum_.setClassName(n_);
                        enum_.setFileName(d_);
                        enum_.setRc(rc_);
                        addError(enum_);
                    }
                    inherit_.addSegment(new ClassEdge(d_), new ClassEdge(base_));
                }
            }
            if (nbDirectSuperClass_ > 1) {
                BadInheritedClass enum_;
                enum_ = new BadInheritedClass();
                enum_.setClassName(EMPTY_STRING);
                enum_.setFileName(d_);
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
        StringList filter_ = new StringList();
        for (EntryCust<String, RootBlock> s: classesBodies.entryList()) {
            String c = s.getKey();
            RootBlock dBl_ = s.getValue();
            if (dBl_.getFile().isPredefined()) {
                continue;
            }
            filter_.add(c);
        }
        TypeUtil.buildInherits(_context, filter_, true);
        LgNames stds_ = _context.getStandards();
        for (EntryCust<String, RootBlock> s: classesBodies.entryList()) {
            String c = s.getKey();
            RootBlock dBl_ = s.getValue();
            Mapping mapping_ = new Mapping();
            StringMap<StringList> cts_ = new StringMap<StringList>();
            StringList variables_ = new StringList();
            boolean ok_ = true;
            for (TypeVar t: dBl_.getParamTypesMapValues()) {
                cts_.put(t.getName(), t.getConstraints());
                variables_.add(t.getName());
            }
            mapping_.setMapping(cts_);
            if (!ok_) {
                continue;
            }
            if (mapping_.isCyclic(objectClassName_)) {
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
                StringList upper_ = Mapping.getAllUpperBounds(cts_, t.getName(),objectClassName_);
                StringList upperNotObj_ = new StringList();
                for (String b: upper_) {
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
                            if (type_ instanceof StandardInterface) {
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
                            if (r_ instanceof InterfaceBlock) {
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
    public void validateSingleParameterizedClasses(ContextEl _context) {
        for (EntryCust<String, RootBlock> i: classesBodies.entryList()) {
            RootBlock r_ = i.getValue();
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

    public StringList getSortedSuperInterfaces(StringList _already, String _interface, ContextEl _context) {
        StringList superInterfaces_ = new StringList(_interface);
        StringList currentInterfaces_ = new StringList(_interface);
        while (true) {
            StringList nextInterfaces_ = new StringList();
            for (String c: currentInterfaces_) {
                String baseClass_ = Templates.getIdFromAllTypes(c);
                RootBlock int_ = getClassBody(baseClass_);
                StringList directSuperInterfaces_ = int_.getCustomDirectSuperClasses(_context);
                for (String s:directSuperInterfaces_) {
                    superInterfaces_.add(s);
                    nextInterfaces_.add(s);
                }
            }
            if (nextInterfaces_.isEmpty()) {
                break;
            }
            currentInterfaces_ = nextInterfaces_;
        }
        StringList superInterfacesSet_ = new StringList();
        for (String s: superInterfaces_.getReverse()) {
            if (_already.containsStr(s)) {
                continue;
            }
            if (!superInterfacesSet_.containsStr(s)) {
                superInterfacesSet_.add(s);
            }
        }
        return superInterfacesSet_;
    }
    public StringList breadthFirst(String _parent, ContextEl _context) {

        StringList all_ = new StringList();
        StringList nodeQueue_ = new StringList();
        nodeQueue_.add(_parent);

        while (!nodeQueue_.isEmpty()) {
            String current_ = nodeQueue_.first();
            nodeQueue_.remove(0);
            all_.add(current_);
            RootBlock info_ = getClassBody(current_);
            StringList direct_ = info_.getDirectSubTypes(_context);
            for (String c : direct_) {
                nodeQueue_.add(c);
            }
        }
        StringList unique_ = new StringList();
        for (String c: all_) {
            if (!unique_.containsStr(c)) {
                unique_.add(c);
            }
        }
        return unique_;
    }

    public StringList getSortedSuperInterfaces(StringList _interfaces, ContextEl _context) {
        StringList sortedSuperInterfaces_ = new StringList();
        for (String i: _interfaces) {
            StringList superInterfaces_ = new StringList(i);
            StringList currentInterfaces_ = new StringList(i);
            while (true) {
                StringList nextInterfaces_ = new StringList();
                for (String c: currentInterfaces_) {
                    String baseClass_ = Templates.getIdFromAllTypes(c);
                    RootBlock int_ = getClassBody(baseClass_);
                    StringList directSuperInterfaces_ = int_.getCustomDirectSuperClasses(_context);
                    for (String s:directSuperInterfaces_) {
                        if (superInterfaces_.containsStr(s)) {
                            continue;
                        }
                        superInterfaces_.add(s);
                        nextInterfaces_.add(s);
                    }
                }
                if (nextInterfaces_.isEmpty()) {
                    break;
                }
                currentInterfaces_ = nextInterfaces_;
            }
            StringMap<InterfaceNode> is_ = new StringMap<InterfaceNode>();
            InterfaceNode i_ = new InterfaceNode();
            i_.setInterfaceName(superInterfaces_.first());
            is_.put(superInterfaces_.first(), i_);
            for (String s: superInterfaces_) {
                String baseClass_ = Templates.getIdFromAllTypes(s);
                RootBlock int_ = getClassBody(baseClass_);
                StringList directSuperInterfaces_ = int_.getCustomDirectSuperClasses(_context);
                InterfaceNode current_ = is_.getVal(s);
                for (String r: directSuperInterfaces_) {
                    InterfaceNode intNode_ = is_.getVal(r);
                    if (intNode_ != null) {
                        continue;
                    }
                    InterfaceNode child_ = current_.getFirstChild();
                    int index_ = CustList.FIRST_INDEX;
                    if (child_ != null) {
                        InterfaceNode loc_ = child_;
                        while (true) {
                            if (loc_ == null) {
                                break;
                            }
                            index_++;
                            InterfaceNode next_ = loc_.getNextSibling();
                            if (next_ == null) {
                                child_ = loc_;
                            }
                            loc_ = next_;
                        }
                    }
                    InterfaceNode ic_ = new InterfaceNode();
                    ic_.setParent(current_);
                    ic_.setInterfaceName(r);
                    is_.put(r, ic_);
                    if (child_ == null) {
                        ic_.setIndexChild(0);
                        current_.setFirstChild(ic_);
                        continue;
                    }
                    ic_.setIndexChild(index_);
                    child_.setNextSibling(ic_);
                }
            }
            CustList<InterfaceNode> all_ = new CustList<InterfaceNode>();
            for (InterfaceNode s: getSortedDescNodes(i_)) {
                all_.add(s);
            }
            int order_ = 0;
            while (true) {
                CustList<InterfaceNode> next_ = new CustList<InterfaceNode>();
                for (InterfaceNode e: all_) {
                    if (e.getOrder() > CustList.INDEX_NOT_FOUND_ELT) {
                        continue;
                    }
                    InterfaceNode cur_ = e;
                    boolean tonumber_ = true;
                    while (cur_ != null) {
                        int index_ = cur_.getIndexChild() - 1;
                        if (index_ >= CustList.FIRST_INDEX) {
                            CustList<InterfaceNode> sn_ = getDirectChildren(cur_.getParent());
                            InterfaceNode s_ = sn_.get(index_);
                            InterfaceNode prev_ = s_;
                            if (prev_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
                                tonumber_ = false;
                                break;
                            }
                        }
                        cur_ = cur_.getParent();
                    }
                    if (!tonumber_) {
                        continue;
                    }
                    CustList<InterfaceNode> list_ = getDirectChildren(e);
                    if (!list_.isEmpty()) {
                        InterfaceNode op_ = list_.last();
                        if (op_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
                            continue;
                        }
                    }
                    next_.add(e);
                }
                if (next_.isEmpty()) {
                    break;
                }
                for (InterfaceNode o: next_) {
                    o.setOrder(order_);
                    order_++;
                }
            }
            all_.sortElts(new ComparatorInterfaceNode());
            for (InterfaceNode j: all_) {
                String name_ = j.getInterfaceName();
                if (!sortedSuperInterfaces_.containsStr(name_)) {
                    sortedSuperInterfaces_.add(name_);
                }
            }
        }
        return sortedSuperInterfaces_;
    }
    public void validateIds(ContextEl _context) {
        _context.setAnalyzing(new AnalyzedPageEl());
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            RootBlock bl_ = c.getValue();
            bl_.validateIds(_context);
        }
    }
    public void validateOverridingInherit(ContextEl _context) {
        _context.setAnalyzing(new AnalyzedPageEl());
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            RootBlock bl_ = c.getValue();
            if (bl_.getFile().isPredefined()) {
                continue;
            }
            bl_.setupBasicOverrides(_context);
            bl_.checkCompatibility(_context);
            bl_.checkImplements(_context);
        }
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            RootBlock bl_ = c.getValue();
            if (bl_.getFile().isPredefined()) {
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
        if (StringList.quickEq(belong_.getFullName(), root_.getFullName())) {
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
        ObjectMap<ClassField,AssignmentBefore> assStd_;
        assStd_ = new ObjectMap<ClassField,AssignmentBefore>();
        for (EntryCust<String, StandardType> e: _context.getStandards().getStandards().entryList()){
            for (EntryCust<String, StandardField> f: e.getValue().getFields().entryList()){
                if (!f.getValue().isStaticField()) {
                    continue;
                }
                AssignmentBefore as_ = new AssignmentBefore();
                as_.setAssignedBefore(true);
                ClassField key_ = new ClassField(e.getKey(), f.getValue().getFieldName());
                assStd_.put(key_, as_);
            }
        }
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            ObjectMap<ClassField,AssignmentBefore> ass_;
            ass_ = new ObjectMap<ClassField,AssignmentBefore>();
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
                ClassField key_ = new ClassField(clDecl_, fieldName_);
                if (staticFields.getVal(clDecl_).getVal(fieldName_) != null) {
                    as_.setAssignedBefore(true);
                } else {
                    as_.setUnassignedBefore(true);
                }
                ass_.put(key_, as_);
            }
            AssignedVariablesBlock asBlock_ = page_.getAssignedVariables();
            ObjectMap<ClassField,AssignmentBefore> b_ = asBlock_.getFinalVariablesGlobal().getFieldsRootBefore();
            asBlock_.getFinalVariablesGlobal().getFields().clear();
            asBlock_.getFinalVariablesGlobal().getFieldsRoot().clear();
            asBlock_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
            asBlock_.getFinalVariablesGlobal().getFieldsBefore().clear();
            b_.putAllMap(ass_);
            for (EntryCust<ClassField, AssignmentBefore> e: assStd_.entryList()) {
                b_.put(e.getKey(), e.getValue().copy());
            }
            ObjectMap<ClassField,SimpleAssignment> assAfter_;
            assAfter_ = new ObjectMap<ClassField,SimpleAssignment>();
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
            for (EntryCust<ClassField, SimpleAssignment> a: assAfter_.entryList()) {
                ClassField key_ = a.getKey();
                FieldInfo finfo_ = _context.getFieldInfo(key_);
                if (!finfo_.isFinalField()) {
                    continue;
                }
                if (!finfo_.isStaticField()) {
                    continue;
                }
                if (!a.getValue().isAssignedAfter()) {
                    //error
                    UnassignedFinalField un_ = new UnassignedFinalField(key_);
                    un_.setFileName(c.getValue().getFile().getFileName());
                    un_.setRc(c.getValue().getRowCol(0,c.getValue().getOffset().getOffsetTrim()));
                    _context.getClasses().addError(un_);
                }
            }
        }
        ObjectMap<ClassField,AssignmentBefore> assSt_;
        assSt_ = new ObjectMap<ClassField,AssignmentBefore>();
        ObjectMap<ClassField,SimpleAssignment> assStAfter_;
        assStAfter_ = new ObjectMap<ClassField,SimpleAssignment>();
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    if (method_.isStaticField()) {
                        AssignmentBefore as_ = new AssignmentBefore();
                        String clDecl_ = c.getKey();
                        String fieldName_ = method_.getFieldName();
                        ClassField key_ = new ClassField(clDecl_, fieldName_);
                        as_.setAssignedBefore(true);
                        assSt_.put(key_, as_);
                        assStAfter_.put(key_, Assignment.assignClassic(true, false));
                    }
                }
            }
        }
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            ObjectMap<ClassField,AssignmentBefore> ass_;
            ass_ = new ObjectMap<ClassField,AssignmentBefore>();
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
                    String clDecl_ = c.getKey();
                    String fieldName_ = f_.getFieldName();
                    ClassField key_ = new ClassField(clDecl_, fieldName_);
                    as_.setUnassignedBefore(true);
                    ass_.put(key_, as_);
                }
            }
            for (EntryCust<String, RootBlock> d: classesBodies.entryList()) {
                if (StringList.quickEq(d.getKey(), c.getKey())) {
                    continue;
                }
                CustList<Block> blt_ = getDirectChildren(d.getValue());
                for (Block b: blt_) {
                    if (b instanceof InfoBlock) {
                        InfoBlock method_ = (InfoBlock) b;
                        if (method_.isStaticField()) {
                            continue;
                        }
                    }
                    if (b instanceof FieldBlock) {
                        InfoBlock f_ = (InfoBlock) b;
                        AssignmentBefore as_ = new AssignmentBefore();
                        String clDecl_ = d.getKey();
                        String fieldName_ = f_.getFieldName();
                        ClassField key_ = new ClassField(clDecl_, fieldName_);
                        as_.setAssignedBefore(true);
                        ass_.put(key_, as_);
                    }
                }
            }
            AssignedVariablesBlock asBlock_ = page_.getAssignedVariables();
            ObjectMap<ClassField,AssignmentBefore> b_ = asBlock_.getFinalVariablesGlobal().getFieldsRootBefore();
            asBlock_.getFinalVariablesGlobal().getFields().clear();
            asBlock_.getFinalVariablesGlobal().getFieldsRoot().clear();
            asBlock_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
            asBlock_.getFinalVariablesGlobal().getFieldsBefore().clear();
            b_.putAllMap(ass_);
            b_.putAllMap(assSt_);
            for (EntryCust<ClassField, AssignmentBefore> e: assStd_.entryList()) {
                b_.put(e.getKey(), e.getValue().copy());
            }
            ObjectMap<ClassField,SimpleAssignment> assAfter_;
            assAfter_ = new ObjectMap<ClassField,SimpleAssignment>();
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
                for (EntryCust<ClassField, SimpleAssignment> a: assAfter_.entryList()) {
                    ClassField key_ = a.getKey();
                    String curCur_ = key_.getClassName();
                    if (!StringList.quickEq(curCur_, c.getKey())) {
                        continue;
                    }
                    String fieldName_ = key_.getFieldName();
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
            for (EntryCust<ClassField, SimpleAssignment> a: assAfter_.entryList()) {
                b_.put(a.getKey(), a.getValue().assignBefore());
            }
            for (EntryCust<ClassField, SimpleAssignment> a: assStAfter_.entryList()) {
                b_.put(a.getKey(), a.getValue().assignBefore());
            }
            for (EntryCust<ClassField, AssignmentBefore> e: assStd_.entryList()) {
                b_.put(e.getKey(), e.getValue().copy());
            }
            RootBlock block_ = c.getValue();
            StringList filteredCtor_ = new StringList();
            if (block_ instanceof UniqueRootedBlock) {
                Classes classes_ = _context.getClasses();
                UniqueRootedBlock un_ = (UniqueRootedBlock)block_;
                StringList all_ = block_.getAllInterfaces();
                StringList allCopy_ = new StringList(all_);
                allCopy_.removeAllElements(_context.getStandards().getPredefinedInterfacesInitOrder());
                RootBlock superType_ = classes_.getClassBody(un_.getSuperClass(_context));
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
        ObjectMap<ClassField,AssignmentBefore> ass_;
        ass_ = new ObjectMap<ClassField,AssignmentBefore>();
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    InfoBlock f_ = (InfoBlock) b;
                    AssignmentBefore as_ = new AssignmentBefore();
                    String clDecl_ = c.getKey();
                    String fieldName_ = f_.getFieldName();
                    ClassField key_ = new ClassField(clDecl_, fieldName_);
                    as_.setAssignedBefore(true);
                    ass_.put(key_, as_);
                }
            }
        }
        for (EntryCust<ClassField, AssignmentBefore> e: assStd_.entryList()) {
            ass_.put(e.getKey(), e.getValue().copy());
        }
        AssignedVariablesBlock asBlock_ = page_.getAssignedVariables();
        ObjectMap<ClassField,AssignmentBefore> b_ = asBlock_.getFinalVariablesGlobal().getFieldsRootBefore();
        asBlock_.getFinalVariablesGlobal().getFields().clear();
        asBlock_.getFinalVariablesGlobal().getFieldsRoot().clear();
        asBlock_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
        asBlock_.getFinalVariablesGlobal().getFieldsBefore().clear();
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            b_.clear();
            for (EntryCust<ClassField, AssignmentBefore> a: ass_.entryList()) {
                b_.put(a.getKey(), a.getValue().copy());
            }
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
        ObjectMap<ClassField,AssignmentBefore> assStd_;
        assStd_ = new ObjectMap<ClassField,AssignmentBefore>();
        for (EntryCust<String, StandardType> e: _context.getStandards().getStandards().entryList()){
            for (EntryCust<String, StandardField> f: e.getValue().getFields().entryList()){
                if (!f.getValue().isStaticField()) {
                    continue;
                }
                AssignmentBefore as_ = new AssignmentBefore();
                as_.setAssignedBefore(true);
                ClassField key_ = new ClassField(e.getKey(), f.getValue().getFieldName());
                assStd_.put(key_, as_);
            }
        }
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            ObjectMap<ClassField,AssignmentBefore> ass_;
            ass_ = new ObjectMap<ClassField,AssignmentBefore>();
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
                ClassField key_ = new ClassField(c.getKey(), f_.getFieldName());
                ass_.put(key_, as_);
            }
            AssignedVariablesBlock asBlock_ = page_.getAssignedVariables();
            ObjectMap<ClassField,AssignmentBefore> b_ = asBlock_.getFinalVariablesGlobal().getFieldsRootBefore();
            b_.clear();
            b_.putAllMap(ass_);
            for (EntryCust<ClassField, AssignmentBefore> e: assStd_.entryList()) {
                b_.put(e.getKey(), e.getValue().copy());
            }
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
    //validate missing return
    //validate break,continue ancestors / try/catch/finally / switch/case/default
    //validate dead code
    public void validateReturns(ContextEl _context) {
        AnalyzedPageEl page_ = new AnalyzedPageEl();
        _context.setAnalyzing(page_);
        LgNames stds_ = _context.getStandards();
        String void_ = stds_.getAliasVoid();
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String className_ = c.getKey();
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof AloneBlock) {
                    CustList<Block> chSort_ = getSortedDescNodes(b);
                    CustList<Block> all_ = new CustList<Block>();
                    for (Block s: chSort_) {
                        all_.add(s);
                    }
                    int order_ = 0;
                    while (true) {
                        CustList<Block> next_ = new CustList<Block>();
                        for (Block e: all_) {
                            if (e.getOrder() > CustList.INDEX_NOT_FOUND_ELT) {
                                continue;
                            }
                            CustList<Block> list_ = getDirectChildren(e);
                            boolean allNb_ = true;
                            for (Block s: list_) {
                                Block op_ = s;
                                if (op_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
                                    allNb_ = false;
                                    break;
                                }
                            }
                            if (!allNb_) {
                                continue;
                            }
                            next_.add(e);
                        }
                        if (next_.isEmpty()) {
                            break;
                        }
                        for (Block o: next_) {
                            o.setOrder(order_);
                            order_++;
                        }
                    }
                    all_.sortElts(new ComparatorBlockOrder());
                    for (Block d: all_) {
                        d.setAlwaysSkipped();
                    }
                    for (Block d: all_) {
                        d.setExitable();
                    }
                    for (Block d: all_) {
                        d.setStoppable();
                    }
                    for (Block d: all_) {
                        RowCol rc_ = d.existDeadCodeInBlock(0, _context.getTabWidth());
                        if (rc_.getRow() > 0) {
                            DeadCodeMethod deadCode_ = new DeadCodeMethod();
                            deadCode_.setFileName(className_);
                            deadCode_.setRc(rc_);
                            deadCode_.setId(EMPTY_STRING);
                            addError(deadCode_);
                        }
                    }
                }
                if (b instanceof Returnable) {
                    Returnable method_ = (Returnable) b;
                    if (method_ instanceof MethodBlock) {
                        if (((MethodBlock)b).isAbstractMethod()) {
                            continue;
                        }
                    }
                    CustList<Block> chSort_ = getSortedDescNodes(b);
                    CustList<Block> all_ = new CustList<Block>();
                    for (Block s: chSort_) {
                        all_.add(s);
                    }
                    int order_ = 0;
                    while (true) {
                        CustList<Block> next_ = new CustList<Block>();
                        for (Block e: all_) {
                            if (e.getOrder() > CustList.INDEX_NOT_FOUND_ELT) {
                                continue;
                            }
                            CustList<Block> list_ = getDirectChildren(e);
                            boolean allNb_ = true;
                            for (Block s: list_) {
                                Block op_ = s;
                                if (op_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
                                    allNb_ = false;
                                    break;
                                }
                            }
                            if (!allNb_) {
                                continue;
                            }
                            next_.add(e);
                        }
                        if (next_.isEmpty()) {
                            break;
                        }
                        for (Block o: next_) {
                            o.setOrder(order_);
                            order_++;
                        }
                    }
                    all_.sortElts(new ComparatorBlockOrder());
                    for (Block d: all_) {
                        d.setAlwaysSkipped();
                    }
                    for (Block d: all_) {
                        d.setExitable();
                    }
                    for (Block d: all_) {
                        d.setStoppable();
                    }
                    Block r_ = all_.last();
                    StringList types_ = method_.getImportedParametersTypes();
                    int len_ = types_.size();
                    EqList<ClassName> pTypes_ = new EqList<ClassName>();
                    for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                        String n_ = types_.get(i);
                        pTypes_.add(new ClassName(n_, i + 1 == len_ && method_.isVarargs()));
                    }
                    if (!r_.isExitable() && !StringList.quickEq(method_.getImportedReturnType(), void_)) {
                        MissingReturnMethod miss_ = new MissingReturnMethod();
                        miss_.setRc(method_.getRowCol(0, method_.getOffset().getOffsetTrim()));
                        miss_.setFileName(className_);
                        miss_.setId(method_.getSignature());
                        miss_.setReturning(method_.getImportedReturnType());
                        addError(miss_);
                    }
                    for (Block d: all_) {
                        RowCol rc_ = d.existDeadCodeInBlock(0, _context.getTabWidth());
                        if (rc_.getRow() > 0) {
                            DeadCodeMethod deadCode_ = new DeadCodeMethod();
                            deadCode_.setFileName(className_);
                            deadCode_.setRc(rc_);
                            deadCode_.setId(method_.getSignature());
                            addError(deadCode_);
                        }
                    }
                }
            }
        }
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
        for (MethodBlock m: Classes.getMethodBlocks(r_)) {
            if (m.getId().eq(_id)) {
                methods_.add(m);
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
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    String m_ = method_.getFieldName();
                    String ret_ = method_.getImportedClassName();
                    boolean enumElement_ = b instanceof ElementBlock;
                    boolean staticElement_ = method_.isStaticField();
                    boolean finalElement_ = method_.isFinalField();
                    FieldMetaInfo met_ = new FieldMetaInfo(_name, m_, ret_, staticElement_, finalElement_, enumElement_);
                    infosFields_.put(m_, met_);
                }
                if (b instanceof MethodBlock) {
                    MethodBlock method_ = (MethodBlock) b;
                    MethodId id_ = method_.getId();
                    String ret_ = method_.getImportedReturnType();
                    MethodMetaInfo met_ = new MethodMetaInfo(method_.getDeclaringType(), id_, method_.getModifier(), ret_);
                    infos_.put(id_, met_);
                }
                if (b instanceof ConstructorBlock) {
                    ConstructorBlock method_ = (ConstructorBlock) b;
                    ConstructorId id_ = method_.getGenericId();
                    ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, id_);
                    infosConst_.put(id_, met_);
                }
            }
            if (clblock_ instanceof InterfaceBlock) {
                return new ClassMetaInfo(_name, ((InterfaceBlock)clblock_).getImportedDirectSuperInterfaces(), infosFields_,infos_, infosConst_, ClassCategory.INTERFACE);
            }
            ClassCategory cat_ = ClassCategory.CLASS;
            if (clblock_ instanceof EnumBlock) {
                cat_ = ClassCategory.ENUM;
            } else if (clblock_ instanceof InterfaceBlock) {
                cat_ = ClassCategory.INTERFACE;
            }
            boolean abs_ = clblock_.isAbstractType();
            boolean final_ = clblock_.isFinalType();
            return new ClassMetaInfo(_name, ((UniqueRootedBlock) clblock_).getImportedDirectGenericSuperClass(), infosFields_,infos_, infosConst_, cat_, abs_, final_);
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
