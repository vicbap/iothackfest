import { html } from '@polymer/lit-element';
import { setPassiveTouchGestures } from '@polymer/polymer/lib/utils/settings.js';
import { connect } from 'pwa-helpers/connect-mixin.js';
import { installMediaQueryWatcher } from 'pwa-helpers/media-query.js';
import { installOfflineWatcher } from 'pwa-helpers/network.js';
import { installRouter } from 'pwa-helpers/router.js';
import { updateMetadata } from 'pwa-helpers/metadata.js';
import { I18nLitElement, literalSelector, availableLocalesSelector, store } from './i18n-lit-element.js';

// This element is connected to the Redux store.
// import { store } from '../store.js';

// These are the actions needed by this element.
import { navigate, updateOffline, updateDrawerState, increaseTimer } from '../actions/navi.js';
import { setLocale } from '../actions/i18n.js';

// These are the elements needed by this element.
import '@polymer/app-layout/app-drawer/app-drawer.js';
import '@polymer/app-layout/app-header/app-header.js';
import '@polymer/app-layout/app-scroll-effects/effects/waterfall.js';
import '@polymer/app-layout/app-toolbar/app-toolbar.js';
import '@polymer/iron-flex-layout/iron-flex-layout-classes.js';
import { menuIcon } from './my-icons.js';
import './snack-bar.js';
import './vw-home.js';

// class IotApp extends connect(store)(LitElement) {
class IotApp extends I18nLitElement {

 render() {
 
	const lithome = this._literal && this._literal.home ? this._literal.home : 'Home';
	const litabout = this._literal && this._literal.about ? this._literal.about : 'About us';
	const litstmsg1 = this._literal && this._literal.apmsg1 ? this._literal.apmsg1 : 'You are now ';
	const litfooter = this._literal && this._literal.footer ? this._literal.footer : 'This is the footer';
    
    // Anything that's related to rendering should be done in here.
    return html`
    <style>
		:host {
			display: block;
			
			--app-drawer-width: 256px;
			--app-header-height: 128px;
			--app-footer-height: 104px;
			/* The 1px is to make the scrollbar appears all the time */
			--app-main-content-min-height: calc(100vh - var(--app-header-height) - var(--app-footer-height) + 1px);
			
			/* Default theme */
			--app-primary-color: var(--paper-brown-700);
			--app-primary-color-grad-l4: var(--paper-brown-400);
			--app-primary-color-grad-l2: var(--paper-brown-200);
			--app-primary-color-grad-l1: var(--paper-brown-100);
			--app-primary-color-grad-l0: var(--paper-brown-50);
			--app-secondary-color: black;
			
			--app-dark-text-color: black;
			--app-ligth-text-color: white;
			--app-background-color: #fafafa;
			
			--app-color-win:  var(--google-green-700);
			--app-color-flat: var(--google-blue-700);
			--app-color-loose: var(--google-red-700)

			color: var(--app-dark-text-color);
			background-color: var(--app-background-color);

			--app-drawer-background-color: var(--app-background-color);
			--app-drawer-text-color: var(--app-dark-text-color);
			--app-drawer-selected-color: var(--app-dark-text-color);
			
			--app-main-background-color: var(--app-primary-color-grad-l0);
		}
		app-header {
			position: fixed;
			top: 0;
			left: 0;
			width: 100%;
			text-align: center;
			background-color: var(--app-background-color);
			z-index: 1;
		}

		.toolbar-top {
			padding: 0 8px 0 8px;
		}
		
		.toolbar-list {
		
		}

		.toolbar-list > div {
			display: block;
			text-decoration: none;
			color: var(--app-drawer-text-color);
			line-height: 40px;
			padding: 0 12px;
			float: right;
		}
		
		.toolbar-list > div[selected] {
			color: white;
			background-color: var(--app-primary-color);
			font-weight: bold;
		}

		
		.toolbar-bottom {
			justify-content: center;
			background-color: var(--app-background-color);
		}
		
		.language{
			height: 95%;
			font-size: 14px;
		}
		
		.language > div {
			width: 24px;
			height: 24px;
			padding: 2px;
			box-sizing: border-box;
			background-color: var(--paper-grey-400);
			text-align: center;		
			float: left;	
		}
		
		.language > div[active] {
			color: white;
			background-color: var(--app-primary-color);
		}

		[main-title] > a {
			font-size: 18px;
			font-weight: bold;
			letter-spacing: 0.1em;
			text-decoration: none;
			text-transform: uppercase;
			color: inherit;
			pointer-events: auto;
			/* required for IE 11, so this <a> can receive pointer events */
			display: inline-block;
		}
	
		app-drawer {
			z-index: 2;
		}

		.drawer-list {
			box-sizing: border-box;
			width: 100%;
			height: 100%;
			padding: 24px;
			background: var(--app-drawer-background-color);
			position: relative;
		}
		
		.drawer-list > div {
			display: block;
			text-decoration: none;
			color: var(--app-drawer-text-color);
			line-height: 40px;
			padding: 0 24px;
		}
		
		.drawer-list > div[selected] {
			color: var(--app-drawer-selected-color);
			font-weight: bold;
		}

		main {
			display: block;
			background-color: var(--app-main-background-color);
		}

		.main-content {
			padding-top: var(--app-header-height);
			min-height: var(--app-main-content-min-height);
			display: block;
		}

		._page {
			display: none;
		}

		._page[active] {
			display: block;
		}
        .subtitle {
			font-size: 18px;
			font-weight: normal;
        }
		.menu-btn,
		.back-btn,
		.signin-btn {
			display: inline-block;
			width: 40px;
			height: 40px;
			padding: 8px;
			box-sizing: border-box;
			background: none;
			border: none;
			fill: var(--app-header-text-color);
			cursor: pointer;
			text-decoration: none;
		}

		footer {
			display: inline-block;
			height: var(--app-footer-height);
			padding: 24px;
			box-sizing: border-box;
			text-align: center;
		}

		[hidden] {
			display: none !important;
		}
    </style>

    <!-- Header -->
    <app-header condenses reveals effects="waterfall">
      <app-toolbar class="toolbar-top">
        <button class="menu-btn" title="Menu" @click="${this._menuButtonClicked}">${menuIcon}</button>
        <div main-title>${this.appTitle}</div>
		<div class="language">
			<div lang="en" ?active="${this._locale === 'en'}" @click="${this._localeDivClicked}" >en</div>
			<div lang="es" ?active="${this._locale === 'es'}" @click="${this._localeDivClicked}" >es</div>
		</div>
      </app-toolbar>

      <!-- This gets hidden on a small screen-->
      <nav class="toolbar-list">
        <div href="/home"   ?selected="${this._page === 'home'}"   @click="${this._toolbarDivClicked}" >${lithome}</div>
		<div href="/about" ?selected="${this._page === 'about'}" @click="${this._toolbarDivClicked}" >${litabout}</div>
      </nav>
    </app-header>

    <!-- Drawer content -->
    <app-drawer .opened="${this._drawerOpened}" @opened-changed="${this._drawerOpenedChanged}">
      <div class="drawer-list">
        <div href="/home"   ?selected="${this._page === 'home'}"   @click="${this._toolbarDivClicked}" >${lithome}</div>
		<div href="/about" ?selected="${this._page === 'about'}" @click="${this._toolbarDivClicked}" >${litabout}</div>
      </div>
    </app-drawer>

    <!-- Main content -->
    <main role="main" class="main-content">
      <vw-home class="_page" ?active="${this._page === 'home'}"></vw-home>
      <vw-about class="_page" ?active="${this._page === 'about'}"></vw-about>
      <vw-e403 class="_page" ?active="${this._page === 'e403'}"></vw-e403>
      <vw-e404 class="_page" ?active="${this._page === 'e404'}"></vw-e404>
    </main>

    <footer>
      <p>${litfooter}</p>
    </footer>

    <snack-bar ?active="${this._snackbarOpened}">${litstmsg1} ${this._offline ? 'offline' : 'online'}.</snack-bar>
    `;
  }

