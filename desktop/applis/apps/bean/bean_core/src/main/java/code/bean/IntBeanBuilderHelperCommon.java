package code.bean;

import code.formathtml.render.*;
import code.sml.util.Translations;
import code.sml.util.TranslationsFile;
import code.util.Ints;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class IntBeanBuilderHelperCommon {
    private final IntBeanBuilderHelperContent content = new IntBeanBuilderHelperContent();

    public IntBeanBuilderHelperContent getContent() {
        return content;
    }

    public void initLine(){
        incr();
    }

    public void initPage(){
        incr();
    }
    public void initGrid(){
        incr();
    }

    private void incr() {
        content.getColCount().add(0);
        content.getColIndex().add(0);
        nextPart();
    }
    public void feedParentsCts(){
        decr();
    }
    public void feedParents(){
        decr();
    }

    public void decr() {
        content.getColCount().removeQuicklyLast();
        content.getColIndex().removeQuicklyLast();
        nextPart();
    }
    public void nextPart() {
        content.getMetaSearchableContents().add(new MetaSearchableContent(null,getFormGroup(), getPartGroup(), getRowGroup()));
        content.setPartGroup(content.getPartGroup()+1);
        content.setRowGroup(0);
    }

    public void indent() {
        int indent_ = content.getIndent();
        for (int i = 0; i < indent_; i++) {
            paintIndent();
        }
    }

    public void formatMessage(String _with,String _file, String _key, String... _values) {
        String txt_ = formatMessageRend(_with,_file, _key, _values);
        formatMessageDir(txt_);
    }

    public void incColIndex() {
        getContent().incColIndex();
    }

    public int colCount() {
        return getColCount().last();
    }

    public Ints getColCount() {
        return getContent().getColCount();
    }

    public int colIndex() {
        return getColIndex().last();
    }

    public Ints getColIndex() {
        return getContent().getColIndex();
    }

    public void colCount(int _c) {
        getContent().colCount(_c);
    }
    public int getFormGroup() {
        return getContent().getFormGroup();
    }
    public void setFormGroup(int _p) {
        this.getContent().setFormGroup(_p);
    }
    public int getPartGroup() {
        return getContent().getPartGroup();
    }

    public void setPartGroup(int _p) {
        this.getContent().setPartGroup(_p);
    }

    public int getRowGroup() {
        return getContent().getRowGroup();
    }

    public void setRowGroup(int _r) {
        this.getContent().setRowGroup(_r);
    }

    public int getIndent() {
        return getContent().getIndent();
    }

    public void setIndent(int _i) {
        this.getContent().setIndent(_i);
    }

    public String getRefLk() {
        return getContent().getRefLk();
    }

    public void setRefLk(String _r) {
        this.getContent().setRefLk(_r);
    }

    public int getHeader() {
        return getContent().getHeader();
    }

    public void setHeader(int _h) {
        this.getContent().setHeader(_h);
    }

    public Ints getOrderedLists() {
        return getContent().getOrderedLists();
    }

    public Translations getTranslations() {
        return getContent().getTranslations();
    }
    public String formatMessageRend(String _with,String _file, String _key, String... _values) {
        if (_values.length == 0) {
            return StringUtil.nullToEmpty(file(_with, _file).getMapping().getVal(_key));
        }
        return StringUtil.simpleStringsFormat(file(_with, _file).getMapping().getVal(_key), _values);
    }

    public TranslationsFile file(String _with, String _file) {
        return files(_with).getVal(_file);
    }
    public StringMap<TranslationsFile> files(String _with) {
        return getTranslations().getMapping().getVal(getLanguage()).getMapping().getVal(_with).getMapping();
    }
    public void setTranslations(Translations _t) {
        this.getContent().setTranslations(_t);
    }
    public abstract void setTitledBorder(String _title);

    public abstract String getLanguage();
    public abstract void formatMessageDir(String _txt);
    public abstract void formatMessageDirCts(String _txt);
    public abstract void paintMetaLabelDisk();
    public abstract void paintNb(int _nb);
    public abstract void paintIndent();
    public abstract void addImg(int[][] _img);
    public abstract void addImgCts(int[][] _img, String _tip);
}
