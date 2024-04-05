package com.bohdanvlad;

public class ExitCommand extends PresentationCommand implements Command
{
    private static final int EXITSTATUS = 0;

    @Override
    public void execute(Object obj, Object pres)
    {
        this.presentation = (Presentation) pres;
        this.presentation.exit(EXITSTATUS);
    }
}
