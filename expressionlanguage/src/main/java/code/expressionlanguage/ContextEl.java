package code.expressionlanguage;
import code.expressionlanguage.exceptions.StackOverFlow;
import code.expressionlanguage.methods.Classes;
import code.util.CustList;
import code.util.StringList;
import code.util.ints.MathFactory;
import code.xml.ElementOffsetsNext;

public final class ContextEl {
    private static final String RETURN_LINE = "\n";
    private static final int DEFAULT_TAB_WIDTH = 4;

    private AccessValue accessValue;

    private MathFactory mathFactory;

    private int tabWidth = DEFAULT_TAB_WIDTH;

    private String filesConfName;

    private String language = "java";

    private int stackOverFlow;

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String _language) {
        language = _language;
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
        if (stackOverFlow >= CustList.FIRST_INDEX && stackOverFlow <= importing.size()) {
            throw new StackOverFlow(joinPages());
        }
        importing.add(_page);
    }

    public String getInfos() {
        return analyzing.getInfos();
    }

    public String joinPages() {
        StringList l_ = new StringList();
        for (PageEl p: importing) {
            l_.add(p.getInfos());
        }
        if (analyzing != null) {
            l_.add(analyzing.getInfos());
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
}
