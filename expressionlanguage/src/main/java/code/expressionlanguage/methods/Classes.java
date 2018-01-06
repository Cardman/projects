package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.FileResolver;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.exceptions.AlreadyExistingClassException;
import code.expressionlanguage.methods.exceptions.AnalyzingErrorsException;
import code.expressionlanguage.methods.exceptions.BadClassNameException;
import code.expressionlanguage.methods.exceptions.BadFileNameException;
import code.expressionlanguage.methods.exceptions.UnknownBlockException;
import code.expressionlanguage.methods.util.BadAccessClass;
import code.expressionlanguage.methods.util.BadClassName;
import code.expressionlanguage.methods.util.BadFileName;
import code.expressionlanguage.methods.util.BadInheritedClass;
import code.expressionlanguage.methods.util.BadVariableName;
import code.expressionlanguage.methods.util.ClassEdge;
import code.expressionlanguage.methods.util.DeadCodeMethod;
import code.expressionlanguage.methods.util.DuplicateGenericSuperTypes;
import code.expressionlanguage.methods.util.EqualsEl;
import code.expressionlanguage.methods.util.ErrorList;
import code.expressionlanguage.methods.util.MissingReturnMethod;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.EnumStruct;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodMetaInfo;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardInterface;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.sml.Element;
import code.sml.ElementOffsetsNext;
import code.sml.Node;
import code.sml.RowCol;
import code.sml.exceptions.XmlParseException;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.graphs.Graph;

public final class Classes {

    public static final String EXT = "cdm";
    public static final String TEMP_PREFIX = "tmp";
    private static final String ATTRIBUTE_PACKAGE = "package";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_LEFT = "left";
    private static final String ATTRIBUTE_OPER = "oper";
    private static final String ATTRIBUTE_RIGHT = "right";
    private static final String ATTRIBUTE_VAR = "var";
    private static final String ATTRIBUTE_CLASS = "class";
    private static final String ATTRIBUTE_MODIFIER = "modifier";
    private static final String ATTRIBUTE_ACCESS = "access";
    private static final String ATTRIBUTE_SUPER_CLASS = "superclass";
    private static final String ATTRIBUTE_CLASS_INDEX = "classindex";
    private static final String ATTRIBUTE_CONDITION = "condition";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_EXPRESSION = "expression";
    private static final String ATTRIBUTE_EQ = "eq";
    private static final String ATTRIBUTE_INIT = "init";
    private static final String ATTRIBUTE_STEP = "step";

    private static final String EXT_PRO = "pro";
    private static final String NAT_EQ_FORMAT = "{0};.={1};.";
    private static final char SEP_FILE = '/';
    private static final char DOT = '.';
    private static final char LT = '<';
    private static final char GT = '>';
    private static final char ARR_BEG = '[';
    private static final char PREF = '#';
    private static final char PRIM = '$';
    private static final char COMMA = ',';
    private static final String LOC_VAR = ";.";
//    private static final String SIMPLE_ITERATOR = "simpleIterator()";
    private static final String SIMPLE_ITERATOR = "iterator()";
    private static final String ITERATOR = "iterator()";
    private static final String HAS_NEXT = "hasNext()";
    private static final String NEXT = "next()";
    private static final String EMPTY_STRING = "";
    private static final String VARARG = "...";

    private final StringMap<RootBlock> classesBodies;

    private final ObjectMap<ClassField,Struct> staticFields;
    private final StringMap<CustList<Struct>> values;
    private final StringMap<Boolean> initializedClasses;
    private final StringMap<Boolean> successfulInitializedClasses;

