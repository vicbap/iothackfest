import { LitElement, html } from '@polymer/lit-element';
import { connect } from 'pwa-helpers/connect-mixin.js';

// This element is connected to the redux store.
import { store } from '../store.js';

// These are the used actions
import { setLocale, fetchMessages } from '../actions/i18n.js';
import { i18n, literalSelector, availableLocalesSelector } from '../reducers/i18n.js';

// We are lazy loading its reducer.
store.addReducers({
  i18n
});

export class I18nLitElement extends connect(store)(LitElement) {
	static get properties() {
		return {
			_locale: { type: String }
		}
	}
	// This is called every time something is updated in the store.
	stateChanged(state) {
		this._locale = state.i18n.locale;
	}
	
	firstUpdated() {
		store.dispatch(fetchMessages());
	}
}

window.customElements.define('i18n-lit-element', I18nLitElement);

export * from '@polymer/lit-element';
export { literalSelector, setLocale, store, availableLocalesSelector};