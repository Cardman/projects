package cards.gui.dialogs;

import cards.facade.Games;
import cards.gui.WindowCardsInt;
import cards.gui.panels.CardsScrollableList;
import code.gui.AbsPanel;
import code.gui.AbsPlainLabel;
import code.gui.initialize.AbsCompoFactory;
import code.scripts.messages.cards.MessagesEditorCards;
import code.sml.util.Translations;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class EditorCards {
    private final CustList<CardsScrollableList> all = new CustList<CardsScrollableList>();
    private AbsPlainLabel labelSelectCards;
    private AbsPanel panelsCards;
    private boolean partieSauvegardee;
    private boolean setToNullGame;
    private final Translations translations;

    public EditorCards(Translations _t) {
        this.translations = _t;
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

    public void addPanel(CardsScrollableList _a) {
        all.add(_a);
    }
    public CustList<CardsScrollableList> getAll() {
        return all;
    }

    public boolean isPartieSauvegardee() {
        return partieSauvegardee;
    }

    public void setPartieSauvegardee(boolean _partieSauvegardee) {
        partieSauvegardee = _partieSauvegardee;
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
}
