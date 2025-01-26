package code.gui.document;

import aiki.beans.*;
import aiki.beans.fight.*;
import aiki.facade.*;
import aiki.game.fight.*;
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
    private StringMap<AbsBeanRender> renders;
    private FindBeanEvent event;
    private AbstractProgramInfos factory;
    private FacadeGame facade;
    private AbsCommonFrame frame;
    private final IdList<AbsTextPane> anchors = new IdList<AbsTextPane>();

    public AbsCustComponent build(AbstractProgramInfos _api, FacadeGame _facade, FindBeanEvent _find, StringMapObject _form){
        anchors.clear();
        AbsCustComponent compo_ = build(_api, _facade, _form);
        _find.setFinding(this);
        return compo_;
    }
    public abstract AbsCustComponent build(AbstractProgramInfos _api, FacadeGame _facade, StringMapObject _form);

    protected void displayStringList(AbstractProgramInfos _api, AbsPanel _form, String _file, CustList<String> _list, String _key) {
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        DisplayingBeanCountable.display(this, _api, _form, _file, _list, _key);
        displayStringList(_api, _form, _list);
    }

    protected void displayStringList(AbstractProgramInfos _api, AbsPanel _form, CustList<String> _list) {
        for (String i: _list) {
            nextPart();
            AbsPanel lineType_ = _api.getCompoFactory().newLineBox();
            paintMetaLabelDisk(_api,lineType_);
            formatMessageDir(_api,lineType_,i);
            feedParents(_form,lineType_);
            getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        }
    }

    protected void init(CommonBean _common, FacadeGame _facade, StringMapObject _form) {
        getMetaSearchableContents().clear();
        getParents().clear();
        getRefsSearch().clear();
        setPartGroup(0);
        setRowGroup(0);
        _common.setDataBase(_facade);
        _common.setForms(_form);
        _common.setLanguage(_facade.getLanguage());
        _common.beforeDisplaying();
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

    public AbsTextPane formatMessageAnc(AbstractProgramInfos _api, AbsPanel _form, String _file, String _key, String... _values) {
        AbsTextPane tx_ = formatMessage(_api, _form, _file, _key, _values);
        AbsAttrSet att_ = _api.getCompoFactory().newAttrSet();
        att_.addUnderline(true);
        att_.addForeground(GuiConstants.BLUE);
        tx_.setCharacterAttributes(0,tx_.getText().length(), att_, false);
        anchors.add(tx_);
        return tx_;
    }
    public AbsTextPane formatMessage(AbstractProgramInfos _api, AbsPanel _form, String _file, String _key, String... _values) {
        String txt_ = formatMessage(_api, _file, _key, _values);
        return formatMessageDir(_api, _form, txt_);
    }

    public AbsTextPane formatMessage(AbstractProgramInfos _api, AbsPanel _form, AbsGridConstraints _cts, String _file, String _key, String... _values) {
        String txt_ = formatMessage(_api, _file, _key, _values);
        return formatMessageDir(_api, _form, _cts, txt_);
    }

    public String formatMessage(AbstractProgramInfos _api, String _file, String _key, String... _values) {
        return StringUtil.simpleStringsFormat(files(_api).getVal(_file).getMapping().getVal(_key), _values);
    }

    public AbsTextPane formatMessageDirAnc(AbstractProgramInfos _api, AbsPanel _form, String _txt) {
        AbsTextPane tx_ = formatMessageDir(_api, _form, _txt);
        AbsAttrSet att_ = _api.getCompoFactory().newAttrSet();
        att_.addUnderline(true);
        att_.addForeground(GuiConstants.BLUE);
        tx_.setCharacterAttributes(0,tx_.getText().length(), att_, false);
        anchors.add(tx_);
        return tx_;
    }

    public AbsTextPane formatMessageDir(AbstractProgramInfos _api, AbsPanel _form, String _txt) {
        AbsTextPane ch_ = message(_api, _txt);
        feedParents(_form, ch_);
        hierarchy(_txt, ch_);
        return ch_;
    }

    public AbsTextPane formatMessageDir(AbstractProgramInfos _api, AbsPanel _form, AbsGridConstraints _cts, String _txt) {
        AbsTextPane ch_ = message(_api, _txt);
        ch_.setLineBorder(GuiConstants.BLACK);
        feedParents(_form, _cts, ch_);
        hierarchy(_txt, ch_);
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
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

    public static StringMap<TranslationsFile> filesFight(AbstractProgramInfos _api) {
        return _api.currentLg().getMapping().getVal(MessagesPkBean.APP_BEAN_FIGHT).getMapping();
    }
    public void displayTrPkMoveTarget(AbstractProgramInfos _api, AbsPanel _container, boolean _key, String _file, TrPkMoveTarget _value) {
        formatMessageDir(_api,_container,_api.getCompoFactory().newGridCts(),_value.getMoveTarget().getMove());
        if (_value.getMoveTarget().getTarget().getTeam() == Fight.CST_FOE) {
            formatMessage(_api,_container,_api.getCompoFactory().newGridCts(),_file,MessagesFightFight.M_P_90_ALLY_CHOICES_FOE);
        } else {
            formatMessage(_api,_container,_api.getCompoFactory().newGridCts(),_file,MessagesFightFight.M_P_90_ALLY_CHOICES_PLAYER);
        }
        AbsGridConstraints gr_;
        if (_key) {
            gr_ = _api.getCompoFactory().newGridCts();
        } else {
            gr_ = remainder(_api);
        }
        if (_value.getMoveTarget().getTarget().getPosition() != Fighter.BACK) {
            formatMessageDir(_api,_container,_api.getCompoFactory().newGridCts(),Long.toString(_value.getMoveTarget().getTarget().getPosition()));
            formatMessageDir(_api,_container,gr_,_value.getTranslation());
        } else {
            formatMessage(_api,_container,_api.getCompoFactory().newGridCts(),_file,MessagesFightFight.M_P_90_ALLY_CHOICES_NO);
            formatMessage(_api,_container,gr_,_file,MessagesFightFight.M_P_90_ALLY_CHOICES_NO);
        }
    }
    public void displayActivityOfMoveEnabled(AbstractProgramInfos _api, AbsPanel _container, AbsGridConstraints _cts, String _file, ActivityOfMove _value, String _one, String _two) {
        if (_value.isEnabled()) {
            formatMessage(_api,_container,_cts,_file,_one);
        } else {
            formatMessage(_api,_container,_cts,_file,_two);
        }
    }
    public void displayActivityOfMoveNbRound(AbstractProgramInfos _api, AbsPanel _container, AbsGridConstraints _cts, String _file, ActivityOfMove _value, String _key) {
        if (_value.isIncrementCount()) {
            formatMessageDir(_api,_container,_cts,Long.toString(_value.getNbTurn()));
        } else {
            formatMessage(_api,_container,_cts,_file,_key);
        }
    }
    public void headerCol(AbstractProgramInfos _api, AbsPanel _tableStat, AbsGridConstraints _cts, String _file, String _key) {
        AbsTextPane th_ = formatMessage(_api, _tableStat, _cts, _file, _key);
        th_.setBackground(GuiConstants.YELLOW);
    }

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

    public IdList<AbsTextPane> getAnchors() {
        return anchors;
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

    public StringMap<AbsBeanRender> getRenders() {
        return renders;
    }

    public void setRenders(StringMap<AbsBeanRender> _r) {
        this.renders = _r;
    }

    public FindBeanEvent getEvent() {
        return event;
    }

    public void setEvent(FindBeanEvent _e) {
        this.event = _e;
    }

    public AbstractProgramInfos getFactory() {
        return factory;
    }

    public void setFactory(AbstractProgramInfos _f) {
        this.factory = _f;
    }

    public FacadeGame getFacade() {
        return facade;
    }

    public void setFacade(FacadeGame _f) {
        this.facade = _f;
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public void setFrame(AbsCommonFrame _f) {
        this.frame = _f;
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
