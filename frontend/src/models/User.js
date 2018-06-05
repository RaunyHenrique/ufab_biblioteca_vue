import JwtDecode from 'jwt-decode'
/* eslint-disable */
export default class User {

  static from (token) {
    try {
      let obj = JwtDecode(token);
      return new User(obj, token)
    } catch (_) {
      return null
    }
  }

  constructor (obj, token) {

    var toJSON = JSON.parse(obj.sub);

    if (toJSON) {

      this.token = token
      this.email = toJSON.email
      this.role = toJSON.auth[0]["authority"]

      console.log(this.token, this.email, this.role)

    }

  }

  get isAdmin () {
    return (this.role.equals("ROLE_ADMIN"))
  }

}
