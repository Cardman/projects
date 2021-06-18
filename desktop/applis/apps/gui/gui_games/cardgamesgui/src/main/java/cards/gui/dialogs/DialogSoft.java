package cards.gui.dialogs;
import java.awt.BorderLayout;
import java.awt.Dimension;

import cards.facade.SoftParams;
import cards.facade.enumerations.GameEnum;
import cards.gui.MainWindow;
import cards.gui.dialogs.events.ListenerChangeSlide;
import cards.gui.dialogs.events.ListenerParameters;
import code.gui.*;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DialogSoft extends DialogCards {

    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogsoft";

    private static final String WAITING_SENTENCE = "waitingSentence";
    private static final String WAITING_VALUES = "waitingValues";
    private static final String CLICK_FOR_PLAYING_CARD = "clickForPlayingCard";
    private static final String CLICK_FOR_PLAYING_TRICK = "clickForPlayingTrick";
    private static final String LAUNCHING = "launching";
    private static final String VALIDATE = "validate";
    private static final String WAITING_BIDDING = "waitingBidding";
    private static final String WAITING_PLAYED_CARD = "waitingPlayedCard";
    private static final String WAITING_TRICK = "waitingTrick";
    private static final String SELECT_HOME_PATH = "selectHomePath";


    private StringMap<String> messages;
    private SoftParams parametres=new SoftParams();
    private ComboBox<GameEnum> list;
    private CustCheckBox saveHomeFolder;
    private String menu;
    private CustCheckBox waitTrickClick;
    private Slider delayWaitTricks;
    private Slider delayWaitCards;
    private Slider delayWaitBids;
    private CustCheckBox clickCard;

    public DialogSoft() {
        setAccessFile(DIALOG_ACCESS);
    }
    public static void initDialogSoft(String _titre, MainWindow _fenetre) {
        _fenetre.getDialogSoft().setDialogIcon(_fenetre);
        _fenetre.getDialogSoft().setTitle(_titre);
//        DIALOG.messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), DIALOG.getClass());
        _fenetre.getDialogSoft().messages = MainWindow.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _fenetre.getLanguageKey(), _fenetre.getDialogSoft().getAccessFile());
        _fenetre.getDialogSoft().parametres = _fenetre.getParametresLogiciel();
        _fenetre.getDialogSoft().setLocationRelativeTo(_fenetre);
    }

    public static SoftParams getParametres(DialogSoft _dialog) {
        _dialog.setVisible(true);
        return _dialog.parametres;
    }

    public static void setDialogSoft(String _menu, MainWindow _fenetre) {
        _fenetre.getDialogSoft().setDialogue(_menu, _fenetre);
    }
    private void setDialogue(String _menu, MainWindow _fenetre) {
        menu = _menu;
        String lg_ = _fenetre.getLanguageKey();
        Panel container_=Panel.newBorder();
        if(StringUtil.quickEq(menu,MainWindow.CST_LAUNCHING)) {
            //Lancement du logiciel
            Panel panneau_=Panel.newPageBox();
            list = new ComboBox<GameEnum>(_fenetre.getFrames().getGeneComboBox().createCombo(new StringList(), -1));
            EnumMap<GameEnum,String> mess_;
            EnumList<GameEnum> order_;
            mess_ = new EnumMap<GameEnum,String>();
            order_ = new EnumList<GameEnum>();
            mess_.put(null, messages.getVal(LAUNCHING));
            order_.add(null);
            for (GameEnum g: GameEnum.values()) {
                mess_.put(g, g.toString(lg_));
                order_.add(g);
            }
            list.refresh(order_, mess_);
//            liste=new JComboBox<>(new Object[]{messages.getVal(LAUNCHING),GameEnum.BELOTE,GameEnum.PRESIDENT,GameEnum.TAROT});
//            panneau_.add(liste);
            panneau_.add(list.getCombo().self());
            saveHomeFolder = new CustCheckBox(messages.getVal(SELECT_HOME_PATH));
            panneau_.add(saveHomeFolder);
            container_.add(panneau_,BorderLayout.CENTER);
        } else if(StringUtil.quickEq(menu,MainWindow.CST_TIMING)) {
            Panel panneau_=Panel.newGrid(0,1);
            TextLabel label_;
            int valeur_=0;
            int minValeur_=0;
            int maxValeur_=0;
//            byte indiceInfo_=0;
//            JSlider barre_=null;
            valeur_=parametres.getDelaiAttenteContrats();
            minValeur_=300;
            maxValeur_=2000;
            String sentence_ = messages.getVal(WAITING_SENTENCE);
            String prefix_ = messages.getVal(WAITING_BIDDING);
            String values_ = StringUtil.simpleNumberFormat(messages.getVal(WAITING_VALUES), minValeur_, maxValeur_, valeur_);
            label_ = new TextLabel(StringUtil.simpleStringsFormat(sentence_, prefix_, values_));
            panneau_.add(label_);
            delayWaitBids=new Slider(minValeur_,maxValeur_);
            delayWaitBids.setValue(valeur_);
            delayWaitBids.addChangeListener(new ListenerChangeSlide(WAITING_BIDDING, messages, sentence_));
            panneau_.add(delayWaitBids);
//            indiceInfo_++;
            valeur_=parametres.getDelaiAttenteCartes();
            minValeur_=300;
            maxValeur_=2000;
            prefix_ = messages.getVal(WAITING_PLAYED_CARD);
            values_ = StringUtil.simpleNumberFormat(messages.getVal(WAITING_VALUES),minValeur_, maxValeur_, valeur_);
            label_ = new TextLabel(StringUtil.simpleStringsFormat(sentence_, prefix_, values_));
            panneau_.add(label_);
            delayWaitCards=new Slider(minValeur_,maxValeur_);
            delayWaitCards.setValue(valeur_);
            delayWaitCards.addChangeListener(new ListenerChangeSlide(WAITING_PLAYED_CARD, messages, sentence_));
            panneau_.add(delayWaitCards);
//            indiceInfo_++;
            valeur_=parametres.getDelaiAttentePlis();
            minValeur_=500;
            maxValeur_=3000;
            prefix_ = messages.getVal(WAITING_TRICK);
            values_ = StringUtil.simpleNumberFormat(messages.getVal(WAITING_VALUES),minValeur_, maxValeur_, valeur_);
            label_ = new TextLabel(StringUtil.simpleStringsFormat(sentence_, prefix_, values_));
            panneau_.add(label_);
            delayWaitTricks=new Slider(minValeur_,maxValeur_);
            delayWaitTricks.setValue(valeur_);
            delayWaitTricks.addChangeListener(new ListenerChangeSlide(WAITING_TRICK, messages, sentence_));
            panneau_.add(delayWaitTricks);
//            indiceInfo_++;
            waitTrickClick =new CustCheckBox(messages.getVal(CLICK_FOR_PLAYING_TRICK));
            waitTrickClick.setSelected(parametres.getAttentePlisClic());
            panneau_.add(waitTrickClick);
            panneau_.setPreferredSize(new Dimension(600,400));
            container_.add(panneau_,BorderLayout.CENTER);
        } else {
            Panel panneau_=Panel.newGrid(0,1);
            clickCard=new CustCheckBox(messages.getVal(CLICK_FOR_PLAYING_CARD));
            clickCard.setSelected(parametres.getJeuCarteClic());
            panneau_.add(clickCard);
            container_.add(panneau_,BorderLayout.CENTER);
        }
        LabelButton bouton_=new LabelButton(messages.getVal(VALIDATE));
        bouton_.addMouseListener(new ListenerParameters(this));
        container_.add(bouton_,BorderLayout.SOUTH);
        setContentPane(container_);
        pack();
    }

    /**Enregistre les_ informations_ dans_ une_ variable_ et_ ferme_ la_ boite_ de_ dialogue_*/
    public void validateParams() {
        if(StringUtil.quickEq(menu,MainWindow.CST_LAUNCHING)) {
//            Object rep_ = liste.getSelectedItem();
            GameEnum rep_ = list.getCurrent();
            EnumList<GameEnum> lancement_ = new EnumList<GameEnum>();
            if(rep_ != null) {
                //jeu
                lancement_.add(rep_);
            }
            parametres.setLancement(lancement_);
            parametres.setSaveHomeFolder(saveHomeFolder.isSelected());
            closeWindow();
        } else if(StringUtil.quickEq(menu,MainWindow.CST_TIMING)) {
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
    }
}

