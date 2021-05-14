import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@polymer/iron-icon/iron-icon.js';
import '@polymer/iron-icons/iron-icons.js';
import '@polymer/iron-icons/av-icons.js';
import '@polymer/paper-material/paper-material.js';
import '@polymer/paper-styles/paper-styles.js';
import '@polymer/paper-icon-button/paper-icon-button.js';
import {mixinBehaviors} from '@polymer/polymer/lib/legacy/class.js';
import {AppLocalizeBehavior} from '@polymer/app-localize-behavior/app-localize-behavior.js';
import '@polymer/iron-flex-layout/iron-flex-layout-classes.js';
 
/**
 * `vb-account-row`
 * Sensor displayer
 *
 * @customElement
 * @polymer
 * @demo demo/index.html
 */
class VbSensorRow extends mixinBehaviors([AppLocalizeBehavior], PolymerElement) {
  static get template() {
    return html`
      <style include="iron-flex iron-flex-alignment">
		:host {
			display: inline-block;
			background: var(--vb-account-card-bg, #fff);
			min-width: 972px;
			min-height: 48px;
		}
		
		#car {
			@apply --layout-vertical;
			@apply --layout-wrap;
			padding: 5px;
			margin: 0;
		}
		
		#info {
			@apply --layout-horizontal;
			// width: 100%;
		}
		
		.hori{
			@apply --layout-horizontal;
		}
		
		.vert{
			@apply --layout-vertical;
		}
		
		/* IE 10 support for HTML5 hidden attr */
		[hidden] {
			display: none !important;
		}
		
		.head1 {
			font-size: 20px;
			padding: 2px;
			min-width: 108px;
			text-align: right;
		}
		
		.head2 {
			padding: 6px;
			min-width: 64px;
		}
		
		.ieq {
			padding: 6px;
			min-width: 108px;
		}
		
		.tratio {
			padding-top: 6px;
			padding-left: 4px;
			font-size: 16px;
			min-width: 96px;
		}
		
		.datelt{
			font-size: 11px;
			padding-top: 10px;
			padding-left: 4px;
		}
		
		.carga{
			padding: 6px;
			min-width: 96px;
			text-align: right;
		}
		
		.sincarga{
			min-width: 320px;
		}
		
		.ops{
			font-size: 11px;
			padding-top: 10px;
			padding-left: 4px;
			min-width: 24px;
			text-align: left;
		}
		
		
		.levelok {
			background: var(--vb-account-card-lok-bg, var(--google-green-100));
			
		}
		.levelwarn1 {
			background: var(--vb-account-card-lw1-bg, var(--google-yellow-300));
		}
		.levelwarn2 {
			background: var(--vb-account-card-lw2-bg, var(--google-yellow-700));
		}
		.levelwarn3 {
			background: var(--vb-account-card-lw3-bg, var(--paper-orange-700));
			color: var(--vb-account-card-lw3-color, white);
		}
		.levelwarn4 {
			background: var(--vb-account-card-lw4-bg, var(--paper-orange-700));
			color: var(--vb-account-card-lw4-color, white);
		}
		.levelwarn5 {
			background: var(--vb-account-card-lw5-bg, var(--paper-brown-700));
			color: var(--vb-account-card-lw5-color, white);
		}
		.win {
			color: var(--vb-account-card-win-color, var(--google-green-700));
		}
		
		.flat {
			color: var(--vb-account-card-flat-color, var(--google-blue-700));
		}
		
		.loose {
			color: var(--vb-account-card-loose-color, var(--google-red-700));
		}
		
		.small {
			--iron-icon-height: 12px;
			--iron-icon-width: 12px;
		}
		
	</style>
	<paper-material id="car" animated=[[animated]] elevation=[[elevation]]>
		<div id="info">
			<div class="head2">
				<span>{{tit}}</span>
			</div>
			<div class="head1">
				<span>{{toFixed2(value)}}</span>
			</div>
            <div class="head2">
				<span>{{meassure}}</span>
			</div>
		</div>
	</paper-material>
    `;
  }
	static get properties() {
		return {
		  id: {type: Number, notify: true},
			tit: {type: String, notify: true},
			meassure: {type: String, notify: true},
			value: {type: Number, notify: true},
			language: { value: 'es'},
			resources: {
				value() {
					return {
						'es': { 'car-level': 'Nivel' },
						'en': { 'car-level': 'Level'}
					}
				}
			},
		};
	}
	
		
	toFixed2(i){
		return i.toFixed(2);
	}
	
	ready() {
		super.ready();
	}
	
}

window.customElements.define('vb-account-row', VbSensorRow);
