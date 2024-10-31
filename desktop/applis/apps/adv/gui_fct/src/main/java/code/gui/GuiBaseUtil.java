package code.gui;

import code.gui.events.*;
import code.gui.files.MessagesGuiFct;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractLightProgramInfos;
import code.gui.initialize.AbstractProgramInfos;
import code.images.*;
import code.sml.*;
import code.sml.Element;
import code.stream.*;
import code.threads.AbstractDate;
import code.threads.AbstractDateFactory;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.NaturalComparator;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.*;

public final class GuiBaseUtil {
//    static final String ACCESS = "gui.groupframe";
    public static final String ATTR_VALUE = "0";
    public static final String ATTR_BASE = "1";
    private static final byte SIXTY_FOUR_BITS = 64;
    private static final byte SIXTEEN_BITS = 16;
    private static final byte FOUR_BITS = 4;
    private static final byte THREE_COLORS_BYTES = 3;
//    private static final String TITLE = "title";
//    private static final String MESSAGE = "message";

    private GuiBaseUtil() {

    }
    public static boolean action(AbsActionListenerAct _c,AbsActionListener _a) {
        if (_c.act()) {
            _a.action();
            return true;
        }
        return false;
    }
    public static boolean action(AbsActionListenerAct _c,AbsMouseListenerIntRel _a, AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (_c.act()) {
            _a.mouseReleased(_location, _keyState, _buttons);
            return true;
        }
        return false;
    }
//    public static boolean actionReleased(AbsActionListenerAct _c,AbsMouseListenerWithoutClickEnter _a, AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        if (_c.act()) {
//            _a.mouseReleased(_location, _keyState, _buttons);
//            return true;
//        }
//        return false;
//    }
    public static boolean actionPressed(AbsActionListenerAct _c,AbsMouseListenerWithoutClickEnter _a, AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (_c.act()) {
            _a.mousePressed(_location, _keyState, _buttons);
            return true;
        }
        return false;
    }
//    public static boolean actionEntered(AbsActionListenerAct _c,AbsMouseListenerEer _a, AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        if (_c.act()) {
//            _a.mouseEntered(_location, _keyState, _buttons);
//            return true;
//        }
//        return false;
//    }
//    public static boolean actionExited(AbsActionListenerAct _c,AbsMouseListenerEer _a, AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        if (_c.act()) {
//            _a.mouseExited(_location, _keyState, _buttons);
//            return true;
//        }
//        return false;
//    }

    public static CustList<AbsShortListTree> removeTreeSelectionListeners(AbsTreeGui _tr) {
        CustList<AbsShortListTree> tr_ = _tr.getTreeSelectionListeners();
        int s_ = tr_.size();
        for (int i = 0; i < s_; i++) {
            _tr.removeTreeSelectionListener(tr_.get(i));
        }
        return tr_;
    }

    public static void addTreeSelectionListeners(AbsTreeGui _tr, CustList<AbsShortListTree> _list) {
        int s_ = _list.size();
        for (int i = 0; i < s_; i++) {
            _tr.addTreeSelectionListener(_list.get(i));
        }
    }

    public static CustList<AbsListSelectionListener> removeListSelectionListeners(AbsTableGui _tr) {
        CustList<AbsListSelectionListener> tr_ = _tr.getListSelectionListeners();
        int s_ = tr_.size();
        for (int i = 0; i < s_; i++) {
            _tr.removeListSelectionListener(tr_.get(i));
        }
        return tr_;
    }

