package cards.gui;

import cards.belote.DisplayingBelote;
import cards.belote.RulesBelote;
import cards.facade.SoftParams;
import cards.facade.enumerations.GameEnum;
import cards.gui.animations.PreparedPagesCards;
import cards.gui.containers.ContainerNoGame;
import cards.gui.dialogs.*;
import cards.president.DisplayingPresident;
import cards.president.RulesPresident;
import cards.tarot.DisplayingTarot;
import cards.tarot.RulesTarot;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.core.TechStreams;
import code.threads.AbstractThreadFactory;
import code.util.*;

public interface WindowCardsInt {
    StringMap<StringMap<int[][]>> getImages();
    StringMap<StringMap<int[][]>> getImagesMiniDef();
    StringMap<StringMap<int[][]>> getImagesMiniSel();
    AbstractImageFactory getImageFactory();
    AbstractProgramInfos getFrames();
    AbsCompoFactory getCompoFactory();
    String getLanguageKey();
    AbstractThreadFactory getThreadFactory();
    StringMap<PreparedPagesCards> getPreparedBelote();
    StringMap<PreparedPagesCards> getPreparedPresident();
    StringMap<PreparedPagesCards> getPreparedTarot();

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

    DialogHelpBelote getDialogHelpBelote();

    DialogHelpPresident getDialogHelpPresident();

    DialogHelpTarot getDialogHelpTarot();
    void setTitle(String _title);

    EnabledMenu getTricksHands();

    EnabledMenu getTeams();

    ConfirmDialogAnsAbs getConfirmDialogAns();
    TechStreams getStreams();

    void menuPrincipal();

    void changeGame();

    void displayTricksHands();

    void manageSoft(String _k);

    SoftParams getParametresLogiciel();
    DialogSoft getDialogSoft();
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

    DialogRulesBelote getDialogRulesBelote();

    DialogRulesPresident getDialogRulesPresident();

    DialogRulesTarot getDialogRulesTarot();

    void displayTeams();
}
