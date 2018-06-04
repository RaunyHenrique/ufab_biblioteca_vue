<template>

  <div class="login-overlay">

    <div class="login-wrapper border border-light">

      <form class="form-signin" @submit.prevent="login()">

        <h2 class="form-signin-heading">Biblioteca - UFAB</h2>
        <div class="alert alert-danger" v-if="error">{{ error }}</div>

        <label for="inputEmail" class="sr-only">Email</label>
        <input v-model="username" name="username" type="email" id="inputEmail" class="form-control" placeholder="Email" required autofocus>
        <label for="inputPassword" class="sr-only">Senha</label>
        <input v-model="password" name="password" type="password" id="inputPassword" class="form-control" placeholder="Senha" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>

      </form>

    </div>

  </div>

</template>

<script>

  import { mapGetters } from 'vuex'

  export default {
    name: "Login",
    computed: {
      ...mapGetters({ currentUser: 'currentUser' })
    },
    data () {
      return {
        username: '',
        password: '',
        error: false
      }
    },
    updated () {
      this.checkCurrentLogin();
    },
    created () {
      this.checkCurrentLogin();
    },
    methods: {
      login () {
        var qs = require('qs');
        var data = { username: this.username, password: this.password };
        this.$http.post('/login', qs.stringify(data))
          .then(request => this.loginSuccessful(request))
          .catch(() => this.loginFailed())
      },
      loginSuccessful (req) {
        if (!req) {
          this.loginFailed();
          return
        }

        this.error = false;
        localStorage.token = req;
        this.$store.dispatch('login'); // <=
        this.$router.replace(this.$route.query.redirect || '/')
      },
      loginFailed () {
        this.error = 'Falha no login!';
        this.$store.dispatch('logout'); // <=
        delete localStorage.token
      },
      checkCurrentLogin () {
        if (this.currentUser) {
          this.$router.replace(this.$route.query.redirect || '/')
        }
      },
    }
  }

</script>

<style scoped>

  .login-overlay {
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
  }
  .login-wrapper {
    background: #fff;
    width: 70%;
    margin: 12% auto;
    animation: fadein 0.6s;
  }
  @keyframes fadein {
    from { opacity: 0; }
    to   { opacity: 1; }
  }
  .form-signin {
    max-width: 330px;
    padding: 10% 15px;
    margin: 0 auto;
  }
  .form-signin .form-signin-heading,
  .form-signin .checkbox {
    margin-bottom: 10px;
  }
  .form-signin .checkbox {
    font-weight: normal;
  }
  .form-signin .form-control {
    position: relative;
    height: auto;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    padding: 10px;
    font-size: 16px;
  }
  .form-signin .form-control:focus {
    z-index: 2;
  }
  .form-signin input[type="email"] {
    margin-bottom: -1px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
  }
  .form-signin input[type="password"] {
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;

  }

</style>
