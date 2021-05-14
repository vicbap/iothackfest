export const REQUEST_SENSORS = 'REQUEST_SENSORS';
export const RECEIVE_SENSORS = 'RECEIVE_SENSORS';
export const FAIL_SENSORS = 'FAIL_SENSORS';

export const searchSensors = (query) => (dispatch, getState) => {
	const payload = query || {}
	if (shouldSearchSensors(getState(), query)) {
		dispatch(requestSensors(query));
		fetch('/sensors/all',{ 
			method: 'POST',
			credentials: 'include', 
			headers: { "Content-Type": "application/json", "Accept": "application/json" }, 
			body: JSON.stringify(payload) 
		})
		.then(res => res.json())
		.then(data =>{ dispatch(receiveSensors(query, data)) })
		.catch(() => dispatch(failSensors(query)));
	}
};

const shouldSearchSensors = (state, query) => {
  return state.sensors.failure || !state.sensors.isFetching;
}

const requestSensors = (query) => {
  return {
    type: REQUEST_SENSORS,
    query
  };
};

const receiveSensors = (query, items) => {
  // console.log(items)
  return {
    type: RECEIVE_SENSORS,
    query,
    items
  };
};

const failSensors = (query) => {
  return {
    type: FAIL_SENSORS,
    query
  };
};
