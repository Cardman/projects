package aiki.gui.dialogs;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import aiki.Resources;
import aiki.gui.MainWindow;
import code.gui.AbstractDialogServer;
import code.gui.Dialog;
import code.gui.LabelButton;
import code.gui.Panel;
import code.gui.events.ClosingDialogEvent;
import code.gui.events.CreateServerEvent;
import code.gui.events.JoinServerEvent;
import code.network.ComboBoxIpType;
import code.network.enums.IpType;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.StringMap;

public final class DialogServer extends Dialog implements AbstractDialogServer{
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.dialogserver";

    private static final DialogServer DIALOG = new DialogServer();

    private static final String TITLE = "title";
    private static final String IP_SERVER = "ipServer";
    private static final String IP_SERVER_TOOL_TIP = "ipServerToolTip";
    private static final String CREATE_SERVER = "createServer";
    private static final String JOIN_SERVER = "joinServer";
    private static final String CANCEL = "cancel";
    private JTextField ipOrHostName;
    private ComboBoxIpType ipType;
    private boolean create;
    private boolean join;
    private StringMap<String> messages;

//    private Map<String,String> messagesIp;

    private EnumMap<IpType,String> messagesIpEnum;

    private DialogServer() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setDialogServer(MainWindow _fenetre) {
        DIALOG.init(_fenetre);
    }
    private void init(MainWindow _fenetre) {
        setDialogIcon(_fenetre);
        join = false;
        create = false;
        messages = getMessages(_fenetre,Resources.MESSAGES_FOLDER);
//        messagesIp = FormatHtml.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, Constants.getLanguage(), IpType.class);
        messagesIpEnum = new EnumMap<IpType,String>();
//        for (String i: messagesIp.getKeys()) {
//            messagesIpEnum.put(IpType.valueOf(i), messagesIp.getVal(i));
//        }
        for (IpType i: IpType.values()) {
            messagesIpEnum.put(i, i.toString(_fenetre.getLanguageKey()));
        }
        setLocationRelativeTo(_fenetre);
        setResizable(false);
        setTitle(messages.getVal(TITLE));
        ipOrHostName = new JTextField();
        Panel pane_ = new Panel();
        pane_.setLayout(new GridLayout(0, 1));
        Panel panel_ = new Panel();
        panel_.setLayout(new GridLayout(0, 2));
        JLabel ipServer_ = new JLabel(messages.getVal(IP_SERVER));
        ipServer_.setToolTipText(messages.getVal(IP_SERVER_TOOL_TIP));
        panel_.add(ipServer_);
        panel_.add(ipOrHostName);
        EnumList<IpType> list_ = new EnumList<IpType>(IpType.values());
        ipType = new ComboBoxIpType();
        ipType.setWithDefaultValue(false);
        ipType.refresh(list_, messagesIpEnum);
        ipType.setSelectedItem(IpType.HOST_NAME);
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
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
