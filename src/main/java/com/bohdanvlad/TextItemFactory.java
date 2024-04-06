package com.bohdanvlad;


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
}
