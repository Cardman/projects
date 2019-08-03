package cards.gui.dialogs;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import cards.gui.MainWindow;
import cards.network.common.select.TeamsPlayers;
import code.gui.Panel;
import code.gui.TextLabel;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;

public final class DialogTeamsPlayers extends DialogCards {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogteamsplayers";

    private static final DialogTeamsPlayers DIALOG = new DialogTeamsPlayers();
    private static final String TEAM = "team";
    private static final String TITLE = "title";
    private StringMap<String> messages;

    private DialogTeamsPlayers() {
        setAccessFile(DIALOG_ACCESS);
    }
    public static void initDialogTeamsPlayers(MainWindow _fenetre) {
        DIALOG.setDialogIcon(_fenetre);
//        DIALOG.messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), DIALOG.getClass());
        DIALOG.messages = DIALOG.getMessages(_fenetre,FileConst.FOLDER_MESSAGES_GUI);
        DIALOG.setTitle(DIALOG.messages.getVal(TITLE));
        DIALOG.setLocationRelativeTo(_fenetre);
    }

    public static void setDialogTeamsPlayers(StringList _pseudos, TeamsPlayers _teamsPlayers) {
        DIALOG.setDialogue(_pseudos, _teamsPlayers);
    }

    public void setDialogue(StringList _pseudos, TeamsPlayers _teamsPlayers) {
        Panel panel_ = Panel.newGrid(0,1);
        int i_ = 1;
        String stringTeam_ = messages.getVal(TEAM);
        for (Bytes t: _teamsPlayers.getTeams()) {
            String stringTeamLoc_ = StringList.simpleNumberFormat(stringTeam_, i_);
            Panel team_ = Panel.newGrid(0,1);
            team_.setBorder(BorderFactory.createTitledBorder(stringTeamLoc_));
            for (byte p:t) {
                TextLabel player_ = new TextLabel(_pseudos.get(p));
                team_.add(player_);
            }
            panel_.add(team_);
            i_++;
        }
        setContentPane(panel_);
        pack();
        setVisible(true);
    }
}
