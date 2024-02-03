package cards.gui.containers;

import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import code.gui.EnabledMenu;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.MonteCarloUtil;
import code.threads.AbstractAtomicBoolean;
import code.util.Ints;
import code.util.StringMap;

public abstract class ContainerSingleImpl extends ContainerGame {
    private static final int ONE_QUATER = 64;
    private static final int ZERO_QUATER = 0;
    private static final int TWO_QUATER = 128;
    private static final int THREE_QUATER = 192;
    private static final int FOUR_QUATER = 255;
    private WindowCardsInt window;
    private final AbstractAtomicBoolean passe;

    protected ContainerSingleImpl(WindowCardsInt _window) {
        super(_window.noGame());
        window = _window;
        passe = _window.getThreadFactory().newAtomicBoolean();
    }
    public static Ints couleursCourbes(AbstractGenerator _gene) {
        Ints colors_ = couleursCourbes();
        Ints edit_ = new Ints();
        while (!colors_.isEmpty()) {
            int v_ = (int) MonteCarloUtil.randomLong(colors_.size(), _gene);
            edit_.add(colors_.get(v_));
            colors_.remove(v_);
        }
        return edit_;
    }
    public static Ints couleursCourbes() {
        Ints couleurs_=new Ints();
        couleurs_.add(GuiConstants.RED);
        couleurs_.add(GuiConstants.GREEN);
        couleurs_.add(GuiConstants.BLUE);
        couleurs_.add(GuiConstants.YELLOW);
        couleurs_.add(GuiConstants.MAGENTA);
        couleurs_.add(GuiConstants.CYAN);
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, ONE_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, ONE_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, ONE_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, TWO_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, TWO_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, TWO_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, THREE_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, THREE_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, THREE_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, FOUR_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, FOUR_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, FOUR_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, ZERO_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, ZERO_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, ZERO_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, TWO_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, TWO_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, TWO_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, THREE_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, THREE_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, THREE_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, FOUR_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, FOUR_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, FOUR_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, ZERO_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, ZERO_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, ZERO_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, ONE_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, ONE_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, ONE_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, THREE_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, THREE_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, THREE_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, FOUR_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, FOUR_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, FOUR_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, ZERO_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, ZERO_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, ZERO_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, ONE_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, ONE_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, ONE_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, TWO_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, TWO_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, TWO_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, FOUR_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, FOUR_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, FOUR_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, ZERO_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, ZERO_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, ZERO_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, ONE_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, ONE_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, ONE_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, TWO_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, TWO_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, TWO_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, THREE_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, THREE_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, THREE_QUATER, TWO_QUATER));
        return couleurs_;
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
    public AbsPanel getPane() {
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

    public StringMap<String> readCoreResourceSuit() {
        return Games.getCommonFileTr(Games.getAppliTr(getOwner().getFrames().currentLg())).getMapping();
//        return ResourceFiles.ressourceFichier(StringUtil.concat(CoreResourcesAccess.NOM_DOSSIER,ResourceFiles.SEPARATEUR,getOwner().getLanguageKey(),ResourceFiles.SEPARATEUR,CoreResourcesAccess.NOM_FICHIER));
    }

    public StringMap<String> readCoreResourceCards() {
        return Games.getCommonCardsTr(Games.getAppliTr(getOwner().getFrames().currentLg())).getMapping();
//        return ResourceFiles.ressourceFichier(StringUtil.concat(CoreResourcesAccess.NOM_DOSSIER,ResourceFiles.SEPARATEUR,getOwner().getLanguageKey(),ResourceFiles.SEPARATEUR,CoreResourcesAccess.NOM_FICHIER));
    }

    public StringMap<String> readCoreResourceMix() {
        return Games.getCommonMixTr(Games.getAppliTr(getOwner().getFrames().currentLg())).getMapping();
//        return ResourceFiles.ressourceFichier(StringUtil.concat(CoreResourcesAccess.NOM_DOSSIER,ResourceFiles.SEPARATEUR,getOwner().getLanguageKey(),ResourceFiles.SEPARATEUR,CoreResourcesAccess.NOM_FICHIER));
    }
    public void thread(Runnable _animContratBelote) {
        getAllThreads().add(getOwner().getThreadFactory().newStartedThread(_animContratBelote));
    }

}
