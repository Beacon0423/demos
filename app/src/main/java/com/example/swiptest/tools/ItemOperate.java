package com.example.swiptest.tools;

public class ItemOperate {
    public static boolean isSwiped(int fromX, int toX){
        return (fromX - toX) > 70;
    }
}
