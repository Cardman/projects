package cards.gui.dialogs;

import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.comboboxes.StringComboBox;
import cards.gui.dialogs.events.BackToRulesEvent;
import cards.gui.dialogs.events.MoveCardsEvent;
import cards.gui.panels.AbsCardsScrollableList;
import code.gui.AbsButton;
import code.gui.AbsPanel;
import code.gui.AbsPlainLabel;
import code.gui.GuiBaseUtil;
import code.gui.files.FileSaveDialogContent;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesEditorCards;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class EditorCards {
    private final CustList<AbsCardsScrollableList> all = new CustList<AbsCardsScrollableList>();
    private AbsPlainLabel labelSelectCards;
    private AbsPlainLabel errors;
    private AbsPanel panelsCards;
    private AbsPanel panelDeal;
    private AbsPanel border;
    private FileSaveDialogContent saveDialogContent;
    private boolean setToNullGame;
    private StringComboBox liste;
    private StringComboBox listeTwo;
    private final AbstractProgramInfos programInfos;
    private AbsButton validateRules;
    private AbsButton moveCards;
    private AbsButton backToRules;

    public EditorCards(AbstractProgramInfos _t) {
        programInfos = _t;
    }
    public String translate(String _k) {
        return translate().getVal(_k);
    }
    public StringMap<String> translate() {
        return Games.getEditorTr(Games.getAppliTr(programInfos.currentLg())).getMapping();
    }

    public AbsPlainLabel buildLabelSelectCard(AbsCompoFactory _compo) {
        String mess_ = Games.getEditorTr(Games.getAppliTr(programInfos.currentLg())).getMapping().getVal(MessagesEditorCards.SELECTED_CARDS);
        labelSelectCards = _compo.newPlainLabel(StringUtil.simpleNumberFormat(mess_,0));
        return getLabelSelectCards();
    }
    public StringComboBox buildDealer(String _ps, AbstractImageFactory _img, AbsCompoFactory _compo, CustList<String> _nicknames, int _nbPlayers) {
        liste=new StringComboBox(GuiBaseUtil.combo(_img,new StringList(), 0, _compo));
        liste.addItem(_ps);
        for(String n: _nicknames) {
            if (liste.getItemCount() == _nbPlayers) {
                break;
            }
            liste.addItem(n);
        }
        liste.addItem(translate(MessagesEditorCards.RANDOM));
        liste.getCombo().repaint();
        return getListe();
    }
    public StringComboBox beginCombo(AbstractImageFactory _img, AbsCompoFactory _compo, CustList<String> _nicknames, int _nbPlayers) {
        listeTwo=new StringComboBox(GuiBaseUtil.combo(_img,new StringList(), 0, _compo));
        listeTwo.addItem(translate(MessagesEditorCards.DEALING_STACK));
        listeTwo.addItem(translate(MessagesEditorCards.USER_HAND));
        for(String n: _nicknames) {
            if (listeTwo.getItemCount() == _nbPlayers + 1) {
                break;
            }
            String message_ = translate(MessagesEditorCards.PLAYER_HAND);
            message_ = StringUtil.simpleStringsFormat(message_, n);
            listeTwo.addItem(message_);
        }
        return getListeTwo();
    }
    public AbsPanel buildMoveCards(WindowCardsInt _parent, SetterSelectedCardList _dialog) {
        AbsPanel sousPanneau_=_parent.getCompoFactory().newLineBox();
        AbsButton bouton_=_parent.getCompoFactory().newPlainButton(translate(MessagesEditorCards.MOVE_CARDS));
        bouton_.addActionListener(new MoveCardsEvent(_dialog));
        sousPanneau_.add(bouton_);
        moveCards = bouton_;
        return sousPanneau_;
    }
    public AbsButton buildBackToRules(WindowCardsInt _parent, SetterSelectedCardList _dialog) {
        AbsButton bouton_= _parent.getCompoFactory().newPlainButton(translate(MessagesEditorCards.BACK));
        bouton_.addActionListener(new BackToRulesEvent(_dialog, _parent));
        backToRules = bouton_;
        return bouton_;
    }
    public AbsButton getMoveCards() {
        return moveCards;
    }

    public AbsButton getBackToRules() {
        return backToRules;
    }

    public StringComboBox getListe() {
        return liste;
    }

    public StringComboBox getListeTwo() {
        return listeTwo;
    }

    public void clear() {
        all.clear();
    }

    public void doNotSetToNullGame() {
        setSetToNullGame(false);
    }

    public boolean isSetToNullGame() {
        return setToNullGame;
    }

    public void setSetToNullGame(boolean _s) {
        this.setToNullGame = _s;
    }
    public AbsPlainLabel getLabelSelectCards() {
        return labelSelectCards;
    }

    public void addPanel(AbsCardsScrollableList _a) {
        all.add(_a);
    }
    public CustList<AbsCardsScrollableList> getAll() {
        return all;
    }

//
//    public String getErrorSaveMessage(StringMap<String> _messages) {
//        return _messages.getVal(ERROR_SAVE_FILE);
//    }
//
//    public String getErrorSaveTitle(StringMap<String> _messages) {
//        return _messages.getVal(ERROR_SAVE_FILE_TITLE);
//    }
    public AbsPanel getPanelsCards() {
        return panelsCards;
    }

    public void setPanelsCards(AbsPanel _p) {
        this.panelsCards = _p;
    }

    public void buildPanelDeal(AbsPanel _border, WindowCards _w, SetterSelectedCardList _d) {
        border = _border;
        setPanelDeal(programInfos.getCompoFactory().newBorder());
        setErrors(programInfos.getCompoFactory().newPlainLabel(""));
        FileSaveDialogContent save_ = new FileSaveDialogContent(programInfos);
        saveDialogContent = save_;
        String folder_ = folder(_w, programInfos);
        save_.setFileSaveDialogByFrame(true,folder_,new EditorPostFileDialogEvent(save_,this,_w,_d),new EditorButtonsSavePanel(this,_w,_d));
    }

    public static String folder(WindowCards _w, AbstractProgramInfos _pr) {
        String folder_;
        if (_w.isSaveHomeFolder()) {
            folder_ = _pr.getHomePath();
        } else {
            folder_ = "";
        }
        return folder_;
    }

    public AbsButton getValidateRules() {
        return validateRules;
    }

    public void setValidateRules(AbsButton _v) {
        this.validateRules = _v;
    }

    public AbsPanel getBorder() {
        return border;
    }

    public AbsPanel getPanelDeal() {
        return panelDeal;
    }

    public AbstractProgramInfos getProgramInfos() {
        return programInfos;
    }

    public void setPanelDeal(AbsPanel _p) {
        this.panelDeal = _p;
    }

    public AbsPlainLabel getErrors() {
        return errors;
    }

    public void setErrors(AbsPlainLabel _e) {
        this.errors = _e;
    }

    public FileSaveDialogContent getSaveDialogContent() {
        return saveDialogContent;
    }
}
