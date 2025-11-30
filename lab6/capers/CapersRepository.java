package capers;

import java.io.File;

/** A repository for Capers 
 * @author TODO
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = Utils.join(".capers");
    static final File DOGS_FOLDER = Utils.join(CAPERS_FOLDER, "dogs");
    static final File STORY_FILE = Utils.join(CAPERS_FOLDER, "story");

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        Utils.mkDir(CAPERS_FOLDER);
        Utils.mkDir(DOGS_FOLDER);
        Utils.mkFile(STORY_FILE);
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        File story = STORY_FILE;

        // 1. 读取现有内容（如果文件不存在就空字符串）
        String oldContent = "";
        if (story.exists()) {
            oldContent = Utils.readContentsAsString(story);
        }

        // 2. 追加新内容 + 换行，然后一次性整体写回去
        Utils.writeContents(story, oldContent, text, "\n");

        // 3. 打印当前完整故事
        System.out.println(Utils.readContentsAsString(story));
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog d = new Dog(name, breed, age);
        d.saveDog();
        String s = String.format("Woof! My name is %s and I am a %s! I am %d years old! Woof!", name, breed, age);
        System.out.println(s);
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog d = Dog.fromFile(name);
        d.haveBirthday();
        d.saveDog();
    }
}
