<template>

  <b-navbar toggleable="md" type="dark" variant="dark">

    <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>

    <b-navbar-brand href="#">
      <img src="@/assets/logo.png" class="d-inline-block align-top" alt="BV" height="30" width="30">
      Biblioteca - UFAB
    </b-navbar-brand>

    <b-collapse is-nav id="nav_collapse">

      <b-navbar-nav>

        <b-nav-item href="#">Home</b-nav-item>
        <b-nav-item href="#">Alunos</b-nav-item>
        <b-nav-item href="#">Cursos</b-nav-item>
        <b-nav-item href="#">Funcionarios</b-nav-item>

        <b-nav-item-dropdown text="Acervo" right>
          <b-dropdown-header>Itens do acervo</b-dropdown-header>
          <b-dropdown-item href="#">Livros</b-dropdown-item>
          <b-dropdown-item href="#">Revistas</b-dropdown-item>
          <b-dropdown-item href="#">TCC's</b-dropdown-item>
          <b-dropdown-item href="#">Jornais</b-dropdown-item>
          <b-dropdown-item href="#">Anais</b-dropdown-item>
          <b-dropdown-item href="#">Midias Eletronicas</b-dropdown-item>
          <b-dropdown-divider></b-dropdown-divider>
          <b-dropdown-header>Outros</b-dropdown-header>
          <b-dropdown-item href="#">Autores</b-dropdown-item>
          <b-dropdown-item href="#">Editoras</b-dropdown-item>
          <b-dropdown-item href="#">Áreas de conhecimento</b-dropdown-item>
          <b-dropdown-item href="#">Temas</b-dropdown-item>
        </b-nav-item-dropdown>

      </b-navbar-nav>

      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">

        <b-nav-form>
          <b-form-input size="sm" class="mr-sm-2" type="text" placeholder="Buscar..."/>
          <b-button size="sm" class="my-2 my-sm-0" variant="primary" type="submit">ok</b-button>
        </b-nav-form>

        <b-nav-item-dropdown right>
          <!-- Using button-content slot -->
          <template slot="button-content">
            <em>Usuário</em>
          </template>
          <b-dropdown-item to="/logout">Logout</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>

    </b-collapse>
  </b-navbar>

</template>

<script>
  export default {
    name: "Menu"
  }
</script>

<style scoped>

</style>
