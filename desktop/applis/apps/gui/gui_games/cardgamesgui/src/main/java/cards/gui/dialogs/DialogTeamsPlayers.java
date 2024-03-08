package cards.gui.dialogs;

import cards.gui.*;
//import cards.network.common.select.TeamsPlayers;
import cards.gui.containers.ContainerSingleImpl;
import code.gui.AbsPanel;

import code.gui.AbsPlainLabel;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesGuiCards;
import code.threads.AbstractAtomicBoolean;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DialogTeamsPlayers extends DialogHelpCards {

    private StringMap<String> messages;

    public DialogTeamsPlayers(AbstractProgramInfos _frameFactory, AbstractAtomicBoolean _modal) {
        super(_frameFactory,_modal);
    }
    public static void initDialogTeamsPlayers(WindowCardsInt _fenetre) {
//        _fenetre.getDialogTeamsPlayers().getAbsDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
//        DIALOG.messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), DIALOG.getClass());
        _fenetre.getDialogTeamsPlayers().messages = ContainerSingleImpl.file(_fenetre.getFrames().currentLg());
        _fenetre.getDialogTeamsPlayers().setTitleDialog(_fenetre,_fenetre.getDialogTeamsPlayers().messages.getVal(MessagesGuiCards.MAIN_TITLE));
//        _fenetre.getDialogTeamsPlayers().getAbsDialog().setTitle(_fenetre.getDialogTeamsPlayers().messages.getVal(MessagesGuiCards.MAIN_TITLE));
//        _fenetre.getDialogTeamsPlayers().getAbsDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
    }

    public static void setDialogTeamsPlayers(StringList _pseudos, TeamsPlayers _teamsPlayers, DialogTeamsPlayers _dialog, AbsCompoFactory _compo) {
        _dialog.setDialogue(_pseudos, _teamsPlayers, _compo);
    }

    public void setDialogue(StringList _pseudos, TeamsPlayers _teamsPlayers, AbsCompoFactory _compo) {
        AbsPanel panel_ = _compo.newGrid(0,1);
        int i_ = 1;
        String stringTeam_ = messages.getVal(MessagesGuiCards.MAIN_TEAM);
        for (Bytes t: _teamsPlayers.getTeams()) {
            String stringTeamLoc_ = StringUtil.simpleNumberFormat(stringTeam_, i_);
            AbsPanel team_ = _compo.newGrid(0,1);
            team_.setTitledBorder(stringTeamLoc_);
            for (byte p:t) {
                AbsPlainLabel player_ = getCompoFactory().newPlainLabel(_pseudos.get(p));
                team_.add(player_);
            }
            panel_.add(team_);
            i_++;
        }
        getAbsDialog().setContentPane(panel_);
        getAbsDialog().pack();
        getAbsDialog().setVisible(true);
    }
}
