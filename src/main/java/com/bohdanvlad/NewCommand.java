package com.bohdanvlad;

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
