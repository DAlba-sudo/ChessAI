package backend.fsm;

public abstract class state {

    private state next;

    public state(state next){
        this.next = next;
    }

    public abstract boolean transferCondition();
    public abstract void action();

    public void setNext(state next) {
        this.next = next;
    }

    public state getNext() {
        return next;
    }


}
