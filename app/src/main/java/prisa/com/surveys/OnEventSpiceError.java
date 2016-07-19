package prisa.com.surveys;

import com.octo.android.robospice.persistence.exception.SpiceException;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class OnEventSpiceError {
    SpiceException exception;

    public OnEventSpiceError(SpiceException exception) {
        this.exception = exception;
    }
}