  static get properties() {
    return {
      appTitle: { type: String },
      _page: { type: String },
      _locale: { type: String },
      _locales: { type: Array },
      _drawerOpened: { type: Boolean },
      _snackbarOpened: { type: Boolean },
      _offline: { type: Boolean },
	  _literal: { type: Object },
	  _litHome: { type: String },
	  _timer: { type: Number }
    }
  }

  constructor() {
    super();
    setPassiveTouchGestures(true);
  }

  firstUpdated() {
    // installRouter((location) => store.dispatch(navigate(location)));
    installRouter((location) => store.dispatch(navigate(decodeURIComponent(location.pathname))));
    installOfflineWatcher((offline) => store.dispatch(updateOffline(offline)));
	this.removeAttribute('unresolved');
    installMediaQueryWatcher(`(min-width: 460px)`, () => store.dispatch(updateDrawerState(false)));
	this.addEventListener('nav', function(e) {
		var l = '/';
		if (e.detail.page != null && e.detail.page != undefined){
			l = l + e.detail.page + '/'
		}
		if (e.detail.id != null && e.detail.id != undefined){
			l = l + e.detail.id
		}
		store.dispatch(navigate(l));
	});
  }

  updated(changedProps) {
    if (changedProps.has('_page')) {
      const pageTitle = this.appTitle + ' - ' + this._page;
      updateMetadata({
        title: pageTitle,
        description: pageTitle
        // This object also takes an image property, that points to an img src.
      });
	  // this.interval = setInterval(() => store.dispatch(increaseTimer(1)), 10000);
    }
  }

  _localeDivClicked(e) {
	// console.log(e, e.target, e.target.lang)
    
    store.dispatch(setLocale(e.target.lang));
  }

  _menuButtonClicked() {
    store.dispatch(updateDrawerState(true));
  }

  _drawerOpenedChanged(e) {
    store.dispatch(updateDrawerState(e.target.opened));
  }
  
  _toolbarDivClicked(e) {
	if (e.target.attributes.getNamedItem('href') != null && e.target.attributes.getNamedItem('href') != undefined){
		store.dispatch(navigate(e.target.attributes.getNamedItem('href').value));
	}
  }
  
  stateChanged(state) {
	// console.log(state)
    this._page = state.navi.page;
    this._offline = state.navi.offline;
    this._snackbarOpened = state.navi.snackbarOpened;
    this._drawerOpened = state.navi.drawerOpened;
	this._literal = literalSelector(state);
	this._locales = availableLocalesSelector(state);
	this._locale = state.i18n.locale;
	this._timer = state.navi.timer;
	
  }
  
}

window.customElements.define('iot-app', IotApp);
