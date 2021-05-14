import { html } from '@polymer/lit-element';
import { I18nLitElement, store } from './i18n-lit-element.js';
import { repeat } from 'lit-html/directives/repeat.js';
import '@polymer/iron-icon/iron-icon.js';
import '@polymer/iron-icons/iron-icons.js';
import '@polymer/iron-icons/image-icons.js';

// These are the used actions
import { navigate } from '../actions/navi.js';
import { searchSensors} from '../actions/sensors.js';
import { sensors, sensorListSelector } from '../reducers/sensors.js';

import './vw-offline.js';
import './vb-sensor-row.js';
import { ActionsStyle } from './vb-styles.js';

// We are lazy loading its reducer.
store.addReducers({
  sensors
});

class VwHome extends I18nLitElement {
	render() {
		const now = new Date().toLocaleString();
		return html`
			${ActionsStyle}
			<style>
				:host {
					display: block;
					text-align: center;
				}
				vb-sensor-card {
					margin: 2px;
				}
				.sensor{
					--vb-sensor-card-bg: var (--app-primary-color-grad-l1);
				}
			</style>
			
			<section >
				<div class="actions horizontal" >
					<div ?hidden="${!this._isFetching}"><iron-icon icon="lock-open"></iron-icon></div>	
					<div ?hidden="${this._isFetching}" ><iron-icon icon="lock-outline"></iron-icon></div>	
					<div class="now">${now}</div>
					<div ?hidden="${this._isFetching}" @click="${() => store.dispatch(searchSensors())}"><iron-icon icon="refresh"></iron-icon></div>	
				</div>
			</section>
			
			
			<section ?hidden="${this._showOffline}">
				<div class="sensors" ?hidden="${!this._sensor}">
				  ${repeat(this._sensor, (item) => html`
					<vb-sensor-row class="sensor" id=${item.id} tit="${item.tit}" value=${item.value} unit=${item.unit} language="${this._locale}" elevation=1 ></vb-sensor-row>
				  `)}
				</div>
			</section>
			<vw-offline ?hidden="${!this._showOffline}" @refresh="${() => store.dispatch(refreshPage())}"></vw-offline>
		`;
	}

	static get properties() { return {
		_locale: { type: String },
		_sensor: { type: Array },
	}}
	
	firstUpdated() {
		store.dispatch(searchSensors());
	}

	// This is called every time something is updated in the store.
	stateChanged(state) {
		// console.log('home',state);
		this._sensor = sensorListSelector(state);
		this._locale = state.i18n.locale;
	}
}

window.customElements.define('vw-home', VwHome);