    private final ErrorList errorsDet;
    private final StringList localVariablesNames;
    private final StringList classesInheriting;
    private EqualsEl natEqEl;
    private String iteratorVar;
    private String hasNextVar;
    private String nextVar;
    private CustList<OperationNode> exps;
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
        errorsDet = new ErrorList();
        staticFields = new ObjectMap<ClassField,Struct>();
        values = new StringMap<CustList<Struct>>();
        initializedClasses = new StringMap<Boolean>();
        successfulInitializedClasses = new StringMap<Boolean>();
        classesInheriting = new StringList();
        localVariablesNames = new StringList();
    }
    private void processPredefinedClass(String _content, ContextEl _context) {
        DocumentResult res_ = DocumentBuilder.parseSaxHtmlRowCol(_content);
        Document doc_ = res_.getDocument();
        _context.setHtml(_content);
        _context.setElements(new ElementOffsetsNext(new RowCol(), 0, 0));
        Element root_ = doc_.getDocumentElement();
        Block bl_ = Block.createOperationNode(root_, _context, 0, null);
        int tabWidth_ = _context.getTabWidth();
        RootBlock cl_ = (RootBlock) bl_;
        ElementOffsetsNext e_ = _context.getElements();
        ElementOffsetsNext ne_ = DocumentBuilder.getIndexesOfElementOrAttribute(_content, e_, root_, tabWidth_);
        processCustomClass(cl_, true, _content, _context, ne_);
    }
    private void processCustomClass(RootBlock _root, boolean _predefined, String _content, ContextEl _context, ElementOffsetsNext _elt) {
        if (classesBodies.contains(_root.getFullName())) {
            throw new AlreadyExistingClassException(_root.getFullName());
        }
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
                throw new BadClassNameException(cl_.getFullName());
            }
            StringList elements_ = StringList.splitChars(packageName_, DOT);
            for (String e: elements_) {
                if (!StringList.isWord(e)) {
                    throw new BadClassNameException(cl_.getFullName());
                }
            }
            String className_;
            className_ = cl_.getName();
            if (!StringList.isWord(className_)) {
                throw new BadClassNameException(cl_.getFullName());
            }
        }
        String fullDef_ = cl_.getFullDefinition();
        StringList params_ = StringList.getAllTypes(fullDef_);
        if (params_ == null) {
            throw new BadClassNameException(fullDef_);
        }
        StringList varTypes_ = new StringList();
        String objectClassName_ = _context.getStandards().getAliasObject();
        for (String p: params_.mid(CustList.SECOND_INDEX)) {
            if (!p.startsWith(Templates.PREFIX_VAR_TYPE)) {
                throw new BadClassNameException(fullDef_);
            }
            String name_ = p.substring(Templates.PREFIX_VAR_TYPE.length());
            TypeVar type_ = new TypeVar();
            int indexDef_ = name_.indexOf(Templates.EXTENDS_DEF);
            StringList parts_ = StringList.splitInTwo(name_, indexDef_);
            if (!StringList.isWord(parts_.first())) {
                throw new BadClassNameException(fullDef_);
            }
            if (varTypes_.containsStr(parts_.first())) {
                throw new BadClassNameException(fullDef_);
            }
            varTypes_.add(parts_.first());
            StringList constraints_ = new StringList();
            if (indexDef_ != CustList.INDEX_NOT_FOUND_ELT) {
                for (String b: StringList.splitChars(parts_.last().substring(1), Templates.SEP_BOUNDS)) {
                    if (!isCorrectTemplate(b, _context)) {
                        throw new BadClassNameException(b);
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
        cl_.buildMapParamType();
        for (String s: cl_.getDirectGenericSuperTypes(_context)) {
            if (!isCorrectTemplate(s, _context)) {
                throw new BadClassNameException(s);
            }
        }
        for (TypeVar t: cl_.getParamTypes()) {
            for (String u: t.getConstraints()) {
                if (!u.startsWith(Templates.PREFIX_VAR_TYPE)) {
                    continue;
                }
                if (!cl_.getParamTypesMap().contains(u.substring(1))) {
                    throw new BadClassNameException(u);
                }
            }
        }
        if (lgNames_.getStandards().contains(cl_.getFullName())) {
            throw new AlreadyExistingClassException(cl_.getFullName());
        }
        if (PrimitiveTypeUtil.isPrimitive(cl_.getFullName(), _context)) {
            throw new AlreadyExistingClassException(cl_.getFullName());
        }
        Block rootBl_ = cl_;
        CustList<Block> all_ = getSortedDescNodesRoot(rootBl_, _context);
        for (Block b: all_) {
            b.setupChars(_content);
            b.setCompleteGroup();
            b.setNullAssociateElement();
        }
        String fullName_ = cl_.getFullName();
        initializedClasses.put(fullName_, false);
        classesBodies.put(fullName_, cl_);
    }
    public void processBracedClass(RootBlock _root, boolean _predefined, ContextEl _context) {
        if (classesBodies.contains(_root.getFullName())) {
            throw new AlreadyExistingClassException(_root.getFullName());
        }
        RootBlock bl_ = _root;
        RootBlock cl_ = bl_;
        String packageName_;
        packageName_ = cl_.getPackageName();
        LgNames lgNames_ = _context.getStandards();
        if (!_predefined) {
            if (packageName_.isEmpty()) {
                throw new BadClassNameException(cl_.getFullName());
            }
            StringList elements_ = StringList.splitChars(packageName_, DOT);
            for (String e: elements_) {
                if (!StringList.isWord(e)) {
                    throw new BadClassNameException(cl_.getFullName());
                }
            }
            String className_;
            className_ = cl_.getName();
            if (!StringList.isWord(className_)) {
                throw new BadClassNameException(cl_.getFullName());
            }
        }
        String fullDef_ = cl_.getFullDefinition();
        StringList params_ = StringList.getAllTypes(fullDef_);
        if (params_ == null) {
            throw new BadClassNameException(fullDef_);
        }
        StringList varTypes_ = new StringList();
        String objectClassName_ = _context.getStandards().getAliasObject();
        for (String p: params_.mid(CustList.SECOND_INDEX)) {
            if (!p.startsWith(Templates.PREFIX_VAR_TYPE)) {
                throw new BadClassNameException(fullDef_);
            }
            String name_ = p.substring(Templates.PREFIX_VAR_TYPE.length());
            TypeVar type_ = new TypeVar();
            int indexDef_ = name_.indexOf(Templates.EXTENDS_DEF);
            StringList parts_ = StringList.splitInTwo(name_, indexDef_);
            if (!StringList.isWord(parts_.first())) {
                throw new BadClassNameException(fullDef_);
            }
            if (varTypes_.containsStr(parts_.first())) {
                throw new BadClassNameException(fullDef_);
            }
            varTypes_.add(parts_.first());
            StringList constraints_ = new StringList();
            if (indexDef_ != CustList.INDEX_NOT_FOUND_ELT) {
                for (String b: StringList.splitChars(parts_.last().substring(1), Templates.SEP_BOUNDS)) {
                    if (!isCorrectTemplate(b, _context)) {
                        throw new BadClassNameException(b);
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
        cl_.buildMapParamType();
        for (String s: cl_.getDirectGenericSuperTypes(_context)) {
            if (!isCorrectTemplate(s, _context)) {
                throw new BadClassNameException(s);
            }
        }
        for (TypeVar t: cl_.getParamTypes()) {
            for (String u: t.getConstraints()) {
                if (!u.startsWith(Templates.PREFIX_VAR_TYPE)) {
                    continue;
                }
                if (!cl_.getParamTypesMap().contains(u.substring(1))) {
                    throw new BadClassNameException(u);
                }
            }
        }
        if (lgNames_.getStandards().contains(cl_.getFullName())) {
            throw new AlreadyExistingClassException(cl_.getFullName());
        }
        if (PrimitiveTypeUtil.isPrimitive(cl_.getFullName(), _context)) {
            throw new AlreadyExistingClassException(cl_.getFullName());
        }
        Block rootBl_ = cl_;
        CustList<Block> all_ = getSortedDescNodes(rootBl_);
        for (Block b: all_) {
            b.setCompleteGroup();
        }
        String fullName_ = cl_.getFullName();
        initializedClasses.put(fullName_, false);
        classesBodies.put(fullName_, cl_);
    }
    private static boolean isCorrectTemplate(String _temp, ContextEl _context) {
        if (!Templates.isCorrectWrite(_temp, _context)) {
            return false;
        }
        if (PrimitiveTypeUtil.isPrimitive(_temp, _context)) {
            return false;
        }
        for (char c: _temp.toCharArray()) {
            if (StringList.isWordChar(c)) {
                continue;
            }
            if (c == DOT) {
                continue;
            }
            if (c == LT) {
                continue;
            }
            if (c == GT) {
                continue;
            }
            if (c == COMMA) {
                continue;
            }
            if (c == PREF) {
                continue;
            }
            if (c == PRIM) {
                continue;
            }
            if (c == ARR_BEG) {
                continue;
            }
            return false;
        }
        return true;
    }
    public ErrorList getErrorsDet() {
        return errorsDet;
    }
    public static void validateAll(StringMap<String> _files, ContextEl _context) {
        Classes bk_ = _context.getClasses();
        Classes classes_ = new Classes();
        try {
            _context.setClasses(classes_);
            classes_.tryBuildClassesBodies(_files, _context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateInheritingClasses(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateSingleParameterizedClasses(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateIds(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateOverridingInherit(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateClassesAccess(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateLocalVariableNamesId(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateEl(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateReturns(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            _context.setAnalyzing(null);
        } catch (AnalyzingErrorsException _0) {
            _context.setClasses(bk_);
            throw _0;
        } catch (RuntimeException _0) {
            _context.setClasses(bk_);
            throw _0;
        } catch (Error _0) {
            _context.setClasses(bk_);
            throw _0;
        }
    }
    public void tryBuildClassesBodies(StringMap<String> _files, ContextEl _context) {
        for (EntryCust<String,String> f: _files.entryList()) {
            String file_ = f.getKey();
            try {
                if (file_.isEmpty()) {
                    throw new BadFileNameException(file_);
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
                    throw new BadFileNameException(file_);
                }
                if (StringList.indexesOfChar(file_, DOT).size() != 1) {
                    throw new BadFileNameException(file_);
                }
                if (file_.lastIndexOf(SEP_FILE) > file_.indexOf(DOT)) {
                    throw new BadFileNameException(file_);
                }
                if (!file_.endsWith(StringList.concat(String.valueOf(DOT),EXT))) {
                    throw new BadFileNameException(file_);
                }
                for (String s: StringList.splitChars(file_, SEP_FILE)) {
                    if (StringList.isWord(s)) {
                        continue;
                    }
                    if (s.isEmpty()) {
                        throw new BadFileNameException(file_);
                    }
                    for (String e: StringList.splitChars(s, DOT)) {
                        if (StringList.isWord(e)) {
                            continue;
                        }
                        throw new BadFileNameException(file_);
                    }
                }
                String content_ = f.getValue();
                DocumentResult res_ = DocumentBuilder.parseSaxHtmlRowCol(content_);
                Document doc_ = res_.getDocument();
                if (doc_ == null) {
                    BadFileName bad_ = new BadFileName();
                    bad_.setRc(res_.getLocation());
                    bad_.setFileName(file_);
                    errorsDet.add(bad_);
                    continue;
                }
                _context.setHtml(content_);
                _context.setElements(new ElementOffsetsNext(new RowCol(), 0, 0));
                Element root_ = doc_.getDocumentElement();
                Block bl_ = Block.createOperationNode(root_, _context, 0, null);
                if (!(bl_ instanceof RootBlock)) {
                    throw new XmlParseException();
                }
                int tabWidth_ = _context.getTabWidth();
                RootBlock cl_ = (RootBlock) bl_;
                ElementOffsetsNext e_ = _context.getElements();
                ElementOffsetsNext ne_ = DocumentBuilder.getIndexesOfElementOrAttribute(content_, e_, root_, tabWidth_);
                processCustomClass(cl_, false, content_, _context, ne_);
            } catch (UnknownBlockException _0) {
                RowCol where_ = _0.getRc();
                UnexpectedTagName t_ = new UnexpectedTagName();
                t_.setRc(where_);
                t_.setFileName(file_);
                t_.setFoundTag(_0.getMessage());
                errorsDet.add(t_);
            } catch (BadClassNameException _0) {
                BadClassName bad_ = new BadClassName();
                bad_.setClassName(_0.getMessage());
                bad_.setRc(new RowCol());
                bad_.setFileName(file_);
                errorsDet.add(bad_);
            } catch (BadFileNameException _0) {
                BadFileName bad_ = new BadFileName();
                bad_.setRc(new RowCol());
                bad_.setFileName(file_);
                errorsDet.add(bad_);
            } catch (XmlParseException _0) {
                //TODO change later class
                BadFileName bad_ = new BadFileName();
                bad_.setRc(_0.getRowCol());
                bad_.setFileName(file_);
                errorsDet.add(bad_);
            } catch (AlreadyExistingClassException _0) {
                //TODO change later class
                BadClassName bad_ = new BadClassName();
                bad_.setClassName(_0.getMessage());
                bad_.setRc(new RowCol());
                bad_.setFileName(file_);
                errorsDet.add(bad_);
            }
        }
        String content_ = PredefinedClasses.getIterableType(_context);
        processPredefinedClass(content_, _context);
        content_ = PredefinedClasses.getIteratorType(_context);
        processPredefinedClass(content_, _context);
        content_ = PredefinedClasses.getEnumType(_context);
        processPredefinedClass(content_, _context);
        content_ = PredefinedClasses.getEnumParamType(_context);
        processPredefinedClass(content_, _context);
        _context.setHtml(EMPTY_STRING);
    }
    public void tryBuildBracedClassesBodies(StringMap<String> _files, ContextEl _context) {
        for (EntryCust<String,String> f: _files.entryList()) {
            String file_ = f.getKey();
            try {
                String content_ = f.getValue();
                FileResolver.parseFile(file_, content_, false, _context);
            } catch (UnknownBlockException _0) {
                RowCol where_ = _0.getRc();
                UnexpectedTagName t_ = new UnexpectedTagName();
                t_.setRc(where_);
                t_.setFileName(file_);
                t_.setFoundTag(_0.getMessage());
                errorsDet.add(t_);
            } catch (BadClassNameException _0) {
                BadClassName bad_ = new BadClassName();
                bad_.setClassName(_0.getMessage());
                bad_.setRc(new RowCol());
                bad_.setFileName(file_);
                errorsDet.add(bad_);
            } catch (BadFileNameException _0) {
                BadFileName bad_ = new BadFileName();
                bad_.setRc(new RowCol());
                bad_.setFileName(file_);
                errorsDet.add(bad_);
            } catch (XmlParseException _0) {
                //TODO change later class
                BadFileName bad_ = new BadFileName();
                bad_.setRc(_0.getRowCol());
                bad_.setFileName(file_);
                errorsDet.add(bad_);
            } catch (AlreadyExistingClassException _0) {
                //TODO change later class
                BadClassName bad_ = new BadClassName();
                bad_.setClassName(_0.getMessage());
                bad_.setRc(new RowCol());
                bad_.setFileName(file_);
                errorsDet.add(bad_);
            }
        }
        String name_;
        LgNames stds_ = _context.getStandards();
        String content_ = PredefinedClasses.getBracedIterableType(_context);
        name_ = stds_.getAliasIterable();
        FileResolver.parseFile(name_, content_, true, _context);
        content_ = PredefinedClasses.getBracedIteratorType(_context);
        name_ = stds_.getAliasIteratorType();
        FileResolver.parseFile(name_, content_, true, _context);
        content_ = PredefinedClasses.getBracedEnumType(_context);
        name_ = stds_.getAliasEnum();
        FileResolver.parseFile(name_, content_, true, _context);
        content_ = PredefinedClasses.getBracedEnumParamType(_context);
        name_ = stds_.getAliasEnumParam();
        FileResolver.parseFile(name_, content_, true, _context);
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
                c_.setupNextSiblingGroup();
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
                    next_.setupNextSiblingGroup();
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
        PageEl page_ = new PageEl();
        _context.setAnalyzing(page_);
        Graph<ClassEdge> inherit_;
        inherit_ = new Graph<ClassEdge>();
        String objectClassName_ = _context.getStandards().getAliasObject();
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String d_ = c.getKey();
            RootBlock bl_ = c.getValue();
            boolean int_ = bl_ instanceof InterfaceBlock;
            int nbDirectSuperClass_ = 0;
            for (String s: bl_.getDirectSuperClasses(_context)) {
                if (bl_ instanceof UniqueRootedBlock) {
                    nbDirectSuperClass_++;
                }
                if (!classesBodies.contains(s)) {
                    if (!StringList.quickEq(s, objectClassName_)) {
                        UnknownClassName undef_;
                        undef_ = new UnknownClassName();
                        undef_.setClassName(s);
                        undef_.setFileName(d_);
                        undef_.setRc(bl_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_SUPER_CLASS));
                        errorsDet.add(undef_);
                    }
                } else {
                    RootBlock super_ = classesBodies.getVal(s);
                    if (int_) {
                        if (!(super_ instanceof InterfaceBlock)) {
                            BadInheritedClass enum_;
                            enum_ = new BadInheritedClass();
                            String n_ = s;
                            enum_.setClassName(n_);
                            enum_.setFileName(d_);
                            enum_.setRc(new RowCol());
                            errorsDet.add(enum_);
                        }
                    } else if (super_.isFinalType()) {
                        BadInheritedClass enum_;
                        enum_ = new BadInheritedClass();
                        String n_ = s;
                        enum_.setClassName(n_);
                        enum_.setFileName(d_);
                        enum_.setRc(new RowCol());
                        errorsDet.add(enum_);
                    }
                    if (!(bl_ instanceof EnumBlock) && !StringList.quickEq(bl_.getFullName(), PredefinedClasses.ENUM_PARAM)) {
                        if (StringList.quickEq(super_.getFullName(), PredefinedClasses.ENUM)) {
                            BadInheritedClass enum_;
                            enum_ = new BadInheritedClass();
                            String n_ = s;
                            enum_.setClassName(n_);
                            enum_.setFileName(d_);
                            enum_.setRc(new RowCol());
                            errorsDet.add(enum_);
                        }
                        if (StringList.quickEq(super_.getFullName(), PredefinedClasses.ENUM_PARAM)) {
                            BadInheritedClass enum_;
                            enum_ = new BadInheritedClass();
                            String n_ = s;
                            enum_.setClassName(n_);
                            enum_.setFileName(d_);
                            enum_.setRc(new RowCol());
                            errorsDet.add(enum_);
                        }
                    }
                }
                inherit_.addSegment(new ClassEdge(d_), new ClassEdge(s));
            }
            if (nbDirectSuperClass_ > 1) {
                BadInheritedClass enum_;
                enum_ = new BadInheritedClass();
                enum_.setClassName(EMPTY_STRING);
                enum_.setFileName(d_);
                enum_.setRc(new RowCol());
                errorsDet.add(enum_);
            }
        }
        if (!errorsDet.isEmpty()) {
            return;
        }
        EqList<ClassEdge> cycle_ = inherit_.elementsCycle();
        if (!cycle_.isEmpty()) {
            for (ClassEdge c: cycle_) {
                BadInheritedClass b_;
                b_ = new BadInheritedClass();
                String n_ = c.getId();
                b_.setClassName(n_);
                b_.setFileName(n_);
                b_.setRc(new RowCol());
                errorsDet.add(b_);
            }
            return;
        }
        if (_context.getOptions().isBreadthFirst()) {
            StringList allTypes_ = new StringList();
            StringList allInterfaces_;
            allInterfaces_ = new StringList();
            for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
                RootBlock bl_ = c.getValue();
                if (!(bl_ instanceof InterfaceBlock)) {
                    continue;
                }
                if (!((InterfaceBlock) bl_).getDirectSuperInterfaces().isEmpty()) {
                    continue;
                }
                String key_ = c.getKey();
                StringList loc_ = breadthFirst(key_, _context);
                for (String s: loc_) {
                    if (!allInterfaces_.containsStr(s)) {
                        allInterfaces_.add(s);
                    }
                }
            }
            allTypes_.addAllElts(allInterfaces_);
            StringList allOthers_;
            allOthers_ = new StringList();
            for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
                RootBlock bl_ = c.getValue();
                if (!(bl_ instanceof UniqueRootedBlock)) {
                    continue;
                }
                UniqueRootedBlock unique_ = (UniqueRootedBlock) bl_;
                if (!StringList.quickEq(unique_.getSuperClass(_context), objectClassName_)) {
                    continue;
                }
                String key_ = c.getKey();
                StringList loc_ = breadthFirst(key_, _context);
                for (String s: loc_) {
                    if (!allOthers_.containsStr(s)) {
                        allOthers_.add(s);
                    }
                }
            }
            allTypes_.addAllElts(allOthers_);
            classesInheriting.clear();
            classesInheriting.addAllElts(allTypes_);
        } else {
            EqList<ClassEdge> elts_ = inherit_.getElementsListCopy();
            int order_ = 0;
            while (true) {
                EqList<ClassEdge> next_ = new EqList<ClassEdge>();
                for (ClassEdge e: elts_) {
                    if (e.getOrder() > CustList.INDEX_NOT_FOUND_ELT) {
                        continue;
                    }
                    EqList<ClassEdge> list_ = inherit_.getChildren(e);
                    boolean allNb_ = true;
                    for (ClassEdge s: list_) {
                        ClassEdge s_ = inherit_.getElementByEq(s);
                        if (s_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
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
                for (ClassEdge o: next_) {
                    o.setOrder(order_);
                    order_++;
                }
            }
            elts_.sortElts(new ComparatorClassEdge());
            for (ClassEdge c: elts_) {
                classesInheriting.add(c.getId());
            }
            classesInheriting.removeAllObj(objectClassName_);
            if (!errorsDet.isEmpty()) {
                return;
            }
        }
        for (String c: classesInheriting) {
            RootBlock dBl_ = classesBodies.getVal(c);
            StringList all_ = dBl_.getAllSuperClasses();
            StringList direct_ = dBl_.getDirectSuperClasses(_context);
            all_.addAllElts(direct_);
            for (String b: direct_) {
                if (StringList.quickEq(b, objectClassName_)) {
                    continue;
                }
                RootBlock bBl_ = classesBodies.getVal(b);
                all_.addAllElts(bBl_.getAllSuperClasses());
            }
        }
        for (String c: classesInheriting) {
            RootBlock bl_ = classesBodies.getVal(c);
            if (!(bl_ instanceof UniqueRootedBlock)) {
                continue;
            }
            StringList all_ = bl_.getAllInterfaces();
            StringList direct_ = ((UniqueRootedBlock) bl_).getDirectInterfaces(_context);
            all_.addAllElts(direct_);
            for (String i: direct_) {
                InterfaceBlock i_ = (InterfaceBlock) classesBodies.getVal(i);
                all_.addAllElts(i_.getAllInterfaces());
            }
            StringList needed_ = new StringList();
            for (String s: bl_.getDirectSuperClasses(_context)) {
                if (!StringList.quickEq(s, objectClassName_)) {
                    RootBlock super_ = classesBodies.getVal(s);
                    all_.addAllElts(super_.getAllInterfaces());
                    needed_.addAllElts(super_.getAllSortedInterfaces());
                }
            }
            all_.removeAllObj(objectClassName_);
            all_.removeDuplicates();
            bl_.getAllSortedInterfaces().addAllElts(getSortedSuperInterfaces(all_,_context));
            bl_.getAllNeededSortedInterfaces().addAllElts(bl_.getAllSortedInterfaces());
            bl_.getAllNeededSortedInterfaces().removeAllElements(needed_);
        }
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            RootBlock r_ = c.getValue();
            if (r_ instanceof UniqueRootedBlock) {
                r_.getAllSuperTypes().addAllElts(((UniqueRootedBlock)r_).getAllSuperClasses(_context));
                r_.getAllSuperTypes().addAllElts(((UniqueRootedBlock)r_).getAllInterfaces());
            } else {
                r_.getAllSuperTypes().addAllElts(((InterfaceBlock)r_).getAllSuperClasses());
            }
        }
        LgNames stds_ = _context.getStandards();
        for (EntryCust<String, RootBlock> s: classesBodies.entryList()) {
            String c = s.getKey();
            RootBlock dBl_ = s.getValue();
            Mapping mapping_ = new Mapping();
            StringList variables_ = new StringList();
            boolean ok_ = true;
            for (TypeVar t: dBl_.getParamTypes()) {
                mapping_.getMapping().put(t.getName(), t.getConstraints());
                variables_.add(t.getName());
                for (String b: t.getConstraints()) {
                    if (!Templates.existAllClassParts(b, variables_, _context)) {
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(b);
                        un_.setFileName(c);
                        un_.setRc(new RowCol());
                        errorsDet.add(un_);
                        ok_ = false;
                    }
                }
            }
            if (!ok_) {
                continue;
            }
            if (mapping_.isCyclic(objectClassName_)) {
                BadInheritedClass b_;
                b_ = new BadInheritedClass();
                //TODO better message
                b_.setClassName(c);
                b_.setFileName(c);
                b_.setRc(new RowCol());
                errorsDet.add(b_);
                continue;
            }
            for (TypeVar t: dBl_.getParamTypes()) {
                boolean existNative_ = false;
                boolean existCustom_ = false;
                StringList upper_ = mapping_.getAllUpperBounds(t.getName(),objectClassName_);
                StringList upperNotObj_ = new StringList();
                for (String b: upper_) {
                    StringList baseParams_ = StringList.getAllTypes(b);
                    String base_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseParams_.first()).getComponent();
                    upperNotObj_.add(b);
                    if (classesBodies.contains(base_)) {
                        existCustom_ = true;
                    } else {
                        existNative_ = true;
                    }
                }
                if (existNative_ && existCustom_) {
                    UnknownClassName un_ = new UnknownClassName();
                    //TODO all conflicting classes
                    un_.setClassName(c);
                    un_.setFileName(c);
                    un_.setRc(new RowCol());
                    errorsDet.add(un_);
                    ok_ = false;
                }
                StringMap<StringList> baseParams_ = getBaseParams(upper_);
                for (EntryCust<String, StringList> e: baseParams_.entryList()) {
                    if (e.getValue().size() > 1) {
                        DuplicateGenericSuperTypes duplicate_;
                        duplicate_ = new DuplicateGenericSuperTypes();
                        duplicate_.setFileName(c);
                        duplicate_.setRc(new RowCol());
                        duplicate_.setGenericSuperTypes(e.getValue());
                        errorsDet.add(duplicate_);
                    }
                }
                if (ok_) {
                    int nbAbs_ = 0;
                    int nbFinal_ = 0;
                    if (existNative_) {
                        for (String b: upperNotObj_) {
                            StringList baseParamsUpp_ = StringList.getAllTypes(b);
                            String base_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseParamsUpp_.first()).getComponent();
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
                            StringList baseParamsUpp_ = StringList.getAllTypes(b);
                            String base_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseParamsUpp_.first()).getComponent();
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
                        inh_.setRc(new RowCol());
                        inh_.setClassName(c);
                        errorsDet.add(inh_);
                    }
                }
            }
        }
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String d_ = c.getKey();
            RootBlock bl_ = c.getValue();
            StringList variables_ = new StringList();
            StringMap<StringList> map_;
            map_ = new StringMap<StringList>();
            for (TypeVar t: bl_.getParamTypes()) {
                variables_.add(t.getName());
                map_.put(t.getName(), t.getConstraints());
            }
            for (TypeVar t: bl_.getParamTypes()) {
                for (String b: t.getConstraints()) {
                    if (!Templates.correctClassParts(b, map_, _context)) {
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(b);
                        un_.setFileName(d_);
                        un_.setRc(new RowCol());
                        errorsDet.add(un_);
                    }
                }
            }
            for (String s: bl_.getDirectGenericSuperClasses(_context)) {
                if (!Templates.correctClassParts(s, map_, _context)) {
                    UnknownClassName un_ = new UnknownClassName();
                    un_.setClassName(s);
                    un_.setFileName(d_);
                    un_.setRc(new RowCol());
                    errorsDet.add(un_);
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
                    duplicate_.setRc(new RowCol());
                    duplicate_.setGenericSuperTypes(e.getValue());
                    errorsDet.add(duplicate_);
                }
            }
        }
    }
    public static StringMap<StringList> getBaseParams(StringList _genericSuperTypes) {
        StringMap<StringList> baseParams_ = new StringMap<StringList>();
        for (String t: _genericSuperTypes) {
            StringList baseParam_ = StringList.getAllTypes(t);
            String key_ = baseParam_.first();
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
                String baseClass_ = StringList.getAllTypes(c).first();
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
                    String baseClass_ = StringList.getAllTypes(c).first();
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
                String baseClass_ = StringList.getAllTypes(s).first();
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
        PageEl page_ = new PageEl();
        _context.setAnalyzing(page_);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            RootBlock bl_ = c.getValue();
            bl_.validateIds(_context);
        }
    }
    public void validateOverridingInherit(ContextEl _context) {
        PageEl page_ = new PageEl();
        _context.setAnalyzing(page_);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            RootBlock bl_ = c.getValue();
            bl_.setupBasicOverrides(_context);
            bl_.checkCompatibility(_context);
            bl_.checkImplements(_context);
        }
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            RootBlock bl_ = c.getValue();
            bl_.checkCompatibilityBounds(_context);
        }
    }
    public void validateClassesAccess(ContextEl _context) {
        PageEl page_ = new PageEl();
        _context.setAnalyzing(page_);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String className_ = c.getKey();
            CustList<Block> bl_ = getSortedDescNodes(c.getValue());
            for (Block e: bl_) {
                Block b_ = e;
                for (EntryCust<String, String> n: b_.getClassNames(_context).entryList()) {
                    String classNameLoc_ = n.getValue();
                    StringList parts_ = StringList.splitChars(classNameLoc_, LT, GT, ARR_BEG, COMMA, Templates.SEP_BOUNDS, Templates.EXTENDS_DEF);
                    for (String p: parts_) {
                        if (classesBodies.contains(p)) {
                            if (!canAccessClass(className_, p, _context)) {
                                BadAccessClass err_ = new BadAccessClass();
                                err_.setFileName(className_);
                                err_.setRc(b_.getRowCol(0, _context.getTabWidth(), n.getKey()));
                                err_.setId(classNameLoc_);
                                errorsDet.add(err_);
                            }
                        }
                    }
                }
            }
        }
    }
    //validate local variables names and loop variables names
    public void validateLocalVariableNamesId(ContextEl _context) {
        PageEl page_ = new PageEl();
        _context.setAnalyzing(page_);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String className_ = c.getKey();
            CustList<Block> bl_ = getSortedDescNodes(c.getValue());
            for (Block b: bl_) {
                Block block_ = b;
                if (b instanceof InitVariable) {
                    String var_ = ((InitVariable)b).getVariableName();
                    if (!StringList.isWord(var_)) {
                        BadVariableName b_ = new BadVariableName();
                        b_.setFileName(className_);
                        b_.setRc(block_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_VAR));
                        b_.setVarName(var_);
                        errorsDet.add(b_);
                    }
                    localVariablesNames.add(var_);
                }
                if (b instanceof ForLoop) {
                    String var_ = ((ForLoop)b).getVariableName();
                    if (!StringList.isWord(var_)) {
                        BadVariableName b_ = new BadVariableName();
                        b_.setFileName(className_);
                        b_.setRc(block_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_VAR));
                        b_.setVarName(var_);
                        errorsDet.add(b_);
                    }
                }
                if (b instanceof CatchEval) {
                    String var_ = ((CatchEval)b).getVariableName();
                    if (!StringList.isWord(var_)) {
                        BadVariableName b_ = new BadVariableName();
                        b_.setFileName(className_);
                        b_.setRc(block_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_VAR));
                        b_.setVarName(var_);
                        errorsDet.add(b_);
                    }
                }
            }
        }
        localVariablesNames.removeDuplicates();
        int i_ = CustList.FIRST_INDEX;
        while (localVariablesNames.containsStr(StringList.concatNbs(TEMP_PREFIX,i_))) {
            i_++;
        }
        int five_ = i_;
        i_++;
        while (localVariablesNames.containsStr(StringList.concatNbs(TEMP_PREFIX,i_))) {
            i_++;
        }
        int six_ = i_;
        String fifthArg_ = StringList.concatNbs(TEMP_PREFIX,five_);
        String sixthArg_ = StringList.concatNbs(TEMP_PREFIX,six_);
        localVariablesNames.add(fifthArg_);
        localVariablesNames.add(sixthArg_);
        LgNames stds_ = _context.getStandards();
        String nateqt_ = StringList.simpleStringsFormat(NAT_EQ_FORMAT, fifthArg_, sixthArg_);
        natEqEl = new EqualsEl(fifthArg_, sixthArg_);
        LocalVariable lc_ = new LocalVariable();
        lc_.setClassName(_context.getStandards().getAliasObject());
        page_.getLocalVars().put(fifthArg_, lc_);
        lc_ = new LocalVariable();
        lc_.setClassName(_context.getStandards().getAliasObject());
        page_.getLocalVars().put(sixthArg_, lc_);
        exps = ElUtil.getAnalyzedOperations(nateqt_, _context, Calculation.staticCalculation(true));
        String locName_ = page_.getNextTempVar(this);
        String exp_;
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasSimpleIterableType());
        page_.getLocalVars().put(locName_, locVar_);
        iteratorVar = locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, SIMPLE_ITERATOR);
        expsIterator = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = page_.getNextTempVar(this);
        locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasSimpleIteratorType());
        page_.getLocalVars().put(locName_, locVar_);
        hasNextVar = locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, HAS_NEXT);
        expsHasNext = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = page_.getNextTempVar(this);
        locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasSimpleIteratorType());
        page_.getLocalVars().put(locName_, locVar_);
        nextVar = locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, NEXT);
        expsNext = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = page_.getNextTempVar(this);
        locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasIterable());
        page_.getLocalVars().put(locName_, locVar_);
        iteratorVarCust = locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, ITERATOR);
        expsIteratorCust = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = page_.getNextTempVar(this);
        locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasIteratorType());
        page_.getLocalVars().put(locName_, locVar_);
        hasNextVarCust = locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, HAS_NEXT);
        expsHasNextCust = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = page_.getNextTempVar(this);
        locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasIteratorType());
        page_.getLocalVars().put(locName_, locVar_);
        nextVarCust = locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, NEXT);
        expsNextCust = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        page_.getLocalVars().removeKey(fifthArg_);
        page_.getLocalVars().removeKey(sixthArg_);
        page_.getLocalVars().removeKey(iteratorVar);
        page_.getLocalVars().removeKey(hasNextVar);
        page_.getLocalVars().removeKey(nextVar);
        page_.getLocalVars().removeKey(iteratorVarCust);
        page_.getLocalVars().removeKey(hasNextVarCust);
        page_.getLocalVars().removeKey(nextVarCust);
    }

    public boolean canAccessField(String _className, String _accessedClass, String _name, ContextEl _context) {
        String baseClass_ = StringList.getAllTypes(_accessedClass).first();
        Block access_ = getClassBody(baseClass_);
        CustList<Block> bl_ = getDirectChildren(access_);
        for (Block b: bl_) {
            if (b instanceof InfoBlock) {
                if (StringList.quickEq(((InfoBlock)b).getFieldName(), _name)) {
                    return canAccess(_className,(InfoBlock)b, _context);
                }
            }
        }
        return false;
    }

    public boolean canAccessClass(String _className, String _accessedClass, ContextEl _context) {
        String baseClass_ = StringList.getAllTypes(_accessedClass).first();
        RootBlock access_ = getClassBody(baseClass_);
        return canAccess(_className, access_, _context);
    }

    public boolean canAccess(String _className, AccessibleBlock _block, ContextEl _context) {
        if (_block.getAccess() == AccessEnum.PUBLIC) {
            return true;
        }
        if (_className == null) {
            return false;
        }
        String baseClass_ = StringList.getAllTypes(_className).first();
        RootBlock root_ = getClassBody(baseClass_);
        RootBlock belong_ = _block.belong();
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

    public EqualsEl getNatEqEl() {
        return natEqEl;
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

    public ExpressionLanguage getEqNatEl() {
        return new ExpressionLanguage(exps);
    }

    //validate el and its possible returned type
    public void validateEl(ContextEl _context) {
        PageEl page_ = new PageEl();
        _context.setAnalyzing(page_);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof FunctionBlock) {
                    FunctionBlock method_ = (FunctionBlock) b;
                    method_.checkFctBlocksTree(_context);
                }
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    method_.checkBlocksTree(_context);
                }
            }
        }
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    page_.setGlobalClass(c.getValue().getGenericString());
                    InfoBlock method_ = (InfoBlock) b;
                    method_.buildExpressionLanguage(_context);
                }
                if (b instanceof AloneBlock) {
                    page_.setGlobalClass(c.getValue().getGenericString());
                    AloneBlock method_ = (AloneBlock) b;
                    method_.buildFctInstructions(_context);
                    page_.getLocalVars().clear();
                    page_.getCatchVars().clear();
                    page_.getVars().clear();
                }
                if (b instanceof Returnable) {
                    page_.setGlobalClass(c.getValue().getGenericString());
                    Returnable method_ = (Returnable) b;
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getParametersTypes();
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
                    page_.getLocalVars().clear();
                    page_.getCatchVars().clear();
                    page_.getVars().clear();
                }
            }
        }
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    page_.setGlobalClass(c.getValue().getGenericString());
                    InfoBlock method_ = (InfoBlock) b;
                    method_.checkCallConstructor(_context);
                }
                if (b instanceof FunctionBlock) {
                    page_.setGlobalClass(c.getValue().getGenericString());
                    FunctionBlock method_ = (FunctionBlock) b;
                    method_.checkFctConstrCalls(_context);
                }
            }
        }
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            RootBlock clblock_ = c.getValue();
            if (clblock_ instanceof UniqueRootedBlock) {
                ((UniqueRootedBlock)clblock_).validateConstructors(_context);
            }
        }
    }
    //validate missing return
    //validate break,continue ancestors / try/catch/finally / switch/case/default
    //validate dead code
    public void validateReturns(ContextEl _context) {
        PageEl page_ = new PageEl();
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
                            errorsDet.add(deadCode_);
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
                    StringList types_ = method_.getParametersTypes();
                    int len_ = types_.size();
                    EqList<ClassName> pTypes_ = new EqList<ClassName>();
                    for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                        String n_ = types_.get(i);
                        pTypes_.add(new ClassName(n_, i + 1 == len_ && method_.isVarargs()));
                    }
                    if (!r_.isExitable() && !StringList.quickEq(method_.getReturnType(stds_), void_)) {
                        MissingReturnMethod miss_ = new MissingReturnMethod();
                        miss_.setRc(method_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                        miss_.setFileName(className_);
                        miss_.setId(method_.getSignature());
                        miss_.setReturning(method_.getReturnType(stds_));
                        errorsDet.add(miss_);
                    }
                    for (Block d: all_) {
                        RowCol rc_ = d.existDeadCodeInBlock(0, _context.getTabWidth());
                        if (rc_.getRow() > 0) {
                            DeadCodeMethod deadCode_ = new DeadCodeMethod();
                            deadCode_.setFileName(className_);
                            deadCode_.setRc(rc_);
                            deadCode_.setId(method_.getSignature());
                            errorsDet.add(deadCode_);
                        }
                    }
                }
            }
        }
    }

    public StringList getLocalVariablesNames() {
        return localVariablesNames;
    }

    public CustList<RootBlock> getClassBodies() {
        return classesBodies.values();
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

    public CustList<MethodBlock> getMethodBodiesById(String _genericClassName, MethodId _id) {
        CustList<MethodBlock> methods_ = new CustList<MethodBlock>();
        StringList types_ = StringList.getAllTypes(_genericClassName);
        String base_ = types_.first();
        RootBlock r_ = getClassBody(base_);
        for (MethodBlock m: Classes.getMethodBlocks(r_)) {
            if (m.getId().eq(_id)) {
                methods_.add(m);
                break;
            }
        }
        return methods_;
    }
    public CustList<MethodBlock> getMethodBodiesByFormattedId(ContextEl _context, String _genericClassName, MethodId _id) {
        return getMethodBodiesByFormattedId(_context, _id.isStaticMethod(), _genericClassName, _id.getName(), _id.getParametersTypes(), _id.isVararg());
    }
    CustList<MethodBlock> getMethodBodiesByFormattedId(ContextEl _context, boolean _static, String _genericClassName, String _methodName, StringList _parametersTypes, boolean _vararg) {
        CustList<MethodBlock> methods_ = new CustList<MethodBlock>();
        StringList types_ = StringList.getAllTypes(_genericClassName);
        String base_ = types_.first();
        int nbParams_ = _parametersTypes.size();
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            if (!StringList.quickEq(c.getKey(), base_)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (!(b instanceof MethodBlock)) {
                    continue;
                }
                MethodBlock method_ = (MethodBlock) b;
                if (!StringList.quickEq(_methodName, method_.getName())) {
                    continue;
                }
                EqList<ClassName> list_ = method_.getId().getClassNames();
                if (list_.size() != nbParams_) {
                    continue;
                }
                if (_static != method_.isStaticMethod()) {
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
                    String type_ = Templates.format(_genericClassName, list_.get(i).getName(), _context);
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

    public CustList<ConstructorBlock> getConstructorBodiesById(String _genericClassName, ConstructorId _id) {
        return getConstructorBodiesById(_genericClassName, _id.getParametersTypes(), _id.isVararg());
    }
    private CustList<ConstructorBlock> getConstructorBodiesById(String _genericClassName, StringList _parametersTypes, boolean _vararg) {
        CustList<ConstructorBlock> methods_ = new CustList<ConstructorBlock>();
        StringList types_ = StringList.getAllTypes(_genericClassName);
        String base_ = types_.first();
        int nbParams_ = _parametersTypes.size();
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            if (!StringList.quickEq(c.getKey(), base_)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (!(b instanceof ConstructorBlock)) {
                    continue;
                }
                ConstructorBlock method_ = (ConstructorBlock) b;
                EqList<ClassName> list_ = method_.getId().getClassNames();
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
                    String type_ = list_.get(i).getName();
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

    public CustList<ConstructorBlock> getConstructorBodiesByFormattedId(ContextEl _context, String _genericClassName, ConstructorId _id) {
        return getConstructorBodiesByFormattedId(_context, _genericClassName, _id.getParametersTypes(), _id.isVararg());
    }
    private CustList<ConstructorBlock> getConstructorBodiesByFormattedId(ContextEl _context, String _genericClassName, StringList _parametersTypes, boolean _vararg) {
        CustList<ConstructorBlock> methods_ = new CustList<ConstructorBlock>();
        StringList types_ = StringList.getAllTypes(_genericClassName);
        String base_ = types_.first();
        int nbParams_ = _parametersTypes.size();
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            if (!StringList.quickEq(c.getKey(), base_)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (!(b instanceof ConstructorBlock)) {
                    continue;
                }
                ConstructorBlock method_ = (ConstructorBlock) b;
                EqList<ClassName> list_ = method_.getId().getClassNames();
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
                    String type_ = Templates.format(_genericClassName, list_.get(i).getName(), _context);
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
        String base_ = StringList.getAllTypes(_name).first();
        return initializedClasses.getVal(base_);
    }
    public boolean isSuccessfulInitialized(String _name) {
        String base_ = StringList.getAllTypes(_name).first();
        return successfulInitializedClasses.getVal(base_);
    }
    public void initialize(String _name) {
        String base_ = StringList.getAllTypes(_name).first();
        initializedClasses.put(base_, true);
    }
    public void successInitClass(String _name) {
        String base_ = StringList.getAllTypes(_name).first();
        successfulInitializedClasses.put(base_, true);
    }
    public void preInitializeStaticFields(String _className, ContextEl _context) {
        String base_ = StringList.getAllTypes(_className).first();
        ObjectMap<ClassField, FieldBlock> fieldsInfos_;
        fieldsInfos_ = new ObjectMap<ClassField, FieldBlock>();
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String k_ = c.getKey();
            if (!StringList.quickEq(k_, base_)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof FieldBlock) {
                    FieldBlock method_ = (FieldBlock) b;
                    String m_ = method_.getFieldName();
                    if (method_.isStaticField()) {
                        Struct str_ = method_.getDefaultStruct(_context);
                        staticFields.put(new ClassField(base_, m_), str_);
                        continue;
                    }
                    fieldsInfos_.put(new ClassField(base_, m_), method_);
                }
            }
        }
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String k_ = c.getKey();
            if (!StringList.quickEq(k_, base_)) {
                continue;
            }
            for (String s: c.getValue().getAllSuperTypes()) {
                ClassMetaInfo clMetaLoc_ = getClassMetaInfo(s, _context);
                if (clMetaLoc_ == null) {
                    continue;
                }
                CustList<Block> bl_ = getDirectChildren(c.getValue());
                for (Block b: bl_) {
                    if (b instanceof FieldBlock) {
                        FieldBlock method_ = (FieldBlock) b;
                        String m_ = method_.getFieldName();
                        if (method_.isStaticField()) {
                            continue;
                        }
                        fieldsInfos_.put(new ClassField(s, m_), method_);
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
            int i_ = CustList.FIRST_INDEX;
            CustList<Struct> values_ = new CustList<Struct>();
            for (Block b: bl_) {
                if (b instanceof ElementBlock) {
                    ElementBlock method_ = (ElementBlock) b;
                    String m_ = method_.getFieldName();
                    ObjectMap<ClassField,Struct> fields_;
                    fields_ = new ObjectMap<ClassField,Struct>();
                    for (EntryCust<ClassField, FieldBlock> f: fieldsInfos_.entryList()) {
                        FieldBlock inf_ = f.getValue();
                        fields_.put(f.getKey(), inf_.getDefaultStruct(_context));
                    }
                    Struct str_;
                    str_ = new EnumStruct(base_, fields_, i_, m_);
                    staticFields.put(new ClassField(base_, m_), str_);
                    values_.add(str_);
                    i_++;
                }
            }
            values.put(base_, values_);
        }
    }
    public void initializeStaticField(ClassField _clField, Struct _str) {
        staticFields.put(_clField, _str);
    }
    public Struct getStaticField(ClassField _clField) {
        return staticFields.getVal(_clField);
    }

    public boolean isCustomType(String _name) {
        StringList types_ = StringList.getAllTypes(_name);
        String base_ = types_.first();
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
        StringList types_ = StringList.getAllTypes(_name);
        String base_ = types_.first();
        LgNames stds_ = _context.getStandards();
        String void_ = stds_.getAliasVoid();
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
                    String ret_ = method_.getClassName();
                    boolean enumElement_ = b instanceof ElementBlock;
                    boolean staticElement_ = method_.isStaticField();
                    boolean finalElement_ = method_.isFinalField();
                    FieldMetaInfo met_ = new FieldMetaInfo(k_, m_, ret_, staticElement_, finalElement_, enumElement_);
                    infosFields_.put(m_, met_);
                }
                if (b instanceof MethodBlock) {
                    MethodBlock method_ = (MethodBlock) b;
                    MethodId id_ = method_.getId();
                    String ret_ = method_.getReturnType(stds_);
                    MethodMetaInfo met_ = new MethodMetaInfo(method_.getDeclaringType(), id_, method_.getModifier(), ret_);
                    infos_.put(id_, met_);
                }
                if (b instanceof ConstructorBlock) {
                    ConstructorBlock method_ = (ConstructorBlock) b;
                    ConstructorId id_ = method_.getGenericId();
                    ConstructorMetaInfo met_ = new ConstructorMetaInfo(void_);
                    infosConst_.put(id_, met_);
                }
            }
            if (clblock_ instanceof InterfaceBlock) {
                return new ClassMetaInfo(_name, ((InterfaceBlock)clblock_).getDirectGenericSuperClasses(_context), infosFields_,infos_, infosConst_, ClassCategory.INTERFACE);
            }
            ClassCategory cat_ = ClassCategory.CLASS;
            if (clblock_ instanceof EnumBlock) {
                cat_ = ClassCategory.ENUM;
            } else if (clblock_ instanceof InterfaceBlock) {
                cat_ = ClassCategory.INTERFACE;
            }
            boolean abs_ = clblock_.isAbstractType();
            boolean final_ = clblock_.isFinalType();
            return new ClassMetaInfo(_name, ((UniqueRootedBlock) clblock_).getGenericSuperClass(_context), infosFields_,infos_, infosConst_, cat_, abs_, final_);
        }
        return null;
    }
}
