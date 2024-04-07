package com.bohdanvlad.slideItemCreator.factory;


import com.bohdanvlad.slideItemCreator.product.SlideItem;
import com.bohdanvlad.slideItemCreator.product.TextItem;

public class TextItemFactory implements SlideItemFactory
{
    @Override
    public SlideItem createSlideItem(int level, String s)
    {
        return new TextItem(level, s);
    }
}
