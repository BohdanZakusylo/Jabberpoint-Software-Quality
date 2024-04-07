package com.bohdanvlad.slideItemCreator.factory;

import com.bohdanvlad.slideItemCreator.product.SlideItem;

public interface SlideItemFactory
{
    SlideItem createSlideItem(int level, String s);
}
