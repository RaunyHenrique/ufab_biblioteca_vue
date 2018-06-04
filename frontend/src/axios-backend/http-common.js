/* global localStorage */

import axios from 'axios'

const API_URL = '/api';

export default axios.create({
  baseURL: API_URL,
  headers: {
    'Accept': 'application/json',
    'Content-Type': 'application/x-www-form-urlencoded',
    'Authorization': localStorage.token
  }
})
