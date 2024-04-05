package com.bohdanvlad;

public class PrevSlideCommand extends PresentationCommand implements Command
{


    public PrevSlideCommand(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute(Object obj)
    {
        this.presentation.prevSlide();
    }
}
