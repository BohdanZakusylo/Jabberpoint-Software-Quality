package com.bohdanvlad;

import com.bohdanvlad.fileAccessors.DemoPresentation;
import com.bohdanvlad.fileAccessors.XMLAccessor;
import com.bohdanvlad.presentationComponents.Slide;
import com.bohdanvlad.slideItemCreator.factory.BitmapFactory;
import com.bohdanvlad.slideItemCreator.factory.SlideItemFactory;
import com.bohdanvlad.slideItemCreator.factory.TextItemFactory;
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
    @BeforeEach
    void setup()
    {
        this.presentation1 = new Presentation();
        this.demoPresentation1 = new DemoPresentation();
        this.bitmapFactory = new BitmapFactory();
        this.textItemFactory = new TextItemFactory();
        this.xmlAccessor = new XMLAccessor();
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

    @Test//TODO:check if ok
    void testXMLAccessor_loadFile_invalidInputBoth_shouldNotThrow_ShouldCatch()
    {
        //Catches FileNotFoundException locally if filename is wrong and stops
        Presentation presentation = null;
        assertDoesNotThrow(()-> this.xmlAccessor.loadFile(presentation, "testWrong"));
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
}
