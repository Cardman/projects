package code.bean;

public final class IntBeanBuilderHelperCommonImpl extends IntBeanBuilderHelperCommon {
    @Override
    public void setTitledBorder(String _title) {
        setIndent(getIndent());
        setHeader(getHeader());
        setRefLk("");
        setPartGroup(getPartGroup());
        setRowGroup(getRowGroup());
        nextPart();
        setIndent(1);
        indent();
        initLine();
        initPage();
        initGrid();
        formatMessage("","","");
        formatMessage("","","","");
        feedParentsCts();
        feedParents();
        feedParents();
    }

    @Override
    public String getLanguage() {
        return ""+getRefLk();
    }

    @Override
    public void formatMessageDir(String _txt) {
        getLanguage();
    }

    @Override
    public void formatMessageDirCts(String _txt) {
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
