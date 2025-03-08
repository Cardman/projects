package aiki.beans.solution;
import aiki.beans.*;
import aiki.beans.facade.comparators.*;
import aiki.beans.facade.solution.dto.*;
import aiki.comparators.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.pokemon.GenderName;
import aiki.map.Solution;
import aiki.map.Step;
import aiki.map.places.Place;
import aiki.map.util.PlaceLevel;
import aiki.util.Coords;
import aiki.util.*;
import code.scripts.confs.PkScriptPages;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.StringUtil;

public final class SolutionBean extends CommonBean implements BeanRenderWithAppName {

    private Solution solution;
    private CustList<StepDto> steps = new CustList<StepDto>();

    public SolutionBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }

    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        setTitledBorder(file().getVal(MessagesDataSolution.M_P_87_TITLE));
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML),MessagesPkBean.SOLUTION,MessagesDataSolution.M_P_87_INDEX);
        int len_ = steps.size();
        for (int i = 0; i < len_; i++) {
            formatMessageDir(Long.toString(i));
            int sub_ = steps.get(i).getPokemon().size();
            for (int j = 0; j < sub_; j++) {
                initLine();
                getBuilder().setIndent(4);
                getBuilder().indent();
                formatMessageDir(getPlace(i,j));
                getBuilder().setIndent(0);
                feedParents();
                new BeanDisplayListGrid<WildPokemonDto>(new BeanDisplayWildPokemonDtoRow()).displayGrid(this,steps.get(i).getPokemon().getValue(j),MessagesPkBean.SOLUTION,"",MessagesDataSolution.M_P_87_IMAGE,MessagesDataSolution.M_P_87_NAME,MessagesDataSolution.M_P_87_GENDER);
                new BeanDisplayList<PlaceTrainerDto>(new BeanDisplayPlaceTrainerDto()).display(this,steps.get(i).getNames());
            }
        }
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML),MessagesPkBean.SOLUTION,MessagesDataSolution.M_P_87_INDEX);
    }
    public StringMap<String> file() {
        return file(MessagesPkBean.SOLUTION).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        if (solution == null) {
            solution = data_.getMap().getSolution();
        }
        steps = new CustList<StepDto>();
        for (Step step_: solution.getSteps()) {
//                TreeMap<Pair<Short,Byte>, List<WildPokemonDto>> treeMap_ = new TreeMap<new>(new NaturalComparator<Pair<Short,Byte>>() {
//                    @Override
//                    public int compare(Pair<Short, Byte> _o1, Pair<Short, Byte> _o2) {
//                        int res_ = _o1.getFirst().compareTo(_o2.getFirst());
//                        if (res_ != 0) {
//                            return res_;
//                        }
//                        return _o1.getSecond().compareTo(_o2.getSecond());
//                    }
//                });
            DictionaryComparatorPlaceLevel<CustList<WildPokemonDto>> treeMap_ = new DictionaryComparatorPlaceLevel<CustList<WildPokemonDto>>();
            for (PlaceLevel key_: step_.getCaughtPokemonPlaceLevel().getKeys()) {
                CustList<WildPokemonDto> pokemon_ = dto(data_, step_, key_);
                pokemon_.sortElts(new ComparatorWildPokemonDto());
                treeMap_.put(key_, pokemon_);
            }
            StepDto s_ = new StepDto(treeMap_);
            CustList<PlaceTrainerDto> places_ = new CustList<PlaceTrainerDto>();
            for (Coords cTrainer_: step_.getImportantsTrainers()) {
                Place pl_ = data_.getMap().getPlace(cTrainer_.getNumberPlace());
//                Level level_ = pl_.getLevelByCoords(cTrainer_);
//                String trainerName_ = DataBase.EMPTY_STRING;
//                if (level_ instanceof LevelIndoorGym) {
//                    LevelIndoorGym lev_ = (LevelIndoorGym) level_;
//                    trainerName_ = lev_.getGymLeader().getName();
//                }
//                if (level_ instanceof LevelLeague) {
//                    League league_ = (League) pl_;
//                    trainerName_ = league_.getRooms().last().getTrainer().getName();
//                }
//                if (level_ instanceof LevelWithWildPokemon) {
//                    trainerName_ = data_.getMap().getTrainerName(cTrainer_);
//                }
                places_.add(new PlaceTrainerDto(pl_.getName(), data_.getMap().getTrainerNameBeat(cTrainer_)));
            }
            s_.getNames().addAllElts(places_);
            steps.add(s_);
        }
    }

    private CustList<WildPokemonDto> dto(DataBase _data, Step _step, PlaceLevel _key) {
        CustList<WildPokemonDto> pokemon_ = new CustList<WildPokemonDto>();
        CustList<GenderName> g_ = PlaceLevelsCustListGenderName.filter(_step.getCaughtPokemonPlaceLevel().getVal(_key));
//                CustList<GenderName> g_ = new CustList<GenderName>();
//                for (GenderName pk_: step_.getCaughtPokemonPlaceLevel().getVal(key_)) {
//                    boolean cont_ = false;
//                    for (GenderName s: g_) {
//                        if (!StringUtil.quickEq(s.getName(),pk_.getName())) {
//                            continue;
//                        }
//                        if (s.getGender() != pk_.getGender()) {
//                            continue;
//                        }
//                        cont_ = true;
//                    }
//                    if (cont_) {
//                        continue;
//                    }
//                    g_.add(pk_);
//                }
        for (GenderName g:g_) {
            String name_ = _data.getTranslatedPokemon().getVal(getLanguage()).getVal(g.getName());
            int[][] image_ = _data.getMiniPk(g.getName());
            String gender_ = _data.getTranslatedGenders().getVal(getLanguage()).getVal(g.getGender());
            pokemon_.add(new WildPokemonDto(image_, name_, gender_));
        }
        return pokemon_;
    }

