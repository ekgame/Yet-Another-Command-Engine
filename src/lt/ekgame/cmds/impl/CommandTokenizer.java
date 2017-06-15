package lt.ekgame.cmds.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lt.ekgame.cmds.CmdToken;
import lt.ekgame.cmds.CmdTokenizer;

public class CommandTokenizer implements CmdTokenizer {
	
	private static Pattern TOKEN = Pattern.compile("^\\s*([^\\s]+)");
	private static Pattern STRING = Pattern.compile("^\\s*(\"(.*?)\"|([^\\s]+))");
	private static Pattern LINE = Pattern.compile("^\\s*(.*)");
	private static Pattern EVERYTHING = Pattern.compile("^\\s*(.*)", Pattern.DOTALL);
	
	private String original, current;
	
	public CommandTokenizer(String input) {
		this.original = this.current = input;
	}

	@Override
	public CmdToken nextToken() {
		Matcher matcher = TOKEN.matcher(current);
		if (matcher.find()) {
			current = current.substring(matcher.group(0).length());
			return new CommandToken(matcher.group(0), matcher.group(1));
		}
		return CommandToken.empty();
	}

	@Override
	public CmdToken nextString() {
		Matcher matcher = STRING.matcher(current);
		if (matcher.find()) {
			current = current.substring(matcher.group(0).length());
			String sanitized = matcher.group(2) == null ? matcher.group(1) : matcher.group(2);
			return new CommandToken(matcher.group(0), sanitized);
		}
		return CommandToken.empty();
	}
	
	@Override
	public CmdToken nextLine() {
		Matcher matcher = LINE.matcher(current);
		if (matcher.find()) {
			current = current.substring(matcher.group(0).length());
			return new CommandToken(matcher.group(0), matcher.group(1));
		}
		return CommandToken.empty();
	}

	@Override
	public CmdToken remaning() {
		Matcher matcher = EVERYTHING.matcher(current);
		if (matcher.find()) {
			current = current.substring(matcher.group(0).length());
			return new CommandToken(matcher.group(0), matcher.group(1));
		}
		return CommandToken.empty();
	}

	@Override
	public String getOriginalInput() {
		return original;
	}

	@Override
	public void pushBack(CmdToken token) {
		current = token.getRawValue() + current;
	}
}
