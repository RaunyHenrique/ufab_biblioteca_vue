<template>

  <div>

    <nav>
      <Menu></Menu>
    </nav>

    <header>
      <slot name="header"></slot>
    </header>

    <main>

      <b-container id="container-body">

        <router-view></router-view>

      </b-container>

    </main>

    <footer>
      <slot name="footer"></slot>
    </footer>

  </div>

</template>

<script>

  import Menu from './Menu'

  export default {
    name: "MainLayout",
    components: {Menu},
  }

</script>

<style scoped>
  #container-body {
    margin-top: 60px;
  }
</style>
