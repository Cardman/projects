package aiki.sml;


import aiki.facade.SexListInt;
import code.util.StringList;

public interface IntConfPkStream {

    LoadedGameConf load(String _tmpFolder,StringList _args, SexListInt _sexListInt);
    LoadingGame save(String _file, LoadingGame _conf);
}
