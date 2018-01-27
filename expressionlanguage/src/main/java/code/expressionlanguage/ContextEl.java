package code.expressionlanguage;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodMetaInfo;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardInterface;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.sml.ElementOffsetsNext;
import code.util.CustList;
import code.util.EntryCust;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.MathFactory;

public final class ContextEl {
    private static final String RETURN_LINE = "\n";
    private static final int DEFAULT_TAB_WIDTH = 4;

    private AccessValue accessValue;

    private MathFactory mathFactory;

    private int tabWidth = DEFAULT_TAB_WIDTH;

    private String filesConfName;

    private int stackOverFlow;

    private Options options = new Options();

    private transient LgNames standards = new LgNames();

    private transient PageEl analyzing;

    private transient boolean staticBlock;

    private transient boolean ambigous;

    private transient ElementOffsetsNext elements;

    private transient Classes classes;

    private transient CustList<PageEl> importing = new CustList<PageEl>();

    private transient String currentUrl;

    private transient String html;

    private transient String resourceUrl;

    private transient int nextIndex;

    public ContextEl() {
        this(CustList.INDEX_NOT_FOUND_ELT);
    }

    public ContextEl(int _stackOverFlow) {
        stackOverFlow = _stackOverFlow;
    }
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
    public GeneType getClassBody(String _type) {
        if (classes == null) {
            return standards.getStandards().getVal(_type);
        }
        if (classes.isCustomType(_type)) {
            return classes.getClassBody(_type);
        }
        return standards.getStandards().getVal(_type);
    }

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

    public AccessValue getAccessValue() {
        return accessValue;
    }

    public void setAccessValue(AccessValue _accessValue) {
        accessValue = _accessValue;
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
            throw new InvokeException(new StdStruct(new CustomError(joinPages()),sof_));
        }
        importing.add(_page);
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

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String _currentUrl) {
        currentUrl = _currentUrl;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String _html) {
        html = _html;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String _resourceUrl) {
        resourceUrl = _resourceUrl;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int _nextIndex) {
        nextIndex = _nextIndex;
    }

    public boolean isAmbigous() {
        return ambigous;
    }

    public void setAmbigous(boolean _ambigous) {
        ambigous = _ambigous;
    }

    public LgNames getStandards() {
        return standards;
    }

    public void setStandards(LgNames _standards) {
        standards = _standards;
    }
}
