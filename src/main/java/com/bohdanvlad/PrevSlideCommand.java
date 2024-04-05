package com.bohdanvlad;

public class PrevSlideCommand extends PresentationCommand implements Command
{


    @Override
    public void execute(Object obj, Object pres)
    {
        this.presentation = (Presentation) pres;
        this.presentation.prevSlide();
    }
}
