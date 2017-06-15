package lt.ekgame.cmds;

public interface CmdMiddleware<C extends CmdContext, R extends Exception> extends CmdHandler<C, R> {

}
