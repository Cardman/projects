package cards.gui.dialogs;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cards.facade.Nicknames;
import cards.facade.enumerations.GameEnum;
import cards.gui.MainWindow;
import cards.gui.dialogs.events.ListenerNicknames;
import code.gui.ConfirmDialog;
import code.gui.LabelButton;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public final class DialogNicknames extends DialogCards {

    private static final String DIALOG_ACCESS = "cards.gui.dialogs.DialogNicknames";

    private static final DialogNicknames DIALOG = new DialogNicknames();
    private static final String TAB = "\t";
    private static final String NICKNAME = "nickname";
    private static final String NICKNAME_PLAYER = "nicknamePlayer";
    private static final String VALIDATE = "validate";
    private static final String ERROR_SAVE = "errorSave";
    private static final String FORBIDDEN_EMPTY = "forbiddenEmpty";
    private static final String FORBIDDEN_TAB = "forbiddenTab";
    private StringMap<String> messages;
    private Nicknames pseudos=new Nicknames(Constants.getLanguage());
    private JTextField nickname;
    private CustList<JTextField> nicknamesBelote = new CustList<JTextField>();
    private CustList<JTextField> nicknamesTarot = new CustList<JTextField>();
    private CustList<JTextField> nicknamesPresident = new CustList<JTextField>();

    private DialogNicknames() {
        setAccessFile(DIALOG_ACCESS);
    }
    public static void initDialogNicknames(String _titre, MainWindow _fenetre) {
        DIALOG.setDialogIcon(_fenetre);
        DIALOG.setTitle(_titre);
//        DIALOG.messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), DIALOG.getClass());
        DIALOG.messages = DIALOG.getMessages(FileConst.FOLDER_MESSAGES_GUI);
        DIALOG.pseudos = _fenetre.getPseudosJoueurs();
        DIALOG.setLocationRelativeTo(_fenetre);
        DIALOG.setDialogue();
    }

    public static Nicknames getPseudos() {
        return DIALOG.pseudos;
    }

    /**Met en place le contenu de la boite de dialogue
    Pour les jeux et les joueurs on a besoin d'onglets pour utiliser moins de place sur l'ecran*/
    public void setDialogue() {
        getJt().removeAll();
        Container container_=new Container();
        container_.setLayout(new BorderLayout());
        //Panneau pseudos des joueurs belote
        JPanel sousPanneau_=new JPanel();
        sousPanneau_.setLayout(new GridLayout(0,1));
        int i_=0;
        for (String p:pseudos.getPseudosBelote()) {
            JTextField pseudo_=new JTextField(30);
            pseudo_.setText(p);
            sousPanneau_.add(new JLabel(StringList.simpleNumberFormat(messages.getVal(NICKNAME_PLAYER), i_+1)));
            sousPanneau_.add(pseudo_);
            nicknamesBelote.add(pseudo_);
            i_++;
        }
        getJt().add(GameEnum.BELOTE.toString(Constants.getLanguage()),sousPanneau_);
        //Panneau pseudos des joueurs president
        sousPanneau_=new JPanel();
        sousPanneau_.setLayout(new GridLayout(0,1));
        i_=0;
        for (String p:pseudos.getPseudosPresident()) {
            JTextField pseudo_=new JTextField(30);
            pseudo_.setText(p);
            sousPanneau_.add(new JLabel(StringList.simpleNumberFormat(messages.getVal(NICKNAME_PLAYER), i_+1)));
            sousPanneau_.add(pseudo_);
            nicknamesPresident.add(pseudo_);
            i_++;
        }
        JScrollPane scroll_ = new JScrollPane(sousPanneau_);
        scroll_.setPreferredSize(new Dimension(300, 400));
        getJt().add(GameEnum.PRESIDENT.toString(Constants.getLanguage()), scroll_);
        //Panneau pseudos des joueurs tarot
        sousPanneau_=new JPanel();
        sousPanneau_.setLayout(new GridLayout(0,1));
        i_=0;
        for (String p:pseudos.getPseudosTarot()) {
            JTextField pseudo_=new JTextField(30);
            pseudo_.setText(p);
            sousPanneau_.add(new JLabel(StringList.simpleNumberFormat(messages.getVal(NICKNAME_PLAYER), i_+1)));
            sousPanneau_.add(pseudo_);
            nicknamesTarot.add(pseudo_);
            i_++;
        }
        getJt().add(GameEnum.TAROT.toString(Constants.getLanguage()),sousPanneau_);
        container_.add(getJt(),BorderLayout.CENTER);
        //Panneau pseudo du joueur
        sousPanneau_=new JPanel();
        sousPanneau_.setLayout(new BoxLayout(sousPanneau_,BoxLayout.PAGE_AXIS));
        sousPanneau_.add(new JLabel(messages.getVal(NICKNAME)));
        nickname=new JTextField(30);
        nickname.setText(pseudos.getPseudo());
        sousPanneau_.add(nickname);
        LabelButton bouton_=new LabelButton(messages.getVal(VALIDATE));
        bouton_.addMouseListener(new ListenerNicknames(this));
        sousPanneau_.add(bouton_);
        container_.add(sousPanneau_,BorderLayout.SOUTH);
        setContentPane(container_);
        pack();
        setVisible(true);
    }

    /**Enregistre les_ informations_ dans_ une_ variable_ et_ ferme_ la_ boite_ de_ dialogue_*/
    public void validateNicknames() {
        if(unChampVidePresent()) {
            ConfirmDialog.showMessage(this, messages.getVal(FORBIDDEN_EMPTY), messages.getVal(ERROR_SAVE), Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(this,messages.getVal(FORBIDDEN_EMPTY), messages.getVal(ERROR_SAVE),JOptionPane.ERROR_MESSAGE);
        } else if(tabulationPresente()) {
            ConfirmDialog.showMessage(this, messages.getVal(FORBIDDEN_TAB), messages.getVal(ERROR_SAVE), Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(this,messages.getVal(FORBIDDEN_TAB), messages.getVal(ERROR_SAVE),JOptionPane.ERROR_MESSAGE);
        } else {
            pseudos.setPseudo(nickname.getText());
            StringList pseudos_=new StringList();
            int nbBotPlayersBelote_ = pseudos.getPseudosBelote().size();
            for (int i = CustList.FIRST_INDEX; i < nbBotPlayersBelote_; i++) {
                pseudos_.add(nicknamesBelote.get(i).getText());
            }
            pseudos.setPseudosBelote(pseudos_);
            pseudos_=new StringList();
            int nbBotPlayersPresident_ = pseudos.getPseudosPresident().size();
            for (int i = CustList.FIRST_INDEX; i < nbBotPlayersPresident_; i++) {
                pseudos_.add(nicknamesPresident.get(i).getText());
            }
            pseudos.setPseudosPresident(pseudos_);
            pseudos_=new StringList();
            int nbBotPlayersTarot_ = pseudos.getPseudosTarot().size();
            for (int i = CustList.FIRST_INDEX; i < nbBotPlayersTarot_; i++) {
                pseudos_.add(nicknamesTarot.get(i).getText());
            }
            pseudos.setPseudosTarot(pseudos_);
            closeWindow();
        }
    }
    /**Retourne vrai_ si_ et_ seulement_ si_ il_ existe_ un_ nom_ de_ joueur_ ayant_ une_ tabulation_*/
    private boolean tabulationPresente() {
        boolean tabulation_=nickname.getText().contains(TAB);
        if(tabulation_) {
            return true;
        }
        int nbBotPlayersBelote_ = pseudos.getPseudosBelote().size();
        for (int i = CustList.FIRST_INDEX; i < nbBotPlayersBelote_; i++) {
            if (nicknamesBelote.get(i).getText().contains(TAB)) {
                tabulation_ = true;
            }
        }
        int nbBotPlayersPresident_ = pseudos.getPseudosPresident().size();
        for (int i = CustList.FIRST_INDEX; i < nbBotPlayersPresident_; i++) {
            if (nicknamesPresident.get(i).getText().contains(TAB)) {
                tabulation_ = true;
            }
        }
        int nbBotPlayersTarot_ = pseudos.getPseudosTarot().size();
        for (int i = CustList.FIRST_INDEX; i < nbBotPlayersTarot_; i++) {
            if (nicknamesTarot.get(i).getText().contains(TAB)) {
                tabulation_ = true;
            }
        }
        return tabulation_;
    }
    /**Retourne vrai_ si_ et_ seulement_ si_ il_ existe_ un_ champ_ vide_ parmi_ les_ champs_ de_ texte_*/
    private boolean unChampVidePresent() {
        boolean vide_= nickname.getText().isEmpty();
        if(vide_) {
            return true;
        }
        int nbBotPlayersBelote_ = pseudos.getPseudosBelote().size();
        for (int i = CustList.FIRST_INDEX; i < nbBotPlayersBelote_; i++) {
            if (nicknamesBelote.get(i).getText().isEmpty()) {
                vide_ = true;
            }
        }
        int nbBotPlayersPresident_ = pseudos.getPseudosPresident().size();
        for (int i = CustList.FIRST_INDEX; i < nbBotPlayersPresident_; i++) {
            if (nicknamesPresident.get(i).getText().isEmpty()) {
                vide_ = true;
            }
        }
        int nbBotPlayersTarot_ = pseudos.getPseudosTarot().size();
        for (int i = CustList.FIRST_INDEX; i < nbBotPlayersTarot_; i++) {
            if (nicknamesTarot.get(i).getText().isEmpty()) {
                vide_ = true;
            }
        }
        return vide_;
    }
}
