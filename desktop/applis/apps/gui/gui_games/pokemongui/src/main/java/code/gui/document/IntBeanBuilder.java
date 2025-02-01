package code.gui.document;

import code.sml.util.*;

public interface IntBeanBuilder {
    void initPagePanel();
    void initLinePanel();
    void initGridPanel();
    void addPanel();
    void paintMetaLabelDisk();
    void addImg(int[][] _i);
    void formatMessage(TranslationsLg _lg, BeanRenderWithAppName _current, String _file, String _key, String... _values);
    void formatMessageDir(String _txt);
    void formatMessageDirCts(String _txt);
    void simpleConstraints();
    void remainder();
    void remainder(int _index, int _count);
    /*
    protected static AbsGridConstraints remainder(AbstractProgramInfos _api) {
        AbsGridConstraints cts_ = _api.getCompoFactory().newGridCts();
        cts_.gridwidth(GuiConstants.REMAINDER);
        return cts_;
    }

    protected static AbsGridConstraints remainder(AbstractProgramInfos _api, int _index, int _count) {
        AbsGridConstraints cts_ = _api.getCompoFactory().newGridCts();
        if (_index+1 == _count) {
            cts_.gridwidth(GuiConstants.REMAINDER);
        }
        return cts_;
    }
*/
}
