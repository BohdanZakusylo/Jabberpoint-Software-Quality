package com.bohdanvlad;

import com.bohdanvlad.fileAccessors.DemoPresentation;
import com.bohdanvlad.slideItemCreator.factory.BitmapFactory;
import com.bohdanvlad.slideItemCreator.factory.SlideItemFactory;
import com.bohdanvlad.slideItemCreator.factory.TextItemFactory;
import org.junit.jupiter.api.Test;
import com.bohdanvlad.presentationComponents.Presentation;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class JabberPointTest
{
    private Presentation presentation1;
    private DemoPresentation demoPresentation1;
    private SlideItemFactory bitmapFactory;
    private SlideItemFactory textItemFactory;
    @BeforeEach
    void setup()
    {
        this.presentation1 = new Presentation();
        this.demoPresentation1 = new DemoPresentation();
        this.bitmapFactory = new BitmapFactory();
        this.textItemFactory = new TextItemFactory();
    }


    @Test
    void getSize()
    {
        //the array before initialisation should be empty
        assertEquals(0, this.presentation1.getSize());
    }

    @Test
    void test_DemoPresentationThrows_Error()
    {
        assertThrows(IllegalStateException.class, ()-> this.demoPresentation1.saveFile(this.presentation1, "unusedFile"));
    }

    @Test
    void testBitMapItem_exceptionDoesntThrows()
    {
        assertDoesNotThrow(()-> this.bitmapFactory.createSlideItem(1, "String"));
    }
}
