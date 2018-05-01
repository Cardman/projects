package code.expressionlanguage;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.CustomFoundMethod;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.LocalThrowing;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.EnumerableStruct;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.FieldableStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodMetaInfo;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardInterface;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.ElementOffsetsNext;
import code.sml.RowCol;
import code.util.CustList;
import code.util.EntryCust;
import code.util.ObjectMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.MathFactory;

public final class ContextEl implements FieldableStruct, EnumerableStruct,Runnable,Analyzable {
    private static final String RETURN_LINE = "\n";
    private static final int DEFAULT_TAB_WIDTH = 4;

    private MathFactory mathFactory;

    private int tabWidth = DEFAULT_TAB_WIDTH;

    private String filesConfName;

    private int stackOverFlow;

    private Options options = new Options();

    private Struct exception;

    private Struct memoryError;

    private LocalThrowing throwing = new LocalThrowing();

    private CustomFoundConstructor callCtor;

    private CustomFoundMethod callMethod;

    private NotInitializedClass initClass;

    private transient int indexChildType;
    private transient LgNames standards;

    private transient PageEl analyzing;

    private transient boolean staticBlock;

    private transient boolean ambigous;

    private transient ElementOffsetsNext elements;

    private transient Classes classes;

    private transient CustList<PageEl> importing = new CustList<PageEl>();

    private transient String html;

    private transient ContextEl parentThread;

    private transient CustList<ContextEl> children = new CustList<ContextEl>();

    private transient Struct parent;

    private transient String className = "";
    private transient ObjectMap<ClassField, Struct> fields = new ObjectMap<ClassField, Struct>();
    private transient Initializer init;
    private transient String name;
    private transient int ordinal;
    private transient boolean enabled;
    private transient boolean rootAffect;
    private transient boolean analyzingRoot;
    private transient boolean merged;
    private transient String currentVarSetting;
    private transient boolean checkAffectation;
    public ContextEl() {
        this(CustList.INDEX_NOT_FOUND_ELT);
    }

    public ContextEl(int _stackOverFlow) {
        this(_stackOverFlow, new DefaultLockingClass(),new DefaultInitializer());
    }
    public ContextEl(DefaultLockingClass _lock,Initializer _init) {
        this(CustList.INDEX_NOT_FOUND_ELT, _lock,_init);
    }

