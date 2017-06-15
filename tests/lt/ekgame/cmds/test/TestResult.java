package lt.ekgame.cmds.test;

import lt.ekgame.cmds.CmdResult;
import lt.ekgame.cmds.ResultStatus;

public class TestResult extends Exception implements CmdResult {
	
	private String message;
	private ResultStatus status;
	private TestBaseCommand command;
	
	public TestResult(TestBaseCommand command, ResultStatus status, String message) {
		this.command = command;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public TestBaseCommand getCommand() {
		return command;
	}

	@Override
	public ResultStatus getStatus() {
		return status;
	}
}
