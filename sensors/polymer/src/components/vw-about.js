import { html } from '@polymer/lit-element';
import { updateMetadata } from 'pwa-helpers/metadata.js';
import { I18nLitElement, literalSelector } from './i18n-lit-element.js';

class VwAbout extends I18nLitElement {
	render() {
		updateMetadata({
			title: 'About Getronics team',
			description: 'About Getronics team'
		});
		
		const litgetronics = this._literal && this._literal.litgetronics ? this._literal.litgetronics : 'Getronics Team';

		return html`
			<style>
				:host {
					padding: 16px;
					text-align: center;
					line-height: 1.5;
				}
			</style>

			<section>
				<h2>${litgetronics}</h2>
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
window.customElements.define('vw-about', VwAbout);