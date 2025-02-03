package aiki.beans;

public final class MockBeanBuilderHelper extends IntBeanBuilderHelper {
    public MockBeanBuilderHelper() {
        setGenInput(new MockBeanGeneInput());
    }
    @Override
    public void setTitledBorder(String _title) {
        setPartGroup(getPartGroup());
        setRowGroup(getRowGroup());
    }

    @Override
    public void formatMessageDir(String _txt) {
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
        setPartGroup(getPartGroup());
        setRowGroup(getRowGroup());
    }

    @Override
    public void paintMetaLabelDisk() {
        setPartGroup(getPartGroup());
        setRowGroup(getRowGroup());
    }

    @Override
    public void feedParentsCts() {
        super.feedParentsCts();
        incColIndex();
    }
    @Override
    public void formatMessageDir(String _txt, IntBeanAction _e){
        getAnchors().add(_e);
    }
    @Override
    public void addImg(int[][] _img) {
        setPartGroup(getPartGroup());
        setRowGroup(getRowGroup());
    }

}