    public static void addListSelectionListeners(AbsTableGui _tr, CustList<AbsListSelectionListener> _list) {
        int s_ = _list.size();
        for (int i = 0; i < s_; i++) {
            _tr.addListSelectionListener(_list.get(i));
        }
    }
    public static void setSelectedIndices(AbsTableGui _tr, int[] _vs) {
        _tr.clearSelect();
        int s_ = _tr.getRowCount();
        for (int i : _vs) {
            if (i < s_) {
                _tr.addSelectInterval(i, i);
            }
        }
    }
    public static void removeActionListeners(AbsButton _tr) {
        CustList<AbsActionListener> tr_ = _tr.getActionListeners();
        int s_ = tr_.size();
        for (int i = 0; i < s_; i++) {
            _tr.removeActionListener(tr_.get(i));
        }
    }
    public static void recalculateWindow(ChangeableTitle _compo) {
        recalculate(_compo.getPane());
    }
    public static void recalculate(AbsCustComponent _compo) {
        _compo.setSize(_compo.getPreferredSizeValue());
        AbsCustComponent curr_ = _compo;
        while(curr_ != null) {
            curr_.recalculate();
            if (curr_ instanceof AbsScrollPane) {
                ((AbsScrollPane)curr_).recalculateViewport();
            }

            AbsCustComponent child_ = childAt(curr_, 0);
            if (child_ != null) {
                curr_ = child_;
            } else {
                while(curr_ != null) {
                    AbsCustComponent par_ = curr_.getParent();
                    int index_ = indexOf(par_, curr_);
                    AbsCustComponent next_ = childAt(par_, index_ + 1);
                    if (next_ != null) {
                        curr_ = next_;
                        break;
                    }

                    curr_ = parent(par_,_compo);
                }
            }
        }
    }

    private static AbsCustComponent parent(AbsCustComponent _par, AbsCustComponent _compo) {
        if (_par == _compo) {
            return null;
        }
        return _par;
    }

    private static AbsCustComponent childAt(AbsCustComponent _elt, int _index) {
        if (_elt == null) {
            return null;
        }
        CustList<AbsCustComponent> children_ = _elt.getChildren();
        if (!children_.isValidIndex(_index)) {
            return null;
        }
        return children_.get(_index);
    }

    private static int indexOf(AbsCustComponent _par, AbsCustComponent _elt) {
        if ( _par == null) {
            return -1;
        }
        return indexOf(_par.getChildren(), _elt);
    }

    public static int indexOf(CustList<AbsCustComponent> _list, AbsCustComponent _elt) {
        int len_ = _list.size();

        for(int i = 0; i < len_; i++) {
            AbsCustComponent c = _list.get(i);
            if (c == _elt) {
                return i;
            }
        }

        return -1;
    }

    public static AbsPreparedLabel prep(AbstractImageFactory _img) {
        return _img.newImageArgb(1,1).newAbsPreparedLabel();
    }

    public static void invokeLater(Runnable _r, AbstractLightProgramInfos _compoFactory) {
        _compoFactory.getCompoFactory().invokeLater(_r);
    }

    public static void setOrient(AbsSlider _slider,int _o) {
        if (GuiConstants.getOrient(_o) == GuiConstants.HORIZONTAL) {
            _slider.setHorizontal();
        } else {
            _slider.setVertical();
        }
    }

    public static void setHorizProg(AbsProgressBar _bar, boolean _horiz) {
        if (_horiz) {
            _bar.setHorizontal();
        } else {
            _bar.setVertical();
        }
    }

    public static void setSelectTable(AbsTableGui _table, boolean _mult) {
        if (_mult) {
            _table.setMultiSelect();
        } else {
            _table.setSingleSelect();
        }
    }

