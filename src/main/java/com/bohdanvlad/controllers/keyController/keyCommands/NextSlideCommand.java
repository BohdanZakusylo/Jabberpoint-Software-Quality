package com.bohdanvlad.controllers.keyController.keyCommands;

import com.bohdanvlad.presentationComponents.Presentation;
import com.bohdanvlad.controllers.Command;

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
