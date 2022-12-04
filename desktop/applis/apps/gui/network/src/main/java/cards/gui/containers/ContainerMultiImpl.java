package cards.gui.containers;

import cards.consts.CoreResourcesAccess;
import code.gui.AbsMenuItem;
import code.gui.AbsPanel;
import code.network.WindowNetWork;
import code.scripts.messages.cards.MessagesCommonCommon;
import code.util.core.StringUtil;

public abstract class ContainerMultiImpl extends ContainerGame {
    private WindowNetWork window;
    protected ContainerMultiImpl(WindowNetWork _window) {
        super(_window.noGame());
        window = _window;
    }
    public WindowNetWork getWindow() {
        return window;
    }
    protected void setWindow(WindowNetWork _window) {
        window = _window;
    }
    public void revalidate() {
        getWindow().revalidateFrame();
    }

    public void pack() {
        getWindow().pack();
    }

    public final WindowNetWork getOwner() {
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

    public AbsMenuItem getExit() {
        return window.getExit();
    }

    public AbsMenuItem getTricksHands() {
        return window.getTricksHands();
    }

    public AbsMenuItem getTeams() {
        return window.getTeams();
    }

    public String readCoreResource() {
        return MessagesCommonCommon.ms().getVal(StringUtil.concat(CoreResourcesAccess.NOM_DOSSIER, "/",getOwner().getLanguageKey(), "/",CoreResourcesAccess.NOM_FICHIER));
//        return ResourceFiles.ressourceFichier(StringUtil.concat(CoreResourcesAccess.NOM_DOSSIER,ResourceFiles.SEPARATEUR,getOwner().getLanguageKey(),ResourceFiles.SEPARATEUR,CoreResourcesAccess.NOM_FICHIER));
    }

}
