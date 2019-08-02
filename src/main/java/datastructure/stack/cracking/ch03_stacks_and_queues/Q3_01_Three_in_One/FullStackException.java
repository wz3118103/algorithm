package datastructure.stack.cracking.ch03_stacks_and_queues.Q3_01_Three_in_One;

public class FullStackException extends Exception {
	private static final long serialVersionUID = 1L;

	public FullStackException(){
        super();
    }

    public FullStackException(String message){
        super(message);
    }
}
