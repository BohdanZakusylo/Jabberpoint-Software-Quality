package com.bohdanvlad;

import java.awt.*;
import java.awt.image.ImageObserver;

public class TextItemFactory implements SlideItemFactory
{
    @Override
    public SlideItem createSlideItem()
    {
        return new TextItem();
    }

    @Override
    public SlideItem createSlideItem(int level, String s)
    {
        return new TextItem(level, s);
    }

//    @Override
//    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style)
//    {
//        SlideItem textItem = createSlideItem();
//        return textItem.getBoundingBox(g, observer, scale, style);
//    }
//
//    @Override
//    public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer)
//    {
//        SlideItem textItem = createSlideItem();
//        textItem.draw(x, y, scale, g, style, observer);
//    }
}
