package com.example.tsmpc47.kamus.data.local.db;

import android.provider.BaseColumns;

/**
 * Created by irmansyah on 06/03/18.
 */

public class DatabaseContract {

    static String TABLE_NAME_ENG_IND = "kamus_engind";
    static String TABLE_NAME_IND_ENG = "kamus_indeng";

    static final class KamusColumnsEngInd implements BaseColumns {

        static String SEARCH_WORD_ENG_IND = "search_word_engind";

        static String RESULT_WORD_ENG_IND = "result_word_engind";
    }

    static final class KamusColumnsIndEng implements BaseColumns {

        static String SEARCH_WORD_IND_ENG = "search_word_indeng";

        static String RESULT_WORD_IND_ENG = "result_word_indeng";
    }
}
