package com.bohdanvlad;

import java.awt.*;
import java.awt.image.ImageObserver;

public class BitmapFactory implements SlideItemFactory
{
    @Override
    public SlideItem createSlideItem()
    {
        return new BitmapItem();
    }

    @Override
    public SlideItem createSlideItem(int level, String s)
    {
        return new BitmapItem(level, s);
    }

//    @Override
//    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style)
//    {
//        SlideItem bitmapItem = createSlideItem();
//        return bitmapItem.getBoundingBox(g, observer, scale, style);
//    }
//
//    @Override
//    public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer)
//    {
//        SlideItem bitmapItem = createSlideItem();
//        bitmapItem.draw(x, y, scale, g, style, observer);
//    }
}
