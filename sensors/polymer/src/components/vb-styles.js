import { html } from '@polymer/lit-element';
import '@polymer/iron-flex-layout/iron-flex-layout-classes.js';

export const FlexStyle = html`
<style include="iron-flex iron-flex-alignment" >

	.horizontal {
		@apply --layout-horizontal;
		@apply --center-justified;	
	}
	
	.vertical {
		@apply --layout-vertical;
		@apply --center-justified;
		
	}			

</style>
`;


export const ActionsStyle = html`
<style>
	.actions{
		background-color: var(--app-main-background-color);
		color: var(--app-primary-color-grad-l5);
		width: 100%;
		height: 30px;
		margin-top: -20px;
		margin-bottom: 4px;
	}
	
	.actions > div{
		float: left;
		padding: 2px;
	}
	
	.now{
		margin: 4px;
		text-align: right;
		font-size: 12px;
		color: var(--app-primary-color-grad-l4);
	}

</style>
`;

export const EventsSyle = html`
	<style include="iron-flex iron-flex-alignment" >

	vb-event {
		width: 98%;
		padding: 2px;
		--vb-event-atr-bg: var(--app-primary-color-grad-l2);
		--vb-event-level1-bg: var(--app-primary-color-grad-l2);
		--vb-event-level2-bg: var(--app-primary-color-grad-l4);
		--vb-event-level3-bg: var(--app-primary-color);
	}

</style>
`;

export const AccountStyle = html`
${EventsSyle}
<style>
	section {
		padding: 2px;
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
	
	.container{
		display: block;
		overflow: hidden;
	}
	.box {
		box-shadow: 0 1px 2px 0 var(--app-primary-color-grad-l2), 0 0 0 1px var(--app-primary-color-grad-l1);
		max-width: 600px;
		margin: 4px;
		background-color: var(--app-background-color);
		display: inline-block;
		float: left;
	}
	
	.load{
		padding: 4px;
	}
	
	vb-load-card{
		--vb-load-card-bg:  var(--app-background-color);
	}
	
	.chart{
		display: inline-block;
		width: 100%;
	}
	
	.charttypes {
		display: inline-block;
		width: 100%;
	}
	
	.charttypes > div{
		float: left;
		padding: 2px;
		width: 80px;
		text-align: center;
		margin: 1px;
		font-size: 16px;
	}
	
	.charttypes > div[active] {
		color: white;
		background-color: var(--app-primary-color);
	}
	
	.timeframe {
		border-bottom: 1px solid var(--app-primary-color-grad-l1);
		display: inline-block;
		width: 100%;
	}
	
	.timeframe > div{
		float: left;
		padding: 2px;
		width: 68px;
		text-align: center;
		margin: 1px 1px -1px 1px;
		font-size: 14px;
	}
	.timeframe > div:hover{
		background-color: var(--app-primary-color-grad-l2);
	}
	
	.timeframe > div[active] {
		font-weight: bold;
		border-bottom: 3px solid var(--app-primary-color-grad-l4);
	}
	
	.chartbox {
		display: block;
		margin: 4px;
		background-color: var(--app-primary-color-grad-l2);
		min-height: 300px;
	}
	
	.chart google-chart {
		height: 300px;
		width: 592px;
	}
	
	[hidden] {
		display: none !important;
	}
	
	vb-cycle-card{
		--vb-cycle-card-bg:  var(--app-background-color);
		--vb-cycle-card-win-bg:  var(--app-background-color);
		--vb-cycle-card-flat-bg:  var(--app-background-color);
		--vb-cycle-card-loose-bg:  var(--app-background-color);
		width: 100%;
	}
	
	.block::after {
		content: '';
		display: block;
		clear: both;
	}
	
	vb-report{
		padding-top: 2px;
		width: 100%;
		font-size: 10px;
		--vb-report-card-bg:  var(--app-background-color);
	}
	
	.data{
		border-top: 1px solid var(--app-primary-color-grad-l1);
		display: inline-block;
		width: 99.5%;
		padding: 2px;
	}
	
	.data > div{
		font-size: 11px;
		padding: 2px;
		
	}
	
	.name{
		padding-left: 2px;
		color: var(--app-primary-color);
		font-weight: bold;
	}
	
	.equity{
		margin: 4px;
		font-size: 14px;
	}
	
	.now{
		margin: 4px;
		text-align: right;
		font-size: 12px;
		color: var(--app-primary-color-grad-l4);
	}
	
	.column{
		float: left;
		padding: 2px;
		width: 48%;
		
	}
	
	
	.routes {
		display: block;
		width: 100%;
		padding: 2px;
		text-align: center;
		min-height: 24px;
		overflow: hidden;
	}
	
	.route{
		display: block;
		float: left;
		margin: 2px;
		width: 80px;
		font-size: 11px;
		background-color: var(--app-primary-color-grad-l2);
		
	}
	.actived{
		background-color: var(--app-primary-color-grad-l4);
		color: var(--app-ligth-text-color);
	}
	
	.route > div{
		float: left;
		margin: 2px;
	}
	
	.routename {
		width: 100%;
		
	}
	
	
</style>
`;

