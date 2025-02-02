package aiki.beans;

import aiki.facade.FacadeGame;
import code.sml.util.*;
import code.util.*;
import code.util.core.*;

public abstract class IntBeanBuilderHelper {
    private StringMap<BeanRenderWithAppName> renders = new StringMap<BeanRenderWithAppName>();
    private int partGroup;
    private int rowGroup;
    private int colCount;
    private int colIndex;
    private IntBeanGeneInput genInput;
    private final CustList<BoolVal> gridLast = new CustList<BoolVal>();
    private FacadeGame facade;
    private Translations translations;
    private final IdList<IntBeanAction> anchors = new IdList<IntBeanAction>();
    protected IntBeanBuilderHelper() {
    }

    public void clearAnchors() {
        anchors.clear();
    }

    public void initLine(){
        getGridLast().add(BoolVal.FALSE);
    }
    public void initPage(){
        getGridLast().add(BoolVal.FALSE);
    }
    public void initGrid(){
        getGridLast().add(BoolVal.TRUE);
    }
    public void feedParentsCts(){
        getGridLast().removeQuicklyLast();
    }
    public abstract void setTitledBorder(String _title);
    public void feedParents(){
        getGridLast().removeQuicklyLast();
    }

    public void formatMessageAnc(String _with,IntBeanAction _e,String _file, String _key, String... _values) {
        String txt_ = formatMessageRend(_with,_file, _key, _values);
        formatMessageDir(txt_,_e);
    }

    public void formatMessage(String _with,String _file, String _key, String... _values) {
        String txt_ = formatMessageRend(_with,_file, _key, _values);
        formatMessageDir(txt_);
    }

    public String formatMessageRend(String _with,String _file, String _key, String... _values) {
        return StringUtil.simpleStringsFormat(file(_with, _file).getMapping().getVal(_key), _values);
    }

    public TranslationsFile file(String _with, String _file) {
        return files(_with).getVal(_file);
    }
    public abstract StringMap<TranslationsFile> files(String _with);
    public abstract void formatMessageDir(String _txt);
    public abstract void formatMessageDir(String _txt, IntBeanAction _e);
    public abstract void formatMessageDirCtsHeader(String _txt);
    public abstract void formatMessageDirCts(String _txt);
    public abstract void breakLine();
    public abstract void paintMetaLabelDisk();
    public abstract void addImg(int[][] _img);
    public abstract void build(String _dest, StringMapObject _form);

    public IntBeanChgSubmit button(String _txt) {
        return getGenInput().newSubmit(_txt);
    }
    public IntBeanGeneInput getGenInput() {
        return genInput;
    }

    protected void setGenInput(IntBeanGeneInput _g) {
        this.genInput = _g;
    }

    public void nextPart() {
        partGroup++;
        rowGroup = 0;
    }

    public void incColIndex() {
        colIndex=(getColIndex() + 1) % colCount;
    }

    public CustList<BoolVal> getGridLast() {
        return gridLast;
    }

    public int getColIndex() {
        return colIndex;
    }

    public StringMap<BeanRenderWithAppName> getRenders() {
        return renders;
    }

    public void setRenders(StringMap<BeanRenderWithAppName> _r) {
        this.renders = _r;
    }
    public int getPartGroup() {
        return partGroup;
    }

    public void setPartGroup(int _p) {
        this.partGroup = _p;
    }

    public int getRowGroup() {
        return rowGroup;
    }

    public void setRowGroup(int _r) {
        this.rowGroup = _r;
    }
    public int getColCount() {
        return colCount;
    }

    public void setColCount(int _c) {
        this.colCount = _c;
    }

    public FacadeGame getFacade() {
        return facade;
    }

    public void setFacade(FacadeGame _f) {
        this.facade = _f;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations _t) {
        this.translations = _t;
    }

    public IdList<IntBeanAction> getAnchors() {
        return anchors;
    }
}
