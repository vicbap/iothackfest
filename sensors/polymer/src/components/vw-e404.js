import { html } from '@polymer/lit-element';
import { updateMetadata } from 'pwa-helpers/metadata.js';
import { I18nLitElement, literalSelector } from './i18n-lit-element.js';

class VwE404 extends I18nLitElement {
	render() {
		updateMetadata({
			title: 'Page Not Found',
			description: 'Page not found'
		});
		
		const lit404 = this._literal && this._literal.warning404 ? this._literal.warning404 : 'Oops! You hit a 404';

		return html`
			<style>
				:host {
					padding: 16px;
					text-align: center;
					line-height: 1.5;
				}
			</style>

			<section>
				<h2>${lit404}</h2>
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
window.customElements.define('vw-e404', VwE404);