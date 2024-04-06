package com.bohdanvlad.controllers.keyController.keyCommands;

import com.bohdanvlad.presentationComponents.Presentation;
import com.bohdanvlad.controllers.Command;

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
