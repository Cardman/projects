package code.gui;

import code.gui.events.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractLightProgramInfos;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.AbsPlayBack;
import code.stream.AbsSoundRecord;
import code.threads.AbstractDate;
import code.threads.AbstractDateFactory;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.NaturalComparator;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class GuiBaseUtil {
    static final String ACCESS = "gui.groupframe";
    private static final String TITLE = "title";
    private static final String MESSAGE = "message";

    private GuiBaseUtil() {

    }

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

    public static void removeActionListeners(AbsPlainButton _tr) {
        CustList<AbsActionListener> tr_ = _tr.getActionListeners();
        int s_ = tr_.size();
        for (int i = 0; i < s_; i++) {
            _tr.removeActionListener(tr_.get(i));
        }
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

                    curr_ = par_;
                }
            }
        }
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
        return getTimeText(_fact,time(_format));
    }

    public static String getTimeText(AbstractThreadFactory _fact, String _separatorDate, String _sep, String _separatorTime) {
        AbstractDateFactory dateFactory_ = _fact.getDateFactory();
        AbstractDate date_ = dateFactory_.newDate(_fact.millis());
        return date_.format(dateFactory_,date(_separatorDate) + _sep + time(_separatorTime));
    }

    private static String date(String _separatorDate) {
        return "yyyy" + _separatorDate + "MM" + _separatorDate + "dd";
    }

    private static String time(String _separator) {
        return "HH" + _separator + "mm" + _separator + "ss";
    }

    public static void removeAllListeners(AbsCommonFrame _com) {
        for (AbsWindowListenerClosing l: _com.getWindowListenersDef()) {
            _com.removeWindowListener(l);
        }
        _com.dispatchExit();
    }

    public static void tryExit(AbsCommonFrame _comm) {
        if(!_comm.getFrames().getFrames().first().getCommonFrame().isVisible()) {
            removeAllListeners(_comm);
        }
    }

    public static StringMap<String> group(String _language, StringMap<String> _res) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, _language, ACCESS);
        String loadedResourcesMessages_ = _res.getVal(fileName_);
        return ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
    }

    public static void choose(String _lg, AbsOpenQuit _this, StringMap<String> _res) {
        choose(_lg, _this.getCommonFrame().getFrames(),_this,_res);
    }
    public static void choose(String _lg, AbstractProgramInfos _list, AbsOpenQuit _this, StringMap<String> _res) {
        _list.getFrames().add(_this);
        AbsGroupFrame first_ = _list.getFrames().first();
        if (_list.getFrames().size() == 1) {
            first_.setMessages(group(_lg, _res));
        }
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

    public static boolean changeStaticLanguage(String _language, AbstractProgramInfos _list, StringMap<String> _res) {
        if (_language.isEmpty()) {
            return false;
        }
        _list.getFrames().first().setMessages(group(_language, _res));
        _list.getFrames().first().changeLanguage(_language);
        return true;
    }

    public static void showDialogError(int _errorMessage, AbsCommonFrame _com) {
        StringMap<String> messages_ = _com.getFrames().getFrames().first().getMessages();
        _com.getFrames().getMessageDialogAbs().input(_com, messages_.getVal(MESSAGE), messages_.getVal(TITLE), _com.getFrames().getFrames().first().getCommonFrame().getLanguageKey(), _errorMessage);
    }
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

    public static void trEx(AbsOpenQuit _t) {
        tryExit(_t.getCommonFrame());
        _t.getCommonFrame().getFrames().getCounts().getVal(_t.getApplicationName()).decrementAndGet();
    }

    public static void setLanguageDialog(AbsGroupFrame _owner,WithDialogs _w, String _title) {
        _w.getLanguageDialog().init(_owner.getCommonFrame(),_owner.getCommonFrame().getFrames(), _title);
    }

    public static String getStaticLanguage(SetterLanguage _dialog) {
        return _dialog.getLanguage();
    }

    public static void initStringMapInt(AbsCommonFrame _c,CrudGeneForm<String,Integer> _f, StringMap<Integer> _m, StringList _aDictionary, AfterValidateText _after) {
        _f.initForm(_c,new StringIntDisplayEntryCust(),new GeneComponentModelString(_f.getFactory(), _aDictionary, _after),new GeneComponentModelInt(_f.getFactory()),new NaturalComparator(),_m);
    }
}
