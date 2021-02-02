package animation.controlPanel;


/**

 * Callback interface to be notified by a SwingAnimator of a new time frame.

 * @author Greg Cope

 *

 */

public interface SwingAnimatorCallback {



    /**

     * Callback method for the SwingAnimator

     * @param caller

     */

    public void callback(Object caller);



    /**

     * Returns true if the SwingAnimator should terminate.

     * @return

     */

    public boolean hasTerminated();



}