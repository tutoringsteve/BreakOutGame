/**
 * Created by Steven A. Sarasin on 8/7/2014.
 *
 * This view class is merely a test bed for trying out the MVC pattern
 */

package com.forcesofodin.breakoutgame.view;

import com.forcesofodin.breakoutgame.model.BreakoutModel;

/**
 * The View is the "front-end" of the MVC framework, our program. It is how the
 * user views the data which is modeled underneath. The user tells the view what
 * to do with the model and the controller implements the request.
 * <br>
 * The view is an interpretation of the data which is modeled by the 'model'. It is
 * dependent on the model and must contain a reference to the model which is passed to
 * the view via a constructor.
 * <br>
 * The view should allow the user to make requests (more data, change data, request an
 * interaction with the model) without being aware of how this will actually be accomplished.
 * The role of interaction is delegated to the controller, and the view is independent of the
 * controller.
 */
public class BreakoutView {

	private BreakoutModel breakoutModel;

	//view data

	public BreakoutView( BreakoutModel breakoutModel) {
		this.breakoutModel = breakoutModel;
	}

	//interface for requests

}
