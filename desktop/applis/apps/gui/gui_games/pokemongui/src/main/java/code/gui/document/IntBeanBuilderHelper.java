package code.gui.document;

import aiki.beans.*;
import code.sml.util.*;
import code.util.*;

public interface IntBeanBuilderHelper {
    void clearAnchors();
    void initLine();
    void initPage();
    void initGrid();
    void feedParentsCts();
    void setTitledBorder(String _title);
    void setBackgroundBody();
    void feedParents();
    void formatMessageAnc(BeanRenderWithAppName _with,IntBeanAction _e,String _file, String _key, String... _values);
    void formatMessage(BeanRenderWithAppName _with,String _file, String _key, String... _values);
    String formatMessageRend(BeanRenderWithAppName _with,String _file, String _key, String... _values);
    TranslationsFile file(BeanRenderWithAppName _with, String _file);
    StringMap<TranslationsFile> files(BeanRenderWithAppName _with);
    void formatMessageDir(String _txt);
    void formatMessageDir(String _txt, IntBeanAction _e);
    void formatMessageDirCtsHeader(String _txt);
    void formatMessageDirCts(String _txt);
    void breakLine();
    void paintMetaLabelDisk();
    void addImg(int[][] _img);
    void nextPart();
    void build(String _dest, StringMapObject _form);
    void formatMessageDirAnc(String _txt, IntBeanAction _b);
    void displayDiff(DifficultyBeanForm _form,AbsBeanRender _rend, DifficultyCommon _common, String _file);
    IntBeanChgSubmit button(String _txt);

    void setColCount(int _len);
    void reset();
}
