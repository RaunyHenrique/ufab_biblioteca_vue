import axios from 'axios'

const API_URL = '/api';//http://localhost:8080/api???

export const AXIOS = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' //+ localStorage.token
  }
})
