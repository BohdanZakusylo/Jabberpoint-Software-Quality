package com.bohdanvlad;

import com.bohdanvlad.fileAccessors.DemoPresentation;
import com.bohdanvlad.fileAccessors.XMLAccessor;
import com.bohdanvlad.presentationComponents.Slide;
import com.bohdanvlad.presentationComponents.SlideViewerComponent;
import com.bohdanvlad.slideItemCreator.factory.BitmapFactory;
import com.bohdanvlad.slideItemCreator.factory.SlideItemFactory;
import com.bohdanvlad.slideItemCreator.factory.TextItemFactory;
import com.bohdanvlad.slideItemCreator.product.BitmapItem;
import com.bohdanvlad.slideItemCreator.product.SlideItem;
import com.bohdanvlad.slideItemCreator.product.TextItem;
import com.bohdanvlad.style.Style;
import org.junit.jupiter.api.Test;
import com.bohdanvlad.presentationComponents.Presentation;
import org.junit.jupiter.api.BeforeEach;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
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
    private SlideViewerComponent slideViewerComponent;
    private JFrame frame;
    private BufferedImage image;
    private Graphics graphics;

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
        this.style1 = new Style(1, Color.blue, 12, 10);
        this.frame = new JFrame();
        this.slideViewerComponent = new SlideViewerComponent(this.presentation1, frame);
        this.image = new BufferedImage(Slide.WIDTH, Slide.HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.graphics = image.getGraphics();
    }


    @Test
    void getSize()
    {
        //the array before initialisation should be empty
        assertEquals(0, this.presentation1.getSize());
    }

    @Test
    void testInitialPresentation_CurrentSlideShouldBeNull_AssertNull()
    {
        assertNull(this.presentation1.getCurrentSlide());
    }

    @Test
    void testInitialPresentation_CurrentSlideNumberShouldBeMinusOne_AssertEquals()
    {
        assertEquals(-1, this.presentation1.getSlideNumber());
    }

    @Test
    void testBitMapItem_exceptionDoesntThrows()
    {
        assertDoesNotThrow(() -> this.bitmapFactory.createSlideItem(1, "String"));
    }

    //Tests XMLAccessor loadFile
    @Test
    void testXMLAccessor_loadFile_fileExists_shouldNotThrow()
    {
        assertDoesNotThrow(() -> this.xmlAccessor.loadFile(this.presentation1, "test.xml"));
    }

    @Test
    void testXMLAccessor_loadFile_fileDoesNotExist_shouldNotThrow_ShouldCatch()
    {

        //Catches FileNotFoundException locally if filename is wrong
        assertDoesNotThrow(() -> this.xmlAccessor.loadFile(this.presentation1, "testFile.xml"));
    }

    @Test
    void testXMLAccessor_loadFile_invalidPresentationInputNull_shouldThrow()
    {
        Presentation presentation = null;
        assertThrows(NullPointerException.class, () -> this.xmlAccessor.loadFile(presentation, "test.xml"));
    }

    //Tests XMLAccessor saveFile
    @Test
    void testXMLAccessor_saveFile_shouldNotThrow()
    {
        assertDoesNotThrow(() -> this.xmlAccessor.saveFile(this.presentation1, "testSave.xml"));
    }

    @Test
    void testXMLAccessor_saveFile_invalidPresentationInputNull_shouldThrow()
    {
        Presentation presentation = null;
        assertThrows(NullPointerException.class, () -> this.xmlAccessor.loadFile(presentation, "test.xml"));
    }

    @Test
    void testXMLAccessor_saveFile_invalidFileNameInputNull_shouldThrow()
    {
        assertThrows(NullPointerException.class, () -> this.xmlAccessor.loadFile(this.presentation1, null));
    }

    @Test
    void testXMLAccessor_saveFile_invalidAllInputNull_shouldThrow()
    {
        Presentation presentation = null;
        assertThrows(NullPointerException.class, () -> this.xmlAccessor.loadFile(presentation, null));
    }

    //Tests DemoPresentation loadFile
    @Test
    void testDemoPresentation_loadFile_invalidPresentationInputNull_shouldThrow()
    {
        Presentation presentation = null;
        assertThrows(NullPointerException.class, () -> this.demoPresentation1.loadFile(presentation, "fileName"));
    }

    @Test
    void testDemoPresentation_loadFile_validInput_shouldNotThrow()
    {
        assertDoesNotThrow(() -> this.demoPresentation1.loadFile(this.presentation1, "fileName"));
    }

    //Tests DemoPresentation saveFile
    @Test
    void test_DemoPresentation_saveFile_shouldThrow()
    {
        assertThrows(IllegalStateException.class, () -> this.demoPresentation1.saveFile(this.presentation1, "unusedFile"));
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
    void testClear_ArrayListSlideBecomesEmpty()
    {
        this.presentation1.append(this.slide1);
        this.presentation1.clear();
        assertEquals(0, this.presentation1.getSize());
    }

    @Test
    void testPresentationClear_SlideNumberShouldBeMinus1_AssertEquals()
    {
        Slide slide2 = new Slide();
        this.presentation1.append(this.slide1);
        this.presentation1.append(slide2);

        this.presentation1.setSlideNumber(0);
        //doing it for 3 times to make sure
        this.presentation1.clear();

        assertEquals(-1, this.presentation1.getSlideNumber());
    }

    @Test
    void testAmountOfPresentation_DemoPresentationAppends_AssertEquals()
    {
        this.demoPresentation1.loadFile(this.presentation1, "unusedString");
        assertEquals(3, this.presentation1.getSize());
    }

    @Test
    void testPresentationPrevSlide_SLideNumber0_AssertEquals()
    {
        Slide slide2 = new Slide();
        this.presentation1.append(this.slide1);
        this.presentation1.append(slide2);

        this.presentation1.setSlideNumber(1);
        this.presentation1.prevSlide();

        assertEquals(0, this.presentation1.getSlideNumber());
    }

    @Test
    void testPresentationPrevSlideThreeTimes_SLideShouldNotGoBellow0_AssertEquals()
    {
        Slide slide2 = new Slide();
        this.presentation1.append(this.slide1);
        this.presentation1.append(slide2);

        this.presentation1.setSlideNumber(1);
        //doing it for 3 times to make sure
        this.presentation1.prevSlide();
        this.presentation1.prevSlide();
        this.presentation1.prevSlide();

        assertEquals(0, this.presentation1.getSlideNumber());
    }

    @Test
    void testPresentationNextSlide_SLideShouldBe1_AssertEquals()
    {
        Slide slide2 = new Slide();
        this.presentation1.append(this.slide1);
        this.presentation1.append(slide2);

        this.presentation1.setSlideNumber(0);
        this.presentation1.nextSlide();

        assertEquals(1, this.presentation1.getSlideNumber());
    }

    @Test
    void testPresentationNextSlideThreeTimes_SLideShouldNotBeBigger1_AssertEquals()
    {
        Slide slide2 = new Slide();
        this.presentation1.append(this.slide1);
        this.presentation1.append(slide2);

        this.presentation1.setSlideNumber(0);
        //doing it for 3 times to make sure
        this.presentation1.nextSlide();
        this.presentation1.nextSlide();
        this.presentation1.nextSlide();

        assertEquals(1, this.presentation1.getSlideNumber());
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

    @Test
    void testGetFontByScaleAndFont_AssertEquals()
    {
        float randomScale = 1.5f;
        assertEquals(18, this.style1.getFont(randomScale).getSize());
    }

    @Test
    void testGetFontByScaleAndFont_RandomNumber_AssertNotEquals()
    {
        float randomScale = 1.5f;
        assertNotEquals(1, this.style1.getFont(randomScale).getSize());
    }

    @Test
    void testStyletoString_AssertEquals()
    {
        assertEquals("[1,java.awt.Color[r=0,g=0,b=255]; 12 on 10]", this.style1.toString());
    }

    @Test
    void testStyletoString_RandomString_AssertNotEquals()
    {
        assertNotEquals("random string", this.style1.toString());
    }

    @Test
    void testBitmapItemtoString_ActualString_AssertEquals()
    {
        assertEquals("BitmapItem[1,some String]", this.bitmapItem.toString());
    }

    @Test
    void testBitmapItemtoString_RandomString_AssertNotEquals()
    {
        assertNotEquals("random string", this.bitmapItem.toString());
    }

    //testing factory methods with instance of
    @Test
    void testBitmapFactory_CreatesTheCorrectType_AssertTrue()
    {
        SlideItem bitmapItem = this.bitmapFactory.createSlideItem(1, "String");
        assertTrue(bitmapItem instanceof BitmapItem);
    }

    @Test
    void testBitmapFactory_CreateIncorrectTypeTextItem_AssertFalse()
    {
        SlideItem bitmapItem = this.bitmapFactory.createSlideItem(1, "String");
        assertFalse(bitmapItem instanceof TextItem);
    }

    @Test
    void testTextItemFactory_CreateCorrectType_AssertTrue()
    {
        SlideItem textItem = this.textItemFactory.createSlideItem(1, "String");
        assertTrue(textItem instanceof TextItem);
    }

    @Test
    void testTextItemFactory_CreateIncorrectTypeTextItem_AssertFalse()
    {
        SlideItem textItem = this.textItemFactory.createSlideItem(1, "String");
        assertFalse(textItem instanceof BitmapItem);
    }

    //testing sliderViewerComponent

    @Test
    void testSliderViewerComponentUpdate_AppendSlide_SlideIsThere()
    {
        this.slideViewerComponent.update(this.presentation1, this.slide1);
        assertEquals(this.slide1, this.slideViewerComponent.getSlide());
    }

    @Test
    void testSliderViewerComponentUpdate_AppendSlideAsNull_SlideIsNull_AssertNull()
    {
        this.slideViewerComponent.update(this.presentation1, null);
        assertNull(this.slideViewerComponent.getSlide());
    }

    @Test
    void testSliderViewerComponentUpdate_AppendPresentationAsNull_AssertThrows()
    {
        assertThrows(IllegalArgumentException.class, () -> this.slideViewerComponent.update(null, this.slide1));
    }

    @Test
    void testSliderViewerComponentPaint_ColorShouldBeBlack_AssertEquals()
    {
        slideViewerComponent.paintComponent(this.graphics);
        assertEquals(Color.white, this.graphics.getColor());
    }

    @Test
    void testSliderViewerComponentInitialFont_FontShouldBeTheSame_AssertEquals()
    {
        assertEquals(new Font("Dialog", Font.BOLD, 10), this.slideViewerComponent.getFont());
    }

    @Test
    void testSliderViewerComponentUpdate_AppendPresentationNotNull_AssertDoesntThrow()
    {
        assertDoesNotThrow(() -> this.slideViewerComponent.update(this.presentation1, this.slide1));
    }

    @Test
    void testInitialDimensionSetup_DimensionWidthHeight_AssertEquals()
    {
        assertEquals(new Dimension(Slide.WIDTH, Slide.HEIGHT), this.slideViewerComponent.getPreferredSize());
    }

    @Test
    void testInitialSetupSlide_SlideIsNull_AssertNull()
    {
        assertNull(this.slideViewerComponent.getSlide());
    }

    @Test
    void testInitialBackgroundColorSetup_BackgroundWhite_AssertEquals()
    {
        assertEquals(Color.white, this.slideViewerComponent.getBackground());
    }

    @Test
    void testSliderViewerComponentUpdate_AppendPresentation_ExactPresentationIsThere()
    {
        this.slideViewerComponent.update(this.presentation1, this.slide1);
        assertEquals(this.presentation1, this.slideViewerComponent.getPresentation());
    }

    @Test
    void testSLideCreation_VectorSlideItemISEmpty_AssertTrue()
    {
        assertTrue(this.slide1.getSlideItems().isEmpty());
    }

    @Test
    void testSLideAppend_AppendElementCheckIfItIsThere_AssertTrue()
    {
        //as the previous test passed and Vector<SlideItem> is empty, I can take the first element for test
        this.slide1.append(1, "String");
        assertNotNull(this.slide1.getSlideItems().firstElement());
    }

    @Test
    void testSLideAppend_AppendItShouldBeTextItem_AssertTrue()
    {
        //as the penultimate test passed and Vector<SlideItem> is empty, I can take the first element for test
        this.slide1.append(1, "String");
        assertTrue(this.slide1.getSlideItems().firstElement() instanceof TextItem);
    }

    @Test
    void testSlideAppendSize_AppendOneSlideItemSizeShouldBeOne_AssertTrue()
    {
        this.slide1.append(1, "String");
        assertEquals(this.slide1.getSlideItems().size(), 1);
    }
}
