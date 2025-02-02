package aiki.beans;

import code.sml.util.*;
import code.util.*;

public final class MockBeanBuilderHelper extends IntBeanBuilderHelper {
    public MockBeanBuilderHelper() {
        setGenInput(new MockBeanGeneInput());
    }
    @Override
    public void setTitledBorder(String _title) {
        setColCount(getColCount());
        setPartGroup(getPartGroup());
        setRowGroup(getRowGroup());
    }

    @Override
    public StringMap<TranslationsFile> files(String _with) {
        return getTranslations().getMapping().getVal(getFacade().getLanguage()).getMapping().getVal(_with).getMapping();
    }

    @Override
    public void formatMessageDir(String _txt) {
        setColCount(getColCount());
        setPartGroup(getPartGroup());
        setRowGroup(getRowGroup());
    }

    @Override
    public void formatMessageDirCtsHeader(String _txt) {
        incColIndex();
    }

    @Override
    public void formatMessageDirCts(String _txt) {
        incColIndex();
    }

    @Override
    public void breakLine() {
        setColCount(getColCount());
        setPartGroup(getPartGroup());
        setRowGroup(getRowGroup());
    }

    @Override
    public void paintMetaLabelDisk() {
        setColCount(getColCount());
        setPartGroup(getPartGroup());
        setRowGroup(getRowGroup());
    }

    @Override
    public void feedParentsCts() {
        incColIndex();
        super.feedParentsCts();
    }
    @Override
    public void formatMessageDir(String _txt, IntBeanAction _e){
        getAnchors().add(_e);
    }
    @Override
    public void addImg(int[][] _img) {
        setColCount(getColCount());
        setPartGroup(getPartGroup());
        setRowGroup(getRowGroup());
    }

    @Override
    public void build(String _dest, StringMapObject _form) {
        BeanRenderWithAppName target_ = getRenders().getVal(_dest);
        clearAnchors();
        initPage();
        target_.setBuilder(this);
        target_.build(target_.getFacade(), _form);
        getGridLast().removeQuicklyLast();
    }
}
