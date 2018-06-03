<template>

  <div id="notFound">

    <img src="@/assets/logo.png" class="center-block logo">

    <div class="text-center col-sm-12">
      <h1>Você está perdido</h1>
      <h4>A pagina solicitada não existe!</h4>
      <router-link to="/" class="vertical-5p lead">Voltar para a página inicial</router-link>
    </div>

  </div>

</template>

<script>
  export default {
    name: "NotFound"
  }
</script>

<style scoped>
  #notFound {
    padding: 10em;
  }
</style>
