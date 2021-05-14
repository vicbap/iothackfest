import { html } from '@polymer/lit-element';
import { updateMetadata } from 'pwa-helpers/metadata.js';
import { I18nLitElement, literalSelector } from './i18n-lit-element.js';

class VwE403 extends I18nLitElement {
	render() {
  
		const lit403 = this._literal && this._literal.warning403 ? this._literal.warning403 : 'Permission denied';
  
		return html`
			<style>
				:host {
					padding: 16px;
					text-align: center;
					line-height: 1.5;
				}
			</style>

			<section>
				<h2>${lit403}</h2>
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
}
window.customElements.define('vw-e403', VwE403);