    public ContextEl(int _stackOverFlow, DefaultLockingClass _lock,Initializer _init) {
        stackOverFlow = _stackOverFlow;
        init = _init;
        classes = new Classes();
        classes.setLocks(_lock);
    }
    public ContextEl(ContextEl _context, String _className,
            String _name, int _ordinal,
            ObjectMap<ClassField,Struct> _fields, Struct _parent) {
        parentThread = _context;
        name = _name;
        ordinal = _ordinal;
        classes = _context.classes;
        options = _context.options;
        standards = _context.standards;
        tabWidth = _context.tabWidth;
        stackOverFlow = _context.stackOverFlow;
        filesConfName = _context.filesConfName;
        memoryError = _context.memoryError;
        className = _className;
        fields = _fields;
        parent = _parent;
        init = _context.init;
        _context.children.add(this);
    }
    @Override
    public void run() {
        String run_ = init.getRunTask(standards);
        String runnable_ = init.getInterfaceTask(standards);
        MethodId id_ = new MethodId(MethodModifier.ABSTRACT, run_, new StringList(), false);
        GeneType type_ = classes.getClassBody(runnable_);
        String base_ = StringList.getAllTypes(className).first();
        ClassMethodId mId_ = TypeUtil.getConcreteMethodsToCall(type_, id_, this).getVal(base_);
        Argument arg_ = new Argument();
        arg_.setStruct(this);
        ProcessMethod.calculateArgument(arg_, mId_.getClassName(), mId_.getConstraints(), new CustList<Argument>(), this);
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }
    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return fields;
    }
    @Override
    public Object getInstance() {
        return this;
    }
    @Override
    public boolean isArray() {
        return false;
    }
    @Override
    public boolean isNull() {
        return false;
    }
    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public Struct getStruct(ClassField _classField) {
        return fields.getVal(_classField);
    }

    @Override
    public void setStruct(ClassField _classField, Struct _value) {
        for (EntryCust<ClassField, Struct> e: fields.entryList()) {
            if (e.getKey().eq(_classField)) {
                e.setValue(_value);
                return;
            }
        }
    }
    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }
    public void initError() {
        memoryError = new StdStruct(new CustomError(), standards.getAliasError());
    }
    @Override
    public ClassMetaInfo getClassMetaInfo(String _name) {
        if (classes == null || !classes.isCustomType(_name)) {
            StringList types_ = StringList.getAllTypes(_name);
            String base_ = types_.first();
            LgNames stds_ = getStandards();
            String void_ = stds_.getAliasVoid();
            for (EntryCust<String, StandardType> c: stds_.getStandards().entryList()) {
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
                StandardType clblock_ = c.getValue();
                for (StandardField f: clblock_.getFields().values()) {
                    String m_ = f.getFieldName();
                    String ret_ = f.getClassName();
                    boolean staticElement_ = f.isStaticField();
                    boolean finalElement_ = f.isFinalField();
                    FieldMetaInfo met_ = new FieldMetaInfo(k_, m_, ret_, staticElement_, finalElement_, false);
                    infosFields_.put(m_, met_);
                }
                for (StandardMethod m: clblock_.getMethods().values()) {
                    MethodId id_ = m.getId();
                    String ret_ = m.getReturnType(stds_);
                    MethodMetaInfo met_ = new MethodMetaInfo(m.getDeclaringType(), id_, m.getModifier(), ret_);
                    infos_.put(id_, met_);
                }
                for (StandardConstructor d: clblock_.getConstructors()) {
                    ConstructorId id_ = d.getGenericId();
                    ConstructorMetaInfo met_ = new ConstructorMetaInfo(void_);
                    infosConst_.put(id_, met_);
                }
                if (clblock_ instanceof StandardInterface) {
                    return new ClassMetaInfo(_name, ((StandardInterface)clblock_).getDirectGenericSuperClasses(this), infosFields_,infos_, infosConst_, ClassCategory.INTERFACE);
                }
                ClassCategory cat_ = ClassCategory.CLASS;
                if (clblock_ instanceof StandardInterface) {
                    cat_ = ClassCategory.INTERFACE;
                }
                boolean abs_ = clblock_.isAbstractType();
                boolean final_ = clblock_.isFinalType();
                return new ClassMetaInfo(_name, ((StandardClass) clblock_).getGenericSuperClass(this), infosFields_,infos_, infosConst_, cat_, abs_, final_);
            }
            return null;
        }
        return classes.getClassMetaInfo(_name, this);
    }
    @Override
    public CustList<GeneType> getClassBodies() {
        CustList<GeneType> types_ = new CustList<GeneType>();
        if (classes == null) {
            for (StandardType t: standards.getStandards().values()) {
                types_.add(t);
            }
            return types_;
        }
        for (StandardType t: standards.getStandards().values()) {
            types_.add(t);
        }
        for (RootBlock t: classes.getClassBodies()) {
            types_.add(t);
        }
        return types_;
    }
    @Override
    public CustList<GeneMethod> getMethodBodiesById(String _genericClassName, MethodId _id) {
        CustList<GeneMethod> methods_ = new CustList<GeneMethod>();
        StringList types_ = StringList.getAllTypes(_genericClassName);
        String base_ = types_.first();
        GeneType r_ = getClassBody(base_);
        if (classes == null || !classes.isCustomType(_genericClassName)) {
            for (EntryCust<MethodId, StandardMethod> m: ((StandardType)r_).getMethods().entryList()) {
                if (m.getKey().eq(_id)) {
                    methods_.add(m.getValue());
                    break;
                }
            }
            return methods_;
        }
        for (MethodBlock m: Classes.getMethodBlocks((RootBlock) r_)) {
            if (m.getId().eq(_id)) {
                methods_.add(m);
                break;
            }
        }
        return methods_;
    }
    public static CustList<GeneConstructor> getConstructorBlocks(GeneType _element) {
        CustList<GeneConstructor> methods_ = new CustList<GeneConstructor>();
        if (_element instanceof RootBlock) {
            for (Block b: Classes.getDirectChildren((RootBlock)_element)) {
                if (b instanceof ConstructorBlock) {
                    methods_.add((ConstructorBlock) b);
                }
            }
        } else {
            for (StandardConstructor m: ((StandardType)_element).getConstructors()) {
                methods_.add(m);
            }
        }
        return methods_;
    }
    public static CustList<GeneMethod> getMethodBlocks(GeneType _element) {
        CustList<GeneMethod> methods_ = new CustList<GeneMethod>();
        if (_element instanceof RootBlock) {
            for (Block b: Classes.getDirectChildren((RootBlock)_element)) {
                if (b instanceof MethodBlock) {
                    methods_.add((MethodBlock) b);
                }
            }
        } else {
            for (StandardMethod m: ((StandardType)_element).getMethods().values()) {
                methods_.add(m);
            }
        }
        return methods_;
    }
    @Override
    public GeneType getClassBody(String _type) {
        if (classes == null) {
            return standards.getStandards().getVal(_type);
        }
        if (classes.isCustomType(_type)) {
            return classes.getClassBody(_type);
        }
        return standards.getStandards().getVal(_type);
    }

    @Override
    public Options getOptions() {
        return options;
    }

    public void setOptions(Options _options) {
        options = _options;
    }

    public void setStackOverFlow(int _stackOverFlow) {
        stackOverFlow = _stackOverFlow;
    }

    public void setOffsetPossibleLastPage(int _offset) {
        if (importing.isEmpty()) {
            return;
        }
        importing.last().setOffset(_offset);
    }

    public void addToOffsetPossibleLastPage(int _offset) {
        if (importing.isEmpty()) {
            return;
        }
        importing.last().addToOffset(_offset);
    }

    public MathFactory getMathFactory() {
        return mathFactory;
    }

    public void setMathFactory(MathFactory _mathFactory) {
        mathFactory = _mathFactory;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
    }

    public String getFilesConfName() {
        return filesConfName;
    }

    public void setFilesConfName(String _filesConfName) {
        filesConfName = _filesConfName;
    }

    public ElementOffsetsNext getElements() {
        return elements;
    }

    public void setElements(ElementOffsetsNext _elements) {
        elements = _elements;
    }

    @Override
    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes _classes) {
        classes = _classes;
    }
    public void clearPages() {
        importing.clear();
    }
    public boolean isEmptyPages() {
        return importing.isEmpty();
    }

    public int nbPages() {
        return importing.size();
    }

    public void removeLastPage() {
        importing.removeLast();
    }

    public void addPage(PageEl _page) {
        LgNames stds_ = getStandards();
        String sof_ = stds_.getAliasSof();
        if (stackOverFlow >= CustList.FIRST_INDEX && stackOverFlow <= importing.size()) {
            exception = new StdStruct(new CustomError(joinPages()),sof_);
        } else {
            importing.add(_page);
        }
    }

    @Override
    public String getCurrentFileName() {
        if (isEmptyPages() || getLastPage().getCurrentBlock() == null) {
            return null;
        }
        return getLastPage().getCurrentBlock().getFile().getFileName();
    }
    public Block getCurrentBlock() {
        if (isEmptyPages()) {
            return null;
        }
        return getLastPage().getCurrentBlock();
    }
    @Override
    public RowCol getCurrentLocation() {
        return analyzing.getTrace();
    }
    public String getInfos() {
        return analyzing.getInfos(this);
    }

    public String joinPages() {
        StringList l_ = new StringList();
        for (PageEl p: importing) {
            l_.add(p.getInfos(this));
        }
        if (analyzing != null) {
            l_.add(analyzing.getInfos(this));
        }
        return l_.join(RETURN_LINE);
    }

    public PageEl getAnalyzing() {
        return analyzing;
    }

    public void setAnalyzing(PageEl _analyzing) {
        analyzing = _analyzing;
    }

    public boolean isStaticBlock() {
        return staticBlock;
    }

    public void setStaticBlock(boolean _staticBlock) {
        staticBlock = _staticBlock;
    }

    public PageEl getLastPage() {
        if (analyzing != null) {
            return analyzing;
        }
        return importing.last();
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String _html) {
        html = _html;
    }

    @Override
    public boolean isAmbigous() {
        return ambigous;
    }

    @Override
    public void setAmbigous(boolean _ambigous) {
        ambigous = _ambigous;
    }

    @Override
    public LgNames getStandards() {
        return standards;
    }

    public void setStandards(LgNames _standards) {
        standards = _standards;
    }

    @Override
    public String getGlobalClass() {
        return getLastPage().getGlobalClass();
    }

    @Override
    public void setGlobalClass(String _globalClass) {
        getLastPage().setGlobalClass(_globalClass);
    }

    @Override
    public StringMap<LoopVariable> getVars() {
        return getLastPage().getVars();
    }

    @Override
    public StringMap<LocalVariable> getLocalVars() {
        return getLastPage().getLocalVars();
    }

    @Override
    public StringMap<LocalVariable> getCatchVars() {
        return getLastPage().getCatchVars();
    }

    @Override
    public StringMap<LocalVariable> getParameters() {
        return getLastPage().getParameters();
    }

    @Override
    public int getOffset() {
        return getLastPage().getOffset();
    }

    @Override
    public void setOffset(int _offset) {
        getLastPage().setOffset(_offset);
    }

    @Override
    public boolean isStaticContext() {
        return getLastPage().isStaticContext();
    }

    @Override
    public void setStaticContext(boolean _staticContext) {
        getLastPage().setStaticContext(_staticContext);
    }

    public boolean callsOrException() {
        if (calls()) {
            return true;
        }
        return exception != null;
    }

    public boolean calls() {
        if (callMethod != null) {
            return true;
        }
        if (callCtor != null) {
            return true;
        }
        if (initClass != null) {
            return true;
        }
        return false;
    }
    public Struct getException() {
        return exception;
    }

    public void setException(Struct _exception) {
        exception = _exception;
    }
    public LocalThrowing getThrowing() {
        return throwing;
    }

    public CustomFoundConstructor getCallCtor() {
        return callCtor;
    }

    public void setCallCtor(CustomFoundConstructor _callCtor) {
        callCtor = _callCtor;
    }

    public CustomFoundMethod getCallMethod() {
        return callMethod;
    }

    public void setCallMethod(CustomFoundMethod _callMethod) {
        callMethod = _callMethod;
    }

    public NotInitializedClass getInitClass() {
        return initClass;
    }

    public void setInitClass(NotInitializedClass _initClass) {
        initClass = _initClass;
    }

    public Struct getMemoryError() {
        return memoryError;
    }

    public Initializer getInit() {
        return init;
    }

    public void setInit(Initializer _init) {
        init = _init;
    }

    @Override
    public int getCurrentChildTypeIndex() {
        return indexChildType;
    }

    @Override
    public void setCurrentChildTypeIndex(int _index) {
        indexChildType = _index;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public boolean isEnabledDotted() {
        return enabled;
    }

    @Override
    public void setEnabledDotted(boolean _enabled) {
        enabled = _enabled;
    }

    @Override
    public boolean isRootAffect() {
        return rootAffect;
    }

    @Override
    public void setRootAffect(boolean _rootAffect) {
        rootAffect = _rootAffect;
    }

    @Override
    public boolean isAnalyzingRoot() {
        return analyzingRoot;
    }

    @Override
    public void setAnalyzingRoot(boolean _analyzingRoot) {
        analyzingRoot = _analyzingRoot;
    }

    @Override
    public boolean isMerged() {
        return merged;
    }

    @Override
    public void setMerged(boolean _merged) {
        merged = _merged;
    }

    public boolean isCheckAffectation() {
        return checkAffectation;
    }

    public void setCheckAffectation(boolean _checkAffectation) {
        checkAffectation = _checkAffectation;
    }

    @Override
    public String getCurrentVarSetting() {
        return currentVarSetting;
    }

    public void setCurrentVarSetting(String _currentVarSetting) {
        currentVarSetting = _currentVarSetting;
    }
}
