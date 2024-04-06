package com.bohdanvlad.controllers.keyController.keyCommands;

import com.bohdanvlad.presentationComponents.Presentation;
import com.bohdanvlad.controllers.Command;

public class ExitCommand extends PresentationCommand implements Command
{
    private static final int EXITSTATUS = 0;

    public ExitCommand(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute(Object obj)
    {
        this.presentation.exit(EXITSTATUS);
    }
}
