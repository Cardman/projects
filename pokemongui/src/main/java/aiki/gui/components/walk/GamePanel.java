package aiki.gui.components.walk;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    /*private static final String BEAT_TRAINER = "beatTrainer";
    private static final String BEAT_GYM_TRAINER = "beatGymTrainer";
    private static final String VISIT_PLACE = "visitPlace";
    private static final String CAUGHT_PK = "caughtPk";

    private static final String NOT_CAUGHT_PK = "notCaughtPk";

    private static final String NICKNAME = "nickname";

    private static final String MONEY = "money";

    private static final String REPEL = "repel";

    private static final String YES = "yes";

    private static final String NO = "no";

    private static final String YES_ALL = "yesAll";

    private static final String NO_ALL = "noAll";

    private FacadeGame facade;

    private JPanel panelTrainers;

    private JPanel panelImportantTrainers;

    private JPanel panelPlaces;

    private JLabel nickname;

    private JPanel panelCaughtPk;
    private JPanel panelPk;

    private JLabel money;

    private JLabel remainingRepelSteps;

    private Map<String,String> messages = new Map<>();

    public GamePanel(FacadeGame _facade) {
        facade = _facade;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        nickname = new JLabel();
        add(nickname);
        panelCaughtPk = new JPanel();
        panelCaughtPk.setLayout(new GridLayout(0, 10));
        add(panelCaughtPk);
        panelPk = new JPanel();
        panelPk.setLayout(new GridLayout(0, 10));
        add(panelPk);
        money = new JLabel();
        add(money);
        remainingRepelSteps = new JLabel();
        add(remainingRepelSteps);
        panelPlaces = new JPanel();
        panelPlaces.setLayout(new GridLayout(0, 1));
        add(panelPlaces);
        panelImportantTrainers = new JPanel();
        panelImportantTrainers.setLayout(new GridLayout(0, 1));
        add(panelImportantTrainers);
        panelTrainers = new JPanel();
        panelTrainers.setLayout(new GridLayout(0, 1));
        add(panelTrainers);
    }

    public void init() {
        messages = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, Constants.getLanguage(), costar);
        panelCaughtPk.removeAll();
        panelPk.removeAll();
        panelCaughtPk.setBorder(BorderFactory.createTitledBorder(messages.getVal(CAUGHT_PK)));
        StringList list_;
        list_ = new StringList();
        for (String p: facade.getPlayer().getCaughtPk().getKeys(true)) {
            list_.add(facade.translatePokemon(p));
        }
        list_.sort();
        for (String p: list_) {
            panelCaughtPk.add(new JLabel(p));
        }
        panelPk.setBorder(BorderFactory.createTitledBorder(messages.getVal(NOT_CAUGHT_PK)));
        list_ = new StringList();
        for (String p: facade.getPlayer().getCaughtPk().getKeys(false)) {
            list_.add(facade.translatePokemon(p));
        }
        list_.sort();
        for (String p: list_) {
            panelPk.add(new JLabel(p));
        }
        String format_;
        format_ = StringList.simpleFormat(messages.getVal(NICKNAME), facade.getPlayer().getNickname());
        nickname.setText(format_);
        format_ = StringList.simpleFormat(messages.getVal(MONEY), facade.getPlayer().getMoney());
        money.setText(format_);
        format_ = StringList.simpleFormat(messages.getVal(REPEL), facade.getPlayer().getRemainingRepelSteps());
        remainingRepelSteps.setText(format_);
        panelPlaces.removeAll();
        panelPlaces.setBorder(BorderFactory.createTitledBorder(messages.getVal(VISIT_PLACE)));
        for (Coords p: facade.getGame().getVisitedPlaces().getKeys()) {
            boolean vis_ = facade.getGame().getVisitedPlaces().getVal(p);
            Place pl_ = facade.getMap().getPlaces().getVal(p.getNumberPlace());
            if (vis_) {
                format_ = StringList.simpleFormat(messages.getVal(YES), pl_.getName());
                panelPlaces.add(new JLabel(format_));
            } else {
                format_ = StringList.simpleFormat(messages.getVal(NO), pl_.getName());
                panelPlaces.add(new JLabel(format_));
            }
        }
        panelImportantTrainers.removeAll();
        panelImportantTrainers.setBorder(BorderFactory.createTitledBorder(messages.getVal(BEAT_TRAINER)));
        for (Coords c: facade.getGame().getBeatGymLeader().getKeys()) {
            boolean b_ = facade.getGame().getBeatGymLeader().getVal(c);
            String name_ = facade.getMap().getTrainerName(c);
            if (b_) {
                format_ = StringList.simpleFormat(messages.getVal(YES), name_);
                panelImportantTrainers.add(new JLabel(format_));
            } else {
                format_ = StringList.simpleFormat(messages.getVal(NO), name_);
                panelImportantTrainers.add(new JLabel(format_));
            }
        }
        panelTrainers.removeAll();
        panelTrainers.setBorder(BorderFactory.createTitledBorder(messages.getVal(BEAT_GYM_TRAINER)));
        TreeMap<Short,List<Point>> map_;
        map_ = new TreeMap<new>(new facade.getGame().getBeatGymTrainer());
        for (short p: map_.getKeys()) {
            Place pl_ = facade.getMap().getPlaces().getVal(p);
            List<Point> all_ = facade.getMap().getBeatGymTrainer().getVal(p);
            if (List.equalsSet(all_, map_.getVal(p))) {
                format_ = StringList.simpleFormat(messages.getVal(YES_ALL), pl_.getName());
                panelTrainers.add(new JLabel(format_));
            } else {
                format_ = StringList.simpleFormat(messages.getVal(NO_ALL), pl_.getName());
                panelTrainers.add(new JLabel(format_));
            }
        }
    }*/
}
