export const SET_LOCALE = 'SET_LOCALE';
export const REQUEST_MESSAGES = 'REQUEST_MESSAGES';
export const RECEIVE_MESSAGES = 'RECEIVE_MESSAGES';
export const FAIL_MESSAGES = 'FAIL_MESSAGES';
export const SHOW_MESSAGE = 'SHOW_MESSAGE';

export const setLocale = (locale) => {
  return {
    type: SET_LOCALE,
    locale
  };
}

export const fetchMessages = () => (dispatch, getState) => {
  dispatch(requestMessages());
  const state = getState();
  if (state.messages) {
    dispatch(receiveMessages(state.messages));
    return Promise.resolve();
  } else {
    return fetch(`/src/data/locales.json`)
      .then(res => res.json())
      .then(data => {
        if (data.error) {
          dispatch(failMessages(data.error));
        } else {
          dispatch(receiveMessages(data));
        }
      })
      .catch((e) => dispatch(failMessages(e)));
  }
};

const requestMessages = () => {
  return {
    type: REQUEST_MESSAGES
  };
}

const receiveMessages = (messages) => {
  return {
    type: RECEIVE_MESSAGES,
    messages
  };
}

const failMessages = (error) => {
  return {
    type: FAIL_MESSAGES,
    error
  };
}

export const fetchMessage = (message) => (dispatch, getState) => {
	const state = getState();
	const locale = state.i18n.locale
	const messages = state.i18n.messages
	const i18lit = message
	if (messages[locale][message]){
		i18lit = messages[locale][message]
	}
	dispatch({ type: SHOW_MESSAGE, i18lit});
};
