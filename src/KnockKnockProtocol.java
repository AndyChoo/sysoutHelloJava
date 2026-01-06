public class KnockKnockProtocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;

    private static final int numJokes = 5;

    private static int state = WAITING;
    private static int currentJoke = 0;

    private String[] clues = {
        "Java",
        "Tank",
        "Boo",
        "Atch",
        "Cow says"
    };
    private String[] answers = {
        "Java nice day!",
        "You're welcome!",
        "Don't cry, it's just a joke!",
        "Bless you!",
        "No silly, cow says moo!"
    };

    // facilitate server, client interaction
    public String processInput(String input) {
        String output = null;
        if(state == WAITING) {
            output = "knock!knock!";
            state = SENTKNOCKKNOCK;
        }
        else if(state == SENTKNOCKKNOCK) {
            if(input.equalsIgnoreCase("Who is there?")) {
                output = clues[currentJoke];
                state = SENTCLUE;
            }
            else {
                output = "You are supposed to say \"Who is there?\". Try again. knock!knock!";
            }
        }
        else if(state == SENTCLUE) {
            if(input.equalsIgnoreCase(clues[currentJoke] + " who?")) {
                output = answers[currentJoke] + ". Want another? (y/n)";
                state = ANOTHER;
            }
            else {
                output = "You are supposed to say \"" + clues[currentJoke] + " who?\". Try again.";
            }
        }
        else if(state == ANOTHER) {
            if(input.equalsIgnoreCase("y")) {
                if(currentJoke == numJokes-1) {
                    currentJoke = 0;
                }
                else {
                    currentJoke++;
                }
                output = "knock!knock!";
                state = SENTKNOCKKNOCK;
            }
            else {
                output = "Bye.";
                state = WAITING;
            }
        }
        return output;
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
