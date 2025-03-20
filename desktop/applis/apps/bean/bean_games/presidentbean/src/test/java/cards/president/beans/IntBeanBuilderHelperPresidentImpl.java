package cards.president.beans;

import code.bean.IntBeanBuilderHelperCommon;

public final class IntBeanBuilderHelperPresidentImpl extends IntBeanBuilderHelperCommon {
    @Override
    public void setTitledBorder(String _title) {
        setIndent(getIndent());
        setHeader(getHeader());
        setRefLk("");
        setPartGroup(getPartGroup());
        setRowGroup(getRowGroup());
        setIndent(1);
        indent();
        initLine();
        initPage();
        initGrid();
        formatMessage("","","");
        feedParentsCts();
        feedParents();
        feedParents();
    }

    @Override
    public String getLanguage() {
        return BeanPresidentCommonTs.EN;
    }

    @Override
    public void formatMessageDir(String _txt) {
        getLanguage();
    }

    @Override
    public void formatMessageDirCtsHeader(String _txt) {
        getLanguage();
    }

    @Override
    public void formatMessageDirCts(String _txt) {
        getLanguage();
    }

    @Override
    public void breakNext() {
        getLanguage();
    }

    @Override
    public void paintMetaLabelDisk() {
        getLanguage();
    }

    @Override
    public void paintNb(int _nb) {
        getLanguage();
    }

    @Override
    public void paintIndent() {
        getLanguage();
    }

    @Override
    public void addImg(int[][] _img) {
        getLanguage();
    }

    @Override
    public void addImgCts(int[][] _img, String _tip) {
        getLanguage();
    }
}
