package com.bohdanvlad.presentationComponents;

import java.util.ArrayList;


/**
 * <p>Presentation maintains the slides in the presentation.</p>
 * <p>There is only instance of this class.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation
{
	private String showTitle; // title of the presentation
	private ArrayList<Slide> showList; // an ArrayList with Slides
	private int currentSlideNumber; // the slidenummer of the current Slide
	private SlideViewerComponent slideViewComponent; // the viewcomponent of the Slides
	private static volatile Presentation presentation;

	public Presentation()
	{
		this.currentSlideNumber = 0;
		this.showTitle = "";
		this.showList = null;
		this.slideViewComponent = null;
		clear();
	}

	public static Presentation getPresentation()
	{
		Presentation result = presentation;
		if(result == null)
		{
			//safer to use Presentation.class instead "this"
			synchronized (Presentation.class)
			{
				result = presentation;
				if(result == null)
				{
					presentation = result = new Presentation();
				}
			}
		}

		return result;
	}

	public Presentation(SlideViewerComponent slideViewerComponent)
	{
		this.slideViewComponent = slideViewerComponent;
		clear();
	}

	public int getSize()
	{
		return this.showList.size();
	}

	public String getTitle()
	{
		return this.showTitle;
	}

	public void setTitle(String nt)
	{
		this.showTitle = nt;
	}

	public void setShowView(SlideViewerComponent slideViewerComponent)
	{
		this.slideViewComponent = slideViewerComponent;
	}

	// give the number of the current slide
	public int getSlideNumber()
	{
		return this.currentSlideNumber;
	}

	// change the current slide number and signal it to the window
	public void setSlideNumber(int number)
	{
		this.currentSlideNumber = number;
		if (this.slideViewComponent != null)
		{
			this.slideViewComponent.update(this, getCurrentSlide());
		}
	}

	// go to the previous slide unless your at the beginning of the presentation
	public void prevSlide()
	{
		if (this.currentSlideNumber > 0)
		{
			setSlideNumber(this.currentSlideNumber - 1);
	    }
	}

	// go to the next slide unless your at the end of the presentation.
	public void nextSlide()
	{
		if (this.currentSlideNumber < (showList.size()-1))
		{
			setSlideNumber(this.currentSlideNumber + 1);
		}
	}

	// Delete the presentation to be ready for the next one.
	public void clear()
	{
		this.showList = new ArrayList<Slide>();
		setSlideNumber(-1);
	}

	// Add a slide to the presentation
	public void append(Slide slide)
	{
		this.showList.add(slide);
	}

	// Get a slide with a certain slidenumber
	public Slide getSlide(int number)
	{
		if (number < 0 || number >= getSize())
		{
			return null;
	    }
			return (Slide)this.showList.get(number);
	}

	// Give the current slide
	public Slide getCurrentSlide()
	{
		return getSlide(this.currentSlideNumber);
	}

	public void exit(int n)
	{
		System.exit(n);
	}
}
