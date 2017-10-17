package main;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.Key;

import java.nio.charset.Charset;


public class Game {

    private Terminal terminal;
    private PlayerObject player;
    private Key key;
    private Render render;

    public Game(){
        this.terminal = TerminalFacade.createTerminal(System.in,System.out, Charset.forName("UTF8"));
        render = new Render(terminal);


    }



    public void run() throws InterruptedException{
        this.player = new PlayerObject(50,15);
        terminal.enterPrivateMode();
        terminal.setCursorVisible(false);

        while (true){

            key = terminal.readInput();
            if(key != null){
                input(key);
            }

            render.drawPlayer(player);

            Thread.sleep(200);

        }
    }

    public void input(Key key){
        switch (key.getKind()){
            case ArrowUp:
                System.out.println("upp");
                break;
            case ArrowDown:

                break;
            case ArrowLeft:

                break;
            case ArrowRight:

                break;
        }

    }


}
