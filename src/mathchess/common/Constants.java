/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathchess.common;

/**
 *
 * @author phucdk
 */
public interface Constants {

    public interface PLAYER {

        public static final int PLAYER_1 = 1;
        public static final int PLAYER_2 = 2;
    }

    public interface TABLE {

        public static final int NUMBER_OF_COLUMN = 9;
        public static final int NUMBER_OF_ROW = 11;
        public static final int MIN_ROW = 0;
        public static final int MAX_ROW = 10;
        public static final int MIN_COLUMN = 0;
        public static final int MAX_COLUMN = 8;
    }
    
    public interface PIECE {
        public static final int EMPTY_PIECE = -1;
    }
    
}