    public static boolean eq(AbstractImage _imgOne, AbstractImage _imgTwo) {
        if (_imgOne.getWidth() != _imgTwo.getWidth()) {
            return false;
        }
        if (_imgOne.getHeight() != _imgTwo.getHeight()) {
            return false;
        }
        int w_ = _imgOne.getWidth();
        int h_ = _imgOne.getHeight();
        for (int i = IndexConstants.FIRST_INDEX; i < w_; i++) {
            for (int j = IndexConstants.FIRST_INDEX; j < h_; j++) {
                if (_imgOne.getRGB(i, j) != _imgTwo.getRGB(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String getTimeText(AbstractThreadFactory _fact, String _format) {
        AbstractDateFactory dateFactory_ = _fact.getDateFactory();
        AbstractDate date_ = dateFactory_.newDate(_fact.millis());
        return date_.format(dateFactory_,_format);
    }

    public static String getTime(AbstractThreadFactory _fact, String _format) {
        return getTimeText(_fact, MessagesGuiFct.time(_format));
    }

    public static String getTimeText(AbstractThreadFactory _fact, String _separatorDate, String _sep, String _separatorTime) {
        AbstractDateFactory dateFactory_ = _fact.getDateFactory();
        AbstractDate date_ = dateFactory_.newDate(_fact.millis());
        return date_.format(dateFactory_, MessagesGuiFct.date(_separatorDate) + _sep + MessagesGuiFct.time(_separatorTime));
    }

    public static void removeAllListeners(AbsCommonFrame _com) {
        for (AbsWindowListenerClosing l: _com.getWindowListenersDef()) {
            _com.removeWindowListener(l);
        }
        _com.dispatchExit();
    }

    public static void tryExit(AbsCommonFrame _comm, AbstractProgramInfos _frames) {
        if(!_frames.getFrames().first().getCommonFrame().isVisible()) {
            removeAllListeners(_comm);
        }
    }

    public static void choose(AbsOpenQuit _this, AbstractProgramInfos _frams) {
        choose(_frams,_this);
    }
    public static void choose(AbstractProgramInfos _list, AbsOpenQuit _this) {
        _list.getFrames().add(_this);
    }

    public static boolean tryToReopen(String _applicationName, AbstractProgramInfos _list) {
        for (AbsOpenQuit g: _list.getFrames()) {
            if (StringUtil.quickEq(g.getApplicationName(), _applicationName)) {
                g.getCommonFrame().pack();
                g.getCommonFrame().setVisible(true);
                return true;
            }
        }
        return false;
    }

//    public static boolean canChangeLanguageAll(AbstractProgramInfos _list) {
//        for (AbsGroupFrame g: _list.getFrames()) {
//            if (!g.canChangeLanguage()) {
//                return false;
//            }
//        }
//        return true;
//    }

    public static boolean changeStaticLanguage(String _language, AbstractProgramInfos _list) {
        if (_language.isEmpty()) {
            return false;
        }
        _list.getFrames().first().changeLanguage(_language);
        return true;
    }
//
//    public static void showDialogError(int _errorMessage, AbsCommonFrame _com) {
//        StringMap<String> messages_ = _com.getFrames().getFrames().first().getMessages();
//        _com.getFrames().getMessageDialogAbs().input(_com, messages_.getVal(MESSAGE), messages_.getVal(TITLE), _errorMessage);
//    }
    public static CustList<String> getKeysAction(AbsTextPane _txt) {
        return _txt.getActionsMap().getKeys();
    }

    public static AbsEnabledAction getAction(AbsCustComponent _txt,int _a, int _b) {
        return _txt.getActionsMap().getVal(buildKey(_a, _b));
    }

    public static String buildKey(int _a, int _b) {
        return _a + "," + _b;
    }

    public static CustList<AbsEnabledAction> getActions(AbsTextPane _txt) {
        return _txt.getActionsMap().values();
    }

    public static AbsClipStream getAbsClipStream(AbstractProgramInfos _api, byte[] _bytes, String _base) {
        AbsClipStream res_ = getAbsClipStreamDirect(_api, _bytes);
        if (res_ != null) {
            return res_;
        }
//        if (FileListInfo.isWav(_bytes)) {
//            AbsClipStream absClipStream_ = _api.openClip(_bytes);
//            if (absClipStream_ != null) {
//                return absClipStream_;
//            }
//        } else if (FileListInfo.isMp3(_bytes)) {
//            AbsClipStream absClipStream_ = _api.openMp3(_bytes);
//            if (absClipStream_ != null) {
//                return absClipStream_;
//            }
//        }
        String strDecode_ = StringUtil.nullToEmpty(StringUtil.decode(_bytes));
        Document doc_ = DocumentBuilder.parseNoTextDocument(strDecode_);
        if (doc_ != null) {
            Element elt_ = doc_.getDocumentElement();
            String value_ = elt_.getAttribute(ATTR_VALUE);
            String encodeLocal_ = BaseSixtyFourUtil.checkBase(elt_.getAttribute(ATTR_BASE),_base);
            byte[] bytesTr_ = parseBaseSixtyFourBinary(value_,encodeLocal_);
            return getAbsClipStreamDirect(_api,bytesTr_);
        }
        byte[] bytesTr_ = parseBaseSixtyFourBinary(strDecode_,_base);
        return getAbsClipStreamDirect(_api,bytesTr_);
//        if (FileListInfo.isWav(bytesTr_)) {
//            return _api.openClip(bytesTr_);
//        }
//        if (FileListInfo.isMp3(bytesTr_)) {
//            return _api.openMp3(bytesTr_);
//        }
//        return _api.openClip(new byte[0]);
    }
    public static AbsClipStream getAbsClipStreamDirect(AbstractProgramInfos _api,byte[] _bytes) {
        if (FileListInfo.isWav(_bytes)) {
            return _api.openClip(_bytes);
        }
        if (FileListInfo.isMp3(_bytes)) {
            return _api.openMp3(_bytes);
        }
        return null;
    }
    public static byte[] parseBaseSixtyFourBinary(String _text, String _base) {
        int buflen_ = guessLength(_text);
        if (buflen_ < 0) {
            return new byte[0];
        }
        byte[] out_ = new byte[buflen_];
        int o_=0;

        int len_ = _text.length();

        byte[] quadruplet_ = new byte[FOUR_BITS];
        int q_=0;

        // convert each quadruplet to three bytes.
        for(int i=0; i<len_; i++ ) {
            char ch_ = _text.charAt(i);
            //v!=-1
            if (ch_ != '=') {
                int index_ = _base.indexOf(ch_);
                if (index_ < 0) {
                    return new byte[0];
                }
                quadruplet_[q_] = (byte) index_;
            } else {
                quadruplet_[q_] = 63;
            }
            q_++;

            if(q_==FOUR_BITS) {
                // quadruplet is now filled.
                int firstBytes_ = quadruplet_[0];
                int secondBytes_ = quadruplet_[1];
                int thirdBytes_ = quadruplet_[2];
                int fourthBytes_ = quadruplet_[THREE_COLORS_BYTES];
                o_ = tryPut(out_, o_, FOUR_BITS * firstBytes_ + secondBytes_ / SIXTEEN_BITS);
                o_ = tryPut(out_, o_, secondBytes_ * SIXTEEN_BITS + thirdBytes_ / FOUR_BITS);
//                if( quadruplet_[2]!=PADDING ) {
//                    out_[o_] = (byte)(secondBytes_ * SIXTEEN_BITS + thirdBytes_ / FOUR_BITS);
//                    o_++;
//                }
                o_ = tryPut(out_, o_, thirdBytes_ * SIXTY_FOUR_BITS + fourthBytes_);
//                if( quadruplet_[THREE_COLORS_BYTES]!=PADDING ) {
//                    out_[o_] = (byte)(thirdBytes_ * SIXTY_FOUR_BITS +fourthBytes_);
//                    o_++;
//                }
                q_=0;
            }
        }
        /*out_[o_] = (byte) (FOUR_BITS * firstBytes_ + secondBytes_ / SIXTEEN_BITS);
        o_++;
        out_[o_] = (byte)(secondBytes_ * SIXTEEN_BITS + thirdBytes_ / FOUR_BITS);
        o_++;
        out_[o_] = (byte)(thirdBytes_ * SIXTY_FOUR_BITS +fourthBytes_);*/
        return out_;
    }

    private static int tryPut(byte[] _out, int _o, int _by) {
        int o_ = _o;
        if (o_ < _out.length) {
            _out[o_] = (byte) _by;
            o_++;
        }
        return o_;
    }

    private static int guessLength(String _text) {
        int len_ = _text.length();

        int size_ = len_/FOUR_BITS*THREE_COLORS_BYTES;
        int j_=len_-1;
        while (j_ >= 0) {
            if (_text.charAt(j_) != '=') {
                break;
            }
            j_--;
        }
        if (j_ < 0) {
            return -1;
        }

        j_++;
        int padSize_ = len_-j_;
        if(padSize_ >2) {
            return size_;
        }

        return size_-padSize_;
    }
    public static String printBaseSixtyFourBinary(byte[] _text, String _base) {
        StringBuilder str_ = new StringBuilder();
        int len_ = _text.length;
        for (int i = 0; i < len_; i+=3) {
            if (i + 1 == len_) {
                byte[] bytes_ = new byte[1];
                bytes_[0] = _text[i];
                str_.append(BaseSixtyFourUtil.printThreeBytes(bytes_,_base));
            } else if (i + 2 == len_) {
                byte[] bytes_ = new byte[2];
                bytes_[0] = _text[i];
                bytes_[1] = _text[i+1];
                str_.append(BaseSixtyFourUtil.printThreeBytes(bytes_,_base));
            } else {
                byte[] bytes_ = new byte[3];
                bytes_[0] = _text[i];
                bytes_[1] = _text[i+1];
                bytes_[2] = _text[i+2];
                str_.append(BaseSixtyFourUtil.printThreeBytes(bytes_,_base));
            }
        }
        return str_.toString();
    }

    public static String getStringTime(long _micro) {
        long s_ = _micro / 1000000L;
        long m_ = s_ /60L;
        s_ = s_ % 60L;
        long h_ = m_ / 60L;
        m_ = m_ % 60;
        String time_ = "";
        if (h_ < 10) {
            time_ += " " + h_ + ":";
        } else {
            time_ += h_ + ":";
        }
        if (m_ < 10) {
            time_ += " " + m_ + ":";
        } else {
            time_ += m_ + ":";
        }
        if (s_ < 10) {
            time_ += " " + s_;
        } else {
            time_ += s_;
        }
        return time_;
    }
    public static void recordSong(AbsSoundRecord _rec) {
        while (_rec.getState().get()) {
            if (_rec.readBytes() == -1) {
                break;
            }
            _rec.writeBytes();
        }
    }
    public static boolean launch(AbsPlayBack _rec) {
        if (_rec == null) {
            return false;
        }
        if (!_rec.prepare()) {
            return false;
        }
        while (_rec.getState().get()) {
            if (_rec.readBytes() == -1) {
                break;
            }
            _rec.remainPrep();
            while (_rec.remain() > 0) {
                _rec.writeBytes();
            }
        }
        if (_rec.getState().get()) {
            _rec.drain();
        }
        _rec.finish();
        return true;
    }

    public static void trEx(AbsOpenQuit _t, AbstractProgramInfos _frames) {
        trEx(_t.getCommonFrame(), _frames);
//        _t.getCommonFrame().getFrames().getCounts().getVal(_t.getApplicationName()).decrementAndGet();
    }

    public static void trEx(AbsCommonFrame _t, AbstractProgramInfos _frames) {
        tryExit(_t, _frames);
//        _t.getCommonFrame().getFrames().getCounts().getVal(_t.getApplicationName()).decrementAndGet();
    }

//    public static void setLanguageDialog(AbsGroupFrame _owner,WithDialogs _w, String _title) {
//        _w.getLanguageDialog().init(_owner.getCommonFrame(),_owner.getCommonFrame().getFrames(), _title);
//    }

    public static String getStaticLanguage(SetterLanguage _dialog) {
        return _dialog.getLanguage();
    }

    public static void initStringMapInt(CrudGeneForm<String,Integer> _f, StringMap<Integer> _m, StringList _aDictionary, AfterValidateText _after) {
        _f.initForm(new StringIntDisplayEntryCust(),new GeneComponentModelString(_f.getFactory(), _aDictionary, _after),new GeneComponentModelInt(_f.getFactory()),new NaturalComparator(),_m);
    }

    public static void initStringList(CrudGeneFormList<String> _f, CustList<String> _m, StringList _aDictionary, AfterValidateText _after) {
        _f.initForm(new IntStringDisplayEntryCust(),new GeneComponentModelString(_f.getFactory(), _aDictionary, _after),_m);
    }

    public static void initStringList(CrudGeneFormList<String> _f, CustList<String> _m, StringList _aDictionary, AfterValidateText _after, Comparing<String> _cmp) {
        _f.initForm(new IntStringDisplayEntryCust(),new GeneComponentModelString(_f.getFactory(), _aDictionary, _after),_m,_cmp);
    }

    public static ScrollCustomGraphicList<String> standard(AbsCompoFactory _compo, AbstractImageFactory _img, boolean _simple, CustList<String> _elts, Ints _selected, int _rows) {
        CustCellRenderString rend_ = new CustCellRenderString(_compo, _img);
        ScrollCustomGraphicList<String> std_ = new DefScrollCustomGraphicList<String>(_compo, _img, rend_, _simple);
        for (String s: _elts) {
            std_.add(s);
        }
        std_.select(_selected);
        int m_ = std_.getElements().getPreferredSizeValue().getWidth();
        rend_.setMaxWidth(m_);
        std_.setVisibleRowCount(_rows);
        std_.applyRows();
        std_.forceRefresh();
        return std_;
    }
    public static ScrollCustomGraphicList<String> standard(AbsCompoFactory _compo, AbstractImageFactory _img, boolean _simple) {
        return new DefScrollCustomGraphicList<String>(_compo,_img,new CustCellRenderString(_compo, _img),_simple);
    }

    public static ScrollCustomCombo combo(AbstractImageFactory _fact, StringList _elts, int _index, AbsCompoFactory _compo) {
        return combo(_fact, _elts, _index, _compo, new AlwaysActionListenerAct());
    }
    public static ScrollCustomCombo combo(AbstractImageFactory _fact, StringList _elts, int _index, AbsCompoFactory _compo, AbsActionListenerAct _act) {
        ScrollCustomCombo scr_ = new ScrollCustomCombo(_compo,_fact, _act);
        for (String s: _elts) {
            scr_.add(s);
        }
        scr_.select(_index);
        scr_.repaint();
        return scr_;
    }
    public static MetaDimension dimension(AbsPanel _curr, int _prefWidth, int _visible) {
        CustList<AbsCustComponent> chs_ = _curr.getChildren();
        if (chs_.isEmpty()) {
            return new MetaDimension(256,_visible * 16);
        }
        return new MetaDimension(_prefWidth, chs_.get(0).getHeight() * NumberUtil.min(chs_.size(), _visible));
    }
    public static String getSelectedItem(ScrollCustomCombo _c) {
        RowGraphicList<String> r_ = _c.getList().getRow(_c.getSelectedIndex());
        if (r_ == null) {
            return "";
        }
        return StringUtil.nullToEmpty(r_.getInfo());
    }
    public static boolean stateChanged(AbsTabbedPane _tab, int _previous, int _next) {
        if (_previous != _next) {
            stateChanged(_tab);
            return true;
        }
        return false;
    }

    public static void stateChanged(AbsTabbedPane _tab) {
        for (AbsChangeListener c: _tab.getChangeListeners()) {
            c.stateChanged();
        }
    }

    public static String buildPath(AbstractMutableTreeNodeCore<String> _treePath) {
        return buildPath(_treePath,"");
    }
    public static String buildPath(AbstractMutableTreeNodeCore<String> _treePath, String _sep) {
        StringList pathFull_ = new StringList();
        AbstractMutableTreeNodeCore<String> current_ = _treePath;
        while (current_ != null) {
            pathFull_.add(0,current_.info());
            current_ = current_.getParent();
        }
        if (_sep.isEmpty()) {
            StringUtil.removeObj(pathFull_, "");
        }
        return StringUtil.join(pathFull_,_sep);
    }
}
