package main;

/*
Render: Ritar ut chars med färger i terminalfönstret.
*/

import com.googlecode.lanterna.terminal.Terminal;

public class Render {

    Terminal terminal;

    public Render(Terminal terminal){
        this.terminal = terminal;

    }



    public void drawPlayer(){

        terminal.moveCursor();



    }

}//end of class
