package cards.gui.containers;

import cards.consts.CoreResourcesAccess;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import code.gui.EnabledMenu;
import code.gui.EnabledMenu;
import code.gui.EnabledMenu;
import code.gui.AbsPanel;
import code.scripts.messages.cards.MessagesCommonCommon;
import code.threads.AbstractAtomicBoolean;
import code.util.core.StringUtil;

public abstract class ContainerSingleImpl extends ContainerGame {
    private WindowCardsInt window;
    private final AbstractAtomicBoolean passe;

    protected ContainerSingleImpl(WindowCardsInt _window) {
        super(_window.noGame());
        window = _window;
        passe = _window.getThreadFactory().newAtomicBoolean();
    }

    public void revalidate() {
        getWindow().revalidateFrame();
    }

    public void pack() {
        getWindow().pack();
    }

    public final WindowCardsInt getOwner() {
        return getWindow();
    }
    protected AbsPanel getPane() {
        return getWindow().getPane();
    }
    public void setContentPane(AbsPanel _container) {
        getWindow().setContentPane(_container);
    }
//    public void saveCurrentGame(String _file) {
//        getPar().sauvegarderPartieEnCours(_file, getWindow().getStreams());
//    }

    public WindowCardsInt getWindow() {
        return window;
    }
    public void setWindow(WindowCards _window) {
        window = _window;
    }

    public EnabledMenu getChange() {
        return ((WindowCards)window).getChange();
    }

    public EnabledMenu getSave() {
        return ((WindowCards)window).getSave();
    }

    public EnabledMenu getConsulting() {
        return ((WindowCards)window).getConsulting();
    }

    public EnabledMenu getPause() {
        return ((WindowCards)window).getPause();
    }

    public EnabledMenu getHelpGame() {
        return ((WindowCards)window).getHelpGame();
    }

//    public AbsMenu getHelp() {
//        return window.getHelp();
//    }
    public boolean isPasse() {
        return passe.get();
    }
    public void setPasse(boolean _passe) {
        passe.set(_passe);
    }
//    public AbsMenuItem getGeneralHelp() {
//        return window.getGeneralHelp();
//    }

//    public AbsMenu getEdit() {
//        return window.getEdit();
//    }

//    public IdMap<GameEnum,AbsMenuItem> getEditGames() {
//        return window.getEditGames();
//    }

    public EnabledMenu getDemo() {
        return ((WindowCards)window).getDemo();
    }

//    public IdMap<GameEnum,AbsMenuItem> getDemoGames() {
//        return window.getDemoGames();
//    }

//    public AbsMenu getTraining() {
//        return window.getTraining();
//    }

//    public IdMap<ChoiceTarot,AbsMenuItem> getTrainingTarot() {
//        return window.getTrainingTarot();
//    }

    public String readCoreResource() {
        return MessagesCommonCommon.ms().getVal(StringUtil.concat(CoreResourcesAccess.NOM_DOSSIER, "/",getOwner().getLanguageKey(), "/",CoreResourcesAccess.NOM_FICHIER));
//        return ResourceFiles.ressourceFichier(StringUtil.concat(CoreResourcesAccess.NOM_DOSSIER,ResourceFiles.SEPARATEUR,getOwner().getLanguageKey(),ResourceFiles.SEPARATEUR,CoreResourcesAccess.NOM_FICHIER));
    }

    public void thread(Runnable _animContratBelote) {
        getOwner().getThreadFactory().newStartedThread(_animContratBelote);
    }

}
