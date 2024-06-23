package cards.gui;

import cards.belote.DisplayingBelote;
import cards.belote.RulesBelote;
import cards.facade.enumerations.GameEnum;
import cards.gui.containers.ContainerNoGame;
import cards.gui.dialogs.*;
import cards.main.CardNatLgNamesNavigation;
import cards.president.DisplayingPresident;
import cards.president.RulesPresident;
import cards.tarot.DisplayingTarot;
import cards.tarot.RulesTarot;
import code.gui.*;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.core.TechStreams;
import code.threads.AbstractFutureParam;
import code.threads.AbstractThreadFactory;
import code.util.StringMap;

public interface WindowCardsInt {

    AbstractImageFactory getImageFactory();
    AbstractProgramInfos getFrames();
    AbsCompoFactory getCompoFactory();

    StringMap<String> getMenusMessages();
    AbstractThreadFactory getThreadFactory();
    StringMap<AbstractFutureParam<CardNatLgNamesNavigation>> getPrepared();

    void revalidateFrame();

    void pack();

    AbsPanel getPane();

    void setContentPane(AbsPanel _cont);
    AbsCommonFrame getCommonFrame();
    Clock getClock();
    AbsPlainLabel getLastSavedGameDate();
    AbstractGenerator getGenerator();
    DialogTeamsPlayers getDialogTeamsPlayers();
    DialogTricksBelote getDialogTricksBelote();

    DialogTricksPresident getDialogTricksPresident();

    DialogTricksTarot getDialogTricksTarot();

    void setTitle(String _title);

    EnabledMenu getTricksHands();

    EnabledMenu getTeams();

    TechStreams getStreams();

    void menuPrincipal();

//    void changeGame();

    void displayTricksHands();

//    void manageSoft(String _k);

    void manageLanguage();

    void displayingGame(GameEnum _g);

    DialogDisplayingBelote getDialogDisplayingBelote();

    DialogDisplayingTarot getDialogDisplayingTarot();

    DialogDisplayingPresident getDialogDisplayingPresident();
    DisplayingTarot getDisplayingTarot();
    DisplayingPresident getDisplayingPresident();
    DisplayingBelote getDisplayingBelote();

    ContainerNoGame noGame();
    RulesBelote getReglesBelote();
    RulesPresident getReglesPresident();
    RulesTarot getReglesTarot();

    void displayTeams();
    WindowCardsCore baseWindow();
}
