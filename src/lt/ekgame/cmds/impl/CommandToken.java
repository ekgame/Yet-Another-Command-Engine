package lt.ekgame.cmds.impl;

import lt.ekgame.cmds.CmdToken;

public class CommandToken implements CmdToken {
	
	private static CommandToken EMPTY = new CommandToken(null, null);
	
	private String raw, sanitized;
	
	public CommandToken(String raw, String sanitized) {
		this.raw = raw;
		this.sanitized = sanitized;
	}

	public static CommandToken empty() {
		return EMPTY;
	}

	@Override
	public boolean isPresent() {
		return raw != null;
	}

	@Override
	public String getRawValue() {
		return raw;
	}

	@Override
	public String getValue() {
		return sanitized;
	}
}
