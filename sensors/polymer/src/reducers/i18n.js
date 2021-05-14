
import { SET_LOCALE, SHOW_MESSAGE, RECEIVE_MESSAGES } from '../actions/i18n.js';
import { createSelector } from 'reselect';

const INITIAL_STATE = {
  locale: 'en',
  messages: {},
  i18lit: '',
  error: ''
};

export const i18n = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case SET_LOCALE:
      return {
        ...state,
        locale: action.locale
      };
    case RECEIVE_MESSAGES:
      return {
        ...state,
        messages: action.messages
      };
    case SHOW_MESSAGE:
      return {
        ...state,
        i18lit: action.i18lit
      };
    default:
      return state;
  }
};

const messagesSelector = state => state.i18n.messages;
const localeSelector = state => state.i18n.locale;

export const literalSelector = createSelector(
localeSelector,
messagesSelector,
(locale, messages) => {
    return messages[locale];
}
);

export const availableLocalesSelector = createSelector(
messagesSelector,
(messages) => {
    return messages.keys;
}
);