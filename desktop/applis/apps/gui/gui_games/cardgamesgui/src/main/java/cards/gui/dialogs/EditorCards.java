package cards.gui.dialogs;

import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.comboboxes.StringComboBox;
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
import code.sml.util.Translations;
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
    private final Translations translations;
    private AbsButton validateRules;

    public EditorCards(AbstractProgramInfos _t) {
        programInfos = _t;
        this.translations = _t.getTranslations();
    }
    public String translate(WindowCardsInt _win, String _k) {
        return translate(_win).getVal(_k);
    }
    public StringMap<String> translate(WindowCardsInt _win) {
        return getTranslations().getMapping().getVal(_win.getLanguageKey()).getMapping().getVal(Games.CARDS).getMapping().getVal(Games.EDITOR_CARDS).getMapping();
    }
    public String translate(String _win, String _k) {
        return translate(_win).getVal(_k);
    }
    public StringMap<String> translate(String _win) {
        return getTranslations().getMapping().getVal(_win).getMapping().getVal(Games.CARDS).getMapping().getVal(Games.EDITOR_CARDS).getMapping();
    }

    public Translations getTranslations() {
        return translations;
    }

    public AbsPlainLabel buildLabelSelectCard(AbsCompoFactory _compo, String _lg) {
        String mess_ = translations.getMapping().getVal(_lg).getMapping().getVal(Games.CARDS).getMapping().getVal(Games.EDITOR_CARDS).getMapping().getVal(MessagesEditorCards.SELECTED_CARDS);
        labelSelectCards = _compo.newPlainLabel(StringUtil.simpleNumberFormat(mess_,0));
        return getLabelSelectCards();
    }
    public StringComboBox buildDealer(String _ps,AbstractImageFactory _img, AbsCompoFactory _compo, String _lg, CustList<String> _nicknames, int _nbPlayers) {
        liste=new StringComboBox(GuiBaseUtil.combo(_img,new StringList(), 0, _compo));
        liste.addItem(_ps);
        for(String n: _nicknames) {
            if (liste.getItemCount() == _nbPlayers) {
                break;
            }
            liste.addItem(n);
        }
        liste.addItem(translate(_lg,MessagesEditorCards.RANDOM));
        liste.getCombo().repaint();
        return getListe();
    }
    public StringComboBox beginCombo(AbstractImageFactory _img, AbsCompoFactory _compo, String _lg, CustList<String> _nicknames, int _nbPlayers) {
        listeTwo=new StringComboBox(GuiBaseUtil.combo(_img,new StringList(), 0, _compo));
        listeTwo.addItem(translate(_lg,MessagesEditorCards.DEALING_STACK));
        listeTwo.addItem(translate(_lg,MessagesEditorCards.USER_HAND));
        for(String n: _nicknames) {
            if (listeTwo.getItemCount() == _nbPlayers + 1) {
                break;
            }
            String message_ = translate(_lg,MessagesEditorCards.PLAYER_HAND);
            message_ = StringUtil.simpleStringsFormat(message_, n);
            listeTwo.addItem(message_);
        }
        return getListeTwo();
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
        save_.setFileSaveDialogByFrame(_w.getLanguageKey(),true,folder_,new EditorPostFileDialogEvent(save_,this,_w,_d),new EditorButtonsSavePanel(this,_w,_d));
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
