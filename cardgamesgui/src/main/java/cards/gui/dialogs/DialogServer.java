package cards.gui.dialogs;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.WindowConstants;

import cards.belote.enumerations.DealingBelote;
import cards.facade.enumerations.GameEnum;
import cards.gui.MainWindow;
import cards.president.RulesPresident;
import cards.tarot.enumerations.DealingTarot;
import code.gui.AbstractDialogServer;
import code.gui.LabelButton;
import code.gui.Panel;
import code.gui.events.ClosingDialogEvent;
import code.gui.events.CreateServerEvent;
import code.gui.events.JoinServerEvent;
import code.network.ComboBoxIpType;
import code.network.enums.IpType;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.Ints;
import code.util.StringMap;

public final class DialogServer extends DialogCards implements AbstractDialogServer{
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogserver";

    private static final DialogServer DIALOG = new DialogServer();
    private static final String CANCEL = "cancel";
    private static final String CREATE_SERVER = "createServer";
    private static final String IP_SERVER = "ipServer";
    private static final String IP_SERVER_TOOL_TIP = "ipServerToolTip";
    private static final String JOIN_SERVER = "joinServer";
    private static final String NUMBER_PLAYERS = "numberPlayers";
    private static final String TITLE = "title";
    private JTextField ipOrHostName;
    private ComboBoxIpType ipType;
    private boolean create;
    private boolean join;
    private JSpinner nbPlayers;
    private StringMap<String> messages;
    private EnumMap<IpType,String> messagesIpEnum;
//    private JComboBox<DealingBelote> repBelote;
//    private JComboBox<DealingTarot> repTarot;

    private DialogServer() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setDialogServer(MainWindow _fenetre, GameEnum _game) {
        DIALOG.initDialogServer(_fenetre, _game);
    }

    private void initDialogServer(MainWindow _fenetre, GameEnum _game) {
        setDialogIcon(_fenetre);
        create = false;
        join = false;
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
        messages = getMessages(_fenetre,FileConst.FOLDER_MESSAGES_GUI);
        messagesIpEnum = new EnumMap<IpType,String>();
        for (IpType i: IpType.values()) {
            messagesIpEnum.put(i, i.toString(_fenetre.getLanguageKey()));
        }
        setTitle(messages.getVal(TITLE));
        setLocationRelativeTo(_fenetre);
        ipOrHostName = new JTextField();
        Panel pane_ = new Panel();
        pane_.setLayout(new GridLayout(0, 1));
        Panel panel_ = new Panel();
        panel_.setLayout(new GridLayout(0, 2));
        if (_game == GameEnum.TAROT) {
            Ints nombreJoueursPossible_=new Ints();
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
                if(!nombreJoueursPossible_.containsObj(r.getNombreJoueurs())){
                    nombreJoueursPossible_.add(r.getNombreJoueurs());
                }
            }
            nombreJoueursPossible_.sort();
            nbPlayers = new JSpinner(new SpinnerListModel(nombreJoueursPossible_.toArray()));
            panel_.add(new JLabel(messages.getVal(NUMBER_PLAYERS)));
            panel_.add(nbPlayers);
        } else if (_game == GameEnum.PRESIDENT) {
            Ints nombreJoueursPossible_=new Ints();
            int minJoueurs_ = RulesPresident.getNbMinPlayers();
            int maxJoueurs_ = RulesPresident.getNbMaxPlayers();
            for (int i = minJoueurs_; i <= maxJoueurs_; i++) {
                nombreJoueursPossible_.add(i);
            }
            nbPlayers = new JSpinner(new SpinnerListModel(nombreJoueursPossible_.toArray()));
            panel_.add(new JLabel(messages.getVal(NUMBER_PLAYERS)));
            panel_.add(nbPlayers);
        } else {
            Ints nombreJoueursPossible_=new Ints();
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
                if(!nombreJoueursPossible_.containsObj(r.getNombreJoueurs())){
                    nombreJoueursPossible_.add(r.getNombreJoueurs());
                }
            }
            nombreJoueursPossible_.sort();
            nbPlayers = new JSpinner(new SpinnerListModel(nombreJoueursPossible_.toArray()));
            panel_.add(new JLabel(messages.getVal(NUMBER_PLAYERS)));
            panel_.add(nbPlayers);
        }
        JLabel ipServer_ = new JLabel(messages.getVal(IP_SERVER));
        ipServer_.setToolTipText(messages.getVal(IP_SERVER_TOOL_TIP));
        panel_.add(ipServer_);
        panel_.add(ipOrHostName);
        EnumList<IpType> list_ = new EnumList<IpType>(IpType.values());
        ipType = new ComboBoxIpType();
        ipType.setWithDefaultValue(false);
        ipType.refresh(list_, messagesIpEnum);
        ipType.setSelectedItem(IpType.HOST_NAME);
//        ipType = new JComboBox<>(IpType.values());
//        ipType.setSelectedItem(IpType.HOST_NAME);
        panel_.add(ipType);
        pane_.add(panel_);
        panel_ = new Panel();
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

    public static String getIpOrHostName() {
        DIALOG.setVisible(true);
        return DIALOG.ipOrHostName.getText();
    }

    public static IpType getIpType() {
        return DIALOG.ipType.getCurrent();
    }
    public static int getNbPlayers() {
        return (Integer)DIALOG.nbPlayers.getValue();
    }
//    public DealingTarot getReparitionTarot() {
//        return (DealingTarot) repTarot.getSelectedItem();
//    }
//    public DealingBelote getReparitionBelote() {
//        return (DealingBelote) repBelote.getSelectedItem();
//    }
    public static boolean isCreate() {
        return DIALOG.create;
    }

    public static boolean isJoin() {
        return DIALOG.join;
    }

    public static boolean isChoosen() {
        return DIALOG.create || DIALOG.join;
    }
}
