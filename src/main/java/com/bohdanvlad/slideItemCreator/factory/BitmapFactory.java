package com.bohdanvlad.slideItemCreator.factory;


import com.bohdanvlad.slideItemCreator.product.BitmapItem;
import com.bohdanvlad.slideItemCreator.product.SlideItem;

public class BitmapFactory implements SlideItemFactory
{
    @Override
    public SlideItem createSlideItem(int level, String s)
    {
        return new BitmapItem(level, s);
    }
}
