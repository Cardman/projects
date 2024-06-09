package code.network;

import cards.belote.enumerations.DealingBelote;
import cards.facade.enumerations.GameEnum;
import cards.gui.containers.ContainerMultiBelote;
import cards.gui.containers.ContainerMultiPresident;
import cards.gui.containers.ContainerMultiTarot;
import cards.network.threads.Net;
import cards.president.RulesPresident;
import cards.tarot.enumerations.DealingTarot;
import code.gui.*;
import code.gui.events.CancelServerEvent;
import code.gui.events.CreateServerEvent;
import code.gui.events.JoinServerEvent;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.AbstractServerSocket;
import code.gui.initialize.AbstractSocket;
import code.netw.MessagesNetWork;
import code.netw.NetWork;
import code.network.enums.ErrorHostConnectionType;
import code.network.enums.IpType;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class DialogServerContent implements AbstractDialogServer {

    private static final String LOCALHOST_OLD_IP = "127.0.0.1";

    private static final String LOCALHOST_NEW_IP = "::1";

    private final AbstractProgramInfos frames;
    private final AbsCompoFactory compoFactory;
    private final AbsPanel component;
    private final AbsPlainLabel errors;
    private AbsTextField ipOrHostName;
    private ComboBox<IpType> ipType;
//    private boolean create;
//    private boolean join;
    private AbsSpinner nbPlayers;
    private GameEnum chosen = GameEnum.NONE;
    private AbsSpinner portChoice;
    private final WindowNetWork window;
    private AbstractServerSocket serverSocket;
    private ConnectionToServer connection;

    private AbstractScheduledExecutorService server;
    private AbstractFuture task;
//    private final CustList<AbsCustComponent> components = new CustList<AbsCustComponent>();
    private AbsButton createServer;
    private AbsButton joinServer;
    //    private JComboBox<DealingBelote> repBelote;
//    private JComboBox<DealingTarot> repTarot;

    public DialogServerContent(WindowNetWork _fenetre,AbstractProgramInfos _frameFactory) {
        window = _fenetre;
        frames = _frameFactory;
        compoFactory = _frameFactory.getCompoFactory();
        component = compoFactory.newGrid(0, 1);
        errors = compoFactory.newPlainLabel("");
//        getCardDialog().setAccessFile(DIALOG_ACCESS);
    }

    public static void setDialogServer(WindowNetWork _fenetre, GameEnum _game, int _port) {
        _fenetre.getDialogServerContent().initDialogServer(_fenetre, _game,_port);
    }

    private void initDialogServer(WindowNetWork _fenetre, GameEnum _game, int _port) {
        component.setVisible(true);
        component.removeAll();
//        getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
//        create = false;
//        join = false;
        StringMap<String> mapping_ = NetWork.getMessages(NetWork.getAppliTr(frames.currentLg())).getMapping();
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
//        messages = WindowNetWork.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _fenetre.getLanguageKey(), getCardDialog().getAccessFile());
        //    private StringMap<String> messages;
        IdMap<IpType, String> messagesIpEnum_ = new IdMap<IpType, String>();
        messagesIpEnum_.addEntry(IpType.HOST_NAME, mapping_.getVal(MessagesNetWork.HOST_NAME));
        messagesIpEnum_.addEntry(IpType.IP_V4, mapping_.getVal(MessagesNetWork.IP_V4));
        messagesIpEnum_.addEntry(IpType.IP_V6, mapping_.getVal(MessagesNetWork.IP_V6));
//        for (IpType i: IpType.values()) {
//            messagesIpEnum.put(i, i.toString(_fenetre.getLanguageKey()));
//        }
        component.setTitledBorder(mapping_.getVal(MessagesNetWork.TITLE));
//        getCardDialog().setTitle(messages.getVal(TITLE));
//        getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        ipOrHostName = getCompoFactory().newTextField();
//        AbsPanel pane_ = _fenetre.getCompoFactory().newGrid(0, 1);
        AbsPanel panel_ = _fenetre.getCompoFactory().newGrid(0, 2);
        chosen = _game;
        if (_game == GameEnum.TAROT) {
            window.setCards(true);
            IdList<DealingTarot> repValides_ = new IdList<DealingTarot>(DealingTarot.getRepartitionsValides());
            int minJoueurs_= repValides_.get(0).getId().getNombreJoueurs();
            int maxJoueurs_= repValides_.get(0).getId().getNombreJoueurs();
            for(DealingTarot r: repValides_){
                minJoueurs_ = NumberUtil.min(minJoueurs_,r.getId().getNombreJoueurs());
                maxJoueurs_ = NumberUtil.max(maxJoueurs_,r.getId().getNombreJoueurs());
//                if(minJoueurs_> r.getId().getNombreJoueurs()){
//                    minJoueurs_= r.getId().getNombreJoueurs();
//                }
//                if(maxJoueurs_< r.getId().getNombreJoueurs()){
//                    maxJoueurs_= r.getId().getNombreJoueurs();
//                }
            }
            nbPlayers = getCompoFactory().newSpinner(minJoueurs_,minJoueurs_,maxJoueurs_,1);
            panel_.add(getCompoFactory().newPlainLabel(mapping_.getVal(MessagesNetWork.NUMBER_PLAYERS)));
            panel_.add(nbPlayers);
        } else if (_game == GameEnum.PRESIDENT) {
            window.setCards(true);
            int minJoueurs_ = RulesPresident.getNbMinPlayers();
            int maxJoueurs_ = RulesPresident.getNbMaxPlayers();
            nbPlayers = getCompoFactory().newSpinner(minJoueurs_,minJoueurs_,maxJoueurs_,1);
            panel_.add(getCompoFactory().newPlainLabel(mapping_.getVal(MessagesNetWork.NUMBER_PLAYERS)));
            panel_.add(nbPlayers);
        } else if (_game == GameEnum.BELOTE) {
            window.setCards(true);
            IdList<DealingBelote> repValides_ = new IdList<DealingBelote>(DealingBelote.getRepartitionsValides());
            int minJoueurs_= repValides_.get(0).getId().getNombreJoueurs();
            int maxJoueurs_= repValides_.get(0).getId().getNombreJoueurs();
            for(DealingBelote r: repValides_){
                minJoueurs_ = NumberUtil.min(minJoueurs_,r.getId().getNombreJoueurs());
                maxJoueurs_ = NumberUtil.max(maxJoueurs_,r.getId().getNombreJoueurs());
//                if(minJoueurs_> r.getId().getNombreJoueurs()){
//                    minJoueurs_= r.getId().getNombreJoueurs();
//                }
//                if(maxJoueurs_< r.getId().getNombreJoueurs()){
//                    maxJoueurs_= r.getId().getNombreJoueurs();
//                }
            }
            nbPlayers = getCompoFactory().newSpinner(minJoueurs_,minJoueurs_,maxJoueurs_,1);
            panel_.add(getCompoFactory().newPlainLabel(mapping_.getVal(MessagesNetWork.NUMBER_PLAYERS)));
            panel_.add(nbPlayers);
        } else {
            window.setCards(false);
        }
        AbsPlainLabel ipServer_ = getCompoFactory().newPlainLabel(mapping_.getVal(MessagesNetWork.IP_SERVER));
        ipServer_.setToolTipText(mapping_.getVal(MessagesNetWork.IP_SERVER_TOOL_TIP));
        panel_.add(ipServer_);
        panel_.add(ipOrHostName);
        portChoice = getCompoFactory().newSpinner(_port,0,65535,1);
        panel_.add(portChoice);
        IdList<IpType> list_ = new IdList<IpType>(messagesIpEnum_.getKeys());
        ipType = new ComboBox<IpType>(GuiBaseUtil.combo(_fenetre.getImageFactory(),new StringList(), -1, _fenetre.getCompoFactory()));
        ipType.refresh(list_, messagesIpEnum_);
        ipType.setSelectedItem(IpType.HOST_NAME);
//        ipType = new JComboBox<>(IpType.values());
//        ipType.setSelectedItem(IpType.HOST_NAME);
        panel_.add(ipType.self());
        component.add(panel_);
        panel_ = _fenetre.getCompoFactory().newLineBox();
        createServer = getCompoFactory().newPlainButton(mapping_.getVal(MessagesNetWork.CREATE_SERVER));
        AbsButton button_ = createServer;
        button_.addActionListener(new CreateServerEvent(this));
        panel_.add(button_);
        joinServer = getCompoFactory().newPlainButton(mapping_.getVal(MessagesNetWork.JOIN_SERVER));
        button_ = joinServer;
        button_.addActionListener(new JoinServerEvent(this));
        panel_.add(button_);
        button_ = getCompoFactory().newPlainButton(mapping_.getVal(MessagesNetWork.CANCEL));
        button_.addActionListener(new CancelServerEvent(this));
        panel_.add(button_);
        component.add(panel_);
        component.add(errors);
//        components.clear();
//        int c_ = _fenetre.getCommonFrame().getPane().getComponentCount();
//        for (int i = 0; i < c_; i++) {
//            components.add( _fenetre.getCommonFrame().getPane().getComponent(i));
//        }
        _fenetre.getCommonFrame().getPane().removeAll();
        _fenetre.getCommonFrame().setContentPane(component);
        _fenetre.pack();
//        getCardDialog().setContentPane(pane_);
//        getCardDialog().setDefaultCloseOperation(GuiConstants.DISPOSE_ON_CLOSE);
//        getCardDialog().pack();
    }

    @Override
    public void createServerChoice() {
//        join = false;
//        create = true;
        String ip_ = NetCreate.getHostAddress(frames.getSocketFactory(),ipType.getCurrent(), adjust(ipOrHostName.getText()));
        AbstractServerSocket serverSocket_ = frames.getSocketFactory().newServerSocket(ip_, portChoice.getValue());
        if (!serverSocket_.isOk()) {
            StringMap<String> mapping_ = NetWork.getMessages(NetWork.getAppliTr(frames.currentLg())).getMapping();
            String message_ = mapping_.getVal(MessagesNetWork.USED_PORT);
            message_ = StringUtil.simpleNumberFormat(message_, portChoice.getValue());
            errors.setText(message_);
            return;
        }
        if (chosen == GameEnum.TAROT) {
            window.getNetg().setContainerGame(new ContainerMultiTarot(window, true, nbPlayers.getValue()));
        } else if (chosen == GameEnum.PRESIDENT) {
            window.getNetg().setContainerGame(new ContainerMultiPresident(window, true, nbPlayers.getValue()));
        } else if (chosen == GameEnum.BELOTE) {
            window.getNetg().setContainerGame(new ContainerMultiBelote(window, true, nbPlayers.getValue()));
        }
        if (chosen != GameEnum.NONE) {
            Net.setNbPlayers(nbPlayers.getValue(), window.getNet());
        }
        serverSocket = serverSocket_;
        closeWindow();
        server = getFrames().getThreadFactory().newScheduledExecutorService();
        connection = new ConnectionToServer(serverSocket_, window, ip_, portChoice.getValue());
        task = server.scheduleAtFixedRateNanos(connection,0,1);
    }

    public AbstractServerSocket getServerSocket() {
        return serverSocket;
    }

    @Override
    public void joinServerChoice() {
//        join = true;
//        create = false;
        closeWindow();
        String ip_ = adjust(ipOrHostName.getText());
        SocketResults connected_ = window.createClient(ip_, ipType.getCurrent(), false, portChoice.getValue());
        if (connected_.getError() != ErrorHostConnectionType.NOTHING) {
            component.setVisible(true);
            window.pack();
            if (chosen != GameEnum.NONE) {
                window.getNetg().setContainerGame(window.noGame());
            }
            StringMap<String> mapping_ = NetWork.getMessages(NetWork.getAppliTr(getFrames().currentLg())).getMapping();
            if (connected_.getError() == ErrorHostConnectionType.UNKNOWN_HOST) {
                String formatted_ = mapping_.getVal(MessagesNetWork.UNKNOWN_HOST);
                formatted_ = StringUtil.simpleStringsFormat(formatted_, ip_);
                errors.setText(formatted_);
            } else {
                errors.setText(mapping_.getVal(MessagesNetWork.NOT_CONNECTED));
            }
            return;
        }
        if (chosen == GameEnum.TAROT) {
            window.getNetg().setContainerGame(new ContainerMultiTarot(window, false, nbPlayers.getValue()));
        } else if (chosen == GameEnum.PRESIDENT) {
            window.getNetg().setContainerGame(new ContainerMultiPresident(window, false, nbPlayers.getValue()));
        } else if (chosen == GameEnum.BELOTE) {
            window.getNetg().setContainerGame(new ContainerMultiBelote(window, false, nbPlayers.getValue()));
        }
        if (chosen == GameEnum.NONE) {
            window.setIndexInGame(IndexConstants.SECOND_INDEX);
        }
    }
    private String adjust(String _i) {
        String ip_ = StringUtil.nullToEmpty(_i);
        if (ip_.isEmpty()) {
            if (ipType.getCurrent() == IpType.IP_V6) {
                ip_ = LOCALHOST_NEW_IP;
            } else {
                ip_ = LOCALHOST_OLD_IP;
            }
        }
        return ip_;
    }
    public void restore() {
        if (window.isCards()) {
            window.menuMultiGames();
        } else {
            window.exitFromTrading();
            window.pack();
        }
//        window.getCommonFrame().getPane().removeAll();
//        for (AbsCustComponent c: components) {
//            window.getCommonFrame().getPane().add(c);
//        }
//        window.pack();
    }
    public void closeConnexion(Exiting _exit, AbstractSocket _socket) {
        _socket.close();
        if (connection == null || !_exit.isServer()) {
            return;
        }
        connection.fermer();
        task.cancel(false);
        server.shutdown();
    }
//    public static String getIpOrHostName(DialogServerContent _dialog) {
//        return _dialog.ipOrHostName.getText();
//    }

    public void closeWindow() {
        component.setVisible(false);
        window.pack();
//        cardDialog.closeWindow();
//        cardDialog.getPane().removeAll();
    }
    public AbstractProgramInfos getFrames() {
        return frames;
    }

    public AbsCompoFactory getCompoFactory() {
        return compoFactory;
    }

    public ComboBox<IpType> getIpType() {
        return ipType;
    }

    public AbsTextField getIpOrHostName() {
        return ipOrHostName;
    }

    public AbsSpinner getNbPlayers() {
        return nbPlayers;
    }

    public static IpType getIpType(DialogServerContent _dialog) {
        return _dialog.ipType.getCurrent();
    }
    public static int getNbPlayers(DialogServerContent _dialog) {
        return _dialog.nbPlayers.getValue();
    }
    //    public DealingTarot getReparitionTarot() {
//        return (DealingTarot) repTarot.getSelectedItem();
//    }
//    public DealingBelote getReparitionBelote() {
//        return (DealingBelote) repBelote.getSelectedItem();
//    }
//    public static boolean isCreate(DialogServerContent _dialog) {
//        return _dialog.create;
//    }

    public ConnectionToServer getConnection() {
        return connection;
    }

    public AbsButton getCreateServer() {
        return createServer;
    }

    public AbsButton getJoinServer() {
        return joinServer;
    }
    //    public static boolean isChoosen(DialogServerContent _dialog) {
//        return _dialog.create || _dialog.join;
//    }
}
