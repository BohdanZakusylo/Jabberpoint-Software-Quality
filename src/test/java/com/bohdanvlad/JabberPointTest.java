package com.bohdanvlad;

import com.bohdanvlad.fileAccessors.DemoPresentation;
import com.bohdanvlad.fileAccessors.XMLAccessor;
import com.bohdanvlad.presentationComponents.Slide;
import com.bohdanvlad.slideItemCreator.factory.BitmapFactory;
import com.bohdanvlad.slideItemCreator.factory.SlideItemFactory;
import com.bohdanvlad.slideItemCreator.factory.TextItemFactory;
import com.bohdanvlad.slideItemCreator.product.BitmapItem;
import com.bohdanvlad.slideItemCreator.product.SlideItem;
import com.bohdanvlad.style.Style;
import org.junit.jupiter.api.Test;
import com.bohdanvlad.presentationComponents.Presentation;
import org.junit.jupiter.api.BeforeEach;
import java.awt.*;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

class JabberPointTest
{
    private Presentation presentation1;
    private DemoPresentation demoPresentation1;
    private SlideItemFactory bitmapFactory;
    private SlideItemFactory textItemFactory;
    private XMLAccessor xmlAccessor;
    private SlideItem bitmapItem;
    private Slide slide1;
    private Style style1;

    @BeforeEach
    void setup()
    {
        this.presentation1 = new Presentation();
        this.demoPresentation1 = new DemoPresentation();
        this.bitmapFactory = new BitmapFactory();
        this.textItemFactory = new TextItemFactory();
        this.xmlAccessor = new XMLAccessor();
        this.slide1 = new Slide();
        this.bitmapItem = this.bitmapFactory.createSlideItem(1, "some String");
    }


    @Test
    void getSize()
    {
        //the array before initialisation should be empty
        assertEquals(0, this.presentation1.getSize());
    }

    @Test
    void testBitMapItem_exceptionDoesntThrows()
    {
        assertDoesNotThrow(()-> this.bitmapFactory.createSlideItem(1, "String"));
    }
    //Tests XMLAccessor loadFile
    @Test
    void testXMLAccessor_loadFile_fileExists_shouldNotThrow()
    {
        assertDoesNotThrow(()-> this.xmlAccessor.loadFile(this.presentation1, "test.xml"));
    }

    @Test
    void testXMLAccessor_loadFile_fileDoesNotExist_shouldNotThrow_ShouldCatch()
    {

        //Catches FileNotFoundException locally if filename is wrong
        assertDoesNotThrow(()-> this.xmlAccessor.loadFile(this.presentation1, "testFile.xml"));
    }

    @Test
    void testXMLAccessor_loadFile_invalidPresentationInputNull_shouldThrow()
    {
        Presentation presentation = null;
        assertThrows(NullPointerException.class, ()-> this.xmlAccessor.loadFile(presentation, "test.xml"));
    }
    //Tests XMLAccessor saveFile
    @Test
    void testXMLAccessor_saveFile_shouldNotThrow()
    {
        assertDoesNotThrow(()-> this.xmlAccessor.saveFile(this.presentation1, "testSave.xml"));
    }

    @Test
    void testXMLAccessor_saveFile_invalidPresentationInputNull_shouldThrow()
    {
        Presentation presentation = null;
        assertThrows(NullPointerException.class, ()-> this.xmlAccessor.loadFile(presentation, "test.xml"));
    }

    @Test
    void testXMLAccessor_saveFile_invalidFileNameInputNull_shouldThrow()
    {
        assertThrows(NullPointerException.class, ()-> this.xmlAccessor.loadFile(this.presentation1, null));
    }

    @Test
    void testXMLAccessor_saveFile_invalidAllInputNull_shouldThrow()
    {
        Presentation presentation = null;
        assertThrows(NullPointerException.class, ()-> this.xmlAccessor.loadFile(presentation, null));
    }
    //Tests DemoPresentation loadFile
    @Test
    void testDemoPresentation_loadFile_invalidPresentationInputNull_shouldThrow()
    {
        Presentation presentation = null;
        assertThrows(NullPointerException.class, ()-> this.demoPresentation1.loadFile(presentation, "fileName"));
    }

    @Test
    void testDemoPresentation_loadFile_validInput_shouldNotThrow()
    {
        assertDoesNotThrow(()-> this.demoPresentation1.loadFile(this.presentation1, "fileName"));
    }
    //Tests DemoPresentation saveFile
    @Test
    void test_DemoPresentation_saveFile_shouldThrow()
    {
        assertThrows(IllegalStateException.class, ()-> this.demoPresentation1.saveFile(this.presentation1, "unusedFile"));
    }
    //Tests Presentation getSlide
    @Test
    void test_Presentation_getSlide_nonExistingSlide_shouldReturnNull()
    {
        assertEquals(null, this.presentation1.getSlide(10));
    }

    @Test
    void test_Presentation_getSlide_existingSlide_shouldNotNull()
    {
        Presentation pres = new Presentation();
        pres.append(new Slide());
        pres.append(new Slide());
        assertNotEquals(null, pres.getSlide(1));
    }

    @Test
    void test_Presentation_getSlide_existingSlideZero_shouldNotNull()
    {
        Presentation pres = new Presentation();
        pres.append(new Slide());
        pres.append(new Slide());
        assertNotEquals(null, pres.getSlide(0));
    }

    @Test
    void test_Presentation_getSlide_nonExistingSlide_inputEquelsAmountOfSlides_shouldReturnNull()
    {
        Presentation pres = new Presentation();
        pres.append(new Slide());
        pres.append(new Slide());
        assertEquals(null, pres.getSlide(2));
    }

    @Test
    void test_Presentation_getSlide_nonExistingSlide_inputNegative_shouldReturnNull()
    {
        Presentation pres = new Presentation();
        pres.append(new Slide());
        pres.append(new Slide());
        assertEquals(null, pres.getSlide(-1));
    }

    @Test
    void getScale_AssertEquals_NormalRectangleArea()
    {
        Rectangle rectangle1 = new Rectangle(1000, 1000, 2000, 2000);
        assertEquals(this.slide1.getScale(rectangle1), 1.6, 0.1);
    }

    @Test
    void getScale_AssertEquals_AreaIs0()
    {
        Rectangle rectangle1 = new Rectangle(0, 0, 0, 0);
        assertEquals(this.slide1.getScale(rectangle1), 0, 0.1);
    }

    @Test
    void testClear_ArrayListSlideBecomesEmpty ()
    {
        this.presentation1.append(this.slide1);
        this.presentation1.clear();
        assertEquals(0, this.presentation1.getSize());
    }

    @Test
    void testAmountOfPresentation_DemoPresentationAppends_AssertEquals()
    {
        this.demoPresentation1.loadFile(this.presentation1, "unusedString");
        assertEquals(3, this.presentation1.getSize());
    }

    @Test
    void testAmountOfPresentation_DemoPresentationAppends_AssertNotEquals()
    {
        this.demoPresentation1.loadFile(this.presentation1, "unusedString");
        assertNotEquals(1, this.presentation1.getSize());
    }

    @Test
    void testAmountOfPresentation_DemoPresentationAppendsAddOne_AssertEquals()
    {
        this.demoPresentation1.loadFile(this.presentation1, "unusedString");
        this.presentation1.append(this.slide1);
        assertEquals(4, this.presentation1.getSize());
    }
}
