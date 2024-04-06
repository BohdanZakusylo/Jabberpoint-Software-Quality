package com.bohdanvlad;

public class NextSlideCommand extends PresentationCommand implements Command
{

    public NextSlideCommand(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute(Object obj)
    {
        this.presentation.nextSlide();
    }
}
