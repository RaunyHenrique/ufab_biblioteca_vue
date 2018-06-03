<template>

  <div id="app">

    <nav>
      <Menu></Menu>
    </nav>

    <main>

      <b-container id="container-body">

        <slot></slot>

      </b-container>

    </main>

    <footer>
      <slot name="footer"></slot>
    </footer>

  </div>

</template>

<script>

  import Menu from "./Menu";

  export default {
    name: "MainLayout",
    components: {Menu}
  }

</script>

<style scoped>
  #container-body {
    margin-top: 60px;
  }
</style>
