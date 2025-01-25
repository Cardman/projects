package code.gui.document;

import aiki.facade.*;
import code.formathtml.render.*;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
import code.sml.util.*;
import code.util.*;
import code.util.core.*;

public abstract class AbsBeanRender {
    private final IdMap<MetaSearchableContent,AbsTextPane> refsSearch = new IdMap<MetaSearchableContent,AbsTextPane>();
    private final IdList<MetaSearchableContent> metaSearchableContents = new IdList<MetaSearchableContent>();
    private final IdMap<AbsCustComponent,AbsCustComponent> parents = new IdMap<AbsCustComponent,AbsCustComponent>();
    private int partGroup;
    private int rowGroup;
    private AbsScrollPane scrollPane;

    public AbsCustComponent build(AbstractProgramInfos _api, FacadeGame _facade, FindBeanEvent _find){
        AbsCustComponent compo_ = build(_api, _facade);
        _find.setFinding(this);
        return compo_;
    }
    public abstract AbsCustComponent build(AbstractProgramInfos _api, FacadeGame _facade);

    protected void displayStringList(AbstractProgramInfos _api, AbsPanel _form, String _file, StringList _list, String _key) {
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        DisplayingBeanCountable.display(this, _api, _form, _file, _list, _key);
        for (String i: _list) {
            nextPart();
            AbsPanel lineType_ = _api.getCompoFactory().newLineBox();
            paintMetaLabelDisk(_api,lineType_);
            formatMessageDir(_api,lineType_,i);
            feedParents(_form,lineType_);
            getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        }
    }

    protected void nextPart() {
        partGroup++;
        rowGroup = 0;
    }

    public static void addImg(AbstractProgramInfos _api, AbsPanel _container, int[][] _img) {
        _container.add(_api.getCompoFactory().newPreparedLabel(ConverterGraphicBufferedImage.decodeToImage(_api.getImageFactory(), _img)));
    }

    public static void paintMetaLabelDisk(AbstractProgramInfos _api, AbsPanel _container) {
        AbstractImage img_ = _api.getImageFactory().newImageArgb(16, 16);
        img_.setColor(GuiConstants.WHITE);
        img_.fillRect(0, 0, 16, 16);
        img_.setColor(GuiConstants.BLACK);
        img_.fillOval(0, 0, 16, 16);
        _container.add(_api.getCompoFactory().newPreparedLabel(img_));
        img_.dispose();
    }

    public void formatMessage(AbstractProgramInfos _api, AbsPanel _form, String _file, String _key, String... _values) {
        String txt_ = formatMessage(_api, _file, _key, _values);
        formatMessageDir(_api, _form, txt_);
    }

    public AbsTextPane formatMessage(AbstractProgramInfos _api, AbsPanel _form, AbsGridConstraints _cts, String _file, String _key, String... _values) {
        String txt_ = formatMessage(_api, _file, _key, _values);
        return formatMessageDir(_api, _form, _cts, txt_);
    }

    public String formatMessage(AbstractProgramInfos _api, String _file, String _key, String... _values) {
        return StringUtil.simpleStringsFormat(files(_api).getVal(_file).getMapping().getVal(_key), _values);
    }

    public void formatMessageDir(AbstractProgramInfos _api, AbsPanel _form, String _txt) {
        AbsTextPane ch_ = message(_api, _txt);
        feedParents(_form, ch_);
        hierarchy(_txt, ch_);
    }

    public AbsTextPane formatMessageDir(AbstractProgramInfos _api, AbsPanel _form, AbsGridConstraints _cts, String _txt) {
        AbsTextPane ch_ = message(_api, _txt);
        ch_.setLineBorder(GuiConstants.BLACK);
        feedParents(_form, _cts, ch_);
        hierarchy(_txt, ch_);
        return ch_;
    }

    protected void feedParents(AbsPanel _form, AbsGridConstraints _cts, AbsCustComponent _ch) {
        _form.add(_ch, _cts);
        getParents().addEntry(_ch, _form);
    }

