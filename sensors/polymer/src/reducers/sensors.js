import { createSelector } from 'reselect';
import {
  REQUEST_SENSORS, RECEIVE_SENSORS, FAIL_SENSORS,
} from '../actions/sensors.js';

export const sensors = (state = {query: null}, action) => {
  switch (action.type) {
    case REQUEST_SENSORS:
      return {
        ...state,
        items: null, // reset items
        failure: false,
        isFetching: true
      };
    case RECEIVE_SENSORS:
	  // console.log(action)
      return {
        ...state,
		items: action.items,
        failure: false,
        isFetching: false
      };
    case FAIL_SENSORS:
      return {
        ...state,
        items: null,
        failure: true,
        isFetching: false
      };
    default:
      return state;
  }
}


export const sensorSelector = state => state.sensors && state.sensors.items;

export const sensorListSelector = createSelector(
  sensorSelector,
  (items) => {
	// console.log(items)
    return items ? Object.keys(items).map(key => items[key]) : [];
  }
);