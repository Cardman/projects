package cards.gui.dialogs;



import cards.belote.enumerations.DealingBelote;
import cards.facade.enumerations.GameEnum;
import cards.president.RulesPresident;
import cards.tarot.enumerations.DealingTarot;
import code.gui.*;
import code.gui.events.ClosingDialogEvent;
import code.gui.events.CreateServerEvent;
import code.gui.events.JoinServerEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.network.WindowNetWork;
import code.network.enums.IpType;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public final class DialogServerCards extends DialogCards implements AbstractDialogServer{
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogserver";

    private static final String CANCEL = "cancel";
    private static final String CREATE_SERVER = "createServer";
    private static final String IP_SERVER = "ipServer";
    private static final String IP_SERVER_TOOL_TIP = "ipServerToolTip";
    private static final String JOIN_SERVER = "joinServer";
    private static final String NUMBER_PLAYERS = "numberPlayers";
    private static final String TITLE = "title";
    private AbsTextField ipOrHostName;
    private ComboBox<IpType> ipType;
    private boolean create;
    private boolean join;
    private AbsSpinner nbPlayers;
    private StringMap<String> messages;
    private IdMap<IpType,String> messagesIpEnum;
//    private JComboBox<DealingBelote> repBelote;
//    private JComboBox<DealingTarot> repTarot;

    public DialogServerCards(AbstractProgramInfos _frameFactory) {
        super(_frameFactory, null);
        getCardDialog().setAccessFile(DIALOG_ACCESS);
    }

    public static void setDialogServer(WindowNetWork _fenetre, GameEnum _game) {
        _fenetre.getDialogServer().initDialogServer(_fenetre, _game);
    }

    private void initDialogServer(WindowNetWork _fenetre, GameEnum _game) {
        getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        create = false;
        join = false;
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
        messages = WindowNetWork.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _fenetre.getLanguageKey(), getCardDialog().getAccessFile());
        messagesIpEnum = new IdMap<IpType,String>();
        for (IpType i: IpType.values()) {
            messagesIpEnum.put(i, i.toString(_fenetre.getLanguageKey()));
        }
        getCardDialog().setTitle(messages.getVal(TITLE));
        getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        ipOrHostName = getCompoFactory().newTextField();
        AbsPanel pane_ = _fenetre.getCompoFactory().newGrid(0, 1);
        AbsPanel panel_ = _fenetre.getCompoFactory().newGrid(0, 2);
        if (_game == GameEnum.TAROT) {
            IdList<DealingTarot> repValides_ = new IdList<DealingTarot>(DealingTarot.getRepartitionsValides());
            int minJoueurs_= repValides_.get(0).getId().getNombreJoueurs();
            int maxJoueurs_= repValides_.get(0).getId().getNombreJoueurs();
            for(DealingTarot r: repValides_){
                if(minJoueurs_> r.getId().getNombreJoueurs()){
                    minJoueurs_= r.getId().getNombreJoueurs();
                }
                if(maxJoueurs_< r.getId().getNombreJoueurs()){
                    maxJoueurs_= r.getId().getNombreJoueurs();
                }
            }
            nbPlayers = getCompoFactory().newSpinner(minJoueurs_,minJoueurs_,maxJoueurs_,1);
            panel_.add(getCompoFactory().newPlainLabel(messages.getVal(NUMBER_PLAYERS)));
            panel_.add(nbPlayers);
        } else if (_game == GameEnum.PRESIDENT) {
            int minJoueurs_ = RulesPresident.getNbMinPlayers();
            int maxJoueurs_ = RulesPresident.getNbMaxPlayers();
            nbPlayers = getCompoFactory().newSpinner(minJoueurs_,minJoueurs_,maxJoueurs_,1);
            panel_.add(getCompoFactory().newPlainLabel(messages.getVal(NUMBER_PLAYERS)));
            panel_.add(nbPlayers);
        } else {
            IdList<DealingBelote> repValides_ = new IdList<DealingBelote>(DealingBelote.getRepartitionsValides());
            int minJoueurs_= repValides_.get(0).getId().getNombreJoueurs();
            int maxJoueurs_= repValides_.get(0).getId().getNombreJoueurs();
            for(DealingBelote r: repValides_){
                if(minJoueurs_> r.getId().getNombreJoueurs()){
                    minJoueurs_= r.getId().getNombreJoueurs();
                }
                if(maxJoueurs_< r.getId().getNombreJoueurs()){
                    maxJoueurs_= r.getId().getNombreJoueurs();
                }
            }
            nbPlayers = getCompoFactory().newSpinner(minJoueurs_,minJoueurs_,maxJoueurs_,1);
            panel_.add(getCompoFactory().newPlainLabel(messages.getVal(NUMBER_PLAYERS)));
            panel_.add(nbPlayers);
        }
        AbsPlainLabel ipServer_ = getCompoFactory().newPlainLabel(messages.getVal(IP_SERVER));
        ipServer_.setToolTipText(messages.getVal(IP_SERVER_TOOL_TIP));
        panel_.add(ipServer_);
        panel_.add(ipOrHostName);
        IdList<IpType> list_ = new IdList<IpType>(IpType.values());
        ipType = new ComboBox<IpType>(_fenetre.getFrames().getGeneComboBox().createCombo(_fenetre.getImageFactory(),new StringList(), -1, _fenetre.getCompoFactory()));
        ipType.setWithDefaultValue(false);
        ipType.refresh(list_, messagesIpEnum);
        ipType.setSelectedItem(IpType.HOST_NAME);
//        ipType = new JComboBox<>(IpType.values());
//        ipType.setSelectedItem(IpType.HOST_NAME);
        panel_.add(ipType.self());
        pane_.add(panel_);
        panel_ = _fenetre.getCompoFactory().newLineBox();
        AbsPlainButton button_ = getCompoFactory().newPlainButton(messages.getVal(CREATE_SERVER));
        button_.addActionListener(new CreateServerEvent(this));
        panel_.add(button_);
        button_ = getCompoFactory().newPlainButton(messages.getVal(JOIN_SERVER));
        button_.addActionListener(new JoinServerEvent(this));
        panel_.add(button_);
        button_ = getCompoFactory().newPlainButton(messages.getVal(CANCEL));
        button_.addActionListener(new ClosingDialogEvent(getCardDialog()));
        panel_.add(button_);
        pane_.add(panel_);
        getCardDialog().setContentPane(pane_);
//        getCardDialog().setDefaultCloseOperation(GuiConstants.DISPOSE_ON_CLOSE);
        getCardDialog().pack();
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

    public static String getIpOrHostName(DialogServerCards _dialog) {
        _dialog.getCardDialog().setVisible(true);
        return _dialog.ipOrHostName.getText();
    }

    public static IpType getIpType(DialogServerCards _dialog) {
        return _dialog.ipType.getCurrent();
    }
    public static int getNbPlayers(DialogServerCards _dialog) {
        return _dialog.nbPlayers.getValue();
    }
//    public DealingTarot getReparitionTarot() {
//        return (DealingTarot) repTarot.getSelectedItem();
//    }
//    public DealingBelote getReparitionBelote() {
//        return (DealingBelote) repBelote.getSelectedItem();
//    }
    public static boolean isCreate(DialogServerCards _dialog) {
        return _dialog.create;
    }

    public static boolean isChoosen(DialogServerCards _dialog) {
        return _dialog.create || _dialog.join;
    }

}
