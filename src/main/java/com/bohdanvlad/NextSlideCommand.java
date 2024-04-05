package com.bohdanvlad;

public class NextSlideCommand extends PresentationCommand implements Command
{

    @Override
    public void execute(Object obj, Object pres)
    {
        this.presentation = (Presentation) pres;
        this.presentation.nextSlide();
    }
}
