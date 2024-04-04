package com.bohdanvlad;


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
}
