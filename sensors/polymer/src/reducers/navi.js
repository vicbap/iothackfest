import {
    UPDATE_PAGE,
    UPDATE_OFFLINE,
    OPEN_SNACKBAR,
    CLOSE_SNACKBAR,
    UPDATE_DRAWER_STATE,
    UPDATE_TIMER
  } from '../actions/navi.js';
  
  const INITIAL_STATE = {
    page: '',
    offline: false,
    drawerOpened: false,
    snackbarOpened: false,
    timer: 0,
  };
  
  const navi = (state = INITIAL_STATE, action) => {
    switch (action.type) {
      case UPDATE_TIMER:
        // console.log('UPDATE_TIMER',action.timer)
        return {
          ...state,
          timer: action.timer
        };
      case UPDATE_PAGE:
        return {
          ...state,
          page: action.page,
          timer: 0
        };
      case UPDATE_OFFLINE:
        return {
          ...state,
          offline: action.offline
        };
      case UPDATE_DRAWER_STATE:
        return {
          ...state,
          drawerOpened: action.opened
        };
      case OPEN_SNACKBAR:
        return {
          ...state,
          snackbarOpened: true
        };
      case CLOSE_SNACKBAR:
        return {
          ...state,
          snackbarOpened: false
        };
      default:
        return state;
    }
  };
  
  export default navi;
  