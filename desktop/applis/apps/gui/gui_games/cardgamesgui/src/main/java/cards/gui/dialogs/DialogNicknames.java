package cards.gui.dialogs;





import cards.facade.Nicknames;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.dialogs.events.ListenerNicknames;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class DialogNicknames extends DialogCards {

    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialognicknames";

    private static final String TAB = "\t";
    private static final String CST_NICKNAME = "nickname";
    private static final String NICKNAME_PLAYER = "nicknamePlayer";
    private static final String VALIDATE = "validate";
    private static final String ERROR_SAVE = "errorSave";
    private static final String FORBIDDEN_EMPTY = "forbiddenEmpty";
    private static final String FORBIDDEN_TAB = "forbiddenTab";
    private StringMap<String> messages;
    private Nicknames pseudos;
    private AbsTextField nickname;
    private final CustList<AbsTextField> nicknamesBelote = new CustList<AbsTextField>();
    private final CustList<AbsTextField> nicknamesTarot = new CustList<AbsTextField>();
    private final CustList<AbsTextField> nicknamesPresident = new CustList<AbsTextField>();

    public DialogNicknames(AbstractProgramInfos _frameFactory) {
        super(_frameFactory);
        getCardDialog().setAccessFile(DIALOG_ACCESS);
    }
    public static void initDialogNicknames(String _titre, WindowCards _fenetre) {
        _fenetre.getDialogNicknames().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        _fenetre.getDialogNicknames().getCardDialog().setTitle(_titre);
//        DIALOG.messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), DIALOG.getClass());
        _fenetre.getDialogNicknames().messages = WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _fenetre.getLanguageKey(), _fenetre.getDialogNicknames().getCardDialog().getAccessFile());
        _fenetre.getDialogNicknames().pseudos = _fenetre.getPseudosJoueurs();
        _fenetre.getDialogNicknames().setMain(_fenetre);
        _fenetre.getDialogNicknames().getCardDialog().setLocationRelativeTo(_fenetre);
        _fenetre.getDialogNicknames().setDialogue(_fenetre);
    }

    public static Nicknames getPseudos(DialogNicknames _dialog) {
        return _dialog.pseudos;
    }

    /**Met en place le contenu de la boite de dialogue
    Pour les jeux et les joueurs on a besoin d'onglets pour utiliser moins de place sur l'ecran*/
    public void setDialogue(WindowCards _fenetre) {
        String lg_ = _fenetre.getLanguageKey();
        getJt().removeAll();
        AbsPanel container_=_fenetre.getCompoFactory().newBorder();
        //Panneau pseudos des joueurs belote
        AbsPanel sousPanneau_=_fenetre.getCompoFactory().newGrid(0,1);
        int i_=0;
        for (String p:pseudos.getPseudosBelote()) {
            AbsTextField pseudo_= getCompoFactory().newTextField(30);
            pseudo_.setText(p);
            sousPanneau_.add(getCompoFactory().newPlainLabel(StringUtil.simpleNumberFormat(messages.getVal(NICKNAME_PLAYER), i_+1)));
            sousPanneau_.add(pseudo_);
            nicknamesBelote.add(pseudo_);
            i_++;
        }
        getJt().add(GameEnum.BELOTE.toString(lg_),sousPanneau_);
        //Panneau pseudos des joueurs president
        sousPanneau_=_fenetre.getCompoFactory().newGrid(0,1);
        i_=0;
        for (String p:pseudos.getPseudosPresident()) {
            AbsTextField pseudo_=getCompoFactory().newTextField(30);
            pseudo_.setText(p);
            sousPanneau_.add(getCompoFactory().newPlainLabel(StringUtil.simpleNumberFormat(messages.getVal(NICKNAME_PLAYER), i_+1)));
            sousPanneau_.add(pseudo_);
            nicknamesPresident.add(pseudo_);
            i_++;
        }
        AbsScrollPane scroll_ = _fenetre.getCompoFactory().newAbsScrollPane(sousPanneau_);
        scroll_.setPreferredSize(new MetaDimension(300, 400));
        getJt().add(GameEnum.PRESIDENT.toString(lg_), scroll_);
        //Panneau pseudos des joueurs tarot
        sousPanneau_=_fenetre.getCompoFactory().newGrid(0,1);
        i_=0;
        for (String p:pseudos.getPseudosTarot()) {
            AbsTextField pseudo_=getCompoFactory().newTextField(30);
            pseudo_.setText(p);
            sousPanneau_.add(getCompoFactory().newPlainLabel(StringUtil.simpleNumberFormat(messages.getVal(NICKNAME_PLAYER), i_+1)));
            sousPanneau_.add(pseudo_);
            nicknamesTarot.add(pseudo_);
            i_++;
        }
        getJt().add(GameEnum.TAROT.toString(lg_),sousPanneau_);
        container_.add(getJt(),GuiConstants.BORDER_LAYOUT_CENTER);
        //Panneau pseudo du joueur
        sousPanneau_=_fenetre.getCompoFactory().newPageBox();
        sousPanneau_.add(getCompoFactory().newPlainLabel(messages.getVal(CST_NICKNAME)));
        nickname=getCompoFactory().newTextField(30);
        nickname.setText(pseudos.getPseudo());
        sousPanneau_.add(nickname);
        AbsPlainButton bouton_=getCompoFactory().newPlainButton(messages.getVal(VALIDATE));
        bouton_.addActionListener(new ListenerNicknames(this));
        sousPanneau_.add(bouton_);
        container_.add(sousPanneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        getCardDialog().setContentPane(container_);
        getCardDialog().pack();
        getCardDialog().setVisible(true);
    }

    /**Enregistre les_ informations_ dans_ une_ variable_ et_ ferme_ la_ boite_ de_ dialogue_*/
    public void validateNicknames() {
        String lg_ = getMain().getLanguageKey();
        if(unChampVidePresent()) {
            ConfirmDialog.showMessage(getCardDialog(), messages.getVal(FORBIDDEN_EMPTY), messages.getVal(ERROR_SAVE), lg_, GuiConstants.ERROR_MESSAGE, getMain().getConfirmDialog());
            //JOptionPane.showMessageDialog(this,messages.getVal(FORBIDDEN_EMPTY), messages.getVal(ERROR_SAVE),JOptionPane.ERROR_MESSAGE);
        } else if(tabulationPresente()) {
            ConfirmDialog.showMessage(getCardDialog(), messages.getVal(FORBIDDEN_TAB), messages.getVal(ERROR_SAVE), lg_, GuiConstants.ERROR_MESSAGE, getMain().getConfirmDialog());
            //JOptionPane.showMessageDialog(this,messages.getVal(FORBIDDEN_TAB), messages.getVal(ERROR_SAVE),JOptionPane.ERROR_MESSAGE);
        } else {
            pseudos.setPseudo(nickname.getText());
            StringList pseudos_=new StringList();
            int nbBotPlayersBelote_ = pseudos.getPseudosBelote().size();
            for (int i = IndexConstants.FIRST_INDEX; i < nbBotPlayersBelote_; i++) {
                pseudos_.add(nicknamesBelote.get(i).getText());
            }
            pseudos.setPseudosBelote(pseudos_);
            pseudos_=new StringList();
            int nbBotPlayersPresident_ = pseudos.getPseudosPresident().size();
            for (int i = IndexConstants.FIRST_INDEX; i < nbBotPlayersPresident_; i++) {
                pseudos_.add(nicknamesPresident.get(i).getText());
            }
            pseudos.setPseudosPresident(pseudos_);
            pseudos_=new StringList();
            int nbBotPlayersTarot_ = pseudos.getPseudosTarot().size();
            for (int i = IndexConstants.FIRST_INDEX; i < nbBotPlayersTarot_; i++) {
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
        for (int i = IndexConstants.FIRST_INDEX; i < nbBotPlayersBelote_; i++) {
            if (nicknamesBelote.get(i).getText().contains(TAB)) {
                tabulation_ = true;
            }
        }
        int nbBotPlayersPresident_ = pseudos.getPseudosPresident().size();
        for (int i = IndexConstants.FIRST_INDEX; i < nbBotPlayersPresident_; i++) {
            if (nicknamesPresident.get(i).getText().contains(TAB)) {
                tabulation_ = true;
            }
        }
        int nbBotPlayersTarot_ = pseudos.getPseudosTarot().size();
        for (int i = IndexConstants.FIRST_INDEX; i < nbBotPlayersTarot_; i++) {
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
        for (int i = IndexConstants.FIRST_INDEX; i < nbBotPlayersBelote_; i++) {
            if (nicknamesBelote.get(i).getText().isEmpty()) {
                vide_ = true;
            }
        }
        int nbBotPlayersPresident_ = pseudos.getPseudosPresident().size();
        for (int i = IndexConstants.FIRST_INDEX; i < nbBotPlayersPresident_; i++) {
            if (nicknamesPresident.get(i).getText().isEmpty()) {
                vide_ = true;
            }
        }
        int nbBotPlayersTarot_ = pseudos.getPseudosTarot().size();
        for (int i = IndexConstants.FIRST_INDEX; i < nbBotPlayersTarot_; i++) {
            if (nicknamesTarot.get(i).getText().isEmpty()) {
                vide_ = true;
            }
        }
        return vide_;
    }
}