//    public CustList<PlaceLevel> getPlaces(int _indexStep) {
//        StepDto step_ = steps.get(_indexStep);
//        CustList<PlaceLevel> keys_ = new CustList<PlaceLevel>();
//        keys_.addAllElts(step_.getPokemon().getKeys());
////        keys_.sort(new NaturalComparator<Pair<Short,Byte>>() {
////            @Override
////            public int compare(Pair<Short, Byte> _o1, Pair<Short, Byte> _o2) {
////                int res_ = _o1.getFirst().compareTo(_o2.getFirst());
////                if (res_ != 0) {
////                    return res_;
////                }
////                return _o1.getSecond().compareTo(_o2.getSecond());
////            }
////        });
//        keys_.sortElts(new ComparatorPlaceLevel());
//        return keys_;
//    }
    public String getPlace(int _indexStep, int _indexPlace) {
        StepDto step_ = steps.get(_indexStep);
        CustList<PlaceLevel> keys_ = new CustList<PlaceLevel>();
        keys_.addAllElts(step_.getPokemon().getKeys());
//        keys_.sort(new NaturalComparator<Pair<Short,Byte>>() {
//            @Override
//            public int compare(Pair<Short, Byte> _o1, Pair<Short, Byte> _o2) {
//                int res_ = _o1.getFirst().compareTo(_o2.getFirst());
//                if (res_ != 0) {
//                    return res_;
//                }
//                return _o1.getSecond().compareTo(_o2.getSecond());
//            }
//        });
        keys_.sortElts(new ComparatorPlaceLevel());
        DataBase data_ = getDataBase();
        PlaceLevel key_ = keys_.get(_indexPlace);
        Place place_ = data_.getMap().getPlace(key_.getPlace());
        String name_ = place_.getName();
        if (place_.getLevelsMap().size() == DataBase.ONE_POSSIBLE_CHOICE) {
            return name_;
        }
        return StringUtil.concat(name_, CST_SPACE,Long.toString(key_.getLevel()));
    }
//    public CustList<WildPokemonDto> getPokemonList(int _indexStep, int _indexLevelPlace) {
//        StepDto step_ = steps.get(_indexStep);
//        CustList<PlaceLevel> keys_ = new CustList<PlaceLevel>();
//        keys_.addAllElts(step_.getPokemon().getKeys());
////        keys_.sort(new NaturalComparator<Pair<Short,Byte>>() {
////            @Override
////            public int compare(Pair<Short, Byte> _o1, Pair<Short, Byte> _o2) {
////                int res_ = _o1.getFirst().compareTo(_o2.getFirst());
////                if (res_ != 0) {
////                    return res_;
////                }
////                return _o1.getSecond().compareTo(_o2.getSecond());
////            }
////        });
//        keys_.sortElts(new ComparatorPlaceLevel());
//        return step_.getPokemon().getVal(keys_.get(_indexLevelPlace));
//    }
//    public String getPokemonName(int _indexStep, int _indexLevelPlace, int _indexPokemon) {
//        Step step_ = solution.getSteps().get(_indexStep);
//        CustList<PlaceLevel> keys_ = new CustList<PlaceLevel>();
//        keys_.addAllElts(step_.getCaughtPokemonPlaceLevel().getKeys());
////        keys_.sort(new NaturalComparator<Pair<Short,Byte>>() {
////            @Override
////            public int compare(Pair<Short, Byte> _o1, Pair<Short, Byte> _o2) {
////                int res_ = _o1.getFirst().compareTo(_o2.getFirst());
////                if (res_ != 0) {
////                    return res_;
////                }
////                return _o1.getSecond().compareTo(_o2.getSecond());
////            }
////        });
//        keys_.sortElts(new ComparatorPlaceLevel());
//        return step_.getCaughtPokemonPlaceLevel().getVal(keys_.get(_indexLevelPlace)).get(_indexPokemon).getName();
//    }

    public CustList<StepDto> getSteps() {
        return steps;
    }
}