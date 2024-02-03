package cards.gui.dialogs;



import cards.facade.FacadeCards;
import cards.facade.Games;
import cards.facade.SoftParams;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.WindowCardsCore;
import cards.gui.WindowCardsInt;
import cards.gui.dialogs.events.ListenerChangeSlide;
import cards.gui.dialogs.events.ListenerParameters;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DialogSoft extends DialogCards {

    private SoftParams parametres=new SoftParams();
    private ComboBox<GameEnum> list;
    private AbsCustCheckBox saveHomeFolder;
    private String menu;
    private AbsCustCheckBox waitTrickClick;
    private AbsSlider delayWaitTricks;
    private AbsSlider delayWaitCards;
    private AbsSlider delayWaitBids;
    private AbsCustCheckBox clickCard;
    private AbsButton validate;

    public DialogSoft(AbstractProgramInfos _frameFactory) {
        super(_frameFactory, null);
    }
    public static void initDialogSoft(String _titre, WindowCardsInt _fenetre) {
        _fenetre.getDialogSoft().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogSoft().getCardDialog().setTitle(_titre);
//        DIALOG.messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), DIALOG.getClass());
        _fenetre.getDialogSoft().parametres = _fenetre.getParametresLogiciel();
        _fenetre.getDialogSoft().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
    }

    public static SoftParams getParametres(DialogSoft _dialog) {
        _dialog.getCardDialog().setVisible(true);
        return _dialog.parametres;
    }

    public static void setDialogSoft(String _menu, WindowCardsInt _fenetre) {
        _fenetre.getDialogSoft().setDialogue(_menu, _fenetre);
    }
    private void setDialogue(String _menu, WindowCardsInt _fenetre) {
        menu = _menu;
        TranslationsLg lg_ = _fenetre.getFrames().currentLg();
        StringMap<String> messSoft_ = Games.getDialogSoftTr(Games.getAppliTr(lg_)).getMapping();
        AbsPanel container_=_fenetre.getCompoFactory().newBorder();
        if(StringUtil.quickEq(menu, MessagesGuiCards.CST_LAUNCHING)) {
            //Lancement du logiciel
            AbsPanel panneau_=_fenetre.getCompoFactory().newPageBox();
            list = new ComboBox<GameEnum>(GuiBaseUtil.combo(_fenetre.getImageFactory(),new StringList(), -1, _fenetre.getCompoFactory()));
            IdMap<GameEnum,String> mess_;
            IdList<GameEnum> order_;
            mess_ = new IdMap<GameEnum,String>();
            order_ = new IdList<GameEnum>();
            mess_.put(GameEnum.NONE, messSoft_.getVal(MessagesGuiCards.DIAL_SOFT_LAUNCHING));
            order_.add(GameEnum.NONE);
            for (GameEnum g: GameEnum.allValid()) {
                mess_.put(g, g.toString(lg_));
                order_.add(g);
            }
            list.refresh(order_, mess_);
            if (!parametres.getLancement().isEmpty()) {
                list.setSelectedItem(parametres.getLancement().get(0));
            }
//            liste=new JComboBox<>(new Object[]{messages.getVal(LAUNCHING),GameEnum.BELOTE,GameEnum.PRESIDENT,GameEnum.TAROT});
//            panneau_.add(liste);
            panneau_.add(list.self());
            saveHomeFolder = getCompoFactory().newCustCheckBox(messSoft_.getVal(MessagesGuiCards.DIAL_SOFT_SELECT_HOME_PATH));
            saveHomeFolder.setSelected(parametres.isSaveHomeFolder());
            panneau_.add(saveHomeFolder);
            container_.add(panneau_,GuiConstants.BORDER_LAYOUT_CENTER);
        } else if(StringUtil.quickEq(menu, MessagesGuiCards.CST_TIMING)) {
            AbsPanel panneau_=_fenetre.getCompoFactory().newPageBox();
//            byte indiceInfo_=0;
//            JSlider barre_=null;
            int valeur_ = parametres.getDelaiAttenteContrats();
            int minValeur_ = 300;
            int maxValeur_ = 2000;
            String sentence_ = messSoft_.getVal(MessagesGuiCards.DIAL_SOFT_WAITING_SENTENCE);
            String prefix_ = messSoft_.getVal(MessagesGuiCards.DIAL_SOFT_WAITING_BIDDING);
            String values_ = StringUtil.simpleNumberFormat(messSoft_.getVal(MessagesGuiCards.DIAL_SOFT_WAITING_VALUES), minValeur_, maxValeur_, valeur_);
            AbsPlainLabel label_ = getCompoFactory().newPlainLabel(StringUtil.simpleStringsFormat(sentence_, prefix_, values_));
            panneau_.add(label_);
            delayWaitBids=getCompoFactory().newAbsSlider(minValeur_,maxValeur_);
            delayWaitBids.setValue(valeur_);
            delayWaitBids.addChangeListener(new ListenerChangeSlide(delayWaitBids,label_,MessagesGuiCards.DIAL_SOFT_WAITING_BIDDING, messSoft_, sentence_));
            panneau_.add(delayWaitBids);
//            indiceInfo_++;
            valeur_=parametres.getDelaiAttenteCartes();
            prefix_ = messSoft_.getVal(MessagesGuiCards.DIAL_SOFT_WAITING_PLAYED_CARD);
            values_ = StringUtil.simpleNumberFormat(messSoft_.getVal(MessagesGuiCards.DIAL_SOFT_WAITING_VALUES),minValeur_, maxValeur_, valeur_);
            label_ = getCompoFactory().newPlainLabel(StringUtil.simpleStringsFormat(sentence_, prefix_, values_));
            panneau_.add(label_);
            delayWaitCards=getCompoFactory().newAbsSlider(minValeur_,maxValeur_);
            delayWaitCards.setValue(valeur_);
            delayWaitCards.addChangeListener(new ListenerChangeSlide(delayWaitCards,label_,MessagesGuiCards.DIAL_SOFT_WAITING_PLAYED_CARD, messSoft_, sentence_));
            panneau_.add(delayWaitCards);
//            indiceInfo_++;
            valeur_=parametres.getDelaiAttentePlis();
            minValeur_=500;
            maxValeur_=3000;
            prefix_ = messSoft_.getVal(MessagesGuiCards.DIAL_SOFT_WAITING_TRICK);
            values_ = StringUtil.simpleNumberFormat(messSoft_.getVal(MessagesGuiCards.DIAL_SOFT_WAITING_VALUES),minValeur_, maxValeur_, valeur_);
            label_ = getCompoFactory().newPlainLabel(StringUtil.simpleStringsFormat(sentence_, prefix_, values_));
            panneau_.add(label_);
            delayWaitTricks=getCompoFactory().newAbsSlider(minValeur_,maxValeur_);
            delayWaitTricks.setValue(valeur_);
            delayWaitTricks.addChangeListener(new ListenerChangeSlide(delayWaitTricks,label_,MessagesGuiCards.DIAL_SOFT_WAITING_TRICK, messSoft_, sentence_));
            panneau_.add(delayWaitTricks);
//            indiceInfo_++;
            waitTrickClick =getCompoFactory().newCustCheckBox(messSoft_.getVal(MessagesGuiCards.DIAL_SOFT_CLICK_FOR_PLAYING_TRICK));
            waitTrickClick.setSelected(parametres.getAttentePlisClic());
            panneau_.add(waitTrickClick);
            container_.add(panneau_,GuiConstants.BORDER_LAYOUT_CENTER);
        } else {
            AbsPanel panneau_=_fenetre.getCompoFactory().newPageBox();
            clickCard=getCompoFactory().newCustCheckBox(messSoft_.getVal(MessagesGuiCards.DIAL_SOFT_CLICK_FOR_PLAYING_CARD));
            clickCard.setSelected(parametres.getJeuCarteClic());
            panneau_.add(clickCard);
            container_.add(panneau_,GuiConstants.BORDER_LAYOUT_CENTER);
        }
        validate = getCompoFactory().newPlainButton(messSoft_.getVal(MessagesGuiCards.DIAL_SOFT_VALIDATE));
        validate.addActionListener(new ListenerParameters(_fenetre,this));
        container_.add(validate,GuiConstants.BORDER_LAYOUT_SOUTH);
        getCardDialog().setContentPane(container_);
        getCardDialog().pack();
    }

    /**Enregistre les_ informations_ dans_ une_ variable_ et_ ferme_ la_ boite_ de_ dialogue_*/
    public void validateParams(WindowCardsInt _window) {
        if(StringUtil.quickEq(menu, MessagesGuiCards.CST_LAUNCHING)) {
//            Object rep_ = liste.getSelectedItem();
            GameEnum rep_ = list.getCurrent();
            IdList<GameEnum> lancement_ = new IdList<GameEnum>();
            if(rep_ != GameEnum.NONE) {
                //jeu
                lancement_.add(rep_);
            }
            parametres.setLancement(lancement_);
            parametres.setSaveHomeFolder(saveHomeFolder.isSelected());
            closeWindow();
        } else if(StringUtil.quickEq(menu, MessagesGuiCards.CST_TIMING)) {
//            JPanel panneau_=(JPanel)getContentPane().getComponent(0);
//            JSlider slide_=null;
//            int indiceInfo_=1;
//            slide_=(JSlider)panneau_.getComponent(indiceInfo_);
            parametres.setDelaiAttenteContrats(delayWaitBids.getValue());
//            indiceInfo_+=2;
//            slide_=(JSlider)panneau_.getComponent(indiceInfo_);
            parametres.setDelaiAttenteCartes(delayWaitCards.getValue());
//            indiceInfo_+=2;
//            slide_=(JSlider)panneau_.getComponent(indiceInfo_);
            parametres.setDelaiAttentePlis(delayWaitTricks.getValue());
//            indiceInfo_++;
//            JCheckBox caseCroix_ = (JCheckBox)panneau_.getComponent(indiceInfo_);
            parametres.setAttentePlisClic(waitTrickClick.isSelected());
            closeWindow();
        } else {
//            JPanel panneau_=(JPanel)getContentPane().getComponent(0);
//            JCheckBox caseCroix_=(JCheckBox)panneau_.getComponent(0);
            parametres.setJeuCarteClic(clickCard.isSelected());
            closeWindow();
        }
        WindowCardsCore windowCardsCore_ = _window.baseWindow();
        windowCardsCore_.getFacadeCards().setParametres(parametres);
        windowCardsCore_.getFacadeCards().getParametres().sauvegarder(StringUtil.concat(WindowCards.getTempFolderSl(_window.getFrames()), FacadeCards.PARAMS),_window.getStreams());
        windowCardsCore_.getContainerGame().setSettings(windowCardsCore_.getFacadeCards().getParametres());
    }

    public ComboBox<GameEnum> getList() {
        return list;
    }

    public AbsButton getValidate() {
        return validate;
    }

    public AbsCustCheckBox getSaveHomeFolder() {
        return saveHomeFolder;
    }

    public AbsSlider getDelayWaitBids() {
        return delayWaitBids;
    }

    public AbsSlider getDelayWaitCards() {
        return delayWaitCards;
    }

    public AbsSlider getDelayWaitTricks() {
        return delayWaitTricks;
    }

    public AbsCustCheckBox getWaitTrickClick() {
        return waitTrickClick;
    }

    public AbsCustCheckBox getClickCard() {
        return clickCard;
    }
}

