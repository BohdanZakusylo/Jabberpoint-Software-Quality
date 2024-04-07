package com.bohdanvlad.controllers.menuController.menuCommands;

import com.bohdanvlad.presentationComponents.Presentation;
import com.bohdanvlad.controllers.Command;
import com.bohdanvlad.controllers.keyController.keyCommands.PresentationCommand;

public class NewCommand extends PresentationCommand implements Command
{
    public NewCommand(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute(Object obj)
    {
        this.presentation.clear();
    }
}
