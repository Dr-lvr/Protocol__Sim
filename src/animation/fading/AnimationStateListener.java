package animation.fading;

/**

 * Listener interface that allows notification when a particular state is reached

 * @author Greg Cope

 *

 */

public interface AnimationStateListener {



    /**

     * Called by the SwingAnimator when animation has started.

     * @param animator

     */

    public void animationComplete(SwingAnimator animator);



    /**

     * Called by the SwingAnimator when animation is complete.

     * @param animator

     */

    public void animationStarted(SwingAnimator animator);



}
