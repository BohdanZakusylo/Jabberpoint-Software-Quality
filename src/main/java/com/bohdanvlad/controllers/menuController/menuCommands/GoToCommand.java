package com.bohdanvlad.controllers.menuController.menuCommands;

import com.bohdanvlad.presentationComponents.Presentation;
import com.bohdanvlad.controllers.Command;
import com.bohdanvlad.controllers.keyController.keyCommands.PresentationCommand;

public class GoToCommand extends PresentationCommand implements Command
{

    public GoToCommand(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute(Object obj)
    {
        int pageNumber = Integer.parseInt((String) obj);
        this.presentation.setSlideNumber(pageNumber-1);
    }
}
