public interface Queue {
    /**
     * adds a Bigram onto the rear of Queue
     * @param b Bigram to be added
     */
    public void enqueue(Bigram b);

    /**
     * removes a Bigram onto the front of Queue
     * @return removed bigram
     */
    public Bigram dequeue();

    /**
     * returns at Bigram at the front of Queue without removing
     * @return Bigram at the front
     */
    public Bigram peek();
}
