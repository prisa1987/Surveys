package prisa.com.surveys.mvp.executor;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public abstract class Executable<T> {

    abstract T runWithRealm();
    abstract void runWithSpiceManager();

    public T execute() {
        runWithRealm();
        runWithSpiceManager();
        return runWithRealm();
    }

}
