package com.bohdanvlad;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public interface SlideItemFactory
{
    SlideItem createSlideItem();

    SlideItem createSlideItem(int level, String s);
}
