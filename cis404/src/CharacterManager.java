import java.util.HashMap;
import java.util.Map;

public class CharacterManager {

	private Map<Character, Character> map = new HashMap<Character, Character>();

	public Character getCharacter(char c) {
		if (map.containsKey(c)) {
			System.out.println("Character '" + c + "' is reused.");
			return map.get(c);
		} else {
			map.put(c, c);
			System.out.println("Character '" + c + "' is created.");
			return map.get(c);
		}
	}

	public static void main(String args[]) {
		CharacterManager characterManager = new CharacterManager();
		characterManager.getCharacter('t');
		characterManager.getCharacter('e');
		characterManager.getCharacter('s');
		characterManager.getCharacter('t');
	}

}