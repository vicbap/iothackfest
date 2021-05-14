import { html } from '@polymer/lit-element';
import { I18nLitElement, literalSelector } from './i18n-lit-element.js';

class VwOffline extends I18nLitElement {
  
	render() {
		
		const litoffline = this._literal && this._literal.offline ? this._literal.offline : 'Offline mode';

		return html`
			<style>
				:host {
					padding: 16px;
					text-align: center;
					line-height: 1.5;
				}
			</style>

			<section>
				<h2>${litoffline}</h2>
				<div @click="${() => this._refresh()}">${litoffline}</div>
			</section>

		`;
	}

	static get properties() { return {
		_literal: { type: Object }
	}}

	// This is called every time something is updated in the store.
	stateChanged(state) {
		this._literal = literalSelector(state);
	}

	_refresh() {
		this.dispatchEvent(new CustomEvent('refresh', {composed: true}));
	}
	
}
window.customElements.define('vw-offline', VwOffline);