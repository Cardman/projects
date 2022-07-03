package cards.gui.dialogs;

import cards.gui.WindowCards;
import cards.network.common.select.TeamsPlayers;
import code.gui.AbsPanel;

import code.gui.AbsPlainLabel;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DialogTeamsPlayers extends DialogCards {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogteamsplayers";

    private static final String TEAM = "team";
    private static final String TITLE = "title";
    private StringMap<String> messages;

    public DialogTeamsPlayers(AbstractProgramInfos _frameFactory) {
        super(_frameFactory);
        getCardDialog().setAccessFile(DIALOG_ACCESS);
    }
    public static void initDialogTeamsPlayers(WindowCards _fenetre) {
        _fenetre.getDialogTeamsPlayers().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
//        DIALOG.messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), DIALOG.getClass());
        _fenetre.getDialogTeamsPlayers().messages = WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _fenetre.getLanguageKey(), _fenetre.getDialogTeamsPlayers().getCardDialog().getAccessFile());
        _fenetre.getDialogTeamsPlayers().getCardDialog().setTitle(_fenetre.getDialogTeamsPlayers().messages.getVal(TITLE));
        _fenetre.getDialogTeamsPlayers().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
    }

    public static void setDialogTeamsPlayers(StringList _pseudos, TeamsPlayers _teamsPlayers, DialogTeamsPlayers _dialog, AbsCompoFactory _compo) {
        _dialog.setDialogue(_pseudos, _teamsPlayers, _compo);
    }

    public void setDialogue(StringList _pseudos, TeamsPlayers _teamsPlayers, AbsCompoFactory _compo) {
        AbsPanel panel_ = _compo.newGrid(0,1);
        int i_ = 1;
        String stringTeam_ = messages.getVal(TEAM);
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
        getCardDialog().setContentPane(panel_);
        getCardDialog().pack();
        getCardDialog().setVisible(true);
    }
}
