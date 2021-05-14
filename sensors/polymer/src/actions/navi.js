export const UPDATE_TIMER = 'UPDATE_TIMER';
export const UPDATE_PAGE = 'UPDATE_PAGE';
export const UPDATE_OFFLINE = 'UPDATE_OFFLINE';
export const UPDATE_DRAWER_STATE = 'UPDATE_DRAWER_STATE';
export const OPEN_SNACKBAR = 'OPEN_SNACKBAR';
export const CLOSE_SNACKBAR = 'CLOSE_SNACKBAR';

export const navigate = (location) => (dispatch) => {
	// console.log(location)
	const parts = location.slice(1).split('/');
	const page = parts[0] || 'home';
	// console.log(location,parts,page)
	dispatch(loadPage(page,parts));
	dispatch(updateDrawerState(false));
};

const loadPage = (page,payload) => async (dispatch, getState) => {
	let module;
	switch(page) {
		case 'home':
		  module = await import('../components/vw-home.js');
		  break;
		case 'about':
		  module = await import('../components/vw-about.js');
		  break;
		case 'e403':
		  module = await import('../components/vw-e403.js');
		  break;
		default:
		  page = 'e404';
	}
	
	if (page === '404') {
		import('../components/vw-e404.js');
	}

	dispatch(updatePage(page));
};

const updatePage = (page) => {
  return {
    type: UPDATE_PAGE,
    page
  };
};

let snackbarTimer;

export const showSnackbar = () => (dispatch) => {
  dispatch({
    type: OPEN_SNACKBAR
  });
  window.clearTimeout(snackbarTimer);
  snackbarTimer = window.setTimeout(() =>
    dispatch({ type: CLOSE_SNACKBAR }), 3000);
};

export const increaseTimer = (seconds)  => (dispatch, getState) => {
	const state = getState()
	var timer = seconds
	if (state.navi && state.navi.timer){
		timer = state.navi.timer + seconds
	}
		
	dispatch({
		type: UPDATE_TIMER,
		timer
	});
};	

export const resetTimer = () => (dispatch, getState) => {
	var timer = 0
	dispatch({
		type: UPDATE_TIMER,
		timer
	});
};	

export const updateOffline = (offline) => (dispatch, getState) => {
  // Show the snackbar only if offline status changes.
  if (offline !== getState().navi.offline) {
    dispatch(showSnackbar());
  }
  dispatch({
    type: UPDATE_OFFLINE,
    offline
  });
};

export const updateDrawerState = (opened) => {
  return {
    type: UPDATE_DRAWER_STATE,
    opened
  };
};
