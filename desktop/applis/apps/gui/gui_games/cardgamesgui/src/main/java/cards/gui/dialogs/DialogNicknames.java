package cards.gui.dialogs;





import cards.facade.Games;
import cards.facade.Nicknames;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.WindowCardsCore;
import cards.gui.dialogs.events.ListenerNicknames;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class DialogNicknames extends DialogHelpCards {

    private Nicknames pseudos;
    private AbsTextField nickname;
    private final CustList<AbsTextField> nicknamesBelote = new CustList<AbsTextField>();
    private final CustList<AbsTextField> nicknamesTarot = new CustList<AbsTextField>();
    private final CustList<AbsTextField> nicknamesPresident = new CustList<AbsTextField>();
    private AbsButton validate;
    private final EnabledMenu associated;

    public DialogNicknames(AbstractProgramInfos _frameFactory, EnabledMenu _menu) {
        super(_frameFactory);
        associated = _menu;
    }
    public static void initDialogNicknames(String _titre, WindowCards _fenetre) {
        _fenetre.getDialogNicknames().associated.setEnabled(false);
//        _fenetre.getDialogNicknames().getAbsDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogNicknames().setTitleDialog(_fenetre,_titre);
//        DIALOG.messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), DIALOG.getClass());
        _fenetre.getDialogNicknames().pseudos = _fenetre.getPseudosJoueurs();
//        _fenetre.getDialogNicknames().getAbsDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        _fenetre.getDialogNicknames().setDialogue(_fenetre);
    }

    @Override
    public void closeWindow() {
        super.closeWindow();
        associated.setEnabled(true);
    }

    public static Nicknames getPseudos(DialogNicknames _dialog) {
        return _dialog.pseudos;
    }

    /**Met en place le contenu de la boite de dialogue
    Pour les jeux et les joueurs on a besoin d'onglets pour utiliser moins de place sur l'ecran*/
    public void setDialogue(WindowCards _fenetre) {
        TranslationsLg lg_ = _fenetre.getFrames().currentLg();
        StringMap<String> nicknamesMessages_ = Games.getDialogNicknameTr(Games.getAppliTr(lg_)).getMapping();
        AbsTabbedPane jt_ = _fenetre.getCompoFactory().newAbsTabbedPane();
        jt_.removeAll();
        AbsPanel container_=_fenetre.getCompoFactory().newBorder();
        //Panneau pseudos des joueurs belote
        AbsPanel sousPanneau_=_fenetre.getCompoFactory().newPageBox();
        int i_=0;
        for (String p:pseudos.getPseudosBelote()) {
            AbsTextField pseudo_= getCompoFactory().newTextField(30);
            pseudo_.setText(p);
            sousPanneau_.add(getCompoFactory().newPlainLabel(StringUtil.simpleNumberFormat(nicknamesMessages_.getVal(MessagesGuiCards.DIAL_NICK_NICKNAME_PLAYER), i_+1)));
            sousPanneau_.add(pseudo_);
            nicknamesBelote.add(pseudo_);
            i_++;
        }
        jt_.add(GameEnum.BELOTE.toString(lg_),sousPanneau_);
        //Panneau pseudos des joueurs president
        sousPanneau_=_fenetre.getCompoFactory().newPageBox();
        i_=0;
        for (String p:pseudos.getPseudosPresident()) {
            AbsTextField pseudo_=getCompoFactory().newTextField(30);
            pseudo_.setText(p);
            sousPanneau_.add(getCompoFactory().newPlainLabel(StringUtil.simpleNumberFormat(nicknamesMessages_.getVal(MessagesGuiCards.DIAL_NICK_NICKNAME_PLAYER), i_+1)));
            sousPanneau_.add(pseudo_);
            nicknamesPresident.add(pseudo_);
            i_++;
        }
        AbsScrollPane scroll_ = _fenetre.getCompoFactory().newAbsScrollPane(sousPanneau_);
        scroll_.setPreferredSize(new MetaDimension(300, 400));
        jt_.add(GameEnum.PRESIDENT.toString(lg_), scroll_);
        //Panneau pseudos des joueurs tarot
        sousPanneau_=_fenetre.getCompoFactory().newPageBox();
        i_=0;
        for (String p:pseudos.getPseudosTarot()) {
            AbsTextField pseudo_=getCompoFactory().newTextField(30);
            pseudo_.setText(p);
            sousPanneau_.add(getCompoFactory().newPlainLabel(StringUtil.simpleNumberFormat(nicknamesMessages_.getVal(MessagesGuiCards.DIAL_NICK_NICKNAME_PLAYER), i_+1)));
            sousPanneau_.add(pseudo_);
            nicknamesTarot.add(pseudo_);
            i_++;
        }
        jt_.add(GameEnum.TAROT.toString(lg_),sousPanneau_);
        container_.add(jt_,GuiConstants.BORDER_LAYOUT_CENTER);
        //Panneau pseudo du joueur
        sousPanneau_=_fenetre.getCompoFactory().newPageBox();
        sousPanneau_.add(getCompoFactory().newPlainLabel(nicknamesMessages_.getVal(MessagesGuiCards.DIAL_NICK_CST_NICKNAME)));
        nickname=getCompoFactory().newTextField(30);
        nickname.setText(pseudos.getPseudo());
        sousPanneau_.add(nickname);
        validate = getCompoFactory().newPlainButton(nicknamesMessages_.getVal(MessagesGuiCards.DIAL_NICK_VALIDATE));
        validate.addActionListener(new ListenerNicknames(_fenetre,this));
        sousPanneau_.add(validate);
        container_.add(sousPanneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        getAbsDialog().setContentPane(container_);
        getAbsDialog().pack();
        getAbsDialog().setVisible(true);
    }

    /**Enregistre les_ informations_ dans_ une_ variable_ et_ ferme_ la_ boite_ de_ dialogue_*/
    public void validateNicknames(WindowCards _window) {
        pseudos.setPseudo(nickname.getText());
        StringList pseudos_=new StringList();
        int nbBotPlayersBelote_ = nicknamesBelote.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nbBotPlayersBelote_; i++) {
            pseudos_.add(nicknamesBelote.get(i).getText());
        }
        pseudos.setPseudosBelote(pseudos_);
        pseudos_=new StringList();
        int nbBotPlayersPresident_ = nicknamesPresident.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nbBotPlayersPresident_; i++) {
            pseudos_.add(nicknamesPresident.get(i).getText());
        }
        pseudos.setPseudosPresident(pseudos_);
        pseudos_=new StringList();
        int nbBotPlayersTarot_ = nicknamesTarot.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nbBotPlayersTarot_; i++) {
            pseudos_.add(nicknamesTarot.get(i).getText());
        }
        pseudos.setPseudosTarot(pseudos_);
        closeWindow();
        WindowCardsCore core_ = _window.getCore();
        core_.getFacadeCards().setPseudosJoueurs(DialogNicknames.getPseudos(this));
        core_.getFacadeCards().getNicknamesCrud().getNicknamesCrud().value(core_.getFacadeCards().getPseudosJoueurs());
        core_.getContainerGame().setNicknames(core_.getFacadeCards().getPseudosJoueurs());
    }

    public AbsTextField getNickname() {
        return nickname;
    }

    public CustList<AbsTextField> getNicknamesBelote() {
        return nicknamesBelote;
    }

    public CustList<AbsTextField> getNicknamesPresident() {
        return nicknamesPresident;
    }

    public CustList<AbsTextField> getNicknamesTarot() {
        return nicknamesTarot;
    }

    public AbsButton getValidate() {
        return validate;
    }
}
