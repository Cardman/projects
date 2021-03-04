package cards.gui.dialogs;

import javax.swing.*;

import cards.belote.enumerations.DealingBelote;
import cards.facade.enumerations.GameEnum;
import cards.gui.MainWindow;
import cards.president.RulesPresident;
import cards.tarot.enumerations.DealingTarot;
import code.gui.*;
import code.gui.events.ClosingDialogEvent;
import code.gui.events.CreateServerEvent;
import code.gui.events.JoinServerEvent;
import code.network.enums.IpType;
import code.sml.stream.ExtractFromFiles;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;

public final class DialogServer extends DialogCards implements AbstractDialogServer{
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogserver";

    private static final String CANCEL = "cancel";
    private static final String CREATE_SERVER = "createServer";
    private static final String IP_SERVER = "ipServer";
    private static final String IP_SERVER_TOOL_TIP = "ipServerToolTip";
    private static final String JOIN_SERVER = "joinServer";
    private static final String NUMBER_PLAYERS = "numberPlayers";
    private static final String TITLE = "title";
    private TextField ipOrHostName;
    private ComboBox<IpType> ipType;
    private boolean create;
    private boolean join;
    private Spinner nbPlayers;
    private StringMap<String> messages;
    private EnumMap<IpType,String> messagesIpEnum;
//    private JComboBox<DealingBelote> repBelote;
//    private JComboBox<DealingTarot> repTarot;

    public DialogServer() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setDialogServer(MainWindow _fenetre, GameEnum _game) {
        _fenetre.getDialogServer().initDialogServer(_fenetre, _game);
    }

    private void initDialogServer(MainWindow _fenetre, GameEnum _game) {
        setDialogIcon(_fenetre);
        create = false;
        join = false;
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
        messages = MainWindow.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _fenetre.getLanguageKey(), getAccessFile());
        messagesIpEnum = new EnumMap<IpType,String>();
        for (IpType i: IpType.values()) {
            messagesIpEnum.put(i, i.toString(_fenetre.getLanguageKey()));
        }
        setTitle(messages.getVal(TITLE));
        setLocationRelativeTo(_fenetre);
        ipOrHostName = new TextField();
        Panel pane_ = Panel.newGrid(0, 1);
        Panel panel_ = Panel.newGrid(0, 2);
        if (_game == GameEnum.TAROT) {
            EnumList<DealingTarot> repValides_ = new EnumList<DealingTarot>(DealingTarot.getRepartitionsValides());
            int minJoueurs_=repValides_.get(0).getNombreJoueurs();
            int maxJoueurs_=repValides_.get(0).getNombreJoueurs();
            for(DealingTarot r: repValides_){
                if(minJoueurs_>r.getNombreJoueurs()){
                    minJoueurs_=r.getNombreJoueurs();
                }
                if(maxJoueurs_<r.getNombreJoueurs()){
                    maxJoueurs_=r.getNombreJoueurs();
                }
            }
            nbPlayers = new Spinner(minJoueurs_,minJoueurs_,maxJoueurs_,1);
            panel_.add(new TextLabel(messages.getVal(NUMBER_PLAYERS)));
            panel_.add(nbPlayers);
        } else if (_game == GameEnum.PRESIDENT) {
            int minJoueurs_ = RulesPresident.getNbMinPlayers();
            int maxJoueurs_ = RulesPresident.getNbMaxPlayers();
            nbPlayers = new Spinner(minJoueurs_,minJoueurs_,maxJoueurs_,1);
            panel_.add(new TextLabel(messages.getVal(NUMBER_PLAYERS)));
            panel_.add(nbPlayers);
        } else {
            EnumList<DealingBelote> repValides_ = new EnumList<DealingBelote>(DealingBelote.getRepartitionsValides());
            int minJoueurs_=repValides_.get(0).getNombreJoueurs();
            int maxJoueurs_=repValides_.get(0).getNombreJoueurs();
            for(DealingBelote r: repValides_){
                if(minJoueurs_>r.getNombreJoueurs()){
                    minJoueurs_=r.getNombreJoueurs();
                }
                if(maxJoueurs_<r.getNombreJoueurs()){
                    maxJoueurs_=r.getNombreJoueurs();
                }
            }
            nbPlayers = new Spinner(minJoueurs_,minJoueurs_,maxJoueurs_,1);
            panel_.add(new TextLabel(messages.getVal(NUMBER_PLAYERS)));
            panel_.add(nbPlayers);
        }
        TextLabel ipServer_ = new TextLabel(messages.getVal(IP_SERVER));
        ipServer_.setToolTipText(messages.getVal(IP_SERVER_TOOL_TIP));
        panel_.add(ipServer_);
        panel_.add(ipOrHostName);
        EnumList<IpType> list_ = new EnumList<IpType>(IpType.values());
        ipType = new ComboBox<IpType>(_fenetre.getFrames().getGeneComboBox().createCombo(new StringList(), -1));
        ipType.setWithDefaultValue(false);
        ipType.refresh(list_, messagesIpEnum);
        ipType.setSelectedItem(IpType.HOST_NAME);
//        ipType = new JComboBox<>(IpType.values());
//        ipType.setSelectedItem(IpType.HOST_NAME);
        panel_.add(ipType.self());
        pane_.add(panel_);
        panel_ = Panel.newLineBox();
        LabelButton button_ = new LabelButton(messages.getVal(CREATE_SERVER));
        button_.addMouseListener(new CreateServerEvent(this));
        panel_.add(button_);
        button_ = new LabelButton(messages.getVal(JOIN_SERVER));
        button_.addMouseListener(new JoinServerEvent(this));
        panel_.add(button_);
        button_ = new LabelButton(messages.getVal(CANCEL));
        button_.addMouseListener(new ClosingDialogEvent(this));
        panel_.add(button_);
        pane_.add(panel_);
        setContentPane(pane_);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
    }

    @Override
    public void createServerChoice() {
        join = false;
        create = true;
        closeWindow();
    }

    @Override
    public void joinServerChoice() {
        join = true;
        create = false;
        closeWindow();
    }

    public static String getIpOrHostName(DialogServer _dialog) {
        _dialog.setVisible(true);
        return _dialog.ipOrHostName.getText();
    }

    public static IpType getIpType(DialogServer _dialog) {
        return _dialog.ipType.getCurrent();
    }
    public static int getNbPlayers(DialogServer _dialog) {
        return _dialog.nbPlayers.getValue();
    }
//    public DealingTarot getReparitionTarot() {
//        return (DealingTarot) repTarot.getSelectedItem();
//    }
//    public DealingBelote getReparitionBelote() {
//        return (DealingBelote) repBelote.getSelectedItem();
//    }
    public static boolean isCreate(DialogServer _dialog) {
        return _dialog.create;
    }

    public static boolean isChoosen(DialogServer _dialog) {
        return _dialog.create || _dialog.join;
    }

}
