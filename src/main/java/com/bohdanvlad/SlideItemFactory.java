package com.bohdanvlad;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public interface SlideItemFactory
{
    SlideItem createSlideItem();

    SlideItem createSlideItem(int level, String s);

//    Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style);
//
//    void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer);
}