    protected void feedParents(AbsPanel _form, AbsCustComponent _ch) {
        _form.add(_ch);
        getParents().addEntry(_ch, _form);
    }

    private void hierarchy(String _txt, AbsTextPane _ch) {
        MetaSearchableContent s_ = new MetaSearchableContent(_txt, partGroup, rowGroup);
        metaSearchableContents.add(s_);
        getRefsSearch().addEntry(s_,_ch);
    }

    public static AbsTextPane message(AbstractProgramInfos _api, String _txt) {
        AbsTextPane tp_ = _api.getCompoFactory().newTextPane();
        tp_.setBackground(GuiConstants.WHITE);
        tp_.setForeground(GuiConstants.BLACK);
        tp_.setText(_txt);
        tp_.setEditable(false);
        return tp_;
    }

    public static void paint(AbstractProgramInfos _api, AbsTextPane _tp, CustList<SegmentPart> _segs) {
        String text_ = _tp.getText();
        _tp.setCharacterAttributes(0,text_.length(),_api.getCompoFactory().newAttrSet(), true);
        MetaFont copy_ = _tp.getMetaFont();
        AbsAttrSet att_ = _api.getCompoFactory().newAttrSet();
        att_.addFontFamily(copy_.getFontFamily());
        att_.addFontSize(_tp.getMetaFont().getRealSize());
        att_.addBackground(_tp.getBackgroundValue());
        att_.addForeground(_tp.getForegroundValue());
        _tp.setCharacterAttributes(0,text_.length(),att_,false);
        for (SegmentPart s: _segs) {
            AbsAttrSet attSeg_ = _api.getCompoFactory().newAttrSet();
            attSeg_.addFontFamily(copy_.getFontFamily());
            attSeg_.addBackground(GuiConstants.ORANGE);
            attSeg_.addForeground(_tp.getForegroundValue());
            attSeg_.addFontSize(_tp.getMetaFont().getRealSize());
            _tp.setCharacterAttributes(s.getBegin(),s.getEnd()-s.getBegin(),attSeg_,false);
        }
        AbsAttrSet attSeg_ = _api.getCompoFactory().newAttrSet();
        attSeg_.addFontFamily(copy_.getFontFamily());
        attSeg_.addForeground(_tp.getForegroundValue());
        attSeg_.addFontSize(_tp.getMetaFont().getRealSize());
        _tp.setCharacterAttributes(0,text_.length(),attSeg_,false);
    }

    public static void scroll(AbsBeanRender _rend, AbsCustComponent _dual) {
        AbsCustComponent c_ = _dual;
        AbsScrollPane sc_ = _rend.getScrollPane();
        int x_ = 0;
        int y_ = 0;
        while (c_ != null) {
            AbsCustComponent par_ = _rend.getParents().getVal(c_);
            if (par_ != null) {
                x_ += c_.getXcoords();
                y_ += c_.getYcoords();
            }
            c_ = par_;
        }
        sc_.setHorizontalValue(x_);
        sc_.setVerticalValue(y_);
    }
    public static StringMap<TranslationsFile> files(AbstractProgramInfos _api) {
        return _api.currentLg().getMapping().getVal(MessagesInit.APP_BEAN).getMapping();
    }

    public int getRowGroup() {
        return rowGroup;
    }

    public void setRowGroup(int _r) {
        this.rowGroup = _r;
    }

    public int getPartGroup() {
        return partGroup;
    }

    public void setPartGroup(int _p) {
        this.partGroup = _p;
    }

    public AbsScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(AbsScrollPane _s) {
        this.scrollPane = _s;
    }

    public IdList<MetaSearchableContent> getMetaSearchableContents() {
        return metaSearchableContents;
    }

    public IdMap<AbsCustComponent, AbsCustComponent> getParents() {
        return parents;
    }

    public IdMap<MetaSearchableContent, AbsTextPane> getRefsSearch() {
        return refsSearch;
    }

}
